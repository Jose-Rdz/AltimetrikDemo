package com.altimetrik.demo;

import java.util.ArrayList;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    private Contact DEFAULT_CONTACT = new Contact("Jose Rdz", "http://localhost:8080", "angel.rgzg@gmail.com");

    private ApiInfo DEFAULT_API_INFO = new ApiInfo(
            "Altimetrik API Document", "Teste documentation for API", "1.0",
            "Terms of service", DEFAULT_CONTACT, "Licence A", "http://licenseA", new ArrayList<>());

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(DEFAULT_API_INFO);
    }
    
}
