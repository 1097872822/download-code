package com.mwq.frame.personnel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mwq.frame.common.DeptTreeDialog;
import com.mwq.hibernate.Dao;
import com.mwq.hibernate.HibernateSessionFactory;
import com.mwq.hibernate.mapping.TbDept;
import com.mwq.hibernate.mapping.TbDutyInfo;
import com.mwq.hibernate.mapping.TbRecord;
import com.mwq.hibernate.mapping.TbRewardsAndPunishment;
import com.mwq.tool.Today;

public class RewardsAndPunishmentPanel extends JPanel {

	private JTextField ratifierDeptTextField;

	private JTextField inDeptTextField;

	private JTextField ratifierDateTextField;

	private JTextArea reasonTextArea;

	private ButtonGroup buttonGroup = new ButtonGroup();

	private JTextField endDateTextField;

	private JComboBox ratifierPersonComboBox;

	private JTextField startDateTextField;

	private JTextField moneyTextField;

	private JTextArea contentTextArea;

	private Dao dao = Dao.getInstance();

	final JRadioButton rewardsRadioButton;

	final JRadioButton punishmentRadioButton;

	private JComboBox personnalComboBox;

	/**
	 * Create the panel
	 */
	public RewardsAndPunishmentPanel() {
		super();
		setLayout(new BorderLayout());

		final JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.NORTH);

		final JButton createButton = new JButton();
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inDeptTextField.setText(null);
				personnalComboBox.removeAllItems();
				personnalComboBox.addItem("请选择");
				Iterator recordIt = dao.queryRecord().iterator();
				while (recordIt.hasNext()) {
					TbRecord record = (TbRecord) recordIt.next();
					personnalComboBox.addItem(record.getRecordNumber() + "    "
							+ record.getName());
				}
				HibernateSessionFactory.closeSession();
				rewardsRadioButton.setSelected(false);
				punishmentRadioButton.setSelected(false);
				reasonTextArea.setText(null);
				contentTextArea.setText(null);
				moneyTextField.setText("00.00");
				startDateTextField.setText(Today.TODAY_DATE);
				endDateTextField.setText(Today.TODAY_DATE);
				ratifierDeptTextField.setText("");
				ratifierPersonComboBox.removeAllItems();
				ratifierPersonComboBox.setEnabled(false);
				ratifierDateTextField.setText(Today.TODAY_DATE);
			}
		});
		createButton.setText("新建");
		panel.add(createButton);

		final JButton saveButton = new JButton();
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//
				String personnal = personnalComboBox.getSelectedItem()
						.toString();
				if (personnal.equals("请选择")) {
					JOptionPane.showMessageDialog(null, "请选择欲奖惩的职员！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				//
				String type = null;
				if (rewardsRadioButton.isSelected())
					type = rewardsRadioButton.getText();
				if (punishmentRadioButton.isSelected())
					type = punishmentRadioButton.getText();
				if (type == null) {
					JOptionPane.showMessageDialog(null, "请选择奖惩类型！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				//
				if (reasonTextArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请填写奖惩原因！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				//
				if (contentTextArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请填写奖惩内容！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				//
				String money = moneyTextField.getText();
				if (money.equals("0.00")) {
					money = "0";
				} else {
					try {
						money = Float.valueOf(money).intValue() + "";
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "奖惩金额填写错误，请重新填写！",
								"友情提示", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
				//
				String startDate = startDateTextField.getText();
				String endDate = endDateTextField.getText();
				if (startDate.equals("") || endDate.equals("")) {
					JOptionPane.showMessageDialog(null, "请填写奖惩的起止日期！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					try {
						Date.valueOf(startDate);
						Date.valueOf(endDate);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "起止日期填写错误，请重新填写！",
								"友情提示", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}
				//
				if (!ratifierPersonComboBox.isEnabled()) {
					JOptionPane.showMessageDialog(null, "请先选择批准的部门，然后再选择批准人！",
							"友情提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String ratifierPerson = ratifierPersonComboBox
						.getSelectedItem().toString();
				if (ratifierPerson.equals("请选择")) {
					JOptionPane.showMessageDialog(null, "请选择批准部门及批准人！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				//
				String ratifierDate = ratifierDateTextField.getText();
				if (ratifierDate.equals("")) {
					JOptionPane.showMessageDialog(null, "请填写奖惩的起止日期！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					try {
						Date.valueOf(ratifierDate);
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "批准日期填写错误，请重新填写！",
								"友情提示", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				}

				//
				TbRewardsAndPunishment rap = new TbRewardsAndPunishment();
				TbRecord record = (TbRecord) dao.queryRecordByNum(personnal
						.substring(0, 6));
				rap.setTbRecordByRecordId(record);
				rap.setType(type);
				rap.setReason(reasonTextArea.getText());
				rap.setContent(contentTextArea.getText());
				rap.setMoney(new Integer(money));
				rap.setStartDate(Date.valueOf(startDate));
				rap.setEndDate(Date.valueOf(endDate));
				TbDept ratifierDept = (TbDept) dao
						.queryDeptByName(ratifierDeptTextField.getText());
				rap.setTbDept(ratifierDept);
				TbRecord ratifierRecord = (TbRecord) dao
						.queryRecordByNum(ratifierPerson.substring(0, 6));
				rap.setTbRecordByRatifierRecordId(ratifierRecord);
				rap.setRatifierDate(Date.valueOf(ratifierDate));
				// 持久化奖惩信息
				dao.saveObject(rap);
				HibernateSessionFactory.closeSession();

				//
				JOptionPane.showMessageDialog(null, "已经成功保存此次奖惩信息！", "友情提示",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		saveButton.setText("保存");
		panel.add(saveButton);

		final JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setLayout(new GridBagLayout());
		add(panel_1, BorderLayout.CENTER);

		final JLabel inDeptLabel = new JLabel();
		inDeptLabel.setText("所在部门：");
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		panel_1.add(inDeptLabel, gridBagConstraints);

		inDeptTextField = new JTextField();
		inDeptTextField.setEditable(false);
		inDeptTextField.setColumns(11);
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_1.gridy = 0;
		gridBagConstraints_1.gridx = 1;
		panel_1.add(inDeptTextField, gridBagConstraints_1);

		final JButton inDeptTreeButton = new JButton();
		inDeptTreeButton.setMargin(new Insets(0, 6, 0, 3));
		inDeptTreeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeptTreeDialog deptTree = new DeptTreeDialog(inDeptTextField);
				deptTree.setBounds(465, 259, 102, 175);
				deptTree.setVisible(true);
				TbDept dept = (TbDept) dao.queryDeptByName(inDeptTextField
						.getText());
				personnalComboBox.removeAllItems();
				personnalComboBox.addItem("请选择");
				Iterator dutyInfoIt = dept.getTbDutyInfos().iterator();
				while (dutyInfoIt.hasNext()) {
					TbDutyInfo dutyInfo = (TbDutyInfo) dutyInfoIt.next();
					TbRecord tbRecord = dutyInfo.getTbRecord();
					personnalComboBox.addItem(tbRecord.getRecordNumber()
							+ "    " + tbRecord.getName());
				}
			}
		});
		inDeptTreeButton.setText("...");
		final GridBagConstraints gridBagConstraints_22 = new GridBagConstraints();
		gridBagConstraints_22.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_22.gridy = 0;
		gridBagConstraints_22.gridx = 2;
		panel_1.add(inDeptTreeButton, gridBagConstraints_22);

		final JLabel label = new JLabel();
		label.setPreferredSize(new Dimension(50, 20));
		final GridBagConstraints gridBagConstraints_24 = new GridBagConstraints();
		gridBagConstraints_24.gridy = 0;
		gridBagConstraints_24.gridx = 3;
		panel_1.add(label, gridBagConstraints_24);

		rewardsRadioButton = new JRadioButton();
		rewardsRadioButton.setBackground(Color.WHITE);
		buttonGroup.add(rewardsRadioButton);
		rewardsRadioButton.setText("奖励");
		final GridBagConstraints gridBagConstraints_18 = new GridBagConstraints();
		gridBagConstraints_18.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_18.gridy = 0;
		gridBagConstraints_18.gridx = 5;
		panel_1.add(rewardsRadioButton, gridBagConstraints_18);

		final JLabel personnalLabel = new JLabel();
		personnalLabel.setText("奖惩员工：");
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_3.gridy = 1;
		gridBagConstraints_3.gridx = 0;
		panel_1.add(personnalLabel, gridBagConstraints_3);

		personnalComboBox = new JComboBox();
		personnalComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) { // 查看是否为新选中的选项触发的
					String selectedItem = (String) e.getItem();
					if (!selectedItem.equals("请选择")) {
						TbRecord record = (TbRecord) dao
								.queryRecordByNum(selectedItem.substring(0, 6));
						inDeptTextField.setText(record.getTbDutyInfo()
								.getTbDept().getName());
					}
				}
			}
		});
		personnalComboBox.addItem("请选择");
		Iterator recordsIt = dao.queryRecord().iterator();
		while (recordsIt.hasNext()) {
			TbRecord record = (TbRecord) recordsIt.next();
			personnalComboBox.addItem(record.getRecordNumber() + "    "
					+ record.getName());
		}
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.gridwidth = 3;
		gridBagConstraints_2.anchor = GridBagConstraints.WEST;
		gridBagConstraints_2.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_2.gridy = 1;
		gridBagConstraints_2.gridx = 1;
		panel_1.add(personnalComboBox, gridBagConstraints_2);

		punishmentRadioButton = new JRadioButton();
		punishmentRadioButton.setBackground(Color.WHITE);
		buttonGroup.add(punishmentRadioButton);
		punishmentRadioButton.setText("惩罚");
		final GridBagConstraints gridBagConstraints_19 = new GridBagConstraints();
		gridBagConstraints_19.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_19.gridy = 1;
		gridBagConstraints_19.gridx = 5;
		panel_1.add(punishmentRadioButton, gridBagConstraints_19);

		final JLabel reasonLabel = new JLabel();
		reasonLabel.setText("原    因：");
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.insets = new Insets(0, 0, 120, 0);
		gridBagConstraints_4.gridy = 2;
		gridBagConstraints_4.gridx = 0;
		panel_1.add(reasonLabel, gridBagConstraints_4);

		final JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setAutoscrolls(true);
		final GridBagConstraints gridBagConstraints_10 = new GridBagConstraints();
		gridBagConstraints_10.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_10.gridwidth = 5;
		gridBagConstraints_10.gridy = 2;
		gridBagConstraints_10.gridx = 1;
		panel_1.add(scrollPane_1, gridBagConstraints_10);

		reasonTextArea = new JTextArea();
		reasonTextArea.setPreferredSize(new Dimension(280, 120));
		reasonTextArea.setLineWrap(true);
		scrollPane_1.setViewportView(reasonTextArea);

		final JLabel contentLabel = new JLabel();
		contentLabel.setText("内    容：");
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.insets = new Insets(0, 0, 120, 0);
		gridBagConstraints_5.gridy = 3;
		gridBagConstraints_5.gridx = 0;
		panel_1.add(contentLabel, gridBagConstraints_5);

		final JScrollPane scrollPane = new JScrollPane();
		final GridBagConstraints gridBagConstraints_11 = new GridBagConstraints();
		gridBagConstraints_11.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_11.gridwidth = 5;
		gridBagConstraints_11.gridy = 3;
		gridBagConstraints_11.gridx = 1;
		panel_1.add(scrollPane, gridBagConstraints_11);

		contentTextArea = new JTextArea();
		contentTextArea.setPreferredSize(new Dimension(280, 120));
		contentTextArea.setLineWrap(true);
		scrollPane.setViewportView(contentTextArea);

		final JLabel moneyLabel = new JLabel();
		moneyLabel.setText("金    额：");
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_6.gridy = 4;
		gridBagConstraints_6.gridx = 0;
		panel_1.add(moneyLabel, gridBagConstraints_6);

		moneyTextField = new JTextField();
		moneyTextField.setColumns(5);
		moneyTextField.setHorizontalAlignment(SwingConstants.CENTER);
		moneyTextField.setText("0.00");
		final GridBagConstraints gridBagConstraints_12 = new GridBagConstraints();
		gridBagConstraints_12.gridwidth = 2;
		gridBagConstraints_12.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_12.ipadx = 65;
		gridBagConstraints_12.gridy = 4;
		gridBagConstraints_12.gridx = 1;
		panel_1.add(moneyTextField, gridBagConstraints_12);

		final JLabel startDateLabel = new JLabel();
		startDateLabel.setText("开始日期：");
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_7.gridy = 5;
		gridBagConstraints_7.gridx = 0;
		panel_1.add(startDateLabel, gridBagConstraints_7);

		startDateTextField = new JTextField();
		startDateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		startDateTextField.setText(Today.TODAY_DATE);
		final GridBagConstraints gridBagConstraints_13 = new GridBagConstraints();
		gridBagConstraints_13.gridwidth = 2;
		gridBagConstraints_13.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_13.ipadx = 35;
		gridBagConstraints_13.gridy = 5;
		gridBagConstraints_13.gridx = 1;
		panel_1.add(startDateTextField, gridBagConstraints_13);

		final JLabel endDateLabel = new JLabel();
		endDateLabel.setText("结束日期：");
		final GridBagConstraints gridBagConstraints_14 = new GridBagConstraints();
		gridBagConstraints_14.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_14.gridy = 5;
		gridBagConstraints_14.gridx = 4;
		panel_1.add(endDateLabel, gridBagConstraints_14);

		endDateTextField = new JTextField();
		endDateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		endDateTextField.setText(Today.TODAY_DATE);
		final GridBagConstraints gridBagConstraints_15 = new GridBagConstraints();
		gridBagConstraints_15.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_15.ipadx = 35;
		gridBagConstraints_15.gridy = 5;
		gridBagConstraints_15.gridx = 5;
		panel_1.add(endDateTextField, gridBagConstraints_15);

		final JLabel ratifierLabel = new JLabel();
		ratifierLabel.setText("批准部门：");
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.gridy = 6;
		gridBagConstraints_8.gridx = 0;
		panel_1.add(ratifierLabel, gridBagConstraints_8);

		ratifierDeptTextField = new JTextField();
		ratifierDeptTextField.setEditable(false);
		ratifierDeptTextField.setColumns(11);
		final GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
		gridBagConstraints_9.gridy = 6;
		gridBagConstraints_9.gridx = 1;
		panel_1.add(ratifierDeptTextField, gridBagConstraints_9);

		final JButton ratifierDeptTreeButton = new JButton();
		ratifierDeptTreeButton.setMargin(new Insets(0, 6, 0, 3));
		ratifierDeptTreeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeptTreeDialog deptTree = new DeptTreeDialog(
						ratifierDeptTextField);
				deptTree.setBounds(465, 465, 102, 175);
				deptTree.setVisible(true);
				// 根据选择的部门初始化批准人列表
				TbDept dept = (TbDept) dao
						.queryDeptByName(ratifierDeptTextField.getText());
				if (ratifierPersonComboBox.isEnabled())
					ratifierPersonComboBox.removeAllItems();
				else
					ratifierPersonComboBox.setEnabled(true);
				ratifierPersonComboBox.addItem("请选择");
				Iterator dutyInfoIt = dept.getTbDutyInfos().iterator();
				while (dutyInfoIt.hasNext()) {
					TbDutyInfo dutyInfo = (TbDutyInfo) dutyInfoIt.next();
					TbRecord tbRecord = dutyInfo.getTbRecord();
					ratifierPersonComboBox.addItem(tbRecord.getRecordNumber()
							+ "    " + tbRecord.getName());
				}
			}
		});
		ratifierDeptTreeButton.setText("...");
		final GridBagConstraints gridBagConstraints_23 = new GridBagConstraints();
		gridBagConstraints_23.gridy = 6;
		gridBagConstraints_23.gridx = 2;
		panel_1.add(ratifierDeptTreeButton, gridBagConstraints_23);

		final JLabel ratifierPersonLabel = new JLabel();
		ratifierPersonLabel.setText("批 准 人：");
		final GridBagConstraints gridBagConstraints_16 = new GridBagConstraints();
		gridBagConstraints_16.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints_16.gridy = 6;
		gridBagConstraints_16.gridx = 4;
		panel_1.add(ratifierPersonLabel, gridBagConstraints_16);

		ratifierPersonComboBox = new JComboBox();
		ratifierPersonComboBox.setEnabled(false);
		final GridBagConstraints gridBagConstraints_17 = new GridBagConstraints();
		gridBagConstraints_17.anchor = GridBagConstraints.WEST;
		gridBagConstraints_17.gridy = 6;
		gridBagConstraints_17.gridx = 5;
		panel_1.add(ratifierPersonComboBox, gridBagConstraints_17);

		final JLabel ratifierDateLabel = new JLabel();
		ratifierDateLabel.setText("批准日期：");
		final GridBagConstraints gridBagConstraints_20 = new GridBagConstraints();
		gridBagConstraints_20.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_20.gridy = 7;
		gridBagConstraints_20.gridx = 0;
		panel_1.add(ratifierDateLabel, gridBagConstraints_20);

		ratifierDateTextField = new JTextField();
		ratifierDateTextField.setColumns(16);
		ratifierDateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		ratifierDateTextField
				.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		ratifierDateTextField.setText(Today.TODAY_DATE);
		final GridBagConstraints gridBagConstraints_21 = new GridBagConstraints();
		gridBagConstraints_21.gridwidth = 2;
		gridBagConstraints_21.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_21.gridy = 7;
		gridBagConstraints_21.gridx = 1;
		panel_1.add(ratifierDateTextField, gridBagConstraints_21);
		//
	}
}
