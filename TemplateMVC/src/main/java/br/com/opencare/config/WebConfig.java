package br.com.opencare.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@EnableWebMvc
@Configuration
@ComponentScan({ "br.com.opencare" })
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AutorizadorInterceptor());
	}

	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
		source.setBasename("classpath:messages");
		source.setDefaultEncoding("UTF-8");
		return source;
	}
	/*
	 * @Bean(name = "simpleMappingExceptionResolver") public
	 * SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
	 * SimpleMappingExceptionResolver r = new SimpleMappingExceptionResolver();
	 * 
	 * r.setDefaultErrorView("errorPage"); // No default return r; }
	 * 
	 * /*
	 * 
	 * @Bean public SessionLocaleResolver localeResolver() {
	 * SessionLocaleResolver locale = new SessionLocaleResolver(); Locale en =
	 * new Locale("en"); locale.setDefaultLocale(en); return locale; }
	 */

}