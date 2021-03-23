package jpabook.jpashop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class JpashopApplication {

	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorProvider() {
		//실무에서는 세션 정보나, 스프링 시큐리티 로그인 정보에서 ID를 받음
		return () -> Optional.of(UUID.randomUUID().toString());
	}
}
