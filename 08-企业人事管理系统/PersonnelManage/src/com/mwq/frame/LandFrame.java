package com.mwq.frame;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import com.mwq.hibernate.Dao;
import com.mwq.hibernate.mapping.TbManager;
import com.mwq.hibernate.mapping.TbRecord;

public class LandFrame extends JFrame {

	private JPasswordField passwordField;

	private JComboBox userNumComboBox;

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

		dao = Dao.getInstance();

		final JLabel softTitleLabel = new JLabel();
		softTitleLabel.setFont(new Font("", Font.BOLD, 22));
		softTitleLabel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		softTitleLabel.setText("企业人事管理系统");
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 0, 20, 0);
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		getContentPane().add(softTitleLabel, gridBagConstraints);

		final JPanel userNumPanel = new JPanel();
		userNumPanel.setLayout(new FlowLayout());
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.gridy = 1;
		gridBagConstraints_1.gridx = 0;
		getContentPane().add(userNumPanel, gridBagConstraints_1);

		final JLabel userNumLabel = new JLabel();
		userNumLabel.setVerticalTextPosition(SwingConstants.TOP);
		userNumLabel.setText("管理员：");
		userNumPanel.add(userNumLabel);

		userNumComboBox = new JComboBox();
		userNumComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String username = userNumComboBox.getSelectedItem().toString()
						.trim();
				if (username.equals("TSoft")) {
					passwordField.setText("111");
				}
			}
		});
		userNumComboBox.setPreferredSize(new Dimension(100, 20));
		userNumComboBox.addItem("  请 选 择  ");
		Iterator managerIt = dao.queryManagerOfNatural().iterator();
		if (managerIt.hasNext()) {
			while (managerIt.hasNext()) {
				TbManager manager = (TbManager) managerIt.next();
				StringBuffer item = new StringBuffer("   ");
				item.append(manager.getTbRecord().getRecordNumber());
				userNumComboBox.addItem(item.toString());
			}
		} else {
			userNumComboBox.addItem("   TSoft");
		}
		userNumPanel.add(userNumComboBox);

		final JPanel passwordPanel = new JPanel();
		passwordPanel.setLayout(new FlowLayout());
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.gridy = 2;
		gridBagConstraints_4.gridx = 0;
		getContentPane().add(passwordPanel, gridBagConstraints_4);

		final JLabel passwordLabel = new JLabel();
		passwordLabel.setText("密  码：");
		passwordPanel.add(passwordLabel);

		passwordField = new JPasswordField();
		passwordField.setPreferredSize(new Dimension(100, 20));
		passwordPanel.add(passwordField);

		final JPanel buttonPanel = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		buttonPanel.setLayout(flowLayout);
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.ipadx = 15;
		gridBagConstraints_2.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_2.gridy = 3;
		gridBagConstraints_2.gridx = 0;
		getContentPane().add(buttonPanel, gridBagConstraints_2);

		final JButton landButton = new JButton();
		landButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userNum = userNumComboBox.getSelectedItem().toString()
						.trim();
				if (userNum.equals("请 选 择")) {
					JOptionPane.showMessageDialog(null, "请选择登录管理员！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				char[] passwords = passwordField.getPassword();
				StringBuffer passwordBuf = new StringBuffer();
				for (int i = 0; i < passwords.length; i++) {
					passwordBuf.append(passwords[i]);
				}
				String password = passwordBuf.toString().trim();
				if (password.length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入登录密码！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				IndexFrame indexFrame = null;
				if (userNum.equals("TSoft")) {
					if (password.equals("111")) {
						indexFrame = new IndexFrame(null);
					} else {
						JOptionPane.showMessageDialog(null,
								"默认用户“TSoft”的登录密码为“111”！", "友情提示",
								JOptionPane.INFORMATION_MESSAGE);
						passwordField.setText("111");
						return;
					}
				} else {
					TbRecord record = (TbRecord) dao.queryRecordByNum(userNum);
					TbManager manager = record.getTbManager();
					if (password.equals(manager.getPassword())) {
						indexFrame = new IndexFrame(record);
					} else {
						JOptionPane.showMessageDialog(null, "登录密码错误，请确认后重新输入！",
								"友情提示", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
				indexFrame.setVisible(true);
				setVisible(false);
			}
		});
		landButton.setMargin(new Insets(2, 6, 2, 6));
		landButton.setText("登录");
		buttonPanel.add(landButton);

		final JButton resetButton = new JButton();
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userNumComboBox.setSelectedIndex(0);
				passwordField.setText(null);
			}
		});
		resetButton.setMargin(new Insets(2, 6, 2, 6));
		resetButton.setText("清空");
		buttonPanel.add(resetButton);

		final JButton exitButton = new JButton();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		exitButton.setMargin(new Insets(2, 6, 2, 6));
		exitButton.setText("退出");
		buttonPanel.add(exitButton);

		final JLabel defaultLabel = new JLabel();
		defaultLabel.setText("默认用户：TSoft    默认密码：111");
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_3.gridy = 4;
		gridBagConstraints_3.gridx = 0;
		getContentPane().add(defaultLabel, gridBagConstraints_3);
		//
	}

}
