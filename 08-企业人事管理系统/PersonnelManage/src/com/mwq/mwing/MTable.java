package com.mwq.mwing;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class MTable extends JTable {

	public MTable(DefaultTableModel tableModel) {
		super(tableModel);
	}

	@Override
	// ʹ��񲻿ɱ༭
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	// ʹ����в�������������
	public JTableHeader getTableHeader() {
		JTableHeader tableHeader = super.getTableHeader();
		tableHeader.setReorderingAllowed(false);
		return tableHeader;
	}

}
