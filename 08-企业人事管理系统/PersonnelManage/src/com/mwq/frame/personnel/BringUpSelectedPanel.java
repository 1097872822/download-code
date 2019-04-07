package com.mwq.frame.personnel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import com.mwq.hibernate.Dao;
import com.mwq.hibernate.mapping.TbBringUpContent;
import com.mwq.mwing.MTable;

public class BringUpSelectedPanel extends JPanel {

	private MTable table;

	private Vector<String> tableColumnV;

	private Vector<Vector<String>> tableValueV;

	private DefaultTableModel tableModel;

	Dao dao = Dao.getInstance();

	/**
	 * Create the panel
	 */
	public BringUpSelectedPanel(final JPanel rightPanel) {
		super();
		setLayout(new BorderLayout());

		final JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);

		final JButton addButton = new JButton();
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rightPanel.removeAll();
				rightPanel.add(new BringUpOperatePanel(rightPanel, null),
						BorderLayout.CENTER);
				SwingUtilities.updateComponentTreeUI(rightPanel);
			}
		});
		addButton.setText("�½�");
		panel.add(addButton);

		final JButton seeButton = new JButton();
		seeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String bucId = table.getValueAt(table.getSelectedRow(), 0)
						.toString();
				rightPanel.removeAll();
				rightPanel.add(new BringUpOperatePanel(rightPanel, bucId),
						BorderLayout.CENTER);
				SwingUtilities.updateComponentTreeUI(rightPanel);
			}
		});
		seeButton.setText("�鿴");
		panel.add(seeButton);

		final JScrollPane scrollPane = new JScrollPane();
		add(scrollPane);

		tableColumnV = new Vector<String>();
		String tableColumns[] = { "���", "��ѵ����", "��ѵ����", "��ѵ����", "��ѵʱ��", "��ѵ�ص�",
				"��ѵ����", "��ѵ��λ", "��ѵ��ʦ" };
		for (int i = 0; i < tableColumns.length; i++) {
			tableColumnV.add(tableColumns[i]);
		}

		tableValueV = new Vector<Vector<String>>();
		Iterator bucIt = dao.queryBringUpContent().iterator();
		while (bucIt.hasNext()) {
			TbBringUpContent buc = (TbBringUpContent) bucIt.next();
			Vector<String> bucV = new Vector<String>();
			bucV.add(buc.getId() + "");
			bucV.add(buc.getName());
			bucV.add(buc.getObject());
			bucV.add(buc.getTbBringUpOntents().size() + "");
			bucV.add(buc.getStartDate().toString().substring(0, 15)
					+ buc.getEndDate().toString().substring(0, 15));
			bucV.add(buc.getPlace());
			bucV.add(buc.getContent());
			bucV.add(buc.getUnit());
			bucV.add(buc.getLecturer());
			tableValueV.add(bucV);
		}

		tableModel = new DefaultTableModel(tableValueV, tableColumnV);

		table = new MTable(tableModel);
		table.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		if (table.getRowCount() > 0)
			table.setRowSelectionInterval(0, 0);
		scrollPane.setViewportView(table);
		//
	}

}
