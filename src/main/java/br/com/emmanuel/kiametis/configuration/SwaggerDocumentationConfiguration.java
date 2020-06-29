package br.com.emmanuel.kiametis.configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerDocumentationConfiguration {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Spring Boot Application by Emmanuel Kiametis")
            .description("This API was developed to provide resources to manage cities and customers.")
            .license("")
            .termsOfServiceUrl("")
            .version("v1")
            .contact(new Contact("Emmanuel Kiametis","", "kiametis91@gmail.com"))
            .build();
    }

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("br.com.emmanuel.kiametis.api"))
                    .build()
                .directModelSubstitute(LocalDate.class, Date.class)
                .directModelSubstitute(LocalDateTime.class, Date.class)
                .apiInfo(apiInfo());
    }

}
