/**
 *
 */
package singh.mahabir.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger Configuration
 * 
 * @author Mahabir Singh
 *
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Value("${info.version}")
    private String version;

    @Bean
    public Docket productApi() {
	return new Docket(DocumentationType.SWAGGER_2).select()
		.apis(RequestHandlerSelectors.basePackage("singh.mahabir.controller"))
//		.paths(regex("/.*Controller"))
		.build().apiInfo(metaData());
    }

    private ApiInfo metaData() {
	return new ApiInfo("Image-Store-Service REST API", "Spring Boot REST API for Image-Store-Service", version,
		"Terms of service",
		new Contact("Mahabir Singh", "https://github.com/singhmahabir/image-store-service",
			"mahabir.s@hotmail.com"),
		"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
    }
}