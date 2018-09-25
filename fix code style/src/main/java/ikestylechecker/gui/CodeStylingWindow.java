package ikestylechecker.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import ikestylechecker.filecheckers.*;
import ikestylechecker.gui.*;

public class CodeStylingWindow extends JFrame
{
	private NormalLayoutManager normalLayoutManager;
	private JTextField filePathTextField;
	private JButton browseFileButton;
	private JButton scanFileButton;
	private JScrollPane errorsListScrollPane;
	private JTextArea errorsListTextArea;
	private File selectedDirectory;
	private FileCheckRunner fileCheckRunner;

	public CodeStylingWindow()
	{
		fileCheckRunner = new FileCheckRunner();
		selectedDirectory = new File(".");
		enableSystemLooks();
		setTitle("Code styler");
		normalLayoutManager = new NormalLayoutManager();
		setLayout(normalLayoutManager);
		initializeComponents();
		setSize(350, 450);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void initializeComponents()
	{
		filePathTextField = new JTextField();
		filePathTextField.setPreferredSize(new Dimension(5, 20));
		add(filePathTextField, new AnchorSettings(5, 110, AnchorSettings.NO_ANCHOR, 5));

		browseFileButton = new JButton("Browse");
		browseFileButton.setPreferredSize(new Dimension(100, 20));
		add(browseFileButton, new AnchorSettings(5, 5, AnchorSettings.NO_ANCHOR, AnchorSettings.NO_ANCHOR));

		scanFileButton = new JButton("Scan file");
		scanFileButton.setPreferredSize(new Dimension(150, 20));
		add(scanFileButton, new AnchorSettings(20 + 5 + 5, 5, AnchorSettings.NO_ANCHOR, AnchorSettings.NO_ANCHOR));

		errorsListTextArea = new JTextArea("Code styler\nPick a directory to begin.");
		errorsListScrollPane = new JScrollPane(errorsListTextArea);
		add(errorsListScrollPane, new AnchorSettings(20 + 5 + 5 + 20 + 5, 5, 5, 5));

		browseFileButton.addActionListener(this::onBrowseClick);
		scanFileButton.addActionListener(this::onScanClicked);
	}

	private void addStyleProblem(CodeStyleProblem problem)
	{
		String oldErrorList = errorsListTextArea.getText();
		String newErrorList = oldErrorList
			+ problem.getFile()
			+ ":"
			+ problem.getLine()
			+ ":"
			+ problem.getMessage() + "\n\n";
		errorsListTextArea.setText(newErrorList);
	}

	/**
	 * Lists all of the errors in a directory.
	 */
	private void listErrorsForDirectory(File dir) throws IOException
	{
		errorsListTextArea.setText("Checking files...");
		repaint();
		ArrayList<CodeStyleProblem> problems = fileCheckRunner.findProblemsInDirectory(dir);
		errorsListTextArea.setText("");
		for (CodeStyleProblem problem : problems)
		{
			addStyleProblem(problem);
		}
		errorsListTextArea.setText(errorsListTextArea.getText() + "End of errors\n");
		repaint();
	}

	private void onScanClicked(ActionEvent evt)
	{
		try
		{
			listErrorsForDirectory(selectedDirectory);
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Uncaught error: " + ex.getMessage());
		}
	}

	private void onFileSelected(File file) throws IOException
	{
		if (file == null)
		{
			throw new IllegalArgumentException();
		}
		filePathTextField.setText(file.getAbsolutePath());
		selectedDirectory = file;
		repaint();
	}

	private void onBrowseClick(ActionEvent evt)
	{
		try
		{
			JFileChooser fileChooser = new JFileChooser(new File("."));
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int optionClicked = fileChooser.showOpenDialog(this);
			if (optionClicked == JFileChooser.APPROVE_OPTION)
			{
				onFileSelected(fileChooser.getSelectedFile());
			}
		}
		catch (Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Uncaught error: " + ex.getMessage());
		}
	}

	private void enableSystemLooks()
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (Exception ex)
		{
			// Oh well, no platform looks.
		}
	}

	public static void main(String[] args)
	{
		new CodeStylingWindow();
	}
}
