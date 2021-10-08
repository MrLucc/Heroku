package com.baependi.projetoIntegrador.configuration;

import java.util.ArrayList;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;




@Configuration
public class SwaggerConfig {

	
	@Bean
	public Docket api() {
		return new Docket (DocumentationType.SWAGGER_2)
		.select()
		.apis (RequestHandlerSelectors
		.basePackage ("com.baependi.projetoIntegrador.controller"))
		.paths (PathSelectors.any())
		.build()
		.apiInfo(metadata())
		.useDefaultResponseMessages(false)
		.globalResponses(HttpMethod.GET, responseMessageForGET())
		.globalResponses(HttpMethod.POST, responseMessageForGET())
		.globalResponses(HttpMethod.PUT, responseMessageForGET())
		.globalResponses(HttpMethod.DELETE, responseMessageForGET());
	
	}
	
	
	
	public static ApiInfo metadata() {
		
		return new ApiInfoBuilder()
			.title("Baependi")
			.description ("E-commerce sustentável")
			.version ("1.0.0")
			.license ("Apache License Vesion 2.0")
		    .licenseUrl ("http://localhost:8080/swagger-ui/")
		    .contact(contact())
		    .build();
	}
	
	
	public static Contact contact() {
		
		return new Contact ("Equipe 6 - Turma 29", "https://github.com/MrLucc/Project-Baependi", "comingsoon@gmail.com" );
	}

	
	private static List<Response> responseMessageForGET() {
        return new ArrayList<Response>() {
            private static final long serialVersionUID = 1L;

            {

                add(new ResponseBuilder().code("200").description("SUCESSO!🙌").build());
                add(new ResponseBuilder().code("201").description("OBJETO CRIADO!👍").build());
                add(new ResponseBuilder().code("401").description("NÃO AUTORIZAD0!🚧").build());
                add(new ResponseBuilder().code("403").description("PROIBIDO!🏴‍☠️").build());
                add(new ResponseBuilder().code("404").description("NÃO ENCONTROU!👀").build());
                add(new ResponseBuilder().code("500").description("ERRO!☠").build());
            }
        };
    }
	
}
