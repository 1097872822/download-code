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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mwq.dao.Dao;

public class MailSetDialog extends JDialog {

	private final Dao dao = Dao.getInstance();
	private final JTextField mailAddress = new JTextField();
	private final JPasswordField mailPassword = new JPasswordField();
	private final Preferences perf = Preferences.userRoot();
	private final JLabel label_2 = new JLabel();
	private final JTextField SMTPAddress = new JTextField();
	public MailSetDialog() {
		super();
		setModal(true);
		setTitle("邮箱设置");
		setBounds(100, 100, 347, 213);

		final JPanel setPanel = new JPanel();
		setPanel.setBorder(new EmptyBorder(30, 0, 20, 10));
		final GridLayout gridLayout = new GridLayout(0, 2);
		gridLayout.setVgap(10);
		gridLayout.setHgap(10);
		setPanel.setLayout(gridLayout);
		getContentPane().add(setPanel, BorderLayout.CENTER);

		String mailName = perf.get("mailName", "mybook@***.com");
		String password = perf.get("password", "111");
		String SMTPserver = perf.get("SMTPserver", "LZW");
		setPanel.add(label_2);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setText("SMTP服务器：");
		setPanel.add(SMTPAddress);
		SMTPAddress.setText(SMTPserver);
		JLabel label = new JLabel();
		JLabel label_3 = new JLabel();
		setPanel.add(label);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("用户名：");
		setPanel.add(mailAddress);
		mailAddress.setText(mailName);
		setPanel.add(label_3);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setText("密码：");
		setPanel.add(mailPassword);
		mailPassword.setText(password);
		final JPanel buttonPanel = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		buttonPanel.setLayout(flowLayout);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		final JButton submitButton = new JButton();
		submitButton.setText("确定");
		submitButton.addActionListener(new OKActionListener());
		buttonPanel.add(submitButton);

		final JButton exitButton = new JButton();
		exitButton.setText("退出");
		exitButton.addActionListener(new ExitActionListener());
		buttonPanel.add(exitButton);

		final JLabel leftLabel = new JLabel();
		leftLabel.setPreferredSize(new Dimension(10, 0));
		getContentPane().add(leftLabel, BorderLayout.WEST);

		final JLabel rightLabel = new JLabel();
		rightLabel.setPreferredSize(new Dimension(10, 0));
		getContentPane().add(rightLabel, BorderLayout.EAST);
	}
	class ExitActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	class OKActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String maild = mailAddress.getText();
			String password = new String(mailPassword.getPassword());
			String SMTPserver = SMTPAddress.getText();
			perf.put("mailName", maild);
			perf.put("password", password);
			perf.put("SMTPserver", SMTPserver);
			JOptionPane.showMessageDialog(MailSetDialog.this, "保存完毕");
			setVisible(false);
		}
	}
}
