package com.mwq.frame.user;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import com.mwq.hibernate.Dao;
import com.mwq.hibernate.HibernateSessionFactory;
import com.mwq.hibernate.mapping.TbManager;
import com.mwq.hibernate.mapping.TbRecord;

public class UpdatePasswordDialog extends JDialog {

	private JPasswordField oldPasswordField;

	private JPasswordField repeatPasswordField;

	private JPasswordField newPasswordField;

	private TbRecord record;

	private final Dao dao = Dao.getInstance();

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			UpdatePasswordDialog dialog = new UpdatePasswordDialog();
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
	public UpdatePasswordDialog() {
		super();
		getContentPane().setLayout(new GridBagLayout());
		setModal(true);
		setTitle("修改密码");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((screenSize.width - 300) / 2, (screenSize.height - 200) / 2,
				300, 200);

		final JLabel oldPasswordLabel = new JLabel();
		oldPasswordLabel.setText("原 密 码：");
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.gridx = 0;
		gridBagConstraints_7.gridy = 0;
		getContentPane().add(oldPasswordLabel, gridBagConstraints_7);

		oldPasswordField = new JPasswordField();
		oldPasswordField.setColumns(25);
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.gridwidth = 3;
		gridBagConstraints_8.gridy = 0;
		gridBagConstraints_8.gridx = 1;
		getContentPane().add(oldPasswordField, gridBagConstraints_8);

		final JLabel newPasswordLabel = new JLabel();
		newPasswordLabel.setText("新 密 码：");
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints.gridy = 1;
		gridBagConstraints.gridx = 0;
		getContentPane().add(newPasswordLabel, gridBagConstraints);

		newPasswordField = new JPasswordField();
		newPasswordField.setColumns(25);
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_1.gridwidth = 3;
		gridBagConstraints_1.gridy = 1;
		gridBagConstraints_1.gridx = 1;
		getContentPane().add(newPasswordField, gridBagConstraints_1);

		final JLabel repeatPasswordLabel = new JLabel();
		repeatPasswordLabel.setText("重新输入：");
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_2.gridy = 2;
		gridBagConstraints_2.gridx = 0;
		getContentPane().add(repeatPasswordLabel, gridBagConstraints_2);

		repeatPasswordField = new JPasswordField();
		repeatPasswordField.setColumns(25);
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.gridwidth = 3;
		gridBagConstraints_3.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_3.gridy = 2;
		gridBagConstraints_3.gridx = 1;
		getContentPane().add(repeatPasswordField, gridBagConstraints_3);

		final JLabel label = new JLabel();
		label.setPreferredSize(new Dimension(30, 20));
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.gridy = 3;
		gridBagConstraints_6.gridx = 1;
		getContentPane().add(label, gridBagConstraints_6);

		final JButton submitButton = new JButton();
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] oldPasswords = oldPasswordField.getPassword();
				StringBuffer oldPassword = new StringBuffer();
				for (int i = 0; i < oldPasswords.length; i++) {
					oldPassword.append(oldPasswords[i]);
				}
				char[] newPasswords = newPasswordField.getPassword();
				StringBuffer newPassword = new StringBuffer();
				for (int i = 0; i < newPasswords.length; i++) {
					newPassword.append(newPasswords[i]);
				}
				char[] repeatPasswords = repeatPasswordField.getPassword();
				StringBuffer repeatPassword = new StringBuffer();
				for (int i = 0; i < repeatPasswords.length; i++) {
					repeatPassword.append(repeatPasswords[i]);
				}
				TbManager manager = record.getTbManager();
				if (!oldPassword.toString().equals(manager.getPassword())) {
					JOptionPane.showMessageDialog(null, "您输入的原密码错误，请确认后重新输入！",
							"友情提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if (!newPassword.toString().equals(repeatPassword.toString())) {
					JOptionPane.showMessageDialog(null,
							"您两次输入的新密码不一致，，请确认后重新输入！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				manager.setPassword(newPassword.toString());
				dao.updateObject(manager);
				HibernateSessionFactory.closeSession();
				JOptionPane.showMessageDialog(null, "密码修改成功！", "友情提示",
						JOptionPane.INFORMATION_MESSAGE);
				dispose();
			}
		});
		submitButton.setText("确定");
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_4.gridy = 3;
		gridBagConstraints_4.gridx = 2;
		getContentPane().add(submitButton, gridBagConstraints_4);

		final JButton exitButton = new JButton();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		exitButton.setText("退出");
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.insets = new Insets(10, 10, 0, 0);
		gridBagConstraints_5.gridy = 3;
		gridBagConstraints_5.gridx = 3;
		getContentPane().add(exitButton, gridBagConstraints_5);
		//
	}

	public void setRecord(TbRecord record) {
		this.record = record;
	}

}
