package com.taiwan.utils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import javax.mail.internet.AddressException;

public class MailQrCode11 {
	
	public void SendMail(String customer, String subject, String msgtxt, String qrcodePath) {
		//連線設定
		Properties prop = new Properties();
		//設定連線方式為smtp
		prop.setProperty("mail.transport.protocol", "smtp");
		//host : smpt.gmail.com
		prop.setProperty("mail.host", "smtp.gmail.com");
		//host port 465
		prop.put("mail.smtp.port", "465");
		//寄件者帳號需要驗證嗎
		prop.put("mail.smtp.auth", "true");
		//需要安全資料傳輸層(SSL):要
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		//SSL port 465
		prop.put("mail.smtp.socketFactory.port", "465");
		
		//帳號密碼不要改
		final String userName = "cga101g4@gmail.com";
		final String password = "wzjjllqxnkodrhfh";
		
		//帳號驗證
		Session session = Session.getDefaultInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		});
		
		//Message 放入基本資料
		try {
			MimeMessage message = new MimeMessage(session);
//			設定寄件人、收件人、副本、主旨
			message.setSentDate(new Date());
			message.setHeader("Content-Type", "text/html; charset=UTF-8");
			message.setFrom(new InternetAddress(userName));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customer));
			message.setSubject(MimeUtility.encodeText(subject, StandardCharsets.UTF_8.toString(), "B"));

//			first part (text)
			MimeBodyPart messageBody = new MimeBodyPart();
			messageBody.setContent(msgtxt, "text/html; charset=UTF-8");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBody);

			
			
//          second part (the image) 可根據自己需要決定是否要加這段
			File file = new File(qrcodePath);
			MimeBodyPart messageImgBody = new MimeBodyPart();
			DataSource fds = new FileDataSource(file);

			messageImgBody.setDataHandler(new DataHandler(fds));
			messageImgBody.setHeader("Content-ID", "<image>");
			messageImgBody.setFileName(file.getName());

//          add image to the multipart
			multipart.addBodyPart(messageImgBody);

			message.setContent(multipart);

//   		寄出email
			Transport transport = session.getTransport("smtp");
			try {
				transport.connect();
				transport.sendMessage(message, message.getAllRecipients());
			} finally {
				transport.close();
			}

		} catch (AddressException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String args[]) {
//
//		String to = "hangermo@gmail.com";
//
//		String subject = "密碼通知";
//
//		String ch_name = "David";
//		String passRandom = "111";
//		String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" + " (已經啟用)";
//
//		MailService mailService = new MailService();
//		mailService.sendMail(to, subject, messageText);
//
//	}
	
}