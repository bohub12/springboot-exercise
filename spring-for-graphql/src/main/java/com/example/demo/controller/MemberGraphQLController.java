package com.example.demo.controller;

import com.example.demo.controller.dto.CreateMemberRequestDto;
import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/graphql")
@RequiredArgsConstructor
public class MemberGraphQLController {

    private final MemberRepository repository;

    /**
     *  @MutationMapping은 @PostMapping과 같은 어노테이션으로 graphql에 Mutation에 사용됩니다.
     *  graphql은 endpoint과 하나이므로 @MutationMapping 어노테이션만 지정해 주고 다른 설정은 필요 없습니다.
     */
    @MutationMapping
    public Member saveMember(@Argument CreateMemberRequestDto body) { // @Argument 는 @RequestBody, @RequestParam과 같은 인자값을 지정해줄 때 사용합니다.
        return repository.save(body.toEntity());
    }

    // @QueryMapping도 @GetMapping과 같은 어노테이션입니다
    @QueryMapping
    public Member getMember(@Argument Long id) {
        return repository.findById(id).orElseThrow();
    }

    @QueryMapping
    public List<Member> getMemberList() {
        return repository.findAll();
    }

//    @QueryMapping()
//    public Member findOne() {
//
//    }
//
//    @QueryMapping
//    public List<Member> findAll() {
//
//    }
}
