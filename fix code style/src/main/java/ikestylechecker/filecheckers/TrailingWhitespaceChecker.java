package ikestylechecker.filecheckers;

import java.util.Scanner;
import java.util.List;
import java.io.File;
import java.io.IOException;

public class TrailingWhitespaceChecker implements IFileChecker
{
	public void checkFile(File file, List<CodeStyleProblem> existingProblems)
	{
		try
		{
			findBadLines(file, existingProblems);
		}
		catch (Exception ex)
		{
			existingProblems.add(CodeStyleProblem.cantReadFileProblem(file));
		}
	}

	private void findBadLines(File file, List<CodeStyleProblem> problems) throws IOException
	{
		Scanner scanner = new Scanner(file);
		try
		{
			int lineCounter = 1;
			while (scanner.hasNextLine())
			{
				String line = scanner.nextLine();
				if (line.endsWith(" ") || line.endsWith("\t"))
				{
					problems.add(new CodeStyleProblem(file, lineCounter, "Trailing whitespace"));
				}
				lineCounter++;
			}
		}
		finally
		{
			scanner.close();
		}
	}
}
