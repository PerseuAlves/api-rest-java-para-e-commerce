package br.com.pereira.LojaDeDoces.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pereira.LojaDeDoces.model.Compra;
import br.com.pereira.LojaDeDoces.repository.CompraRepository;

@Service
public class CompraService {

	@Autowired
	private CompraRepository cRep;
	
	public Optional<Compra> findById(int id) {
		return cRep.findById(id);
	}
	
	public List<Compra> findAll() {
		return cRep.findAll();
	}
	
	public void save(Compra c) {
		cRep.save(c);
	}
	
	public void delete(Compra c) {
		cRep.delete(c);
	}
}
