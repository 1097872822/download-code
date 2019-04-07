package com.mwq.frame.mwing;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class MTable extends JTable {

	public MTable() {
		super();
	}

	public MTable(DefaultTableModel tableModel) {
		super(tableModel);
	}

	// ���������Ϣ
	@Override
	public JTableHeader getTableHeader() {
		// ��ñ��ͷ����
		JTableHeader tableHeader = super.getTableHeader();
		tableHeader.setReorderingAllowed(false);// ���ñ���в�������
		// ��ñ��ͷ�ĵ�Ԫ�����
		DefaultTableCellRenderer defaultRenderer = (DefaultTableCellRenderer) tableHeader
				.getDefaultRenderer();
		// ���õ�Ԫ�����ݣ���������������ʾ
		defaultRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		return tableHeader;
	}

	// �����ֵ������ʾ
	@Override
	public TableCellRenderer getDefaultRenderer(Class<?> columnClass) {
		// ��ó����ͷ���ֵĵ�Ԫ�����
		DefaultTableCellRenderer defaultRenderer = (DefaultTableCellRenderer) super
				.getDefaultRenderer(columnClass);
		// ���õ�Ԫ�����ݾ�����ʾ
		defaultRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		return defaultRenderer;
	}

	// ��񲻿ɱ༭
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	// �������ñ���ѡ����
	@Override
	public void setRowSelectionInterval(int fromRow, int toRow) {// �ع�����ķ���
		super.setRowSelectionInterval(fromRow, toRow);
	}

	// �������ñ���Ψһѡ����
	public void setRowSelectionInterval(int row) {// ͨ������ʵ���Լ��ķ���
		setRowSelectionInterval(row, row);
	}

//	// �����ֻ�ɵ�ѡ
//	@Override
//	public ListSelectionModel getSelectionModel() {
//		ListSelectionModel selectionModel = super.getSelectionModel();
//		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		return selectionModel;
//	}

}
