package br.com.pereira.LojaDeDoces.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
import br.com.pereira.LojaDeDoces.repository.UsuarioRepository;
import br.com.pereira.LojaDeDoces.services.EmailService;

@RestController
@RequestMapping("/api")
public class UsuarioController {

	@Autowired
    private UsuarioRepository uRep;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Usuario> getUsuario(@PathVariable(value = "idUsuario") int idUsuario) throws ResourceNotFoundException {
        Usuario usuario = uRep.findById(idUsuario).orElseThrow(
                () -> new ResourceNotFoundException(idUsuario + " inválido")
        );
        return ResponseEntity.ok().body(usuario);
    }
	
	@GetMapping("/usuario")
    public ResponseEntity<List<Usuario>> getAllUsuario() throws ResourceNotFoundException {
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		listaUsuario = uRep.findAll();
        return ResponseEntity.ok().body(listaUsuario);
    }
	
	@PostMapping("/usuario")
    public ResponseEntity<String> postUsuario(@Valid @RequestBody Usuario u) {
		Optional<Usuario> usuario = uRep.findById(u.getId());
		if(usuario.isPresent()) {
			return ResponseEntity.badRequest().body("Usuario já presente no banco");
		} else {
			uRep.save(u);
			emailService.sendEmailConfirmation(u);
	        return ResponseEntity.ok().body("Usuario inserido com sucesso");
		}
    }
	
	@PutMapping("/usuario")
	public ResponseEntity<String> putUsuario(@Valid @RequestBody Usuario u) {
		uRep.save(u);
		emailService.sendEmailConfirmation(u);
        return ResponseEntity.ok().body("Usuario atualizado com sucesso");
    }
	
	@DeleteMapping("/usuario")
	public ResponseEntity<String> deleteUsuario(@Valid @RequestBody Usuario u) {
		uRep.delete(u);
        return ResponseEntity.ok().body("Usuario deletado com sucesso");
    }
}
