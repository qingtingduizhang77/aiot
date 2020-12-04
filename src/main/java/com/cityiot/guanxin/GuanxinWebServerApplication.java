package com.cityiot.guanxin;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import lombok.extern.slf4j.Slf4j;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Slf4j
@SpringBootApplication
@EnableJpaAuditing
@EnableAspectJAutoProxy
@EnableAsync
@EnableTransactionManagement
public class GuanxinWebServerApplication extends SpringBootServletInitializer {
	
	    @Override  
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {  
	        builder.sources(this.getClass());  
	        return super.configure(builder);  
	    }  

	
	
	public static void main(String[] args) {				
		SpringApplication.run(GuanxinWebServerApplication.class, args);
		System.out.println("系统启动");
		//log.info("系统启动");
	}
	
}
