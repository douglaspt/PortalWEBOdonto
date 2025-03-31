package controle.negocio;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GerenciadorEMail {

  // Host do Gmail  
  private String SMTP_HOST_NAME;  

  // Login do usuário  
  private String SMTP_AUTH_USER;  

  // Senha do usuário  
  private String SMTP_AUTH_PWD;  

  // E-mail do remetente  
  private String emailFromAddress;  

  // Título do e-mail  
  private String emailSubjectTxt;  

  // Conteúdo do e-mail  
  private String emailMsgTxt;  

  // E-mails dos destinatários  
  private String[] emailList;  

  public GerenciadorEMail() {  
      SMTP_HOST_NAME = "smtp.imasf.com.br";  
      SMTP_AUTH_USER = "sistemasql@imasf.com.br";  
      SMTP_AUTH_PWD = "sql@imasf";
//      SMTP_HOST_NAME = "mail.horizontesistemas.com";  
//      SMTP_AUTH_USER = "bruno@horizontesistemas.com";  
//      SMTP_AUTH_PWD = "a5x2";  	  
  }

  
  public void sendEmail(String subjectTxt, String messageTxt) throws Exception{
	  String[] listaEmails = {"bruno@horizontesistemas.com","b_pimenta@yahoo.com.br"};
	  sendEmail(listaEmails, subjectTxt, messageTxt);
  }
  
  public void sendEmail(String[] toAddress, String subjectTxt, String messageTxt) throws Exception{ 
	  sendEmail(SMTP_AUTH_USER, toAddress, subjectTxt, messageTxt);
  }

  public void sendEmail(String fromAddress, String[] toAddress, String subjectTxt, String messageTxt) throws Exception{  
      emailFromAddress = fromAddress;  
      emailList = toAddress;  
      emailSubjectTxt = subjectTxt;  
      emailMsgTxt = messageTxt;  

      boolean debug = false;  
      java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());  

      // Configurando o endereço SMTP do host   
      Properties props = new Properties();  
      props.put("mail.transport.protocol", "smtp");  
      props.put("mail.smtp.starttls.enable", "true");  
      props.put("mail.smtp.host", SMTP_HOST_NAME);  
      props.put("mail.smtp.auth", "true");  

      // Caso o e-mail precise de autenticação  
      Authenticator auth = new SMTPAuthenticator();  
      Session session = Session.getDefaultInstance(props, auth);  

      session.setDebug(debug);  

      // Criando a mensagem  
      Message msg = new MimeMessage(session);  

      // Adicionando o e-mail do remetente  
      InternetAddress addressFrom = new InternetAddress(emailFromAddress);  
      msg.setFrom(addressFrom);  

      // Adicionando os e-mails dos destinatários  
      InternetAddress[] addressTo = new InternetAddress[emailList.length];  
      for (int i = 0; i < emailList.length; i++) {  
          addressTo[i] = new InternetAddress(emailList[i]);  
      }  
      msg.setRecipients(Message.RecipientType.TO, addressTo);  

      // Adicionando o título do e-mail  
      msg.setSubject(emailSubjectTxt);  
        
      // Adicionando o conteúdo do e-mail  
      msg.setContent(emailMsgTxt, "text/plain");  
      Transport.send(msg);  
  }  

  /** 
   * Autentica o usuário quando o servidor SMTP requisita 
   */  
  private class SMTPAuthenticator extends javax.mail.Authenticator {  

      public PasswordAuthentication getPasswordAuthentication() {  
          String username = SMTP_AUTH_USER;  
          String password = SMTP_AUTH_PWD;  
          return new PasswordAuthentication(username, password);  
      }  
  }  

}
