package pl.edu.pjatk.s14310.mas.ooops;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories("pl.edu.pjatk.s14310.mas.ooops.repositories")
@EntityScan("pl.edu.pjatk.s14310.mas.ooops.*")
@ComponentScan(basePackages = { "pl.edu.pjatk.s14310.mas.ooops.*" })
public class OoopsApplication {

    public static void main(String[] args) {
        SpringApplication.run(OoopsApplication.class, args);


    }
}
