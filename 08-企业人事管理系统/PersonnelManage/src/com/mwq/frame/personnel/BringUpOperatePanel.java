package com.mwq.frame.personnel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mwq.frame.common.DeptAndPersonnelDialog;
import com.mwq.hibernate.Dao;
import com.mwq.hibernate.HibernateSessionFactory;
import com.mwq.hibernate.mapping.TbBringUpContent;
import com.mwq.hibernate.mapping.TbBringUpOntent;
import com.mwq.hibernate.mapping.TbDutyInfo;
import com.mwq.hibernate.mapping.TbRecord;
import com.mwq.mwing.MTable;
import com.mwq.tool.Today;

public class BringUpOperatePanel extends JPanel {

	private MTable table;

	private JTextField lecuterTextField;

	private JTextField objectTextField;

	private JTextField unitTextField;

	private JTextField endDateTextField;

	private JTextField addressTextField;

	private JTextField startDateTextField;

	private JTextField contentTextField;

	private JTextField nameTextField;

	private final Vector<String> columnNameV = new Vector<String>();

	private final Vector<Vector<String>> cellV = new Vector<Vector<String>>();

	private final DefaultTableModel tableModel = new DefaultTableModel();

	/**
	 * Create the panel
	 */
	public BringUpOperatePanel(final JPanel rightPanel, String bucId) {
		super();
		setLayout(new BorderLayout());

		final JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		add(buttonPanel, BorderLayout.NORTH);

		final JButton exitButton = new JButton();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rightPanel.removeAll();
				rightPanel.add(new BringUpSelectedPanel(rightPanel),
						BorderLayout.CENTER);
				SwingUtilities.updateComponentTreeUI(rightPanel);
			}
		});
		exitButton.setText("�˳�");
		buttonPanel.add(exitButton);

		final JButton addButton = new JButton();
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeptAndPersonnelDialog dpDialog = new DeptAndPersonnelDialog();
				Dimension screenSize = Toolkit.getDefaultToolkit()
						.getScreenSize();
				int w = 550;
				int h = 400;
				int x = (screenSize.width - w) / 2;
				int y = (screenSize.height - h) / 2;
				dpDialog.setBounds(x, y, w, h);
				dpDialog.setVisible(true);
				Vector<Vector<String>> selectedRecordV = dpDialog
						.getSelectedRecordV();
				if (cellV.size() == 0) {
					for (int i = 0; i < selectedRecordV.size(); i++) {
						Vector<String> newRecordV = selectedRecordV.get(i);
						newRecordV.set(0, i + 1 + "");
						cellV.add(newRecordV);
					}
				} else {
					int k = cellV.size();
					for (int i = 0; i < selectedRecordV.size(); i++) {
						Vector<String> newRecordV = selectedRecordV.get(i);
						boolean add = true;
						for (int j = 0; j < cellV.size(); j++) {
							Vector<String> oldRecordV = cellV.get(j);
							if (newRecordV.get(1).equals(oldRecordV.get(1))) {
								add = false;
								break;
							}
						}
						if (add) {
							newRecordV.set(0, ++k + "");
							cellV.add(newRecordV);
						}
					}
				}
				tableModel.setDataVector(cellV, columnNameV);
				dpDialog.dispose();
			}
		});
		addButton.setText("��Ӳ�ѵ��Ա");
		buttonPanel.add(addButton);

		final JButton deleteButton = new JButton();
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = table.getSelectedRows();
				if (selectedRows.length == 0) {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫȡ���μӴ˴���ѵ�ʸ��Ա����",
							"������ʾ", JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					for (int i = selectedRows.length - 1; i >= 0; i--) {
						cellV.remove(selectedRows[i]);
					}
					for (int i = 0; i < cellV.size(); i++) {
						cellV.get(i).set(0, i + 1 + "");
					}
					tableModel.setDataVector(cellV, columnNameV);
				}
			}
		});
		deleteButton.setText("ȡ����ѵ�ʸ�");
		buttonPanel.add(deleteButton);

		final JButton saveButton = new JButton();
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ͨ��Java���������֤��ѵ��Ϣ�Ƿ�Ϊ�գ�������Ϣ��������Ϊ�գ�
				Field[] fields = BringUpOperatePanel.class.getDeclaredFields();// ͨ��Java������ƻ�����е���������
				for (int i = 0; i < fields.length; i++) {// ������������
					Field field = fields[i];// �������
					if (field.getType().equals(JTextField.class)) {// ֻ��֤JTextField���͵�����
						field.setAccessible(true);// Ĭ������²��������˽�����ԣ������Ϊtrue���������
						JTextField textField = null;
						try {
							textField = (JTextField) field
									.get(BringUpOperatePanel.this);// ��ñ����еĶ�Ӧ����
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (textField.getText().equals("")) {// �鿴�������Ƿ�Ϊ��
							String infos[] = { "�뽫��ѵ��Ϣ��д������", "������Ϣ��������Ϊ�գ�" };
							JOptionPane.showMessageDialog(null, infos, "������ʾ",// ������ʾ��Ϣ
									JOptionPane.INFORMATION_MESSAGE);
							textField.requestFocus();// ��Ϊ�յ��ı����ý���
							return;
						}
					}
				}
				// ��֤����
				String dateFormat = "20[0-9]{2}-[0-1]{1}[0-9]{1}-[0-3]{1}[0-9]{1} [0-2]{1}[0-9]{1}:[0-5]{1}[0-9]{1}";
				Pattern pattern = Pattern.compile(dateFormat);
				Matcher startDateMatch = pattern.matcher(startDateTextField
						.getText());
				Matcher endDateMatch = pattern.matcher(endDateTextField
						.getText());
				if (!startDateMatch.matches() || !endDateMatch.matches()) {
					JOptionPane.showMessageDialog(null, "��ѵ��������������������룡",
							"������ʾ", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				// ��֤�Ƿ�����˲�ѵ��Ա
				if (cellV.size() == 0) {
					JOptionPane.showMessageDialog(null, "�����ò�ѵ��Ա��", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				// ��ѵ����
				TbBringUpContent bringUpContent = new TbBringUpContent();
				bringUpContent.setName(nameTextField.getText());
				bringUpContent.setContent(contentTextField.getText());
				bringUpContent.setObject(objectTextField.getText());
				//
				DateFormat df = DateFormat.getDateInstance();
				try {
					bringUpContent.setStartDate(df.parse(startDateTextField
							.getText()));
					bringUpContent.setEndDate(df.parse(endDateTextField
							.getText()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				//
				bringUpContent.setUnit(unitTextField.getText());
				bringUpContent.setLecturer(lecuterTextField.getText());
				bringUpContent.setPlace(addressTextField.getText());

				// ��ѵ��Ա
				int column = 0;
				for (int i = 0; i < table.getColumnCount(); i++) {
					if (table.getColumnName(i).equals("�������")) {
						column = i;
						break;
					}
				}
				Dao dao = Dao.getInstance();
				for (int i = 0; i < cellV.size(); i++) {
					TbBringUpOntent bringUpOntent = new TbBringUpOntent();
					bringUpOntent.setTbBringUpContent(bringUpContent);
					bringUpContent.getTbBringUpOntents().add(bringUpOntent);
					Vector<String> recordV = cellV.get(i);
					TbRecord record = (TbRecord) dao.queryRecordByNum(recordV
							.get(column));
					record.getTbBringUpOntents().add(bringUpOntent);
					bringUpOntent.setTbRecord(record);
				}
				dao.saveObject(bringUpContent);
				HibernateSessionFactory.closeSession();

				//
				JOptionPane.showMessageDialog(null, "�Ѿ��ɹ�������ѵ��Ϣ��", "������ʾ",
						JOptionPane.INFORMATION_MESSAGE);
			}
		});
		saveButton.setText("����");
		buttonPanel.add(saveButton);

		final JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(new GridBagLayout());
		add(contentPanel, BorderLayout.CENTER);

		final JLabel nameLabel = new JLabel();
		nameLabel.setText("��ѵ���ƣ�");
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		contentPanel.add(nameLabel, gridBagConstraints);

		nameTextField = new JTextField();
		nameTextField.setPreferredSize(new Dimension(310, 20));
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.gridwidth = 3;
		gridBagConstraints_5.gridy = 0;
		gridBagConstraints_5.gridx = 1;
		contentPanel.add(nameTextField, gridBagConstraints_5);

		final JLabel objectLabel = new JLabel();
		objectLabel.setText("��ѵ����");
		final GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
		gridBagConstraints_9.insets = new Insets(0, 20, 0, 0);
		gridBagConstraints_9.gridy = 0;
		gridBagConstraints_9.gridx = 4;
		contentPanel.add(objectLabel, gridBagConstraints_9);

		objectTextField = new JTextField();
		objectTextField.setPreferredSize(new Dimension(310, 20));
		final GridBagConstraints gridBagConstraints_14 = new GridBagConstraints();
		gridBagConstraints_14.gridy = 0;
		gridBagConstraints_14.gridx = 5;
		contentPanel.add(objectTextField, gridBagConstraints_14);

		final JLabel contentLabel = new JLabel();
		contentLabel.setText("��ѵ���ݣ�");
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_1.gridy = 1;
		gridBagConstraints_1.gridx = 0;
		contentPanel.add(contentLabel, gridBagConstraints_1);

		contentTextField = new JTextField();
		contentTextField.setPreferredSize(new Dimension(310, 20));
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_6.gridwidth = 3;
		gridBagConstraints_6.gridy = 1;
		gridBagConstraints_6.gridx = 1;
		contentPanel.add(contentTextField, gridBagConstraints_6);

		final JLabel unitLabel = new JLabel();
		unitLabel.setText("��ѵ��λ��");
		final GridBagConstraints gridBagConstraints_10 = new GridBagConstraints();
		gridBagConstraints_10.insets = new Insets(10, 20, 0, 0);
		gridBagConstraints_10.gridy = 1;
		gridBagConstraints_10.gridx = 4;
		contentPanel.add(unitLabel, gridBagConstraints_10);

		unitTextField = new JTextField();
		unitTextField.setPreferredSize(new Dimension(310, 20));
		final GridBagConstraints gridBagConstraints_13 = new GridBagConstraints();
		gridBagConstraints_13.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_13.gridy = 1;
		gridBagConstraints_13.gridx = 5;
		contentPanel.add(unitTextField, gridBagConstraints_13);

		final JLabel dateLabel = new JLabel();
		dateLabel.setText("��ѵʱ�䣺");
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_2.gridy = 2;
		gridBagConstraints_2.gridx = 0;
		contentPanel.add(dateLabel, gridBagConstraints_2);

		String nowDateAndTime = new Today().getNowDateAndTime();

		startDateTextField = new JTextField();
		startDateTextField.setText(nowDateAndTime);
		startDateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		startDateTextField.setPreferredSize(new Dimension(143, 20));
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_7.gridy = 2;
		gridBagConstraints_7.gridx = 1;
		contentPanel.add(startDateTextField, gridBagConstraints_7);

		final JLabel label_7 = new JLabel();
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setText("����");
		final GridBagConstraints gridBagConstraints_11 = new GridBagConstraints();
		gridBagConstraints_11.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_11.gridy = 2;
		gridBagConstraints_11.gridx = 2;
		contentPanel.add(label_7, gridBagConstraints_11);

		endDateTextField = new JTextField();
		endDateTextField.setText(nowDateAndTime);
		endDateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		endDateTextField.setPreferredSize(new Dimension(143, 20));
		final GridBagConstraints gridBagConstraints_12 = new GridBagConstraints();
		gridBagConstraints_12.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_12.gridy = 2;
		gridBagConstraints_12.gridx = 3;
		contentPanel.add(endDateTextField, gridBagConstraints_12);

		final JLabel lecuterLabel = new JLabel();
		lecuterLabel.setText("��ѵ��ʦ��");
		final GridBagConstraints gridBagConstraints_15 = new GridBagConstraints();
		gridBagConstraints_15.insets = new Insets(10, 20, 0, 0);
		gridBagConstraints_15.gridy = 2;
		gridBagConstraints_15.gridx = 4;
		contentPanel.add(lecuterLabel, gridBagConstraints_15);

		lecuterTextField = new JTextField();
		lecuterTextField.setPreferredSize(new Dimension(310, 20));
		final GridBagConstraints gridBagConstraints_16 = new GridBagConstraints();
		gridBagConstraints_16.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_16.gridy = 2;
		gridBagConstraints_16.gridx = 5;
		contentPanel.add(lecuterTextField, gridBagConstraints_16);

		final JLabel addressLabel = new JLabel();
		addressLabel.setText("��ѵ�ص㣺");
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_3.gridy = 3;
		gridBagConstraints_3.gridx = 0;
		contentPanel.add(addressLabel, gridBagConstraints_3);

		addressTextField = new JTextField();
		addressTextField.setPreferredSize(new Dimension(700, 20));
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_8.gridwidth = 5;
		gridBagConstraints_8.gridy = 3;
		gridBagConstraints_8.gridx = 1;
		contentPanel.add(addressTextField, gridBagConstraints_8);

		final JLabel personListLabel = new JLabel();
		personListLabel.setText("��ѵ��Ա��");
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.insets = new Insets(10, 0, 380, 0);
		gridBagConstraints_4.gridy = 4;
		gridBagConstraints_4.gridx = 0;
		contentPanel.add(personListLabel, gridBagConstraints_4);

		final JScrollPane scrollPane = new JScrollPane();
		scrollPane.setPreferredSize(new Dimension(700, 400));
		final GridBagConstraints gridBagConstraints_17 = new GridBagConstraints();
		gridBagConstraints_17.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_17.gridwidth = 5;
		gridBagConstraints_17.gridy = 4;
		gridBagConstraints_17.gridx = 1;
		contentPanel.add(scrollPane, gridBagConstraints_17);

		columnNameV.add(" ��  �� ");
		columnNameV.add("�������");
		columnNameV.add(" ��  �� ");
		columnNameV.add(" ��  �� ");
		columnNameV.add(" ��  �� ");
		columnNameV.add(" ְ  �� ");
		tableModel.setDataVector(cellV, columnNameV);

		table = new MTable(tableModel);
		scrollPane.setViewportView(table);

		System.out.println(bucId);

		if (bucId != null) {
			addButton.setEnabled(false);
			deleteButton.setEnabled(false);
			saveButton.setEnabled(false);
			nameTextField.setEditable(false);
			objectTextField.setEditable(false);
			contentTextField.setEditable(false);
			unitTextField.setEditable(false);
			startDateTextField.setEditable(false);
			endDateTextField.setEditable(false);
			lecuterTextField.setEditable(false);
			addressTextField.setEditable(false);
			TbBringUpContent buc = (TbBringUpContent) Dao.getInstance()
					.queryBringUpContentById(bucId);
			nameTextField.setText(buc.getName());
			objectTextField.setText(buc.getObject());
			contentTextField.setText(buc.getContent());
			unitTextField.setText(buc.getUnit());
			startDateTextField.setText(buc.getStartDate().toString().substring(
					0, 16));
			endDateTextField.setText(buc.getEndDate().toString().substring(0,
					16));
			lecuterTextField.setText(buc.getLecturer());
			addressTextField.setText(buc.getPlace());
			Set bringUpOntents = buc.getTbBringUpOntents();
			int i = 1;
			for (Iterator iter = bringUpOntents.iterator(); iter.hasNext();) {
				TbBringUpOntent buo = (TbBringUpOntent) iter.next();
				Vector buoV = new Vector();
				buoV.add(i++);
				TbRecord record = buo.getTbRecord();
				buoV.add(record.getRecordNumber());
				buoV.add(record.getName());
				buoV.add(record.getSex());
				TbDutyInfo dutyInfo = record.getTbDutyInfo();
				buoV.add(dutyInfo.getTbDept().getName());
				buoV.add(dutyInfo.getTbDuty().getName());
				tableModel.addRow(buoV);
			}
		}
		//
	}
}
