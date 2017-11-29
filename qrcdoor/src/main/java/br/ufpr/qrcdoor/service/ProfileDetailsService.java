package br.ufpr.qrcdoor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.ufpr.qrcdoor.entity.Estrutura;
import br.ufpr.qrcdoor.entity.Pessoa;
import br.ufpr.qrcdoor.entity.RoleEnum;
import br.ufpr.qrcdoor.repository.EstruturaRepository;
import br.ufpr.qrcdoor.repository.PessoaRepository;

@Service
public class ProfileDetailsService implements UserDetailsService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private EstruturaRepository estruturaRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public UserDetails loadUserByLoginAndPassword(String login, String senha) throws UsernameNotFoundException {
		Estrutura estruturaProfile;
		Pessoa pessoaProfile;
		List<GrantedAuthority> grants = new ArrayList<GrantedAuthority>();
		try {
			estruturaProfile = this.estruturaRepository.findOne(Long.valueOf(login));
			grants.add(new SimpleGrantedAuthority(RoleEnum.ESTRUTURA.getValue()));
			if (estruturaProfile == null || !new BCryptPasswordEncoder().matches(senha, estruturaProfile.getSenha())) {
				throw new UsernameNotFoundException("Usuário e senha inválidos.");
			}
			return new User(estruturaProfile.getId().toString(), estruturaProfile.getSenha(), grants);
		} catch(Exception e) {
			pessoaProfile = this.pessoaRepository.loadProfile(login);
			if (pessoaProfile == null || !new BCryptPasswordEncoder().matches(senha, pessoaProfile.getSenha())) {
				throw new UsernameNotFoundException("Usuário e senha inválidos.");
			}
			if (pessoaProfile.getRole() != null) {
				grants.add(new SimpleGrantedAuthority(pessoaProfile.getRole().getValue()));
			}
			return new User(pessoaProfile.getLogin(), pessoaProfile.getSenha(), grants);
		}
		
	}
	
}
