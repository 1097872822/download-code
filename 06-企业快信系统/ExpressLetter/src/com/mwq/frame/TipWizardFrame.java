package com.mwq.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mwq.frame.explorer.LetterSetDialog;
import com.mwq.frame.explorer.MailSetDialog;
import com.mwq.frame.mwing.MButton;
import com.mwq.frame.mwing.MTable;
import com.mwq.frame.user.UpdatePasswordDialog;
import com.mwq.frame.user.UserManagerDialog;

public class TipWizardFrame extends JFrame {

	private MTable sendListTable;

	private Vector<String> sendListTableColumnV;

	private Vector<Vector> sendListTableValueV;

	private DefaultTableModel sendListTableModel;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			TipWizardFrame frame = new TipWizardFrame(null);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame
	 */
	public TipWizardFrame(final Vector user) {
		super();
		setTitle("企业快信");
		setBounds(100, 100, 900, 690);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JToolBar toolBar = new JToolBar();// 创建工具栏对象
		toolBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED));// 设置工具栏的边框样式
		toolBar.setFloatable(false);// 设置工具栏不可移动
		getContentPane().add(toolBar, BorderLayout.NORTH);// 将工具栏添加到面板中

		final MButton userButton = new MButton();// 创建用户管理按钮
		userButton.addActionListener(new ActionListener() {// 添加事件监听器
					public void actionPerformed(ActionEvent e) {// 处理按钮事件
						UserManagerDialog dialog = new UserManagerDialog();// 创建用户管理对话框对象
						dialog.setVisible(true);// 设置用户管理对话框可见
					}
				});
		URL userUrl = this.getClass().getResource("/img/user.png");// 获得按钮默认图片的路径
		userButton.setIcon(new ImageIcon(userUrl));// 设置按钮的默认图片
		URL userOverUrl = this.getClass().getResource("/img/user_over.png");// 获得按钮鼠标经过图片的路径
		userButton.setRolloverIcon(new ImageIcon(userOverUrl));// 设置按钮的鼠标经过图片
		toolBar.add(userButton);// 将按钮添加到工具栏

		final MButton passwordButton = new MButton();
		passwordButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdatePasswordDialog dialog = new UpdatePasswordDialog(user);
				dialog.setVisible(true);
			}
		});
		URL passwordUrl = this.getClass().getResource("/img/password.png");
		passwordButton.setIcon(new ImageIcon(passwordUrl));
		URL passwordOverUrl = this.getClass().getResource(
				"/img/password_over.png");
		passwordButton.setRolloverIcon(new ImageIcon(passwordOverUrl));
		toolBar.add(passwordButton);

		final MButton handsetButton = new MButton();
		handsetButton.addActionListener(new HandsetButtonActionListener());
		URL infoUrl = this.getClass().getResource("/img/info.png");
		handsetButton.setIcon(new ImageIcon(infoUrl));
		URL infoOverUrl = this.getClass().getResource("/img/info_over.png");
		handsetButton.setRolloverIcon(new ImageIcon(infoOverUrl));
		toolBar.add(handsetButton);

		final MButton emailButton = new MButton();
		emailButton.addActionListener(new EmailButtonActionListener());
		URL emailUrl = this.getClass().getResource("/img/email.png");
		emailButton.setIcon(new ImageIcon(emailUrl));
		URL emailOverUrl = this.getClass().getResource("/img/email_over.png");
		emailButton.setRolloverIcon(new ImageIcon(emailOverUrl));
		toolBar.add(emailButton);

		final MButton exitButton = new MButton();
		URL exitUrl = this.getClass().getResource("/img/exit.png");
		exitButton.setIcon(new ImageIcon(exitUrl));
		URL exitOverUrl = this.getClass().getResource("/img/exit_over.png");
		exitButton.setRolloverIcon(new ImageIcon(exitOverUrl));
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		toolBar.add(exitButton);

		final JSplitPane workaroundSplitPane = new JSplitPane();// 创建分割面板对象
		workaroundSplitPane.setDividerSize(12);// 设置分割条的宽度
		workaroundSplitPane.setOneTouchExpandable(true);// 设置为支持快速展开/折叠分割条
		workaroundSplitPane.setDividerLocation(310);// 设置面版默认的分割位置
		workaroundSplitPane.setPreferredSize(new Dimension(0, 590));// 设置分割面板的首选高度
		workaroundSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);// 设置为垂直分割
		getContentPane().add(workaroundSplitPane);

		final JSplitPane sendSplitPane = new JSplitPane();
		sendSplitPane.setOneTouchExpandable(true);// 设置为支持快速展开/折叠分割条
		sendSplitPane.setDividerSize(12);// 设置分割条的宽度
		sendSplitPane.setDividerLocation(244);// 设置面版默认的分割位置
		sendSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);// 设置为水平分割
		workaroundSplitPane.setLeftComponent(sendSplitPane);

		final JPanel sendListPanel = new JPanel();
		sendListPanel.setBorder(new TitledBorder(null, "收信人列表",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		sendListPanel.setLayout(new BorderLayout());
		sendSplitPane.setLeftComponent(sendListPanel);

		final JScrollPane scrollPane = new JScrollPane();
		sendListPanel.add(scrollPane, BorderLayout.CENTER);

		sendListTableColumnV = new Vector<String>();
		sendListTableColumnV.add("序号");
		sendListTableColumnV.add("编号");
		sendListTableColumnV.add("姓名");

		sendListTableValueV = new Vector<Vector>();

		sendListTableModel = new DefaultTableModel(sendListTableValueV,
				sendListTableColumnV);

		sendListTable = new MTable(sendListTableModel);
		scrollPane.setViewportView(sendListTable);

		final JPanel buttonPanel = new JPanel();
		final FlowLayout flowLayout_1 = new FlowLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		buttonPanel.setLayout(flowLayout_1);
		sendListPanel.add(buttonPanel, BorderLayout.SOUTH);

		final MButton cancelButton = new MButton();
		URL cancelUrl = this.getClass()
				.getResource("/img/cancel_personnel.png");
		cancelButton.setIcon(new ImageIcon(cancelUrl));
		URL cancelOverUrl = this.getClass().getResource(
				"/img/cancel_personnel_over.png");
		cancelButton.setRolloverIcon(new ImageIcon(cancelOverUrl));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = sendListTable.getSelectedRows();
				if (selectedRows.length == 0) {
					JOptionPane.showMessageDialog(null, "请选择要取消的人员！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					String[] infos = new String[selectedRows.length + 1];
					infos[0] = "确定要取消以下人员：";
					for (int i = 0; i < selectedRows.length; i++) {
						infos[i + 1] = "    "
								+ sendListTable.getValueAt(selectedRows[i], 1)
								+ "  "
								+ sendListTable.getValueAt(selectedRows[i], 2);
					}
					int i = JOptionPane.showConfirmDialog(null, infos, "友情提示",
							JOptionPane.YES_NO_OPTION);
					if (i == 0) {
						for (int j = selectedRows.length - 1; j >= 0; j--) {
							sendListTableModel.removeRow(selectedRows[j]);
						}
						for (int row = selectedRows[0]; row < sendListTable
								.getRowCount(); row++) {
							sendListTable.setValueAt(row + 1, row, 0);
						}
					}
				}
			}
		});
		buttonPanel.add(cancelButton);

		final MButton clearButton = new MButton();
		URL cancelListUrl = this.getClass().getResource("/img/cancel_list.png");
		clearButton.setIcon(new ImageIcon(cancelListUrl));
		URL cancelListOverUrl = this.getClass().getResource(
				"/img/cancel_list_over.png");
		clearButton.setRolloverIcon(new ImageIcon(cancelListOverUrl));
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = JOptionPane.showConfirmDialog(null, "确定要清空收信人列表？",
						"友情提示", JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					sendListTableValueV.removeAllElements();
					sendListTableModel.setDataVector(sendListTableValueV,
							sendListTableColumnV);
				}
			}
		});
		buttonPanel.add(clearButton);

		final InfoPanel infoPanel = new InfoPanel(sendListTable);
		sendSplitPane.setRightComponent(infoPanel);

		final ExplorerPanel explorerPanel = new ExplorerPanel(
				sendListTableModel, infoPanel.getTabbedPane(), infoPanel
						.getInfoTextArea(), infoPanel.getEmailTextArea());
		workaroundSplitPane.setRightComponent(explorerPanel);
		//
	}

	private class HandsetButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			LetterSetDialog dialog = new LetterSetDialog();
			dialog.setVisible(true);
		}
	}

	private class EmailButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MailSetDialog dialog = new MailSetDialog();
			dialog.setVisible(true);
		}
	}
}
