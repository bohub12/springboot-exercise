package com.example.demo.repository.team;

import com.example.demo.domain.team.Team;

import java.util.List;

public interface TeamQueryDslRepository {

    Long countTeams();


    List<Team> findTeamsHavingMembersByArgument(long memberCount);
}
