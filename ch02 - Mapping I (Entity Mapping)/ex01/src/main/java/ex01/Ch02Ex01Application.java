package ex01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"ex01.domain"})
public class Ch02Ex01Application {
    public static void main(String[] args) {
        SpringApplication.run(Ch02Ex01Application.class, args);
    }
}