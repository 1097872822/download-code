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
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.mwq.hibernate.Dao;
import com.mwq.hibernate.HibernateSessionFactory;
import com.mwq.hibernate.mapping.TbAccountItem;
import com.mwq.hibernate.mapping.TbReckoning;
import com.mwq.hibernate.mapping.TbReckoningInfo;
import com.mwq.mwing.MTable;

public class CriterionSetPanel extends JPanel {

	private JTextArea textArea;

	private JTable leftTable;

	private JTable rightTable;

	private final Vector<String> leftTableColumnV = new Vector<String>();

	private final Vector<Vector<String>> leftTableValueV = new Vector<Vector<String>>();

	private final DefaultTableModel leftTableModel = new DefaultTableModel(
			leftTableValueV, leftTableColumnV);

	private final Vector<String> rightTableColumnV = new Vector<String>();

	private final Vector<Vector<String>> rightTableValueV = new Vector<Vector<String>>();

	private final DefaultTableModel rightTableModel = new DefaultTableModel(
			rightTableValueV, rightTableColumnV);

	private final Vector<TbReckoning> reckoningV = new Vector<TbReckoning>();

	private final Dimension dimension = Toolkit.getDefaultToolkit()
			.getScreenSize();

	private final int width = dimension.width;

	private final int height = dimension.height;

	private final Dao dao = Dao.getInstance();

	private String reckoningExplain = "";

	private int lastSelectedRow = -1;

	private int needSaveRow = -1;

	/**
	 * Create the panel
	 */
	public CriterionSetPanel() {
		super();
		setLayout(new BorderLayout());

		final JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(Color.WHITE);
		add(buttonPanel, BorderLayout.NORTH);

		final JButton addSetButton = new JButton();
		addSetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (needSaveRow == -1) {// û����Ҫ���������
					CreateCriterionSetDialog createCriterionSet = new CreateCriterionSetDialog();
					createCriterionSet.setBounds((width - 350) / 2,
							(height - 250) / 2, 350, 250);
					createCriterionSet.setVisible(true);// �����½����׶Ի���
					if (createCriterionSet.isSubmit()) {// ������ȷ������ť
						String name = createCriterionSet.getNameTextField()
								.getText();// �����������
						String explain = createCriterionSet
								.getExplainTextArea().getText();// �������˵��

						needSaveRow = leftTableValueV.size();// ���½���������Ϊ��Ҫ���������

						Vector<String> newCriterionSetV = new Vector<String>();// �����������ױ���е���������
						newCriterionSetV.add(needSaveRow + 1 + "");// ����������
						newCriterionSetV.add(name);// �����������
						leftTableModel.addRow(newCriterionSetV);// ������������ӵ��������ױ����
						leftTable.setRowSelectionInterval(needSaveRow,
								needSaveRow);// �����½�����Ϊѡ����
						textArea.setText(explain);// ��������˵��

						TbReckoning reckoning = new TbReckoning();// �������׶���
						reckoning.setName(name);// ������������
						reckoning.setExplain(explain);// ��������˵��
						reckoningV.add(reckoning);// �����׶�����ӵ�������

						refreshItemAllRowValueV(needSaveRow);// ͬ��ˢ���Ҳ��������Ŀ���
					}
				} else {// ����Ҫ��������ף�������ʾ����Ի���
					JOptionPane.showMessageDialog(null, "���ȱ������ף� "
							+ leftTable.getValueAt(needSaveRow, 1), "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		addSetButton.setText("�½�����");
		buttonPanel.add(addSetButton);

		final JButton updateSetButton = new JButton();
		updateSetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = leftTable.getSelectedRow();
				if (needSaveRow == -1 || needSaveRow == selectedRow) {
					if (selectedRow == -1) {
						JOptionPane.showMessageDialog(null, "�����Ѿ�û���κ�������Ϣ��",
								"������ʾ", JOptionPane.INFORMATION_MESSAGE);
					} else {
						CreateCriterionSetDialog updateCriterionSet = new CreateCriterionSetDialog();
						updateCriterionSet.setTitle("����ά��");
						updateCriterionSet.getNameTextField().setText(
								leftTableValueV.get(selectedRow).get(1));
						updateCriterionSet.getExplainTextArea().setText(
								textArea.getText());
						updateCriterionSet.setBounds((width - 350) / 2,
								(height - 250) / 2, 350, 250);
						updateCriterionSet.setVisible(true);
						if (updateCriterionSet.isSubmit()) {
							needSaveRow = selectedRow;
							Vector<String> updateCriterionSetV = leftTableValueV
									.get(needSaveRow);
							updateCriterionSetV.set(1, updateCriterionSet
									.getNameTextField().getText().toString());
							leftTableModel.setDataVector(leftTableValueV,
									leftTableColumnV);
							textArea.setText(updateCriterionSet
									.getExplainTextArea().getText());
							leftTable.setRowSelectionInterval(needSaveRow,
									needSaveRow);
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "���ȱ������ף� "
							+ leftTable.getValueAt(needSaveRow, 1), "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		updateSetButton.setText("�޸�����");
		buttonPanel.add(updateSetButton);

		final JButton delSetButton = new JButton();
		delSetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int leftSelectedRow = leftTable.getSelectedRow();
				if (leftSelectedRow == -1) {
					JOptionPane.showMessageDialog(null, "�����Ѿ�û���κ�������Ϣ��",
							"������ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else {
					int i = JOptionPane.showConfirmDialog(null, "ȷ��ɾ�����ף�"
							+ leftTable.getValueAt(leftSelectedRow, 1), "������ʾ",
							JOptionPane.YES_NO_OPTION);
					if (i == 0) {
						if (needSaveRow == -1 || leftSelectedRow == needSaveRow) {
							needSaveRow = -1;
							leftTableValueV.remove(leftSelectedRow);
							dao.deleteObject(reckoningV.get(leftSelectedRow));
							reckoningV.remove(leftSelectedRow);
							if (leftSelectedRow == leftTableValueV.size()) {
								// ����Ĭ��ѡ���е�����
								leftSelectedRow -= 1;
							} else {
								// �޸����
								for (int j = leftSelectedRow; j < leftTableValueV
										.size(); j++) {
									leftTable.setValueAt(j + 1, j, 0);
								}
							}
							leftTableModel.setDataVector(leftTableValueV,
									leftTableColumnV);
							if (leftSelectedRow != -1)
								leftTable.setRowSelectionInterval(
										leftSelectedRow, leftSelectedRow);
							refreshItemAllRowValueV(leftSelectedRow);
						} else {
							JOptionPane.showMessageDialog(null, "���ȱ������ף� "
									+ leftTable.getValueAt(needSaveRow, 1),
									"������ʾ", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					}
				}
			}
		});
		delSetButton.setText("ɾ������");
		buttonPanel.add(delSetButton);

		final JLabel leftLabel = new JLabel();
		leftLabel.setPreferredSize(new Dimension(20, 20));
		buttonPanel.add(leftLabel);

		final JButton addItemButton = new JButton();
		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int leftSelectedRow = leftTable.getSelectedRow();// ���ѡ�е�����
				if (leftSelectedRow == -1) {// δѡ���κ�����
					JOptionPane.showMessageDialog(null, "���Ƚ������ף�", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ������ʾ��Ϣ
				} else {// ��ѡ������
					if (needSaveRow == -1 || needSaveRow == leftSelectedRow) {
						addItem(leftSelectedRow);// ���÷����������
					} else {
						JOptionPane.showMessageDialog(null, "���ȱ������ף� "
								+ leftTable.getValueAt(needSaveRow, 1), "������ʾ",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		addItemButton.setText("�����Ŀ");
		buttonPanel.add(addItemButton);

		final JButton delItemButton = new JButton();
		delItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int leftSelectedRow = leftTable.getSelectedRow();
				if (leftSelectedRow == -1) {
					JOptionPane.showMessageDialog(null, "�����Ѿ�û���κ�������Ϣ��",
							"������ʾ", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (needSaveRow == -1 || needSaveRow == leftSelectedRow) {
						int rightSelectedRow = rightTable.getSelectedRow();
						if (rightSelectedRow == -1) {
							JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ������Ŀ��",
									"������ʾ", JOptionPane.INFORMATION_MESSAGE);
						} else {
							int i = JOptionPane.showConfirmDialog(null,
									"ȷ��ɾ����Ŀ�� "
											+ rightTable.getValueAt(
													rightSelectedRow, 1),
									"������ʾ", JOptionPane.YES_NO_OPTION);
							if (i == 0) {
								needSaveRow = leftSelectedRow;
								String name = (String) rightTable.getValueAt(
										rightSelectedRow, 1);
								String unit = (String) rightTable.getValueAt(
										rightSelectedRow, 2);
								Set reckoningInfoSet = reckoningV.get(
										leftSelectedRow).getTbReckoningInfos();
								Iterator<TbReckoningInfo> reckoningInfoIter = reckoningInfoSet
										.iterator();
								while (reckoningInfoIter.hasNext()) {
									TbReckoningInfo reckoningInfo = reckoningInfoIter
											.next();
									TbAccountItem tbAccountItem = reckoningInfo
											.getTbAccountItem();
									if (tbAccountItem.getName().equals(name)
											&& tbAccountItem.getUnit().equals(
													unit)) {
										reckoningInfoSet.remove(reckoningInfo);
										break;
									}
								}
								refreshItemAllRowValueV(leftSelectedRow);
								if (rightSelectedRow != 0) {
									if (rightSelectedRow == rightTableValueV
											.size()) {
										rightSelectedRow -= 1;
									}
									rightTable.setRowSelectionInterval(
											rightSelectedRow, rightSelectedRow);
								}
							}
						}
					} else {
						JOptionPane.showMessageDialog(null, "���ȱ������ף� "
								+ leftTable.getValueAt(needSaveRow, 1), "������ʾ",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		delItemButton.setText("ɾ����Ŀ");
		buttonPanel.add(delItemButton);

		final JButton updateMoneyButton = new JButton();
		updateMoneyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int leftSelectedRow = leftTable.getSelectedRow();// ���ѡ�е�����
				if (needSaveRow == -1 || needSaveRow == leftSelectedRow) {
					int rightSelectedRow = rightTable.getSelectedRow();
					if (rightSelectedRow == -1) {
						JOptionPane.showMessageDialog(null, "�����Ѿ�û���κ���Ŀ��Ϣ��",
								"������ʾ", JOptionPane.INFORMATION_MESSAGE);
						return;
					} else {
						updateItemMoney(leftSelectedRow, rightSelectedRow);
					}
				} else {
					JOptionPane.showMessageDialog(null, "���ȱ������ף� "
							+ leftTable.getValueAt(needSaveRow, 1), "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		updateMoneyButton.setText("�޸Ľ��");
		buttonPanel.add(updateMoneyButton);

		final JLabel rightLabel = new JLabel();
		rightLabel.setPreferredSize(new Dimension(20, 20));
		buttonPanel.add(rightLabel);

		final JButton saveButton = new JButton();
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (needSaveRow == -1) {
					JOptionPane.showMessageDialog(null, "��ǰû����Ҫ��������ף�", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					leftTable.setRowSelectionInterval(needSaveRow, needSaveRow);
					int rowCount = rightTable.getRowCount();
					if (rowCount == 0) {
						JOptionPane.showMessageDialog(null, "��Ϊ���ס�"
								+ leftTable.getValueAt(needSaveRow, 1)
								+ "�������Ŀ��", "������ʾ",
								JOptionPane.INFORMATION_MESSAGE);
						addItem(needSaveRow);
						return;
					} else {
						for (int i = 0; i < rowCount; i++) {
							if (rightTable.getValueAt(i, 4).equals("0")) {
								String info = "��Ϊ���ס�"
										+ leftTable.getValueAt(needSaveRow, 1)
										+ "���е���Ŀ��"
										+ rightTable.getValueAt(i, 1) + "����д��"
										+ rightTable.getValueAt(i, 3) + "����";
								JOptionPane
										.showMessageDialog(null, info, "������ʾ",
												JOptionPane.INFORMATION_MESSAGE);
								rightTable.setRowSelectionInterval(i, i);
								updateItemMoney(needSaveRow, i);
								return;
							}
						}
					}
					dao.saveOrUpdateObject(reckoningV.get(needSaveRow));
					HibernateSessionFactory.closeSession();
					//
					JOptionPane.showMessageDialog(null, "�Ѿ��ɹ��������ף� "
							+ leftTable.getValueAt(needSaveRow, 1) + "��",
							"������ʾ", JOptionPane.INFORMATION_MESSAGE);
					needSaveRow = -1;
				}
			}
		});
		saveButton.setText("����");
		buttonPanel.add(saveButton);

		final JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(300);
		add(splitPane);

		final JScrollPane leftScrollPane = new JScrollPane();
		splitPane.setLeftComponent(leftScrollPane);
		leftScrollPane.setPreferredSize(new Dimension(240, 0));

		leftTableColumnV.add("���");
		leftTableColumnV.add("��������");

		leftTable = new MTable(leftTableModel);
		leftTable.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int selectedRow = leftTable.getSelectedRow();
				if (selectedRow != lastSelectedRow) {
					lastSelectedRow = selectedRow;
					refreshItemAllRowValueV(selectedRow);
				}
			}
		});
		leftTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		leftScrollPane.setViewportView(leftTable);

		final JScrollPane rightScrollPane = new JScrollPane();
		splitPane.setRightComponent(rightScrollPane);

		rightTableColumnV.add("���");
		rightTableColumnV.add("��Ŀ����");
		rightTableColumnV.add("��Ŀ��λ");
		rightTableColumnV.add("��Ŀ����");
		rightTableColumnV.add("���");

		rightTable = new MTable(rightTableModel);
		rightTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		rightScrollPane.setViewportView(rightTable);

		final JPanel explainPanel = new JPanel();
		add(explainPanel, BorderLayout.SOUTH);
		explainPanel.setLayout(new GridBagLayout());
		explainPanel.setBorder(new TitledBorder(null, "",
				TitledBorder.DEFAULT_JUSTIFICATION,
				TitledBorder.DEFAULT_POSITION, null, null));
		explainPanel.setBackground(Color.WHITE);

		final JLabel explainLabel = new JLabel();
		explainLabel.setText("����˵����");
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.insets = new Insets(0, 0, 60, 0);
		gridBagConstraints.gridx = 0;
		gridBagConstraints.gridy = 0;
		explainPanel.add(explainLabel, gridBagConstraints);

		final JScrollPane explainScrollPane = new JScrollPane();
		explainScrollPane.setPreferredSize(new Dimension(766, 80));
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.gridx = 1;
		gridBagConstraints_1.gridy = 0;
		explainPanel.add(explainScrollPane, gridBagConstraints_1);

		textArea = new JTextArea();
		textArea.setText(reckoningExplain);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		explainScrollPane.setViewportView(textArea);
		//
		Iterator reckoningIt = dao.queryReckoning().iterator();
		int reckoningNum = 1;
		while (reckoningIt.hasNext()) {
			TbReckoning reckoning = (TbReckoning) reckoningIt.next();
			reckoningV.add(reckoning);
			Vector<String> rowValueV = new Vector<String>();
			rowValueV.add(reckoningNum++ + "");
			rowValueV.add(reckoning.getName());
			leftTableValueV.add(rowValueV);
		}
		leftTableModel.setDataVector(leftTableValueV, leftTableColumnV);
		if (leftTable.getRowCount() > 0)
			leftTable.setRowSelectionInterval(0, 0);// Ĭ��ѡ�е�һ�У���a�е�b�У�

		refreshItemAllRowValueV(0);
		HibernateSessionFactory.closeSession();
	}

	public void refreshItemAllRowValueV(int row) {
		rightTableValueV.removeAllElements();
		if (reckoningV.size() > 0) {
			TbReckoning reckoning = reckoningV.get(row);
			textArea.setText(reckoning.getExplain());
			Iterator<TbReckoningInfo> reckoningInfoIt = reckoning
					.getTbReckoningInfos().iterator();
			int reckoningInfoNum = 1;
			while (reckoningInfoIt.hasNext()) {
				TbReckoningInfo reckoningInfo = reckoningInfoIt.next();
				Vector<String> reckoningInfoV = new Vector<String>();
				reckoningInfoV.add(reckoningInfoNum++ + "");
				reckoningInfoV.add(reckoningInfo.getTbAccountItem().getName());
				reckoningInfoV.add(reckoningInfo.getTbAccountItem().getUnit());
				reckoningInfoV.add(reckoningInfo.getTbAccountItem().getType());
				reckoningInfoV.add(reckoningInfo.getMoney().toString());
				rightTableValueV.add(reckoningInfoV);
			}
		} else {
			textArea.setText("");
		}
		rightTableModel.setDataVector(rightTableValueV, rightTableColumnV);
		if (rightTable.getRowCount() > 0)
			rightTable.setRowSelectionInterval(0, 0);
	}

	public void addItem(int leftSelectedRow) {
		AddAccountItemDialog addAccountItemDialog = new AddAccountItemDialog();
		addAccountItemDialog.setBounds((width - 500) / 2, (height - 375) / 2,
				500, 375);
		addAccountItemDialog.setVisible(true);// ���������Ŀ�Ի���
		//
		JTable itemTable = addAccountItemDialog.getTable();// ��öԻ����еı�����
		int[] selectedRows = itemTable.getSelectedRows();// ���ѡ���е�����
		if (selectedRows.length > 0) {// ������ӵ���Ŀ
			needSaveRow = leftSelectedRow;// ���õ�ǰ����Ϊ��Ҫ���������
			int defaultSelectedRow = rightTable.getRowCount();// ��ѡ��������Ϊ�������Ŀ�ĵ�һ��
			TbReckoning reckoning = reckoningV.get(leftSelectedRow);// ���ѡ�����׵Ķ���
			for (int i = 0; i < selectedRows.length; i++) {// ͨ��ѭ���������������Ŀ
				String name = itemTable.getValueAt(selectedRows[i], 1)
						.toString();// �����Ŀ����
				String unit = itemTable.getValueAt(selectedRows[i], 2)
						.toString();// �����Ŀ��λ
				Iterator<TbReckoningInfo> reckoningInfoIt = reckoning
						.getTbReckoningInfos().iterator();// ���������е�������Ŀ
				boolean had = false;// Ĭ����������Ŀ�в���������ӵ���Ŀ
				while (reckoningInfoIt.hasNext()) {// ͨ��ѭ�������Ƿ����
					TbAccountItem accountItem = reckoningInfoIt.next()
							.getTbAccountItem();// ������е���Ŀ����
					if (accountItem.getName().equals(name)
							&& accountItem.getUnit().equals(unit)) {
						had = true;// ����
						break;// ����ѭ��
					}
				}
				if (!had) {// ���û�������
					TbReckoningInfo reckoningInfo = new TbReckoningInfo();// ����������Ϣ����
					TbAccountItem accountItem = (TbAccountItem) dao
							.queryAccountItemByNameUnit(name, unit);// ���������Ŀ����
					accountItem.getTbReckoningInfos().add(reckoningInfo);// ������������Ŀ����������Ϣ����Ĺ���
					reckoningInfo.setTbAccountItem(accountItem);// ������������Ϣ����������Ŀ����Ĺ���
					reckoningInfo.setMoney(0);// ������Ŀ���Ϊ0
					reckoningInfo.setTbReckoning(reckoning);// ������������Ϣ�������׶���Ĺ���
					reckoning.getTbReckoningInfos().add(reckoningInfo);// ���������׶���������Ϣ����Ĺ���
				}
			}
			refreshItemAllRowValueV(leftSelectedRow);// ͬ��ˢ���Ҳ��������Ŀ���
			rightTable.setRowSelectionInterval(defaultSelectedRow,
					defaultSelectedRow);// �����������Ŀ�ĵ�һ��Ϊѡ����
			addAccountItemDialog.dispose();// ���������Ŀ�Ի���
		}
	}

	public void updateItemMoney(int leftSelectedRow, int rightSelectedRow) {
		String money = null;
		done: while (true) {
			money = JOptionPane.showInputDialog(null, "����д"
					+ rightTable.getValueAt(rightSelectedRow, 1) + "��"
					+ rightTable.getValueAt(rightSelectedRow, 3).toString().trim() + "��",
					"�޸Ľ��", JOptionPane.INFORMATION_MESSAGE);
			if (money == null) {// �û�������ȡ������ť
				break done;// ȡ���޸�
			} else {// �û�������ȷ������ť
				if (money.equals("")) {// δ�����������ʾ�Ի���
					JOptionPane.showMessageDialog(null, "�������", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);
				} else {// �����˽��
					Pattern pattern = Pattern.compile("[1-9][0-9]{0,5}");// ��������1����999999֮��
					Matcher matcher = pattern.matcher(money);// ͨ��������ʽ�ж��Ƿ����Ҫ��
					if (matcher.matches()) {// ����Ҫ��
						needSaveRow = leftSelectedRow;// ���õ�ǰ����Ϊ��Ҫ���������
						rightTable.setValueAt(money, rightSelectedRow, 4);// �޸���Ŀ���
						int nextSelectedRow = rightSelectedRow + 1;// Ĭ�ϴ�����һ��
						if (nextSelectedRow < rightTable.getRowCount()) {// ������һ��
							rightTable.setRowSelectionInterval(nextSelectedRow,
									nextSelectedRow);
						}
						//
						String name = rightTable
								.getValueAt(rightSelectedRow, 1).toString();// �����Ŀ����
						String unit = rightTable
								.getValueAt(rightSelectedRow, 2).toString();// �����Ŀ��λ
						TbReckoning reckoning = reckoningV.get(leftSelectedRow);// ���ѡ�����׵Ķ���
						Iterator reckoningInfoIt = reckoning
								.getTbReckoningInfos().iterator();// ������Ŀ
						while (reckoningInfoIt.hasNext()) {// ͨ��ѭ������ѡ����Ŀ
							TbReckoningInfo reckoningInfo = (TbReckoningInfo) reckoningInfoIt
									.next();
							TbAccountItem accountItem = reckoningInfo
									.getTbAccountItem();
							if (accountItem.getName().equals(name)
									&& accountItem.getUnit().equals(unit)) {
								reckoningInfo.setMoney(new Integer(money));// �޸Ľ��
								break;// ����ѭ��
							}
						}
						break done;// �޸����
					} else {// ������Ҫ�󣬵�����ʾ�Ի���
						String infos[] = { "�������������������룡",
								"������Ϊ0����999999֮���������" };
						JOptionPane.showMessageDialog(null, infos, "������ʾ",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}

}
