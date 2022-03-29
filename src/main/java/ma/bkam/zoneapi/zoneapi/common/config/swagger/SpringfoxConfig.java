package ma.bkam.zoneapi.zoneapi.common.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;

import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static ma.bkam.zoneapi.zoneapi.common.config.swagger.SwaggerDocConst.ZONEAPI_CONTROLLER_DESCRIPTION;

@Configuration
@EnableSwagger2
public class SpringfoxConfig {

    @Value(ZONEAPI_CONTROLLER_DESCRIPTION)
    String boZoAp;


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .forCodeGeneration(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("ma.bkam.zoneapi.zoneapi"))
                .paths(PathSelectors.any())
                .build()
                .tags(
                        new Tag(ZONEAPI_CONTROLLER_DESCRIPTION, boZoAp)
                );
    }

}
