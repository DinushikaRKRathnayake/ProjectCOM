package com.test.projectcom.util;

import com.fasterxml.classmate.TypeResolver;
import com.test.projectcom.bean.Response;
import com.test.projectcom.bean.ResponseError;
import com.test.projectcom.bean.ResponseSuccess;
import com.test.projectcom.bean.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)

public class SwaggerConfig {
    @Bean
    public Docket api() {

        TypeResolver typeResolver = new TypeResolver();

        return new Docket(DocumentationType.SWAGGER_2)
                .host("localhost:8088")
                .apiInfo(generateAPIInfo())
                .select()
                //Here adding base package to scan controllers. This will scan only controllers inside
                //specific package and include in the swagger documentation
                .apis(RequestHandlerSelectors.basePackage("com.test.projectcom.controller"))
                .paths(PathSelectors.any())
                .build().additionalModels(
                        typeResolver.resolve(Transaction.class),
                        typeResolver.resolve(Response.class),
                        typeResolver.resolve(ResponseSuccess.class),
                        typeResolver.resolve(ResponseError.class));
    }

    //Api information
    private ApiInfo generateAPIInfo() {
        return new ApiInfo("PROJECT COM", "PROJECT COM API REFERENCE", "1.00",
                "", getContacts(), "", "", new ArrayList());
    }

    // Developer Contacts
    private Contact getContacts() {
        return new Contact("Dinushika Rathnayake", "", "dinushika.r917@gmail.com");
    }

}
