package EnvoiMail;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class ConfigMail {

	private final String username = "tempest.contact.fr@gmail.com";
	private final String password = "diabolodead931";
	private final String adressMailWebsite = "tempest.contact.fr@gmail.com";
	
	private String objectMail;
	private String bodyMail;
	private String adressFrom;
	
	public ConfigMail(String object, String body,String from ) {
		this.objectMail = object;
		this.bodyMail = body;
		this.adressFrom = from;
	}

	public void sendMailGmail() {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "465");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.socketFactory.port", "465");
		prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(this.adressFrom));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(this.adressMailWebsite));
			message.setSubject(this.objectMail);
			message.setText(this.bodyMail);

			Transport.send(message);

			System.out.println("Mail envoy� avec succ�s");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
