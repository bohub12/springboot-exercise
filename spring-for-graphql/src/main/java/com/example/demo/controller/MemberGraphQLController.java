package com.example.demo.controller;

import com.example.demo.controller.dto.CreateMemberRequestDto;
import com.example.demo.controller.dto.UpdateMemberRequestDto;
import com.example.demo.domain.Member;
import com.example.demo.domain.MemberRole;
import com.example.demo.repository.MemberRepository;
import graphql.schema.DataFetchingFieldSelectionSet;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MemberGraphQLController {

    private final MemberRepository repository;

    /**
     * @MutationMapping은 @PostMapping과 같은 어노테이션으로 graphql에 Mutation에 사용됩니다.
     * graphql은 endpoint과 하나이므로 @MutationMapping 어노테이션만 지정해 주고 다른 설정은 필요 없습니다.
     */
    @MutationMapping
    public Member saveMember(@Argument CreateMemberRequestDto body) { // @Argument 는 @RequestBody, @RequestParam과 같은 인자값을 지정해줄 때 사용합니다.
        return repository.save(body.toEntity());
    }

    @MutationMapping
    @Transactional
    public Member updateMember(@Argument UpdateMemberRequestDto body) {
        Member member = repository.findById(body.id()).orElseThrow();
        member.update(body.name(), body.role(), body.age());

        return member;
    }

    @MutationMapping
    @Transactional
    public Member updateField(@Argument long id, @Argument String name, @Argument MemberRole role, @Argument Integer age) {
        Member member = repository.findById(id).orElseThrow();
        member.updateIfNotNull(name, role, age);

        return member;
    }

    // @QueryMapping도 @GetMapping과 같은 어노테이션입니다
    @QueryMapping
    public Member getMember(@Argument Long id, DataFetchingFieldSelectionSet selectionSet) {
        if (selectionSet.contains("id"))                // 응답 schema 에 id가 있다면 true 아니면 false
            log.info("response schema contain [id] schema");
        if (selectionSet.contains("name"))
            log.info("response schema contain [name] schema");
        if (selectionSet.contains("role"))
            log.info("response schema contain [role] schema");
        if (selectionSet.contains("age"))
            log.info("response schema contain [age] schema");

        return repository.findById(id).orElseThrow();
    }

    @QueryMapping
    public List<Member> getMemberList() {
        return repository.findAll();
    }
}
