package com.cityiot.guanxin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration implements WebMvcConfigurer {
	
	 private CorsConfiguration buildConfig() {
	        CorsConfiguration corsConfiguration = new CorsConfiguration();
	        corsConfiguration.addAllowedOrigin("*");
	        corsConfiguration.setAllowCredentials(true);
	        corsConfiguration.addAllowedHeader("*");
	        corsConfiguration.addAllowedMethod("*");
	      
	        return corsConfiguration;
	    }
	 
	   @Bean
	    public CorsFilter corsFilter() {
	        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", buildConfig());
	        return new CorsFilter(source);
	    }
	   
	   
	   @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    	
	        registry.addResourceHandler("swagger-ui.html")
	                .addResourceLocations("classpath:/META-INF/resources/");
	        registry.addResourceHandler("/webjars/**")
	                .addResourceLocations("classpath:/META-INF/resources/webjars/");

	    }
	
	@Bean
    public Docket api() {
		
		 ParameterBuilder ticketPar = new ParameterBuilder();
	       List<Parameter> pars = new ArrayList<>();
	       ticketPar.name("Authorization").description("user token")
	               .modelRef(new ModelRef("string")).parameterType("header")
	               .required(false).build(); //header中的ticket参数非必填，传空也可以
	       pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数

		
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cityiot.guanxin")) //过滤的接口
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getApiInfo() {
        // 定义联系人信息
     //   Contact contact = new Contact("name","https://baidu.com", "test@test.com");
        return new ApiInfoBuilder()
                .title("aiot")
                .description("描述")
                .version("v2")
               // .license("Apache 2.0")
              //  .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
               // .contact(contact)
                .build();
    }
    
    
}
