package com.taiwan.controller.customer;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import redis.clients.jedis.Jedis;

public class SendMail extends Thread{
	public String email;//收件人信箱
	public String code;//傳入啟用驗證
	
	public SendMail(String email) {
		this.email = email;
		this.code = UUID.randomUUID().toString();
	}
	
	@Override
	public void run() {
	      // Recipient's email ID needs to be mentioned.
	      String to = email;//change accordingly

	      // Sender's email ID needs to be mentioned
	      String from = "aka9988test@gmail.com";//change accordingly
	      final String username = "cga101g1@gmail.com";//change accordingly
	      final String password = "Tibamecga101g1";//change accordingly
	      
	      
	      //Redis存入信箱對應的驗證碼
	      Jedis jedis = new Jedis("34.81.27.105", 6379);
	      jedis.auth("CFA101G3");
	      jedis.hset(email, "email", code);
	      
	      
	      
	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "587");
	      props.setProperty("mail.debug", "true");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	      new javax.mail.Authenticator() {
	         protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(username, password);
	         }
	      });

	      try {
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.TO,
	         InternetAddress.parse(to));

	         // Set Subject: header field(信箱標題)
	         message.setSubject("請驗證您的信箱");

	         // Now set the actual message

	         //信件內容以HTML格式傳出去
	         message.setContent("<h1>請點擊下列連結啟用您的帳號</h1><br><a href=\"http://localhost:8081/CFA101G3/member/activeServlet?username="+email+"&code="+code+"\">點擊後啟用您的帳號</a>","text/html; charset=UTF-8" );
	         
	         
	         // Send message
	         Transport.send(message);

	         System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	            throw new RuntimeException(e);
	      }
	}
}
