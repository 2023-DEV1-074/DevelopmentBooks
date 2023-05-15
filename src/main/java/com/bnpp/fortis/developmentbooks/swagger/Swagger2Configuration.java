package com.bnpp.fortis.developmentbooks.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
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
@EnableWebMvc
public class Swagger2Configuration extends WebMvcConfigurerAdapter {


    @Value("${swagger.app.base.url}")
    private String appBaseUrl;
    @Value("${swagger.contact.name}")
    private String contactName;
    @Value("${swagger.contact.email}")
    private String contactEmail;
    @Value("${swagger.contact.license}")
    private String contactLicense;
    @Value("${swagger.contact.version}")
    private String contactVersion;


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()

                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }


    private ApiInfo apiInfo() {

        return new ApiInfoBuilder().title(" Development Book REST APIs")
                .description("REST APIs for  Development Book list and Calculate best price for the books")
                .termsOfServiceUrl(appBaseUrl)
                .contact(new Contact(contactName, appBaseUrl, contactEmail)).license(contactLicense)
                .licenseUrl(appBaseUrl).version(contactVersion).build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
