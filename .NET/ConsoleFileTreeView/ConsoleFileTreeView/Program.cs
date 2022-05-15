using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleFileTreeView
{
	class Program
	{
		static void Main(string[] Args)
		{
			if (Args.Length == 0)
			{
				Console.Error.WriteLine("No input");
				return;
			}
			if (Directory.Exists(Args[0]) == false)
			{
				Console.Error.WriteLine("Wrong path");
				return;
			}
			DirectoryInfo directoryInfo = new DirectoryInfo(Args[0]);
			MyDirectory myDirectory = new MyDirectory(directoryInfo);
			myDirectory.Print(0);
			Console.WriteLine("\nOldest file: {0}", directoryInfo.GetOldestFile());
			MakeCollection(Args[0]);
			Console.ReadLine();
		}

		public static void MakeCollection(string path)
		{
			SortedDictionary<string, int> collection = new SortedDictionary<string, int>(new MyComparer());
			if (File.Exists(path))
			{
				FileInfo file = new FileInfo(path);
				collection.Add(file.Name, (int)file.Length);
			}
			else if (Directory.Exists(path))
			{
				DirectoryInfo dir = new DirectoryInfo(path);
				foreach (var subdir in dir.GetDirectories())
					collection.Add(subdir.Name, (subdir.GetFiles().Length + subdir.GetDirectories().Length));
				foreach (var file in dir.GetFiles())
					collection.Add(file.Name, (int)file.Length);
			}
			FileStream fileStream = new FileStream("DataFile.dat", FileMode.Create);
			BinaryFormatter binaryFormatter = new BinaryFormatter();
			try
			{
				binaryFormatter.Serialize(fileStream, collection);
			}
			catch (SerializationException ex)
			{
				Console.WriteLine("Serialization error: {0}", ex.Message);
			}
			fileStream.Close();
			Deserialize();
		}

		public static void Deserialize()
		{
			SortedDictionary<string, int> collection = new SortedDictionary<string, int>(new MyComparer());
			FileStream fs = new FileStream("DataFile.dat", FileMode.Open);
			try
			{
				BinaryFormatter bf = new BinaryFormatter();
				collection = (SortedDictionary<string, int>)bf.Deserialize(fs);
			}
			catch (SerializationException ex)
			{
				Console.WriteLine("Serialization error: {0}", ex.Message);
			}
			foreach (var dir in collection)
				Console.WriteLine("{0} -> {1}", dir.Key, dir.Value);
		}
	}
}
