package module.mail;

import javax.mail.MessagingException;

import module.entity.MailInfo;

public interface MailerService {
	void send(MailInfo mail) throws MessagingException;
	
	void send(String to, String subject, String body) throws MessagingException;
	
	void queue(MailInfo mail);
	
	void queue(String to, String subject, String body);
}
