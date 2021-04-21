package com.maldaliza.freeboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing		// JPA Auditing을 활성화 하기 위한 애노테이션
@SpringBootApplication
public class FreeboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreeboardApplication.class, args);
	}
}
