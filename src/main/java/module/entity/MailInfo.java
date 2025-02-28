package module.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {
	String from;
	String to;
	String[] cc;
	String[] bcc;
	String subject;
	String body;
	String[] attachments;

	public MailInfo(String to, String subject, String body) {
		this.from = "FPT Polytechnic <lvhuy92.1617@gmail.com>";
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
}
