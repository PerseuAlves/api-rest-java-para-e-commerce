package br.com.pereira.LojaDeDoces.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pereira.LojaDeDoces.model.Endereco;
import br.com.pereira.LojaDeDoces.repository.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
    private EnderecoRepository eRep;
	
	public Optional<List<Endereco>> findByCep(int cepId) {
		return eRep.findByEnderecoIdCep(cepId);
	}
	
	public Optional<Endereco> findByCepLogradouroNumero(
			int cepId, String logradouroId, int numeroId) {
		return eRep.findByEnderecoIdCepAndEnderecoIdLogradouroAndEnderecoIdNumero(
				cepId, logradouroId, numeroId);
	}
	
	public Optional<Endereco> findByCepLogradouroNumeroBairroCidade(
			int cepId, String logradouroId, int numeroId, String bairroId, 
			String cidadeId) {
		return eRep.findByEnderecoIdCepAndEnderecoIdLogradouroAndEnderecoIdNumeroAndEnderecoIdBairroAndEnderecoIdCidade(
				cepId, logradouroId, numeroId, bairroId, cidadeId);
	}
	
	public List<Endereco> findAll() {
		return eRep.findAll();
	}
	
	public Optional<Endereco> findByCepLogradouroNumeroBairroCidadePost(Endereco e) {
		return eRep.findByEnderecoIdCepAndEnderecoIdLogradouroAndEnderecoIdNumeroAndEnderecoIdBairroAndEnderecoIdCidade(
				e.getEnderecoId().getCep(), e.getEnderecoId().getLogradouro(), 
				e.getEnderecoId().getNumero(), e.getEnderecoId().getBairro(), 
				e.getEnderecoId().getCidade());
	}
	
	public void save(Endereco e) {
		eRep.save(e);
	}
	
	public void spPutNewEndereco(Endereco[] e) {
		eRep.spPutNewEndereco(e[0].getEnderecoId().getCep(), 
        		e[0].getEnderecoId().getLogradouro(), 
        		e[0].getEnderecoId().getNumero(), 
        		e[0].getEnderecoId().getBairro(), 
        		e[0].getEnderecoId().getCidade(), 
        		e[1].getEnderecoId().getCep(), 
        		e[1].getEnderecoId().getLogradouro(), 
        		e[1].getEnderecoId().getNumero(), 
        		e[1].getEnderecoId().getBairro(), 
        		e[1].getEnderecoId().getCidade(),
        		e[1].getComplemento());
	}
	
	public void delete(Endereco e) {
		eRep.delete(e);
	}
}
