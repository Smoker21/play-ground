package com.rainsoft.sukebai;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class MoveDialog extends TitleAreaDialog {
	private Text txtSrcFile;
	private Text txtTargetFile;
	private String srcFilePath;
	private String targetFilePath;

	/**
	 * Create the dialog.
	 *
	 * @param parentShell
	 */
	public MoveDialog(final Shell parentShell) {
		super(parentShell);
	}

	public String getSrcFilePath() {
		return this.srcFilePath;
	}

	public void setSrcFilePath(final String srcFilePath) {
		this.srcFilePath = srcFilePath;
	}

	public String getTargetFilePath() {
		return this.targetFilePath;
	}

	public void setTargetFilePath(final String targetFilePath) {
		this.targetFilePath = targetFilePath;
	}

	/**
	 * Create contents of the dialog.
	 *
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(final Composite parent) {
		setTitle("對檔名做最後修改");
		final Composite area = (Composite) super.createDialogArea(parent);
		final Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		final Label label = new Label(container, SWT.NONE);
		label.setBounds(10, 10, 60, 16);
		label.setText("原始檔名");

		txtSrcFile = new Text(container, SWT.BORDER);
		txtSrcFile.setEditable(false);
		txtSrcFile.setBounds(10, 32, 596, 22);
		txtSrcFile.setText(this.srcFilePath);

		final Label label_1 = new Label(container, SWT.NONE);
		label_1.setBounds(10, 72, 60, 16);
		label_1.setText("目標檔名");

		txtTargetFile = new Text(container, SWT.BORDER);
		txtTargetFile.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(final DisposeEvent arg0) {
				targetFilePath = txtTargetFile.getText();
			}
		});
		txtTargetFile.setBounds(10, 96, 596, 22);
		txtTargetFile.setText(this.getTargetFilePath());
		return area;
	}

	/**
	 * Create contents of the button bar.
	 *
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(final Composite parent) {
		final Button button = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		final Button button_1 = createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(622, 300);
	}

}
