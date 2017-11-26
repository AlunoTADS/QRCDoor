package br.ufpr.qrcdoor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import br.ufpr.qrcdoor.service.ProfileDetailsService;

@Component
public class ProfileAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private ProfileDetailsService profileDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String login = authentication.getName();
		String senha = authentication.getCredentials().toString();
		UserDetails user = profileDetailsService.loadUserByLoginAndPassword(login, senha);
		return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
