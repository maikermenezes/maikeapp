package gama.bankline.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import gama.bankline.model.Mensagem;

@Repository
public class MyRepository {
	@PersistenceContext
	private EntityManager em;
	
	public List<Mensagem> listByTitulo(String titulo){
		Query query = em.createQuery("SELECT m FROM Mensagem m WHERE m.titulo = :titulo");
		
		query.setParameter("titulo", titulo);
		
		return query.getResultList();
	}
}
