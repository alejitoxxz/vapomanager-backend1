package co.edu.uco.vapomanager.init;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"co.edu.uco.vapomanager"})
public class VapomanagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(VapomanagerApplication.class, args);
	}

}
