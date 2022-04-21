package br.com.pereira.LojaDeDoces.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pereira.LojaDeDoces.model.Adm;
import br.com.pereira.LojaDeDoces.services.AdmService;

@RestController
@RequestMapping("/api")
public class AdmController {

	@Autowired
    private AdmService admService;
	
	@GetMapping("/adm/{idAdm}")
    public ResponseEntity<Adm> getAdm(@PathVariable(value = "idAdm") int idAdm) throws ResourceNotFoundException {
		Adm adm = admService.findById(idAdm);
        return ResponseEntity.ok().body(adm);
    }
	
	@PutMapping("/adm")
	public ResponseEntity<String> putAdm(@Valid @RequestBody Adm a) {
		admService.save(a);
        return ResponseEntity.ok().body("{\"status\":\"Adm atualizado com sucesso\"}");
    }
}
