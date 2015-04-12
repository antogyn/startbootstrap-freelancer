package aginiers.freelancer;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Contact
 * TODO
 * 
 * @author aginiers
 *
 */
public class Contact extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Contact() {
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

      String name = request.getParameter("name");
      String phone = request.getParameter("phone");
      String email = request.getParameter("email");
      String message = request.getParameter("message");

      Properties props = new Properties();
      Session session = Session.getDefaultInstance(props, null);

      try {
          Message msg = new MimeMessage(session);
          msg.setFrom(new InternetAddress("noreply@aginiers.com", "Contact"));
          msg.addRecipient(Message.RecipientType.TO,
                           new InternetAddress("antogyn@gmail.com", "Anthony Giniers"));
          msg.setSubject("A new contact request has been sent : ");
          String msgBody = ""
              + "Name : " + name + "\n"
              + "Phone : " + phone + "\n"
              + "Email : " + email + "\n"
              + "Message : \n" +  message;
          msg.setText(msgBody);
          Transport.send(msg);

      } catch (AddressException e) {
        e.printStackTrace();
      } catch (MessagingException e) {
        e.printStackTrace();
      }
      
      
      System.out.println(name + phone + email + message);
      
      response.sendRedirect("");
      
	}

}
