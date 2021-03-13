package gama.bankline.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gama.bankline.model.Conta;
import gama.bankline.model.Usuario;
import gama.bankline.repository.ContaRepository;
import gama.bankline.repository.UsuarioRepository;

@Service
public class UsuarioService {
	@Autowired
	private UsuarioRepository rep;
	
	@Autowired
	private ContaRepository contaRep;
	
	public List<Usuario> usuarios(){
		return rep.findAll();
	}
	public List<Conta> contas(Integer id){
		return contaRep.findByUsuarioId(id);
	} 
	
	@Transactional
	public void salvar(Usuario usuario) {
		rep.save(usuario);
		
		Conta c1 = new Conta();
		c1.setNumero(usuario.getLogin() + "BANCO");
		c1.setUsuario(usuario);
		
		Conta c2 = new Conta();
		c2.setNumero(usuario.getLogin() + "CREDITO");
		c2.setUsuario(usuario);
	
		contaRep.save(c1);
		contaRep.save(c2);
		
	}
}
