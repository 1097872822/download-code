package com.mwq.frame.common;

import java.awt.BorderLayout;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import com.mwq.hibernate.Dao;
import com.mwq.hibernate.mapping.TbDept;

public class DeptTreeDialog extends JDialog {

	private JTree tree;

	private Dao dao;

	public DeptTreeDialog(final JTextField textField) {
		super();
		dao = Dao.getInstance();
		setModal(true);// 设置对话框阻止当前线程
		setUndecorated(true);// 设置对话框不提供标题栏
		setBounds(100, 100, 125, 175);

		final JScrollPane scrollPane = new JScrollPane();// 创建滚动面板
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		TbDept company = (TbDept) dao.queryDeptById(1);
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(company
				.getName());// 创建部门树的根节点

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

		tree = new JTree(treeModel); // 利用树模型对象创建树对象
		DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();
		render.setLeafIcon(new ImageIcon());// 设置叶子结点的图标
		render.setClosedIcon(new ImageIcon());// 设置结点折叠时的图标
		render.setOpenIcon(new ImageIcon());// 设置结点展开时的图标
		tree.setCellRenderer(render);
		tree.addTreeSelectionListener(new TreeSelectionListener() {// 捕获树节点被选中的事件
					public void valueChanged(TreeSelectionEvent e) {
						TreePath treePath = e.getPath();// 获得被选中树节点的路径
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath
								.getLastPathComponent();// 获得被选中树节点的对象
						if (node.getChildCount() == 0) {// 被选中的节点为叶子节点
							textField.setText(node.toString());// 将选中节点的名称显示到传入的文本框中
						} else {// 被选中的节点不是叶子节点
							JOptionPane.showMessageDialog(null, "请选择所在的具体部门",
									"错误提示", JOptionPane.ERROR_MESSAGE);
							return;
						}
						dispose();// 销毁部门树对话框
					}
				});
		scrollPane.setViewportView(tree); // 将部门树放到滚动面板中
		//
	}

}
