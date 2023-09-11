package com.example.demo.controller;

import com.example.demo.config.GraphQlConfig;
import com.example.demo.domain.Member;
import com.example.demo.domain.MemberRole;
import com.example.demo.repository.MemberRepository;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@AutoConfigureGraphQlTester
@Import(GraphQlConfig.class)
class MemberGraphQLControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @Autowired
    private MemberRepository repository;


    @BeforeEach
    void addDummyData() {
        repository.save(new Member("member1", MemberRole.NORMAL, 10));
        repository.save(new Member("member2", MemberRole.NORMAL, 20));
        repository.save(new Member("member3", MemberRole.NORMAL, 30));
        repository.save(new Member("member4", MemberRole.ADMIN, 40));
        repository.save(new Member("member5", MemberRole.ADMIN, 50));
    }

    @AfterEach
    void clear() {
        repository.deleteAll();
    }


    @Test
    void getMember_thenReturnResponse1() {
        long id = repository.findAll().stream().findFirst().orElseThrow().getId();

        String documentName = "getMember";

        graphQlTester.documentName(documentName)
                .variable("id", id)
                .execute()
                .path("$")
                .matchesJson(expected(documentName));
    }

    @Test
    void getMember_thenReturnResponse2() {
        long id = repository.findAll().stream().findFirst().orElseThrow().getId();

        String query = String.format("{ getMember(id: %d) { id name age role }}", id);
        Member member = graphQlTester.document(query)
                .execute()
                .path("data.getMember")
                .entity(Member.class)
                .get();

        assertThat(member).isNotNull();
        assertThat(member.getAge()).isEqualTo(10);
    }


    @Test
    void getMemberList_thenReturnResponse1() {
        String documentName = "getMemberList";

        List<Member> memberList = graphQlTester.documentName(documentName)
                .execute()
                .path("data.getMemberList[*]")
                .entityList(Member.class)
                .get();

        assertThat(memberList.size()).isEqualTo(5);
    }

    @Test
    void saveMember_thenReturnResponse1() {
        String name = "멤버";
        MemberRole role = MemberRole.ADMIN;
        int age = 15;

        HashMap<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("role", role);
        map.put("age", age);

        String documentName = "saveMember";

        graphQlTester.documentName(documentName)
                .variable("body", map)
                .execute()
                .path("$")
                .matchesJson(expected(documentName));
    }

    @SneakyThrows
    public static String expected(String fileName) {
        Path path = Paths.get("src/test/resources/graphql-response/" + fileName + "ExpectedResponse.json");
        return new String(Files.readAllBytes(path));
    }
}