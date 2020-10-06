package com.abdelysf.edulocity.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    public Docket edulocityApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(getApiInfo());
    }

    private ApiInfo getApiInfo() {
        return  new ApiInfoBuilder()
                .title("Edulocity API")
                .version("1.0")
                .description("API for a learning management system called EduLocity")
                .contact(new Contact("EL Youssfi Alaoui Abdellah","https://www.linkedin.com/in/abdelysf/","abdel.ysf@gmail.com"))
                .license("Apache Licence Version 2.0")
                .build();
    }
}
