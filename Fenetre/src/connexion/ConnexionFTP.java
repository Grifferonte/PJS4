package connexion;

import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class ConnexionFTP {

	private static void showServerReply(FTPClient ftpClient) {
        String[] replies = ftpClient.getReplyStrings();
        if (replies != null && replies.length > 0) {
            for (String aReply : replies) {
                System.out.println("SERVER: " + aReply);
            }
        }
    }
	
	public static FTPClient connexion() {
		
		 String server = "localhost";
	        int port = 2121;
	        String user = "anonymous";
	        String pass = "me@nowhere.com";
	        FTPClient ftpClient = new FTPClient();
	        try {
	            ftpClient.connect(server, port);
	            showServerReply(ftpClient);
	            int replyCode = ftpClient.getReplyCode();
	            if (!FTPReply.isPositiveCompletion(replyCode)) {
	                System.out.println("Operation failed. Server reply code: " + replyCode);
	                return null;
	            }
	            boolean success = ftpClient.login(user, pass);
	            showServerReply(ftpClient);
	            if (!success) {
	                System.out.println("Could not login to the server");
	                return null;
	            } else {
	                System.out.println("LOGGED IN SERVER");
	              return ftpClient;
	            }
	        } catch (IOException ex) {
	            System.out.println("Oops! Something wrong happened");
	            ex.printStackTrace();
	        }
			return null;
	}
}
