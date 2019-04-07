package com.mwq.frame.personnel;

import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mwq.frame.common.DeptTreeDialog;
import com.mwq.hibernate.Dao;
import com.mwq.hibernate.HibernateSessionFactory;
import com.mwq.hibernate.mapping.TbAccountItem;
import com.mwq.hibernate.mapping.TbDept;
import com.mwq.hibernate.mapping.TbDutyInfo;
import com.mwq.hibernate.mapping.TbRecord;
import com.mwq.hibernate.mapping.TbTimecard;
import com.mwq.tool.Today;

public class TimecardPanel extends JPanel {

	private JTextField inDeptTextField;

	private JTextField deptTextField;

	private JTextArea explainTextArea;

	private JTextField endDateTextField;

	private JTextField startDateTextField;

	private JTextField ratifierDateTextField;

	private JComboBox ratifierPersonComboBox;

	private JComboBox timecardTypeComboBox;

	private JComboBox personnalComboBox;

	private Dao dao = Dao.getInstance();

	/**
	 * Create the panel
	 */
	public TimecardPanel() {
		super();
		setLayout(new BorderLayout());

		final JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		add(buttonPanel, BorderLayout.NORTH);

		final JButton createButton = new JButton();
		createButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inDeptTextField.setText(null);
				personnalComboBox.removeAllItems();
				personnalComboBox.addItem("请选择");
				Iterator recordsIt = dao.queryRecord().iterator();
				while (recordsIt.hasNext()) {
					TbRecord record = (TbRecord) recordsIt.next();
					personnalComboBox.addItem(record.getRecordNumber() + "    "
							+ record.getName());
				}
				HibernateSessionFactory.closeSession();
				timecardTypeComboBox.setSelectedIndex(0);
				explainTextArea.setText("");
				startDateTextField.setText(Today.TODAY_DATE);
				endDateTextField.setText(Today.TODAY_DATE);
				deptTextField.setText("");
				ratifierPersonComboBox.removeAllItems();
				ratifierPersonComboBox.setEnabled(false);
				ratifierDateTextField.setText(Today.TODAY_DATE);
			}
		});
		createButton.setText("新建");
		buttonPanel.add(createButton);

		final JButton saveButton = new JButton();
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TbTimecard timecard = new TbTimecard();
				String personnal = personnalComboBox.getSelectedItem()
						.toString();
				if (personnal.equals("请选择")) {
					JOptionPane.showMessageDialog(null, "请选择欲考勤的职员！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				TbRecord record = (TbRecord) dao.queryRecordByNum(personnal
						.substring(0, 6));
				timecard.setTbRecordByRecordId(record);
				String timecardType = timecardTypeComboBox.getSelectedItem()
						.toString();
				if (timecardType.equals("请选择")) {
					JOptionPane.showMessageDialog(null, "请选择考勤类型！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				TbAccountItem accountItem = (TbAccountItem) dao
						.queryAccountItemByName(timecardType);
				timecard.setTbAccountItem(accountItem);
				if (explainTextArea.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请填写考勤说明！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				timecard.setExplain(explainTextArea.getText());
				try {
					timecard.setStartDate(Date.valueOf(startDateTextField
							.getText()));
					timecard.setEndDate(Date
							.valueOf(endDateTextField.getText()));
				} catch (RuntimeException e) {
					JOptionPane.showMessageDialog(null, "开始日期或结束日期填写错误，请重新填写！",
							"友情提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				if (deptTextField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请选择批准部门！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				TbDept dept = (TbDept) dao.queryDeptByName(deptTextField
						.getText());
				timecard.setTbDept(dept);
				String ratifierPerson = ratifierPersonComboBox
						.getSelectedItem().toString();
				if (ratifierPerson.equals("请选择")) {
					JOptionPane.showMessageDialog(null, "请选择批准人！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				TbRecord ratifierRecord = (TbRecord) dao
						.queryRecordByNum(ratifierPerson.substring(0, 6));
				timecard.setTbRecordByRatifierRecordId(ratifierRecord);
				try {
					timecard.setRatifierDate(Date.valueOf(ratifierDateTextField
							.getText()));
				} catch (RuntimeException e) {
					JOptionPane.showMessageDialog(null, "批准日期填写错误，请重新填写！",
							"友情提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				// 持久化考勤信息
				dao.saveObject(timecard);
				HibernateSessionFactory.closeSession();

				//
				JOptionPane.showMessageDialog(null, "已经成功保存此次考勤信息！", "友情提示",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		saveButton.setText("保存");
		buttonPanel.add(saveButton);

		final JPanel contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(new GridBagLayout());
		add(contentPanel, BorderLayout.CENTER);

		final JLabel inDeptLabel = new JLabel();
		inDeptLabel.setText("所在部门：");
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		contentPanel.add(inDeptLabel, gridBagConstraints);

		inDeptTextField = new JTextField();
		inDeptTextField.setEditable(false);
		inDeptTextField.setColumns(11);
		final GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
		gridBagConstraints_9.gridy = 0;
		gridBagConstraints_9.gridx = 1;
		contentPanel.add(inDeptTextField, gridBagConstraints_9);

		final JButton inDeptTreeButton = new JButton();// 创建按钮对象
		inDeptTreeButton.setMargin(new Insets(0, 6, 0, 3));
		inDeptTreeButton.addActionListener(new ActionListener() {// 捕获按钮事件
					public void actionPerformed(ActionEvent e) {
						DeptTreeDialog deptTree = new DeptTreeDialog(
								inDeptTextField);// 创建部门选取对话框
						deptTree.setBounds(375, 317, 101, 175);// 设置部门选取对话框的显示位置
						deptTree.setVisible(true);// 弹出部门选取对话框
						TbDept dept = (TbDept) dao
								.queryDeptByName(inDeptTextField.getText());// 检索选中的部门对象
						personnalComboBox.removeAllItems();// 清空“考勤员工”下拉菜单中的所有选项
						personnalComboBox.addItem("请选择"); // 添加提示项
						// 通过Hibernate的一对多关联获得与该部门关联的职务信息对象
						Iterator dutyInfoIt = dept.getTbDutyInfos().iterator();
						while (dutyInfoIt.hasNext()) {// 遍历职务信息对象
							TbDutyInfo dutyInfo = (TbDutyInfo) dutyInfoIt
									.next();// 获得职务信息对象
							TbRecord tbRecord = dutyInfo.getTbRecord();// 通过Hibernate的一对一关联获得与职务信息对象关联的档案信息对象
							personnalComboBox.addItem(tbRecord
									.getRecordNumber()
									+ "    " + tbRecord.getName());// 将该员工添加到“考勤员工”下拉菜单中
						}
					}
				});
		inDeptTreeButton.setText("...");
		final GridBagConstraints gridBagConstraints_21 = new GridBagConstraints();
		gridBagConstraints_21.gridy = 0;
		gridBagConstraints_21.gridx = 2;
		contentPanel.add(inDeptTreeButton, gridBagConstraints_21);

		final JLabel label = new JLabel();
		label.setPreferredSize(new Dimension(100, 20));
		final GridBagConstraints gridBagConstraints_19 = new GridBagConstraints();
		gridBagConstraints_19.gridy = 0;
		gridBagConstraints_19.gridx = 3;
		contentPanel.add(label, gridBagConstraints_19);

		final JLabel nameLabel = new JLabel();
		nameLabel.setText("考勤员工：");
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints_1.gridy = 0;
		gridBagConstraints_1.gridx = 4;
		contentPanel.add(nameLabel, gridBagConstraints_1);

		personnalComboBox = new JComboBox(); // 创建下拉菜单对象
		personnalComboBox.addItemListener(new ItemListener() { // 捕获下拉菜单选项状态发生改变的事件
					public void itemStateChanged(ItemEvent e) {
						if (e.getStateChange() == ItemEvent.SELECTED) { // 查看是否由选中当前项触发的
							String selectedItem = (String) e.getItem();// 获得选中项的内容
							if (selectedItem.equals("请选择")) {// 当选中项为“请选择”时，设置部门文本框为空
								inDeptTextField.setText(null);
							} else {// 否则设置部门文本框为选中员工所在的部门
								TbRecord record = (TbRecord) dao
										.queryRecordByNum(selectedItem
												.substring(0, 6));
								inDeptTextField.setText(record.getTbDutyInfo()
										.getTbDept().getName());
							}
						}
					}
				});
		personnalComboBox.addItem("请选择"); // 添加提示项
		Iterator recordIt = dao.queryRecord().iterator();// 检索所有员工
		while (recordIt.hasNext()) {// 通过循环添加到下拉菜单中
			TbRecord record = (TbRecord) recordIt.next();
			personnalComboBox.addItem(record.getRecordNumber() + "    "
					+ record.getName());
		}
		final GridBagConstraints gridBagConstraints_10 = new GridBagConstraints();
		gridBagConstraints_10.anchor = GridBagConstraints.WEST;
		gridBagConstraints_10.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints_10.gridy = 0;
		gridBagConstraints_10.gridx = 5;
		contentPanel.add(personnalComboBox, gridBagConstraints_10);

		final JLabel label_9 = new JLabel();
		final GridBagConstraints gridBagConstraints_18 = new GridBagConstraints();
		gridBagConstraints_18.gridy = 0;
		gridBagConstraints_18.gridx = 6;
		contentPanel.add(label_9, gridBagConstraints_18);

		final JLabel timecardTypeLabel = new JLabel();
		timecardTypeLabel.setText("考勤类型：");
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints_2.gridy = 1;
		gridBagConstraints_2.gridx = 0;
		contentPanel.add(timecardTypeLabel, gridBagConstraints_2);

		timecardTypeComboBox = new JComboBox();
		timecardTypeComboBox.addItem("请选择");
		Iterator accountItemIt = dao.queryAccountItemUsedTimecard().iterator();
		while (accountItemIt.hasNext()) {
			TbAccountItem accountItem = (TbAccountItem) accountItemIt.next();
			timecardTypeComboBox.addItem(accountItem.getName());
		}
		final GridBagConstraints gridBagConstraints_11 = new GridBagConstraints();
		gridBagConstraints_11.anchor = GridBagConstraints.WEST;
		gridBagConstraints_11.gridwidth = 2;
		gridBagConstraints_11.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints_11.gridy = 1;
		gridBagConstraints_11.gridx = 1;
		contentPanel.add(timecardTypeComboBox, gridBagConstraints_11);

		final JLabel explainLabel = new JLabel();
		explainLabel.setText("考勤说明：");
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.insets = new Insets(20, 0, 104, 0);
		gridBagConstraints_3.gridy = 2;
		gridBagConstraints_3.gridx = 0;
		contentPanel.add(explainLabel, gridBagConstraints_3);

		final JScrollPane scrollPane = new JScrollPane();
		final GridBagConstraints gridBagConstraints_17 = new GridBagConstraints();
		gridBagConstraints_17.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints_17.gridwidth = 6;
		gridBagConstraints_17.gridy = 2;
		gridBagConstraints_17.gridx = 1;
		contentPanel.add(scrollPane, gridBagConstraints_17);

		explainTextArea = new JTextArea();
		explainTextArea.setLineWrap(true);
		explainTextArea.setRows(6);
		explainTextArea.setColumns(70);
		scrollPane.setPreferredSize(explainTextArea.getPreferredSize());
		scrollPane.setViewportView(explainTextArea);

		final JLabel startDateLabel = new JLabel();
		startDateLabel.setText("开始日期：");
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints_4.gridy = 3;
		gridBagConstraints_4.gridx = 0;
		contentPanel.add(startDateLabel, gridBagConstraints_4);

		startDateTextField = new JTextField();
		startDateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		startDateTextField.setColumns(16);
		startDateTextField.setText(Today.TODAY_DATE);
		final GridBagConstraints gridBagConstraints_15 = new GridBagConstraints();
		gridBagConstraints_15.gridwidth = 2;
		gridBagConstraints_15.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints_15.gridy = 3;
		gridBagConstraints_15.gridx = 1;
		contentPanel.add(startDateTextField, gridBagConstraints_15);

		final JLabel endDateLabel = new JLabel();
		endDateLabel.setText("结束日期：");
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints_5.gridy = 3;
		gridBagConstraints_5.gridx = 4;
		contentPanel.add(endDateLabel, gridBagConstraints_5);

		endDateTextField = new JTextField();
		endDateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		endDateTextField.setColumns(16);
		endDateTextField.setText(Today.TODAY_DATE);
		final GridBagConstraints gridBagConstraints_16 = new GridBagConstraints();
		gridBagConstraints_16.anchor = GridBagConstraints.WEST;
		gridBagConstraints_16.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints_16.gridy = 3;
		gridBagConstraints_16.gridx = 5;
		contentPanel.add(endDateTextField, gridBagConstraints_16);

		final JLabel ratifierDeptLabel = new JLabel();
		ratifierDeptLabel.setText("批准部门：");
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints_6.gridy = 4;
		gridBagConstraints_6.gridx = 0;
		contentPanel.add(ratifierDeptLabel, gridBagConstraints_6);

		deptTextField = new JTextField();
		deptTextField.setEditable(false);
		deptTextField.setColumns(11);
		final GridBagConstraints gridBagConstraints_12 = new GridBagConstraints();
		gridBagConstraints_12.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints_12.gridy = 4;
		gridBagConstraints_12.gridx = 1;
		contentPanel.add(deptTextField, gridBagConstraints_12);

		final JButton deptTreeButton = new JButton();
		deptTreeButton.setMargin(new Insets(0, 6, 0, 3));
		deptTreeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeptTreeDialog deptTree = new DeptTreeDialog(deptTextField);
				deptTree.setBounds(375, 393, 101, 175);
				deptTree.setVisible(true);
				// 根据选择的部门初始化批准人列表
				TbDept dept = (TbDept) dao.queryDeptByName(deptTextField
						.getText());
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
		deptTreeButton.setText("...");
		final GridBagConstraints gridBagConstraints_20 = new GridBagConstraints();
		gridBagConstraints_20.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints_20.gridy = 4;
		gridBagConstraints_20.gridx = 2;
		contentPanel.add(deptTreeButton, gridBagConstraints_20);

		final JLabel ratifierPersonLabel = new JLabel();
		ratifierPersonLabel.setText("批 准 人：");
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints_7.gridy = 4;
		gridBagConstraints_7.gridx = 4;
		contentPanel.add(ratifierPersonLabel, gridBagConstraints_7);

		ratifierPersonComboBox = new JComboBox();
		ratifierPersonComboBox.setEnabled(false);
		final GridBagConstraints gridBagConstraints_13 = new GridBagConstraints();
		gridBagConstraints_13.anchor = GridBagConstraints.WEST;
		gridBagConstraints_13.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints_13.gridy = 4;
		gridBagConstraints_13.gridx = 5;
		contentPanel.add(ratifierPersonComboBox, gridBagConstraints_13);

		final JLabel ratifierDateLabel = new JLabel();
		ratifierDateLabel.setText("批准日期：");
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints_8.gridy = 5;
		gridBagConstraints_8.gridx = 0;
		contentPanel.add(ratifierDateLabel, gridBagConstraints_8);

		ratifierDateTextField = new JTextField();
		ratifierDateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		ratifierDateTextField.setText(Today.TODAY_DATE);
		ratifierDateTextField.setColumns(16);
		final GridBagConstraints gridBagConstraints_14 = new GridBagConstraints();
		gridBagConstraints_14.gridwidth = 2;
		gridBagConstraints_14.insets = new Insets(20, 0, 0, 0);
		gridBagConstraints_14.gridy = 5;
		gridBagConstraints_14.gridx = 1;
		contentPanel.add(ratifierDateTextField, gridBagConstraints_14);
		//
	}
}
