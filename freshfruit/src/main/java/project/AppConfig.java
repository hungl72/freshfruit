package project;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
@ComponentScans({ @ComponentScan("project") })
@EnableWebMvc
public class AppConfig {

	// IOC
//	@Bean("viewResolver")
//	public InternalResourceViewResolver viewResolver() {
//		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//		viewResolver.setPrefix("/WEB-INF/jsp/views/");
//		viewResolver.setSuffix(".jsp");
//		viewResolver.setOrder(0);
//		return viewResolver;
//	}

	@Bean // đ�?c thông tin message
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean(name = "multipartResolver") // upload file
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		// multipartResolver.setMaxUploadSize(100000);
		return multipartResolver;
	}

	@Bean(name = "viewResolverTiles") //tiles
	public ViewResolver getViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		// TilesView 3
		viewResolver.setViewClass(TilesView.class);
		return viewResolver;
	}

	@Bean(name = "tilesConfigurer")
	public TilesConfigurer getTilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		// TilesView 3
		tilesConfigurer.setDefinitions(
																"/WEB-INF/templates/freshfruit-template.xml",
																"/WEB-INF/templates/auth-user-template.xml",
																"/WEB-INF/templates/payment-success.xml",
																"/WEB-INF/templates/error-404.xml",
																"/WEB-INF/templates/auth-admin-template.xml",
																"/WEB-INF/templates/admin-template.xml"
															);
		return tilesConfigurer;
	}

}
