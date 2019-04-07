package com.mwq.frame.user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.mwq.frame.common.DeptAndPersonnelDialog;
import com.mwq.hibernate.Dao;
import com.mwq.hibernate.HibernateSessionFactory;
import com.mwq.hibernate.mapping.TbDutyInfo;
import com.mwq.hibernate.mapping.TbManager;
import com.mwq.hibernate.mapping.TbRecord;
import com.mwq.mwing.MTable;

public class AddUserPanel extends JPanel {

	private MTable table;

	private Vector<String> tableColumnV;

	private Vector tableValueV;

	private DefaultTableModel tableModel;

	private TbRecord record;

	private Dao dao;

	/**
	 * Create the panel
	 */
	public AddUserPanel() {
		super();
		dao = Dao.getInstance();
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		final JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.NORTH);

		final JButton addButton = new JButton();
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DeptAndPersonnelDialog dpDialog = new DeptAndPersonnelDialog();
				Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
				int w = 550;
				int h = 400;
				int x = (size.width - w) / 2;
				int y = (size.height - h) / 2;
				dpDialog.setBounds(x, y, w, h);
				dpDialog.setVisible(true);
				Vector<Vector<String>> selectedRecordV = dpDialog
						.getSelectedRecordV();
				dpDialog.dispose();
				int startRowCount = table.getRowCount();
				if (table.getRowCount() == 0) {
					for (int i = 0; i < selectedRecordV.size(); i++) {
						Vector<String> managerV = selectedRecordV.get(i);
						managerV.set(0, i + 1 + "");
						if (record == null && i == 0) {
							managerV.add("超级管理员");
						} else {
							managerV.add("普通管理员");
						}
						managerV.add("正常");
						tableModel.addRow(managerV);
					}
				} else {
					int k = table.getRowCount();
					for (int i = 0; i < selectedRecordV.size(); i++) {
						Vector<String> managerV = selectedRecordV.get(i);
						boolean add = true;
						for (int j = 0; j < tableValueV.size(); j++) {
							Vector<String> oldRecordV = (Vector<String>) tableValueV
									.get(j);
							if (managerV.get(1).equals(oldRecordV.get(1))) {
								add = false;
								break;
							}
						}
						if (add) {
							managerV.set(0, ++k + "");
							managerV.add("普通管理员");
							managerV.add("正常");
							tableModel.addRow(managerV);
						}
					}
				}
				int endRowCount = table.getRowCount();
				if (endRowCount > 0) {
					table.setRowSelectionInterval(startRowCount, startRowCount);
				}
				for (int i = startRowCount; i < endRowCount; i++) {
					TbManager manager = new TbManager();
					manager.setPassword("111");
					if (record == null && i == 0) {
						manager.setPurview("超级管理员");
					} else {
						manager.setPurview("普通管理员");
					}
					manager.setState("正常");
					String recordNum = table.getValueAt(i, 1).toString();
					TbRecord record = (TbRecord) dao
							.queryRecordByNum(recordNum);
					record.setTbManager(manager);
					manager.setTbRecord(record);
					dao.saveObject(manager);
				}
				HibernateSessionFactory.closeSession();
			}
		});
		addButton.setText("添加新用户");
		panel.add(addButton);

		final JButton delButton = new JButton();
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = table.getSelectedRow();
				int i = JOptionPane.showConfirmDialog(null, "确定要删除管理员："
						+ table.getValueAt(selectedRow, 2), "友情提示",
						JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					String recordNum = table.getValueAt(selectedRow, 1)
							.toString();
					//
					tableModel.removeRow(selectedRow);
					int rowCount = table.getRowCount();
					if (rowCount != 0) {
						if (selectedRow < rowCount) {
							for (int j = selectedRow; j < rowCount; j++) {
								table.setValueAt(j + 1, j, 0);
							}
							table.setRowSelectionInterval(selectedRow,
									selectedRow);
						} else {
							table.setRowSelectionInterval(rowCount - 1,
									rowCount - 1);
						}
					}
					//
					TbRecord record = (TbRecord) dao
							.queryRecordByNum(recordNum);
					TbManager manager = record.getTbManager();
					record.setTbManager(null);
					dao.deleteObject(manager);
					HibernateSessionFactory.closeSession();
				}
			}
		});
		delButton.setText("删除该用户");
		panel.add(delButton);

		final JButton freezeButton = new JButton();
		freezeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				String clueOn = "";
				String state = table.getValueAt(selectedRow, 7).toString();
				if (state.equals("正常")) {
					state = "冻结";
					clueOn = "确定要“冻结”管理员：" + table.getValueAt(selectedRow, 2);
				} else {// 冻结
					state = "正常";
					clueOn = "确定要“解冻”管理员：" + table.getValueAt(selectedRow, 2);
				}
				int i = JOptionPane.showConfirmDialog(null, clueOn, "友情提示",
						JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					table.setValueAt(state, selectedRow, 7);
					//
					String recordNum = table.getValueAt(selectedRow, 1)
							.toString();
					TbRecord record = (TbRecord) dao
							.queryRecordByNum(recordNum);
					record.getTbManager().setState(state);
					dao.updateObject(record);
					HibernateSessionFactory.closeSession();
				}
			}
		});
		freezeButton.setText("（冻结/解冻）该用户");
		panel.add(freezeButton);

		final JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		tableColumnV = new Vector<String>();
		String tableColumns[] = { "序号", "档案编号", "姓名", "性别", "部门", "职务", "权限",
				"状态" };
		for (int i = 0; i < tableColumns.length; i++) {
			tableColumnV.add(tableColumns[i]);
		}

		tableValueV = new Vector();

		Iterator managerIt = dao.queryManager().iterator();
		int index = 1;
		while (managerIt.hasNext()) {
			TbManager manager = (TbManager) managerIt.next();
			TbRecord record = manager.getTbRecord();
			TbDutyInfo dutyInfo = record.getTbDutyInfo();
			Vector tableRowV = new Vector();
			tableRowV.add(index++);
			tableRowV.add(record.getRecordNumber());
			tableRowV.add(record.getName());
			tableRowV.add(record.getSex());
			tableRowV.add(dutyInfo.getTbDept().getName());
			tableRowV.add(dutyInfo.getTbDuty().getName());
			tableRowV.add(manager.getPurview());
			tableRowV.add(manager.getState());
			tableValueV.add(tableRowV);
		}
		HibernateSessionFactory.closeSession();

		tableModel = new DefaultTableModel(tableValueV, tableColumnV);

		table = new MTable(tableModel);
		if (table.getRowCount() > 0) {
			table.setRowSelectionInterval(0, 0);
		}
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		//
	}

	public TbRecord getRecord() {
		return record;
	}

	public void setRecord(TbRecord record) {
		this.record = record;
	}

}
