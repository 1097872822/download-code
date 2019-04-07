package com.mwq.frame.treatement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mwq.frame.common.DeptAndPersonnelDialog;
import com.mwq.hibernate.Dao;
import com.mwq.hibernate.HibernateSessionFactory;
import com.mwq.hibernate.mapping.TbDutyInfo;
import com.mwq.hibernate.mapping.TbReckoning;
import com.mwq.hibernate.mapping.TbReckoningList;
import com.mwq.hibernate.mapping.TbRecord;
import com.mwq.mwing.MTable;

public class PersonnelSetPanel extends JPanel {

	private JTextArea explainTextArea;

	private MTable listTable;

	private Vector<String> listTableColumnV;

	private Vector<Vector<String>> listTableValueV;

	private DefaultTableModel listTableModel;

	private MTable reckoningTable;

	private Vector<String> reckoningTableColumnV;

	private Vector<Vector<String>> reckoningTableValueV;

	private DefaultTableModel reckoningTableModel;

	private final Dao dao = Dao.getInstance();

	private final Vector<TbReckoning> reckoningV = new Vector<TbReckoning>();

	/**
	 * Create the panel
	 */
	public PersonnelSetPanel() {
		super();
		setLayout(new BorderLayout());

		final JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		add(buttonPanel, BorderLayout.NORTH);

		final JButton addButton = new JButton();
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = reckoningTable.getSelectedRow();
				if (selectedRow == -1) {
					JOptionPane.showMessageDialog(null,
							"请先到“账套管理”模块建立账套，然后才能添加人员！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					DeptAndPersonnelDialog dpDialog = new DeptAndPersonnelDialog();
					Dimension screenSize = Toolkit.getDefaultToolkit()
							.getScreenSize();
					int w = 550;
					int h = 400;
					int x = (screenSize.width - w) / 2;
					int y = (screenSize.height - h) / 2;
					dpDialog.setBounds(x, y, w, h);
					dpDialog.setVisible(true);
					int rowCount = listTable.getRowCount();
					Vector<Vector<String>> selectedRecordV = dpDialog
							.getSelectedRecordV();
					TbReckoning reckoning = reckoningV.get(selectedRow);
					for (int i = 0; i < selectedRecordV.size(); i++) {
						Vector<String> addRecordV = selectedRecordV.get(i);
						boolean had = false;
						Set eckoningLists = reckoning.getTbReckoningLists();
						Iterator reckoningListIt = eckoningLists.iterator();
						while (reckoningListIt.hasNext()) {
							TbReckoningList reckoningList = (TbReckoningList) reckoningListIt
									.next();
							String recordNumber = reckoningList.getTbRecord()
									.getRecordNumber();
							if (addRecordV.get(1).equals(recordNumber)) {
								had = true;
								break;
							}
						}
						if (!had) {
							addRecordV.set(0, ++rowCount + "");
							listTableModel.addRow(addRecordV);
							//
							TbReckoningList reckoningList = new TbReckoningList();
							reckoningList.setTbReckoning(reckoning);
							eckoningLists.add(reckoningList);
							TbRecord record = (TbRecord) dao
									.queryRecordByNum(addRecordV.get(1));
							record.setTbReckoningList(reckoningList);
							reckoningList.setTbRecord(record);
						}
					}
					rowCount -= 1;
					listTable.setRowSelectionInterval(rowCount, rowCount);
					dao.updateObject(reckoning);
					HibernateSessionFactory.closeSession();
				}
			}
		});
		addButton.setText("添加人员");
		buttonPanel.add(addButton);

		final JButton delButton = new JButton();
		delButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int rSelectedRow = reckoningTable.getSelectedRow();
				int[] selectedRows = listTable.getSelectedRows();
				if (selectedRows.length == 0) {
					JOptionPane.showMessageDialog(null, "请选择要删除的人员！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					String infos[] = new String[selectedRows.length + 1];
					infos[0] = "确定要从账套“"
							+ reckoningTable.getValueAt(rSelectedRow, 1)
							+ "”中删除下列人员？";
					for (int i = 0; i < selectedRows.length; i++) {
						infos[i + 1] = "    " + (i + 1) + "、"
								+ listTable.getValueAt(selectedRows[i], 1)
								+ "  "
								+ listTable.getValueAt(selectedRows[i], 2);
					}
					int i = JOptionPane.showConfirmDialog(null, infos, "友情提示",
							JOptionPane.YES_NO_OPTION);
					if (i == 0) {
						TbReckoning reckoning = reckoningV.get(rSelectedRow);
						for (int j = selectedRows.length - 1; j >= 0; j--) {
							Iterator reckoningListIt = reckoning
									.getTbReckoningLists().iterator();
							while (reckoningListIt.hasNext()) {
								TbReckoningList reckoningList = (TbReckoningList) reckoningListIt
										.next();
								String recordNumber = reckoningList
										.getTbRecord().getRecordNumber();
								if (listTable.getValueAt(selectedRows[j], 1)
										.equals(recordNumber)) {
									reckoning.getTbReckoningLists().remove(
											reckoningList);
									break;
								}
							}
							listTableModel.removeRow(selectedRows[j]);
						}
						dao.updateObject(reckoning);
						HibernateSessionFactory.closeSession();
						int row = selectedRows[0];
						if (row < listTable.getRowCount()) {
							for (int j = row; j < listTable.getRowCount(); j++) {
								listTable.setValueAt(j + 1, j, 0);
							}
						} else {
							row = listTable.getRowCount() - 1;
						}
						if (row >= 0)
							listTable.setRowSelectionInterval(row, row);
						//
						JOptionPane.showMessageDialog(null, "删除完成！", "友情提示",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		delButton.setText("删除人员");
		buttonPanel.add(delButton);

		final JButton delAllButton = new JButton();
		delAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = reckoningTable.getSelectedRow();
				int i = JOptionPane.showConfirmDialog(null, "确定要删除账套“"
						+ reckoningTable.getValueAt(selectedRow, 1)
						+ "”中的所有人员？", "友情提示", JOptionPane.YES_NO_OPTION);
				if (i == 0) {
					listTableValueV.removeAllElements();
					listTableModel.setDataVector(listTableValueV,
							listTableColumnV);
					TbReckoning reckoning = reckoningV.get(selectedRow);
					reckoning.getTbReckoningLists().clear();
					dao.updateObject(reckoning);
					HibernateSessionFactory.closeSession();
					JOptionPane.showMessageDialog(null, "删除完成！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		delAllButton.setText("全部删除");
		buttonPanel.add(delAllButton);

		final JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(260);
		add(splitPane);

		final JPanel reckoningPanel = new JPanel();
		reckoningPanel.setBackground(Color.WHITE);
		splitPane.setLeftComponent(reckoningPanel);
		reckoningPanel.setLayout(new BorderLayout());

		final JLabel reckoningLabel = new JLabel();
		reckoningLabel.setPreferredSize(new Dimension(0, 30));
		reckoningLabel.setText("  账套列表：");
		reckoningPanel.add(reckoningLabel, BorderLayout.NORTH);

		final JScrollPane reckoningScrollPane = new JScrollPane();
		reckoningPanel.add(reckoningScrollPane);

		reckoningTableColumnV = new Vector<String>();
		reckoningTableColumnV.add("序号");
		reckoningTableColumnV.add("账套名称");

		reckoningTableValueV = new Vector<Vector<String>>();

		reckoningTableModel = new DefaultTableModel(reckoningTableValueV,
				reckoningTableColumnV);

		reckoningTable = new MTable(reckoningTableModel);
		reckoningTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				refreshListTableValue(reckoningTable.getSelectedRow());
			}
		});
		reckoningTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		reckoningScrollPane.setViewportView(reckoningTable);

		final JLabel reckoningRightLabel = new JLabel();
		reckoningRightLabel.setPreferredSize(new Dimension(12, 20));
		reckoningPanel.add(reckoningRightLabel, BorderLayout.EAST);

		final JLabel reckoningLeftLabel = new JLabel();
		reckoningLeftLabel.setPreferredSize(new Dimension(12, 20));
		reckoningPanel.add(reckoningLeftLabel, BorderLayout.WEST);

		final JPanel listPanel = new JPanel();
		listPanel.setBackground(Color.WHITE);
		splitPane.setRightComponent(listPanel);
		listPanel.setLayout(new BorderLayout());

		final JLabel listLabel = new JLabel();
		listLabel.setPreferredSize(new Dimension(0, 30));
		listLabel.setText("  人员列表：");
		listPanel.add(listLabel, BorderLayout.NORTH);

		final JScrollPane listScrollPane = new JScrollPane();
		listPanel.add(listScrollPane);

		listTableColumnV = new Vector<String>();
		String listTableColumns[] = { "序号", "档案编号", "姓名", "性别", "部门", "职务" };
		for (int i = 0; i < listTableColumns.length; i++) {
			listTableColumnV.add(listTableColumns[i]);
		}

		listTableValueV = new Vector<Vector<String>>();

		listTableModel = new DefaultTableModel(listTableValueV,
				listTableColumnV);

		listTable = new MTable(listTableModel);
		listScrollPane.setViewportView(listTable);

		final JLabel listLeftLabel = new JLabel();
		listLeftLabel.setPreferredSize(new Dimension(12, 20));
		listPanel.add(listLeftLabel, BorderLayout.WEST);

		final JPanel explainPanel = new JPanel();
		explainPanel.setBorder(new TitledBorder(null, "", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
		explainPanel.setBackground(Color.WHITE);
		explainPanel.setLayout(new GridBagLayout());
		add(explainPanel, BorderLayout.SOUTH);

		final JLabel explainLabel = new JLabel();
		explainLabel.setText("账套说明：");
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.insets = new Insets(0, 0, 60, 0);
		explainPanel.add(explainLabel, gridBagConstraints_1);

		final JScrollPane explainScrollPane = new JScrollPane();
		explainScrollPane.setPreferredSize(new Dimension(766, 80));
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 1;
		explainPanel.add(explainScrollPane, gridBagConstraints);

		explainTextArea = new JTextArea();
		explainTextArea.setLineWrap(true);
		explainTextArea.setEditable(false);
		explainTextArea.setRows(3);
		explainTextArea.setColumns(600);
		explainScrollPane.setViewportView(explainTextArea);
		//

		Iterator reckoningIt = dao.queryReckoning().iterator();
		int reckoningNum = 1;
		while (reckoningIt.hasNext()) {
			TbReckoning reckoning = (TbReckoning) reckoningIt.next();
			reckoningV.add(reckoning);
			Vector<String> rowValueV = new Vector<String>();
			rowValueV.add("" + reckoningNum++);
			rowValueV.add(reckoning.getName());
			reckoningTableValueV.add(rowValueV);
		}

		if (reckoningTable.getRowCount() > 0) {
			reckoningTable.setRowSelectionInterval(0, 0);
			refreshListTableValue(0);
		}
		HibernateSessionFactory.closeSession();
	}

	public void refreshListTableValue(int reckoningTableSelectedRow) {
		listTableValueV.removeAllElements();
		TbReckoning reckoning = reckoningV.get(reckoningTableSelectedRow);
		explainTextArea.setText(reckoning.getExplain());
		Iterator reckoningListIt = reckoning.getTbReckoningLists().iterator();
		int listNum = 1;
		while (reckoningListIt.hasNext()) {
			TbReckoningList reckoningList = (TbReckoningList) reckoningListIt
					.next();
			TbRecord record = reckoningList.getTbRecord();
			Vector<String> rowValueV = new Vector<String>();
			rowValueV.add("" + listNum++);
			rowValueV.add(record.getRecordNumber());
			rowValueV.add(record.getName());
			rowValueV.add(record.getSex());
			TbDutyInfo dutyInfo = record.getTbDutyInfo();
			rowValueV.add(dutyInfo.getTbDept().getName());
			rowValueV.add(dutyInfo.getTbDuty().getName());
			listTableValueV.add(rowValueV);
		}
		listTableModel.setDataVector(listTableValueV, listTableColumnV);
		if (listTable.getRowCount() > 0)
			listTable.setRowSelectionInterval(0, 0);
	}

}
