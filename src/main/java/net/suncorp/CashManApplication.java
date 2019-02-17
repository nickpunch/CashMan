package net.suncorp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.*;
//import springfox.documentation.spi.*;
//import springfox.documentation.spring.web.plugins.*;
//import springfox.documentation.swagger2.annotations.*;

@SpringBootApplication
@ComponentScan(basePackages = {"net.suncorp"})
public class CashManApplication {

    public static void main(String[] args) {
        SpringApplication.run(CashManApplication.class, args);
    }

//    @Configuration
//    @EnableSwagger2
//    public class SwaggerConfig {
//        @Bean
//        public Docket api() {
//            return new Docket(DocumentationType.SWAGGER_2)
//                    .select()
//                    .apis(RequestHandlerSelectors.any())
//                    .paths(PathSelectors.any())
//                    .build();
//        }
//    }
}