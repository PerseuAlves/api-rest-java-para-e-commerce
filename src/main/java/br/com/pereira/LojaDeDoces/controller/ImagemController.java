package br.com.pereira.LojaDeDoces.controller;

import java.time.Instant;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.pereira.LojaDeDoces.model.resource.Imagem;
import br.com.pereira.LojaDeDoces.services.ImagemService;
import br.com.pereira.LojaDeDoces.services.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api")
public class ImagemController {

	@Autowired
	private ImagemService imagemService;
	
	@GetMapping("/imagem/{ImagemId}")
	public ResponseEntity<Imagem> getImagem(
			@PathVariable(value = "ImagemId") String imagemId) {
		
		Imagem imagem = imagemService.findImagem(imagemId);
		
		
		return ResponseEntity.ok().body(imagem);
	}
	
	@PostMapping("/imagem")
    public ResponseEntity<String> postImagem(@Valid @RequestBody Imagem i) {
		
		try {
			@SuppressWarnings("unused")
			Imagem imagem = imagemService.findImagem(i.getId());
			return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"timestamp\":\"" + Instant.now() + "\",\"message\":\"Imagem já cadastrada\"}");
		} catch (ResourceNotFoundException ex) {
			imagemService.save(i);
			return ResponseEntity.status(HttpStatus.OK).body("{\"timestamp\":\"" + Instant.now() + "\",\"message\":\"Imagem inserida com sucesso\"}");
		}
    }
	
	@PutMapping("/imagem")
	public ResponseEntity<String> putImagem(@Valid @RequestBody Imagem i) {
		
		imagemService.save(i);
		return ResponseEntity.status(HttpStatus.OK).body("{\"timestamp\":\"" + Instant.now() + "\",\"message\":\"Imagem atualizada com sucesso\"}");
    }
	
	@DeleteMapping("/imagem")
	public ResponseEntity<String> deleteImagem(@Valid @RequestBody Imagem i) {
		
		imagemService.delete(i);
		return ResponseEntity.status(HttpStatus.OK).body("{\"timestamp\":\"" + Instant.now() + "\",\"message\":\"Imagem deletada com sucesso\"}");
    }
}
