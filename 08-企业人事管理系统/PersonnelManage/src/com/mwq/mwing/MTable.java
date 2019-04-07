package com.mwq.mwing;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class MTable extends JTable {

	public MTable(DefaultTableModel tableModel) {
		super(tableModel);
	}

	@Override
	// 使表格不可编辑
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	// 使表格列不允许重新排列
	public JTableHeader getTableHeader() {
		JTableHeader tableHeader = super.getTableHeader();
		tableHeader.setReorderingAllowed(false);
		return tableHeader;
	}

}
