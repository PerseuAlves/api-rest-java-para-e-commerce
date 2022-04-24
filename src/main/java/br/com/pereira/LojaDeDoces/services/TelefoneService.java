package br.com.pereira.LojaDeDoces.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pereira.LojaDeDoces.model.Telefone;
import br.com.pereira.LojaDeDoces.repository.TelefoneRepository;
import br.com.pereira.LojaDeDoces.services.exception.IllegalArgumentException;
import br.com.pereira.LojaDeDoces.services.exception.ResourceNotFoundException;

@Service
public class TelefoneService {

	@Autowired
    private TelefoneRepository tRep;
	
	public List<Telefone> findAll() {
		return tRep.findAll();
	}
	
	public Telefone findById(String idTelefone) {
		return tRep.findById(idTelefone).orElseThrow(
                () -> new ResourceNotFoundException()
        );
	}
	
	public void save(Telefone t) throws IllegalArgumentException {
		validaTelefone(t);
		tRep.save(t);
	}

	public void delete(Telefone t) throws IllegalArgumentException {
		tRep.delete(t);
	}

	public String putNewTelefone(String numAntigo, String numNovo) {
		return tRep.putNewTelefone(numAntigo, numNovo);
	}
	
	private void validaTelefone(Telefone t) {
		try {
			Long.parseLong(t.getNumero());
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException();
		}		
	}
}
