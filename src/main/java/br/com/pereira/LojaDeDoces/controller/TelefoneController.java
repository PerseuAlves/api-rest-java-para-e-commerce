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

import br.com.pereira.LojaDeDoces.model.Telefone;
import br.com.pereira.LojaDeDoces.repository.TelefoneRepository;

@RestController
@RequestMapping("/api")
public class TelefoneController {

	@Autowired
    private TelefoneRepository tRep;
	
	@GetMapping("/telefone/{idTelefone}")
    public ResponseEntity<Telefone> getTelefone(@PathVariable(value = "idTelefone") String idTelefone) throws ResourceNotFoundException {
        Telefone telefone = tRep.findById(idTelefone).orElseThrow(
                () -> new ResourceNotFoundException(idTelefone + " inválido")
        );
        return ResponseEntity.ok().body(telefone);
    }
	
	@GetMapping("/telefone")
    public ResponseEntity<List<Telefone>> getAllTelefone() throws ResourceNotFoundException {
		List<Telefone> listaTelefone = new ArrayList<Telefone>();
		listaTelefone = tRep.findAll();
        return ResponseEntity.ok().body(listaTelefone);
    }
	
	@PostMapping("/telefone")
    public ResponseEntity<String> postTelefone(@Valid @RequestBody Telefone t) {
		Optional<Telefone> telefone = tRep.findById(t.getNumero());
		if(telefone.isPresent()) {
			return ResponseEntity.badRequest().body("Telefone já presente no banco");
		} else {
			tRep.save(t);
	        return ResponseEntity.ok().body("Telefone inserido com sucesso");
		}
    }
	
	@PutMapping("/telefone")
	public ResponseEntity<String> putTelefone(@Valid @RequestBody Telefone[] t) {
		if(t[0].getNumero().isBlank() || t[1].getNumero().isBlank()) {
			return ResponseEntity.ok().body("Telefone inválido");
		}
        tRep.spPutNewTel(t[0].getNumero(), t[1].getNumero());
        return ResponseEntity.ok().body("Telefone atualizado com sucesso");
    }
	
	@DeleteMapping("/telefone")
	public ResponseEntity<String> deleteTelefone(@Valid @RequestBody Telefone t) {
        tRep.delete(t);
        return ResponseEntity.ok().body("Telefone deletado com sucesso");
    }
}
