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

import br.com.pereira.LojaDeDoces.model.Endereco;
import br.com.pereira.LojaDeDoces.services.EnderecoService;

@RestController
@RequestMapping("/api")
public class EnderecoController {

	@Autowired
    private EnderecoService enderecoService;
	
	@GetMapping("/endereco/{CepId}")
    public ResponseEntity<Optional<List<Endereco>>> getEndereco(
    		@PathVariable(value = "CepId") int cepId) throws ResourceNotFoundException {
		Optional<List<Endereco>> enderecos = enderecoService.findByCep(cepId);
        if(enderecos.equals(null)) {
        	return ResponseEntity.badRequest().body(null);
        } else {
        	return ResponseEntity.ok().body(enderecos);
        }
    }
	
	@GetMapping("/endereco/{CepId}/{LogradouroId}/{NumeroId}")
    public ResponseEntity<Optional<Endereco>> getEndereco(
    		@PathVariable(value = "CepId") int cepId,
    		@PathVariable(value = "LogradouroId") String logradouroId,
    		@PathVariable(value = "NumeroId") int numeroId) throws ResourceNotFoundException {
		Optional<Endereco> endereco = enderecoService.findByCepLogradouroNumero(
        		cepId, logradouroId, numeroId);
        if(endereco.isEmpty()) {
        	return ResponseEntity.badRequest().body(null);
        } else {
        	return ResponseEntity.ok().body(endereco);
        }
    }
	
	@GetMapping("/endereco/{CepId}/{LogradouroId}/{NumeroId}/{BairroId}/{CidadeId}")
    public ResponseEntity<Optional<Endereco>> getEndereco(
    		@PathVariable(value = "CepId") int cepId,
    		@PathVariable(value = "LogradouroId") String logradouroId,
    		@PathVariable(value = "NumeroId") int numeroId,
    		@PathVariable(value = "BairroId") String bairroId,
    		@PathVariable(value = "CidadeId") String cidadeId) throws ResourceNotFoundException {
		Optional<Endereco> endereco = enderecoService.findByCepLogradouroNumeroBairroCidade(
        		cepId, logradouroId, numeroId, bairroId, cidadeId);
        if(endereco.equals(null)) {
        	return ResponseEntity.badRequest().body(null);
        } else {
        	return ResponseEntity.ok().body(endereco);
        }
    }
	
	@GetMapping("/endereco")
    public ResponseEntity<List<Endereco>> getAllEndereco() throws ResourceNotFoundException {
		List<Endereco> listaEndereco = enderecoService.findAll();
        return ResponseEntity.ok().body(listaEndereco);
    }
	
	@PostMapping("/endereco")
    public ResponseEntity<String> postEndereco(@Valid @RequestBody Endereco e) {
		Optional<Endereco> endereco = enderecoService.findByCepLogradouroNumeroBairroCidadePost(e);
		if(endereco.isPresent()) {
			return ResponseEntity.badRequest().body("{\"status\":\"Endereco já presente no banco\"}");
		} else {
			enderecoService.save(e);
	        return ResponseEntity.ok().body("{\"status\":\"Endereco inserido com sucesso\"}");
		}
    }
	
	@PutMapping("/endereco")
	public ResponseEntity<String> putEndereco(@Valid @RequestBody Endereco[] e) {
		if(validaEndereco(e)) {
			return ResponseEntity.badRequest().body("{\"status\":\"Endereco inválido\"}");
		} else {
			enderecoService.spPutNewEndereco(e);
	        return ResponseEntity.ok().body("{\"status\":\"Endereco atualizado com sucesso\"}");
		}
    }
	
	@DeleteMapping("/endereco")
	public ResponseEntity<String> deleteEndereco(@RequestBody Endereco e) {
        enderecoService.delete(e);
        return ResponseEntity.ok().body("{\"status\":\"Endereco deletado com sucesso\"}");
    }
	
	private boolean validaEndereco(Endereco[] e) {
		if(e[0].getEnderecoId().getCep() == 0 || 
		   e[0].getEnderecoId().getLogradouro().isBlank() || 
		   e[0].getEnderecoId().getNumero() == 0 || 
		   e[0].getEnderecoId().getBairro().isBlank() || 
		   e[0].getEnderecoId().getCidade().isBlank() || 
		   e[1].getEnderecoId().getCep() == 0 || 
		   e[1].getEnderecoId().getLogradouro().isBlank() || 
		   e[1].getEnderecoId().getNumero() == 0 || 
		   e[1].getEnderecoId().getBairro().isBlank() || 
		   e[1].getEnderecoId().getCidade().isBlank()) {
			return true;
		} else {
			return false;
		}
	}
}
