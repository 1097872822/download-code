package com.mwq.frame.explorer;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Field;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.mwq.dao.Dao;

public class PersonnelDialog extends JDialog {

	private ButtonGroup buttonGroup = new ButtonGroup();

	private JComboBox dayComboBox;

	private JComboBox monthComboBox;

	private JComboBox yearComboBox;

	private JComboBox typeComboBox;

	private JTextField emailTextField;

	private JTextField handsetTextField;

	private JTextField dutyTextField;

	private JTextField deptTextField;

	private JTextField companyTextField;

	private JTextField nameTextField;

	private JTextField numTextField;

	private final Dao dao = Dao.getInstance();

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			PersonnelDialog dialog = new PersonnelDialog("", "", -1);
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
	public PersonnelDialog(String title, String cardName, final int num) {
		super();
		setModal(true);
		getContentPane().setLayout(new GridBagLayout());
		setTitle(title);
		setBounds(100, 100, 500, 375);

		final JLabel numLabel = new JLabel();
		numLabel.setText("编    号：");
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		getContentPane().add(numLabel, gridBagConstraints);

		numTextField = new JTextField();
		numTextField.setHorizontalAlignment(SwingConstants.CENTER);
		numTextField.setEditable(false);
		numTextField.setColumns(13);
		numTextField
				.setText((num < 0 ? dao.sPersonnelIdOfMax() + 1 : num) + "");
		final GridBagConstraints gridBagConstraints_10 = new GridBagConstraints();
		gridBagConstraints_10.anchor = GridBagConstraints.WEST;
		gridBagConstraints_10.gridy = 0;
		gridBagConstraints_10.gridx = 1;
		getContentPane().add(numTextField, gridBagConstraints_10);

		final JLabel typeLabel = new JLabel();
		typeLabel.setText("类    别：");
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_1.gridy = 1;
		gridBagConstraints_1.gridx = 0;
		getContentPane().add(typeLabel, gridBagConstraints_1);

		typeComboBox = new JComboBox();
		Vector cardV = dao.sTypeByUsed("card");
		for (int i = 0; i < cardV.size(); i++) {
			Vector card = (Vector) cardV.get(i);
			typeComboBox.addItem(card.get(2));
		}
		typeComboBox.setSelectedItem(cardName);
		final GridBagConstraints gridBagConstraints_17 = new GridBagConstraints();
		gridBagConstraints_17.anchor = GridBagConstraints.WEST;
		gridBagConstraints_17.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_17.gridy = 1;
		gridBagConstraints_17.gridx = 1;
		getContentPane().add(typeComboBox, gridBagConstraints_17);

		final JLabel nameLabel = new JLabel();
		nameLabel.setText("姓    名：");
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_2.gridy = 2;
		gridBagConstraints_2.gridx = 0;
		getContentPane().add(nameLabel, gridBagConstraints_2);

		nameTextField = new JTextField();
		nameTextField.setName("姓名");
		nameTextField.setColumns(13);
		final GridBagConstraints gridBagConstraints_11 = new GridBagConstraints();
		gridBagConstraints_11.anchor = GridBagConstraints.WEST;
		gridBagConstraints_11.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_11.gridy = 2;
		gridBagConstraints_11.gridx = 1;
		getContentPane().add(nameTextField, gridBagConstraints_11);

		final JLabel sexLabel = new JLabel();
		sexLabel.setText("性    别：");
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_3.gridy = 3;
		gridBagConstraints_3.gridx = 0;
		getContentPane().add(sexLabel, gridBagConstraints_3);

		final JPanel sexPanel = new JPanel();
		final FlowLayout flowLayout_1 = new FlowLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		sexPanel.setLayout(flowLayout_1);
		final GridBagConstraints gridBagConstraints_18 = new GridBagConstraints();
		gridBagConstraints_18.anchor = GridBagConstraints.WEST;
		gridBagConstraints_18.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_18.gridy = 3;
		gridBagConstraints_18.gridx = 1;
		getContentPane().add(sexPanel, gridBagConstraints_18);

		final JRadioButton manRadioButton = new JRadioButton();
		buttonGroup.add(manRadioButton);
		manRadioButton.setText("男  ");
		sexPanel.add(manRadioButton);

		final JRadioButton womanRadioButton = new JRadioButton();
		buttonGroup.add(womanRadioButton);
		womanRadioButton.setText("女");
		sexPanel.add(womanRadioButton);

		final JLabel birthdayLabel = new JLabel();
		birthdayLabel.setText("出生日期：");
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_4.gridy = 4;
		gridBagConstraints_4.gridx = 0;
		getContentPane().add(birthdayLabel, gridBagConstraints_4);

		final JPanel birthdayPanel = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		birthdayPanel.setLayout(flowLayout);
		final GridBagConstraints gridBagConstraints_19 = new GridBagConstraints();
		gridBagConstraints_19.anchor = GridBagConstraints.WEST;
		gridBagConstraints_19.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_19.gridy = 4;
		gridBagConstraints_19.gridx = 1;
		getContentPane().add(birthdayPanel, gridBagConstraints_19);

		yearComboBox = new JComboBox();
		yearComboBox.addItem("请选择");
		for (int year = 1900; year <= 2020; year++) {
			yearComboBox.addItem(year);
		}
		birthdayPanel.add(yearComboBox);

		final JLabel yearLabel = new JLabel();
		yearLabel.setText("年");
		birthdayPanel.add(yearLabel);

		monthComboBox = new JComboBox();
		monthComboBox.addItem("请选择");
		for (int month = 1; month <= 12; month++) {
			monthComboBox.addItem(month);
		}
		birthdayPanel.add(monthComboBox);

		final JLabel monthLabel = new JLabel();
		monthLabel.setText("月");
		birthdayPanel.add(monthLabel);

		dayComboBox = new JComboBox();
		dayComboBox.addItem("请选择");
		for (int day = 1; day <= 31; day++) {
			dayComboBox.addItem(day);
		}
		birthdayPanel.add(dayComboBox);

		final JLabel dayLabel = new JLabel();
		dayLabel.setText("日");
		birthdayPanel.add(dayLabel);

		final JLabel companyLabel = new JLabel();
		companyLabel.setText("公    司：");
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_5.gridy = 5;
		gridBagConstraints_5.gridx = 0;
		getContentPane().add(companyLabel, gridBagConstraints_5);

		companyTextField = new JTextField();
		companyTextField.setName("公司");
		companyTextField.setColumns(60);
		final GridBagConstraints gridBagConstraints_12 = new GridBagConstraints();
		gridBagConstraints_12.anchor = GridBagConstraints.WEST;
		gridBagConstraints_12.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_12.gridy = 5;
		gridBagConstraints_12.gridx = 1;
		getContentPane().add(companyTextField, gridBagConstraints_12);

		final JLabel deptLabel = new JLabel();
		deptLabel.setText("部    门：");
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_6.gridy = 6;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(deptLabel, gridBagConstraints_6);

		deptTextField = new JTextField();
		deptTextField.setName("部门");
		deptTextField.setColumns(60);
		final GridBagConstraints gridBagConstraints_13 = new GridBagConstraints();
		gridBagConstraints_13.anchor = GridBagConstraints.WEST;
		gridBagConstraints_13.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_13.gridy = 6;
		gridBagConstraints_13.gridx = 1;
		getContentPane().add(deptTextField, gridBagConstraints_13);

		final JLabel dutyLabel = new JLabel();
		dutyLabel.setText("职    务：");
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_7.gridy = 7;
		gridBagConstraints_7.gridx = 0;
		getContentPane().add(dutyLabel, gridBagConstraints_7);

		dutyTextField = new JTextField();
		dutyTextField.setName("职务");
		dutyTextField.setColumns(60);
		final GridBagConstraints gridBagConstraints_14 = new GridBagConstraints();
		gridBagConstraints_14.anchor = GridBagConstraints.WEST;
		gridBagConstraints_14.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_14.gridy = 7;
		gridBagConstraints_14.gridx = 1;
		getContentPane().add(dutyTextField, gridBagConstraints_14);

		final JLabel handsetLabel = new JLabel();
		handsetLabel.setText("移动电话：");
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_8.gridy = 8;
		gridBagConstraints_8.gridx = 0;
		getContentPane().add(handsetLabel, gridBagConstraints_8);

		handsetTextField = new JTextField();
		handsetTextField.setName("移动电话");
		handsetTextField.setColumns(60);
		final GridBagConstraints gridBagConstraints_15 = new GridBagConstraints();
		gridBagConstraints_15.anchor = GridBagConstraints.WEST;
		gridBagConstraints_15.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_15.gridy = 8;
		gridBagConstraints_15.gridx = 1;
		getContentPane().add(handsetTextField, gridBagConstraints_15);

		final JLabel emailLabel = new JLabel();
		emailLabel.setText(" E-mail ：");
		final GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
		gridBagConstraints_9.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_9.gridy = 9;
		gridBagConstraints_9.gridx = 0;
		getContentPane().add(emailLabel, gridBagConstraints_9);

		emailTextField = new JTextField();
		emailTextField.setName("E-mail");
		emailTextField.setColumns(60);
		final GridBagConstraints gridBagConstraints_16 = new GridBagConstraints();
		gridBagConstraints_16.anchor = GridBagConstraints.WEST;
		gridBagConstraints_16.insets = new Insets(5, 0, 0, 0);
		gridBagConstraints_16.gridy = 9;
		gridBagConstraints_16.gridx = 1;
		getContentPane().add(emailTextField, gridBagConstraints_16);

		final JPanel panel_2 = new JPanel();
		final FlowLayout flowLayout_2 = new FlowLayout();
		flowLayout_2.setAlignment(FlowLayout.RIGHT);
		flowLayout_2.setVgap(0);
		panel_2.setLayout(flowLayout_2);
		final GridBagConstraints gridBagConstraints_20 = new GridBagConstraints();
		gridBagConstraints_20.anchor = GridBagConstraints.EAST;
		gridBagConstraints_20.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_20.gridy = 10;
		gridBagConstraints_20.gridx = 1;
		getContentPane().add(panel_2, gridBagConstraints_20);

		final JButton submitButton = new JButton();
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 通过反射验证文本框
				Field[] fields = PersonnelDialog.class.getDeclaredFields();
				for (int i = fields.length - 1; i >= 0; i--) {
					Field field = fields[i];
					if (field.getType().equals(JTextField.class)) {
						field.setAccessible(true);
						JTextField textField = null;
						try {
							textField = (JTextField) field
									.get(PersonnelDialog.this);
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						if (textField.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "请填写“"
									+ textField.getName() + "”！", "友情提示",
									JOptionPane.INFORMATION_MESSAGE);
							textField.requestFocus();
							return;
						}
					}
				}
				// 验证性别
				if (!manRadioButton.isSelected()
						&& !womanRadioButton.isSelected()) {
					JOptionPane.showMessageDialog(null, "请填写“性别”！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// 验证出生日期
				boolean notSelectedYear = yearComboBox.getSelectedIndex() == 0;
				boolean notSelectedMonth = monthComboBox.getSelectedIndex() == 0;
				boolean notSelectedDay = dayComboBox.getSelectedIndex() == 0;
				if (notSelectedYear || notSelectedMonth || notSelectedDay) {
					JOptionPane.showMessageDialog(null, "请填写“出生日期”！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				// 获取信息
				Vector personnelV = new Vector();
				personnelV.add(numTextField.getText());
				personnelV.add(typeComboBox.getSelectedItem());
				personnelV.add(nameTextField.getText());
				personnelV.add((manRadioButton.isSelected() ? "男" : "女"));
				personnelV.add(yearComboBox.getSelectedItem() + "-"
						+ monthComboBox.getSelectedItem() + "-"
						+ dayComboBox.getSelectedItem());
				personnelV.add(companyTextField.getText());
				personnelV.add(deptTextField.getText());
				personnelV.add(dutyTextField.getText());
				personnelV.add(handsetTextField.getText());
				personnelV.add(emailTextField.getText());
				if (num < 0)
					dao.iPersonnel(personnelV);
				else
					dao.uPersonnel(personnelV);
				dispose();
			}
		});
		submitButton.setText("确定");
		panel_2.add(submitButton);

		final JButton exitButton = new JButton();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		exitButton.setText("退出");
		panel_2.add(exitButton);
		//
		if (num > 0) {
			Vector personnelV = dao.sPersonnelVByNum(num);
			typeComboBox.setSelectedItem(personnelV.get(1));
			nameTextField.setText(personnelV.get(2).toString());
			String sex = personnelV.get(3).toString().trim();
			if (sex.equals("男"))
				manRadioButton.setSelected(true);
			else
				womanRadioButton.setSelected(true);
			String birthday = personnelV.get(4).toString();
			int year = Integer.valueOf(birthday.substring(0, 4));
			int month = Integer.valueOf(birthday.substring(5, 7));
			int day = Integer.valueOf(birthday.substring(8, 10));
			yearComboBox.setSelectedItem(year);
			monthComboBox.setSelectedItem(month);
			dayComboBox.setSelectedItem(day);
			companyTextField.setText(personnelV.get(5).toString());
			deptTextField.setText(personnelV.get(6).toString());
			dutyTextField.setText(personnelV.get(7).toString());
			handsetTextField.setText(personnelV.get(8).toString());
			emailTextField.setText(personnelV.get(9).toString());
		}
	}

}
