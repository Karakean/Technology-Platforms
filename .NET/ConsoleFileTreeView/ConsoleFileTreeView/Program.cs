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
			MyDirectory diskDirectory = new MyDirectory(directoryInfo);
			diskDirectory.Print();
			Console.WriteLine("\nOldest file: {0}", directoryInfo.GetOldestFile());
			CreateCollection(Args[0]);
			Console.ReadLine();
		}

		public static void CreateCollection(string path)
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

			FileStream fs = new FileStream("DataFile.dat", FileMode.Create);
			BinaryFormatter formatter = new BinaryFormatter();

			try
			{
				formatter.Serialize(fs, collection);
			}
			catch (SerializationException e)
			{
				Console.WriteLine("Serialization error: {0}", e.Message);
			}
			fs.Close();
			Deserialize();
		}

		public static void Deserialize()
		{
			SortedDictionary<string, int> collection = new SortedDictionary<string, int>(new MyComparer());
			FileStream fs = new FileStream("DataFile.dat", FileMode.Open);

			try
			{
				BinaryFormatter formatter = new BinaryFormatter();
				collection = (SortedDictionary<string, int>)formatter.Deserialize(fs);
			}
			catch (SerializationException e)
			{
				Console.WriteLine("Serialization error: {0}", e.Message);
			}

			foreach (var file in collection)
				Console.WriteLine("{0} -> {1}", file.Key, file.Value);
		}
	}

    internal class Comparator : IDictionary<string, int>
    {
        public int this[string key] { get => throw new NotImplementedException(); set => throw new NotImplementedException(); }

        public ICollection<string> Keys => throw new NotImplementedException();

        public ICollection<int> Values => throw new NotImplementedException();

        public int Count => throw new NotImplementedException();

        public bool IsReadOnly => throw new NotImplementedException();

        public void Add(string key, int value)
        {
            throw new NotImplementedException();
        }

        public void Add(KeyValuePair<string, int> item)
        {
            throw new NotImplementedException();
        }

        public void Clear()
        {
            throw new NotImplementedException();
        }

        public bool Contains(KeyValuePair<string, int> item)
        {
            throw new NotImplementedException();
        }

        public bool ContainsKey(string key)
        {
            throw new NotImplementedException();
        }

        public void CopyTo(KeyValuePair<string, int>[] array, int arrayIndex)
        {
            throw new NotImplementedException();
        }

        public IEnumerator<KeyValuePair<string, int>> GetEnumerator()
        {
            throw new NotImplementedException();
        }

        public bool Remove(string key)
        {
            throw new NotImplementedException();
        }

        public bool Remove(KeyValuePair<string, int> item)
        {
            throw new NotImplementedException();
        }

        public bool TryGetValue(string key, out int value)
        {
            throw new NotImplementedException();
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            throw new NotImplementedException();
        }
    }
}
