package module.rest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import module.entity.Account;
import module.entity.Authority;
import module.entity.Role;
import module.mail.MailerService;
import module.service.AccountService;
import module.service.AuthorityService;
import module.service.RoleService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest")
public class RegisterRestController {
	@Autowired
	AccountService accountService;

	@Autowired
	RoleService roleService;

	@Autowired
	AuthorityService authorityService;

	@Autowired
	private MailerService mailerService;

	private Map<String, String> otpStorage = new HashMap<>();

	@PostMapping("/register")
	public Account register(@RequestBody Account account) throws MessagingException {

		String otp = generateOtp();
		otpStorage.put(account.getEmail(), otp);

		// Send OTP via email
		sendOtpEmail(account.getEmail(), otp);

		return account;
	}

	@PostMapping("/verify-otp")
	public Account verifyOtp(@RequestBody JsonNode request) {
		String email = request.get("account").get("email").asText();
		String otp = request.get("otp").asText();
		String storedOtp = otpStorage.get(email);
		Account account = null;

		List<Role> roles = roleService.findAll();
		Role customerRole = roles.stream().filter(role -> "CUST".equals(role.getId())).findFirst().orElse(null);

		if (storedOtp != null && storedOtp.equals(otp)) {
			ObjectMapper mapper = new ObjectMapper();
			account = mapper.convertValue(request.get("account"), Account.class);

			// Lưu account vào cơ sở dữ liệu
			accountService.save(account);

			// Lưu authority vào cơ sở dữ liệu
			Authority authority = new Authority();
			authority.setAccount(account);
			authority.setRole(customerRole);
			authorityService.create(authority);
			
			return account;
		}
		return account;
	}

	private String generateOtp() {
		return String.format("%06d", new Random().nextInt(999999));
	}

	private void sendOtpEmail(String email, String otp) throws MessagingException {
		mailerService.send(email, "Your OTP Code", "Your OTP code is: " + otp);
	}
}
