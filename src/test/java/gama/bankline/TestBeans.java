package gama.bankline;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import gama.bankline.service.ServiceCore;

@Configuration
public class TestBeans {
	@Bean
	public ServiceCore serviceCore() {
		System.out.println("criando nosso ServiceCore");
		return new ServiceCore();
	}
}
