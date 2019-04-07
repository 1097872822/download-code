package com.mwq.dao;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

import BestMail.smssend;

import com.mwq.form.SendLetterForm;
public class SendLetterDAO {
	private smssend smssender = null;
	// ���Ͷ���
	private final Preferences perf = Preferences.userRoot();
	private String device = perf.get("device", "COM1");
	private String baud = perf.get("baud", "9600");
	private String sn = perf.get("sn", "YIWU-IJDD-****-****");
	public String sendLetter(SendLetterForm form) {
		String ret = "";
		String info = "";
		String sendnum = "";
		String flag = "";

		try {
			info = form.getContent();
			sendnum = form.getToMan();
			System.out.println("SN:" + sn + "***********" + info);
			flag = mySend(device, baud, sn, info, sendnum);// ���Ͷ���
			if (flag.equals("ok")) {
			} else {
				ret = flag;
			}
		} catch (Exception e) {
			System.out.println("���Ͷ��Ų����Ĵ���" + e.getMessage());
			ret = "���Ͷ���ʧ�ܣ�";
		}
		return ret;
	}

	// ��ʼ��GSM Modem�豸
	public boolean getConnectionModem(String device, String baud, String sn) {
		smssender = new smssend();
		boolean connection = true;
		if (!smssender.GSMModemInitNew(device, baud, null, "GSM", false, sn)) {
			JOptionPane.showMessageDialog(null, "��ʼ��GSM Modem �豸ʧ�ܣ�"
					+ smssender.GSMModemGetErrorMsg());
			connection = false;
		}
		return connection;
	}
	// �����ֻ����ŵķ���
	private String mySend(String device, String baud, String sn, String info,
			String sendnum) {
		boolean flag = false;
		String rtn = "";
		flag = this.getConnectionModem(device, baud, sn);
		if (flag) {
			byte[] sendtest = smssender.getUNIByteArray(info); // ת��ΪUNICOCE
			// ʵ��Ⱥ��
			String[] arrSendnum = sendnum.split(",");
			for (int i = 0; i < arrSendnum.length; i++) {
				if (!smssender.GSMModemSMSsend(null, 8, sendtest,
						arrSendnum[i], false)) {
					System.out.println("���Ͷ���ʧ�ܣ�"
							+ smssender.GSMModemGetErrorMsg());
					rtn = rtn + "��" + arrSendnum[i] + "���Ͷ���ʧ��!<br>ԭ���ǣ�"
							+ smssender.GSMModemGetErrorMsg() + "<br>";
				}
			}
		} else {
			rtn = "��ʼ��GSM Modem�豸ʧ�ܣ�";
		}
		if (rtn.equals("")) {
			rtn = "ok";
		}
		closeConnection(); // �ر�����
		return rtn;
	}
	// �ر����ӵķ���
	public void closeConnection() {
		if (smssender != null) {
			smssender.GSMModemRelease();
			System.out.println("�رճɹ�������");
		}
	}
}
