package com.mwq.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.mwq.dao.Dao;
import com.mwq.frame.explorer.InfoDialog;
import com.mwq.frame.explorer.PersonnelDialog;
import com.mwq.frame.mwing.MButton;
import com.mwq.frame.mwing.MTable;

public class ExplorerPanel extends JPanel {

	private MTable infoListTable;

	private Vector<String> infoListTableColumnV;

	private Vector<Vector> infoListTableValueV;

	private DefaultTableModel infoListTableModel;

	private JTree infoTree;

	private DefaultMutableTreeNode infoTreeRoot;

	private DefaultTreeModel infoTreeModel;

	private MTable cardListTable;

	private Vector<String> cardListTableColumnV;

	private Vector<Vector> cardListTableValueV;

	private DefaultTableModel cardListTableModel;

	private JTree cardTree;

	private DefaultMutableTreeNode cardTreeRoot;

	private DefaultTreeModel cardTreeModel;

	private final Dao dao = Dao.getInstance();

	/**
	 * Create the panel
	 */
	public ExplorerPanel(final DefaultTableModel sendListTableModel,
			final JTabbedPane infoTabbedPane, final JTextArea infoTextArea,
			final JTextArea emailTextArea) {
		super();
		setLayout(new BorderLayout());

		final JTabbedPane tabbedPane = new JTabbedPane();
		add(tabbedPane, BorderLayout.CENTER);

		final JSplitPane cardSplitPane = new JSplitPane();
		cardSplitPane.setOneTouchExpandable(true);
		cardSplitPane.setDividerSize(12);
		cardSplitPane.setDividerLocation(244);
		tabbedPane.addTab("名片夹", null, cardSplitPane, null);

		final JPanel cardTreePanel = new JPanel();
		cardSplitPane.setLeftComponent(cardTreePanel);
		cardTreePanel.setLayout(new BorderLayout());

		final JScrollPane cardTreeScrollPane = new JScrollPane();// 创建显示名片夹树的滚动面板
		cardTreePanel.add(cardTreeScrollPane);// 添加到上级面板中

		cardTreeRoot = new DefaultMutableTreeNode("root");// 创建名片夹树的根节点
		initTree(cardTreeRoot, "card");// 初始化名片夹树

		cardTreeModel = new DefaultTreeModel(cardTreeRoot);// 创建名片夹树模型

		cardTree = new JTree(cardTreeModel);// 创建名片夹树
		cardTree.setRootVisible(false);// 设置名片夹树的根节点不可见
		System.out.println(cardTree.getSelectionModel().getSelectionMode());
		cardTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);// 设置名片夹树的选择模式为单选
		if (cardTreeRoot.getChildCount() > 0)
			cardTree.setSelectionRow(0);// 如果名片夹树存在子节点，则设置选中第一个子节点
		cardTree.addTreeSelectionListener(new TreeSelectionListener() {// 为名片夹树添加接点选中事件监听器
					public void valueChanged(TreeSelectionEvent e) {
						initCardListTable();// 初始化名片夹列表
					}
				});
		cardTreeScrollPane.setViewportView(cardTree);// 将名片夹树添加到滚动面板中

		final JPanel cardTreeButtonPanel = new JPanel();
		final FlowLayout flowLayout_1 = new FlowLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		cardTreeButtonPanel.setLayout(flowLayout_1);
		cardTreePanel.add(cardTreeButtonPanel, BorderLayout.SOUTH);

		final MButton addCardTypeButton = new MButton();
		addCardTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = addTreeNode(cardTreeRoot, "名片夹");// 获得新名片夹名称
				if (name != null) {// 当用户取消新建时名称为空
					int childCount = cardTreeRoot.getChildCount();// 获得当前拥有名片夹的数量
					cardTreeModel.insertNodeInto(new DefaultMutableTreeNode(
							name), cardTreeRoot, childCount);// 在名片夹树的最后创建新的名片夹
					cardTreeModel.reload();// 刷新名片夹树模型
					cardTree.setSelectionRow(childCount);// 设置新建名片夹为选中状态
					dao.iType(name, "card");// 将新建名片夹保存到数据库中
				}
			}
		});
		URL creCardTypeUrl = this.getClass().getResource("/img/add_tree.png");
		addCardTypeButton.setIcon(new ImageIcon(creCardTypeUrl));
		URL creCardTypeOverUrl = this.getClass().getResource(
				"/img/add_tree_over.png");
		addCardTypeButton.setRolloverIcon(new ImageIcon(creCardTypeOverUrl));
		cardTreeButtonPanel.add(addCardTypeButton);

		final MButton updCardTypeButton = new MButton();
		updCardTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath selectionPath = cardTree.getSelectionPath();// 获得选中节点的路径对象
				if (selectionPath == null) {// 判断路径是否为空
					JOptionPane.showMessageDialog(null, "请选择要修改的名片夹！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);// 如果为空则弹出提示
				} else {
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) selectionPath
							.getLastPathComponent();// 获得选中节点对象
					String nowName = treeNode.getUserObject().toString();// 获得选中节点的名称
					int i = JOptionPane.showConfirmDialog(null, "确定要修改名片夹“"
							+ nowName + "”的名称？", "友情提示",
							JOptionPane.YES_NO_OPTION);// 弹出确认修改提示框
					if (i == 0) {// 如果为0则修改
						String newName = updateTreeNode(treeNode);// 获得修改后的名称
						if (newName != null) {// 判断修改后的名称是否为空，如果为空则用户取消了修改
							treeNode.setUserObject(newName);// 修改节点名称
							cardTreeModel.reload();// 刷新树
							cardTree.setSelectionPath(selectionPath);// 设置修改的节点为选中节点
							dao.uTypeNameByName("card", nowName, newName);// 将修改后的名称保存到数据库
						}
					}
				}
			}
		});
		URL updCardTypeUrl = this.getClass().getResource("/img/upd_tree.png");
		updCardTypeButton.setIcon(new ImageIcon(updCardTypeUrl));
		URL updCardTypeOverUrl = this.getClass().getResource(
				"/img/upd_tree_over.png");
		updCardTypeButton.setRolloverIcon(new ImageIcon(updCardTypeOverUrl));
		cardTreeButtonPanel.add(updCardTypeButton);

		final MButton delCardTypeButton = new MButton();
		delCardTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) cardTree
						.getLastSelectedPathComponent();// 获得选中的名片夹对象
				if (treeNode == null) {// 未选择要删除的名片夹
					JOptionPane.showMessageDialog(null, "请选择要删除的名片夹！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);// 弹出提示信息
					return;// 并直接返回
				}
				String name = treeNode.getUserObject().toString();// 获得欲删除名片夹的名称
				int i = JOptionPane.showConfirmDialog(null, "确定要删除名片夹“" + name
						+ "”？", "友情提示", JOptionPane.YES_NO_OPTION);// 弹出删除的确认提示
				if (i != 0)// 用户取消了删除操作
					return;// 直接返回
				if (dao.sPersonnelVByTypeName(name).size() > 0) {// 该名片夹中包含名片
					String options[] = { "取消", "移入其他名片夹", "删除" };// 定义对其包含名片的处理方式
					int optionIndex = JOptionPane.showOptionDialog(null,
							"请选择对该名片夹下人员的处理方式：", "友情提示",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]);// 弹出提示信息，令用户选择提供的处理方式
					if (optionIndex == 0)// 用户取消了删除操作
						return;// 直接返回
					int typeId = dao.sTypeIdByUsedAndName("card", name);// 获得名片夹的主键ID
					if (optionIndex == 1) {// 用户选择移入其他名片夹
						Vector<Vector> cardV = dao.sTypeByUsedExcept("card",
								typeId);// 查询可移入的名片夹
						String[] cards = new String[cardV.size() + 1];// 创建一个选择项数组
						cards[0] = "请选择";// 添加一个提示选择项
						for (int j = 0; j < cardV.size(); j++) {// 初始化选择项数组
							cards[j + 1] = cardV.get(j).get(2).toString();// 添加可移入的名片夹
						}
						Object card = "请选择";// 默认为选中“请选择”
						while (card.equals("请选择")) {// 当选中的为“请选择”时执行循环
							card = JOptionPane.showInputDialog(null,
									"请选择要移入的名片夹：", "友情提示",
									JOptionPane.INFORMATION_MESSAGE, null,
									cards, cards[0]);// 弹出对话框令用户选择欲移入的名片夹
							if (card == null)// 用户取消了删除操作
								return;// 直接返回
						}
						int newTypeId = dao.sTypeIdByUsedAndName("card", card
								.toString());// 获得欲移入名片夹的主键ID
						dao.uPersonnelTypeIdByTypeId(typeId, newTypeId);// 修改名片的外键
					}
					if (optionIndex == 2) {// 用户选择删除其包含的名片
						dao.dPersonnelByTypeId(typeId);// 从数据库删除其包含的名片
					}
				}
				cardTreeModel.removeNodeFromParent(treeNode);// 从名片夹树中删除名片夹
				dao.dTypeByName("card", name);// 从数据库中删除名片夹
			}
		});
		URL delCardTypeUrl = this.getClass().getResource("/img/del_tree.png");
		delCardTypeButton.setIcon(new ImageIcon(delCardTypeUrl));
		URL delCardTypeOverUrl = this.getClass().getResource(
				"/img/del_tree_over.png");
		delCardTypeButton.setRolloverIcon(new ImageIcon(delCardTypeOverUrl));
		cardTreeButtonPanel.add(delCardTypeButton);

		final JPanel cardListPanel = new JPanel();
		cardSplitPane.setRightComponent(cardListPanel);
		cardListPanel.setLayout(new BorderLayout());

		final JScrollPane cardListScrollPane = new JScrollPane();
		cardListPanel.add(cardListScrollPane);

		cardListTableColumnV = new Vector<String>();
		String cardListTableColumns[] = { "序号", "编号", "姓名", "性别", "出生日期", "公司",
				"部门", "职务", "移动电话", "E-mail" };
		for (int i = 0; i < cardListTableColumns.length; i++) {
			cardListTableColumnV.add(cardListTableColumns[i]);
		}

		cardListTableValueV = new Vector<Vector>();

		cardListTableModel = new DefaultTableModel(cardListTableValueV,
				cardListTableColumnV);

		cardListTable = new MTable(cardListTableModel);
		initCardListTable();
		cardListScrollPane.setViewportView(cardListTable);

		final JPanel cardButtonPanel = new JPanel();
		cardButtonPanel.setLayout(new BoxLayout(cardButtonPanel,
				BoxLayout.Y_AXIS));
		cardListPanel.add(cardButtonPanel, BorderLayout.EAST);

		final MButton selAllButton = new MButton();
		URL selAllUrl = this.getClass().getResource("/img/select_all.png");
		selAllButton.setIcon(new ImageIcon(selAllUrl));
		URL selAllOverUrl = this.getClass().getResource(
				"/img/select_all_over.png");
		selAllButton.setRolloverIcon(new ImageIcon(selAllOverUrl));
		selAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardListTable.selectAll();// 选中表格中的所有行
			}
		});
		cardButtonPanel.add(selAllButton);

		final MButton addToSendListButton = new MButton();
		URL addToSendListUrl = this.getClass().getResource(
				"/img/add_to_list.png");
		addToSendListButton.setIcon(new ImageIcon(addToSendListUrl));
		URL addToSendListOverUrl = this.getClass().getResource(
				"/img/add_to_list_over.png");
		addToSendListButton
				.setRolloverIcon(new ImageIcon(addToSendListOverUrl));
		addToSendListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowCount = sendListTableModel.getRowCount();// 获得当前收信人列表中的收信人个数
				int[] selectedRows = cardListTable.getSelectedRows();// 获得名片列表中的选中行
				int index = rowCount + 1;// 初始化收信人列表的序号
				for (int selectedRow = 0; selectedRow < selectedRows.length; selectedRow++) {// 遍历选中行
					int newNum = (Integer) cardListTable.getValueAt(
							selectedRows[selectedRow], 1);// 获得名片编号
					boolean had = false;// 默认为未加入收信人列表
					for (int row = 0; row < rowCount; row++) {// 遍历收信人列表
						int nowNum = (Integer) sendListTableModel.getValueAt(
								row, 1);// 获得收信人的编号
						if (newNum == nowNum) {// 判断名片编号和收信人编号是否相同
							had = true;// 已经加入收信人列表
							break;// 跳出循环
						}
					}
					if (!had) {// 未加入收信人列表
						Vector rowV = new Vector();// 创建一个代表收信人的向量
						rowV.add(index++);// 添加序号
						rowV.add(newNum);// 添加编号
						rowV.add(cardListTable.getValueAt(
								selectedRows[selectedRow], 2));// 添加姓名
						sendListTableModel.addRow(rowV);// 加入收信人列表
					}
				}
				cardListTable.clearSelection();// 取消名片列表中的选中行
			}
		});
		cardButtonPanel.add(addToSendListButton);

		final MButton addCardButton = new MButton();
		URL addCardUrl = this.getClass().getResource("/img/add_info.png");
		addCardButton.setIcon(new ImageIcon(addCardUrl));
		URL addCardOverUrl = this.getClass().getResource(
				"/img/add_info_over.png");
		addCardButton.setRolloverIcon(new ImageIcon(addCardOverUrl));
		addCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cardTreeRoot.getChildCount() == 0) {// 判断是否存在名片夹
					JOptionPane.showMessageDialog(null, "请先建立名片夹！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);// 弹出建立名片夹的提示
				} else {
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) cardTree
							.getLastSelectedPathComponent();// 获得当前选中的名片夹
					String cardName = treeNode.getUserObject().toString();// 获得名片夹名称
					PersonnelDialog personnelDialog = new PersonnelDialog(
							"添加名片", cardName, -1);// 创建添加名片的对话框对象
					personnelDialog.setVisible(true);// 设置添加名片的对话框为可见
					initCardListTable();// 刷新名片列表
				}
			}
		});
		cardButtonPanel.add(addCardButton);

		final MButton updCardButton = new MButton();
		URL updCardUrl = this.getClass().getResource("/img/upd_info.png");
		updCardButton.setIcon(new ImageIcon(updCardUrl));
		URL updCardOverUrl = this.getClass().getResource(
				"/img/upd_info_over.png");
		updCardButton.setRolloverIcon(new ImageIcon(updCardOverUrl));
		updCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = cardListTable.getSelectedRows();// 获得名片列表中的选中行
				if (selectedRows.length == 1) {// 仅选中了一个名片
					int num = (Integer) cardListTable.getValueAt(
							selectedRows[0], 1);// 获得选中名片的编号
					PersonnelDialog personnelDialog = new PersonnelDialog(
							"修改名片", "", num);// 创建修改名片的对话框对象
					personnelDialog.setVisible(true);// 设置修改名片的对话框为可见
					initCardListTable();
				} else {
					if (selectedRows.length == 0) {// 未选中要修改的名片
						JOptionPane.showMessageDialog(null, "请选择要修改的人员！",
								"友情提示", JOptionPane.INFORMATION_MESSAGE);// 弹出提示信息
					} else {// 选中了多个名片
						JOptionPane.showMessageDialog(null, "一次只能修改一个人员！",
								"友情提示", JOptionPane.INFORMATION_MESSAGE);// 弹出提示信息
					}
				}
			}
		});
		cardButtonPanel.add(updCardButton);

		final MButton delCardButton = new MButton();
		URL delCardUrl = this.getClass().getResource("/img/del_info.png");
		delCardButton.setIcon(new ImageIcon(delCardUrl));
		URL delCardOverUrl = this.getClass().getResource(
				"/img/del_info_over.png");
		delCardButton.setRolloverIcon(new ImageIcon(delCardOverUrl));
		delCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = cardListTable.getSelectedRows();// 获得名片列表中的选中行
				if (selectedRows.length == 0) {// 未选中要删除的名片
					JOptionPane.showMessageDialog(null, "请选择要删除的人员！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);// 弹出提示信息
				} else {
					String[] infos = new String[selectedRows.length + 1];// 组织提示信息
					infos[0] = "确定要删除以下人员：";// 添加提示信息
					for (int i = 0; i < selectedRows.length; i++) {// 遍历选中的名片
						infos[i + 1] = "    "
								+ cardListTable.getValueAt(selectedRows[i], 1)// 获得名片编号
								+ "  "
								+ cardListTable.getValueAt(selectedRows[i], 2);// 获得名片名称
					}
					int i = JOptionPane.showConfirmDialog(null, infos, "友情提示",
							JOptionPane.YES_NO_OPTION);// 弹出提示信息
					if (i == 0) {// 确定删除
						for (int j = 0; j < selectedRows.length; j++) {// 遍历选中的名片
							int num = (Integer) cardListTable.getValueAt(
									selectedRows[j], 1);// 获得名片编号
							dao.dPersonnelByNum(num);// 从数据库删除
						}
						initCardListTable();// 刷新名片列表
					}
				}
			}
		});
		cardButtonPanel.add(delCardButton);

		final JSplitPane infoSplitPane = new JSplitPane();
		infoSplitPane.setOneTouchExpandable(true);
		infoSplitPane.setDividerSize(12);
		infoSplitPane.setDividerLocation(244);
		tabbedPane.addTab("信息库", null, infoSplitPane, null);

		final JPanel infoTreePanel = new JPanel();
		infoSplitPane.setLeftComponent(infoTreePanel);
		infoTreePanel.setLayout(new BorderLayout());

		final JScrollPane infoTreeScrollPane = new JScrollPane();
		infoTreePanel.add(infoTreeScrollPane);

		infoTreeRoot = new DefaultMutableTreeNode("root");
		initTree(infoTreeRoot, "info");

		infoTreeModel = new DefaultTreeModel(infoTreeRoot);

		infoTree = new JTree(infoTreeModel);
		infoTree.setRootVisible(false);
		if (infoTreeRoot.getChildCount() > 0)
			infoTree.setSelectionRow(0);
		infoTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				initInfoListTable();
			}
		});
		infoTreeScrollPane.setViewportView(infoTree);

		final JPanel infoTreeButtonPanel = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		infoTreeButtonPanel.setLayout(flowLayout);
		infoTreePanel.add(infoTreeButtonPanel, BorderLayout.SOUTH);

		final MButton addInfoTypeButton = new MButton();
		addInfoTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = addTreeNode(infoTreeRoot, "信息库");
				if (name != null) {
					int childCount = infoTreeRoot.getChildCount();
					infoTreeModel.insertNodeInto(new DefaultMutableTreeNode(
							name), infoTreeRoot, childCount);
					infoTreeModel.reload();
					infoTree.setSelectionRow(childCount);
					dao.iType(name, "info");
				}
			}
		});
		URL creInfoTypeUrl = this.getClass().getResource("/img/add_tree.png");
		addInfoTypeButton.setIcon(new ImageIcon(creInfoTypeUrl));
		URL creInfoTypeOverUrl = this.getClass().getResource(
				"/img/add_tree_over.png");
		addInfoTypeButton.setRolloverIcon(new ImageIcon(creInfoTypeOverUrl));
		infoTreeButtonPanel.add(addInfoTypeButton);

		final MButton updInfoTypeButton = new MButton();
		updInfoTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath selectionPath = infoTree.getSelectionPath();
				if (selectionPath == null) {
					JOptionPane.showMessageDialog(null, "请选择要修改的信息库！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) selectionPath
							.getLastPathComponent();
					String nowName = treeNode.getUserObject().toString();
					int i = JOptionPane.showConfirmDialog(null, "确定要修改信息库“"
							+ nowName + "”的名称？", "友情提示",
							JOptionPane.YES_NO_OPTION);
					if (i == 0) {
						String newName = updateTreeNode(treeNode);
						if (newName != null) {
							treeNode.setUserObject(newName);
							infoTreeModel.reload();
							infoTree.setSelectionPath(selectionPath);
							dao.uTypeNameByName("info", nowName, newName);
						}
					}
				}
			}
		});
		URL updInfoTypeUrl = this.getClass().getResource("/img/upd_tree.png");
		updInfoTypeButton.setIcon(new ImageIcon(updInfoTypeUrl));
		URL updInfoTypeOverUrl = this.getClass().getResource(
				"/img/upd_tree_over.png");
		updInfoTypeButton.setRolloverIcon(new ImageIcon(updInfoTypeOverUrl));
		infoTreeButtonPanel.add(updInfoTypeButton);

		final MButton delInfoTypeButton = new MButton();
		delInfoTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) infoTree
						.getLastSelectedPathComponent();
				if (treeNode == null) {// 未选择要删除的信息库
					JOptionPane.showMessageDialog(null, "请选择要删除的信息库！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String name = treeNode.getUserObject().toString();
				int i = JOptionPane.showConfirmDialog(null, "确定要删除信息库“" + name
						+ "”？", "友情提示", JOptionPane.YES_NO_OPTION);
				if (i != 0)// 用户取消了删除操作
					return;
				if (dao.sInfoVByTypeName(name).size() > 0) {
					String options[] = { "取消", "移入其他信息库", "删除" };
					int optionIndex = JOptionPane.showOptionDialog(null,
							"请选择对该信息库下信息的处理方式：", "友情提示",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]);
					int typeId = dao.sTypeIdByUsedAndName("info", name);
					if (optionIndex == 0)// 用户取消了删除操作
						return;
					if (optionIndex == 1) {// 移入其他信息库
						Vector infoV = dao.sTypeByUsedExcept("info", typeId);
						String[] infos = new String[infoV.size() + 1];
						infos[0] = "请选择";
						for (int j = 0; j < infoV.size(); j++) {
							infos[j + 1] = ((Vector) infoV.get(j)).get(2)
									.toString();
						}
						Object info = "请选择";
						while (info.equals("请选择")) {
							info = JOptionPane.showInputDialog(null,
									"请选择要移入的信息库：", "友情提示",
									JOptionPane.INFORMATION_MESSAGE, null,
									infos, infos[0]);
							if (info == null)// 用户取消了删除操作
								return;
						}
						int newTypeId = dao.sTypeIdByUsedAndName("info", info
								.toString());
						dao.uPersonnelTypeIdByTypeId(typeId, newTypeId);
					}
					if (optionIndex == 2) {// 删除
						dao.dPersonnelByTypeId(typeId);
					}
				}
				DefaultMutableTreeNode selectedNode = treeNode.getNextNode();
				if (selectedNode == null)
					selectedNode = treeNode.getPreviousNode();
				infoTreeModel.removeNodeFromParent(treeNode);
				infoTree.setSelectionRow(selectedNode.getDepth());
				dao.dTypeByName("info", name);
			}
		});
		URL delInfoTypeUrl = this.getClass().getResource("/img/del_tree.png");
		delInfoTypeButton.setIcon(new ImageIcon(delInfoTypeUrl));
		URL delInfoTypeOverUrl = this.getClass().getResource(
				"/img/del_tree_over.png");
		delInfoTypeButton.setRolloverIcon(new ImageIcon(delInfoTypeOverUrl));
		infoTreeButtonPanel.add(delInfoTypeButton);

		final JPanel infoListPanel = new JPanel();
		infoSplitPane.setRightComponent(infoListPanel);
		infoListPanel.setLayout(new BorderLayout());

		final JScrollPane infoListScrollPane = new JScrollPane();
		infoListPanel.add(infoListScrollPane);

		infoListTableColumnV = new Vector<String>();
		infoListTableColumnV.add("序号");
		infoListTableColumnV.add("编号");
		infoListTableColumnV.add("信息内容");

		infoListTableValueV = new Vector<Vector>();

		infoListTableModel = new DefaultTableModel(infoListTableValueV,
				infoListTableColumnV);

		infoListTable = new MTable(infoListTableModel);
		infoListTable.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		initInfoListTable();
		infoListScrollPane.setViewportView(infoListTable);

		final JPanel infoButtonPanel = new JPanel();
		infoButtonPanel.setLayout(new BoxLayout(infoButtonPanel,
				BoxLayout.Y_AXIS));
		infoListPanel.add(infoButtonPanel, BorderLayout.EAST);

		final MButton addToSendInfoButton = new MButton();
		URL addToSendInfoUrl = this.getClass().getResource(
				"/img/add_to_info.png");
		addToSendInfoButton.setIcon(new ImageIcon(addToSendInfoUrl));
		URL addToSendInfoOverUrl = this.getClass().getResource(
				"/img/add_to_info_over.png");
		addToSendInfoButton
				.setRolloverIcon(new ImageIcon(addToSendInfoOverUrl));
		addToSendInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = infoListTable.getSelectedRow();// 获得信息列表的选中行
				if (selectedRow < 0) {// 未选择任何行
					JOptionPane.showMessageDialog(null, "请选择要编辑的信息！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);// 弹出提示信息
				} else {
					String info = infoListTable.getValueAt(selectedRow, 2)
							.toString();// 获得信息内容
					if (infoTabbedPane.getSelectedIndex() == 0)// 当前被选中的是“短信内容”面板
						infoTextArea.setText(info);// 添加信息到短信内容文本区域
					else
						// 当前被选中的是“E-mail内容”面板
						emailTextArea.setText(info);// 添加信息到E-mail内容文本区域
				}
			}
		});
		infoButtonPanel.add(addToSendInfoButton);

		final MButton addInfoButton = new MButton();
		URL addInfoUrl = this.getClass().getResource("/img/add_info.png");
		addInfoButton.setIcon(new ImageIcon(addInfoUrl));
		URL addInfoOverUrl = this.getClass().getResource(
				"/img/add_info_over.png");
		addInfoButton.setRolloverIcon(new ImageIcon(addInfoOverUrl));
		addInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) infoTree
						.getLastSelectedPathComponent();
				InfoDialog infoDialog = new InfoDialog("添加信息", treeNode
						.getUserObject().toString(), -1, null);
				infoDialog.setVisible(true);
				initInfoListTable();
			}
		});
		infoButtonPanel.add(addInfoButton);

		final MButton updInfoButton = new MButton();
		URL updInfoUrl = this.getClass().getResource("/img/upd_info.png");
		updInfoButton.setIcon(new ImageIcon(updInfoUrl));
		URL updInfoOverUrl = this.getClass().getResource(
				"/img/upd_info_over.png");
		updInfoButton.setRolloverIcon(new ImageIcon(updInfoOverUrl));
		updInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = infoListTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "请选择要修改的信息！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) infoTree
							.getLastSelectedPathComponent();
					int num = (Integer) infoListTable
							.getValueAt(selectedRow, 1);
					String content = infoListTable.getValueAt(selectedRow, 2)
							.toString();
					InfoDialog infoDialog = new InfoDialog("修改信息", treeNode
							.getUserObject().toString(), num, content);
					infoDialog.setVisible(true);
					initInfoListTable();
				}
			}
		});
		infoButtonPanel.add(updInfoButton);

		final MButton delInfoButton = new MButton();
		URL delInfoUrl = this.getClass().getResource("/img/del_info.png");
		delInfoButton.setIcon(new ImageIcon(delInfoUrl));
		URL delInfoOverUrl = this.getClass().getResource(
				"/img/del_info_over.png");
		delInfoButton.setRolloverIcon(new ImageIcon(delInfoOverUrl));
		delInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = infoListTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "请选择要删除的信息！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					int num = (Integer) infoListTable
							.getValueAt(selectedRow, 1);
					int i = JOptionPane.showConfirmDialog(null, "确定要删除信息" + num
							+ "？", "友情提示", JOptionPane.YES_NO_OPTION);
					if (i == 0) {
						dao.dInfoByNum(num);
						initInfoListTable();
					}
				}
			}
		});
		infoButtonPanel.add(delInfoButton);
		//
	}

	private void initTree(DefaultMutableTreeNode treeRoot, String used) {// 初始化树的方法
		Vector typeV = dao.sTypeByUsed(used);// 查询用于指定树的类型
		for (int i = 0; i < typeV.size(); i++) {// 遍历向量
			Vector type = (Vector) typeV.get(i);// 获得类型向量
			treeRoot.add(new DefaultMutableTreeNode(type.get(2)));// 将类型添加到树中
		}
	}

	private void initCardListTable() {
		cardListTableValueV.removeAllElements();// 清空名片列表
		DefaultMutableTreeNode cardTreeNode = (DefaultMutableTreeNode) cardTree
				.getLastSelectedPathComponent();// 获得名片夹树的选中节点对象
		if (cardTreeNode != null) {// 判断是否存在选中的节点
			String cardName = cardTreeNode.getUserObject().toString();// 获得选中名片夹的名称
			cardListTableValueV.addAll(dao.sPersonnelVByTypeName(cardName));// 检索名片夹包含的名片
		}
		cardListTableModel.setDataVector(cardListTableValueV,
				cardListTableColumnV);// 刷新名片列表表格模型
	}

	private void initInfoListTable() {
		infoListTableValueV.removeAllElements();
		DefaultMutableTreeNode infoTreeNode = (DefaultMutableTreeNode) infoTree
				.getLastSelectedPathComponent();
		if (infoTreeNode != null) {
			String infoName = infoTreeNode.getUserObject().toString();
			infoListTableValueV.addAll(dao.sInfoVByTypeName(infoName));
		}
		infoListTableModel.setDataVector(infoListTableValueV,
				infoListTableColumnV);
	}

	private boolean isHad(DefaultMutableTreeNode treeNode, String newChildName) {// 验证该名称是否已经存在
		boolean had = false;// 默认为不存在
		int childCount = treeNode.getChildCount();// 获得子节点的数量
		for (int i = 0; i < childCount; i++) {// 遍历子节点
			DefaultMutableTreeNode childTreeNode = (DefaultMutableTreeNode) treeNode
					.getChildAt(i);// 获得子节点对象
			if (childTreeNode.getUserObject().toString().equals(newChildName)) {// 判断名称是否相同
				JOptionPane.showMessageDialog(null, "该名称已经存在！", "友情提示",
						JOptionPane.INFORMATION_MESSAGE);// 弹出该名称已经存在的提示
				had = true;// 该名称已经存在
				break;// 跳出循环，停止遍历后面的子节点
			}
		}
		return had;// 返回结果
	}

	private String addTreeNode(DefaultMutableTreeNode treeNode, String typeName) {
		String nodeName = "";// 创建节点名称为空字符串
		while (nodeName.length() == 0) {// 判断节点名称的长度是否为0
			nodeName = JOptionPane.showInputDialog(null, "请输入" + typeName
					+ "名称：", "新建" + typeName, JOptionPane.INFORMATION_MESSAGE);// 弹出输入框令用户输入名称
			if (nodeName == null) {// 判断节点名称是否为空值
				break;// 为空值即用户取消了新建，则跳出循环
			} else {
				nodeName = nodeName.trim();// 去掉首尾空格
				if (nodeName.length() > 0) {// 判断节点名称的长度是否为0
					if (isHad(treeNode, nodeName))// 如果不为0则判断该名称是否已经存在
						nodeName = "";// 如果存在则设置节点名称为空字符串
				}
			}
		}
		return nodeName;// 返回节点名称
	}

	private String updateTreeNode(DefaultMutableTreeNode treeNode) {
		DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) treeNode
				.getParent();// 获得欲修改节点的父节点
		String newNodeName = ""; // 创建节点名称为空字符串
		while (newNodeName.length() == 0) { // 判断节点名称的长度是否为0
			newNodeName = JOptionPane.showInputDialog(null, "请输入新名称：", "修改名称",
					JOptionPane.INFORMATION_MESSAGE);// 接受用户输入名称
			if (newNodeName == null) { // 判断节点名称是否为空值
				break; // 为空值即用户取消了修改，则跳出循环
			} else {
				newNodeName = newNodeName.trim(); // 去掉首尾空格
				if (newNodeName.length() > 0) {// 判断节点名称的长度是否为0
					if (isHad(parentNode, newNodeName))// 如果不为0则判断该名称是否已经存在
						newNodeName = ""; // 如果存在则设置节点名称为空字符串
				}
			}
		}
		return newNodeName; // 返回节点名称
	}

}
