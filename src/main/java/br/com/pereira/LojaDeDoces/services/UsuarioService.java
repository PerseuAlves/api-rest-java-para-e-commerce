package br.com.pereira.LojaDeDoces.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.pereira.LojaDeDoces.model.Usuario;
import br.com.pereira.LojaDeDoces.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
    private UsuarioRepository uRep;
	
	public Usuario findById(int idUsuario) {
		return uRep.findById(idUsuario).orElseThrow(
                () -> new ResourceNotFoundException(idUsuario + " inválido")
        );
	}
	
	public Optional<Usuario> findByIdForPostMethod(int idUsuario) {
		return uRep.findById(idUsuario);
	}
	
	public List<Usuario> findAll() {
		return uRep.findAll();
	}
	
	public void save(Usuario u) {
		uRep.save(u);
	}
	
	public void delete(Usuario u) {
		uRep.delete(u);
	}
}
