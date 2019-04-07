package com.mwq.frame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.mwq.dao.Dao;
import com.mwq.frame.explorer.InfoDialog;
import com.mwq.frame.explorer.PersonnelDialog;
import com.mwq.frame.mwing.MButton;
import com.mwq.frame.mwing.MTable;

public class ExplorerPanel extends JPanel {

	private MTable infoListTable;

	private Vector<String> infoListTableColumnV;

	private Vector<Vector> infoListTableValueV;

	private DefaultTableModel infoListTableModel;

	private JTree infoTree;

	private DefaultMutableTreeNode infoTreeRoot;

	private DefaultTreeModel infoTreeModel;

	private MTable cardListTable;

	private Vector<String> cardListTableColumnV;

	private Vector<Vector> cardListTableValueV;

	private DefaultTableModel cardListTableModel;

	private JTree cardTree;

	private DefaultMutableTreeNode cardTreeRoot;

	private DefaultTreeModel cardTreeModel;

	private final Dao dao = Dao.getInstance();

	/**
	 * Create the panel
	 */
	public ExplorerPanel(final DefaultTableModel sendListTableModel,
			final JTabbedPane infoTabbedPane, final JTextArea infoTextArea,
			final JTextArea emailTextArea) {
		super();
		setLayout(new BorderLayout());

		final JTabbedPane tabbedPane = new JTabbedPane();
		add(tabbedPane, BorderLayout.CENTER);

		final JSplitPane cardSplitPane = new JSplitPane();
		cardSplitPane.setOneTouchExpandable(true);
		cardSplitPane.setDividerSize(12);
		cardSplitPane.setDividerLocation(244);
		tabbedPane.addTab("��Ƭ��", null, cardSplitPane, null);

		final JPanel cardTreePanel = new JPanel();
		cardSplitPane.setLeftComponent(cardTreePanel);
		cardTreePanel.setLayout(new BorderLayout());

		final JScrollPane cardTreeScrollPane = new JScrollPane();// ������ʾ��Ƭ�����Ĺ������
		cardTreePanel.add(cardTreeScrollPane);// ��ӵ��ϼ������

		cardTreeRoot = new DefaultMutableTreeNode("root");// ������Ƭ�����ĸ��ڵ�
		initTree(cardTreeRoot, "card");// ��ʼ����Ƭ����

		cardTreeModel = new DefaultTreeModel(cardTreeRoot);// ������Ƭ����ģ��

		cardTree = new JTree(cardTreeModel);// ������Ƭ����
		cardTree.setRootVisible(false);// ������Ƭ�����ĸ��ڵ㲻�ɼ�
		System.out.println(cardTree.getSelectionModel().getSelectionMode());
		cardTree.getSelectionModel().setSelectionMode(
				TreeSelectionModel.SINGLE_TREE_SELECTION);// ������Ƭ������ѡ��ģʽΪ��ѡ
		if (cardTreeRoot.getChildCount() > 0)
			cardTree.setSelectionRow(0);// �����Ƭ���������ӽڵ㣬������ѡ�е�һ���ӽڵ�
		cardTree.addTreeSelectionListener(new TreeSelectionListener() {// Ϊ��Ƭ������ӽӵ�ѡ���¼�������
					public void valueChanged(TreeSelectionEvent e) {
						initCardListTable();// ��ʼ����Ƭ���б�
					}
				});
		cardTreeScrollPane.setViewportView(cardTree);// ����Ƭ������ӵ����������

		final JPanel cardTreeButtonPanel = new JPanel();
		final FlowLayout flowLayout_1 = new FlowLayout();
		flowLayout_1.setVgap(0);
		flowLayout_1.setHgap(0);
		cardTreeButtonPanel.setLayout(flowLayout_1);
		cardTreePanel.add(cardTreeButtonPanel, BorderLayout.SOUTH);

		final MButton addCardTypeButton = new MButton();
		addCardTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = addTreeNode(cardTreeRoot, "��Ƭ��");// �������Ƭ������
				if (name != null) {// ���û�ȡ���½�ʱ����Ϊ��
					int childCount = cardTreeRoot.getChildCount();// ��õ�ǰӵ����Ƭ�е�����
					cardTreeModel.insertNodeInto(new DefaultMutableTreeNode(
							name), cardTreeRoot, childCount);// ����Ƭ��������󴴽��µ���Ƭ��
					cardTreeModel.reload();// ˢ����Ƭ����ģ��
					cardTree.setSelectionRow(childCount);// �����½���Ƭ��Ϊѡ��״̬
					dao.iType(name, "card");// ���½���Ƭ�б��浽���ݿ���
				}
			}
		});
		URL creCardTypeUrl = this.getClass().getResource("/img/add_tree.png");
		addCardTypeButton.setIcon(new ImageIcon(creCardTypeUrl));
		URL creCardTypeOverUrl = this.getClass().getResource(
				"/img/add_tree_over.png");
		addCardTypeButton.setRolloverIcon(new ImageIcon(creCardTypeOverUrl));
		cardTreeButtonPanel.add(addCardTypeButton);

		final MButton updCardTypeButton = new MButton();
		updCardTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath selectionPath = cardTree.getSelectionPath();// ���ѡ�нڵ��·������
				if (selectionPath == null) {// �ж�·���Ƿ�Ϊ��
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ���Ƭ�У�", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ���Ϊ���򵯳���ʾ
				} else {
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) selectionPath
							.getLastPathComponent();// ���ѡ�нڵ����
					String nowName = treeNode.getUserObject().toString();// ���ѡ�нڵ������
					int i = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�޸���Ƭ�С�"
							+ nowName + "�������ƣ�", "������ʾ",
							JOptionPane.YES_NO_OPTION);// ����ȷ���޸���ʾ��
					if (i == 0) {// ���Ϊ0���޸�
						String newName = updateTreeNode(treeNode);// ����޸ĺ������
						if (newName != null) {// �ж��޸ĺ�������Ƿ�Ϊ�գ����Ϊ�����û�ȡ�����޸�
							treeNode.setUserObject(newName);// �޸Ľڵ�����
							cardTreeModel.reload();// ˢ����
							cardTree.setSelectionPath(selectionPath);// �����޸ĵĽڵ�Ϊѡ�нڵ�
							dao.uTypeNameByName("card", nowName, newName);// ���޸ĺ�����Ʊ��浽���ݿ�
						}
					}
				}
			}
		});
		URL updCardTypeUrl = this.getClass().getResource("/img/upd_tree.png");
		updCardTypeButton.setIcon(new ImageIcon(updCardTypeUrl));
		URL updCardTypeOverUrl = this.getClass().getResource(
				"/img/upd_tree_over.png");
		updCardTypeButton.setRolloverIcon(new ImageIcon(updCardTypeOverUrl));
		cardTreeButtonPanel.add(updCardTypeButton);

		final MButton delCardTypeButton = new MButton();
		delCardTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) cardTree
						.getLastSelectedPathComponent();// ���ѡ�е���Ƭ�ж���
				if (treeNode == null) {// δѡ��Ҫɾ������Ƭ��
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ������Ƭ�У�", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ������ʾ��Ϣ
					return;// ��ֱ�ӷ���
				}
				String name = treeNode.getUserObject().toString();// �����ɾ����Ƭ�е�����
				int i = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ����Ƭ�С�" + name
						+ "����", "������ʾ", JOptionPane.YES_NO_OPTION);// ����ɾ����ȷ����ʾ
				if (i != 0)// �û�ȡ����ɾ������
					return;// ֱ�ӷ���
				if (dao.sPersonnelVByTypeName(name).size() > 0) {// ����Ƭ���а�����Ƭ
					String options[] = { "ȡ��", "����������Ƭ��", "ɾ��" };// ������������Ƭ�Ĵ���ʽ
					int optionIndex = JOptionPane.showOptionDialog(null,
							"��ѡ��Ը���Ƭ������Ա�Ĵ���ʽ��", "������ʾ",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]);// ������ʾ��Ϣ�����û�ѡ���ṩ�Ĵ���ʽ
					if (optionIndex == 0)// �û�ȡ����ɾ������
						return;// ֱ�ӷ���
					int typeId = dao.sTypeIdByUsedAndName("card", name);// �����Ƭ�е�����ID
					if (optionIndex == 1) {// �û�ѡ������������Ƭ��
						Vector<Vector> cardV = dao.sTypeByUsedExcept("card",
								typeId);// ��ѯ���������Ƭ��
						String[] cards = new String[cardV.size() + 1];// ����һ��ѡ��������
						cards[0] = "��ѡ��";// ���һ����ʾѡ����
						for (int j = 0; j < cardV.size(); j++) {// ��ʼ��ѡ��������
							cards[j + 1] = cardV.get(j).get(2).toString();// ��ӿ��������Ƭ��
						}
						Object card = "��ѡ��";// Ĭ��Ϊѡ�С���ѡ��
						while (card.equals("��ѡ��")) {// ��ѡ�е�Ϊ����ѡ��ʱִ��ѭ��
							card = JOptionPane.showInputDialog(null,
									"��ѡ��Ҫ�������Ƭ�У�", "������ʾ",
									JOptionPane.INFORMATION_MESSAGE, null,
									cards, cards[0]);// �����Ի������û�ѡ�����������Ƭ��
							if (card == null)// �û�ȡ����ɾ������
								return;// ֱ�ӷ���
						}
						int newTypeId = dao.sTypeIdByUsedAndName("card", card
								.toString());// �����������Ƭ�е�����ID
						dao.uPersonnelTypeIdByTypeId(typeId, newTypeId);// �޸���Ƭ�����
					}
					if (optionIndex == 2) {// �û�ѡ��ɾ�����������Ƭ
						dao.dPersonnelByTypeId(typeId);// �����ݿ�ɾ�����������Ƭ
					}
				}
				cardTreeModel.removeNodeFromParent(treeNode);// ����Ƭ������ɾ����Ƭ��
				dao.dTypeByName("card", name);// �����ݿ���ɾ����Ƭ��
			}
		});
		URL delCardTypeUrl = this.getClass().getResource("/img/del_tree.png");
		delCardTypeButton.setIcon(new ImageIcon(delCardTypeUrl));
		URL delCardTypeOverUrl = this.getClass().getResource(
				"/img/del_tree_over.png");
		delCardTypeButton.setRolloverIcon(new ImageIcon(delCardTypeOverUrl));
		cardTreeButtonPanel.add(delCardTypeButton);

		final JPanel cardListPanel = new JPanel();
		cardSplitPane.setRightComponent(cardListPanel);
		cardListPanel.setLayout(new BorderLayout());

		final JScrollPane cardListScrollPane = new JScrollPane();
		cardListPanel.add(cardListScrollPane);

		cardListTableColumnV = new Vector<String>();
		String cardListTableColumns[] = { "���", "���", "����", "�Ա�", "��������", "��˾",
				"����", "ְ��", "�ƶ��绰", "E-mail" };
		for (int i = 0; i < cardListTableColumns.length; i++) {
			cardListTableColumnV.add(cardListTableColumns[i]);
		}

		cardListTableValueV = new Vector<Vector>();

		cardListTableModel = new DefaultTableModel(cardListTableValueV,
				cardListTableColumnV);

		cardListTable = new MTable(cardListTableModel);
		initCardListTable();
		cardListScrollPane.setViewportView(cardListTable);

		final JPanel cardButtonPanel = new JPanel();
		cardButtonPanel.setLayout(new BoxLayout(cardButtonPanel,
				BoxLayout.Y_AXIS));
		cardListPanel.add(cardButtonPanel, BorderLayout.EAST);

		final MButton selAllButton = new MButton();
		URL selAllUrl = this.getClass().getResource("/img/select_all.png");
		selAllButton.setIcon(new ImageIcon(selAllUrl));
		URL selAllOverUrl = this.getClass().getResource(
				"/img/select_all_over.png");
		selAllButton.setRolloverIcon(new ImageIcon(selAllOverUrl));
		selAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardListTable.selectAll();// ѡ�б���е�������
			}
		});
		cardButtonPanel.add(selAllButton);

		final MButton addToSendListButton = new MButton();
		URL addToSendListUrl = this.getClass().getResource(
				"/img/add_to_list.png");
		addToSendListButton.setIcon(new ImageIcon(addToSendListUrl));
		URL addToSendListOverUrl = this.getClass().getResource(
				"/img/add_to_list_over.png");
		addToSendListButton
				.setRolloverIcon(new ImageIcon(addToSendListOverUrl));
		addToSendListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowCount = sendListTableModel.getRowCount();// ��õ�ǰ�������б��е������˸���
				int[] selectedRows = cardListTable.getSelectedRows();// �����Ƭ�б��е�ѡ����
				int index = rowCount + 1;// ��ʼ���������б�����
				for (int selectedRow = 0; selectedRow < selectedRows.length; selectedRow++) {// ����ѡ����
					int newNum = (Integer) cardListTable.getValueAt(
							selectedRows[selectedRow], 1);// �����Ƭ���
					boolean had = false;// Ĭ��Ϊδ�����������б�
					for (int row = 0; row < rowCount; row++) {// �����������б�
						int nowNum = (Integer) sendListTableModel.getValueAt(
								row, 1);// ��������˵ı��
						if (newNum == nowNum) {// �ж���Ƭ��ź������˱���Ƿ���ͬ
							had = true;// �Ѿ������������б�
							break;// ����ѭ��
						}
					}
					if (!had) {// δ�����������б�
						Vector rowV = new Vector();// ����һ�����������˵�����
						rowV.add(index++);// ������
						rowV.add(newNum);// ��ӱ��
						rowV.add(cardListTable.getValueAt(
								selectedRows[selectedRow], 2));// �������
						sendListTableModel.addRow(rowV);// �����������б�
					}
				}
				cardListTable.clearSelection();// ȡ����Ƭ�б��е�ѡ����
			}
		});
		cardButtonPanel.add(addToSendListButton);

		final MButton addCardButton = new MButton();
		URL addCardUrl = this.getClass().getResource("/img/add_info.png");
		addCardButton.setIcon(new ImageIcon(addCardUrl));
		URL addCardOverUrl = this.getClass().getResource(
				"/img/add_info_over.png");
		addCardButton.setRolloverIcon(new ImageIcon(addCardOverUrl));
		addCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cardTreeRoot.getChildCount() == 0) {// �ж��Ƿ������Ƭ��
					JOptionPane.showMessageDialog(null, "���Ƚ�����Ƭ�У�", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ����������Ƭ�е���ʾ
				} else {
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) cardTree
							.getLastSelectedPathComponent();// ��õ�ǰѡ�е���Ƭ��
					String cardName = treeNode.getUserObject().toString();// �����Ƭ������
					PersonnelDialog personnelDialog = new PersonnelDialog(
							"�����Ƭ", cardName, -1);// ���������Ƭ�ĶԻ������
					personnelDialog.setVisible(true);// ���������Ƭ�ĶԻ���Ϊ�ɼ�
					initCardListTable();// ˢ����Ƭ�б�
				}
			}
		});
		cardButtonPanel.add(addCardButton);

		final MButton updCardButton = new MButton();
		URL updCardUrl = this.getClass().getResource("/img/upd_info.png");
		updCardButton.setIcon(new ImageIcon(updCardUrl));
		URL updCardOverUrl = this.getClass().getResource(
				"/img/upd_info_over.png");
		updCardButton.setRolloverIcon(new ImageIcon(updCardOverUrl));
		updCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = cardListTable.getSelectedRows();// �����Ƭ�б��е�ѡ����
				if (selectedRows.length == 1) {// ��ѡ����һ����Ƭ
					int num = (Integer) cardListTable.getValueAt(
							selectedRows[0], 1);// ���ѡ����Ƭ�ı��
					PersonnelDialog personnelDialog = new PersonnelDialog(
							"�޸���Ƭ", "", num);// �����޸���Ƭ�ĶԻ������
					personnelDialog.setVisible(true);// �����޸���Ƭ�ĶԻ���Ϊ�ɼ�
					initCardListTable();
				} else {
					if (selectedRows.length == 0) {// δѡ��Ҫ�޸ĵ���Ƭ
						JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ���Ա��",
								"������ʾ", JOptionPane.INFORMATION_MESSAGE);// ������ʾ��Ϣ
					} else {// ѡ���˶����Ƭ
						JOptionPane.showMessageDialog(null, "һ��ֻ���޸�һ����Ա��",
								"������ʾ", JOptionPane.INFORMATION_MESSAGE);// ������ʾ��Ϣ
					}
				}
			}
		});
		cardButtonPanel.add(updCardButton);

		final MButton delCardButton = new MButton();
		URL delCardUrl = this.getClass().getResource("/img/del_info.png");
		delCardButton.setIcon(new ImageIcon(delCardUrl));
		URL delCardOverUrl = this.getClass().getResource(
				"/img/del_info_over.png");
		delCardButton.setRolloverIcon(new ImageIcon(delCardOverUrl));
		delCardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selectedRows = cardListTable.getSelectedRows();// �����Ƭ�б��е�ѡ����
				if (selectedRows.length == 0) {// δѡ��Ҫɾ������Ƭ
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ������Ա��", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ������ʾ��Ϣ
				} else {
					String[] infos = new String[selectedRows.length + 1];// ��֯��ʾ��Ϣ
					infos[0] = "ȷ��Ҫɾ��������Ա��";// �����ʾ��Ϣ
					for (int i = 0; i < selectedRows.length; i++) {// ����ѡ�е���Ƭ
						infos[i + 1] = "    "
								+ cardListTable.getValueAt(selectedRows[i], 1)// �����Ƭ���
								+ "  "
								+ cardListTable.getValueAt(selectedRows[i], 2);// �����Ƭ����
					}
					int i = JOptionPane.showConfirmDialog(null, infos, "������ʾ",
							JOptionPane.YES_NO_OPTION);// ������ʾ��Ϣ
					if (i == 0) {// ȷ��ɾ��
						for (int j = 0; j < selectedRows.length; j++) {// ����ѡ�е���Ƭ
							int num = (Integer) cardListTable.getValueAt(
									selectedRows[j], 1);// �����Ƭ���
							dao.dPersonnelByNum(num);// �����ݿ�ɾ��
						}
						initCardListTable();// ˢ����Ƭ�б�
					}
				}
			}
		});
		cardButtonPanel.add(delCardButton);

		final JSplitPane infoSplitPane = new JSplitPane();
		infoSplitPane.setOneTouchExpandable(true);
		infoSplitPane.setDividerSize(12);
		infoSplitPane.setDividerLocation(244);
		tabbedPane.addTab("��Ϣ��", null, infoSplitPane, null);

		final JPanel infoTreePanel = new JPanel();
		infoSplitPane.setLeftComponent(infoTreePanel);
		infoTreePanel.setLayout(new BorderLayout());

		final JScrollPane infoTreeScrollPane = new JScrollPane();
		infoTreePanel.add(infoTreeScrollPane);

		infoTreeRoot = new DefaultMutableTreeNode("root");
		initTree(infoTreeRoot, "info");

		infoTreeModel = new DefaultTreeModel(infoTreeRoot);

		infoTree = new JTree(infoTreeModel);
		infoTree.setRootVisible(false);
		if (infoTreeRoot.getChildCount() > 0)
			infoTree.setSelectionRow(0);
		infoTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				initInfoListTable();
			}
		});
		infoTreeScrollPane.setViewportView(infoTree);

		final JPanel infoTreeButtonPanel = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(0);
		flowLayout.setHgap(0);
		infoTreeButtonPanel.setLayout(flowLayout);
		infoTreePanel.add(infoTreeButtonPanel, BorderLayout.SOUTH);

		final MButton addInfoTypeButton = new MButton();
		addInfoTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = addTreeNode(infoTreeRoot, "��Ϣ��");
				if (name != null) {
					int childCount = infoTreeRoot.getChildCount();
					infoTreeModel.insertNodeInto(new DefaultMutableTreeNode(
							name), infoTreeRoot, childCount);
					infoTreeModel.reload();
					infoTree.setSelectionRow(childCount);
					dao.iType(name, "info");
				}
			}
		});
		URL creInfoTypeUrl = this.getClass().getResource("/img/add_tree.png");
		addInfoTypeButton.setIcon(new ImageIcon(creInfoTypeUrl));
		URL creInfoTypeOverUrl = this.getClass().getResource(
				"/img/add_tree_over.png");
		addInfoTypeButton.setRolloverIcon(new ImageIcon(creInfoTypeOverUrl));
		infoTreeButtonPanel.add(addInfoTypeButton);

		final MButton updInfoTypeButton = new MButton();
		updInfoTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TreePath selectionPath = infoTree.getSelectionPath();
				if (selectionPath == null) {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ���Ϣ�⣡", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) selectionPath
							.getLastPathComponent();
					String nowName = treeNode.getUserObject().toString();
					int i = JOptionPane.showConfirmDialog(null, "ȷ��Ҫ�޸���Ϣ�⡰"
							+ nowName + "�������ƣ�", "������ʾ",
							JOptionPane.YES_NO_OPTION);
					if (i == 0) {
						String newName = updateTreeNode(treeNode);
						if (newName != null) {
							treeNode.setUserObject(newName);
							infoTreeModel.reload();
							infoTree.setSelectionPath(selectionPath);
							dao.uTypeNameByName("info", nowName, newName);
						}
					}
				}
			}
		});
		URL updInfoTypeUrl = this.getClass().getResource("/img/upd_tree.png");
		updInfoTypeButton.setIcon(new ImageIcon(updInfoTypeUrl));
		URL updInfoTypeOverUrl = this.getClass().getResource(
				"/img/upd_tree_over.png");
		updInfoTypeButton.setRolloverIcon(new ImageIcon(updInfoTypeOverUrl));
		infoTreeButtonPanel.add(updInfoTypeButton);

		final MButton delInfoTypeButton = new MButton();
		delInfoTypeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) infoTree
						.getLastSelectedPathComponent();
				if (treeNode == null) {// δѡ��Ҫɾ������Ϣ��
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ������Ϣ�⣡", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String name = treeNode.getUserObject().toString();
				int i = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ����Ϣ�⡰" + name
						+ "����", "������ʾ", JOptionPane.YES_NO_OPTION);
				if (i != 0)// �û�ȡ����ɾ������
					return;
				if (dao.sInfoVByTypeName(name).size() > 0) {
					String options[] = { "ȡ��", "����������Ϣ��", "ɾ��" };
					int optionIndex = JOptionPane.showOptionDialog(null,
							"��ѡ��Ը���Ϣ������Ϣ�Ĵ���ʽ��", "������ʾ",
							JOptionPane.YES_NO_CANCEL_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options,
							options[0]);
					int typeId = dao.sTypeIdByUsedAndName("info", name);
					if (optionIndex == 0)// �û�ȡ����ɾ������
						return;
					if (optionIndex == 1) {// ����������Ϣ��
						Vector infoV = dao.sTypeByUsedExcept("info", typeId);
						String[] infos = new String[infoV.size() + 1];
						infos[0] = "��ѡ��";
						for (int j = 0; j < infoV.size(); j++) {
							infos[j + 1] = ((Vector) infoV.get(j)).get(2)
									.toString();
						}
						Object info = "��ѡ��";
						while (info.equals("��ѡ��")) {
							info = JOptionPane.showInputDialog(null,
									"��ѡ��Ҫ�������Ϣ�⣺", "������ʾ",
									JOptionPane.INFORMATION_MESSAGE, null,
									infos, infos[0]);
							if (info == null)// �û�ȡ����ɾ������
								return;
						}
						int newTypeId = dao.sTypeIdByUsedAndName("info", info
								.toString());
						dao.uPersonnelTypeIdByTypeId(typeId, newTypeId);
					}
					if (optionIndex == 2) {// ɾ��
						dao.dPersonnelByTypeId(typeId);
					}
				}
				DefaultMutableTreeNode selectedNode = treeNode.getNextNode();
				if (selectedNode == null)
					selectedNode = treeNode.getPreviousNode();
				infoTreeModel.removeNodeFromParent(treeNode);
				infoTree.setSelectionRow(selectedNode.getDepth());
				dao.dTypeByName("info", name);
			}
		});
		URL delInfoTypeUrl = this.getClass().getResource("/img/del_tree.png");
		delInfoTypeButton.setIcon(new ImageIcon(delInfoTypeUrl));
		URL delInfoTypeOverUrl = this.getClass().getResource(
				"/img/del_tree_over.png");
		delInfoTypeButton.setRolloverIcon(new ImageIcon(delInfoTypeOverUrl));
		infoTreeButtonPanel.add(delInfoTypeButton);

		final JPanel infoListPanel = new JPanel();
		infoSplitPane.setRightComponent(infoListPanel);
		infoListPanel.setLayout(new BorderLayout());

		final JScrollPane infoListScrollPane = new JScrollPane();
		infoListPanel.add(infoListScrollPane);

		infoListTableColumnV = new Vector<String>();
		infoListTableColumnV.add("���");
		infoListTableColumnV.add("���");
		infoListTableColumnV.add("��Ϣ����");

		infoListTableValueV = new Vector<Vector>();

		infoListTableModel = new DefaultTableModel(infoListTableValueV,
				infoListTableColumnV);

		infoListTable = new MTable(infoListTableModel);
		infoListTable.getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);
		initInfoListTable();
		infoListScrollPane.setViewportView(infoListTable);

		final JPanel infoButtonPanel = new JPanel();
		infoButtonPanel.setLayout(new BoxLayout(infoButtonPanel,
				BoxLayout.Y_AXIS));
		infoListPanel.add(infoButtonPanel, BorderLayout.EAST);

		final MButton addToSendInfoButton = new MButton();
		URL addToSendInfoUrl = this.getClass().getResource(
				"/img/add_to_info.png");
		addToSendInfoButton.setIcon(new ImageIcon(addToSendInfoUrl));
		URL addToSendInfoOverUrl = this.getClass().getResource(
				"/img/add_to_info_over.png");
		addToSendInfoButton
				.setRolloverIcon(new ImageIcon(addToSendInfoOverUrl));
		addToSendInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = infoListTable.getSelectedRow();// �����Ϣ�б��ѡ����
				if (selectedRow < 0) {// δѡ���κ���
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�༭����Ϣ��", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);// ������ʾ��Ϣ
				} else {
					String info = infoListTable.getValueAt(selectedRow, 2)
							.toString();// �����Ϣ����
					if (infoTabbedPane.getSelectedIndex() == 0)// ��ǰ��ѡ�е��ǡ��������ݡ����
						infoTextArea.setText(info);// �����Ϣ�����������ı�����
					else
						// ��ǰ��ѡ�е��ǡ�E-mail���ݡ����
						emailTextArea.setText(info);// �����Ϣ��E-mail�����ı�����
				}
			}
		});
		infoButtonPanel.add(addToSendInfoButton);

		final MButton addInfoButton = new MButton();
		URL addInfoUrl = this.getClass().getResource("/img/add_info.png");
		addInfoButton.setIcon(new ImageIcon(addInfoUrl));
		URL addInfoOverUrl = this.getClass().getResource(
				"/img/add_info_over.png");
		addInfoButton.setRolloverIcon(new ImageIcon(addInfoOverUrl));
		addInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) infoTree
						.getLastSelectedPathComponent();
				InfoDialog infoDialog = new InfoDialog("�����Ϣ", treeNode
						.getUserObject().toString(), -1, null);
				infoDialog.setVisible(true);
				initInfoListTable();
			}
		});
		infoButtonPanel.add(addInfoButton);

		final MButton updInfoButton = new MButton();
		URL updInfoUrl = this.getClass().getResource("/img/upd_info.png");
		updInfoButton.setIcon(new ImageIcon(updInfoUrl));
		URL updInfoOverUrl = this.getClass().getResource(
				"/img/upd_info_over.png");
		updInfoButton.setRolloverIcon(new ImageIcon(updInfoOverUrl));
		updInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = infoListTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ���Ϣ��", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					DefaultMutableTreeNode treeNode = (DefaultMutableTreeNode) infoTree
							.getLastSelectedPathComponent();
					int num = (Integer) infoListTable
							.getValueAt(selectedRow, 1);
					String content = infoListTable.getValueAt(selectedRow, 2)
							.toString();
					InfoDialog infoDialog = new InfoDialog("�޸���Ϣ", treeNode
							.getUserObject().toString(), num, content);
					infoDialog.setVisible(true);
					initInfoListTable();
				}
			}
		});
		infoButtonPanel.add(updInfoButton);

		final MButton delInfoButton = new MButton();
		URL delInfoUrl = this.getClass().getResource("/img/del_info.png");
		delInfoButton.setIcon(new ImageIcon(delInfoUrl));
		URL delInfoOverUrl = this.getClass().getResource(
				"/img/del_info_over.png");
		delInfoButton.setRolloverIcon(new ImageIcon(delInfoOverUrl));
		delInfoButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int selectedRow = infoListTable.getSelectedRow();
				if (selectedRow < 0) {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ������Ϣ��", "������ʾ",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					int num = (Integer) infoListTable
							.getValueAt(selectedRow, 1);
					int i = JOptionPane.showConfirmDialog(null, "ȷ��Ҫɾ����Ϣ" + num
							+ "��", "������ʾ", JOptionPane.YES_NO_OPTION);
					if (i == 0) {
						dao.dInfoByNum(num);
						initInfoListTable();
					}
				}
			}
		});
		infoButtonPanel.add(delInfoButton);
		//
	}

	private void initTree(DefaultMutableTreeNode treeRoot, String used) {// ��ʼ�����ķ���
		Vector typeV = dao.sTypeByUsed(used);// ��ѯ����ָ����������
		for (int i = 0; i < typeV.size(); i++) {// ��������
			Vector type = (Vector) typeV.get(i);// �����������
			treeRoot.add(new DefaultMutableTreeNode(type.get(2)));// ��������ӵ�����
		}
	}

	private void initCardListTable() {
		cardListTableValueV.removeAllElements();// �����Ƭ�б�
		DefaultMutableTreeNode cardTreeNode = (DefaultMutableTreeNode) cardTree
				.getLastSelectedPathComponent();// �����Ƭ������ѡ�нڵ����
		if (cardTreeNode != null) {// �ж��Ƿ����ѡ�еĽڵ�
			String cardName = cardTreeNode.getUserObject().toString();// ���ѡ����Ƭ�е�����
			cardListTableValueV.addAll(dao.sPersonnelVByTypeName(cardName));// ������Ƭ�а�������Ƭ
		}
		cardListTableModel.setDataVector(cardListTableValueV,
				cardListTableColumnV);// ˢ����Ƭ�б���ģ��
	}

	private void initInfoListTable() {
		infoListTableValueV.removeAllElements();
		DefaultMutableTreeNode infoTreeNode = (DefaultMutableTreeNode) infoTree
				.getLastSelectedPathComponent();
		if (infoTreeNode != null) {
			String infoName = infoTreeNode.getUserObject().toString();
			infoListTableValueV.addAll(dao.sInfoVByTypeName(infoName));
		}
		infoListTableModel.setDataVector(infoListTableValueV,
				infoListTableColumnV);
	}

	private boolean isHad(DefaultMutableTreeNode treeNode, String newChildName) {// ��֤�������Ƿ��Ѿ�����
		boolean had = false;// Ĭ��Ϊ������
		int childCount = treeNode.getChildCount();// ����ӽڵ������
		for (int i = 0; i < childCount; i++) {// �����ӽڵ�
			DefaultMutableTreeNode childTreeNode = (DefaultMutableTreeNode) treeNode
					.getChildAt(i);// ����ӽڵ����
			if (childTreeNode.getUserObject().toString().equals(newChildName)) {// �ж������Ƿ���ͬ
				JOptionPane.showMessageDialog(null, "�������Ѿ����ڣ�", "������ʾ",
						JOptionPane.INFORMATION_MESSAGE);// �����������Ѿ����ڵ���ʾ
				had = true;// �������Ѿ�����
				break;// ����ѭ����ֹͣ����������ӽڵ�
			}
		}
		return had;// ���ؽ��
	}

	private String addTreeNode(DefaultMutableTreeNode treeNode, String typeName) {
		String nodeName = "";// �����ڵ�����Ϊ���ַ���
		while (nodeName.length() == 0) {// �жϽڵ����Ƶĳ����Ƿ�Ϊ0
			nodeName = JOptionPane.showInputDialog(null, "������" + typeName
					+ "���ƣ�", "�½�" + typeName, JOptionPane.INFORMATION_MESSAGE);// ������������û���������
			if (nodeName == null) {// �жϽڵ������Ƿ�Ϊ��ֵ
				break;// Ϊ��ֵ���û�ȡ�����½���������ѭ��
			} else {
				nodeName = nodeName.trim();// ȥ����β�ո�
				if (nodeName.length() > 0) {// �жϽڵ����Ƶĳ����Ƿ�Ϊ0
					if (isHad(treeNode, nodeName))// �����Ϊ0���жϸ������Ƿ��Ѿ�����
						nodeName = "";// ������������ýڵ�����Ϊ���ַ���
				}
			}
		}
		return nodeName;// ���ؽڵ�����
	}

	private String updateTreeNode(DefaultMutableTreeNode treeNode) {
		DefaultMutableTreeNode parentNode = (DefaultMutableTreeNode) treeNode
				.getParent();// ������޸Ľڵ�ĸ��ڵ�
		String newNodeName = ""; // �����ڵ�����Ϊ���ַ���
		while (newNodeName.length() == 0) { // �жϽڵ����Ƶĳ����Ƿ�Ϊ0
			newNodeName = JOptionPane.showInputDialog(null, "�����������ƣ�", "�޸�����",
					JOptionPane.INFORMATION_MESSAGE);// �����û���������
			if (newNodeName == null) { // �жϽڵ������Ƿ�Ϊ��ֵ
				break; // Ϊ��ֵ���û�ȡ�����޸ģ�������ѭ��
			} else {
				newNodeName = newNodeName.trim(); // ȥ����β�ո�
				if (newNodeName.length() > 0) {// �жϽڵ����Ƶĳ����Ƿ�Ϊ0
					if (isHad(parentNode, newNodeName))// �����Ϊ0���жϸ������Ƿ��Ѿ�����
						newNodeName = ""; // ������������ýڵ�����Ϊ���ַ���
				}
			}
		}
		return newNodeName; // ���ؽڵ�����
	}

}
