package com.mwq.frame.common;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;

public class DeptAndPersonnelDialog extends JDialog {

	public static void main(String args[]) {
		try {
			DeptAndPersonnelDialog dialog = new DeptAndPersonnelDialog();
			dialog.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private final DeptAndPersonnelPanel panel = new DeptAndPersonnelPanel();

	private final JTable table = panel.getTable();

	private final Vector<Vector<String>> selectedRecordV = new Vector<Vector<String>>();

	/**
	 * Create the dialog
	 */
	public DeptAndPersonnelDialog() {
		super();
		setModal(true);
		setTitle("按部门查找员工");
		setBounds(100, 100, 550, 400);

		getContentPane().add(panel, BorderLayout.CENTER);

		final JPanel buttonPanel = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		buttonPanel.setLayout(flowLayout);
		getContentPane().add(buttonPanel, BorderLayout.SOUTH);

		final JButton selectAllButton = new JButton();
		selectAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.selectAll();
			}
		});
		selectAllButton.setText("全选");
		buttonPanel.add(selectAllButton);

		final JButton addButton = new JButton();
		addButton.addActionListener(new ActionListener() {// 捕获按钮被按下的事件
					public void actionPerformed(ActionEvent e) {
						int[] rows = table.getSelectedRows();// 获得选中行的索引
						int columnCount = table.getColumnCount();// 获得表格的列数
						for (int row = 0; row < rows.length; row++) {
							Vector<String> recordV = new Vector<String>();// 创建一个向量对象，代表表格的一行
							for (int column = 0; column < columnCount; column++) {
								recordV.add(table.getValueAt(rows[row], column)
										.toString());// 将表格中的值添加到向量中
							}
							selectedRecordV.add(recordV);// 将代表选中行的向量添加到另一个向量中
						}
					}
				});
		addButton.setText("添加");// 设置按钮的名称
		buttonPanel.add(addButton);

		final JButton exitButton = new JButton();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		exitButton.setText("退出");
		buttonPanel.add(exitButton);
		//
	}

	public DeptAndPersonnelPanel getPanel() {
		return panel;
	}

	public Vector getSelectedRecordV() {
		return selectedRecordV;
	}

}
