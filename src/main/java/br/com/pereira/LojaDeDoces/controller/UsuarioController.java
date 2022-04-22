package br.com.pereira.LojaDeDoces.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pereira.LojaDeDoces.model.Usuario;
import br.com.pereira.LojaDeDoces.services.EmailService;
import br.com.pereira.LojaDeDoces.services.UsuarioService;

@RestController
@RequestMapping("/api")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable(value = "idUsuario") int idUsuario) {
		try {
			Usuario usuario = usuarioService.findById(idUsuario);
	        return ResponseEntity.ok().body(usuario);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new Usuario());
		}
    }
	
	@GetMapping("/usuario")
    public ResponseEntity<List<Usuario>> getAllUsuario() {
		try {
			List<Usuario> listaUsuario = new ArrayList<Usuario>();
			listaUsuario = usuarioService.findAll();
	        return ResponseEntity.ok().body(listaUsuario);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ArrayList<Usuario>());
		}
    }
	
	@PostMapping("/usuario")
    public ResponseEntity<String> postUsuario(@Valid @RequestBody Usuario u) {
		try {
			Optional<Usuario> usuario = usuarioService.findByIdForPostMethod(u.getId());
			if(usuario.isPresent()) {
				return ResponseEntity.badRequest().body("{\"status\":\"Usuario já presente no banco\"}");
			} else {
				usuarioService.save(u);
				String emailStatus = emailService.sendEmailConfirmation(u);
		        return ResponseEntity.ok().body("{\"status\":\"Usuario inserido com sucesso. " + emailStatus + "\"}");
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{\"status\":\"Erro ao inserir Usuario\"}");
		}
    }
	
	@PutMapping("/usuario")
	public ResponseEntity<String> putUsuario(@Valid @RequestBody Usuario u) {
		try {
			usuarioService.save(u);
			String emailStatus = emailService.sendEmailConfirmation(u);
	        return ResponseEntity.ok().body("{\"status\":\"Usuario atualizado com sucesso. " + emailStatus + "\"}");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{\"status\":\"Erro ao atualizar Usuario\"}");
		}
    }
	
	@DeleteMapping("/usuario")
	public ResponseEntity<String> deleteUsuario(@Valid @RequestBody Usuario u) {
		try {
			usuarioService.delete(u);
	        return ResponseEntity.ok().body("{\"status\":\"Usuario deletado com sucesso\"}");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{\"status\":\"Erro ao deletar Usuario\"}");
		}
    }
}
