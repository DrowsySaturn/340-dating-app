package ikestylechecker.filecheckers;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Runs checks on the specified file or directory.
 * Able to identify problems according to currently added problem checkers.
 *
 * @author Jonathan Cooper
 *
 * @version sep-19-2018
 */
public class FileCheckRunner
{
	/**
	 * Checks to perform on the files to make sure they're appropriate.
	 */
	private LinkedList<IFileChecker> tasks;

	public FileCheckRunner()
	{
		tasks = new LinkedList<IFileChecker>();
		initTasks();
	}

	public ArrayList<CodeStyleProblem> findProblemsInDirectory(File directory)
	{
		ArrayList<CodeStyleProblem> problems = new ArrayList<CodeStyleProblem>();
		return findProblemsInDirectory(directory, problems);
	}

	public ArrayList<CodeStyleProblem> findProblemsInDirectory(File directory, ArrayList<CodeStyleProblem> currentProblems)
	{
		for (File child : directory.listFiles())
		{
			if (child.isFile() && child.getName().toLowerCase().endsWith(".java"))
			{
				findProblemsInFile(child, currentProblems);
			}
			else if (child.isDirectory())
			{
				findProblemsInDirectory(child, currentProblems);
			}
		}
		return currentProblems;
	}

	private ArrayList<CodeStyleProblem> findProblemsInFile(File file, ArrayList<CodeStyleProblem> currentProblems)
	{
		for (IFileChecker fileChecker : tasks)
		{
			fileChecker.checkFile(file, currentProblems);
		}
		return currentProblems;
	}

	/**
	 * Initializes checking tasks.
	 */
	private void initTasks()
	{
		tasks.add(new TrailingWhitespaceChecker());
		tasks.add(new PackageChecker());
	}
}
