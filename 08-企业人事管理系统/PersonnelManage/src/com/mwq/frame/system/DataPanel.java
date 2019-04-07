package com.mwq.frame.system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.AncestorListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeSelectionModel;

import com.mwq.hibernate.Dao;
import com.mwq.hibernate.HibernateSessionFactory;
import com.mwq.hibernate.mapping.TbAccessionForm;
import com.mwq.hibernate.mapping.TbAccountItem;
import com.mwq.hibernate.mapping.TbDuty;
import com.mwq.hibernate.mapping.TbNation;
import com.mwq.hibernate.mapping.TbNativePlace;
import com.mwq.mwing.MTable;

public class DataPanel extends JPanel {

	private MTable table;

	private Vector<String> tableColumnV;

	private Vector<Vector> tableValueV;

	private DefaultTableModel tableModel;

	private JTree tree;

	private final Dao dao = Dao.getInstance();

	/**
	 * Create the panel
	 */
	public DataPanel() {
		super();
		setLayout(new BorderLayout());

		final JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.NORTH);

		final JButton addButton = new JButton();
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (treeNode == null) {
					JOptionPane.showMessageDialog(null, "请选择要维护的信息！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					String item = (String) treeNode.getUserObject();
					item = item.trim().replace(" ", "");
					int row = table.getRowCount();
					if (table.getColumnCount() == 2) {
						String input = getparms(item);
						if (input != null) {
							Vector tableRowV = new Vector();
							tableRowV.add(row + 1);
							tableRowV.add(input);
							tableModel.addRow(tableRowV);
							table.setRowSelectionInterval(row, row);
							if (item.equals("职务种类")) {
								TbDuty duty = new TbDuty();
								duty.setName(input);
								dao.saveObject(duty);
							} else if (item.equals("用工形式")) {
								TbAccessionForm form = new TbAccessionForm();
								form.setName(input);
								dao.saveObject(form);
							} else if (item.equals("民族")) {
								TbNation nation = new TbNation();
								nation.setName(input);
								dao.saveObject(nation);
							} else {// 籍贯
								TbNativePlace place = new TbNativePlace();
								place.setName(input);
								dao.saveObject(place);
							}
							HibernateSessionFactory.closeSession();
						}
					} else {// table.getColumnCount() == 4
						AddAccountItemDialog aaid;
						Vector vector;
						if (item.equals("账套项目")) {
							aaid = new AddAccountItemDialog(true, item);
						} else {// 考勤项目
							aaid = new AddAccountItemDialog(false, item);
						}
						Dimension size = Toolkit.getDefaultToolkit()
								.getScreenSize();
						aaid.setBounds((size.width - 280) / 2,
								(size.height - 220) / 2, 280, 220);
						aaid.setVisible(true);
						vector = aaid.getVector();
						if (vector != null) {
							Vector tableRowV = new Vector(vector);
							tableRowV.remove(3);
							tableRowV.insertElementAt(row + 1, 0);
							tableModel.addRow(tableRowV);
							table.setRowSelectionInterval(row, row);
							//
							TbAccountItem accountItem = new TbAccountItem();
							accountItem.setName(vector.get(0).toString());
							accountItem.setType(vector.get(1).toString());
							accountItem.setUnit(vector.get(2).toString());
							accountItem.setIsTimecard(vector.get(3).toString());
							dao.saveObject(accountItem);
						}
						aaid.dispose();
					}
				}
			}
		});
		addButton.setText("添加");
		panel.add(addButton);

		final JButton delButton = new JButton();
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				if (treeNode == null) {
					JOptionPane.showMessageDialog(null, "请选择要维护的信息！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					String item = (String) treeNode.getUserObject();
					item = item.trim().replace(" ", "");
					int row = table.getSelectedRow();
					String name = table.getValueAt(row, 1).toString();
					int i = JOptionPane.showConfirmDialog(null, "确定要删除“" + item
							+ "”中的“" + name + "”？", "友情提示",
							JOptionPane.YES_NO_OPTION);
					if (i == 0) {
						if (item.equals("职务种类")) {
							TbDuty duty = (TbDuty) dao.queryDutyByName(name);
							dao.deleteObject(duty);
						} else if (item.equals("用工形式")) {
							TbAccessionForm form = (TbAccessionForm) dao
									.queryAccessionFormByName(name);
							dao.deleteObject(form);
						} else if (item.equals("民族")) {
							TbNation nation = (TbNation) dao
									.queryNationByName(name);
							dao.deleteObject(nation);
						} else if (item.equals("籍贯")) {
							TbNativePlace place = (TbNativePlace) dao
									.queryNativePlaceByName(name);
							dao.deleteObject(place);
						} else {
							TbAccountItem accountItem = (TbAccountItem) dao
									.queryAccountItemByNameUnit(name, table
											.getValueAt(row, 3).toString());
							dao.deleteObject(accountItem);
						}
						HibernateSessionFactory.closeSession();
						//
						tableModel.removeRow(row);
						int rowCount = table.getRowCount();
						if (row < rowCount) {
							for (int j = row; j < rowCount; j++) {
								table.setValueAt(j + 1, j, 0);
							}
							table.setRowSelectionInterval(row, row);
						} else {
							table.setRowSelectionInterval(rowCount - 1,
									rowCount - 1);
						}
					}
				}
			}
		});
		delButton.setText("删除");
		panel.add(delButton);

		final JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(130);
		add(splitPane, BorderLayout.CENTER);

		final JPanel treePanel = new JPanel();
		treePanel.setBackground(Color.WHITE);
		splitPane.setLeftComponent(treePanel);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(30);
		treePanel.setLayout(flowLayout);

		DefaultMutableTreeNode root = new DefaultMutableTreeNode();
		root.add(new DefaultMutableTreeNode(" 职 务 种 类 "));
		root.add(new DefaultMutableTreeNode(" 用 工 形 式 "));
		root.add(new DefaultMutableTreeNode(" 账 套 项 目 "));
		root.add(new DefaultMutableTreeNode(" 考 勤 项 目 "));
		root.add(new DefaultMutableTreeNode(" 民       族 "));
		root.add(new DefaultMutableTreeNode(" 籍       贯 "));

		DefaultTreeModel treeModel = new DefaultTreeModel(root);

		tree = new JTree(treeModel);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				tableValueV.removeAllElements();
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) tree
						.getLastSelectedPathComponent();
				String name = (String) treeNode.getUserObject();
				name = name.trim().replace(" ", "");
				if (name.equals("账套项目") || name.equals("考勤项目")) {
					if (tableColumnV.size() == 2) {
						tableColumnV.add("类型");
						tableColumnV.add("单位");
					}
					if (name.equals("账套项目")) {
						List list = dao.queryAccountItem();
						int num = 1;
						for (Iterator iter = list.iterator(); iter.hasNext();) {
							TbAccountItem item = (TbAccountItem) iter.next();
							Vector tableRowV = new Vector();
							tableRowV.add(num++);
							tableRowV.add(item.getName());
							tableRowV.add(item.getType());
							tableRowV.add(item.getUnit());
							tableModel.addRow(tableRowV);
						}
					} else {// 考勤项目
						List list = dao.queryAccountItemUsedTimecard();
						int num = 1;
						for (Iterator iter = list.iterator(); iter.hasNext();) {
							TbAccountItem item = (TbAccountItem) iter.next();
							Vector tableRowV = new Vector();
							tableRowV.add(num++);
							tableRowV.add(item.getName());
							tableRowV.add(item.getType());
							tableRowV.add(item.getUnit());
							tableModel.addRow(tableRowV);
						}
					}
				} else {
					if (tableColumnV.size() > 2) {
						tableColumnV.remove(3);
						tableColumnV.remove(2);
					}
					if (name.equals("职务种类")) {
						List list = dao.queryDuty();
						int num = 1;
						for (Iterator iter = list.iterator(); iter.hasNext();) {
							TbDuty duty = (TbDuty) iter.next();
							Vector tableRowV = new Vector();
							tableRowV.add(num++);
							tableRowV.add(duty.getName());
							tableModel.addRow(tableRowV);
						}
					} else if (name.equals("用工形式")) {
						List list = dao.queryAccessionForm();
						int num = 1;
						for (Iterator iter = list.iterator(); iter.hasNext();) {
							TbAccessionForm form = (TbAccessionForm) iter
									.next();
							Vector tableRowV = new Vector();
							tableRowV.add(num++);
							tableRowV.add(form.getName());
							tableModel.addRow(tableRowV);
						}
					} else if (name.equals("民族")) {
						List list = dao.queryNation();
						int num = 1;
						for (Iterator iter = list.iterator(); iter.hasNext();) {
							TbNation nation = (TbNation) iter.next();
							Vector tableRowV = new Vector();
							tableRowV.add(num++);
							tableRowV.add(nation.getName());
							tableModel.addRow(tableRowV);
						}
					} else {// 籍贯
						List list = dao.queryNativePlace();
						int num = 1;
						for (Iterator iter = list.iterator(); iter.hasNext();) {
							TbNativePlace place = (TbNativePlace) iter.next();
							Vector tableRowV = new Vector();
							tableRowV.add(num++);
							tableRowV.add(place.getName());
							tableModel.addRow(tableRowV);
						}
					}
				}
				tableModel.setDataVector(tableValueV, tableColumnV);
				if (table.getRowCount() > 0)
					table.setRowSelectionInterval(0, 0);
			}
		});
		DefaultTreeCellRenderer treeCellRenderer = new DefaultTreeCellRenderer();// 设置结点的图标、字体和背景色
		treeCellRenderer.setLeafIcon(new ImageIcon());// 设置叶子结点的图标
		treeCellRenderer.setClosedIcon(new ImageIcon());// 设置结点折叠时的图标
		treeCellRenderer.setOpenIcon(new ImageIcon());// 设置结点展开时的图标
		tree.setCellRenderer(treeCellRenderer);
		tree.setRowHeight(30);
		tree.setRootVisible(false);
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);
		treePanel.add(tree);

		final JScrollPane scrollPane = new JScrollPane();
		splitPane.setRightComponent(scrollPane);

		tableColumnV = new Vector<String>();
		tableColumnV.add("序号");
		tableColumnV.add("名称");

		tableValueV = new Vector<Vector>();

		tableModel = new DefaultTableModel(tableValueV, tableColumnV);

		table = new MTable(tableModel);
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		//
	}

	public String getparms(String item) {
		String input = "";
		while (input != null && input.length() == 0) {
			input = JOptionPane.showInputDialog(null, "请输入要添加的" + item + "：",
					"添加" + item, JOptionPane.INFORMATION_MESSAGE);
			if (input != null) {
				if (input.length() == 0) {
					JOptionPane.showMessageDialog(null, "请输入" + item
							+ "信息，该信息不允许为空！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					for (int i = 0; i < table.getRowCount(); i++) {
						Object valueAt = table.getValueAt(i, 1);
						if (input.equals(valueAt.toString())) {
							table.setRowSelectionInterval(i, i);
							JOptionPane.showMessageDialog(null, "您要添加的" + item
									+ "已经存在！", "友情提示",
									JOptionPane.INFORMATION_MESSAGE);
							input = "";
						}
					}
				}
			}
		}
		return input;
	}

}
