package com.mwq.frame.system;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.mwq.hibernate.Dao;

public class AddAccountItemDialog extends JDialog {

	private JComboBox timecardComboBox;

	private JComboBox unitComboBox;

	private JComboBox typeComboBox;

	private JTextField nameTextField;

	private Vector vector;

	private final Dao dao = Dao.getInstance();

	/**
	 * Launch the application
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			AddAccountItemDialog dialog = new AddAccountItemDialog(true, "");
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
	public AddAccountItemDialog(final boolean isTimecard, final String item) {
		super();
		getContentPane().setLayout(new GridBagLayout());
		setTitle("添加" + item);
		setModal(true);
		setResizable(false);
		setBounds(100, 100, 300, 230);

		final JLabel nameLabel = new JLabel();
		nameLabel.setText("项目名称：");
		final GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridy = 0;
		gridBagConstraints.gridx = 0;
		getContentPane().add(nameLabel, gridBagConstraints);

		nameTextField = new JTextField();
		nameTextField.setColumns(11);
		final GridBagConstraints gridBagConstraints_4 = new GridBagConstraints();
		gridBagConstraints_4.gridy = 0;
		gridBagConstraints_4.gridx = 1;
		getContentPane().add(nameTextField, gridBagConstraints_4);

		final JLabel typeLabel = new JLabel();
		typeLabel.setText("项目类型：");
		final GridBagConstraints gridBagConstraints_1 = new GridBagConstraints();
		gridBagConstraints_1.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_1.gridy = 1;
		gridBagConstraints_1.gridx = 0;
		getContentPane().add(typeLabel, gridBagConstraints_1);

		typeComboBox = new JComboBox();
		typeComboBox.addItem("请选择");
		typeComboBox.addItem("发放");
		typeComboBox.addItem("扣除");
		final GridBagConstraints gridBagConstraints_5 = new GridBagConstraints();
		gridBagConstraints_5.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_5.gridy = 1;
		gridBagConstraints_5.gridx = 1;
		getContentPane().add(typeComboBox, gridBagConstraints_5);

		final JLabel unitLabel = new JLabel();
		unitLabel.setText("项目单位：");
		final GridBagConstraints gridBagConstraints_2 = new GridBagConstraints();
		gridBagConstraints_2.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_2.gridy = 2;
		gridBagConstraints_2.gridx = 0;
		getContentPane().add(unitLabel, gridBagConstraints_2);

		unitComboBox = new JComboBox();
		unitComboBox.addItem("请选择");
		unitComboBox.addItem("次");
		unitComboBox.addItem("天");
		unitComboBox.addItem("月");
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_6.gridy = 2;
		gridBagConstraints_6.gridx = 1;
		getContentPane().add(unitComboBox, gridBagConstraints_6);

		if (isTimecard) {
			final JLabel timecardLabel = new JLabel();
			timecardLabel.setText("用于考勤：");
			final GridBagConstraints gridBagConstraints_3 = new GridBagConstraints();
			gridBagConstraints_3.insets = new Insets(10, 0, 0, 0);
			gridBagConstraints_3.gridy = 3;
			gridBagConstraints_3.gridx = 0;
			getContentPane().add(timecardLabel, gridBagConstraints_3);

			timecardComboBox = new JComboBox();
			timecardComboBox.addItem("请选择");
			timecardComboBox.addItem("是");
			timecardComboBox.addItem("否");
			final GridBagConstraints gridBagConstraints_7 = new GridBagConstraints();
			gridBagConstraints_7.insets = new Insets(10, 0, 0, 0);
			gridBagConstraints_7.gridy = 3;
			gridBagConstraints_7.gridx = 1;
			getContentPane().add(timecardComboBox, gridBagConstraints_7);
		}

		final JPanel panel = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel.setLayout(flowLayout);
		final GridBagConstraints gridBagConstraints_8 = new GridBagConstraints();
		gridBagConstraints_8.ipadx = 25;
		gridBagConstraints_8.gridwidth = 2;
		gridBagConstraints_8.insets = new Insets(10, 0, 0, 0);
		gridBagConstraints_8.gridy = 4;
		gridBagConstraints_8.gridx = 0;
		getContentPane().add(panel, gridBagConstraints_8);

		final JButton submitButton = new JButton();
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameTextField.getText();
				if (name.length() == 0 || name.length() > 5) {
					JOptionPane.showMessageDialog(null,
							"项目名称不允许为空，并且最多只能为5个汉字！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String type = typeComboBox.getSelectedItem().toString();
				if (type.equals("请选择")) {
					JOptionPane.showMessageDialog(null, "请选择项目类型！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				String unit = unitComboBox.getSelectedItem().toString();
				if (unit.equals("请选择")) {
					JOptionPane.showMessageDialog(null, "请选择项目单位！", "友情提示",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Object object = dao.queryAccountItemByNameUnit(name, unit);
				if (object != null) {
					JOptionPane.showMessageDialog(null, "您要添加的" + item
							+ "已经存在！", "友情提示", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				vector = new Vector();
				vector.add(name);
				vector.add(type);
				vector.add(unit);
				if (isTimecard) {
					String timecard = timecardComboBox.getSelectedItem()
							.toString();
					if (timecard.equals("请选择")) {
						JOptionPane.showMessageDialog(null, "请选择该项目是否用于考勤！",
								"友情提示", JOptionPane.INFORMATION_MESSAGE);
					}
					vector.add(timecardComboBox.getSelectedItem());
				} else {
					vector.add("是");
				}
				setVisible(false);
			}
		});
		submitButton.setText("确定");
		panel.add(submitButton);

		final JButton exitButton = new JButton();
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		exitButton.setText("退出");
		panel.add(exitButton);
		//
	}

	public Vector getVector() {
		return vector;
	}

}
