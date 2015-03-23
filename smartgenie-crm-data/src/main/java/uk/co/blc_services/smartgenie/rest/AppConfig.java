package uk.co.blc_services.smartgenie.rest;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping;

@Configuration
public class AppConfig {
	
	@Bean
	public String daveProp(){
		return "davepropvalue";
	}
	
	@Bean
	public LocaleResolver localeResolver(){
		SessionLocaleResolver resolver = new SessionLocaleResolver();
		resolver.setDefaultLocale(Locale.UK);
		return resolver;
	}
	
	@Bean
	public AsyncHandlerInterceptor localeChangeInterceptor(){
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("language");
		return interceptor;
	}
	
	@Bean
	public ControllerClassNameHandlerMapping classNameHandlerMapping(){
		ControllerClassNameHandlerMapping mapping = new ControllerClassNameHandlerMapping();
		mapping.setInterceptors(new Object[]{localeChangeInterceptor()});
		return mapping;
	}
	
	@Bean
	public MessageSource messageSource(){
		ResourceBundleMessageSource msgSrc = new ResourceBundleMessageSource();
		msgSrc.setBasename("locale/messages");
		return msgSrc;
	}
	
	
}
/*
 * <beans:bean id="localeResolver" class="org.springframework.web.servlet.i18n.SessionLocaleResolver">
	<beans:property name="defaultLocale" value="en_US" />
</beans:bean>
 
<beans:bean id="localeChangeInterceptor" class="org.springframework.web.servlet.i18n.LocaleChangeInterceptor">
	<beans:property name="paramName" value="language" />
</beans:bean>
 
<beans:bean class="org.springframework.web.servlet.mvc.support.ControllerClassNameHandlerMapping" >
	<beans:property name="interceptors">
	   <beans:list>
		<beans:ref bean="localeChangeInterceptor" />
	   </beans:list>
	</beans:property>
</beans:bean>
 
<beans:bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
	<beans:property name="basename" value="locale/messages" />
</beans:bean>
*/
