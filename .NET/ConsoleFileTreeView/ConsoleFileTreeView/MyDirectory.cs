using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace ConsoleFileTreeView
{
	public class MyDirectory : MyData
	{
		IList<MyData> children;

		public MyDirectory(DirectoryInfo directoryInfo)
		{
			this.info = directoryInfo;
			this.children = new List<MyData>();
			foreach (FileSystemInfo fsi in directoryInfo.EnumerateFileSystemInfos())
			{
				if (fsi.GetType() == typeof(FileInfo))
					this.children.Add(new MyFile(fsi as FileInfo));
				else if (fsi.GetType() == typeof(DirectoryInfo))
					this.children.Add(new MyDirectory(fsi as DirectoryInfo));
			}
		}

		protected override string Format(int depth)
		{
			string format = "";
			for (int i = 0; i < depth; ++i)
				format += '\t';
			format += String.Format("{0} ({1}) {2}", info.Name, ((DirectoryInfo)info).GetFileSystemInfos().Length, info.GetDOSAttributes());
			return format;
		}

		protected internal override void Print(int recursionDepth)
		{
			Console.WriteLine(this.Format(recursionDepth));
			foreach (MyData child in children)
				child.Print(recursionDepth + 1);
		}
	}
}
