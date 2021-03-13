package gama.bankline;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BanklineApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BanklineApiApplication.class, args);
	}
	
	@Bean
    public CommandLineRunner run(TestComponent t) throws Exception {
        return args -> {
        	t.test();
        };
    }
}
