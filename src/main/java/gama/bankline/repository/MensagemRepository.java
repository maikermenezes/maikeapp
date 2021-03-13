package gama.bankline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gama.bankline.dto.MensagemDto;
import gama.bankline.model.Mensagem;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Integer>  {
	@Query(value = "SELECT new gama.bankline.dto.MensagemDto (m.conteudo) FROM Mensagem m ")//filtros
	public List<MensagemDto> listDtos();
	
	public List<Mensagem> findByTitulo(String titulo);
}
