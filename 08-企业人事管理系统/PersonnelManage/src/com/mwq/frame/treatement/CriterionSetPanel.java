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
				if (needSaveRow == -1) {// 没有需要保存的账套
					CreateCriterionSetDialog createCriterionSet = new CreateCriterionSetDialog();
					createCriterionSet.setBounds((width - 350) / 2,
							(height - 250) / 2, 350, 250);
					createCriterionSet.setVisible(true);// 弹出新建账套对话框
					if (createCriterionSet.isSubmit()) {// 单击“确定”按钮
						String name = createCriterionSet.getNameTextField()
								.getText();// 获得账套名称
						String explain = createCriterionSet
								.getExplainTextArea().getText();// 获得账套说明

						needSaveRow = leftTableValueV.size();// 将新建账套设置为需要保存的账套

						Vector<String> newCriterionSetV = new Vector<String>();// 创建代表账套表格行的向量对象
						newCriterionSetV.add(needSaveRow + 1 + "");// 添加账套序号
						newCriterionSetV.add(name);// 添加账套名称
						leftTableModel.addRow(newCriterionSetV);// 将向量对象添加到左侧的账套表格中
						leftTable.setRowSelectionInterval(needSaveRow,
								needSaveRow);// 设置新建账套为选中行
						textArea.setText(explain);// 设置账套说明

						TbReckoning reckoning = new TbReckoning();// 创建账套对象
						reckoning.setName(name);// 设置账套名称
						reckoning.setExplain(explain);// 设置账套说明
						reckoningV.add(reckoning);// 将账套对象添加到向量中

						refreshItemAllRowValueV(needSaveRow);// 同步刷新右侧的账套项目表格
					}
				} else {// 有需要保存的账套，弹出提示保存对话框
					JOptionPane.showMessageDialog(null, "请先保存账套： "
							+ leftTable.getValueAt(needSaveRow, 1), "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		addSetButton.setText("新建账套");
		buttonPanel.add(addSetButton);

		final JButton updateSetButton = new JButton();
		updateSetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int selectedRow = leftTable.getSelectedRow();
				if (needSaveRow == -1 || needSaveRow == selectedRow) {
					if (selectedRow == -1) {
						JOptionPane.showMessageDialog(null, "现在已经没有任何账套信息！",
								"友情提示", JOptionPane.INFORMATION_MESSAGE);
					} else {
						CreateCriterionSetDialog updateCriterionSet = new CreateCriterionSetDialog();
						updateCriterionSet.setTitle("账套维护");
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
					JOptionPane.showMessageDialog(null, "请先保存账套： "
							+ leftTable.getValueAt(needSaveRow, 1), "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		updateSetButton.setText("修改账套");
		buttonPanel.add(updateSetButton);

		final JButton delSetButton = new JButton();
		delSetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int leftSelectedRow = leftTable.getSelectedRow();
				if (leftSelectedRow == -1) {
					JOptionPane.showMessageDialog(null, "现在已经没有任何账套信息！",
							"友情提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					int i = JOptionPane.showConfirmDialog(null, "确定删除账套："
							+ leftTable.getValueAt(leftSelectedRow, 1), "友情提示",
							JOptionPane.YES_NO_OPTION);
					if (i == 0) {
						if (needSaveRow == -1 || leftSelectedRow == needSaveRow) {
							needSaveRow = -1;
							leftTableValueV.remove(leftSelectedRow);
							dao.deleteObject(reckoningV.get(leftSelectedRow));
							reckoningV.remove(leftSelectedRow);
							if (leftSelectedRow == leftTableValueV.size()) {
								// 设置默认选中行的索引
								leftSelectedRow -= 1;
							} else {
								// 修改序号
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
							JOptionPane.showMessageDialog(null, "请先保存账套： "
									+ leftTable.getValueAt(needSaveRow, 1),
									"友情提示", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
					}
				}
			}
		});
		delSetButton.setText("删除账套");
		buttonPanel.add(delSetButton);

		final JLabel leftLabel = new JLabel();
		leftLabel.setPreferredSize(new Dimension(20, 20));
		buttonPanel.add(leftLabel);

		final JButton addItemButton = new JButton();
		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int leftSelectedRow = leftTable.getSelectedRow();// 获得选中的账套
				if (leftSelectedRow == -1) {// 未选中任何账套
					JOptionPane.showMessageDialog(null, "请先建立账套！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);// 弹出提示信息
				} else {// 已选中账套
					if (needSaveRow == -1 || needSaveRow == leftSelectedRow) {
						addItem(leftSelectedRow);// 调用方法添加账套
					} else {
						JOptionPane.showMessageDialog(null, "请先保存账套： "
								+ leftTable.getValueAt(needSaveRow, 1), "友情提示",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		addItemButton.setText("添加项目");
		buttonPanel.add(addItemButton);

		final JButton delItemButton = new JButton();
		delItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int leftSelectedRow = leftTable.getSelectedRow();
				if (leftSelectedRow == -1) {
					JOptionPane.showMessageDialog(null, "现在已经没有任何账套信息！",
							"友情提示", JOptionPane.INFORMATION_MESSAGE);
				} else {
					if (needSaveRow == -1 || needSaveRow == leftSelectedRow) {
						int rightSelectedRow = rightTable.getSelectedRow();
						if (rightSelectedRow == -1) {
							JOptionPane.showMessageDialog(null, "请选择要删除的项目！",
									"友情提示", JOptionPane.INFORMATION_MESSAGE);
						} else {
							int i = JOptionPane.showConfirmDialog(null,
									"确定删除项目： "
											+ rightTable.getValueAt(
													rightSelectedRow, 1),
									"友情提示", JOptionPane.YES_NO_OPTION);
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
						JOptionPane.showMessageDialog(null, "请先保存账套： "
								+ leftTable.getValueAt(needSaveRow, 1), "友情提示",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		delItemButton.setText("删除项目");
		buttonPanel.add(delItemButton);

		final JButton updateMoneyButton = new JButton();
		updateMoneyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int leftSelectedRow = leftTable.getSelectedRow();// 获得选中的账套
				if (needSaveRow == -1 || needSaveRow == leftSelectedRow) {
					int rightSelectedRow = rightTable.getSelectedRow();
					if (rightSelectedRow == -1) {
						JOptionPane.showMessageDialog(null, "现在已经没有任何项目信息！",
								"友情提示", JOptionPane.INFORMATION_MESSAGE);
						return;
					} else {
						updateItemMoney(leftSelectedRow, rightSelectedRow);
					}
				} else {
					JOptionPane.showMessageDialog(null, "请先保存账套： "
							+ leftTable.getValueAt(needSaveRow, 1), "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		updateMoneyButton.setText("修改金额");
		buttonPanel.add(updateMoneyButton);

		final JLabel rightLabel = new JLabel();
		rightLabel.setPreferredSize(new Dimension(20, 20));
		buttonPanel.add(rightLabel);

		final JButton saveButton = new JButton();
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (needSaveRow == -1) {
					JOptionPane.showMessageDialog(null, "当前没有需要保存的账套！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				} else {
					leftTable.setRowSelectionInterval(needSaveRow, needSaveRow);
					int rowCount = rightTable.getRowCount();
					if (rowCount == 0) {
						JOptionPane.showMessageDialog(null, "请为账套“"
								+ leftTable.getValueAt(needSaveRow, 1)
								+ "”添加项目！", "友情提示",
								JOptionPane.INFORMATION_MESSAGE);
						addItem(needSaveRow);
						return;
					} else {
						for (int i = 0; i < rowCount; i++) {
							if (rightTable.getValueAt(i, 4).equals("0")) {
								String info = "请为账套“"
										+ leftTable.getValueAt(needSaveRow, 1)
										+ "”中的项目“"
										+ rightTable.getValueAt(i, 1) + "”填写“"
										+ rightTable.getValueAt(i, 3) + "”金额！";
								JOptionPane
										.showMessageDialog(null, info, "友情提示",
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
					JOptionPane.showMessageDialog(null, "已经成功保存账套： "
							+ leftTable.getValueAt(needSaveRow, 1) + "！",
							"友情提示", JOptionPane.INFORMATION_MESSAGE);
					needSaveRow = -1;
				}
			}
		});
		saveButton.setText("保存");
		buttonPanel.add(saveButton);

		final JSplitPane splitPane = new JSplitPane();
		splitPane.setDividerLocation(300);
		add(splitPane);

		final JScrollPane leftScrollPane = new JScrollPane();
		splitPane.setLeftComponent(leftScrollPane);
		leftScrollPane.setPreferredSize(new Dimension(240, 0));

		leftTableColumnV.add("序号");
		leftTableColumnV.add("账套名称");

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

		rightTableColumnV.add("序号");
		rightTableColumnV.add("项目名称");
		rightTableColumnV.add("项目单位");
		rightTableColumnV.add("项目类型");
		rightTableColumnV.add("金额");

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
		explainLabel.setText("账套说明：");
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
			leftTable.setRowSelectionInterval(0, 0);// 默认选中第一行（从a行到b行）

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
		addAccountItemDialog.setVisible(true);// 弹出添加项目对话框
		//
		JTable itemTable = addAccountItemDialog.getTable();// 获得对话框中的表格对象
		int[] selectedRows = itemTable.getSelectedRows();// 获得选中行的索引
		if (selectedRows.length > 0) {// 有新添加的项目
			needSaveRow = leftSelectedRow;// 设置当前账套为需要保存的账套
			int defaultSelectedRow = rightTable.getRowCount();// 将选中行设置为新添加项目的第一行
			TbReckoning reckoning = reckoningV.get(leftSelectedRow);// 获得选中账套的对象
			for (int i = 0; i < selectedRows.length; i++) {// 通过循环向账套中添加项目
				String name = itemTable.getValueAt(selectedRows[i], 1)
						.toString();// 获得项目名称
				String unit = itemTable.getValueAt(selectedRows[i], 2)
						.toString();// 获得项目单位
				Iterator<TbReckoningInfo> reckoningInfoIt = reckoning
						.getTbReckoningInfos().iterator();// 遍历账套中的现有项目
				boolean had = false;// 默认在现有项目中不包含新添加的项目
				while (reckoningInfoIt.hasNext()) {// 通过循环查找是否存在
					TbAccountItem accountItem = reckoningInfoIt.next()
							.getTbAccountItem();// 获得已有的项目对象
					if (accountItem.getName().equals(name)
							&& accountItem.getUnit().equals(unit)) {
						had = true;// 存在
						break;// 跳出循环
					}
				}
				if (!had) {// 如果没有则添加
					TbReckoningInfo reckoningInfo = new TbReckoningInfo();// 创建账套信息对象
					TbAccountItem accountItem = (TbAccountItem) dao
							.queryAccountItemByNameUnit(name, unit);// 获得账套项目对象
					accountItem.getTbReckoningInfos().add(reckoningInfo);// 建立从账套项目对象到账套信息对象的关联
					reckoningInfo.setTbAccountItem(accountItem);// 建立从账套信息对象到账套项目对象的关联
					reckoningInfo.setMoney(0);// 设置项目金额为0
					reckoningInfo.setTbReckoning(reckoning);// 建立从账套信息对象到账套对象的关联
					reckoning.getTbReckoningInfos().add(reckoningInfo);// 建立从账套对象到账套信息对象的关联
				}
			}
			refreshItemAllRowValueV(leftSelectedRow);// 同步刷新右侧的账套项目表格
			rightTable.setRowSelectionInterval(defaultSelectedRow,
					defaultSelectedRow);// 设置新添加项目的第一行为选中行
			addAccountItemDialog.dispose();// 销毁添加项目对话框
		}
	}

	public void updateItemMoney(int leftSelectedRow, int rightSelectedRow) {
		String money = null;
		done: while (true) {
			money = JOptionPane.showInputDialog(null, "请填写"
					+ rightTable.getValueAt(rightSelectedRow, 1) + "的"
					+ rightTable.getValueAt(rightSelectedRow, 3).toString().trim() + "金额：",
					"修改金额", JOptionPane.INFORMATION_MESSAGE);
			if (money == null) {// 用户单击“取消”按钮
				break done;// 取消修改
			} else {// 用户单击“确定”按钮
				if (money.equals("")) {// 未输入金额，弹出提示对话框
					JOptionPane.showMessageDialog(null, "请输入金额！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
				} else {// 输入了金额
					Pattern pattern = Pattern.compile("[1-9][0-9]{0,5}");// 金额必须在1——999999之间
					Matcher matcher = pattern.matcher(money);// 通过正则表达式判断是否符合要求
					if (matcher.matches()) {// 符合要求
						needSaveRow = leftSelectedRow;// 设置当前账套为需要保存的账套
						rightTable.setValueAt(money, rightSelectedRow, 4);// 修改项目金额
						int nextSelectedRow = rightSelectedRow + 1;// 默认存在下一行
						if (nextSelectedRow < rightTable.getRowCount()) {// 存在下一行
							rightTable.setRowSelectionInterval(nextSelectedRow,
									nextSelectedRow);
						}
						//
						String name = rightTable
								.getValueAt(rightSelectedRow, 1).toString();// 获得项目名称
						String unit = rightTable
								.getValueAt(rightSelectedRow, 2).toString();// 获得项目单位
						TbReckoning reckoning = reckoningV.get(leftSelectedRow);// 获得选中账套的对象
						Iterator reckoningInfoIt = reckoning
								.getTbReckoningInfos().iterator();// 遍历项目
						while (reckoningInfoIt.hasNext()) {// 通过循环查找选中项目
							TbReckoningInfo reckoningInfo = (TbReckoningInfo) reckoningInfoIt
									.next();
							TbAccountItem accountItem = reckoningInfo
									.getTbAccountItem();
							if (accountItem.getName().equals(name)
									&& accountItem.getUnit().equals(unit)) {
								reckoningInfo.setMoney(new Integer(money));// 修改金额
								break;// 跳出循环
							}
						}
						break done;// 修改完成
					} else {// 不符合要求，弹出提示对话框
						String infos[] = { "金额输入错误，请重新输入！",
								"金额必须为0——999999之间的整数！" };
						JOptionPane.showMessageDialog(null, infos, "友情提示",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		}
	}

}
