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
		setTitle("��ҵ����");
		setBounds(100, 100, 900, 690);
		setExtendedState(MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JToolBar toolBar = new JToolBar();// ��������������
		toolBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED));// ���ù������ı߿���ʽ
		toolBar.setFloatable(false);// ���ù����������ƶ�
		getContentPane().add(toolBar, BorderLayout.NORTH);// ����������ӵ������

		final MButton userButton = new MButton();// �����û�����ť
		userButton.addActionListener(new ActionListener() {// ����¼�������
					public void actionPerformed(ActionEvent e) {// ����ť�¼�
						UserManagerDialog dialog = new UserManagerDialog();// �����û�����Ի������
						dialog.setVisible(true);// �����û�����Ի���ɼ�
					}
				});
		URL userUrl = this.getClass().getResource("/img/user.png");// ��ð�ťĬ��ͼƬ��·��
		userButton.setIcon(new ImageIcon(userUrl));// ���ð�ť��Ĭ��ͼƬ
		URL userOverUrl = this.getClass().getResource("/img/user_over.png");// ��ð�ť��꾭��ͼƬ��·��
		userButton.setRolloverIcon(new ImageIcon(userOverUrl));// ���ð�ť����꾭��ͼƬ
		toolBar.add(userButton);// ����ť��ӵ�������

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

		final JSplitPane workaroundSplitPane = new JSplitPane();// �����ָ�������
		workaroundSplitPane.setDividerSize(12);// ���÷ָ����Ŀ��
		workaroundSplitPane.setOneTouchExpandable(true);// ����Ϊ֧�ֿ���չ��/�۵��ָ���
		workaroundSplitPane.setDividerLocation(310);// �������Ĭ�ϵķָ�λ��
		workaroundSplitPane.setPreferredSize(new Dimension(0, 590));// ���÷ָ�������ѡ�߶�
		workaroundSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);// ����Ϊ��ֱ�ָ�
		getContentPane().add(workaroundSplitPane);

		final JSplitPane sendSplitPane = new JSplitPane();
		sendSplitPane.setOneTouchExpandable(true);// ����Ϊ֧�ֿ���չ��/�۵��ָ���
		sendSplitPane.setDividerSize(12);// ���÷ָ����Ŀ��
		sendSplitPane.setDividerLocation(244);// �������Ĭ�ϵķָ�λ��
		sendSplitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);// ����Ϊˮƽ�ָ�
		workaroundSplitPane.setLeftComponent(sendSplitPane);

		final JPanel sendListPanel = new JPanel();
		sendListPanel.setBorder(new TitledBorder(null, "�������б�",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		sendListPanel.setLayout(new BorderLayout());
		sendSplitPane.setLeftComponent(sendListPanel);

		final JScrollPane scrollPane = new JScrollPane();
		sendListPanel.add(scrollPane, BorderLayout.CENTER);

		sendListTableColumnV = new Vector<String>();
		sendListTableColumnV.add("���");
		sendListTableColumnV.add("���");
		sendListTableColumnV.add("����");

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
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫȡ������Ա��", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					String[] infos = new String[selectedRows.length + 1];
					infos[0] = "ȷ��Ҫȡ��������Ա��";
					for (int i = 0; i < selectedRows.length; i++) {
						infos[i + 1] = "    "
								+ sendListTable.getValueAt(selectedRows[i], 1)
								+ "  "
								+ sendListTable.getValueAt(selectedRows[i], 2);
					}
					int i = JOptionPane.showConfirmDialog(null, infos, "������ʾ",
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
				int i = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ����������б�",
						"������ʾ", JOptionPane.YES_NO_OPTION);
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
