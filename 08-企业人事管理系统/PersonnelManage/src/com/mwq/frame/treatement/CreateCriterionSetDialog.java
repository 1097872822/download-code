package com.mwq.frame.treatement;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateCriterionSetDialog extends JDialog {

	private JTextArea explainTextArea;

	private JTextField nameTextField;

	private boolean submit = true;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			CreateCriterionSetDialog dialog = new CreateCriterionSetDialog();
			dialog.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog
	 */
	public CreateCriterionSetDialog() {
		super();
		setModal(true);
		setTitle("新建账套");
		setBounds(100, 100, 350, 250);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				submit = false;
			}
		});

		final JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridBagLayout());
		getContentPane().add(topPanel);

		final JLabel nameLabel = new JLabel();
		nameLabel.setText("账套名称：");
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		topPanel.add(nameLabel, gridBagConstraints);

		nameTextField = new JTextField();
		nameTextField.setColumns(40);
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.gridy = 0;
		gridBagConstraints_1.gridx = 1;
		topPanel.add(nameTextField, gridBagConstraints_1);

		final JLabel explainLabel = new JLabel();
		explainLabel.setText("账套说明：");
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(10, 0, 100, 0);
		gridBagConstraints_2.gridy = 1;
		gridBagConstraints_2.gridx = 0;
		topPanel.add(explainLabel, gridBagConstraints_2);

		explainTextArea = new JTextArea();
		explainTextArea.setLineWrap(true);
		explainTextArea.setRows(6);
		explainTextArea.setColumns(34);
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_3.gridy = 1;
		gridBagConstraints_3.gridx = 1;
		topPanel.add(explainTextArea, gridBagConstraints_3);

		final JPanel bottomPanel = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		bottomPanel.setLayout(flowLayout);
		getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		final JButton exitButton = new JButton();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				submit = false;
				dispose();
			}
		});
		exitButton.setText("退出");
		bottomPanel.add(exitButton);

		final JButton submitButton = new JButton();
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (nameTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请填写账套名称！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if (explainTextArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请填写账套说明！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				dispose();
			}
		});
		submitButton.setText("确定");
		bottomPanel.add(submitButton);

		final JLabel label = new JLabel();
		bottomPanel.add(label);
		//
	}

	public JTextArea getExplainTextArea() {
		return explainTextArea;
	}

	public JTextField getNameTextField() {
		return nameTextField;
	}

	public boolean isSubmit() {
		return submit;
	}

}
