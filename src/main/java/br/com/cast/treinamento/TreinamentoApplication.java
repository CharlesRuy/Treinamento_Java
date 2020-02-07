package br.com.cast.treinamento;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.cast.treinamento.converter.UsuarioConverter;
import br.com.cast.treinamento.model.Usuario;
import br.com.cast.treinamento.service.UsuarioService;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class TreinamentoApplication {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private UsuarioConverter usuarioConverter;

	
	public static void main(String[] args) {
		SpringApplication.run(TreinamentoApplication.class, args);
	}
	
	@PostConstruct
	private void init() {
		if (usuarioService.consultarTodos().isEmpty()) {
			Usuario usuario = new Usuario(null, "roberto.junior@castgroup.com.br", "Roberto Junior",
					new BCryptPasswordEncoder().encode("admin"));
			usuarioService.incluir(usuarioConverter.convertToDTO(usuario));
		}
	}

	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.cast.treinamento.controller"))
				.paths(PathSelectors.any()).build();
	}

}
