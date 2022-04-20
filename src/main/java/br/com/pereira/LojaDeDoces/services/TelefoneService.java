package br.com.pereira.LojaDeDoces.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pereira.LojaDeDoces.model.Telefone;
import br.com.pereira.LojaDeDoces.repository.TelefoneRepository;

@Service
public class TelefoneService {

	@Autowired
    private TelefoneRepository tRep;
	
	public Telefone findById(String idTelefone) {
		return tRep.findById(idTelefone).orElseThrow(
                () -> new ResourceNotFoundException(idTelefone + " inválido")
        );
	}
	
	public Optional<Telefone> findByIdForOptional(String idTelefone) {
		return tRep.findById(idTelefone);
	}
	
	public List<Telefone> findAll() {
		return tRep.findAll();
	}
	
	public void save(Telefone t) {
		tRep.save(t);
	}
	
	public void delete(Telefone t) {
		tRep.delete(t);
	}

	public String putNewTelefone(String numAntigo, String numNovo) {
		return tRep.putNewTelefone(numAntigo, numNovo);
	}
}
