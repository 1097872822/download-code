package com.mwq.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.mwq.PersonnelManage;
import com.mwq.frame.personnel.BringUpSelectedPanel;
import com.mwq.frame.personnel.RecordSelectedPanel;
import com.mwq.frame.personnel.RewardsAndPunishmentPanel;
import com.mwq.frame.personnel.TimecardPanel;
import com.mwq.frame.system.DataPanel;
import com.mwq.frame.system.FrameworkPanel;
import com.mwq.frame.treatement.CriterionSetPanel;
import com.mwq.frame.treatement.PersonnelSetPanel;
import com.mwq.frame.treatement.ReportFormsPanel;
import com.mwq.frame.user.AddUserPanel;
import com.mwq.frame.user.UpdatePasswordDialog;
import com.mwq.hibernate.Dao;
import com.mwq.hibernate.mapping.TbRecord;

public class IndexFrame extends JFrame {

	private JTree tree;

	final JPanel rightPanel = new JPanel();

	private TbRecord record;

	private final Dao dao = Dao.getInstance();

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			IndexFrame frame = new IndexFrame(null);
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame
	 */
	public IndexFrame(TbRecord tbRecord) {
		super();

		this.record = tbRecord;

		setResizable(false);
		setExtendedState(IndexFrame.MAXIMIZED_BOTH);// ���ô�������󻯴�
		setTitle(" T �Ƽ�");
		setBounds(0, 0, 950, 700);
		setBounds(0, 0, 1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		topPanel.setPreferredSize(new Dimension(0, 100));
		getContentPane().add(topPanel, BorderLayout.NORTH);

		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		URL resource = IndexFrame.this.getClass().getResource("/img/logo.JPG");
		ImageIcon icon = new ImageIcon(resource);
		label.setIcon(icon);
		label.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		label.setPreferredSize(new Dimension(160, 0));
		topPanel.add(label, BorderLayout.WEST);

		final JPanel buttonPanel = new JPanel();// �������������
		final GridLayout gridLayout = new GridLayout(1, 0);// ����һ��ˮƽ��ʽ���ֹ���������
		gridLayout.setVgap(6);// ��Ĵ�ֱ���Ϊ6����
		gridLayout.setHgap(6);// ���ˮƽ���Ϊ6����
		buttonPanel.setLayout(gridLayout);// ���ù����������õĲ��ֹ�����Ϊ��ʽ����
		buttonPanel.setBackground(new Color(90, 130, 189));// ���ù��������ı���ɫ
		buttonPanel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));// ���ù����������õı߿���ʽ
		topPanel.add(buttonPanel, BorderLayout.CENTER);// �������������ӵ��ϼ������

		final JButton recordShortcutKeyButton = new JButton();// �������롰���������Ŀ�ݰ�ť
		resource = this.getClass().getResource("/img/record.JPG");
		icon = new ImageIcon(resource);
		recordShortcutKeyButton.setIcon(icon);
		// Ϊ��ť����¼�����������������ť��������¼�
		recordShortcutKeyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightPanel.removeAll();// �Ƴ���������е���������
				rightPanel.add(new RecordSelectedPanel(rightPanel),
						BorderLayout.CENTER);// ���������������ӵ����������
				SwingUtilities.updateComponentTreeUI(rightPanel);// ˢ����������е�����
			}
		});
		buttonPanel.add(recordShortcutKeyButton);

		final JButton timecardShortcutKeyButton = new JButton();
		resource = this.getClass().getResource("/img/timecard.JPG");
		icon = new ImageIcon(resource);
		timecardShortcutKeyButton.setIcon(icon);
		timecardShortcutKeyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightPanel.removeAll();
				rightPanel.add(new TimecardPanel(), BorderLayout.CENTER);
				SwingUtilities.updateComponentTreeUI(rightPanel);
			}
		});
		buttonPanel.add(timecardShortcutKeyButton);

		final JButton rewardsAndPunishmentShortcutKeyButton = new JButton();
		resource = this.getClass().getResource("/img/rewAndPun.JPG");
		icon = new ImageIcon(resource);
		rewardsAndPunishmentShortcutKeyButton.setIcon(icon);
		rewardsAndPunishmentShortcutKeyButton
				.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						rightPanel.removeAll();
						rightPanel.add(new RewardsAndPunishmentPanel(),
								BorderLayout.CENTER);
						SwingUtilities.updateComponentTreeUI(rightPanel);
					}
				});
		buttonPanel.add(rewardsAndPunishmentShortcutKeyButton);

		final JButton reportFormsShortcutKeyButton = new JButton();
		resource = this.getClass().getResource("/img/stat.JPG");
		icon = new ImageIcon(resource);
		reportFormsShortcutKeyButton.setIcon(icon);
		reportFormsShortcutKeyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightPanel.removeAll();
				rightPanel.add(new ReportFormsPanel(), BorderLayout.CENTER);
				SwingUtilities.updateComponentTreeUI(rightPanel);
			}
		});
		buttonPanel.add(reportFormsShortcutKeyButton);

		final JButton basicDataShortcutKeyButton = new JButton();
		resource = this.getClass().getResource("/img/data.JPG");
		icon = new ImageIcon(resource);
		basicDataShortcutKeyButton.setIcon(icon);
		basicDataShortcutKeyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightPanel.removeAll();
				rightPanel.add(new DataPanel(), BorderLayout.CENTER);
				SwingUtilities.updateComponentTreeUI(rightPanel);
			}
		});
		buttonPanel.add(basicDataShortcutKeyButton);

		final JButton updatePasswordShortcutKeyButton = new JButton();
		resource = this.getClass().getResource("/img/password.JPG");
		icon = new ImageIcon(resource);
		updatePasswordShortcutKeyButton.setIcon(icon);
		if (record == null)// ��recordΪnullʱ��˵����ͨ��Ĭ���û���¼�ģ���ʱ�����޸�����
			updatePasswordShortcutKeyButton.setEnabled(false);// ��������������ð�ť������
		updatePasswordShortcutKeyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightPanel.removeAll();
				SwingUtilities.updateComponentTreeUI(rightPanel);
				UpdatePasswordDialog dialog = new UpdatePasswordDialog();// ���������޸�����ĶԻ���
				dialog.setRecord(record);// ����ǰ��¼����Ա�ĵ���������Ի���
				dialog.setVisible(true);// ���öԻ���Ϊ�ɼ��ģ�����ʾ�Ի���
			}
		});
		buttonPanel.add(updatePasswordShortcutKeyButton);

		final JButton counterShortcutKeyButton = new JButton();
		resource = this.getClass().getResource("/img/calculator.JPG");
		icon = new ImageIcon(resource);
		counterShortcutKeyButton.setIcon(icon);
		counterShortcutKeyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desktop desktop = Desktop.getDesktop();// ��õ�ǰϵͳ����
				File file = new File("C:/WINDOWS/system32/calc.exe");// ����һ��ϵͳ����������
				try {
					desktop.open(file);// ��ϵͳ������
				} catch (Exception e1) {// ����ʧ��ʱ��������ʾ��Ϣ
					JOptionPane.showMessageDialog(null, "�ܱ�Ǹ��δ�ܴ�ϵͳ�Դ��ļ�������",
							"������ʾ", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		});
		buttonPanel.add(counterShortcutKeyButton);

		final JButton excelShortcutKeyButton = new JButton();
		resource = this.getClass().getResource("/img/excel.JPG");
		icon = new ImageIcon(resource);
		excelShortcutKeyButton.setIcon(icon);
		excelShortcutKeyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desktop desktop = Desktop.getDesktop();
				File file = new File("res/other/new.xls");
				try {
					desktop.open(file);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "�ܱ�Ǹ�����Ļ�����û�а�װOffice��",
							"������ʾ", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			}
		});
		buttonPanel.add(excelShortcutKeyButton);

		final JButton exitShortcutKeyButton = new JButton();
		resource = this.getClass().getResource("/img/exit.JPG");
		icon = new ImageIcon(resource);
		exitShortcutKeyButton.setIcon(icon);
		exitShortcutKeyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		buttonPanel.add(exitShortcutKeyButton);

		final JPanel leftPanel = new JPanel();
		leftPanel.setBackground(new Color(90, 130, 189));
		leftPanel.setLayout(new BorderLayout());
		leftPanel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		leftPanel.setPreferredSize(new Dimension(160, 0));
		getContentPane().add(leftPanel, BorderLayout.WEST);

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");// �������ĸ����

		DefaultMutableTreeNode personnelNode = new DefaultMutableTreeNode(
				"���¹���");// ��������һ���ӽ��
		personnelNode.add(new DefaultMutableTreeNode("��������"));// ��������Ҷ�ӽ�㲢��ӵ�һ���ӽ��
		personnelNode.add(new DefaultMutableTreeNode("���ڹ���"));
		personnelNode.add(new DefaultMutableTreeNode("���͹���"));
		personnelNode.add(new DefaultMutableTreeNode("��ѵ����"));
		root.add(personnelNode);// ���������һ���ӽ��

		DefaultMutableTreeNode treatmentNode = new DefaultMutableTreeNode(
				"��������");
		treatmentNode.add(new DefaultMutableTreeNode("���׹���"));
		treatmentNode.add(new DefaultMutableTreeNode("��Ա����"));
		treatmentNode.add(new DefaultMutableTreeNode("ͳ�Ʊ���"));
		root.add(treatmentNode);

		DefaultMutableTreeNode systemNode = new DefaultMutableTreeNode("ϵͳά��");
		systemNode.add(new DefaultMutableTreeNode("��ҵ�ܹ�"));
		systemNode.add(new DefaultMutableTreeNode("��������"));
		systemNode.add(new DefaultMutableTreeNode("��ʼ��ϵͳ"));
		root.add(systemNode);

		DefaultMutableTreeNode userNode = new DefaultMutableTreeNode("�û�����");
		if (record == null) {// ��recordΪnullʱ��˵����ͨ��Ĭ���û���¼�ģ���ʱֻ�������û��������޸�����
			userNode.add(new DefaultMutableTreeNode("�����û�"));
		} else {// ����Ϊͨ������Ա��¼
			String purview = record.getTbManager().getPurview();
			if (purview.equals("��������Ա")) {// ֻ�е�����Ա��Ȩ��Ϊ����������Ա��ʱ������Ȩ�����û�
				userNode.add(new DefaultMutableTreeNode("�����û�"));
			}
			userNode.add(new DefaultMutableTreeNode("�޸�����"));// ֻ��ͨ������Ա��¼ʱ����Ȩ�޸�����
		}
		root.add(userNode);

		DefaultMutableTreeNode toolNode = new DefaultMutableTreeNode("ϵͳ����");
		toolNode.add(new DefaultMutableTreeNode("�򿪼�����"));
		toolNode.add(new DefaultMutableTreeNode("��WORD"));
		toolNode.add(new DefaultMutableTreeNode("��EXCEL"));
		root.add(toolNode);

		DefaultTreeModel treeModel = new DefaultTreeModel(root);// ͨ���������󴴽���ģ�Ͷ���

		tree = new JTree(treeModel);// ͨ����ģ�Ͷ��󴴽�������
		tree.setBackground(Color.WHITE);// �������ı���ɫ
		tree.setRootVisible(false);// ���ò���ʾ���ĸ���㣬Ĭ��Ϊ��ʾ����true
		tree.setRowHeight(24);// ���ø����ĸ߶�Ϊ27����
		tree.setFont(new Font("����", Font.BOLD, 14));// ���ý���������ʽ

		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();// ����һ�����Ļ��ƶ���
		// renderer.setLeafIcon(null);// ����Ҷ�ӽ�㲻����ͼ��
		renderer.setClosedIcon(null);// ���ý���۵�ʱ������ͼ��
		renderer.setOpenIcon(null);// ���ý��չ��ʱ������ͼ��
		tree.setCellRenderer(renderer);// �����Ļ��ƶ������õ�����
		int count = root.getChildCount();// ���һ����������
		for (int i = 0; i < count; i++) {// ��������һ�����
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) root
					.getChildAt(i);// ���ָ������λ�õ�һ��������
			TreePath path = new TreePath(node.getPath());// ��ý������·��
			tree.expandPath(path);// չ���ý��
		}
		tree.addTreeSelectionListener(new TreeSelectionListener() {// ��������ѡȡ�¼�
					public void valueChanged(TreeSelectionEvent e) {
						rightPanel.removeAll();
						TreePath treePath = e.getPath();
						if (treePath.getPathCount() == 2) {
							rightPanel
									.add(backgroundLabel, BorderLayout.CENTER);
						} else {
							String selectedNode = treePath
									.getLastPathComponent().toString();
							String parentNode = treePath.getParentPath()
									.getLastPathComponent().toString();
							if (parentNode.equals("���¹���")) {
								System.out.println("���¹���");
								if (selectedNode.equals("��������")) {
									rightPanel.add(new RecordSelectedPanel(
											rightPanel), BorderLayout.CENTER);
								} else if (selectedNode.equals("���ڹ���")) {
									rightPanel.add(new TimecardPanel(),
											BorderLayout.CENTER);
								} else if (selectedNode.equals("���͹���")) {
									rightPanel.add(
											new RewardsAndPunishmentPanel(),
											BorderLayout.CENTER);
								} else {// ������ѵ����
									rightPanel.add(new BringUpSelectedPanel(
											rightPanel), BorderLayout.CENTER);
								}
							} else if (parentNode.equals("��������")) {
								System.out.println("��������");
								if (selectedNode.equals("���׹���")) {
									rightPanel.add(new CriterionSetPanel(),
											BorderLayout.CENTER);
								} else if (selectedNode.equals("��Ա����")) {
									rightPanel.add(new PersonnelSetPanel(),
											BorderLayout.CENTER);
								} else {// ͳ�Ʊ���
									rightPanel.add(new ReportFormsPanel(),
											BorderLayout.CENTER);
								}
							} else if (parentNode.equals("ϵͳά��")) {
								System.out.println("ϵͳά��");
								if (selectedNode.equals("��ҵ�ܹ�")) {
									rightPanel.add(new FrameworkPanel(),
											BorderLayout.CENTER);
								} else if (selectedNode.equals("��������")) {
									rightPanel.add(new DataPanel(),
											BorderLayout.CENTER);
								} else {// ϵͳ��ʼ��
									int i = JOptionPane.showConfirmDialog(null,
											"ȷ��Ҫ��ʼ��ϵͳ��", "������ʾ",
											JOptionPane.YES_NO_OPTION,
											JOptionPane.QUESTION_MESSAGE);
									if (i == 0) {
										dao.initTimecard();
										dao.initBringUpOntent();
										dao.initBringUpContent();
										dao.initRewardsAndPunishment();
										dao.initReckoningInfo();
										dao.initReckoningList();
										dao.initReckoning();
										dao.initDutyInfo();
										dao.initPersonalInfo();
										dao.initManager();
										dao.initRecord();
										dao.initDept();
										dao.initDuty();
										dao.initNation();
										dao.initNativePlace();
										dao.initAccessionForm();
										dao.initAccountItem();
										String clueOn[] = new String[] {
												"    ϵͳ��ʼ����ɣ�",
												"    ϵͳ��������������������Ҫͨ��Ĭ���û���¼��",
												"Ȼ��ͨ�������¹���->����������������µ�����",
												"���ͨ�����û�����->�������û������ϵͳ����",
												"Ա�����ͨ������ӵĹ���Ա��¼��",
												"    ��Ҫע����ǣ�ֻ�е�һ������ӵĹ���ԱΪ",
												"��������Ա�����С������û�����Ȩ�ޣ�" };
										JOptionPane
												.showMessageDialog(
														null,
														clueOn,
														"������ʾ",
														JOptionPane.INFORMATION_MESSAGE);
										dispose();
										new PersonnelManage();
									}
								}
							} else if (parentNode.equals("�û�����")) {
								System.out.println("�û�����");
								if (selectedNode.equals("�����û�")) {
									AddUserPanel addUserPanel = new AddUserPanel();
									addUserPanel.setRecord(record);
									rightPanel.add(addUserPanel,
											BorderLayout.CENTER);
								} else {// �޸�����
									rightPanel.removeAll();
									SwingUtilities
											.updateComponentTreeUI(rightPanel);
									UpdatePasswordDialog updatePasswordDialog = new UpdatePasswordDialog();
									updatePasswordDialog.setRecord(record);
									updatePasswordDialog.setVisible(true);
								}
							} else {
								if (Desktop.isDesktopSupported()) {
									Desktop desktop = Desktop.getDesktop();
									File file = null;
									try {
										if (selectedNode.equals("�򿪼�����")) {
											Runtime runtime = Runtime
													.getRuntime();
											runtime.exec("calc.exe");
										} else if (selectedNode
												.equals("��WORD")) {
											file = new File("src/office/new.doc");
											desktop.open(file);
										} else {// ��EXCEL
											file = new File("src/office/new.xls");
											desktop.open(file);
										}
									} catch (Exception e1) {
										JOptionPane
												.showMessageDialog(
														null,
														"�ܱ�Ǹ��δ�ܴ��������ϵͳ���ߣ�",
														"������ʾ",
														JOptionPane.INFORMATION_MESSAGE);
										return;
									}
								}
							}
						}
						SwingUtilities.updateComponentTreeUI(rightPanel);
					}
				});
		leftPanel.add(tree);

		rightPanel.setLayout(new BorderLayout());
		rightPanel.setPreferredSize(new Dimension(0, 0));
		rightPanel.setBackground(new Color(255, 255, 247));
		rightPanel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		getContentPane().add(rightPanel, BorderLayout.CENTER);

		resource = this.getClass().getResource("/img/back.JPG");
		icon = new ImageIcon(resource);
		backgroundLabel.setIcon(icon);
		backgroundLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		backgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
		rightPanel.add(backgroundLabel, BorderLayout.CENTER);
		//
	}

	final JLabel backgroundLabel = new JLabel();

}
