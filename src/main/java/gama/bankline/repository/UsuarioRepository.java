package gama.bankline.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import gama.bankline.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	public Optional <Usuario> findByLogin(String login);
	public boolean existsByLogin(String login);
}
