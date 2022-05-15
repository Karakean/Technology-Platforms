using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ConsoleFileTreeView
{
	public static class MyExtensions
	{
		public static string GetDOSAttributes(this FileSystemInfo fsi)
		{
			string[] att = { "-", "r", "a", "h", "s" };
			return att[fsi.Attributes.HasFlag(FileAttributes.ReadOnly) ? 1 : 0] +
					att[fsi.Attributes.HasFlag(FileAttributes.Archive) ? 2 : 0] +
					att[fsi.Attributes.HasFlag(FileAttributes.Hidden) ? 3 : 0] +
					att[fsi.Attributes.HasFlag(FileAttributes.System) ? 4 : 0];
		}

		public static DateTime GetOldestFile(this DirectoryInfo dir)
		{
			DateTime oldest = DateTime.MinValue;
			foreach (var info in dir.EnumerateFileSystemInfos())
			{
				if (info.GetType() == typeof(DirectoryInfo))
				{
					DateTime curr = GetOldestFile(info as DirectoryInfo);
					if (curr > oldest)
						oldest = curr;
				}
				else if (info.GetType() == typeof(FileInfo))
				{
					if (info.CreationTime > oldest)
						oldest = info.CreationTime;
				}
			}
			return oldest;
		}
	}
}
