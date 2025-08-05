package com.mx.ApiGatewayEmpleados.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")//le va aplicar cors a todas las rutas
				.allowedHeaders("*")//le va a pertimitir creacion de encabezados
				.allowedOrigins("http://localhost:4200")//permite la conexion solo con dominio angular
				.allowedMethods("*")//permite todos los metodos(GET, POST, PUT, ....)
				.allowCredentials(true);//permite los las dredenciales
		
	}
	
/*
    @Bean
    CorsWebFilter corsWebFilter() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOriginPatterns(Arrays.asList("http://localhost:4200")); 
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        
        System.out.println(source);

        return new CorsWebFilter(source);
    }*/
}
