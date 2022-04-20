package br.com.pereira.LojaDeDoces.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.com.pereira.LojaDeDoces.model.Usuario;

@Service
public class EmailService {
	
	@Value("${spring.mail.username}")
	private String sender;
	
	@Autowired
	private JavaMailSender emailSender;

	public String sendEmailConfirmation(Usuario usuario) {
		try {
			SimpleMailMessage sm = prepareSimpleMailMessageFromUsuario(usuario);
			emailSender.send(sm);
			return "Email enviado com sucesso";
		} catch (Exception e) {
			return "Email não enviado";
		}
	}

	private SimpleMailMessage prepareSimpleMailMessageFromUsuario(Usuario usuario) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(usuario.getEmail());
		sm.setFrom(sender);
		sm.setSubject("Email confirmado!");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText("Login cadastrado: " + usuario.getEmail() + "\nSenha cadastrada: " + usuario.getSenha());
		return sm;
	}
}
