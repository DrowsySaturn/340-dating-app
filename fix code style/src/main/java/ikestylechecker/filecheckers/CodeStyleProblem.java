package ikestylechecker.filecheckers;

import java.io.File;

/**
 * An exception to handle issues in files.
 *
 * @author Jonathan Cooper
 *
 * @version sep-19-2018
 */
public class CodeStyleProblem
{
	public static CodeStyleProblem cantReadFileProblem(File file)
	{
		return new CodeStyleProblem(file, 1, "Can't read file.");
	}

	private File file;

	private int line;

	private String message;

	public CodeStyleProblem(File file, int line, String message)
	{
		this.file = file;
		this.line = line;
		this.message = message;
	}

	public File getFile()
	{
		return file;
	}

	public int getLine()
	{
		return line;
	}

	public String getMessage()
	{
		return message;
	}
}
