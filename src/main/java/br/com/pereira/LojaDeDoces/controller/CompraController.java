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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pereira.LojaDeDoces.model.Compra;
import br.com.pereira.LojaDeDoces.repository.CompraRepository;

@RestController
@RequestMapping("/api")
public class CompraController {

	@Autowired
    private CompraRepository cRep;
	
	@GetMapping("/compra")
    public ResponseEntity<List<Compra>> getAllCompra() throws ResourceNotFoundException {
		List<Compra> listaAviso = new ArrayList<Compra>();
		listaAviso = cRep.findAll();
        return ResponseEntity.ok().body(listaAviso);
    }
	
	@PostMapping("/compra")
    public ResponseEntity<String> postCompra(@Valid @RequestBody Compra c) {
		Optional<Compra> compra = cRep.findById(c.getId());
		if(compra.isPresent()) {
			return ResponseEntity.badRequest().body("Compra já presente no banco");
		} else {
			cRep.save(c);
	        return ResponseEntity.ok().body("Compra inserido com sucesso");
		}
    }
	
	@PutMapping("/compra")
	public ResponseEntity<String> putCompra(@Valid @RequestBody Compra c) {
        cRep.save(c);
        return ResponseEntity.ok().body("Compra atualizado com sucesso");
    }
	
	@DeleteMapping("/compra")
	public ResponseEntity<String> deleteCompra(@Valid @RequestBody Compra c) {
        cRep.delete(c);
        return ResponseEntity.ok().body("Compra deletado com sucesso");
    }
}
