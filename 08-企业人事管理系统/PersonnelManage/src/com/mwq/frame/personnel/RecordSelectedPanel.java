package com.mwq.frame.personnel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import com.mwq.frame.common.DeptAndPersonnelPanel;
import com.mwq.hibernate.Dao;
import com.mwq.hibernate.mapping.TbRecord;

public class RecordSelectedPanel extends JPanel {

	private Dao dao;

	private DeptAndPersonnelPanel deptAndPersonnelPanel;

	/**
	 * Create the panel
	 */
	public RecordSelectedPanel(final JPanel rightPanel) {
		super();
		setLayout(new BorderLayout());
		dao=Dao.getInstance();
		final JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		add(buttonPanel, BorderLayout.NORTH);

		final JButton createButton = new JButton();
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rightPanel.getComponent(0).setVisible(false);
				RecordOperatePanel.UPDATE_RECORD = null;
				RecordOperatePanel recordOperatePanel = new RecordOperatePanel(
						rightPanel);
				rightPanel.add(recordOperatePanel, BorderLayout.CENTER);
				recordOperatePanel.setVisible(true);
			}
		});
		createButton.setText("新建员工档案");
		buttonPanel.add(createButton);

		final JButton updateButton = new JButton();
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTable table = deptAndPersonnelPanel.getTable();
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "请在下面的表格中选择要修改的记录！",
							"友情提示", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int col = 0;
				int cols = table.getColumnCount();
				for (int i = 0; i < cols; i++) {
					if (table.getColumnName(i).equals("档案编号")) {
						col = i;
						break;
					}
				}
				String selectRecordNum = (String) table.getValueAt(row, col);
				TbRecord updateRecord = (TbRecord) dao
						.queryRecordByNum(selectRecordNum);
				RecordOperatePanel.UPDATE_RECORD = updateRecord;
				rightPanel.getComponent(0).setVisible(false);
				RecordOperatePanel panel = new RecordOperatePanel(rightPanel);
				rightPanel.add(panel, BorderLayout.CENTER);
				panel.setVisible(true);
			}
		});
		updateButton.setText("修改员工档案");
		buttonPanel.add(updateButton);

		deptAndPersonnelPanel = new DeptAndPersonnelPanel();
		deptAndPersonnelPanel.getTable().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		add(deptAndPersonnelPanel, BorderLayout.CENTER);
		//
	}
}
