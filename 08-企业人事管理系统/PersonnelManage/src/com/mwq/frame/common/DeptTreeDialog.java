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
		setModal(true);// ���öԻ�����ֹ��ǰ�߳�
		setUndecorated(true);// ���öԻ����ṩ������
		setBounds(100, 100, 125, 175);

		final JScrollPane scrollPane = new JScrollPane();// �����������
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		TbDept company = (TbDept) dao.queryDeptById(1);
		DefaultMutableTreeNode root = new DefaultMutableTreeNode(company
				.getName());// �����������ĸ��ڵ�

		Set depts = company.getTbDepts();
		for (Iterator deptIt = depts.iterator(); deptIt.hasNext();) {
			TbDept dept = (TbDept) deptIt.next();
			DefaultMutableTreeNode deptNode = new DefaultMutableTreeNode(dept
					.getName());// �����������Ķ����ӽڵ�
			root.add(deptNode);
			Set sonDepts = dept.getTbDepts();
			for (Iterator sonDeptIt = sonDepts.iterator(); sonDeptIt.hasNext();) {
				TbDept sonDept = (TbDept) sonDeptIt.next();
				deptNode.add(new DefaultMutableTreeNode(sonDept.getName()));// ������������Ҷ�ӽڵ�
			}
		}

		DefaultTreeModel treeModel = new DefaultTreeModel(root); // ���ø��ڵ���󴴽���ģ�Ͷ���

		tree = new JTree(treeModel); // ������ģ�Ͷ��󴴽�������
		DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();
		render.setLeafIcon(new ImageIcon());// ����Ҷ�ӽ���ͼ��
		render.setClosedIcon(new ImageIcon());// ���ý���۵�ʱ��ͼ��
		render.setOpenIcon(new ImageIcon());// ���ý��չ��ʱ��ͼ��
		tree.setCellRenderer(render);
		tree.addTreeSelectionListener(new TreeSelectionListener() {// �������ڵ㱻ѡ�е��¼�
					public void valueChanged(TreeSelectionEvent e) {
						TreePath treePath = e.getPath();// ��ñ�ѡ�����ڵ��·��
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) treePath
								.getLastPathComponent();// ��ñ�ѡ�����ڵ�Ķ���
						if (node.getChildCount() == 0) {// ��ѡ�еĽڵ�ΪҶ�ӽڵ�
							textField.setText(node.toString());// ��ѡ�нڵ��������ʾ��������ı�����
						} else {// ��ѡ�еĽڵ㲻��Ҷ�ӽڵ�
							JOptionPane.showMessageDialog(null, "��ѡ�����ڵľ��岿��",
									"������ʾ", JOptionPane.ERROR_MESSAGE);
							return;
						}
						dispose();// ���ٲ������Ի���
					}
				});
		scrollPane.setViewportView(tree); // ���������ŵ����������
		//
	}

}
