package com.template.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: ZhouYuJi
 * @Description: Swagger配置Bean
 *            注: 要在启动类上配置@EnableSwagger2
 * @Date: Created in 16:47 2019/8/19
 * @Modified by:
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.demo2.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("CityDo 城市大数据运营")
                .contact(new Contact("ZhouYuJi","http://www.citydo.com.cn","zhouyuji@citydo.com.cn"))
                .description("更懂城市更懂你")
//                .termsOfServiceUrl("http://www.citydo.com.cn")
                .version("1.0")
                .build();
    }
}
