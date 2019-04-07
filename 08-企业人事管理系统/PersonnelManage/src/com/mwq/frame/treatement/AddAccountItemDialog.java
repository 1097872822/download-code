package com.mwq.frame.treatement;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.mwq.hibernate.Dao;
import com.mwq.hibernate.HibernateSessionFactory;
import com.mwq.hibernate.mapping.TbAccountItem;
import com.mwq.mwing.MTable;

public class AddAccountItemDialog extends JDialog {

	private MTable table;

	private Dao dao;

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			AddAccountItemDialog dialog = new AddAccountItemDialog();
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

	/**
	 * Create the dialog
	 */
	public AddAccountItemDialog() {
		super();
		dao = Dao.getInstance();
		setTitle("添加项目");
		setModal(true);
		setBounds(100, 100, 500, 375);

		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);

		Vector<String> columnNameV = new Vector<String>();
		columnNameV.add("序号");
		columnNameV.add("名称");
		columnNameV.add("单位");
		columnNameV.add("类型");
		Vector<Vector<String>> cellV = new Vector<Vector<String>>();
		Iterator it = dao.queryAccountItem().iterator();
		int num = 1;
		while (it.hasNext()) {
			Vector<String> itemV = new Vector<String>();
			TbAccountItem accountItem = (TbAccountItem) it.next();
			itemV.add(num++ + "");
			itemV.add(accountItem.getName());
			itemV.add(accountItem.getUnit());
			itemV.add(accountItem.getType());
			cellV.add(itemV);
		}
		HibernateSessionFactory.closeSession();

		DefaultTableModel tableModel = new DefaultTableModel(cellV, columnNameV);

		table = new MTable(tableModel);
		scrollPane.setViewportView(table);

		final JPanel panel = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setLayout(flowLayout);
		getContentPane().add(panel, BorderLayout.SOUTH);

		final JButton exitButton = new JButton();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		exitButton.setText("退出");
		panel.add(exitButton);

		final JButton selectAllButton = new JButton();
		selectAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table.selectAll();
			}
		});
		selectAllButton.setText("全选");
		panel.add(selectAllButton);

		final JButton addButton = new JButton();
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRows().length == 0) {
					JOptionPane.showMessageDialog(null, "请选择要添加的项目！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				setVisible(false);
			}
		});
		addButton.setText("添加");
		panel.add(addButton);

		final JLabel bottomLabel = new JLabel();
		bottomLabel.setPreferredSize(new Dimension(10, 30));
		panel.add(bottomLabel);

		final JLabel leftLabel = new JLabel();
		leftLabel.setPreferredSize(new Dimension(20, 20));
		getContentPane().add(leftLabel, BorderLayout.WEST);

		final JLabel rightLabel = new JLabel();
		rightLabel.setPreferredSize(new Dimension(20, 20));
		getContentPane().add(rightLabel, BorderLayout.EAST);

		final JLabel topLabel = new JLabel();
		topLabel.setPreferredSize(new Dimension(20, 20));
		getContentPane().add(topLabel, BorderLayout.NORTH);
		//
	}

	public JTable getTable() {
		return table;
	}

}
