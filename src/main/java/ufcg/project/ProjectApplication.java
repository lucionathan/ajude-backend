package ufcg.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import ufcg.project.filter.TokenFilter;

@SpringBootApplication
@EnableScheduling
public class ProjectApplication {

	@Bean
	public FilterRegistrationBean<TokenFilter> filterJwt() {
		FilterRegistrationBean<TokenFilter> filterRB = new FilterRegistrationBean<TokenFilter>();
		filterRB.setFilter(new TokenFilter());
		filterRB.addUrlPatterns("/getTest");
		return filterRB;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
