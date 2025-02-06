package br.com.luger.dev.precad.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/imagens/**")
                .addResourceLocations("classpath:/static/imagens/");

        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/");
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry.addMapping("/**")
                .allowedOrigins("https://pre-cadastro.luger-academia.com.br")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization")
                .allowCredentials(true);

        registry.addMapping("/mgmt/isfacc")
                .allowedOrigins("https://pre-cadastro.luger-academia.com.br")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
                .allowedHeaders("Content-Type", "Authorization")
                .allowCredentials(true);

        registry.addMapping("/**")
                .allowedOrigins("https://pre-cadastro.luger-academia.com.br") // Adjust this to match your client-side domain
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("Authorization", "Content-Type", "X-Requested-With", "accept", "Origin", "Access-Control-Request-Method", "Access-Control-Request-Headers")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                .allowCredentials(true).maxAge(3600);


    }
}