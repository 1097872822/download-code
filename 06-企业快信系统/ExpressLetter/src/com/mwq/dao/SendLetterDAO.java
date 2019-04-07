package com.mwq.dao;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;

import BestMail.smssend;

import com.mwq.form.SendLetterForm;
public class SendLetterDAO {
	private smssend smssender = null;
	// 发送短信
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
			flag = mySend(device, baud, sn, info, sendnum);// 发送短信
			if (flag.equals("ok")) {
			} else {
				ret = flag;
			}
		} catch (Exception e) {
			System.out.println("发送短信产生的错误：" + e.getMessage());
			ret = "发送短信失败！";
		}
		return ret;
	}

	// 初始化GSM Modem设备
	public boolean getConnectionModem(String device, String baud, String sn) {
		smssender = new smssend();
		boolean connection = true;
		if (!smssender.GSMModemInitNew(device, baud, null, "GSM", false, sn)) {
			JOptionPane.showMessageDialog(null, "初始化GSM Modem 设备失败："
					+ smssender.GSMModemGetErrorMsg());
			connection = false;
		}
		return connection;
	}
	// 发送手机短信的方法
	private String mySend(String device, String baud, String sn, String info,
			String sendnum) {
		boolean flag = false;
		String rtn = "";
		flag = this.getConnectionModem(device, baud, sn);
		if (flag) {
			byte[] sendtest = smssender.getUNIByteArray(info); // 转化为UNICOCE
			// 实现群发
			String[] arrSendnum = sendnum.split(",");
			for (int i = 0; i < arrSendnum.length; i++) {
				if (!smssender.GSMModemSMSsend(null, 8, sendtest,
						arrSendnum[i], false)) {
					System.out.println("发送短信失败："
							+ smssender.GSMModemGetErrorMsg());
					rtn = rtn + "向" + arrSendnum[i] + "发送短信失败!<br>原因是："
							+ smssender.GSMModemGetErrorMsg() + "<br>";
				}
			}
		} else {
			rtn = "初始化GSM Modem设备失败！";
		}
		if (rtn.equals("")) {
			rtn = "ok";
		}
		closeConnection(); // 关闭连接
		return rtn;
	}
	// 关闭连接的方法
	public void closeConnection() {
		if (smssender != null) {
			smssender.GSMModemRelease();
			System.out.println("关闭成功！！！");
		}
	}
}
