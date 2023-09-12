package com.example.demo;

import com.example.demo.domain.Member;
import com.example.demo.domain.MemberRole;
import com.example.demo.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(MemberRepository repository) {
		return args -> {
			repository.save(new Member("member1", MemberRole.NORMAL, 10));
			repository.save(new Member("member2", MemberRole.NORMAL, 20));
			repository.save(new Member("member3", MemberRole.NORMAL, 30));
			repository.save(new Member("member4", MemberRole.ADMIN, 40));
			repository.save(new Member("member5", MemberRole.ADMIN, 50));
		};
	}

}
