package com.mwq.frame.explorer;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mwq.dao.Dao;

public class LetterSetDialog extends JDialog {

	private final Dao dao = Dao.getInstance();
	private final JTextField deviceField = new JTextField();
	private final JTextField baudField = new JTextField();
	private final JTextField snField = new JTextField();
	private final Preferences perf=Preferences.userRoot();
	public LetterSetDialog() {
		super();
		setModal(true);
		setTitle("����è����");
		setBounds(100, 100, 347, 204);

		final JPanel setPanel = new JPanel();
		setPanel.setBorder(new EmptyBorder(20, 0, 10, 10));
		final GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		setPanel.setLayout(gridLayout);
		getContentPane().add(setPanel, BorderLayout.CENTER);

		JLabel label = new JLabel("ͨѶ�˿ڣ�");
		JLabel label_2 = new JLabel("������ ��");
		JLabel label_3 = new JLabel("ע���룺");
		String device = perf.get("device","COM1");
		String baud = perf.get("baud","9600");
		String sn = perf.get("sn","YIWU-IJDD-****-****");
		setPanel.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		setPanel.add(deviceField);
		deviceField.setText(device);
		setPanel.add(label_2);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		setPanel.add(baudField);
		baudField.setText(baud);
		setPanel.add(label_3);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		setPanel.add(snField);
		snField.setText(sn);
		final JPanel buttonPanel = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		buttonPanel.setLayout(flowLayout);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		final JButton submitButton = new JButton();
		submitButton.setText("ȷ��");
		submitButton.addActionListener(new OKActionListener());
		buttonPanel.add(submitButton);

		final JButton exitButton = new JButton();
		exitButton.setText("�˳�");
		exitButton.addActionListener(new ExitActionListener());
		buttonPanel.add(exitButton);

		final JLabel leftLabel = new JLabel();
		leftLabel.setPreferredSize(new Dimension(10, 0));
		getContentPane().add(leftLabel, BorderLayout.WEST);

		final JLabel rightLabel = new JLabel();
		rightLabel.setPreferredSize(new Dimension(10, 0));
		getContentPane().add(rightLabel, BorderLayout.EAST);
		//
	}

	class ExitActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}

	class OKActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String device = deviceField.getText();
			String baud = baudField.getText();
			String sn = snField.getText();
			perf.put("device",device);
			perf.put("baud",baud);
			perf.put("sn",sn);
			JOptionPane.showMessageDialog(LetterSetDialog.this, "�������");
			setVisible(false);
		}
	}
}
