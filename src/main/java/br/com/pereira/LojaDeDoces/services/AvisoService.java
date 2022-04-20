package br.com.pereira.LojaDeDoces.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pereira.LojaDeDoces.model.Aviso;
import br.com.pereira.LojaDeDoces.repository.AvisoRepository;

@Service
public class AvisoService {

	@Autowired
    private AvisoRepository aRep;
	
	public Aviso findById(int idAviso) {
		return aRep.findById(idAviso).orElseThrow(
                () -> new ResourceNotFoundException(idAviso + " inválido")
        );
	}
	
	public Optional<Aviso> findByIdOptional(int idAviso) {
		return aRep.findById(idAviso);
	}
	
	public List<Aviso> findAll() {
		return aRep.findAll();
	}
	
	public void save(Aviso a) {
		aRep.save(a);
	}
	
	public void delete(Aviso a) {
		aRep.delete(a);
	}
}
