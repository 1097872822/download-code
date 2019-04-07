package com.mwq.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.Vector;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.mwq.dao.Dao;
import com.mwq.dao.SendLetterDAO;
import com.mwq.dao.SendMailDAO;
import com.mwq.form.SendLetterForm;
import com.mwq.form.SendMailForm;
import com.mwq.frame.mwing.MButton;
import com.mwq.frame.mwing.MTable;

public class InfoPanel extends JPanel {

	private JTabbedPane tabbedPane;

	private JTextArea emailTextArea;

	private JTextArea infoTextArea;

	private MTable sendListTable;

	private Dao dao = Dao.getInstance();

	private SendMailForm mailForm;

	private SendMailDAO mailDao;

	private final JTextField titleField = new JTextField();

	private final JPanel panel = new JPanel();

	private JLabel annexLabel;

	public InfoPanel(MTable sendListTable) {
		super();
		this.sendListTable = sendListTable;
		mailDao = new SendMailDAO();
		mailForm = new SendMailForm();
		setBorder(new TitledBorder("信息内容"));
		setLayout(new BorderLayout());

		tabbedPane = new JTabbedPane();
		add(tabbedPane, BorderLayout.CENTER);

		final JPanel infoPanel = new JPanel();
		infoPanel.setLayout(new BorderLayout());
		tabbedPane.addTab("短信内容", null, infoPanel, null);

		final JScrollPane infoScrollPane = new JScrollPane();
		infoPanel.add(infoScrollPane, BorderLayout.CENTER);

		infoTextArea = new JTextArea();
		infoTextArea.setLineWrap(true);
		infoScrollPane.setViewportView(infoTextArea);

		final JPanel infoButtonPanel = new JPanel();
		infoButtonPanel.setLayout(new BoxLayout(infoButtonPanel,
				BoxLayout.Y_AXIS));
		infoPanel.add(infoButtonPanel, BorderLayout.EAST);

		final MButton infoSendButton = new MButton();
		infoSendButton.addActionListener(new InfoSendButtonActionListener());
		URL sendUrl = this.getClass().getResource("/img/send.png");
		infoSendButton.setIcon(new ImageIcon(sendUrl));
		URL sendOverUrl = this.getClass().getResource("/img/send_over.png");
		infoSendButton.setRolloverIcon(new ImageIcon(sendOverUrl));
		infoButtonPanel.add(infoSendButton);

		final MButton infoCancelButton = new MButton();
		infoCancelButton
				.addActionListener(new InfoCancelButtonActionListener());
		URL cancelUrl = this.getClass().getResource("/img/cancel.png");
		infoCancelButton.setIcon(new ImageIcon(cancelUrl));
		URL cancelOverUrl = this.getClass().getResource("/img/cancel_over.png");
		infoCancelButton.setRolloverIcon(new ImageIcon(cancelOverUrl));
		infoButtonPanel.add(infoCancelButton);

		final JPanel emailPanel = new JPanel();
		final BorderLayout borderLayout_2 = new BorderLayout();
		borderLayout_2.setHgap(10);
		emailPanel.setLayout(borderLayout_2);
		tabbedPane.addTab("E-mail内容", null, emailPanel, null);

		final JPanel emailInfoPanel = new JPanel();
		final BorderLayout borderLayout_1 = new BorderLayout();
		borderLayout_1.setVgap(5);
		emailInfoPanel.setLayout(borderLayout_1);
		emailPanel.add(emailInfoPanel);

		final JScrollPane emailScrollPane = new JScrollPane();
		emailInfoPanel.add(emailScrollPane);

		emailTextArea = new JTextArea();
		emailTextArea.setLineWrap(true);
		emailScrollPane.setViewportView(emailTextArea);

		final BorderLayout borderLayout = new BorderLayout();
		borderLayout.setHgap(10);
		borderLayout.setVgap(10);
		panel.setLayout(borderLayout);
		emailInfoPanel.add(panel, BorderLayout.NORTH);

		panel.add(new JLabel(" 邮件标题："), BorderLayout.WEST);
		panel.add(titleField);
		titleField.setMargin(new Insets(2, 2, 2, 2));

		final JLabel label = new JLabel();
		label.setPreferredSize(new Dimension(5, 0));
		panel.add(label, BorderLayout.NORTH);

		final JPanel annexPanel = new JPanel();
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		annexPanel.setLayout(flowLayout);
		emailInfoPanel.add(annexPanel, BorderLayout.SOUTH);

		annexLabel = new JLabel();
		annexLabel.setText("附    件：");
		annexPanel.add(annexLabel);

		final JPanel emailButtonPanel = new JPanel();
		emailButtonPanel.setLayout(new BoxLayout(emailButtonPanel,
				BoxLayout.Y_AXIS));
		emailPanel.add(emailButtonPanel, BorderLayout.EAST);

		final MButton emailSendButton = new MButton();
		emailSendButton.addActionListener(new EmailSendButtonActionListener());
		URL emailSendUrl = this.getClass().getResource("/img/send.png");
		emailSendButton.setIcon(new ImageIcon(emailSendUrl));
		URL emailSendOverUrl = this.getClass()
				.getResource("/img/send_over.png");
		emailSendButton.setRolloverIcon(new ImageIcon(emailSendOverUrl));
		emailButtonPanel.add(emailSendButton);

		final MButton emailCancelButton = new MButton();
		emailCancelButton
				.addActionListener(new EmailCancelButtonActionListener());
		URL emailCancelUrl = this.getClass().getResource("/img/cancel.png");
		emailCancelButton.setIcon(new ImageIcon(emailCancelUrl));
		URL emailCancelOverUrl = this.getClass().getResource(
				"/img/cancel_over.png");
		emailCancelButton.setRolloverIcon(new ImageIcon(emailCancelOverUrl));
		emailButtonPanel.add(emailCancelButton);

		final MButton addAnnexButton = new MButton();
		addAnnexButton.addActionListener(new AddAnnexButtonActionListener());
		URL addAnnexUrl = this.getClass().getResource("/img/add_annex.png");
		addAnnexButton.setIcon(new ImageIcon(addAnnexUrl));
		URL addAnnexOverUrl = this.getClass().getResource(
				"/img/add_annex_over.png");
		addAnnexButton.setRolloverIcon(new ImageIcon(addAnnexOverUrl));
		emailButtonPanel.add(addAnnexButton);

		final MButton cancelAnnexButton = new MButton();
		cancelAnnexButton
				.addActionListener(new CancelAnnexButtonActionListener());
		URL delAnnexUrl = this.getClass().getResource("/img/del_annex.png");
		cancelAnnexButton.setIcon(new ImageIcon(delAnnexUrl));
		URL delAnnexOverUrl = this.getClass().getResource(
				"/img/del_annex_over.png");
		cancelAnnexButton.setRolloverIcon(new ImageIcon(delAnnexOverUrl));
		emailButtonPanel.add(cancelAnnexButton);

		//
	}

	private class InfoCancelButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			infoTextArea.setText(null);
		}
	}

	private class InfoSendButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int sendCount = sendListTable.getRowCount();
			for (int i = 0; i < sendCount; i++) {
				Integer id = (Integer) sendListTable.getValueAt(i, 1);
				Vector person = dao.sPersonnelByNum(id);
				String phone = person.get(8).toString();
				String info = infoTextArea.getText();
				SendLetterForm form = new SendLetterForm();
				form.setContent(info);
				form.setToMan(phone);
				SendLetterDAO sendDao = new SendLetterDAO();
				String message = sendDao.sendLetter(form);
				JOptionPane.showMessageDialog(InfoPanel.this, message);
			}
		}
	}

	private class EmailSendButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String title = titleField.getText();
			String content = emailTextArea.getText();
			if (title.isEmpty() || content.isEmpty()) {
				JOptionPane.showMessageDialog(InfoPanel.this, "请填写邮件标题和内容");
				return;
			}
			int sendCount = sendListTable.getRowCount();
			for (int i = 0; i < sendCount; i++) {
				Integer id = (Integer) sendListTable.getValueAt(i, 1);
				Vector person = dao.sPersonnelByNum(id);
				String addressee = person.get(9).toString();
				mailForm.setTitle(title);
				mailForm.setContent(content);
				if (mailForm.getAddressee() == null
						|| mailForm.getAddressee().isEmpty())
					mailForm.setAddressee(addressee);
				else
					mailForm.setAddressee(mailForm.getAddressee() + ','
							+ addressee);
			}
			int res = mailDao.sendMail(mailForm);
			String message = null;
			if (res == 1)
				message += "E-mail群体发送成功";
			else
				message += "E-mail发送失败";
			JOptionPane.showMessageDialog(InfoPanel.this, message);
			mailForm = new SendMailForm();
		}
	}

	private class AddAnnexButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(InfoPanel.this);
			File sFile = fc.getSelectedFile();
			if (sFile == null)
				return;
			mailForm.setAdjunct(sFile.getAbsolutePath());
			annexLabel.setText("附件：" + sFile.getName());
		}
	}

	private class CancelAnnexButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			mailForm.setAdjunct("");
			annexLabel.setText("附件：");
		}
	}

	private class EmailCancelButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			emailTextArea.setText(null);
		}
	}

	public JTextArea getEmailTextArea() {
		return emailTextArea;
	}

	public JTextArea getInfoTextArea() {
		return infoTextArea;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

}
