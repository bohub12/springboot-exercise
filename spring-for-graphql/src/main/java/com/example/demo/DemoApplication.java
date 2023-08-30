package com.example.demo;

import com.example.demo.member.Member;
import com.example.demo.member.MemberRepository;
import com.example.demo.member.MemberRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(MemberRepository repository) {
		return args -> {
			repository.save(new Member(1L, "member1", MemberRole.NORMAL, 10));
			repository.save(new Member(2L, "member2", MemberRole.NORMAL, 20));
			repository.save(new Member(3L, "member3", MemberRole.NORMAL, 30));
			repository.save(new Member(4L, "member4", MemberRole.ADMIN, 40));
			repository.save(new Member(5L, "member5", MemberRole.ADMIN, 50));
		};
	}

}
