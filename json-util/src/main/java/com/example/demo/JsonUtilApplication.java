package com.example.demo;

import com.example.demo.domain.Member;
import com.example.demo.domain.Team;
import com.example.demo.infrastructure.MemberRepository;
import com.example.demo.infrastructure.TeamRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JsonUtilApplication {

	public static void main(String[] args) {
		SpringApplication.run(JsonUtilApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(MemberRepository memberRepository, TeamRepository teamRepository) {
		return args -> {
			Team team1 = teamRepository.save(new Team("teamA"));
			Team team2 = teamRepository.save(new Team("teamB"));

			memberRepository.save(new Member(team1, "member1", 10));
			memberRepository.save(new Member(team2, "member2", 20));
			memberRepository.save(new Member(team2, "member3", 30));
		};
	}

}
