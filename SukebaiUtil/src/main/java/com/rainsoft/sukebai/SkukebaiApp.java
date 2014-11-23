package com.rainsoft.sukebai;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class SkukebaiApp {

	protected Shell shlSukebaiUtilities;
	private Text txtSourcePath;
	private Table tblSource;
	private Menu menuSource;
	private Table tblTarget;
	private Text txtTargetPath;
	private Button btnFileChooser;
	private Button btnGo;
	private final DateFormat df = DateFormat.getDateInstance();
	private Text txtFileLength;
	private String srcFilePath;
	private String targetFilePath;

	/**
	 * Launch the application.
	 *
	 * @param args
	 */
	public static void main(final String[] args) {
		try {
			final SkukebaiApp window = new SkukebaiApp();
			window.open();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		try {
			final Display display = Display.getDefault();
			createContents();
			shlSukebaiUtilities.open();
			shlSukebaiUtilities.layout();
			while (!shlSukebaiUtilities.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (final RuntimeException e) {
			MessageDialog.openError(shlSukebaiUtilities, "Error", e.getMessage());
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlSukebaiUtilities = new Shell();
		shlSukebaiUtilities.setSize(1292, 669);
		shlSukebaiUtilities.setText("SukeBei 檔名尋找幫手");
		shlSukebaiUtilities.setLayout(null);

		final Label lblSelectADirectory = new Label(shlSukebaiUtilities, SWT.NONE);
		lblSelectADirectory.setBounds(5, 10, 108, 16);
		lblSelectADirectory.setText("選取或輸入檔案路徑");

		txtSourcePath = new Text(shlSukebaiUtilities, SWT.BORDER);
		txtSourcePath.setBounds(120, 7, 243, 22);

		initBtnFileChooser();
		initBtnGo();

		final Label lblTargetDirectory = new Label(shlSukebaiUtilities, SWT.NONE);
		lblTargetDirectory.setBounds(646, 8, 91, 16);
		lblTargetDirectory.setText("更名後搬移目標");

		txtTargetPath = new Text(shlSukebaiUtilities, SWT.BORDER);
		txtTargetPath.setBounds(742, 5, 206, 22);

		final Button btnFiles = new Button(shlSukebaiUtilities, SWT.NONE);
		btnFiles.setBounds(954, 5, 36, 26);
		btnFiles.setText("Files");

		tblSource = initSrcTable();
		menuSource = initSrcMenu();
		tblSource.setMenu(menuSource);

		tblTarget = new Table(shlSukebaiUtilities, SWT.BORDER | SWT.FULL_SELECTION);

		tblTarget.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(final MouseEvent e) {
				final MoveDialog dialog = new MoveDialog(shlSukebaiUtilities);
				srcFilePath = tblSource.getItem(tblSource.getSelectionIndex()).getText();
				dialog.setSrcFilePath(srcFilePath);
				if (StringUtils.isNoneBlank(txtTargetPath.getText())) {
					final String _path = txtTargetPath.getText();
					final String _filename = tblTarget.getItem(tblTarget.getSelectionIndex()).getText();
					final String _ext = FilenameUtils.getExtension(srcFilePath);
					targetFilePath = _path + _filename + "." + _ext;
				} else {
					final String _path = FilenameUtils.getFullPath(srcFilePath);
					final String _filename = tblTarget.getItem(tblTarget.getSelectionIndex()).getText();
					final String _ext = FilenameUtils.getExtension(srcFilePath);
					targetFilePath = _path + _filename + "." + _ext;
				}
				dialog.setTargetFilePath(targetFilePath);
				if (Window.OK == dialog.open()) {
					try {
						System.out.println(dialog.getTargetFilePath());
						FileUtils.moveFile(new File(dialog.getSrcFilePath()), new File(dialog.getTargetFilePath()));
						tblSource.remove(tblSource.getSelectionIndex());
					} catch (final IOException e1) {
						final StringWriter sw = new StringWriter();
						final PrintWriter pw = new PrintWriter(sw);
						e1.printStackTrace(pw);
						pw.close();
						MessageDialog.openError(shlSukebaiUtilities, "Error", sw.toString());
						e1.printStackTrace();
					}
				}
			}
		});
		tblTarget.setBounds(646, 35, 620, 586);
		tblTarget.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
			}
		});
		tblTarget.setHeaderVisible(true);
		tblTarget.setLinesVisible(true);

		final TableColumn tblTargetcol = new TableColumn(tblTarget, SWT.NONE);
		tblTargetcol.setWidth(501);
		tblTargetcol.setText("Candidate File Name");

		txtFileLength = new Text(shlSukebaiUtilities, SWT.BORDER);
		txtFileLength.setText("12");
		txtFileLength.setBounds(555, 7, 70, 22);

		final Label label = new Label(shlSukebaiUtilities, SWT.NONE);
		label.setBounds(469, 10, 80, 16);
		label.setText("最長檔名長度");

	}

	/**
	 *
	 */
	private void initBtnFileChooser() {
		btnFileChooser = new Button(shlSukebaiUtilities, SWT.NONE);
		btnFileChooser.setBounds(369, 5, 36, 26);
		btnFileChooser.setText("Files");
		// file action
		btnFileChooser.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final DirectoryDialog dialog = new DirectoryDialog(shlSukebaiUtilities, SWT.MULTI);
				dialog.setMessage("Please select a file path");
				final String dir = dialog.open();
				if (StringUtils.isNoneBlank(dir)) {
					txtSourcePath.setText(dir);
				}
			}
		});
	}

	/**
	 *
	 */
	private void initBtnGo() {
		btnGo = new Button(shlSukebaiUtilities, SWT.NONE);
		btnGo.setBounds(411, 5, 29, 26);
		btnGo.setText("Go");
		// go action
		btnGo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				if (StringUtils.isBlank(txtSourcePath.getText())) {
					MessageDialog.openError(shlSukebaiUtilities, "Error", "Please select a file path");
				}
				updateSrcTbl(txtSourcePath.getText());
			}

		});
	}

	protected void updateSrcTbl(final String text) {
		tblSource.removeAll();
		final Collection<File> files = FileUtils.listFiles(new File(txtSourcePath.getText()), new String[] { "avi", "mp4", "mkv",
				"wmv" }, true);
		final List<File> fileList = new ArrayList<File>(files);
		Collections.sort(fileList, new Comparator<File>() {
			@Override
			public int compare(final File f1, final File f2) {
				if (f1.lastModified() == f2.lastModified()) {
					return 0;
				}
				return (f1.lastModified() > f2.lastModified()) ? -1 : 1;
			}
		});
		for (final File file : fileList) {
			final String realName = (FilenameUtils.getBaseName(file.getAbsolutePath()));
			// only list the shorter file name
			if (StringUtils.trimToEmpty(realName).length() < Integer.parseInt(txtFileLength.getText())) {
				final TableItem _item = new TableItem(tblSource, SWT.NONE);
				_item.addListener(SWT.MouseDoubleClick, new SearchSukebeiListener());
				_item.setText(0, file.getAbsolutePath());
				_item.setText(1, df.format(new Date(file.lastModified())));
			}
		}
	}

	private Table initSrcTable() {
		final Table table = new Table(shlSukebaiUtilities, SWT.BORDER | SWT.MULTI);
		table.setBounds(5, 36, 635, 585);
		table.addMouseListener(new SearchSukebeiListener());
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		final TableColumn tblSourceCol = new TableColumn(table, SWT.NONE);
		tblSourceCol.setWidth(500);
		tblSourceCol.setText("Source File Name");

		final TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(120);
		tblclmnNewColumn.setText("Modified Date");
		return table;
	}

	private Menu initSrcMenu() {
		final Menu srcMenu = new Menu(tblSource);
		// open menu item
		final MenuItem srcMnItmOpen = new MenuItem(srcMenu, SWT.NONE);
		srcMnItmOpen.setText("Open");
		srcMnItmOpen.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final int idx = tblSource.getSelectionIndex();
				final TableItem item = tblSource.getItem(idx);
				Program.launch(item.getText());
			}
		});

		// search menu item
		final MenuItem srcMnItmFind = new MenuItem(srcMenu, SWT.NONE);
		srcMnItmFind.setText("Find on Sukebai");
		srcMnItmFind.addSelectionListener(new SearchSukebeiListener());

		final MenuItem srcMnItmOpenFolder = new MenuItem(srcMenu, SWT.NONE);
		srcMnItmOpenFolder.setText("Open folder");
		srcMnItmOpenFolder.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(final SelectionEvent e) {
				final int idx = tblSource.getSelectionIndex();
				final TableItem item = tblSource.getItem(idx);
				try {
					Runtime.getRuntime().exec("explorer.exe /select," + item.getText());
				} catch (final IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		return srcMenu;
	}

	class SearchSukebeiListener implements Listener, MouseListener, SelectionListener {

		private void searchSukebei() {
			tblTarget.removeAll();
			final int idx = tblSource.getSelectionIndex();
			final TableItem item = tblSource.getItem(idx);
			String searchStr = FilenameUtils.getBaseName(item.getText());
			System.out.println("searchStr:" + searchStr);
			final Collection<String> candidateNames = SukebaiFindUtils.searchByFileName(searchStr);
			searchStr = SukebaiFindUtils.separateAlphaNumber(searchStr);
			System.out.println("searchStr:" + searchStr);
			candidateNames.addAll(SukebaiFindUtils.searchByFileName(searchStr));
			// if nothing found, use original
			if (candidateNames.isEmpty()) {
				candidateNames.add(FilenameUtils.getBaseName(item.getText()));
			}
			for (final String name : candidateNames) {
				final TableItem _item = new TableItem(tblTarget, SWT.NONE);
				_item.setText(name);
			}
			tblTarget.getColumn(0).pack();

		}

		@Override
		public void widgetSelected(final SelectionEvent arg0) {
			this.searchSukebei();

		}

		@Override
		public void mouseDoubleClick(final MouseEvent event) {
			this.searchSukebei();
		}

		@Override
		public void mouseDown(final MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseUp(final MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void handleEvent(final Event evt) {
			final TableItem item = (TableItem) evt.widget;
			System.out.println(item.getText());

		}

		@Override
		public void widgetDefaultSelected(final SelectionEvent arg0) {
			// TODO Auto-generated method stub

		}

	}
}
