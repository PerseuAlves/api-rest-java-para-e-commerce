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

import br.com.pereira.LojaDeDoces.model.Endereco;
import br.com.pereira.LojaDeDoces.services.EnderecoService;

@RestController
@RequestMapping("/api")
public class EnderecoController {

	@Autowired
    private EnderecoService enderecoService;
	
	@GetMapping("/endereco/{CepId}")
    public ResponseEntity<Optional<List<Endereco>>> getEndereco(
    		@PathVariable(value = "CepId") int cepId) {
		try {
			Optional<List<Endereco>> enderecos = enderecoService.findByCep(cepId);
	        if(enderecos.equals(null)) {
	        	return ResponseEntity.badRequest().body(null);
	        } else {
	        	return ResponseEntity.ok().body(enderecos);
	        }
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
    }
	
	@GetMapping("/endereco/{CepId}/{LogradouroId}/{NumeroId}")
    public ResponseEntity<Optional<Endereco>> getEndereco(
    		@PathVariable(value = "CepId") int cepId,
    		@PathVariable(value = "LogradouroId") String logradouroId,
    		@PathVariable(value = "NumeroId") int numeroId) {
		try {
			Optional<Endereco> endereco = enderecoService.findByCepLogradouroNumero(
	        		cepId, logradouroId, numeroId);
	        if(endereco.isEmpty()) {
	        	return ResponseEntity.badRequest().body(null);
	        } else {
	        	return ResponseEntity.ok().body(endereco);
	        }
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
    }
	
	@GetMapping("/endereco/{CepId}/{LogradouroId}/{NumeroId}/{BairroId}/{CidadeId}")
    public ResponseEntity<Optional<Endereco>> getEndereco(
    		@PathVariable(value = "CepId") int cepId,
    		@PathVariable(value = "LogradouroId") String logradouroId,
    		@PathVariable(value = "NumeroId") int numeroId,
    		@PathVariable(value = "BairroId") String bairroId,
    		@PathVariable(value = "CidadeId") String cidadeId) {
		try {
			Optional<Endereco> endereco = enderecoService.findByCepLogradouroNumeroBairroCidade(
	        		cepId, logradouroId, numeroId, bairroId, cidadeId);
	        if(endereco.equals(null)) {
	        	return ResponseEntity.badRequest().body(null);
	        } else {
	        	return ResponseEntity.ok().body(endereco);
	        }
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(null);
		}
    }
	
	@GetMapping("/endereco")
    public ResponseEntity<List<Endereco>> getAllEndereco() {
		try {
			List<Endereco> listaEndereco = enderecoService.findAll();
	        return ResponseEntity.ok().body(listaEndereco);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new ArrayList<Endereco>());
		}
    }
	
	@PostMapping("/endereco")
    public ResponseEntity<String> postEndereco(@Valid @RequestBody Endereco e) {
		try {
			Optional<Endereco> endereco = enderecoService.findByCepLogradouroNumeroBairroCidadePost(e);
			if(endereco.isPresent()) {
				return ResponseEntity.badRequest().body("{\"status\":\"Endereco já presente no banco\"}");
			} else {
				enderecoService.save(e);
		        return ResponseEntity.ok().body("{\"status\":\"Endereco inserido com sucesso\"}");
			}
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("{\"status\":\"Erro ao inserir Endereco\"}");
		}
    }
	
	@PutMapping("/endereco")
	public ResponseEntity<String> putEndereco(@Valid @RequestBody Endereco[] e) {
		try {
			if(validaEndereco(e)) {
				return ResponseEntity.badRequest().body("{\"status\":\"Endereco inválido\"}");
			} else {
				enderecoService.spPutNewEndereco(e);
		        return ResponseEntity.ok().body("{\"status\":\"Endereco atualizado com sucesso\"}");
			}
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("{\"status\":\"Erro ao atualizar Endereco\"}");
		}
    }
	
	@DeleteMapping("/endereco")
	public ResponseEntity<String> deleteEndereco(@RequestBody Endereco e) {
		try {
			enderecoService.delete(e);
	        return ResponseEntity.ok().body("{\"status\":\"Endereco deletado com sucesso\"}");
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body("{\"status\":\"Erro ao deletar Endereco\"}");
		}
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
