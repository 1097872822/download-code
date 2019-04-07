package com.mwq.frame.explorer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import com.mwq.dao.Dao;

public class InfoDialog extends JDialog {

	private JComboBox typeComboBox;

	private JTextArea textArea;

	private final Dao dao = Dao.getInstance();

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			InfoDialog dialog = new InfoDialog("", "", 6, "");
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
	public InfoDialog(String title, String infoType, final int infoNum,
			final String infoContent) {
		super();
		setModal(true);
		setTitle(title);
		setBounds(100, 100, 500, 375);

		final JPanel typePanel = new JPanel();
		final FlowLayout flowLayout_1 = new FlowLayout();
		typePanel.setLayout(flowLayout_1);
		getContentPane().add(typePanel, BorderLayout.NORTH);

		final JLabel typeLabel = new JLabel();
		typeLabel.setText("信息类别：");
		typePanel.add(typeLabel);

		typeComboBox = new JComboBox();
		Vector infoV = dao.sTypeByUsed("info");
		for (int i = 0; i < infoV.size(); i++) {
			Vector info = (Vector) infoV.get(i);
			typeComboBox.addItem(info.get(2));
		}
		typeComboBox.setSelectedItem(infoType);
		typePanel.add(typeComboBox);

		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		if (infoContent != null)
			textArea.setText(infoContent);
		scrollPane.setViewportView(textArea);

		final JPanel buttonPanel = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		buttonPanel.setLayout(flowLayout);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		final JButton submitButton = new JButton();
		submitButton.setText("确定");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String newContent = textArea.getText().trim();
				if (newContent.length() == 0) {
					JOptionPane.showMessageDialog(null, "请填写信息内容！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					String type = typeComboBox.getSelectedItem().toString();
					if (infoContent == null)
						dao.iInfo(type, newContent);
					else
						dao.uInfoByNum(infoNum, type, newContent);
					dispose();
				}
			}
		});
		buttonPanel.add(submitButton);

		final JButton exitButton = new JButton();
		exitButton.setText("退出");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPanel.add(exitButton);

		final JLabel leftLabel = new JLabel();
		leftLabel.setPreferredSize(new Dimension(10, 0));
		getContentPane().add(leftLabel, BorderLayout.WEST);

		final JLabel rightLabel = new JLabel();
		rightLabel.setPreferredSize(new Dimension(10, 0));
		getContentPane().add(rightLabel, BorderLayout.EAST);
		//
	}

}
