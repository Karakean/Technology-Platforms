using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace ConsoleFileTreeView
{
	public abstract class MyData
	{
		protected FileSystemInfo info;
		protected abstract string Format(int recursionDepth);
		protected internal abstract void Print(int recursionDepth);
	}
}
