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
				if (selectionPath.getPathCount() == 1) {// �޸Ĺ�˾����
					int i = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�޸Ĺ�˾�����ƣ�",
							"������ʾ", JOptionPane.YES_NO_OPTION);// ������ʾ��
					if (i == 0) {// �޸ģ��������ǡ���ť��
						String infos[] = { "�������˾�������ƣ�", "�޸Ĺ�˾����",
								"�������˾�������ƣ�" };
						newName = getName(infos);// ����޸ĺ������
						if (newName != null)
							selected = company;// �޸ĵ�Ϊ��˾����
					}
				} else {// �޸Ĳ�������
					String infos[] = { "�����벿�ŵ������ƣ�", "�޸Ĳ�������", "�����벿�ŵ������ƣ�" };
					newName = getName(infos);// ����޸ĺ������
					if (newName != null) {
						selected = company;// ѡ�в��ŵ���������
						Object[] paths = selectionPath.getPath();// ѡ�в��Žڵ��·������
						for (int i = 1; i < paths.length; i++) {// ����ѡ�нڵ�·��
							Iterator deptIt = selected.getTbDepts().iterator();
							finded: while (deptIt.hasNext()) {// ͨ��ѭ������ѡ�нڵ�·����Ӧ�Ĳ���
								TbDept dept = (TbDept) deptIt.next();
								if (dept.getName().equals(paths[i].toString())) {
									selected = dept;// �ҵ�ѡ�нڵ�·����Ӧ�Ĳ���
									break finded;// ������ָ��λ��
								}
							}
						}
					}
				}
				if (selected != null) {
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) selectionPath
							.getLastPathComponent();// ���ѡ�нڵ����
					treeNode.setUserObject(newName);// �޸Ľڵ�����
					treeModel.reload();// ˢ�����ṹ
					tree.setSelectionPath(selectionPath);// ���ýڵ�Ϊѡ��״̬
					//
					selected.setName(newName);// �޸Ĳ��Ŷ���
					dao.updateObject(company);// ���޸ĳ־û������ݿ�
					HibernateSessionFactory.closeSession();// �ر����ݿ�����
				}

			}
		});
		updateButton.setText("�޸�����");
		buttonPanel.add(updateButton);

		final JButton delButton = new JButton();
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath selectionPath = tree.getSelectionPath();
				int pathCount = selectionPath.getPathCount();// ���ѡ�нڵ�ļ���
				if (pathCount == 1) {// ѡ�е�Ϊ1���ڵ㣬����˾�ڵ�
					JOptionPane.showMessageDialog(null, "��˾�ڵ㲻��ɾ����", "������ʾ",
							JOptionPane.WARNING_MESSAGE);
				} else {// ѡ�е�Ϊ2����3���ڵ㣬�����Žڵ�
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) selectionPath
							.getLastPathComponent();// ���ѡ�в��Žڵ����
					int i = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ���ò��ţ�"
							+ treeNode, "������ʾ", JOptionPane.YES_NO_OPTION);
					if (i == 0) {// ɾ��
						treeModel.removeNodeFromParent(treeNode);// ɾ��ѡ�нڵ�
						tree.setSelectionRow(0);// ѡ�и�����˾���ڵ�
						TbDept selected = company;// ѡ�в��ŵ���������
						Object[] paths = selectionPath.getPath();// ѡ�в��Žڵ��·������
						int lastIndex = paths.length - 1;// ����������
						for (int j = 1; j <= lastIndex; j++) {// ����ѡ�нڵ�·��
							Iterator deptIt = selected.getTbDepts().iterator();
							finded: while (deptIt.hasNext()) {// ͨ��ѭ������ѡ�нڵ�·����Ӧ�Ĳ���
								TbDept dept = (TbDept) deptIt.next();
								if (dept.getName().equals(paths[j].toString())) {
									if (j == lastIndex) // Ϊѡ�нڵ�
										selected.getTbDepts().remove(dept);// ɾ��ѡ�в���
									else
										// Ϊ�����ڵ�
										selected = dept;
									break finded;// ������ָ��λ��
								}
							}
						}
						dao.updateObject(company);// ͬ��ɾ�����ݿ�
						HibernateSessionFactory.closeSession();// �ر����ݿ�����
					}
				}
			}
		});
		delButton.setText("ɾ���ò���");
		buttonPanel.add(delButton);

		final JButton addButton = new JButton();
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath selectionPath = tree.getSelectionPath();
				int pathCount = selectionPath.getPathCount();// ���ѡ�нڵ�ļ���
				had: if (pathCount == 3) {// ѡ�е�Ϊ3���ڵ�
					JOptionPane.showMessageDialog(null, "�ܱ�Ǹ���ڸü������²����ٰ����Ӳ��ţ�",
							"������ʾ", JOptionPane.WARNING_MESSAGE);
				} else {// ѡ�е�Ϊ1����2���ڵ�
					String infos[] = { "�����벿�����ƣ�", "����²���", "�����벿�����ƣ�" };
					String newName = getName(infos);// ����²��ŵ�����
					if (newName != null) {// �����²���
						DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) selectionPath
								.getLastPathComponent();// ���ѡ�в��Žڵ����
						int childCount = parentNode.getChildCount();// ��øò��Ű����Ӳ��ŵĸ���
						for (int i = 0; i < childCount; i++) {// �鿴�´����Ĳ����Ƿ��Ѿ�����
							TreeNode childNode = parentNode.getChildAt(i);
							if (childNode.toString().equals(newName)) {
								JOptionPane.showMessageDialog(null, "�ò����Ѿ����ڣ�",
										"������ʾ", JOptionPane.WARNING_MESSAGE);
								break had;// �Ѿ����ڣ�������ָ��λ��
							}
						}
						DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(
								newName);// �����²��Žڵ����
						treeModel.insertNodeInto(childNode, parentNode,
								childCount);// ���²��Žڵ���뵽ѡ�в��Žڵ�����
						tree.expandPath(selectionPath);// չ��ѡ�в��Žڵ�
						//
						TbDept selected = company;// Ĭ��ѡ�е�Ϊ1���ڵ�
						if (pathCount == 2) {// ѡ�е�Ϊ2���ڵ�
							String selectedName = selectionPath.getPath()[1]
									.toString();// ���ѡ�нڵ������
							Iterator deptIt = company.getTbDepts().iterator();// ������˾ֱ�����ŵĵ���������
							finded: while (deptIt.hasNext()) {// ������˾��ֱ������
								TbDept dept = (TbDept) deptIt.next();
								if (dept.getName().equals(selectedName)) {// ������ѡ�нڵ��Ӧ�Ĳ���
									selected = dept;// ����Ϊѡ�в���
									break finded;// ����ѭ��
								}
							}
						}
						TbDept sonDept = new TbDept();// �����²��Ŷ���
						sonDept.setName(newName);// ���ò�������
						sonDept.setTbDept(selected);// �������²��ŵ��������ŵĹ���
						selected.getTbDepts().add(sonDept);// �������������ŵ��²��ŵĹ���
						dao.updateObject(company);// ���²��ų־û������ݿ�
						HibernateSessionFactory.closeSession();// �ر����ݿ�����
					}
				}
			}
		});
		addButton.setText("����Ӳ���");
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
		DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();// ���ý���ͼ�ꡢ����ͱ���ɫ
		render.setLeafIcon(new ImageIcon());// ����Ҷ�ӽ���ͼ��
		render.setClosedIcon(new ImageIcon());// ���ý���۵�ʱ��ͼ��
		render.setOpenIcon(new ImageIcon());// ���ý��չ��ʱ��ͼ��
		tree.setCellRenderer(render);
		tree.setSelectionRow(0);// Ĭ��ѡ�и��ڵ�
		tree.setRowHeight(26);// ���ýڵ���и�
		tree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);// ���ýڵ��ѡȡģʽΪ��ѡ
		treePanel.add(tree);

		//
	}

	public String getName(String infos[]) {
		String newName = "";
		while (newName != null && newName.length() == 0) {
			newName = JOptionPane.showInputDialog(null, infos[0], infos[1],
					JOptionPane.INFORMATION_MESSAGE);
			if (newName != null && newName.length() == 0) {
				JOptionPane.showMessageDialog(null, infos[2], "������ʾ",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
		return newName;
	}

}