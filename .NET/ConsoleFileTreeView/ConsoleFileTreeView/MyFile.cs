using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace ConsoleFileTreeView
{
	public class MyFile : MyData
	{
		public MyFile(FileInfo fileInfo) { 
			this.info = fileInfo; 
		}

		protected override string Format(int recursionDepth)
		{
			string format = "";
			for (int i = 0; i < recursionDepth; ++i)
				format += '\t';
			format += String.Format("{0} {1} bytes {2}", info.Name, ((FileInfo)info).Length, info.GetDOSAttributes());
			return format;
		}

		protected internal override void Print(int recursionDepth)
		{
			Console.WriteLine(this.Format(recursionDepth));
		}
	}
}
