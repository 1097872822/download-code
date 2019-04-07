package com.mwq.frame.personnel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Date;
import java.util.Enumeration;
import java.util.Iterator;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;

import com.mwq.frame.common.DeptTreeDialog;
import com.mwq.hibernate.Dao;
import com.mwq.hibernate.HibernateSessionFactory;
import com.mwq.hibernate.mapping.TbAccessionForm;
import com.mwq.hibernate.mapping.TbDept;
import com.mwq.hibernate.mapping.TbDuty;
import com.mwq.hibernate.mapping.TbDutyInfo;
import com.mwq.hibernate.mapping.TbNation;
import com.mwq.hibernate.mapping.TbNativePlace;
import com.mwq.hibernate.mapping.TbPersonalInfo;
import com.mwq.hibernate.mapping.TbRecord;
import com.mwq.tool.Today;

public class RecordOperatePanel extends JPanel {

	private JTextField deptTextField;

	private JComboBox accessionFormComboBox;

	private JComboBox dutyComboBox;

	private ButtonGroup partyMemberButtonGroup = new ButtonGroup();

	private ButtonGroup marriagedButtonGroup = new ButtonGroup();

	private ButtonGroup sexButtonGroup = new ButtonGroup();

	private JComboBox schoolageComboBox;

	private JComboBox nativePlaceComboBox;

	private JComboBox nationComboBox;

	private JTextField accumulationFundNOTextField;

	private JTextField annuitySafetyNOTextField;

	private JTextField pactEndDateTextField;

	private JTextField dimissionReasonTextField;

	private JTextField compoSafetyNOTextField;

	private JTextField doleSafetyNOTextField;

	private JTextField medicareSafetyNOTextField;

	private JTextField societySafetyNOTextField;

	private JTextField pactAgeTextField;

	private JTextField bankNOTextField;

	private JTextField pactStartDateTextField;

	private JTextField dimissionDateTextField;

	private JTextField bankNameTextField;

	private JTextField firstPactDateTextField;

	private JTextField accessionDateTextField;

	private JTextField homeAddressTextField;

	private JTextField onesStrongSuitTextField;

	private JTextField likeTextField;

	private JTextField homePostalcodeTextField;

	private JTextField partyMemberDateTextField;

	private JTextField graduateSchoolTextField;

	private JTextField graduateDateTextField;

	private JTextField secondSpecialtyTextField;

	private JTextField secondSchoolageTextField;

	private JTextField computerTextField;

	private JTextField emailTextField;

	private JTextField qqTextField;

	private JTextField telephoneTextField;

	private JTextField handsetTextField;

	private JTextField addressTextField;

	private JTextField postalcodeTextField;

	private JTextField gradeTextField;

	private JTextField specialtyTextField;

	private JTextField foreignLanguageTextField;

	private JTextField idCardTextField;

	private JTextField partyMemberTextField;

	private JTextField birthdayTextField;

	private JTextField nameTextField;

	private JTextField recordNoTextField;

	private JLabel photoLabel;

	private Dao dao = Dao.getInstance();

	public static TbRecord UPDATE_RECORD = null;

	/**
	 * Create the panel
	 */
	public RecordOperatePanel(final JPanel rightPanel) {
		super();
		setLayout(new BorderLayout());

		final JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		add(buttonPanel, BorderLayout.NORTH);

		final JButton saveButton = new JButton();
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// ��ȡ������Ϣ
				TbRecord record = null;
				if (UPDATE_RECORD == null) {
					record = new TbRecord();
				} else {
					record = (TbRecord) dao.queryRecordByNum(recordNoTextField
							.getText());
				}
				record.setRecordNumber(recordNoTextField.getText());
				record.setName(nameTextField.getText());
				Enumeration<AbstractButton> sexElements = sexButtonGroup
						.getElements();
				while (sexElements.hasMoreElements()) {
					AbstractButton button = sexElements.nextElement();
					if (button.isSelected()) {
						record.setSex(button.getText());
						break;
					}
				}
				TbNation nation = (TbNation) dao
						.queryNationByName(nationComboBox.getSelectedItem()
								.toString());
				record.setTbNation(nation);
				TbNativePlace nativePlace = (TbNativePlace) dao
						.queryNativePlaceByName(nativePlaceComboBox
								.getSelectedItem().toString());
				record.setTbNativePlace(nativePlace);
				record.setIdCard(idCardTextField.getText());
				record.setSchoolAge(schoolageComboBox.getSelectedItem()
						.toString());
				record.setSpecialty(specialtyTextField.getText());

				Enumeration<AbstractButton> partyMembers = partyMemberButtonGroup
						.getElements();
				while (partyMembers.hasMoreElements()) {
					AbstractButton button = partyMembers.nextElement();
					if (button.isSelected()) {
						record.setPartyMember(button.getText().trim());
						break;
					}
				}

				record.setForeignLanguage(foreignLanguageTextField.getText());
				record.setGrade(gradeTextField.getText());
				Enumeration<AbstractButton> marriagedElements = marriagedButtonGroup
						.getElements();
				while (marriagedElements.hasMoreElements()) {
					AbstractButton button = marriagedElements.nextElement();
					if (button.isSelected()) {
						record.setMarriaged(button.getText());
						break;
					}
				}
				record.setPostalcode(postalcodeTextField.getText());
				record.setAddress(addressTextField.getText());

				// ��֤������Ϣ
				Method[] methods = record.getClass().getMethods();
				String recordMethodNames[] = { "name", "sex", "tbNation",
						"tbNativePlace;", "idCard", " schoolAge", " specialty",
						" partyMember", " foreignLanguage", " grade",
						"marriaged", " postalcode", "address" };
				for (int i = 0; i < methods.length; i++) {
					String methodName = methods[i].getName();
					if (methodName.startsWith("get")) {
						for (int m = 0; m < recordMethodNames.length; m++) {
							if (methodName.substring(3).toLowerCase().equals(
									recordMethodNames[m].toLowerCase())) {
								Object object = null;
								try {
									object = methods[i].invoke(record, null);
								} catch (Exception e) {
									e.printStackTrace();
								}
								if (object == null) {
									System.out.println(methods[i].getName()
											+ "=" + object);
									JOptionPane.showMessageDialog(null,
											"����Ƭ�⣬[������Ϣ]�����������������Ϊ�գ�", "������ʾ",
											JOptionPane.WARNING_MESSAGE);
									return;
								}
							}
						}

					}
				}
				String myBirthday = birthdayTextField.getText();
				if (myBirthday.equals("YYYY-MM-DD")) {
					JOptionPane.showMessageDialog(null, "������������ڣ�", "������ʾ",
							JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					try {
						Date birthday = Date.valueOf(myBirthday);
						record.setBirthday(birthday);
					} catch (RuntimeException e) {
						JOptionPane.showMessageDialog(null, "������������������������룡",
								"������ʾ", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}

				// ����ͼƬ
				if (photoLabel.getIcon() != null) {// �鿴�Ƿ��ϴ���Ƭ
					File selectPhoto = new File(photoLabel.getIcon().toString());// ͨ��ѡ��ͼƬ��·�������ļ�����
					URL url = this.getClass().getResource("/personnel_photo/"); // ���ָ��·���ľ���·��
					StringBuffer uriBuffer = new StringBuffer(url.toString());// ��֯�ļ�·��
					String selectPhotoName = selectPhoto.getName();
					int i = selectPhotoName.lastIndexOf(".");
					uriBuffer.append(recordNoTextField.getText());
					uriBuffer.append(selectPhotoName.substring(i));
					try {
						File photo = new File(new URL(uriBuffer.toString())
								.toURI());// �����ϴ��ļ�����
						record.setPhoto(photo.getName());// ��ͼƬ���Ʊ��浽���ݿ�
						if (!photo.exists()) {// ����ļ��������򴴽��ļ�
							photo.createNewFile();
						}
						InputStream inStream = new FileInputStream(selectPhoto);// ��������������
						OutputStream outStream = new FileOutputStream(photo);// �������������
						int readBytes = 0; // ��ȡ�ֽ���
						byte[] buffer = new byte[10240]; // ���建������
						while ((readBytes = inStream.read(buffer, 0, 10240)) != -1) { // ����������ȡ���ݵ�����������
							outStream.write(buffer, 0, readBytes); // �����������е���������������
						}
						outStream.close();// �ر����������
						inStream.close();// �ر�����������
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				// ��ȡ������Ϣ
				TbPersonalInfo personalInfo = null;
				if (UPDATE_RECORD == null)
					personalInfo = new TbPersonalInfo();
				else
					personalInfo = record.getTbPersonalInfo();
				personalInfo.setHandset(handsetTextField.getText());
				personalInfo.setTelephone(telephoneTextField.getText());
				personalInfo.setQq(qqTextField.getText());
				personalInfo.setEMail(emailTextField.getText());
				personalInfo.setSecondSchoolAge(secondSchoolageTextField
						.getText());
				personalInfo.setSecondSpecialty(secondSpecialtyTextField
						.getText());
				String myGraduateDate = graduateDateTextField.getText();
				if (!myGraduateDate.equals("YYYY-MM-DD")
						&& myGraduateDate.length() != 0) {
					try {
						Date date = Date.valueOf(myGraduateDate);
						personalInfo.setGraduateDate(date);
					} catch (RuntimeException e) {
						JOptionPane.showMessageDialog(null, "��ҵ��������������������룡",
								"������ʾ", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				personalInfo.setGraduateSchool(graduateSchoolTextField
						.getText());
				personalInfo.setComputerGrade(computerTextField.getText());
				personalInfo.setLikes(likeTextField.getText());
				String myPartyMemberDate = partyMemberDateTextField.getText();
				if (!myPartyMemberDate.equals("YYYY-MM-DD")
						&& myPartyMemberDate.length() != 0) {
					try {
						Date date = Date.valueOf(myPartyMemberDate);
						personalInfo.setPartyMemberDate(date);
					} catch (RuntimeException e) {
						JOptionPane.showMessageDialog(null, "�뵳��������������������룡",
								"������ʾ", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				personalInfo.setOnesStrongSuit(onesStrongSuitTextField
						.getText());
				personalInfo.setPostalcode(homePostalcodeTextField.getText());
				personalInfo.setAddress(homeAddressTextField.getText());

				// ���ְ����Ϣ
				TbDutyInfo dutyInfo = null;
				if (UPDATE_RECORD == null)
					dutyInfo = new TbDutyInfo();
				else
					dutyInfo = record.getTbDutyInfo();

				TbDept dept = (TbDept) dao.queryDeptByName(deptTextField
						.getText());
				dutyInfo.setTbDept(dept);// ����
				TbDuty duty = (TbDuty) dao.queryDutyByName(dutyComboBox
						.getSelectedItem().toString());
				dutyInfo.setTbDuty(duty);// ְ��
				TbAccessionForm accessionForm = (TbAccessionForm) dao
						.queryAccessionFormByName(accessionFormComboBox
								.getSelectedItem().toString());
				dutyInfo.setTbAccessionForm(accessionForm);// �ù���ʽ
				String myDimissionDate = dimissionDateTextField.getText();
				if (!myDimissionDate.equals("YYYY-MM-DD")
						&& myDimissionDate.length() != 0) {
					try {
						Date date = Date.valueOf(myDimissionDate);
						dutyInfo.setDimissionDate(date);// ��ְ����
					} catch (RuntimeException e) {
						JOptionPane.showMessageDialog(null, "��ְ��������������������룡",
								"������ʾ", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				dutyInfo.setDimissionReason(dimissionReasonTextField.getText());// ��ְԭ��
				String myPactStartDate = pactStartDateTextField.getText();
				if (!myPactStartDate.equals("YYYY-MM-DD")
						&& myPactStartDate.length() != 0) {
					try {
						Date date = Date.valueOf(myPactStartDate);
						dutyInfo.setPactStartDate(date);// ��ͬ��ʼ����
						if (firstPactDateTextField.getText().length() == 0)
							dutyInfo.setFirstPactDate(date);// ת������
					} catch (RuntimeException e) {
						JOptionPane.showMessageDialog(null,
								"��ͬ��ʼ��������������������룡", "������ʾ",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				String myPactEndDate = pactEndDateTextField.getText();
				if (!myPactEndDate.equals("YYYY-MM-DD")
						&& myPactEndDate.length() != 0) {
					try {
						Date date = Date.valueOf(myPactEndDate);
						dutyInfo.setPactEndDate(date);// ��ͬ��������
					} catch (RuntimeException e) {
						JOptionPane.showMessageDialog(null,
								"��ͬ������������������������룡", "������ʾ",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
				}
				dutyInfo.setBankName(bankNameTextField.getText());
				dutyInfo.setBankNo(bankNOTextField.getText());
				dutyInfo.setAnnuitySafetyNo(annuitySafetyNOTextField.getText());
				dutyInfo.setMedicareSafetyNo(medicareSafetyNOTextField
						.getText());
				dutyInfo.setCompoSafetyNo(compoSafetyNOTextField.getText());
				dutyInfo.setDoleSafetyNo(doleSafetyNOTextField.getText());
				dutyInfo.setSocietySafetyNo(societySafetyNOTextField.getText());
				dutyInfo.setAccumulationFundNo(accumulationFundNOTextField
						.getText());

				// ��ְ֤����Ϣ
				methods = dutyInfo.getClass().getMethods();
				String dutyInfoMethodNames[] = { "tbDept", "tbDuty",
						"tbAccessionForm" };
				for (int i = 0; i < methods.length; i++) {
					String methodName = methods[i].getName();
					if (methodName.startsWith("get")) {
						for (int m = 0; m < dutyInfoMethodNames.length; m++) {
							if (methodName.substring(3).toLowerCase().equals(
									dutyInfoMethodNames[m].toLowerCase())) {
								Object object = null;
								try {
									object = methods[i].invoke(dutyInfo, null);
									System.out.println("method=" + methods[i]);
									System.out.println("object=" + object);
								} catch (Exception e) {
									e.printStackTrace();
								}
								if (object == null) {
									JOptionPane.showMessageDialog(null,
											new String[] { "[ְ����Ϣ]���еģ�",
													"    ��    ��", "    ְ    ��",
													"    ��ְ����", "    �ù���ʽ",
													"�������Ϊ�գ�" }, "������ʾ",
											JOptionPane.WARNING_MESSAGE);
									return;
								}
							}
						}

					}
				}
				String myAccessionDate = accessionDateTextField.getText();
				if (myAccessionDate.equals("YYYY-MM-DD")) {
					JOptionPane.showMessageDialog(null, "����д��ְ���ڣ�", "������ʾ",
							JOptionPane.WARNING_MESSAGE);
					return;
				} else {
					try {
						Date date = Date.valueOf(myAccessionDate);
						dutyInfo.setAccessionDate(date);
					} catch (RuntimeException e) {
						JOptionPane.showMessageDialog(null, "��ְ��������������������룡",
								"������ʾ", JOptionPane.WARNING_MESSAGE);
						return;
					}
				}

				// �ύ��Ϣ
				if (UPDATE_RECORD == null) {
					personalInfo.setTbRecord(record);
					record.setTbPersonalInfo(personalInfo);
					dutyInfo.setTbRecord(record);
					record.setTbDutyInfo(dutyInfo);
					dao.saveObject(record);
					UPDATE_RECORD = record;
				} else {
					dao.updateObject(record);
				}

				// �ر�Session
				HibernateSessionFactory.closeSession();

				// �����ɹ���ʾ
				JOptionPane.showMessageDialog(null, "�Ѿ��ɹ�����õ�����Ϣ��", "������ʾ",
						JOptionPane.INFORMATION_MESSAGE);

			}
		});
		saveButton.setText("����");
		buttonPanel.add(saveButton);

		final JButton exitButton = new JButton();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rightPanel.removeAll();
				rightPanel.add(new RecordSelectedPanel(rightPanel));
				SwingUtilities.updateComponentTreeUI(rightPanel);
			}
		});
		exitButton.setText("�˳�");
		buttonPanel.add(exitButton);

		final JPanel contentPanel = new JPanel();
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		contentPanel.setLayout(new BorderLayout());
		add(contentPanel, BorderLayout.CENTER);

		final JPanel recordNumPanel = new JPanel();
		recordNumPanel.setBackground(Color.WHITE);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		recordNumPanel.setLayout(flowLayout);
		contentPanel.add(recordNumPanel, BorderLayout.NORTH);

		final JLabel recordNoLabel = new JLabel();
		recordNoLabel.setText(" ������ţ�");
		recordNumPanel.add(recordNoLabel);

		recordNoTextField = new JTextField();
		recordNoTextField.setHorizontalAlignment(SwingConstants.CENTER);
		recordNoTextField.setPreferredSize(new Dimension(125, 20));
		recordNoTextField.setEditable(false);

		if (UPDATE_RECORD == null) {
			String record = (String) dao.queryRecordOfMaxRecordNum();
			if (record == null) {
				recordNoTextField.setText("T00001");
			} else {
				String recordNum = (Integer.valueOf(record.substring(1)) + 1)
						+ "";
				StringBuffer id = new StringBuffer(6);
				id.append(recordNum);
				for (int i = 0; i < 5 - recordNum.length(); i++) {
					id.insert(0, '0');
				}
				id.insert(0, 'T');
				recordNoTextField.setText(id.toString());
			}
		} else {
			recordNoTextField.setText(UPDATE_RECORD.getRecordNumber());
		}
		recordNumPanel.add(recordNoTextField);

		final JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		infoPanel.setBackground(Color.WHITE);
		contentPanel.add(infoPanel, BorderLayout.CENTER);

		final JPanel recordInfoPanel = new JPanel();
		recordInfoPanel.setLayout(new GridBagLayout());
		recordInfoPanel.setBorder(new TitledBorder(null, "������Ϣ",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		recordInfoPanel.setBackground(Color.WHITE);
		infoPanel.add(recordInfoPanel, BorderLayout.NORTH);

		final JLabel nameLabel = new JLabel();
		nameLabel.setText("��    ����");
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(5, 0, 10, 0);
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		recordInfoPanel.add(nameLabel, gridBagConstraints);

		nameTextField = new JTextField();
		nameTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			nameTextField.setText(UPDATE_RECORD.getName());
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.insets = new Insets(5, 0, 10, 20);
		gridBagConstraints_1.gridy = 0;
		gridBagConstraints_1.gridx = 1;
		recordInfoPanel.add(nameTextField, gridBagConstraints_1);

		final JLabel sexLabel = new JLabel();
		sexLabel.setText("��    ��");
		final GridBagConstraints gridBagConstraints_11 = new GridBagConstraints();
		gridBagConstraints_11.insets = new Insets(5, 0, 10, 0);
		gridBagConstraints_11.gridy = 0;
		gridBagConstraints_11.gridx = 2;
		recordInfoPanel.add(sexLabel, gridBagConstraints_11);

		final JRadioButton manRadioButton = new JRadioButton();
		sexButtonGroup.add(manRadioButton);
		manRadioButton.setBackground(Color.WHITE);
		manRadioButton.setText("��");
		final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
		gridBagConstraints_3.insets = new Insets(0, 16, 10, 0);
		gridBagConstraints_3.gridy = 0;
		gridBagConstraints_3.gridx = 3;
		recordInfoPanel.add(manRadioButton, gridBagConstraints_3);

		final JRadioButton womanRadioButton = new JRadioButton();
		sexButtonGroup.add(womanRadioButton);
		womanRadioButton.setBackground(Color.WHITE);
		womanRadioButton.setText("Ů");
		final GridBagConstraints gridBagConstraints_95 = new GridBagConstraints();
		gridBagConstraints_95.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_95.gridy = 0;
		gridBagConstraints_95.gridx = 4;
		recordInfoPanel.add(womanRadioButton, gridBagConstraints_95);

		if (UPDATE_RECORD != null) {
			if (UPDATE_RECORD.getSex().equals("��"))
				manRadioButton.setSelected(true);
			else
				womanRadioButton.setSelected(true);
		}

		final JLabel birthdayLabel = new JLabel();
		birthdayLabel.setText("�������ڣ�");
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.insets = new Insets(5, 0, 10, 0);
		gridBagConstraints_6.gridy = 0;
		gridBagConstraints_6.gridx = 5;
		recordInfoPanel.add(birthdayLabel, gridBagConstraints_6);

		birthdayTextField = new JTextField();
		birthdayTextField.setHorizontalAlignment(SwingConstants.CENTER);
		birthdayTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD == null) {
			birthdayTextField.setText("YYYY-MM-DD");
		} else {
			String date = UPDATE_RECORD.getBirthday().toString();
			date = date.substring(0, 10);
			birthdayTextField.setText(date);
		}
		final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
		gridBagConstraints_7.gridwidth = 2;
		gridBagConstraints_7.insets = new Insets(5, 0, 10, 0);
		gridBagConstraints_7.gridy = 0;
		gridBagConstraints_7.gridx = 6;
		recordInfoPanel.add(birthdayTextField, gridBagConstraints_7);

		final JLabel label_2 = new JLabel();
		label_2.setText("     ");
		final GridBagConstraints gridBagConstraints_93 = new GridBagConstraints();
		gridBagConstraints_93.insets = new Insets(0, 20, 0, 0);
		gridBagConstraints_93.gridy = 0;
		gridBagConstraints_93.gridx = 8;
		recordInfoPanel.add(label_2, gridBagConstraints_93);

		photoLabel = new JLabel();// ����������ʾ��Ƭ�Ķ���
		photoLabel.setHorizontalAlignment(SwingConstants.CENTER);// ������Ƭ�����־�����ʾ
		photoLabel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));// ���ñ߿�
		photoLabel.setPreferredSize(new Dimension(120, 140));// ������ʾ��Ƭ�Ĵ�С
		if (UPDATE_RECORD == null || UPDATE_RECORD.getPhoto() == null) {// �½�������δ�ϴ���Ƭ
			photoLabel.setText("˫�������Ƭ");// ��ʾ������ʾ
		} else {// �޸ĵ����������ϴ���Ƭ
			URL url = this.getClass().getResource("/personnel_photo/");// ���ָ��·���ľ���·��
			String photo = url.toString().substring(5)
					+ UPDATE_RECORD.getPhoto();// ��֯Ա����Ƭ�Ĵ��·��
			photoLabel.setIcon(new ImageIcon(photo));// ������Ƭ������ʾ
		}
		photoLabel.addMouseListener(new MouseAdapter() {// �����������
					public void mouseClicked(MouseEvent e) {
						if (e.getClickCount() == 2) {// �ж��Ƿ�Ϊ˫��
							JFileChooser fileChooser = new JFileChooser();// �����ļ�ѡȡ�Ի���
							fileChooser.setFileFilter(new FileFilter() {// Ϊ�Ի�������ļ�������
										public String getDescription() {// ������ʾ��Ϣ
											return "ͼ���ļ���.jpg;.gif��";
										}

										public boolean accept(File file) {// ���ý����ļ�����
											if (file.isDirectory())// Ϊ�ļ����򷵻�true
												return true;
											String fileName = file.getName()
													.toLowerCase();
											if (fileName.endsWith(".jpg")
													|| fileName
															.endsWith(".gif"))// ΪJPG��JIF��ʽ�ļ��򷵻�true
												return true;
											return false;// ���򷵻�false��������ʾ���ļ�ѡȡ�Ի�����
										}
									});
							int i = fileChooser.showOpenDialog(getParent());// �����ļ�ѡȡ�Ի��򲢽����û��Ĵ�����Ϣ
							if (i == fileChooser.APPROVE_OPTION) {// �û�ѡȡ����Ƭ
								File file = fileChooser.getSelectedFile();// ����û�ѡȡ���ļ�����
								if (file != null) {
									ImageIcon icon = new ImageIcon(file
											.getAbsolutePath());// ������Ƭ����
									photoLabel.setText(null);// ȡ����ʾ����
									photoLabel.setIcon(icon);// ��ʾ��Ƭ
								}
							}
						}
					}
				});
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.insets = new Insets(0, 0, 0, 0);
		gridBagConstraints_8.gridheight = 5;
		gridBagConstraints_8.gridy = 0;
		gridBagConstraints_8.gridx = 9;
		recordInfoPanel.add(photoLabel, gridBagConstraints_8);

		final JLabel label_3 = new JLabel();
		label_3.setText("      ");
		final GridBagConstraints gridBagConstraints_94 = new GridBagConstraints();
		gridBagConstraints_94.gridy = 0;
		gridBagConstraints_94.gridx = 10;
		recordInfoPanel.add(label_3, gridBagConstraints_94);

		final JLabel nationLabel = new JLabel();
		nationLabel.setText("��    �壺");
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_4.gridy = 1;
		gridBagConstraints_4.gridx = 0;
		recordInfoPanel.add(nationLabel, gridBagConstraints_4);

		nationComboBox = new JComboBox();
		nationComboBox.addItem("��ѡ��");
		Iterator nationIt = dao.queryNation().iterator();
		while (nationIt.hasNext()) {
			TbNation nation = (TbNation) nationIt.next();
			nationComboBox.addItem(nation.getName());
		}
		if (UPDATE_RECORD != null) {
			String myNation = UPDATE_RECORD.getTbNation().getName();
			nationComboBox.setSelectedItem(myNation);
		}
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.anchor = GridBagConstraints.WEST;
		gridBagConstraints_5.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_5.gridy = 1;
		gridBagConstraints_5.gridx = 1;
		recordInfoPanel.add(nationComboBox, gridBagConstraints_5);

		final JLabel nativePlaceLabel = new JLabel();
		nativePlaceLabel.setText("��    �᣺");
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_2.gridy = 1;
		gridBagConstraints_2.gridx = 2;
		recordInfoPanel.add(nativePlaceLabel, gridBagConstraints_2);

		nativePlaceComboBox = new JComboBox();
		nativePlaceComboBox.addItem("��ѡ��");
		Iterator nativePlaceIt = dao.queryNativePlace().iterator();
		while (nativePlaceIt.hasNext()) {
			TbNativePlace nativePlace = (TbNativePlace) nativePlaceIt.next();
			nativePlaceComboBox.addItem(nativePlace.getName());
		}
		if (UPDATE_RECORD != null) {
			String myNativePlace = UPDATE_RECORD.getTbNativePlace().getName();
			nativePlaceComboBox.setSelectedItem(myNativePlace);
		}
		final GridBagConstraints gridBagConstraints_12 = new GridBagConstraints();
		gridBagConstraints_12.anchor = GridBagConstraints.WEST;
		gridBagConstraints_12.gridwidth = 2;
		gridBagConstraints_12.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_12.gridy = 1;
		gridBagConstraints_12.gridx = 3;
		recordInfoPanel.add(nativePlaceComboBox, gridBagConstraints_12);

		final JLabel idCardLabel = new JLabel();
		idCardLabel.setText("���֤�ţ�");
		final GridBagConstraints gridBagConstraints_15 = new GridBagConstraints();
		gridBagConstraints_15.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_15.gridy = 1;
		gridBagConstraints_15.gridx = 5;
		recordInfoPanel.add(idCardLabel, gridBagConstraints_15);

		idCardTextField = new JTextField();
		idCardTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			idCardTextField.setText(UPDATE_RECORD.getIdCard());
		final GridBagConstraints gridBagConstraints_16 = new GridBagConstraints();
		gridBagConstraints_16.gridwidth = 2;
		gridBagConstraints_16.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_16.gridy = 1;
		gridBagConstraints_16.gridx = 6;
		recordInfoPanel.add(idCardTextField, gridBagConstraints_16);

		final JLabel schoolageLabel = new JLabel();
		schoolageLabel.setText("ѧ    ����");
		final GridBagConstraints gridBagConstraints_9 = new GridBagConstraints();
		gridBagConstraints_9.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_9.gridy = 2;
		gridBagConstraints_9.gridx = 0;
		recordInfoPanel.add(schoolageLabel, gridBagConstraints_9);

		schoolageComboBox = new JComboBox();
		schoolageComboBox.addItem("��ѡ��");
		schoolageComboBox.addItem("����");
		schoolageComboBox.addItem("��ר");
		schoolageComboBox.addItem("����");
		schoolageComboBox.addItem("˶ʿ");
		schoolageComboBox.addItem("��ʿ");
		if (UPDATE_RECORD != null)
			schoolageComboBox.setSelectedItem(UPDATE_RECORD.getSchoolAge());
		final GridBagConstraints gridBagConstraints_10 = new GridBagConstraints();
		gridBagConstraints_10.anchor = GridBagConstraints.WEST;
		gridBagConstraints_10.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_10.gridy = 2;
		gridBagConstraints_10.gridx = 1;
		recordInfoPanel.add(schoolageComboBox, gridBagConstraints_10);

		final JLabel specialtyLabel = new JLabel();
		specialtyLabel.setText("ר    ҵ��");
		final GridBagConstraints gridBagConstraints_19 = new GridBagConstraints();
		gridBagConstraints_19.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_19.gridy = 2;
		gridBagConstraints_19.gridx = 2;
		recordInfoPanel.add(specialtyLabel, gridBagConstraints_19);

		specialtyTextField = new JTextField();
		specialtyTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			specialtyTextField.setText(UPDATE_RECORD.getSpecialty());
		final GridBagConstraints gridBagConstraints_20 = new GridBagConstraints();
		gridBagConstraints_20.gridwidth = 2;
		gridBagConstraints_20.insets = new Insets(0, 0, 10, 20);
		gridBagConstraints_20.gridy = 2;
		gridBagConstraints_20.gridx = 3;
		recordInfoPanel.add(specialtyTextField, gridBagConstraints_20);

		final JLabel partyMemberLabel = new JLabel();
		partyMemberLabel.setText("��    Ա��");
		final GridBagConstraints gridBagConstraints_13 = new GridBagConstraints();
		gridBagConstraints_13.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_13.gridy = 2;
		gridBagConstraints_13.gridx = 5;
		recordInfoPanel.add(partyMemberLabel, gridBagConstraints_13);

		//

		final JRadioButton isPartyMemberRadioButton = new JRadioButton();
		partyMemberButtonGroup.add(isPartyMemberRadioButton);
		isPartyMemberRadioButton.setBackground(Color.WHITE);
		isPartyMemberRadioButton.setText(" �� ");
		final GridBagConstraints gridBagConstraints_222 = new GridBagConstraints();
		gridBagConstraints_222.insets = new Insets(0, 10, 10, 0);
		gridBagConstraints_222.gridy = 2;
		gridBagConstraints_222.gridx = 6;
		recordInfoPanel.add(isPartyMemberRadioButton, gridBagConstraints_222);

		final JRadioButton notPartyMemberRadioButton = new JRadioButton();
		partyMemberButtonGroup.add(notPartyMemberRadioButton);
		notPartyMemberRadioButton.setBackground(Color.WHITE);
		notPartyMemberRadioButton.setSelected(true);
		notPartyMemberRadioButton.setText(" �� ");
		final GridBagConstraints gridBagConstraints_961 = new GridBagConstraints();
		gridBagConstraints_961.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_961.gridy = 2;
		gridBagConstraints_961.gridx = 7;
		recordInfoPanel.add(notPartyMemberRadioButton, gridBagConstraints_961);

		if (UPDATE_RECORD != null) {
			if (UPDATE_RECORD.getPartyMember().equals("��"))
				isPartyMemberRadioButton.setSelected(true);
			else
				notPartyMemberRadioButton.setSelected(true);
		}

		final JLabel foreignLanguageLabel = new JLabel();
		foreignLanguageLabel.setText("�������֣�");
		final GridBagConstraints gridBagConstraints_17 = new GridBagConstraints();
		gridBagConstraints_17.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_17.gridy = 3;
		gridBagConstraints_17.gridx = 0;
		recordInfoPanel.add(foreignLanguageLabel, gridBagConstraints_17);

		foreignLanguageTextField = new JTextField();
		foreignLanguageTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			foreignLanguageTextField
					.setText(UPDATE_RECORD.getForeignLanguage());
		final GridBagConstraints gridBagConstraints_18 = new GridBagConstraints();
		gridBagConstraints_18.insets = new Insets(0, 0, 10, 20);
		gridBagConstraints_18.gridy = 3;
		gridBagConstraints_18.gridx = 1;
		recordInfoPanel.add(foreignLanguageTextField, gridBagConstraints_18);

		final JLabel gradeLabel = new JLabel();
		gradeLabel.setText("����ˮƽ��");
		final GridBagConstraints gridBagConstraints_23 = new GridBagConstraints();
		gridBagConstraints_23.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_23.gridy = 3;
		gridBagConstraints_23.gridx = 2;
		recordInfoPanel.add(gradeLabel, gridBagConstraints_23);

		gradeTextField = new JTextField();
		gradeTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			gradeTextField.setText(UPDATE_RECORD.getGrade());
		final GridBagConstraints gridBagConstraints_24 = new GridBagConstraints();
		gridBagConstraints_24.gridwidth = 2;
		gridBagConstraints_24.insets = new Insets(0, 0, 10, 20);
		gridBagConstraints_24.gridy = 3;
		gridBagConstraints_24.gridx = 3;
		recordInfoPanel.add(gradeTextField, gridBagConstraints_24);

		final JLabel marriagedLabel = new JLabel();
		marriagedLabel.setText("����״����");
		final GridBagConstraints gridBagConstraints_21 = new GridBagConstraints();
		gridBagConstraints_21.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_21.gridy = 3;
		gridBagConstraints_21.gridx = 5;
		recordInfoPanel.add(marriagedLabel, gridBagConstraints_21);

		final JRadioButton notMarriagedRadioButton = new JRadioButton();
		marriagedButtonGroup.add(notMarriagedRadioButton);
		notMarriagedRadioButton.setBackground(Color.WHITE);
		notMarriagedRadioButton.setText("δ��");
		final GridBagConstraints gridBagConstraints_22 = new GridBagConstraints();
		gridBagConstraints_22.insets = new Insets(0, 10, 10, 0);
		gridBagConstraints_22.gridy = 3;
		gridBagConstraints_22.gridx = 6;
		recordInfoPanel.add(notMarriagedRadioButton, gridBagConstraints_22);

		final JRadioButton hasMarriagedRadioButton = new JRadioButton();
		marriagedButtonGroup.add(hasMarriagedRadioButton);
		hasMarriagedRadioButton.setBackground(Color.WHITE);
		hasMarriagedRadioButton.setText("�ѻ�");
		final GridBagConstraints gridBagConstraints_96 = new GridBagConstraints();
		gridBagConstraints_96.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_96.gridy = 3;
		gridBagConstraints_96.gridx = 7;
		recordInfoPanel.add(hasMarriagedRadioButton, gridBagConstraints_96);

		if (UPDATE_RECORD != null) {
			if (UPDATE_RECORD.getMarriaged().equals("δ��"))
				notMarriagedRadioButton.setSelected(true);
			else
				hasMarriagedRadioButton.setSelected(true);
		}

		final JLabel postalcodeLabel = new JLabel();
		postalcodeLabel.setText("�������룺");
		final GridBagConstraints gridBagConstraints_25 = new GridBagConstraints();
		gridBagConstraints_25.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_25.gridy = 4;
		gridBagConstraints_25.gridx = 0;
		recordInfoPanel.add(postalcodeLabel, gridBagConstraints_25);

		postalcodeTextField = new JTextField();
		postalcodeTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			postalcodeTextField.setText(UPDATE_RECORD.getPostalcode());
		final GridBagConstraints gridBagConstraints_26 = new GridBagConstraints();
		gridBagConstraints_26.insets = new Insets(0, 0, 5, 20);
		gridBagConstraints_26.gridy = 4;
		gridBagConstraints_26.gridx = 1;
		recordInfoPanel.add(postalcodeTextField, gridBagConstraints_26);

		final JLabel addressLabel = new JLabel();
		addressLabel.setText("������ַ��");
		final GridBagConstraints gridBagConstraints_27 = new GridBagConstraints();
		gridBagConstraints_27.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_27.gridy = 4;
		gridBagConstraints_27.gridx = 2;
		recordInfoPanel.add(addressLabel, gridBagConstraints_27);

		addressTextField = new JTextField();
		addressTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			addressTextField.setText(UPDATE_RECORD.getAddress());
		final GridBagConstraints gridBagConstraints_28 = new GridBagConstraints();
		gridBagConstraints_28.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_28.ipadx = 205;
		gridBagConstraints_28.gridwidth = 5;
		gridBagConstraints_28.gridy = 4;
		gridBagConstraints_28.gridx = 3;
		recordInfoPanel.add(addressTextField, gridBagConstraints_28);

		final JPanel personnelInfoLanel = new JPanel();
		personnelInfoLanel.setLayout(new GridBagLayout());
		personnelInfoLanel.setBorder(new TitledBorder(null, "������Ϣ",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		personnelInfoLanel.setBackground(Color.WHITE);
		infoPanel.add(personnelInfoLanel, BorderLayout.SOUTH);

		//
		TbPersonalInfo personalInfo = null;
		if (UPDATE_RECORD != null)
			personalInfo = UPDATE_RECORD.getTbPersonalInfo();

		final JLabel handsetLabel = new JLabel();
		handsetLabel.setText("�ƶ��绰��");
		final GridBagConstraints gridBagConstraints_29 = new GridBagConstraints();
		gridBagConstraints_29.insets = new Insets(5, 0, 10, 0);
		gridBagConstraints_29.gridy = 0;
		gridBagConstraints_29.gridx = 0;
		personnelInfoLanel.add(handsetLabel, gridBagConstraints_29);

		handsetTextField = new JTextField();
		handsetTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			handsetTextField.setText(personalInfo.getHandset());
		final GridBagConstraints gridBagConstraints_30 = new GridBagConstraints();
		gridBagConstraints_30.insets = new Insets(5, 0, 10, 20);
		gridBagConstraints_30.gridy = 0;
		gridBagConstraints_30.gridx = 1;
		personnelInfoLanel.add(handsetTextField, gridBagConstraints_30);

		final JLabel telephoneLabel = new JLabel();
		telephoneLabel.setText("�̶��绰��");
		final GridBagConstraints gridBagConstraints_31 = new GridBagConstraints();
		gridBagConstraints_31.insets = new Insets(5, 0, 10, 0);
		gridBagConstraints_31.gridy = 0;
		gridBagConstraints_31.gridx = 2;
		personnelInfoLanel.add(telephoneLabel, gridBagConstraints_31);

		telephoneTextField = new JTextField();
		telephoneTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			telephoneTextField.setText(personalInfo.getTelephone());
		final GridBagConstraints gridBagConstraints_32 = new GridBagConstraints();
		gridBagConstraints_32.insets = new Insets(5, 0, 10, 20);
		gridBagConstraints_32.gridy = 0;
		gridBagConstraints_32.gridx = 3;
		personnelInfoLanel.add(telephoneTextField, gridBagConstraints_32);

		final JLabel qqLabel = new JLabel();
		qqLabel.setText("   QQ   ��");
		final GridBagConstraints gridBagConstraints_33 = new GridBagConstraints();
		gridBagConstraints_33.insets = new Insets(5, 0, 10, 0);
		gridBagConstraints_33.gridy = 0;
		gridBagConstraints_33.gridx = 4;
		personnelInfoLanel.add(qqLabel, gridBagConstraints_33);

		qqTextField = new JTextField();
		qqTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			qqTextField.setText(personalInfo.getQq());
		final GridBagConstraints gridBagConstraints_34 = new GridBagConstraints();
		gridBagConstraints_34.insets = new Insets(5, 0, 10, 20);
		gridBagConstraints_34.gridy = 0;
		gridBagConstraints_34.gridx = 5;
		personnelInfoLanel.add(qqTextField, gridBagConstraints_34);

		final JLabel emailLabel = new JLabel();
		emailLabel.setText(" E_mail ��");
		final GridBagConstraints gridBagConstraints_35 = new GridBagConstraints();
		gridBagConstraints_35.insets = new Insets(5, 0, 10, 0);
		gridBagConstraints_35.gridy = 0;
		gridBagConstraints_35.gridx = 6;
		personnelInfoLanel.add(emailLabel, gridBagConstraints_35);

		emailTextField = new JTextField();
		emailTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			emailTextField.setText(personalInfo.getEMail());
		final GridBagConstraints gridBagConstraints_36 = new GridBagConstraints();
		gridBagConstraints_36.insets = new Insets(5, 0, 10, 0);
		gridBagConstraints_36.gridy = 0;
		gridBagConstraints_36.gridx = 7;
		personnelInfoLanel.add(emailTextField, gridBagConstraints_36);

		final JLabel secondSchoolageLabel = new JLabel();
		secondSchoolageLabel.setText("�ڶ�ѧ����");
		final GridBagConstraints gridBagConstraints_37 = new GridBagConstraints();
		gridBagConstraints_37.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_37.gridy = 1;
		gridBagConstraints_37.gridx = 0;
		personnelInfoLanel.add(secondSchoolageLabel, gridBagConstraints_37);

		secondSchoolageTextField = new JTextField();
		secondSchoolageTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			secondSchoolageTextField.setText(personalInfo.getSecondSchoolAge());
		final GridBagConstraints gridBagConstraints_42 = new GridBagConstraints();
		gridBagConstraints_42.insets = new Insets(0, 0, 10, 20);
		gridBagConstraints_42.gridy = 1;
		gridBagConstraints_42.gridx = 1;
		personnelInfoLanel.add(secondSchoolageTextField, gridBagConstraints_42);

		final JLabel secondSpecialtyLabel = new JLabel();
		secondSpecialtyLabel.setText("�ڶ�רҵ��");
		final GridBagConstraints gridBagConstraints_38 = new GridBagConstraints();
		gridBagConstraints_38.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_38.gridy = 1;
		gridBagConstraints_38.gridx = 2;
		personnelInfoLanel.add(secondSpecialtyLabel, gridBagConstraints_38);

		secondSpecialtyTextField = new JTextField();
		secondSpecialtyTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			secondSpecialtyTextField.setText(personalInfo.getSecondSpecialty());
		final GridBagConstraints gridBagConstraints_43 = new GridBagConstraints();
		gridBagConstraints_43.insets = new Insets(0, 0, 10, 20);
		gridBagConstraints_43.gridy = 1;
		gridBagConstraints_43.gridx = 3;
		personnelInfoLanel.add(secondSpecialtyTextField, gridBagConstraints_43);

		final JLabel graduateDateLabel = new JLabel();
		graduateDateLabel.setText("��ҵ���ڣ�");
		final GridBagConstraints gridBagConstraints_39 = new GridBagConstraints();
		gridBagConstraints_39.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_39.gridy = 1;
		gridBagConstraints_39.gridx = 4;
		personnelInfoLanel.add(graduateDateLabel, gridBagConstraints_39);

		graduateDateTextField = new JTextField();
		graduateDateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		graduateDateTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD == null || personalInfo.getGraduateDate() == null) {
			graduateDateTextField.setText("YYYY-MM-DD");
		} else {
			String date = personalInfo.getGraduateDate().toString();
			date = date.substring(0, 10);
			graduateDateTextField.setText(date);
		}
		final GridBagConstraints gridBagConstraints_44 = new GridBagConstraints();
		gridBagConstraints_44.insets = new Insets(0, 0, 10, 20);
		gridBagConstraints_44.gridy = 1;
		gridBagConstraints_44.gridx = 5;
		personnelInfoLanel.add(graduateDateTextField, gridBagConstraints_44);

		final JLabel graduateSchoolLabel = new JLabel();
		graduateSchoolLabel.setText("��ҵѧУ��");
		final GridBagConstraints gridBagConstraints_40 = new GridBagConstraints();
		gridBagConstraints_40.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_40.gridy = 1;
		gridBagConstraints_40.gridx = 6;
		personnelInfoLanel.add(graduateSchoolLabel, gridBagConstraints_40);

		graduateSchoolTextField = new JTextField();
		graduateSchoolTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			graduateSchoolTextField.setText(personalInfo.getGraduateSchool());
		final GridBagConstraints gridBagConstraints_45 = new GridBagConstraints();
		gridBagConstraints_45.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_45.gridy = 1;
		gridBagConstraints_45.gridx = 7;
		personnelInfoLanel.add(graduateSchoolTextField, gridBagConstraints_45);

		final JLabel computerLabel = new JLabel();
		computerLabel.setText("����ˮƽ��");
		final GridBagConstraints gridBagConstraints_46 = new GridBagConstraints();
		gridBagConstraints_46.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_46.gridy = 2;
		gridBagConstraints_46.gridx = 0;
		personnelInfoLanel.add(computerLabel, gridBagConstraints_46);

		computerTextField = new JTextField();
		computerTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			computerTextField.setText(personalInfo.getComputerGrade());
		final GridBagConstraints gridBagConstraints_41 = new GridBagConstraints();
		gridBagConstraints_41.insets = new Insets(0, 0, 10, 20);
		gridBagConstraints_41.gridy = 2;
		gridBagConstraints_41.gridx = 1;
		personnelInfoLanel.add(computerTextField, gridBagConstraints_41);

		final JLabel likeLabel = new JLabel();
		likeLabel.setText("��    �ã�");
		final GridBagConstraints gridBagConstraints_47 = new GridBagConstraints();
		gridBagConstraints_47.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_47.gridy = 2;
		gridBagConstraints_47.gridx = 2;
		personnelInfoLanel.add(likeLabel, gridBagConstraints_47);

		likeTextField = new JTextField();
		likeTextField.setPreferredSize(new Dimension(195, 20));
		if (UPDATE_RECORD != null)
			likeTextField.setText(personalInfo.getLikes());
		final GridBagConstraints gridBagConstraints_54 = new GridBagConstraints();
		gridBagConstraints_54.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_54.ipadx = 340;
		gridBagConstraints_54.gridwidth = 5;
		gridBagConstraints_54.gridy = 2;
		gridBagConstraints_54.gridx = 3;
		personnelInfoLanel.add(likeTextField, gridBagConstraints_54);

		final JLabel partyMemberDateLabel = new JLabel();
		partyMemberDateLabel.setText("�뵳���ڣ�");
		final GridBagConstraints gridBagConstraints_50 = new GridBagConstraints();
		gridBagConstraints_50.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_50.gridy = 3;
		gridBagConstraints_50.gridx = 0;
		personnelInfoLanel.add(partyMemberDateLabel, gridBagConstraints_50);

		partyMemberDateTextField = new JTextField();
		partyMemberDateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		partyMemberDateTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD == null || personalInfo.getPartyMemberDate() == null) {
			partyMemberDateTextField.setText("YYYY-MM-DD");
		} else {
			String date = personalInfo.getPartyMemberDate().toString();
			date = date.substring(0, 10);
			partyMemberDateTextField.setText(date);
		}
		final GridBagConstraints gridBagConstraints_52 = new GridBagConstraints();
		gridBagConstraints_52.insets = new Insets(0, 0, 10, 20);
		gridBagConstraints_52.gridy = 3;
		gridBagConstraints_52.gridx = 1;
		personnelInfoLanel.add(partyMemberDateTextField, gridBagConstraints_52);

		final JLabel onesStrongSuitLabel = new JLabel();
		onesStrongSuitLabel.setText("��    ����");
		final GridBagConstraints gridBagConstraints_48 = new GridBagConstraints();
		gridBagConstraints_48.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_48.gridy = 3;
		gridBagConstraints_48.gridx = 2;
		personnelInfoLanel.add(onesStrongSuitLabel, gridBagConstraints_48);

		onesStrongSuitTextField = new JTextField();
		onesStrongSuitTextField.setPreferredSize(new Dimension(195, 20));
		if (UPDATE_RECORD != null)
			onesStrongSuitTextField.setText(personalInfo.getOnesStrongSuit());
		final GridBagConstraints gridBagConstraints_55 = new GridBagConstraints();
		gridBagConstraints_55.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_55.ipadx = 340;
		gridBagConstraints_55.gridwidth = 5;
		gridBagConstraints_55.gridy = 3;
		gridBagConstraints_55.gridx = 3;
		personnelInfoLanel.add(onesStrongSuitTextField, gridBagConstraints_55);

		final JLabel homePostalcodeLabel = new JLabel();
		homePostalcodeLabel.setText("�������룺");
		final GridBagConstraints gridBagConstraints_51 = new GridBagConstraints();
		gridBagConstraints_51.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_51.gridy = 4;
		gridBagConstraints_51.gridx = 0;
		personnelInfoLanel.add(homePostalcodeLabel, gridBagConstraints_51);

		homePostalcodeTextField = new JTextField();
		homePostalcodeTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			homePostalcodeTextField.setText(personalInfo.getPostalcode());
		final GridBagConstraints gridBagConstraints_53 = new GridBagConstraints();
		gridBagConstraints_53.insets = new Insets(0, 0, 5, 20);
		gridBagConstraints_53.gridy = 4;
		gridBagConstraints_53.gridx = 1;
		personnelInfoLanel.add(homePostalcodeTextField, gridBagConstraints_53);

		final JLabel homeAddressLabel = new JLabel();
		homeAddressLabel.setText("��ͥסַ��");
		final GridBagConstraints gridBagConstraints_49 = new GridBagConstraints();
		gridBagConstraints_49.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_49.gridy = 4;
		gridBagConstraints_49.gridx = 2;
		personnelInfoLanel.add(homeAddressLabel, gridBagConstraints_49);

		homeAddressTextField = new JTextField();
		homeAddressTextField.setPreferredSize(new Dimension(195, 20));
		if (UPDATE_RECORD != null)
			homeAddressTextField.setText(personalInfo.getAddress());
		final GridBagConstraints gridBagConstraints_56 = new GridBagConstraints();
		gridBagConstraints_56.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_56.ipadx = 340;
		gridBagConstraints_56.gridwidth = 5;
		gridBagConstraints_56.gridy = 4;
		gridBagConstraints_56.gridx = 3;
		personnelInfoLanel.add(homeAddressTextField, gridBagConstraints_56);

		final JPanel dutyInfoPanel = new JPanel();
		dutyInfoPanel.setLayout(new GridBagLayout());
		dutyInfoPanel.setBorder(new TitledBorder(null, "ְ����Ϣ",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		dutyInfoPanel.setBackground(Color.WHITE);
		infoPanel.add(dutyInfoPanel);

		//
		TbDutyInfo dutyInfo = null;
		if (UPDATE_RECORD != null)
			dutyInfo = UPDATE_RECORD.getTbDutyInfo();

		final JLabel deptLabel = new JLabel();
		deptLabel.setText("��    �ţ�");
		final GridBagConstraints gridBagConstraints_57 = new GridBagConstraints();
		gridBagConstraints_57.insets = new Insets(5, 0, 10, 0);
		gridBagConstraints_57.gridy = 0;
		gridBagConstraints_57.gridx = 0;
		dutyInfoPanel.add(deptLabel, gridBagConstraints_57);

		deptTextField = new JTextField();
		deptTextField.setEditable(false);
		deptTextField.setColumns(14);
		if (UPDATE_RECORD != null)
			deptTextField.setText(dutyInfo.getTbDept().getName());
		final GridBagConstraints gridBagConstraints_58 = new GridBagConstraints();
		gridBagConstraints_58.gridy = 0;
		gridBagConstraints_58.gridx = 1;
		dutyInfoPanel.add(deptTextField, gridBagConstraints_58);

		final JButton deptTreeButton = new JButton();
		deptTreeButton.setMargin(new Insets(0, 6, 0, 3));
		deptTreeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeptTreeDialog deptTree = new DeptTreeDialog(deptTextField);
				deptTree.setBounds(252, 437, 125, 175);
				deptTree.setVisible(true);
			}
		});
		deptTreeButton.setText("...");
		final GridBagConstraints gridBagConstraints_97 = new GridBagConstraints();
		gridBagConstraints_97.insets = new Insets(0, 0, 0, 20);
		gridBagConstraints_97.gridy = 0;
		gridBagConstraints_97.gridx = 2;
		dutyInfoPanel.add(deptTreeButton, gridBagConstraints_97);

		final JLabel dutyLabel = new JLabel();
		dutyLabel.setText("ְ    ��");
		final GridBagConstraints gridBagConstraints_59 = new GridBagConstraints();
		gridBagConstraints_59.insets = new Insets(5, 0, 10, 0);
		gridBagConstraints_59.gridy = 0;
		gridBagConstraints_59.gridx = 3;
		dutyInfoPanel.add(dutyLabel, gridBagConstraints_59);

		dutyComboBox = new JComboBox();
		dutyComboBox.addItem("��ѡ��");
		Iterator dutyIt = dao.queryDuty().iterator();
		while (dutyIt.hasNext()) {
			TbDuty duty = (TbDuty) dutyIt.next();
			dutyComboBox.addItem(duty.getName());
		}
		if (UPDATE_RECORD != null)
			dutyComboBox.setSelectedItem(dutyInfo.getTbDuty().getName());
		final GridBagConstraints gridBagConstraints_60 = new GridBagConstraints();
		gridBagConstraints_60.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_60.anchor = GridBagConstraints.WEST;
		gridBagConstraints_60.gridy = 0;
		gridBagConstraints_60.gridx = 4;
		dutyInfoPanel.add(dutyComboBox, gridBagConstraints_60);

		final JLabel accessionDateLabel = new JLabel();
		accessionDateLabel.setText("��ְ���ڣ�");
		final GridBagConstraints gridBagConstraints_61 = new GridBagConstraints();
		gridBagConstraints_61.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_61.gridy = 1;
		gridBagConstraints_61.gridx = 0;
		dutyInfoPanel.add(accessionDateLabel, gridBagConstraints_61);

		accessionDateTextField = new JTextField();
		accessionDateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		accessionDateTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD == null) {
			accessionDateTextField.setText("YYYY-MM-DD");
		} else {
			String date = dutyInfo.getAccessionDate().toString();
			date = date.substring(0, 10);
			accessionDateTextField.setText(date);
		}
		final GridBagConstraints gridBagConstraints_70 = new GridBagConstraints();
		gridBagConstraints_70.gridwidth = 2;
		gridBagConstraints_70.insets = new Insets(0, 0, 10, 20);
		gridBagConstraints_70.gridy = 1;
		gridBagConstraints_70.gridx = 1;
		dutyInfoPanel.add(accessionDateTextField, gridBagConstraints_70);

		final JLabel accessionFormLabel = new JLabel();
		accessionFormLabel.setText("�ù���ʽ��");
		final GridBagConstraints gridBagConstraints_65 = new GridBagConstraints();
		gridBagConstraints_65.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_65.gridy = 1;
		gridBagConstraints_65.gridx = 3;
		dutyInfoPanel.add(accessionFormLabel, gridBagConstraints_65);

		accessionFormComboBox = new JComboBox();
		accessionFormComboBox.addItem("��ѡ��");
		Iterator accessionFormIt = dao.queryAccessionForm().iterator();
		while (accessionFormIt.hasNext()) {
			TbAccessionForm accessionForm = (TbAccessionForm) accessionFormIt
					.next();
			accessionFormComboBox.addItem(accessionForm.getName());
		}
		if (UPDATE_RECORD != null)
			accessionFormComboBox.setSelectedItem(dutyInfo.getTbAccessionForm()
					.getName());
		HibernateSessionFactory.closeSession();
		final GridBagConstraints gridBagConstraints_76 = new GridBagConstraints();
		gridBagConstraints_76.anchor = GridBagConstraints.WEST;
		gridBagConstraints_76.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_76.gridy = 1;
		gridBagConstraints_76.gridx = 4;
		dutyInfoPanel.add(accessionFormComboBox, gridBagConstraints_76);

		final JLabel dimissionDateLabel = new JLabel();
		dimissionDateLabel.setText("��ְ���ڣ�");
		final GridBagConstraints gridBagConstraints_69 = new GridBagConstraints();
		gridBagConstraints_69.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_69.gridy = 1;
		gridBagConstraints_69.gridx = 5;
		dutyInfoPanel.add(dimissionDateLabel, gridBagConstraints_69);

		dimissionDateTextField = new JTextField();
		dimissionDateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		dimissionDateTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD == null || dutyInfo.getDimissionDate() == null) {
			dimissionDateTextField.setText("YYYY-MM-DD");
		} else {
			String date = dutyInfo.getDimissionDate().toString();
			date = date.substring(0, 10);
			dimissionDateTextField.setText(date);
		}
		final GridBagConstraints gridBagConstraints_73 = new GridBagConstraints();
		gridBagConstraints_73.insets = new Insets(0, 0, 10, 20);
		gridBagConstraints_73.gridy = 1;
		gridBagConstraints_73.gridx = 6;
		dutyInfoPanel.add(dimissionDateTextField, gridBagConstraints_73);

		final JLabel dimissionReasonLabel = new JLabel();
		dimissionReasonLabel.setText("��ְԭ��");
		final GridBagConstraints gridBagConstraints_85 = new GridBagConstraints();
		gridBagConstraints_85.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_85.gridy = 1;
		gridBagConstraints_85.gridx = 7;
		dutyInfoPanel.add(dimissionReasonLabel, gridBagConstraints_85);

		dimissionReasonTextField = new JTextField();
		dimissionReasonTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			dimissionReasonTextField.setText(dutyInfo.getDimissionReason());
		final GridBagConstraints gridBagConstraints_86 = new GridBagConstraints();
		gridBagConstraints_86.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_86.gridy = 1;
		gridBagConstraints_86.gridx = 8;
		dutyInfoPanel.add(dimissionReasonTextField, gridBagConstraints_86);

		final JLabel pactStartDateLabel = new JLabel();
		pactStartDateLabel.setText("��ͬ��ʼ��");
		final GridBagConstraints gridBagConstraints_80 = new GridBagConstraints();
		gridBagConstraints_80.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_80.gridy = 2;
		gridBagConstraints_80.gridx = 0;
		dutyInfoPanel.add(pactStartDateLabel, gridBagConstraints_80);

		pactStartDateTextField = new JTextField();
		pactStartDateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		pactStartDateTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD == null || dutyInfo.getPactStartDate() == null) {
			pactStartDateTextField.setText("YYYY-MM-DD");
		} else {
			String date = dutyInfo.getPactStartDate().toString();
			date = date.substring(0, 10);
			pactStartDateTextField.setText(date);
		}
		final GridBagConstraints gridBagConstraints_74 = new GridBagConstraints();
		gridBagConstraints_74.gridwidth = 2;
		gridBagConstraints_74.insets = new Insets(0, 0, 10, 20);
		gridBagConstraints_74.gridy = 2;
		gridBagConstraints_74.gridx = 1;
		dutyInfoPanel.add(pactStartDateTextField, gridBagConstraints_74);

		final JLabel pactEndDateLabel = new JLabel();
		pactEndDateLabel.setText("��ͬ������");
		final GridBagConstraints gridBagConstraints_87 = new GridBagConstraints();
		gridBagConstraints_87.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_87.gridy = 2;
		gridBagConstraints_87.gridx = 3;
		dutyInfoPanel.add(pactEndDateLabel, gridBagConstraints_87);

		pactEndDateTextField = new JTextField();
		pactEndDateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		pactEndDateTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD == null || dutyInfo.getPactEndDate() == null) {
			pactEndDateTextField.setText("YYYY-MM-DD");
		} else {
			String date = dutyInfo.getPactEndDate().toString();
			date = date.substring(0, 10);
			pactEndDateTextField.setText(date);
		}
		final GridBagConstraints gridBagConstraints_90 = new GridBagConstraints();
		gridBagConstraints_90.insets = new Insets(0, 0, 10, 20);
		gridBagConstraints_90.gridy = 2;
		gridBagConstraints_90.gridx = 4;
		dutyInfoPanel.add(pactEndDateTextField, gridBagConstraints_90);

		final JLabel firstPactDateLabel = new JLabel();
		firstPactDateLabel.setText("ת�����ڣ�");
		final GridBagConstraints gridBagConstraints_62 = new GridBagConstraints();
		gridBagConstraints_62.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_62.gridy = 2;
		gridBagConstraints_62.gridx = 5;
		dutyInfoPanel.add(firstPactDateLabel, gridBagConstraints_62);

		firstPactDateTextField = new JTextField();
		firstPactDateTextField.setEditable(false);
		firstPactDateTextField.setHorizontalAlignment(SwingConstants.CENTER);
		firstPactDateTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null && dutyInfo.getFirstPactDate() != null) {
			String date = dutyInfo.getFirstPactDate().toString();
			date = date.substring(0, 10);
			firstPactDateTextField.setText(date);
		}
		final GridBagConstraints gridBagConstraints_71 = new GridBagConstraints();
		gridBagConstraints_71.insets = new Insets(0, 0, 10, 20);
		gridBagConstraints_71.gridy = 2;
		gridBagConstraints_71.gridx = 6;
		dutyInfoPanel.add(firstPactDateTextField, gridBagConstraints_71);

		final JLabel pactAgeLabel = new JLabel();
		pactAgeLabel.setText("ת�����䣺");
		final GridBagConstraints gridBagConstraints_66 = new GridBagConstraints();
		gridBagConstraints_66.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_66.gridy = 2;
		gridBagConstraints_66.gridx = 7;
		dutyInfoPanel.add(pactAgeLabel, gridBagConstraints_66);

		pactAgeTextField = new JTextField();
		pactAgeTextField.setHorizontalAlignment(SwingConstants.CENTER);
		pactAgeTextField.setEditable(false);
		pactAgeTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null && dutyInfo.getFirstPactDate() != null) {
			String date = dutyInfo.getFirstPactDate().toString();
			System.out.println(date);
			String year = date.substring(0, 4);
			System.out.println(year);
			String month = date.substring(5, 7);
			System.out.println(month);
			int pactAge = Today.YEAR - Integer.valueOf(year);
			if (Today.MONTH <= Integer.valueOf(month))
				pactAge -= 1;
			pactAgeTextField.setText(pactAge + "");
		}
		final GridBagConstraints gridBagConstraints_77 = new GridBagConstraints();
		gridBagConstraints_77.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_77.gridy = 2;
		gridBagConstraints_77.gridx = 8;
		dutyInfoPanel.add(pactAgeTextField, gridBagConstraints_77);

		final JLabel bankNameLabel = new JLabel();
		bankNameLabel.setText("�������У�");
		final GridBagConstraints gridBagConstraints_63 = new GridBagConstraints();
		gridBagConstraints_63.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_63.gridy = 3;
		gridBagConstraints_63.gridx = 0;
		dutyInfoPanel.add(bankNameLabel, gridBagConstraints_63);

		bankNameTextField = new JTextField();
		bankNameTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			bankNameTextField.setText(dutyInfo.getBankName());
		final GridBagConstraints gridBagConstraints_72 = new GridBagConstraints();
		gridBagConstraints_72.gridwidth = 2;
		gridBagConstraints_72.insets = new Insets(0, 0, 10, 20);
		gridBagConstraints_72.gridy = 3;
		gridBagConstraints_72.gridx = 1;
		dutyInfoPanel.add(bankNameTextField, gridBagConstraints_72);

		final JLabel societySafetyNOLabel = new JLabel();
		societySafetyNOLabel.setText("��ᱣ�գ�");
		final GridBagConstraints gridBagConstraints_67 = new GridBagConstraints();
		gridBagConstraints_67.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_67.gridy = 3;
		gridBagConstraints_67.gridx = 3;
		dutyInfoPanel.add(societySafetyNOLabel, gridBagConstraints_67);

		societySafetyNOTextField = new JTextField();
		societySafetyNOTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			societySafetyNOTextField.setText(dutyInfo.getSocietySafetyNo());
		final GridBagConstraints gridBagConstraints_78 = new GridBagConstraints();
		gridBagConstraints_78.insets = new Insets(0, 0, 10, 20);
		gridBagConstraints_78.gridy = 3;
		gridBagConstraints_78.gridx = 4;
		dutyInfoPanel.add(societySafetyNOTextField, gridBagConstraints_78);

		final JLabel doleSafetyNOLabel = new JLabel();
		doleSafetyNOLabel.setText("ʧҵ���գ�");
		final GridBagConstraints gridBagConstraints_81 = new GridBagConstraints();
		gridBagConstraints_81.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_81.gridy = 3;
		gridBagConstraints_81.gridx = 5;
		dutyInfoPanel.add(doleSafetyNOLabel, gridBagConstraints_81);

		doleSafetyNOTextField = new JTextField();
		doleSafetyNOTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			doleSafetyNOTextField.setText(dutyInfo.getDoleSafetyNo());
		final GridBagConstraints gridBagConstraints_83 = new GridBagConstraints();
		gridBagConstraints_83.insets = new Insets(0, 0, 10, 20);
		gridBagConstraints_83.gridy = 3;
		gridBagConstraints_83.gridx = 6;
		dutyInfoPanel.add(doleSafetyNOTextField, gridBagConstraints_83);

		final JLabel annuitySafetyNOLabel = new JLabel();
		annuitySafetyNOLabel.setText("���ϱ��գ�");
		final GridBagConstraints gridBagConstraints_88 = new GridBagConstraints();
		gridBagConstraints_88.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_88.gridy = 3;
		gridBagConstraints_88.gridx = 7;
		dutyInfoPanel.add(annuitySafetyNOLabel, gridBagConstraints_88);

		annuitySafetyNOTextField = new JTextField();
		annuitySafetyNOTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			annuitySafetyNOTextField.setText(dutyInfo.getAnnuitySafetyNo());
		final GridBagConstraints gridBagConstraints_91 = new GridBagConstraints();
		gridBagConstraints_91.insets = new Insets(0, 0, 10, 0);
		gridBagConstraints_91.gridy = 3;
		gridBagConstraints_91.gridx = 8;
		dutyInfoPanel.add(annuitySafetyNOTextField, gridBagConstraints_91);

		final JLabel bankNOLabel = new JLabel();
		bankNOLabel.setText("���ÿ��ţ�");
		final GridBagConstraints gridBagConstraints_64 = new GridBagConstraints();
		gridBagConstraints_64.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_64.gridy = 4;
		gridBagConstraints_64.gridx = 0;
		dutyInfoPanel.add(bankNOLabel, gridBagConstraints_64);

		bankNOTextField = new JTextField();
		bankNOTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			bankNOTextField.setText(dutyInfo.getBankNo());
		final GridBagConstraints gridBagConstraints_75 = new GridBagConstraints();
		gridBagConstraints_75.gridwidth = 2;
		gridBagConstraints_75.insets = new Insets(0, 0, 5, 20);
		gridBagConstraints_75.gridy = 4;
		gridBagConstraints_75.gridx = 1;
		dutyInfoPanel.add(bankNOTextField, gridBagConstraints_75);

		final JLabel medicareSafetyNOLabel = new JLabel();
		medicareSafetyNOLabel.setText("ҽ�Ʊ��գ�");
		final GridBagConstraints gridBagConstraints_68 = new GridBagConstraints();
		gridBagConstraints_68.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_68.gridy = 4;
		gridBagConstraints_68.gridx = 3;
		dutyInfoPanel.add(medicareSafetyNOLabel, gridBagConstraints_68);

		medicareSafetyNOTextField = new JTextField();
		medicareSafetyNOTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			medicareSafetyNOTextField.setText(dutyInfo.getMedicareSafetyNo());
		final GridBagConstraints gridBagConstraints_79 = new GridBagConstraints();
		gridBagConstraints_79.insets = new Insets(0, 0, 5, 20);
		gridBagConstraints_79.gridy = 4;
		gridBagConstraints_79.gridx = 4;
		dutyInfoPanel.add(medicareSafetyNOTextField, gridBagConstraints_79);

		final JLabel compoSafetyNOLabel = new JLabel();
		compoSafetyNOLabel.setText("���˱��գ�");
		final GridBagConstraints gridBagConstraints_82 = new GridBagConstraints();
		gridBagConstraints_82.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_82.gridy = 4;
		gridBagConstraints_82.gridx = 5;
		dutyInfoPanel.add(compoSafetyNOLabel, gridBagConstraints_82);

		compoSafetyNOTextField = new JTextField();
		compoSafetyNOTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			compoSafetyNOTextField.setText(dutyInfo.getCompoSafetyNo());
		final GridBagConstraints gridBagConstraints_84 = new GridBagConstraints();
		gridBagConstraints_84.insets = new Insets(0, 0, 5, 20);
		gridBagConstraints_84.gridy = 4;
		gridBagConstraints_84.gridx = 6;
		dutyInfoPanel.add(compoSafetyNOTextField, gridBagConstraints_84);

		final JLabel accumulationFundNOLabel = new JLabel();
		accumulationFundNOLabel.setText("������ţ�");
		final GridBagConstraints gridBagConstraints_89 = new GridBagConstraints();
		gridBagConstraints_89.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_89.gridy = 4;
		gridBagConstraints_89.gridx = 7;
		dutyInfoPanel.add(accumulationFundNOLabel, gridBagConstraints_89);

		accumulationFundNOTextField = new JTextField();
		accumulationFundNOTextField.setPreferredSize(new Dimension(125, 20));
		if (UPDATE_RECORD != null)
			accumulationFundNOTextField.setText(dutyInfo
					.getAccumulationFundNo());
		final GridBagConstraints gridBagConstraints_92 = new GridBagConstraints();
		gridBagConstraints_92.insets = new Insets(0, 0, 5, 0);
		gridBagConstraints_92.gridy = 4;
		gridBagConstraints_92.gridx = 8;
		dutyInfoPanel.add(accumulationFundNOTextField, gridBagConstraints_92);
		//
	}

}
