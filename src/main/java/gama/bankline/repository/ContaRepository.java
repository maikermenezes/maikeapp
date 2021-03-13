package gama.bankline.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import gama.bankline.model.Conta;

public interface ContaRepository extends JpaRepository<Conta, Integer> {
	List<Conta> findByUsuarioId(Integer id);
}
