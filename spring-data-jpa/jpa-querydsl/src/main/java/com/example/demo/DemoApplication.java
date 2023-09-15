package com.example.demo;

import com.example.demo.domain.member.Address;
import com.example.demo.domain.member.Member;
import com.example.demo.domain.member.MemberRole;
import com.example.demo.domain.team.Team;
import com.example.demo.repository.member.MemberRepository;
import com.example.demo.repository.team.TeamRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
}
