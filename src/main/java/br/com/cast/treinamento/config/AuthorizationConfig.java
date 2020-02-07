package br.com.cast.treinamento.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * 
 * O Authorization Server é um componente arquitetural supremo para a segurança
 * da API da Web. O Authorization Server atua como um ponto de autorização de
 * centralização que permite que seus aplicativos e pontos de extremidade HTTP
 * identifiquem os recursos do seu aplicativo.
 *
 */

@Configuration
@EnableAuthorizationServer
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

	@Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
	private String clienteId;

	@Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
	private String clienteSecret;

	@Autowired
	private AuthenticationManager authenticationManager;

	/*
	 * Define as configurações de segurança nos endpoints relativos aos tokens de
	 * acesso
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore())
				.accessTokenConverter(tokenEnhancer());
	}
	
	@Bean
	public JwtAccessTokenConverter tokenEnhancer() {
		return new JwtAccessTokenConverter();
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(tokenEnhancer());
	}

	/*
	 * Define configurações para os endpoints de autenticação e geração de tokens.
	 * 
	 * /oauth/check_token e /oauth/token_key
	 *
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()")
				.allowFormAuthenticationForClients();
	}

	/*
	 * Define os detalhes para o acesso da aplicação cliente ao servidor de
	 * autenticação; 
	 * 
	 * https://swagger.io/docs/specification/authentication/oauth2/
	 * 
	 * 
	 * Credenciais de senha do proprietário do recurso (ou apenas senha ) - Requer
	 * login com um nome de usuário e senha. Como nesse caso as credenciais farão
	 * parte da solicitação, esse fluxo é adequado apenas para clientes confiáveis
	 * ​​(por exemplo, aplicativos oficiais liberados pelo provedor de API).
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory().withClient(clienteId).secret(new BCryptPasswordEncoder().encode(clienteSecret))
				.authorizedGrantTypes("password", "refresh_token")
				.scopes("read", "write").accessTokenValiditySeconds(600).
				refreshTokenValiditySeconds(600);
	}

}
