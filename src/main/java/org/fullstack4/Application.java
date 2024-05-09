package org.fullstack4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Application {
	// @EnableJpaAuditing : BaseEntity 에서 사용한 @EntityListeners(value = {AuditingEntityListener.class}) 활성화해주기위해 필요함. => Jpa 감사(감독?) 활성화
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
