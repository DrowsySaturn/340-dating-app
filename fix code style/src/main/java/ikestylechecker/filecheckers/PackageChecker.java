package ikestylechecker.filecheckers;

import java.util.List;
import java.io.File;
import java.util.Scanner;
import java.io.IOException;

public class PackageChecker implements IFileChecker
{
	public void checkFile(File file, List<CodeStyleProblem> existingProblems)
	{
		try
		{
			if (!checkPackageOnFile(file))
			{
				existingProblems.add(new CodeStyleProblem(file, 1, "Missing package on first line OR capital letters in package name."));
			}
		}
		catch (Exception ex)
		{
			existingProblems.add(CodeStyleProblem.cantReadFileProblem(file));
		}
	}

	private boolean checkPackageOnFile(File file) throws IOException
	{
		Scanner scanner = new Scanner(file);
		if (scanner.hasNextLine())
		{
			String firstLine = scanner.nextLine();
			if (!firstLine.matches("\\s*package\\s+[a-z\\.]+;\\s*"))
			{
				return false;
			}
		}
		return true;
	}
}
