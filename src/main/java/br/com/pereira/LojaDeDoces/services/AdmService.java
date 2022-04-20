package br.com.pereira.LojaDeDoces.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pereira.LojaDeDoces.model.Adm;
import br.com.pereira.LojaDeDoces.repository.AdmRepository;

@Service
public class AdmService {

	@Autowired
    private AdmRepository aRep;
	
	public Adm findById(int idAdm) {
		return aRep.findById(idAdm).orElseThrow(
                () -> new ResourceNotFoundException(idAdm + " inválido")
        );
	}
	
	public void save(Adm a) {
		aRep.save(a);
	}
}
