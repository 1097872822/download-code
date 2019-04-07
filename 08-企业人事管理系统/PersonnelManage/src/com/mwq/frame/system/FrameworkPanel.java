package com.mwq.frame.system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.mwq.hibernate.Dao;
import com.mwq.hibernate.HibernateSessionFactory;
import com.mwq.hibernate.mapping.TbDept;

public class FrameworkPanel extends JPanel {

	private JTree tree;

	private DefaultTreeModel treeModel;

	private DefaultMutableTreeNode root;

	private Dao dao;

	private TbDept company;

	/**
	 * Create the panel
	 */
	public FrameworkPanel() {
		super();
		dao = Dao.getInstance();
		setLayout(new BorderLayout());

		final JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		add(buttonPanel, BorderLayout.NORTH);

		final JButton updateButton = new JButton();
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath selectionPath = tree.getSelectionPath();
				TbDept selected = null;
				String newName = null;
				if (selectionPath.getPathCount() == 1) {// 修改公司名称
					int i = JOptionPane.showConfirmDialog(null, "确定要修改贵公司的名称？",
							"友情提示", JOptionPane.YES_NO_OPTION);// 弹出提示框
					if (i == 0) {// 修改（单击“是”按钮）
						String infos[] = { "请输入贵公司的新名称：", "修改公司名称",
								"请输入贵公司的新名称！" };
						newName = getName(infos);// 获得修改后的名称
						if (newName != null)
							selected = company;// 修改的为公司名称
					}
				} else {// 修改部门名称
					String infos[] = { "请输入部门的新名称：", "修改部门名称", "请输入部门的新名称！" };
					newName = getName(infos);// 获得修改后的名称
					if (newName != null) {
						selected = company;// 选中部门的所属部门
						Object[] paths = selectionPath.getPath();// 选中部门节点的路径对象
						for (int i = 1; i < paths.length; i++) {// 遍历选中节点路径
							Iterator deptIt = selected.getTbDepts().iterator();
							finded: while (deptIt.hasNext()) {// 通过循环查找选中节点路径对应的部门
								TbDept dept = (TbDept) deptIt.next();
								if (dept.getName().equals(paths[i].toString())) {
									selected = dept;// 找到选中节点路径对应的部门
									break finded;// 跳出到指定位置
								}
							}
						}
					}
				}
				if (selected != null) {
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) selectionPath
							.getLastPathComponent();// 获得选中节点对象
					treeNode.setUserObject(newName);// 修改节点名称
					treeModel.reload();// 刷新树结构
					tree.setSelectionPath(selectionPath);// 设置节点为选中状态
					//
					selected.setName(newName);// 修改部门对象
					dao.updateObject(company);// 将修改持久化到数据库
					HibernateSessionFactory.closeSession();// 关闭数据库连接
				}

			}
		});
		updateButton.setText("修改名称");
		buttonPanel.add(updateButton);

		final JButton delButton = new JButton();
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath selectionPath = tree.getSelectionPath();
				int pathCount = selectionPath.getPathCount();// 获得选中节点的级别
				if (pathCount == 1) {// 选中的为1级节点，即公司节点
					JOptionPane.showMessageDialog(null, "公司节点不能删除！", "友情提示",
							JOptionPane.WARNING_MESSAGE);
				} else {// 选中的为2级或3级节点，即部门节点
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) selectionPath
							.getLastPathComponent();// 获得选中部门节点对象
					int i = JOptionPane.showConfirmDialog(null, "确定要删除该部门："
							+ treeNode, "友情提示", JOptionPane.YES_NO_OPTION);
					if (i == 0) {// 删除
						treeModel.removeNodeFromParent(treeNode);// 删除选中节点
						tree.setSelectionRow(0);// 选中根（公司）节点
						TbDept selected = company;// 选中部门的所属部门
						Object[] paths = selectionPath.getPath();// 选中部门节点的路径对象
						int lastIndex = paths.length - 1;// 获得最大索引
						for (int j = 1; j <= lastIndex; j++) {// 遍历选中节点路径
							Iterator deptIt = selected.getTbDepts().iterator();
							finded: while (deptIt.hasNext()) {// 通过循环查找选中节点路径对应的部门
								TbDept dept = (TbDept) deptIt.next();
								if (dept.getName().equals(paths[j].toString())) {
									if (j == lastIndex) // 为选中节点
										selected.getTbDepts().remove(dept);// 删除选中部门
									else
										// 为所属节点
										selected = dept;
									break finded;// 跳出到指定位置
								}
							}
						}
						dao.updateObject(company);// 同步删除数据库
						HibernateSessionFactory.closeSession();// 关闭数据库连接
					}
				}
			}
		});
		delButton.setText("删除该部门");
		buttonPanel.add(delButton);

		final JButton addButton = new JButton();
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath selectionPath = tree.getSelectionPath();
				int pathCount = selectionPath.getPathCount();// 获得选中节点的级别
				had: if (pathCount == 3) {// 选中的为3级节点
					JOptionPane.showMessageDialog(null, "很抱歉，在该级部门下不能再包含子部门！",
							"友情提示", JOptionPane.WARNING_MESSAGE);
				} else {// 选中的为1级或2级节点
					String infos[] = { "请输入部门名称：", "添加新部门", "请输入部门名称！" };
					String newName = getName(infos);// 获得新部门的名称
					if (newName != null) {// 创建新部门
						DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectionPath
								.getLastPathComponent();// 获得选中部门节点对象
						int childCount = parentNode.getChildCount();// 获得该部门包含子部门的个数
						for (int i = 0; i < childCount; i++) {// 查看新创建的部门是否已经存在
							TreeNode childNode = parentNode.getChildAt(i);
							if (childNode.toString().equals(newName)) {
								JOptionPane.showMessageDialog(null, "该部门已经存在！",
										"友情提示", JOptionPane.WARNING_MESSAGE);
								break had;// 已经存在，跳出到指定位置
							}
						}
						DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(
								newName);// 创建新部门节点对象
						treeModel.insertNodeInto(childNode, parentNode,
								childCount);// 将新部门节点插入到选中部门节点的最后
						tree.expandPath(selectionPath);// 展开选中部门节点
						//
						TbDept selected = company;// 默认选中的为1级节点
						if (pathCount == 2) {// 选中的为2级节点
							String selectedName = selectionPath.getPath()[1]
									.toString();// 获得选中节点的名称
							Iterator deptIt = company.getTbDepts().iterator();// 创建公司直属部门的迭代器对象
							finded: while (deptIt.hasNext()) {// 遍历公司的直属部门
								TbDept dept = (TbDept) deptIt.next();
								if (dept.getName().equals(selectedName)) {// 查找与选中节点对应的部门
									selected = dept;// 设置为选中部门
									break finded;// 跳出循环
								}
							}
						}
						TbDept sonDept = new TbDept();// 创建新部门对象
						sonDept.setName(newName);// 设置部门名称
						sonDept.setTbDept(selected);// 建立从新部门到所属部门的关联
						selected.getTbDepts().add(sonDept);// 建立从所属部门到新部门的关联
						dao.updateObject(company);// 将新部门持久化到数据库
						HibernateSessionFactory.closeSession();// 关闭数据库连接
					}
				}
			}
		});
		addButton.setText("添加子部门");
		buttonPanel.add(addButton);

		final JPanel treePanel = new JPanel();
		treePanel.setBackground(Color.WHITE);
		add(treePanel, BorderLayout.CENTER);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(50);
		treePanel.setLayout(flowLayout);

		company = (TbDept) dao.queryDeptById(1);
		root = new DefaultMutableTreeNode(company.getName());
		Set depts = company.getTbDepts();
		for (Iterator iter = depts.iterator(); iter.hasNext();) {
			TbDept dept = (TbDept) iter.next();
			DefaultMutableTreeNode deptNode = new DefaultMutableTreeNode(dept
					.getName());
			root.add(deptNode);
			if (dept.getTbDepts().size() != 0) {
				Set sonDepts = dept.getTbDepts();
				for (Iterator iterator = sonDepts.iterator(); iterator
						.hasNext();) {
					TbDept sonDept = (TbDept) iterator.next();
					deptNode.add(new DefaultMutableTreeNode(sonDept.getName()));
				}
			}
		}
		HibernateSessionFactory.closeSession();

		treeModel = new DefaultTreeModel(root);

		tree = new JTree(treeModel);
		tree.setForeground(new Color(255, 0, 0));
		tree.setFont(new Font("", Font.BOLD, 14));
		DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();// 设置结点的图标、字体和背景色
		render.setLeafIcon(new ImageIcon());// 设置叶子结点的图标
		render.setClosedIcon(new ImageIcon());// 设置结点折叠时的图标
		render.setOpenIcon(new ImageIcon());// 设置结点展开时的图标
		tree.setCellRenderer(render);
		tree.setSelectionRow(0);// 默认选中根节点
		tree.setRowHeight(26);// 设置节点的行高
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);// 设置节点的选取模式为单选
		treePanel.add(tree);

		//
	}

	public String getName(String infos[]) {
		String newName = "";
		while (newName != null && newName.length() == 0) {
			newName = JOptionPane.showInputDialog(null, infos[0], infos[1],
					JOptionPane.INFORMATION_MESSAGE);
			if (newName != null && newName.length() == 0) {
				JOptionPane.showMessageDialog(null, infos[2], "友情提示",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		return newName;
	}

}