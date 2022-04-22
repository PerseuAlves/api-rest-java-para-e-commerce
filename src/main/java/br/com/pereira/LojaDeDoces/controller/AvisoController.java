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

import br.com.pereira.LojaDeDoces.model.Aviso;
import br.com.pereira.LojaDeDoces.services.AvisoService;

@RestController
@RequestMapping("/api")
public class AvisoController {

	@Autowired
	AvisoService avisoService;
	
	@GetMapping("/aviso/{idAviso}")
    public ResponseEntity<Aviso> getAviso(@PathVariable(value = "idAviso") int idAviso) {
		try {
			Aviso aviso = avisoService.findById(idAviso);
	        return ResponseEntity.ok().body(aviso);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new Aviso());
		}
    }
	
	@GetMapping("/aviso")
    public ResponseEntity<List<Aviso>> getAllAviso() {
		try {
			List<Aviso> listaAviso = avisoService.findAll();
	        return ResponseEntity.ok().body(listaAviso);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ArrayList<Aviso>());
		}
    }
	
	@PostMapping("/aviso")
    public ResponseEntity<String> postAviso(@Valid @RequestBody Aviso a) {
		try {
			Optional<Aviso> aviso = avisoService.findByIdOptional(a.getId());
			if(aviso.isPresent()) {
				return ResponseEntity.badRequest().body("{\"status\":\"Aviso já presente no banco\"}");
			} else {
				avisoService.save(a);
				return ResponseEntity.ok().body("{\"status\":\"Aviso inserido com sucesso\"}");
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{\"status\":\"Erro ao inserir Aviso\"}");
		}
    }
	
	@PutMapping("/aviso")
	public ResponseEntity<String> putAviso(@Valid @RequestBody Aviso a) {
		try {
			avisoService.save(a);
	        return ResponseEntity.ok().body("{\"status\":\"Aviso atualizado com sucesso\"}");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{\"status\":\"Erro ao atualizar Aviso\"}");
		}
    }
	
	@DeleteMapping("/aviso")
	public ResponseEntity<String> deleteAviso(@Valid @RequestBody Aviso a) {
		try {
			avisoService.delete(a);
	        return ResponseEntity.ok().body("{\"status\":\"Aviso deletado com sucesso\"}");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{\"status\":\"Erro ao deletar Aviso\"}");
		}
    }
}
