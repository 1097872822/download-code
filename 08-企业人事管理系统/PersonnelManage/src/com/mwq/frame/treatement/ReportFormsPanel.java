package com.mwq.frame.treatement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import com.mwq.hibernate.Dao;
import com.mwq.hibernate.mapping.TbAccountItem;
import com.mwq.hibernate.mapping.TbDutyInfo;
import com.mwq.hibernate.mapping.TbReckoning;
import com.mwq.hibernate.mapping.TbReckoningInfo;
import com.mwq.hibernate.mapping.TbReckoningList;
import com.mwq.hibernate.mapping.TbRecord;
import com.mwq.hibernate.mapping.TbRewardsAndPunishment;
import com.mwq.hibernate.mapping.TbTimecard;
import com.mwq.mwing.MTable;
import com.mwq.tool.Today;

public class ReportFormsPanel extends JPanel {

	private JComboBox monthComboBox;

	private JComboBox quarterComboBox;

	private JComboBox halfYearComboBox;

	private JComboBox yearComboBox;

	private ButtonGroup buttonGroup = new ButtonGroup();

	private MTable table;

	private final Vector<String> tableColumnV = new Vector<String>();

	private final Vector<Vector> tableValueV = new Vector<Vector>();

	private final DefaultTableModel tableModel = new DefaultTableModel(
			tableValueV, tableColumnV);

	private final Dao dao = Dao.getInstance();

	/**
	 * Create the panel
	 */
	public ReportFormsPanel() {
		super();
		setLayout(new BorderLayout());

		final JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		add(buttonPanel, BorderLayout.NORTH);

		final JLabel createLabel = new JLabel();
		createLabel.setText("����");
		buttonPanel.add(createLabel);

		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.setPreferredSize(new Dimension(175, 31));
		panel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		panel.setBackground(Color.WHITE);
		buttonPanel.add(panel);

		final JRadioButton monthRadioButton = new JRadioButton();
		monthRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				halfYearComboBox.setEnabled(false);
				quarterComboBox.setEnabled(false);
				monthComboBox.setEnabled(true);
			}
		});
		panel.add(monthRadioButton);
		buttonGroup.add(monthRadioButton);
		monthRadioButton.setSelected(true);
		monthRadioButton.setBackground(Color.WHITE);
		monthRadioButton.setText("��");

		final JRadioButton quarterRadioButton = new JRadioButton();
		quarterRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				halfYearComboBox.setEnabled(false);
				quarterComboBox.setEnabled(true);
				monthComboBox.setEnabled(false);
			}
		});
		panel.add(quarterRadioButton);
		buttonGroup.add(quarterRadioButton);
		quarterRadioButton.setBackground(Color.WHITE);
		quarterRadioButton.setText("��");

		final JRadioButton halfYearRadioButton = new JRadioButton();
		halfYearRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				halfYearComboBox.setEnabled(true);
				quarterComboBox.setEnabled(false);
				monthComboBox.setEnabled(false);
			}
		});
		panel.add(halfYearRadioButton);
		buttonGroup.add(halfYearRadioButton);
		halfYearRadioButton.setBackground(Color.WHITE);
		halfYearRadioButton.setText("����");

		final JRadioButton yearRadioButton = new JRadioButton();
		yearRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				halfYearComboBox.setEnabled(false);
				quarterComboBox.setEnabled(false);
				monthComboBox.setEnabled(false);
			}
		});
		panel.add(yearRadioButton);
		buttonGroup.add(yearRadioButton);
		yearRadioButton.setBackground(Color.WHITE);
		yearRadioButton.setText("��");

		final JLabel reportFormsLabel = new JLabel();
		reportFormsLabel.setText("����          ");
		buttonPanel.add(reportFormsLabel);

		yearComboBox = new JComboBox();
		Object minAccessionDateObj = dao.queryRecordOfMinAccessionDate();
		String minAccessionDate = Today.YEAR + "";
		if (minAccessionDateObj != null)
			minAccessionDate = minAccessionDateObj.toString();
		int minYear = Integer.valueOf(minAccessionDate.substring(0, 4));
		for (int i = Today.YEAR; i >= minYear; i--) {
			yearComboBox.addItem(i);
		}
		buttonPanel.add(yearComboBox);

		final JLabel yearLabel = new JLabel();
		yearLabel.setText("��");
		buttonPanel.add(yearLabel);

		halfYearComboBox = new JComboBox();
		halfYearComboBox.addItem("��");
		halfYearComboBox.addItem("��");
		buttonPanel.add(halfYearComboBox);

		final JLabel halfYearLabel = new JLabel();
		halfYearLabel.setText("����");
		buttonPanel.add(halfYearLabel);

		quarterComboBox = new JComboBox();
		quarterComboBox.addItem("��һ");
		quarterComboBox.addItem("�ڶ�");
		quarterComboBox.addItem("����");
		quarterComboBox.addItem("����");
		buttonPanel.add(quarterComboBox);

		final JLabel quarterLabel = new JLabel();
		quarterLabel.setText("����");
		buttonPanel.add(quarterLabel);

		monthComboBox = new JComboBox();
		monthComboBox.setMaximumRowCount(12);
		monthComboBox.addItem("1");
		monthComboBox.addItem("2");
		monthComboBox.addItem("3");
		monthComboBox.addItem("4");
		monthComboBox.addItem("5");
		monthComboBox.addItem("6");
		monthComboBox.addItem("7");
		monthComboBox.addItem("8");
		monthComboBox.addItem("9");
		monthComboBox.addItem("10");
		monthComboBox.addItem("11");
		monthComboBox.addItem("12");
		buttonPanel.add(monthComboBox);

		final JLabel monthLabel = new JLabel();
		monthLabel.setText("�·�    ");
		buttonPanel.add(monthLabel);

		final JButton submitButton = new JButton();
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tableValueV.removeAllElements();// �Ƴ�����е�������
				Enumeration<AbstractButton> buttonEnu = buttonGroup
						.getElements();
				while (buttonEnu.hasMoreElements()) {// �����������͵�ѡ��ť
					JRadioButton button = (JRadioButton) buttonEnu
							.nextElement();// ��õ�ѡ��ť����
					if (button.isSelected()) {// ��ѡ��ť��ѡ��
						String year = yearComboBox.getSelectedItem().toString();// ��ñ������
						String reportFormsType = button.getText();// ��ñ�����������
						if (reportFormsType.equals("��")) {// �����±���
							String month = monthComboBox.getSelectedItem()
									.toString();// ��ñ����·�
							String monthDay[] = new String[] { "00", "31",
									"28", "31", "30", "31", "30", "31", "31",
									"30", "31", "30", "31" };
							if (month.equals("2")) {// ���������2�·ݵı�����Ҫ�ж��Ƿ�Ϊ����
								int y = Integer.valueOf(year);
								if (y / 100 == 0) {
									if (y / 400 == 0)
										monthDay[2] = "29";
								} else {
									if (y / 4 == 0)
										monthDay[2] = "29";
								}
								reportForms(year + "-2-1", year + "-2-"
										+ monthDay[2]);// ���ɱ���
							} else {
								reportForms(year + "-" + month + "-1", year
										+ "-" + month + "-"
										+ monthDay[Integer.valueOf(month)]);// ���ɱ���
							}
						} else if (reportFormsType.equals("��")) {// ���ɼ�����
							String quarter = quarterComboBox.getSelectedItem()
									.toString();// ��ñ�����
							if (quarter.equals("��һ")) {
								reportForms(year + "-1-1", year + "-3-31");// ���ɱ���
							} else if (quarter.equals("�ڶ�")) {
								reportForms(year + "-4-1", year + "-6-30");// ���ɱ���
							} else if (quarter.equals("����")) {
								reportForms(year + "-7-1", year + "-9-30");// ���ɱ���
							} else {// ����
								reportForms(year + "-10-1", year + "-12-31");// ���ɱ���
							}
						} else if (reportFormsType.equals("����")) {// ���ɰ��걨��
							String halfYear = halfYearComboBox
									.getSelectedItem().toString();// ��ñ���ʱ��
							if (halfYear.equals("��")) {
								reportForms(year + "-1-1", year + "-6-30");// ���ɱ���
							} else {// ��
								reportForms(year + "-7-1", year + "-12-31");// ���ɱ���
							}
						} else { // �����걨��
							reportForms(year + "-1-1", year + "-12-31");// ���ɱ���
						}
						break;
					}
				}
			}
		});
		submitButton.setText("ȷ��");
		buttonPanel.add(submitButton);

		final JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		tableColumnV.add("���");
		tableColumnV.add("�������");
		tableColumnV.add("����");
		tableColumnV.add("�Ա�");
		tableColumnV.add("����");
		tableColumnV.add("ְ��");
		Iterator accountItemIt = dao.queryAccountItem().iterator();
		while (accountItemIt.hasNext()) {
			TbAccountItem accountItem = (TbAccountItem) accountItemIt.next();
			tableColumnV.add(accountItem.getName());
		}
		tableColumnV.add("����");
		tableColumnV.add("�ͷ�");
		tableColumnV.add("ʵ�����");

		table = new MTable(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		//
		halfYearComboBox.setEnabled(false);
		quarterComboBox.setEnabled(false);
		setComboBoxSelected();
	}

	public void setComboBoxSelected() {
		yearComboBox.setSelectedItem(Today.YEAR + "");
		int month = Today.MONTH;
		switch (month) {
		case 1:
		case 2:
		case 3:
			halfYearComboBox.setSelectedIndex(0);
			quarterComboBox.setSelectedIndex(0);
			break;
		case 4:
		case 5:
		case 6:
			halfYearComboBox.setSelectedIndex(0);
			quarterComboBox.setSelectedIndex(1);
			break;
		case 7:
		case 8:
		case 9:
			halfYearComboBox.setSelectedIndex(1);
			quarterComboBox.setSelectedIndex(2);
			break;
		case 10:
		case 11:
		case 12:
			halfYearComboBox.setSelectedIndex(1);
			quarterComboBox.setSelectedIndex(3);
			break;
		}
		monthComboBox.setSelectedItem(month + "");
	}

	public void reportForms(String reportStartDateStr, String reprotEndDateStr) {
		Iterator dutyInfoIt = dao.queryDutyInfoOfAccessionDateMax(
				reportStartDateStr).iterator();
		int num = 1;
		while (dutyInfoIt.hasNext()) {
			TbDutyInfo dutyInfo = (TbDutyInfo) dutyInfoIt.next();
			TbRecord record = (TbRecord) dutyInfo.getTbRecord();
			Vector recordV = new Vector();// �����뵵�������Ӧ������
			recordV.add(num++);// ������
			recordV.add(record.getRecordNumber());// ��ӵ������
			recordV.add(record.getName());// ��� ����
			recordV.add(record.getSex());// ����Ա�
			recordV.add(dutyInfo.getTbDept().getName());// ��Ӳ���
			recordV.add(dutyInfo.getTbDuty().getName());// ���ְ��
			int salary = 0;// ��ʼʵ�����Ϊ0
			// �����·�
			Date reportStartDate = null;
			Date reportEndDate = null;
			DateFormat df = DateFormat.getDateInstance();
			try {
				reportStartDate = df.parse(reportStartDateStr);
				reportEndDate = df.parse(reprotEndDateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// ���㿼����Ŀ
			int column = 6;
			int columnCount = table.getColumnCount() - 3;
			TbReckoningList reckoningList = record.getTbReckoningList();
			if (reckoningList != null) {
				TbReckoning reckoning = reckoningList.getTbReckoning();
				Iterator reckoningInfoIt = reckoning.getTbReckoningInfos()
						.iterator();
				while (reckoningInfoIt.hasNext()) {
					TbReckoningInfo reckoningInfo = (TbReckoningInfo) reckoningInfoIt
							.next();
					int money = reckoningInfo.getMoney();
					TbAccountItem accountItem = reckoningInfo
							.getTbAccountItem();
					String itemName = accountItem.getName();
					for (; column < columnCount;) {
						String columnName = table.getColumnName(column);
						column++;
						if (itemName.equals(columnName)) {
							if (accountItem.getType().toString().trim().equals(
									"�۳�"))
								money = -money;
							if (accountItem.getIsTimecard().equals("��")) {// ������Ŀ
								String filterHql = "where this.tbAccountItem.name='"
										+ columnName
										+ "' and ( ( startDate between to_date('"
										+ reportStartDateStr
										+ "','yyyy-mm-dd') and to_date('"
										+ reportStartDateStr
										+ "','yyyy-mm-dd') or endDate between to_date('"
										+ reportStartDateStr
										+ "','yyyy-mm-dd') and to_date('"
										+ reportStartDateStr
										+ "','yyyy-mm-dd') ) or ( to_date('"
										+ reportStartDateStr
										+ "','yyyy-mm-dd') between startDate and endDate and to_date('"
										+ reportStartDateStr
										+ "','yyyy-mm-dd') between startDate and endDate ) )";
								List list = dao
										.filterSet(record
												.getTbTimecardsForRecordId(),
												filterHql);
								int times = 0;
								for (Iterator iter = list.iterator(); iter
										.hasNext();) {
									TbTimecard timecard = (TbTimecard) iter
											.next();
									Date startDate = timecard.getStartDate();
									Date endDate = timecard.getEndDate();
									int ms = 1;
									int me = Integer.valueOf(reportEndDate
											.toString().substring(8, 10));
									int sd = Integer.valueOf(startDate
											.toString().substring(8, 10));
									int ed = Integer.valueOf(endDate.toString()
											.substring(8, 10));
									if (startDate.compareTo(reportStartDate) > 0) {
										if (endDate.compareTo(reportEndDate) > 0) {
											times += (me - sd + 1);
										} else {
											times += (ed - sd);
										}
									} else {
										if (endDate.compareTo(reportEndDate) > 0) {
											times += (me - ms);
										} else {
											times += (ed - ms);
										}
									}
									times += 1;
								}
								recordV.add(money + " x " + times);// ��Ŀ���
								salary += (money * times);
							} else {// �ǿ�����Ŀ
								recordV.add(money);// ��Ŀ���
								salary += money;
							}
							break;
						} else {
							recordV.add("��");// δ����Ŀ
						}
					}
				}
			}
			// ���δ������Ŀ
			for (; column < columnCount; column++) {
				recordV.add("��");// δ����Ŀ
			}
			// ���㽱����Ŀ
			Set rewAndPuns = record.getTbRewardsAndPunishmentsForRecordId();
			String types[] = new String[] { "����", "�ͷ�" };
			for (int i = 0; i < types.length; i++) {
				String filterHql = "where this.type='"
						+ types[i]
						+ "' and ( ( startDate between to_date('"
						+ reportStartDateStr
						+ "','yyyy-mm-dd') and to_date('"
						+ reprotEndDateStr
						+ "','yyyy-mm-dd') or endDate between to_date('"
						+ reportStartDateStr
						+ "','yyyy-mm-dd') and to_date('"
						+ reprotEndDateStr
						+ "','yyyy-mm-dd') ) or ( to_date('"
						+ reportStartDateStr
						+ "','yyyy-mm-dd') between startDate and endDate and to_date('"
						+ reprotEndDateStr
						+ "','yyyy-mm-dd') between startDate and endDate ) )";
				System.out.println(filterHql);
				List list = dao.filterSet(rewAndPuns, filterHql);// ���˽��ͼ�¼
				if (list.size() > 0) {// ���ڽ���
					column += 1;// ��������1
					int money = 0;// ��ʼ���ͽ��Ϊ0
					for (Iterator it = list.iterator(); it.hasNext();) {
						TbRewardsAndPunishment rewAndPun = (TbRewardsAndPunishment) it
								.next();
						money += rewAndPun.getMoney();// �ۼӽ��ͽ��
					}
					recordV.add(money);// ��ӽ��ͽ��
					if (i == 0) // ����
						salary += money;// ����ʵ�����
					else
						// �ͷ�
						salary -= money;// ����ʵ�����

				} else {
					recordV.add("��");// û�н�����ͷ�
				}
			}
			recordV.add(salary);
			tableModel.addRow(recordV);
		}
	}

}
