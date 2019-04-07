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

		// ��ʼ�����Ĳ�����
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("ȫ��ְԱ");
		TbDept company = (TbDept) dao.queryDeptById(1);
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

		tree = new JTree(treeModel);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				TreePath path = e.getPath(); // ��ñ�ѡ�����ڵ��·��
				tableValueV.removeAllElements();// �Ƴ�����е�������
				if (path.getPathCount() == 1) {// ѡ�����ĸ��ڵ�
					showAllRecord();// ��ʾ���е���
				} else {// ѡ�������ӽڵ�
					String deptName = path.getLastPathComponent().toString();// ���ѡ�в��ŵ�����
					TbDept selectDept = (TbDept) dao.queryDeptByName(deptName);// ����ָ�����Ŷ���
					Iterator sonDeptIt = selectDept.getTbDepts().iterator();
					if (sonDeptIt.hasNext()) {// ѡ�����Ķ����ڵ�
						while (sonDeptIt.hasNext()) {
							showRecordInDept((TbDept) sonDeptIt.next());// ��ʾѡ�в��������Ӳ��ŵĵ���
						}
					} else {// ѡ������Ҷ�ӽڵ�
						showRecordInDept(selectDept);// ��ʾѡ�в��ŵĵ���
					}
				}
				tableModel.setDataVector(tableValueV, tableColumnV);
				HibernateSessionFactory.closeSession();
			}
		});

		DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();
		render.setLeafIcon(new ImageIcon());// ����Ҷ�ӽ���ͼ��
		render.setClosedIcon(new ImageIcon());// ���ý���۵�ʱ��ͼ��
		render.setOpenIcon(new ImageIcon());// ���ý��չ��ʱ��ͼ��
		tree.setCellRenderer(render);
		deptScrollPane.setViewportView(tree);

		final JScrollPane personnalScrollPane = new JScrollPane();
		splitPane.setRightComponent(personnalScrollPane);

		tableColumnV = new Vector<String>();// ���������������
		String tableColumns[] = new String[] { "��    ��", "�������", "��    ��",
				"��    ��", "��    ��", "ְ    ��" };
		for (int i = 0; i < tableColumns.length; i++) {// ��ӱ������
			tableColumnV.add(tableColumns[i]);
		}

		tableValueV = new Vector<Vector<String>>();// �������ֵ����
		showAllRecord();
		HibernateSessionFactory.closeSession();

		tableModel = new DefaultTableModel(tableValueV, tableColumnV);// �������ģ�Ͷ���

		table = new JTable(tableModel);// ����������
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
