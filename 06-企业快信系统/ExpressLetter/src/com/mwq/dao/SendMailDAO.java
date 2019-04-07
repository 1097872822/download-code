package com.mwq.dao;

import java.util.Properties;
import java.util.prefs.Preferences;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.mwq.form.SendMailForm;

public class SendMailDAO {

	// 发送邮件
	private final Preferences perf=Preferences.userRoot();
	public int sendMail(SendMailForm form) {
		int ret = 0;
		String from =perf.get("mailName","");
		String to = form.getAddressee();
		String subject = form.getTitle();
		String content = form.getContent();
		String password = perf.get("password","");
		String path = form.getAdjunct();
		try {
			//String mailserver ="smtp."+to.substring(to.indexOf('@')+1,to.length());	//在Internet上发送邮件时的代码
			String mailserver = perf.get("SMTPserver", "");									//在局域网内发送邮件时的代码

			Properties prop = new Properties();
			prop.put("mail.smtp.host", mailserver);
			prop.put("mail.smtp.auth", "true");
			Session sess = Session.getDefaultInstance(prop);
			sess.setDebug(true);
			MimeMessage message = new MimeMessage(sess);
			message.setFrom(new InternetAddress(from));	// 给消息对象设置发件人
			//设置收件人
			String toArr[]=to.split(",");
			InternetAddress[] to_mail=new InternetAddress[toArr.length];
			for(int i=0;i<toArr.length;i++){
				to_mail[i]=new InternetAddress(toArr[i]);
			}
		    message.setRecipients(Message.RecipientType.BCC,to_mail);
			//设置主题
			message.setSubject(subject);
			Multipart mul = new MimeMultipart(); // 新建一个MimeMultipart对象来存放多个BodyPart对象
			BodyPart mdp = new MimeBodyPart(); // 新建一个存放信件内容的BodyPart对象
			mdp.setContent(content, "text/html;charset=gb2312");
			mul.addBodyPart(mdp); // 将含有信件内容的BodyPart加入到MimeMulitipart对象中
			if (path != null && !path.isEmpty() ){	//当存在附件时
				// 设置信件的附件（用本机上的文件作为附件）
				mdp = new MimeBodyPart(); // 新建一个存放附件的BodyPart
				String adjunctname = new String(path.getBytes("GBK"), "ISO-8859-1"); // 此处需要转码，否则附件中包括中文时，将产生乱码
				FileDataSource fds = new FileDataSource(path);
				DataHandler handler = new DataHandler(fds);
				mdp.setFileName(adjunctname);
				mdp.setDataHandler(handler);
				mul.addBodyPart(mdp);
			}
			message.setContent(mul); // 把mul作为消息对象的内容
			message.saveChanges();
			Transport transport = sess.getTransport("smtp");
			// 以smtp方式登录邮箱，第1个参数是发送邮件用的邮件服务器SMTP地址，第2个参数为用户名，第3个参数为密码
			transport.connect(mailserver, from, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			ret = 1;
		} catch (Exception e) {
			System.out.println("发送邮件产生的错误：" + e.getMessage());
			ret = 0;
		}
		return ret;
	}
}
