package com.example.demo.repository.team;

import com.example.demo.domain.team.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long>, TeamQueryDslRepository {
}
