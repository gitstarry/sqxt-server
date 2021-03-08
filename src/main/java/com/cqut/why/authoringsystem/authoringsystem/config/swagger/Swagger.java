package com.cqut.why.authoringsystem.authoringsystem.config.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.function.Predicate;

@Configuration
@ComponentScan
@EnableSwagger2
public class Swagger {



    @Bean
    public Docket createRestApi() {
        // swagger 扫描XL的api
        Predicate<RequestHandler> xlSelector = RequestHandlerSelectors.basePackage("com.cqut.why.authoringsystem.authoringsystem.controller")::apply;
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                .apis(xlSelector::test)
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("authoring-system")
                .description("authoring-system API工具")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

}
