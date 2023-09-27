package com.example.demo;

import com.example.demo.domain.Board;
import com.example.demo.domain.Comment;
import com.example.demo.domain.Member;
import com.example.demo.domain.Team;
import com.example.demo.infrastructure.BoardRepository;
import com.example.demo.infrastructure.CommentRepository;
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
    public CommandLineRunner commandLineRunner(MemberRepository memberRepository,
                                               TeamRepository teamRepository,
                                               BoardRepository boardRepository,
                                               CommentRepository commentRepository) {
        return args -> {
            Team team1 = teamRepository.save(new Team("teamA"));
            Team team2 = teamRepository.save(new Team("teamB"));

            memberRepository.save(new Member(team1, "member1", 10));
            memberRepository.save(new Member(team2, "member2", 20));
            memberRepository.save(new Member(team2, "member3", 30));

            Board board1 = boardRepository.save(new Board("title1", "content1"));
            Board board2 = boardRepository.save(new Board("title2", "content2"));

            commentRepository.save(new Comment(board1, "board1-comment1"));
            commentRepository.save(new Comment(board1, "board1-comment2"));
            commentRepository.save(new Comment(board1, "board1-comment3"));
            commentRepository.save(new Comment(board2, "board2-comment1"));
            commentRepository.save(new Comment(board2, "board2-comment2"));
        };
    }

}
