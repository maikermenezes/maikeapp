package gama.bankline.controller;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gama.bankline.dto.LoginDTO;
import gama.bankline.dto.Sessao;
import gama.bankline.exception.BusinessException;
import gama.bankline.model.Usuario;
import gama.bankline.repository.UsuarioRepository;
import gama.bankline.security.jwt.JWTConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@PostMapping
	public Sessao logar(@RequestBody LoginDTO login) throws Exception {

		if (login == null || login.getUsuario().isEmpty() || login.getSenha().isEmpty()) {
			throw new RuntimeException("Login e senha são requeridos");
		}

		Optional<Usuario> optuser = repository.findByLogin(login.getUsuario());

		Usuario usuario = optuser.get();

		boolean senhaOk = encoder.matches(login.getSenha(),usuario.getPassword());

		if (!senhaOk) {
			//throw new RuntimeException("Senha inválida para o login: " + login.getUsuario());
			throw new BusinessException("400","400.200", "Senha Inválida para o Login " + login.getUsuario());
		}

		// tempo do token = 1 horas
		long tempoToken = 1 * 60 * 60 * 1000;
		Sessao sessao = new Sessao();
		sessao.setDataInicio(new Date(System.currentTimeMillis()));
		sessao.setDataFim(new Date(System.currentTimeMillis() + tempoToken));
		
		sessao.setLogin(usuario.getLogin());

		sessao.setToken(JWTConstants.PREFIX + getJWTToken(sessao));

		return sessao;
	}
	//como vc gerenciaria a nivel de banco o role de um usuario
	private String getJWTToken(Sessao sessao) {
		String role = "ROLE_ADMIN";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(role);

		String token = Jwts.builder().setSubject(sessao.getLogin())
				.claim("authorities",
						grantedAuthorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(sessao.getDataInicio()).setExpiration(sessao.getDataFim())
				.signWith(SignatureAlgorithm.HS512, JWTConstants.KEY.getBytes()).compact();

		return token;
	}
}

