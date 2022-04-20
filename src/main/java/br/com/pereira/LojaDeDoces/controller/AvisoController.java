package br.com.pereira.LojaDeDoces.controller;

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

import br.com.pereira.LojaDeDoces.model.Aviso;
import br.com.pereira.LojaDeDoces.services.AvisoService;

@RestController
@RequestMapping("/api")
public class AvisoController {

	@Autowired
	AvisoService avisoService;
	
	@GetMapping("/aviso/{idAviso}")
    public ResponseEntity<Aviso> getAviso(@PathVariable(value = "idAviso") int idAviso) throws ResourceNotFoundException {
        Aviso aviso = avisoService.findById(idAviso);
        return ResponseEntity.ok().body(aviso);
    }
	
	@GetMapping("/aviso")
    public ResponseEntity<List<Aviso>> getAllAviso() throws ResourceNotFoundException {
		List<Aviso> listaAviso = avisoService.findAll();
        return ResponseEntity.ok().body(listaAviso);
    }
	
	@PostMapping("/aviso")
    public ResponseEntity<String> postAviso(@Valid @RequestBody Aviso a) {
		Optional<Aviso> aviso = avisoService.findByIdOptional(a.getId());
		if(aviso.isPresent()) {
			return ResponseEntity.badRequest().body("Aviso já presente no banco");
		} else {
			avisoService.save(a);
	        return ResponseEntity.ok().body("Aviso inserido com sucesso");
		}
    }
	
	@PutMapping("/aviso")
	public ResponseEntity<String> putAviso(@Valid @RequestBody Aviso a) {
        avisoService.save(a);
        return ResponseEntity.ok().body("Aviso atualizado com sucesso");
    }
	
	@DeleteMapping("/aviso")
	public ResponseEntity<String> deleteAviso(@Valid @RequestBody Aviso a) {
        avisoService.delete(a);
        return ResponseEntity.ok().body("Aviso deletado com sucesso");
    }
}
