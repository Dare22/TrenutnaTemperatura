package stavi.u.email.i.posalji;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Email {

	public static void sendEmailWithAttachments(String message, String subject, ArrayList<String> toAddress,
			ArrayList<String> attachFiles) throws Exception {

		Properties properties = new Properties();

		// ucitavanje pomocu .properties
		   InputStream inputStreamProp = new FileInputStream("resources/emaill.properties");
		
		// ucitavanje pomocu .xml
		// InputStream inputStreamXml = new FileInputStream("resources/email.xml");

		try {

			// ucitavanje emaill.properties fajla
			   properties.load(inputStreamProp);
			
			// ucitavanje email.xml fajla
			// properties.loadFromXML(inputStreamXml);

		} catch (Exception e) {
			// sta ce se dogoditi ako ne uspije ucitavanje iz properties fajla?
			e.printStackTrace();
		}
		// ovdje treba u finally blok dodati zatvaranje InputStream ili koristiti try with resources
		
		// uzimanje vrijednosti iz email.xml ili properties fajla
		String host = properties.getProperty("host");
		String port = properties.getProperty("port");
		final String userName = properties.getProperty("userName");
		final String password = properties.getProperty("password");

		// SMTP server
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.user", userName);
		properties.put("mail.password", password);

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};
		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = new InternetAddress[toAddress.size()];
		for (int i = 0; i < toAddress.size(); i++) {
			toAddresses[i] = new InternetAddress(toAddress.get(i));
		}
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.setSubject(subject);
		msg.setSentDate(new Date());

		// creates message part
		MimeBodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setContent(message, "text/html");

		// creates multi-part
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);

		// adds attachments
		if (attachFiles != null) {
			for (String filePath : attachFiles) {
				MimeBodyPart attachPart = new MimeBodyPart();

				try {
					attachPart.attachFile(filePath);
				} catch (IOException ex) {
					ex.printStackTrace();
				}

				multipart.addBodyPart(attachPart);
			}
		}

		// sets the multi-part as e-mail's content
		msg.setContent(multipart);

		// sends the e-mail
		Transport.send(msg);

	}

}
