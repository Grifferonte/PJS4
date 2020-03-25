import java.security.NoSuchProviderException;
import java.util.Properties;


import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/ContactService")
public class ContactService extends HttpServlet {

	/**
	 * 
	 */
	
	//kfurmimnrdkbgipm_mdp
	private static final long serialVersionUID = 1L;
	private Session session = null;
	private Transport transport = null;
	private final String mailSite = "leroykl93@gmail.com";

	public void connect(String host, String user, String password) throws NoSuchProviderException, MessagingException {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.auth", "true");
		// properties.setProperty("mail.smtp.port", "587");
		this.session = Session.getDefaultInstance(properties, null);
		this.transport = this.session.getTransport();
		this.transport.connect(host, user, password);
	}

	public void send(String from, String to, String subject, String body) throws MessagingException {
		MimeMessage message = new MimeMessage(this.session);
		message.setSubject(subject);
		message.setContent(body, "text/plain");
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setFrom(new InternetAddress(from));
		this.transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
		this.transport.close();
	}

	/**
	 * Exemple pour envoyer un email avec SMTP
	 */

	public void doPost(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
		utilisateur user = (utilisateur) session.getAttribute("Utilisateur");

		String message = (String) session.getAttribute("message");
		String mail = (String) session.getAttribute("mail");
		String objet = (String) session.getAttribute("objet");

		try {

			this.connect("smtp.gmail.fr", "leroykl93@gmail.com", "Killian93");
			this.send(this.mailSite, mail, objet, message);

		} catch (MessagingException | NoSuchProviderException ex) {
			ex.printStackTrace();
		}

	}

}
