package br.com.pereira.LojaDeDoces.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pereira.LojaDeDoces.model.Produto;
import br.com.pereira.LojaDeDoces.repository.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
    private ProdutoRepository pRep;
	
	public Produto findById(int idProduto) {
		return pRep.findById(idProduto).orElseThrow(
                () -> new ResourceNotFoundException(idProduto + " inválido")
        );
	}
	
	public Optional<Produto> findByIdForOptional(int idProduto) {
		return pRep.findById(idProduto);
	}
	
	public Produto findByTitulo(String tituloProduto) {
		return pRep.findByTitulo(tituloProduto).orElseThrow(
				() -> new ResourceNotFoundException(tituloProduto + " inválido"));
	}
	
	public List<Produto> findAllByCategoria(String categoriaProduto) {
		return pRep.findByCategoria1OrCategoria2OrCategoria3(categoriaProduto, categoriaProduto, categoriaProduto).orElseThrow(
				() -> new ResourceNotFoundException(categoriaProduto + " inválido"));
	}
	
	public List<Produto> findAllByHighPrice() {
		return pRep.findAllByHighPrice().orElseThrow(
				() -> new ResourceNotFoundException("Erro ao buscar todos os produtos pelo maior preco"));
	}
	
	public List<Produto> findAllByLowerPrice() {
		return pRep.findAllByLowerPrice().orElseThrow(
				() -> new ResourceNotFoundException("Erro ao buscar todos os produtos pelo menor preco"));
	}
	
	public List<Produto> findAll() {
		return pRep.findAll();
	}
	
	public void save(Produto p) {
		pRep.save(p);
	}
	
	public void delete(Produto p) {
		pRep.delete(p);
	}
}
