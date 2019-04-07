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
		setExtendedState(IndexFrame.MAXIMIZED_BOTH);// 设置窗口以最大化打开
		setTitle(" T 科技");
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

		final JPanel buttonPanel = new JPanel();// 创建工具栏面板
		final GridLayout gridLayout = new GridLayout(1, 0);// 创建一个水平箱式布局管理器对象
		gridLayout.setVgap(6);// 箱的垂直间隔为6像素
		gridLayout.setHgap(6);// 箱的水平间隔为6像素
		buttonPanel.setLayout(gridLayout);// 设置工具栏面板采用的布局管理器为箱式布局
		buttonPanel.setBackground(new Color(90, 130, 189));// 设置工具栏面板的背景色
		buttonPanel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));// 设置工具栏面板采用的边框样式
		topPanel.add(buttonPanel, BorderLayout.CENTER);// 将工具栏面板添加到上级面板中

		final JButton recordShortcutKeyButton = new JButton();// 创建进入“档案管理”的快捷按钮
		resource = this.getClass().getResource("/img/record.JPG");
		icon = new ImageIcon(resource);
		recordShortcutKeyButton.setIcon(icon);
		// 为按钮添加事件监听器，用来捕获按钮被点击的事件
		recordShortcutKeyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightPanel.removeAll();// 移除内容面板中的所有内容
				rightPanel.add(new RecordSelectedPanel(rightPanel),
						BorderLayout.CENTER);// 将档案管理面版添加到内容面板中
				SwingUtilities.updateComponentTreeUI(rightPanel);// 刷新内容面板中的内容
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
		if (record == null)// 当record为null时，说明是通过默认用户登录的，此时不能修改密码
			updatePasswordShortcutKeyButton.setEnabled(false);// 在这种情况下设置按钮不可用
		updatePasswordShortcutKeyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightPanel.removeAll();
				SwingUtilities.updateComponentTreeUI(rightPanel);
				UpdatePasswordDialog dialog = new UpdatePasswordDialog();// 创建用来修改密码的对话框
				dialog.setRecord(record);// 将当前登录管理员的档案对象传入对话框
				dialog.setVisible(true);// 设置对话框为可见的，即显示对话框
			}
		});
		buttonPanel.add(updatePasswordShortcutKeyButton);

		final JButton counterShortcutKeyButton = new JButton();
		resource = this.getClass().getResource("/img/calculator.JPG");
		icon = new ImageIcon(resource);
		counterShortcutKeyButton.setIcon(icon);
		counterShortcutKeyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desktop desktop = Desktop.getDesktop();// 获得当前系统对象
				File file = new File("C:/WINDOWS/system32/calc.exe");// 创建一个系统计算器对象
				try {
					desktop.open(file);// 打开系统计算器
				} catch (Exception e1) {// 当打开失败时，弹出提示信息
					JOptionPane.showMessageDialog(null, "很抱歉，未能打开系统自带的计算器！",
							"友情提示", JOptionPane.INFORMATION_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "很抱歉，您的机器还没有安装Office！",
							"友情提示", JOptionPane.INFORMATION_MESSAGE);
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

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("root");// 创建树的根结点

		DefaultMutableTreeNode personnelNode = new DefaultMutableTreeNode(
				"人事管理");// 创建树的一级子结点
		personnelNode.add(new DefaultMutableTreeNode("档案管理"));// 创建树的叶子结点并添加到一级子结点
		personnelNode.add(new DefaultMutableTreeNode("考勤管理"));
		personnelNode.add(new DefaultMutableTreeNode("奖惩管理"));
		personnelNode.add(new DefaultMutableTreeNode("培训管理"));
		root.add(personnelNode);// 向根结点添加一级子结点

		DefaultMutableTreeNode treatmentNode = new DefaultMutableTreeNode(
				"待遇管理");
		treatmentNode.add(new DefaultMutableTreeNode("账套管理"));
		treatmentNode.add(new DefaultMutableTreeNode("人员设置"));
		treatmentNode.add(new DefaultMutableTreeNode("统计报表"));
		root.add(treatmentNode);

		DefaultMutableTreeNode systemNode = new DefaultMutableTreeNode("系统维护");
		systemNode.add(new DefaultMutableTreeNode("企业架构"));
		systemNode.add(new DefaultMutableTreeNode("基本资料"));
		systemNode.add(new DefaultMutableTreeNode("初始化系统"));
		root.add(systemNode);

		DefaultMutableTreeNode userNode = new DefaultMutableTreeNode("用户管理");
		if (record == null) {// 当record为null时，说明是通过默认用户登录的，此时只能新增用户，不能修改密码
			userNode.add(new DefaultMutableTreeNode("新增用户"));
		} else {// 否则为通过管理员登录
			String purview = record.getTbManager().getPurview();
			if (purview.equals("超级管理员")) {// 只有当管理员的权限为“超级管理员”时，才有权新增用户
				userNode.add(new DefaultMutableTreeNode("新增用户"));
			}
			userNode.add(new DefaultMutableTreeNode("修改密码"));// 只有通过管理员登录时才有权修改密码
		}
		root.add(userNode);

		DefaultMutableTreeNode toolNode = new DefaultMutableTreeNode("系统工具");
		toolNode.add(new DefaultMutableTreeNode("打开计算器"));
		toolNode.add(new DefaultMutableTreeNode("打开WORD"));
		toolNode.add(new DefaultMutableTreeNode("打开EXCEL"));
		root.add(toolNode);

		DefaultTreeModel treeModel = new DefaultTreeModel(root);// 通过树结点对象创建树模型对象

		tree = new JTree(treeModel);// 通过树模型对象创建树对象
		tree.setBackground(Color.WHITE);// 设置树的背景色
		tree.setRootVisible(false);// 设置不显示树的根结点，默认为显示，即true
		tree.setRowHeight(24);// 设置各结点的高度为27像素
		tree.setFont(new Font("宋体", Font.BOLD, 14));// 设置结点的字体样式

		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();// 创建一个树的绘制对象
		// renderer.setLeafIcon(null);// 设置叶子结点不采用图标
		renderer.setClosedIcon(null);// 设置结点折叠时不采用图标
		renderer.setOpenIcon(null);// 设置结点展开时不采用图标
		tree.setCellRenderer(renderer);// 将树的绘制对象设置到树中
		int count = root.getChildCount();// 获得一级结点的数量
		for (int i = 0; i < count; i++) {// 遍历树的一级结点
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) root
					.getChildAt(i);// 获得指定索引位置的一级结点对象
			TreePath path = new TreePath(node.getPath());// 获得结点对象的路径
			tree.expandPath(path);// 展开该结点
		}
		tree.addTreeSelectionListener(new TreeSelectionListener() {// 捕获树的选取事件
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
							if (parentNode.equals("人事管理")) {
								System.out.println("人事管理");
								if (selectedNode.equals("档案管理")) {
									rightPanel.add(new RecordSelectedPanel(
											rightPanel), BorderLayout.CENTER);
								} else if (selectedNode.equals("考勤管理")) {
									rightPanel.add(new TimecardPanel(),
											BorderLayout.CENTER);
								} else if (selectedNode.equals("奖惩管理")) {
									rightPanel.add(
											new RewardsAndPunishmentPanel(),
											BorderLayout.CENTER);
								} else {// 人事培训管理
									rightPanel.add(new BringUpSelectedPanel(
											rightPanel), BorderLayout.CENTER);
								}
							} else if (parentNode.equals("待遇管理")) {
								System.out.println("待遇管理");
								if (selectedNode.equals("账套管理")) {
									rightPanel.add(new CriterionSetPanel(),
											BorderLayout.CENTER);
								} else if (selectedNode.equals("人员设置")) {
									rightPanel.add(new PersonnelSetPanel(),
											BorderLayout.CENTER);
								} else {// 统计报表
									rightPanel.add(new ReportFormsPanel(),
											BorderLayout.CENTER);
								}
							} else if (parentNode.equals("系统维护")) {
								System.out.println("系统维护");
								if (selectedNode.equals("企业架构")) {
									rightPanel.add(new FrameworkPanel(),
											BorderLayout.CENTER);
								} else if (selectedNode.equals("基本资料")) {
									rightPanel.add(new DataPanel(),
											BorderLayout.CENTER);
								} else {// 系统初始化
									int i = JOptionPane.showConfirmDialog(null,
											"确定要初始化系统？", "友情提示",
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
												"    系统初始化完成！",
												"    系统将重新启动，您首先需要通过默认用户登录，",
												"然后通过“人事管理”->“档案管理”添加人事档案，",
												"其次通过“用户管理”->“新增用户”添加系统管理",
												"员，最后通过新添加的管理员登录！",
												"    需要注意的是，只有第一个被添加的管理员为",
												"超级管理员，具有“新增用户”的权限！" };
										JOptionPane
												.showMessageDialog(
														null,
														clueOn,
														"友情提示",
														JOptionPane.INFORMATION_MESSAGE);
										dispose();
										new PersonnelManage();
									}
								}
							} else if (parentNode.equals("用户管理")) {
								System.out.println("用户管理");
								if (selectedNode.equals("新增用户")) {
									AddUserPanel addUserPanel = new AddUserPanel();
									addUserPanel.setRecord(record);
									rightPanel.add(addUserPanel,
											BorderLayout.CENTER);
								} else {// 修改密码
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
										if (selectedNode.equals("打开计算器")) {
											Runtime runtime = Runtime
													.getRuntime();
											runtime.exec("calc.exe");
										} else if (selectedNode
												.equals("打开WORD")) {
											file = new File("src/office/new.doc");
											desktop.open(file);
										} else {// 打开EXCEL
											file = new File("src/office/new.xls");
											desktop.open(file);
										}
									} catch (Exception e1) {
										JOptionPane
												.showMessageDialog(
														null,
														"很抱歉，未能打开您请求的系统工具！",
														"友情提示",
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
