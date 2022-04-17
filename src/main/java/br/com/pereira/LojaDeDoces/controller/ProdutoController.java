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

import br.com.pereira.LojaDeDoces.model.Produto;
import br.com.pereira.LojaDeDoces.repository.ProdutoRepository;

@RestController
@RequestMapping("/api")
public class ProdutoController {

	@Autowired
    private ProdutoRepository pRep;
	
	@GetMapping("/produto")
    public ResponseEntity<List<Produto>> getAllProduto() throws ResourceNotFoundException {
		List<Produto> listaProduto = new ArrayList<Produto>();
		listaProduto = pRep.findAll();
        return ResponseEntity.ok().body(listaProduto);
    }
	
	@GetMapping("/produto/{idProduto}")
    public ResponseEntity<Produto> getProduto(@PathVariable(value = "idProduto") int idProduto) throws ResourceNotFoundException {
        Produto produto = pRep.findById(idProduto).orElseThrow(
                () -> new ResourceNotFoundException(idProduto + " inválido")
        );
        return ResponseEntity.ok().body(produto);
    }
	
	@GetMapping("/produto/titulo/{tituloProduto}")
    public ResponseEntity<Produto> getProdutoByTitulo(@PathVariable(value = "tituloProduto") String tituloProduto) throws ResourceNotFoundException {
        Produto produto = pRep.findByTitulo(tituloProduto).orElseThrow(
                () -> new ResourceNotFoundException(tituloProduto + " inválido")
        );
        return ResponseEntity.ok().body(produto);
    }
	
	@GetMapping("/produto/byHighPrice")
    public ResponseEntity<List<Produto>> getAllProdutoByHighPrice() throws ResourceNotFoundException {
		List<Produto> listaProduto = new ArrayList<Produto>();
		listaProduto = pRep.findAllByHighPrice().orElseThrow(
				() -> new ResourceNotFoundException("Erro ao buscar todos os produtos pelo maior preco")
		);
        return ResponseEntity.ok().body(listaProduto);
    }
	
	@GetMapping("/produto/byLowerPrice")
    public ResponseEntity<List<Produto>> getAllProdutoByLowerPrice() throws ResourceNotFoundException {
		List<Produto> listaProduto = new ArrayList<Produto>();
		listaProduto = pRep.findAllByLowerPrice().orElseThrow(
				() -> new ResourceNotFoundException("Erro ao buscar todos os produtos pelo menor preco")
		);
        return ResponseEntity.ok().body(listaProduto);
    }
	
	@PostMapping("/produto")
    public ResponseEntity<String> postProduto(@Valid @RequestBody Produto p) {
		Optional<Produto> produto = pRep.findById( p.getId());
		if(produto.isPresent()) {
			return ResponseEntity.badRequest().body("Produto já presente no banco");
		} else {
			pRep.save( p);
	        return ResponseEntity.ok().body("Produto inserido com sucesso");
		}
    }
	
	@PutMapping("/produto")
	public ResponseEntity<String> putProduto(@Valid @RequestBody Produto p) {
        pRep.save( p);
        return ResponseEntity.ok().body("Produto atualizado com sucesso");
    }
	
	@DeleteMapping("/produto")
	public ResponseEntity<String> deleteProduto(@Valid @RequestBody Produto p) {
        pRep.delete( p);
        return ResponseEntity.ok().body("Produto deletado com sucesso");
    }
}
