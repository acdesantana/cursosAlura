package br.com.alura.forum.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.alura.forum.config.enums.Role;
import br.com.alura.forum.repository.UsuarioRepository;

@EnableWebSecurity
@Configuration
@Profile(value = {"prod", "test"})
public class SecurityConfigurations extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TokenService tokenService;
	
	@Override
	@Bean // o spring sabe que este metodo devolvera o AutenticationManager onde ele deve ser injetado
	protected AuthenticationManager authenticationManager() throws Exception{
		return super.authenticationManager();
	}
	
	//configuracoes de autenticacao
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	//configuracoes de autorizacao
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/topicos").permitAll()
		.antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
		.antMatchers(HttpMethod.GET, "/actuator/**").permitAll() // nao deixar o permitAll em PRD!
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.antMatchers(HttpMethod.DELETE, "/topicos").hasRole(Role.MODERADOR.toString())
		.anyRequest().authenticated()
		.and().csrf().disable()  //cross site request forgery, um tipo de ataque hacker. Pode ser desabilitado pois a API nao esta usando sessao (JSESSIONID)
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);
		//.and().formLogin(); Usado na Autenticacao por sessao. Porém, microserviços sao STATELESS e a autenticação deve ser feita usando token
		
	}
	
	//configuracoes de recusros estaticos (js, css, imagens, etc.)
	@Override
	public void configure(WebSecurity web) throws Exception {
		//http://localhost:8080/swagger-ui.html
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}
	
	
	/*public static void main(String[] args) {
		System.out.println(new BCryptPasswordEncoder().encode("123456"));
	}*/
}
