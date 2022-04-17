package br.com.pereira.LojaDeDoces.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import br.com.pereira.LojaDeDoces.model.Endereco;
import br.com.pereira.LojaDeDoces.model.compositeID.EnderecoId;

public interface EnderecoRepository extends JpaRepository<Endereco, EnderecoId> {

	@Procedure(name = "Endereco.spPutNewEndereco")
    String spPutNewEndereco(
    		@Param("cepAntigo") int cepAntigo, 
    		@Param("logradouroAntigo") String logradouroAntigo, 
    		@Param("numeroAntigo") int numeroAntigo, 
    		@Param("bairroAntigo") String bairroAntigo, 
    		@Param("cidadeAntigo") String cidadeAntigo, 
    		@Param("cepNovo") int cepNovo, 
    		@Param("logradouroNovo") String logradouroNovo, 
    		@Param("numeroNovo") int numeroNovo, 
    		@Param("bairroNovo") String bairroNovo, 
    		@Param("cidadeNovo") String cidadeNovo,
			@Param("complementoNovo") String complementoNovo);
	Optional<List<Endereco>> findByEnderecoIdCep(int cep);
	Optional<Endereco> findByEnderecoIdCepAndEnderecoIdLogradouroAndEnderecoIdNumero(int cep, String logradouro, int numero);
	Optional<Endereco> findByEnderecoIdCepAndEnderecoIdLogradouroAndEnderecoIdNumeroAndEnderecoIdBairroAndEnderecoIdCidade(int cep, String logradouro, int numero, String bairro, String cidade);
}
