package br.com.pereira.LojaDeDoces.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.pereira.LojaDeDoces.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

	Optional<Produto> findByTitulo(String titulo);
	Optional<List<Produto>> findAllByHighPrice();
	Optional<List<Produto>> findAllByLowerPrice();
}