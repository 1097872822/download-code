package com.mwq.frame;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;

import com.mwq.dao.Dao;

public class LandFrame extends JFrame {

	private JPasswordField passwordField;

	private JComboBox usernameComboBox;

	private Dao dao;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			LandFrame frame = new LandFrame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame
	 */
	public LandFrame() {
		super();
		getContentPane().setLayout(new GridBagLayout());
		setTitle(" T 科技");
		setResizable(false);
		setBounds(100, 100, 400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JLabel softTitleLabel = new JLabel();
		softTitleLabel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		softTitleLabel.setFont(new Font("", Font.BOLD, 22));
		softTitleLabel.setText("  企  业  快  信  ");
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 0, 40, 0);
		gridBagConstraints.gridwidth = 2;
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		getContentPane().add(softTitleLabel, gridBagConstraints);

		final JLabel usernameLabel = new JLabel();
		usernameLabel.setText("登录用户：");
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.gridy = 1;
		gridBagConstraints_1.gridx = 0;
		getContentPane().add(usernameLabel, gridBagConstraints_1);

		usernameComboBox = new JComboBox();
		usernameComboBox.addItem("请选择");
		dao = Dao.getInstance();
		Vector userNameV = dao.sUserNameOfNotFreeze();
		if (userNameV.size() == 0) {
			usernameComboBox.addItem("TSoft");
		} else {
			for (int i = 0; i < userNameV.size(); i++) {
				usernameComboBox.addItem(userNameV.get(i));
			}
		}
		usernameComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = (String) usernameComboBox.getSelectedItem();
				if (userName.equals("TSoft"))
					passwordField.setText("111");
			}
		});
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.anchor = GridBagConstraints.WEST;
		gridBagConstraints_2.gridy = 1;
		gridBagConstraints_2.gridx = 1;
		getContentPane().add(usernameComboBox, gridBagConstraints_2);

		final JLabel passwordLabel = new JLabel();
		passwordLabel.setText("登录密码：");
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_3.gridy = 2;
		gridBagConstraints_3.gridx = 0;
		getContentPane().add(passwordLabel, gridBagConstraints_3);

		passwordField = new JPasswordField();
		passwordField.setText("      ");
		passwordField.addFocusListener(new FocusListener() {
			public void focusGained(FocusEvent e) {
				passwordField.setText("");
			}

			public void focusLost(FocusEvent e) {
				char[] passwords = passwordField.getPassword();
				String password = turnCharsToString(passwords);
				if (password.length() == 0) {
					passwordField.setText("      ");
				}
			}
		});
		passwordField.setColumns(20);
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_4.anchor = GridBagConstraints.WEST;
		gridBagConstraints_4.gridy = 2;
		gridBagConstraints_4.gridx = 1;
		getContentPane().add(passwordField, gridBagConstraints_4);

		final JPanel panel = new JPanel();
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_5.gridwidth = 2;
		gridBagConstraints_5.gridy = 3;
		gridBagConstraints_5.gridx = 0;
		getContentPane().add(panel, gridBagConstraints_5);

		final JButton landButton = new JButton();
		landButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameComboBox.getSelectedItem().toString();
				if (username.equals("请选择")) {
					JOptionPane.showMessageDialog(null, "请选择登录用户！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					passwordField.setText("      ");
				} else {
					char[] passwords = passwordField.getPassword();
					String inputPassword = turnCharsToString(passwords);
					if (username.equals("TSoft")) {
						if (inputPassword.equals("111")) {
							TipWizardFrame tipWizard = new TipWizardFrame(null);
							tipWizard.setVisible(true);
							setVisible(false);
							String infos[] = { "请立刻单击“用户管理”按钮添加用户！",
									"添加用户后需要重新登录，本系统才能正常使用！" };
							JOptionPane.showMessageDialog(null, infos, "友情提示",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null,
									"默认用户“TSoft”的登录密码为“111”！", "友情提示",
									JOptionPane.INFORMATION_MESSAGE);
							passwordField.setText("111");
						}
					} else {
						if (inputPassword.length() == 0) {
							JOptionPane.showMessageDialog(null, "请输入登录密码！",
									"友情提示", JOptionPane.INFORMATION_MESSAGE);
							usernameComboBox.setSelectedIndex(0);
							passwordField.setText("      ");
						} else {
							Vector user = dao.sUserByName(username);
							String freeze = user.get(6).toString();
							String password = user.get(5).toString();
							if (inputPassword.equals(password)) {
								TipWizardFrame tipWizard = new TipWizardFrame(
										user);
								tipWizard.setVisible(true);
								setVisible(false);
							} else {
								JOptionPane.showMessageDialog(null,
										"登录密码错误，请确认后重新登录！", "友情提示",
										JOptionPane.INFORMATION_MESSAGE);
								usernameComboBox.setSelectedIndex(0);
								passwordField.setText("      ");
							}
						}
					}
				}
			}
		});
		landButton.setText("登录");
		panel.add(landButton);

		final JButton resetButton = new JButton();
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				usernameComboBox.setSelectedIndex(0);
				passwordField.setText("      ");
			}
		});
		resetButton.setText("重置");
		panel.add(resetButton);

		final JButton exitButton = new JButton();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		exitButton.setText("退出");
		panel.add(exitButton);

		final JLabel defaultUserLabel = new JLabel();
		defaultUserLabel.setText("默认用户：TSoft    默认密码：111");
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints_6.gridwidth = 2;
		gridBagConstraints_6.gridy = 4;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(defaultUserLabel, gridBagConstraints_6);
		//
	}

	private String turnCharsToString(char[] chars) {
		StringBuffer strBuf = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			strBuf.append(chars[i]);
		}
		return strBuf.toString().trim();
	}

}
