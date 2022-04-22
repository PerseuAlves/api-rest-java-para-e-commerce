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

import br.com.pereira.LojaDeDoces.model.Compra;
import br.com.pereira.LojaDeDoces.services.CompraService;

@RestController
@RequestMapping("/api")
public class CompraController {

	@Autowired
    private CompraService CompraService;
	
	@GetMapping("/compra")
    public ResponseEntity<List<Compra>> getAllCompra() {
		try {
			List<Compra> listaCompra = new ArrayList<Compra>();
			listaCompra = CompraService.findAll();
	        return ResponseEntity.ok().body(listaCompra);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ArrayList<Compra>());
		}
    }
	
	@GetMapping("/compra/{idCompra}")
    public ResponseEntity<Optional<Compra>> getCompra(@PathVariable(value = "idCompra") int idCompra) {
		try {
			Optional<Compra> compra = CompraService.findById(idCompra);
			if(compra.isPresent()) {
				return ResponseEntity.ok().body(compra);
			} else {
				return ResponseEntity.badRequest().body(null);
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
    }
	
	@PostMapping("/compra")
    public ResponseEntity<String> postCompra(@Valid @RequestBody Compra c) {
		try {
			Optional<Compra> compra = CompraService.findById(c.getId());
			if(compra.isPresent()) {
				return ResponseEntity.badRequest().body("{\"status\":\"Compra já presente no banco\"}");
			} else {
				CompraService.save(c);
		        return ResponseEntity.ok().body("{\"status\":\"Compra inserida com sucesso\"}");
			}
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{\"status\":\"Erro ao inserir Compra\"}");
		}
    }
	
	@PutMapping("/compra")
	public ResponseEntity<String> putCompra(@Valid @RequestBody Compra c) {
		try {
			CompraService.save(c);
	        return ResponseEntity.ok().body("{\"status\":\"Compra atualizada com sucesso\"}");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{\"status\":\"Erro ao atualizar Compra\"}");
		}
    }
	
	@DeleteMapping("/compra")
	public ResponseEntity<String> deleteCompra(@Valid @RequestBody Compra c) {
		try {
			CompraService.delete(c);
	        return ResponseEntity.ok().body("{\"status\":\"Compra deletada com sucesso\"}");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("{\"status\":\"Erro ao deletar Compra\"}");
		}
    }
}
