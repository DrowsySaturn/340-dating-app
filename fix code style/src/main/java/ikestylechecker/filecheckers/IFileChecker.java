package ikestylechecker.filecheckers;

import java.util.List;
import java.io.File;
import ikestylechecker.filecheckers.CodeStyleProblem;

/**
 * Interface to implement for file checkers.
 *
 * @author Jonathan Cooper
 *
 * @version sep-19-2018
 */
public interface IFileChecker
{
	public void checkFile(File file, List<CodeStyleProblem> existingProblems);
}
