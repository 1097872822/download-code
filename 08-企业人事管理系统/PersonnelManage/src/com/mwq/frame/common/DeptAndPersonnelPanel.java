package com.mwq.frame.common;

import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.mwq.hibernate.Dao;
import com.mwq.hibernate.HibernateSessionFactory;
import com.mwq.hibernate.mapping.TbDept;
import com.mwq.hibernate.mapping.TbDutyInfo;
import com.mwq.hibernate.mapping.TbRecord;

public class DeptAndPersonnelPanel extends JPanel {

	private JTable table;

	private Vector<String> tableColumnV;

	private Vector<Vector<String>> tableValueV;

	private DefaultTableModel tableModel;

	private JTree tree;

	private Dao dao = Dao.getInstance();

	public DeptAndPersonnelPanel() {
		super();
		setLayout(new BorderLayout());

		final JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(140);
		add(splitPane);

		final JScrollPane deptScrollPane = new JScrollPane();
		splitPane.setLeftComponent(deptScrollPane);

		// 初始化左侧的部门树
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("全部职员");
		TbDept company = (TbDept) dao.queryDeptById(1);
		Set depts = company.getTbDepts();
		for (Iterator deptIt = depts.iterator(); deptIt.hasNext();) {
			TbDept dept = (TbDept) deptIt.next();
			DefaultMutableTreeNode deptNode = new DefaultMutableTreeNode(dept
					.getName());// 创建部门树的二级子节点
			root.add(deptNode);
			Set sonDepts = dept.getTbDepts();
			for (Iterator sonDeptIt = sonDepts.iterator(); sonDeptIt.hasNext();) {
				TbDept sonDept = (TbDept) sonDeptIt.next();
				deptNode.add(new DefaultMutableTreeNode(sonDept.getName()));// 创建部门树的叶子节点
			}
		}

		DefaultTreeModel treeModel = new DefaultTreeModel(root); // 利用根节点对象创建树模型对象

		tree = new JTree(treeModel);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path = e.getPath(); // 获得被选中树节点的路径
				tableValueV.removeAllElements();// 移除表格中的所有行
				if (path.getPathCount() == 1) {// 选中树的根节点
					showAllRecord();// 显示所有档案
				} else {// 选中树的子节点
					String deptName = path.getLastPathComponent().toString();// 获得选中部门的名称
					TbDept selectDept = (TbDept) dao.queryDeptByName(deptName);// 检索指定部门对象
					Iterator sonDeptIt = selectDept.getTbDepts().iterator();
					if (sonDeptIt.hasNext()) {// 选中树的二级节点
						while (sonDeptIt.hasNext()) {
							showRecordInDept((TbDept) sonDeptIt.next());// 显示选中部门所有子部门的档案
						}
					} else {// 选中树的叶子节点
						showRecordInDept(selectDept);// 显示选中部门的档案
					}
				}
				tableModel.setDataVector(tableValueV, tableColumnV);
				HibernateSessionFactory.closeSession();
			}
		});

		DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();
		render.setLeafIcon(new ImageIcon());// 设置叶子结点的图标
		render.setClosedIcon(new ImageIcon());// 设置结点折叠时的图标
		render.setOpenIcon(new ImageIcon());// 设置结点展开时的图标
		tree.setCellRenderer(render);
		deptScrollPane.setViewportView(tree);

		final JScrollPane personnalScrollPane = new JScrollPane();
		splitPane.setRightComponent(personnalScrollPane);

		tableColumnV = new Vector<String>();// 创建表格列名向量
		String tableColumns[] = new String[] { "序    号", "档案编号", "姓    名",
				"性    别", "部    门", "职    务" };
		for (int i = 0; i < tableColumns.length; i++) {// 添加表格列名
			tableColumnV.add(tableColumns[i]);
		}

		tableValueV = new Vector<Vector<String>>();// 创建表格值向量
		showAllRecord();
		HibernateSessionFactory.closeSession();

		tableModel = new DefaultTableModel(tableValueV, tableColumnV);// 创建表格模型对象

		table = new JTable(tableModel);// 创建表格对象
		personnalScrollPane.setViewportView(table);
	}

	private void showAllRecord() {
		Iterator recordIt = dao.queryRecord().iterator();
		int sequenceNumber = 1;
		while (recordIt.hasNext()) {
			TbRecord record = (TbRecord) recordIt.next();
			Vector personnelV = new Vector<String>();
			personnelV.add(sequenceNumber++);
			personnelV.add(record.getRecordNumber());
			personnelV.add(record.getName());
			personnelV.add(record.getSex());
			TbDutyInfo dutyInfo = record.getTbDutyInfo();
			personnelV.add(dutyInfo.getTbDept().getName());
			personnelV.add(dutyInfo.getTbDuty().getName());
			tableValueV.add(personnelV);
		}
	}

	private void showRecordInDept(TbDept dept) {
		Iterator dutyInfoIt = dept.getTbDutyInfos().iterator();
		int sequenceNumber = 1;
		while (dutyInfoIt.hasNext()) {
			TbDutyInfo dutyInfo = (TbDutyInfo) dutyInfoIt.next();
			TbRecord record = dutyInfo.getTbRecord();
			Vector personnelV = new Vector<String>();
			personnelV.add(sequenceNumber++);
			personnelV.add(record.getRecordNumber());
			personnelV.add(record.getName());
			personnelV.add(record.getSex());
			personnelV.add(dutyInfo.getTbDept().getName());
			personnelV.add(dutyInfo.getTbDuty().getName());
			tableValueV.add(personnelV);
		}
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

}
