# GraphQL에 대하여

페이스북에서 개발된 쿼리 언어인 `GraphQL`은 기존에 많이 사용되던 RESTful API와는 다른 형식의 인터페이스입니다.

가장 큰 차이로는 RESTful API가 URL, Method 등의 조합으로 다양한 Endpoint를 가지는 반면, GraphQL은 단 하나의 Endpoint 만으로 데이터를 요청하고 응답받을 수 있습니다.

![](https://velog.velcdn.com/images/ddangle/post/fcd2c194-b8c8-4447-b29d-660c1526f052/image.png)

## GraphQL의 장점

이 방식을 통해 기존의 RESTful API의 단점인 Over-Fetching, Under-Fetching 문제를 해결할 수 있습니다.

- `Over-Fetching(오버 패칭)`
    - 오버 패칭은 클라이언트에서 데이터를 요청했을 때, 실제로 사용되는 데이터 외에 사용되지 않는 데이터들도 함께 불러옴으로써 리소스의 낭비가 발생하는 것을 말합니다.
- `Under-Fetching(언더 패칭)`
    - 언더 패칭은 클라이언트에서 화면을 구성하기 위해 데이터를 요청할 때, 하나의 API에서 필요한 데이터를 모두 내려주는 것이 아닐 수 있기 때문에 여러 개의 API에 데이터를 요청해야 하는 것을
      말합니다. (즉, HTTP 요청 횟수와 응답 사이즈를 줄일 수 있습니다)

## Query, Mutation

RESTful API를 구현할 때 사용하는 HTTP method인 GET, PUT, PATCH, DELETE 와 같은 메서드들과 마찬가지로 GraphQL에는 `Query`, `Mutation`이
있습니다. `Query`는 CRUD의 R(Read)를 담당하고, `Mutation`은 C(Create), U(Update), D(Delete) 를 담당합니다.

> Query로 동작한다고 하더라도, HTTP Method는 기본적으로는 `POST`라는 점을 잊으면 안됩니다. 물론 `GET`으로 가게끔 바꿀 수도 있지만, 권장하지 않습니다. 이유는 GET으로 하게 되면
> Request Body가 아닌 쿼리 파라미터로 받게 되는데 graphql query 문을 쿼리로 받게 되면 너무 길어져서 `HTTP 상태코드 414(URI Too Long)` 가 내려올 수 있기때문입니다. 그래서
> 웬만하면 POST 만을 사용하기를 권장합니다.
[해당 링크](https://www.reddit.com/r/graphql/comments/ekzn86/why_does_graphql_only_take_http_post_for_requests/)를 보면,
> graphql이 왜 `POST`만을 사용했는지에 대한 이야기가 적혀있습니다.

# GraphQL 구현해보기

SpringBoot 서버에 `GraphQL` 을 구현해보겠습니다. 원래 SpringBoot 웹서버에서는 `@RequestMapping`, `@GetMapping`, `@PostMapping` 등으로 url을
매핑시켜줬지만 `GraphQL`은 앞서 얘기했듯이 하나의 엔드포인트로도 많은 동작을 열어줄 수 있다는 차이점을 가집니다.

그래서 `GraphQL` 에서 사용하는 `@QueryMapping`, `@MutationMapping` 어노테이션들에는 url 패턴을 지정하는 기능이 없습니다.

## 구현한 GraphQL Controller

(using `@QueryMapping`, `@MutationMapping`, `@Argument`)

코드의 주석에도 적혀있다싶이 GraphQL에는 @RequestBody, @RequestParam, @PathVariable 이 없습니다. `@Argument`로 통합됩니다.

그리고 사용되지 않는 데이터를 조회해오거나 로직을 타는 일은 없어도 되기에 오버페칭 문제를 해결할 수 있도록 GraphQL은 DataFetchingFieldSelectionSet 을 통해 응답 schema 구조를 미리 알고 수행할 로직을 지정해줄 수 있습니다.
```java
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
            log.info("query contain [id] schema");
        if (selectionSet.contains("name"))
            log.info("query contain [name] schema");
        if (selectionSet.contains("role"))
            log.info("query contain [role] schema");
        if (selectionSet.contains("age"))
            log.info("query contain [age] schema");

        return repository.findById(id).orElseThrow();
    }

    @QueryMapping
    public List<Member> getMemberList() {
        return repository.findAll();
    }
}
```

## resources/graphql/** 폴더

앞에서처럼 Controller에만 정의해두면 끝이 아니라, `.graphqls` 파일을 만들어서 앞의 컨트롤러와 GraphQL 쿼리를 매핑해줘야 합니다. 아래의 예시처럼 GraphQL 로직들을 관리하기 편하게
패키징을 할수도 있습니다.

> 아래를 보면 `scalar Long`이 있는데 이는 `com.graphql-java:graphql-java-extended-scalars` 라이브러리를 받아서 이용한 추가적인 기능입니다.
`GraphQL`에는 기본적인 타입이 `String`, `Boolean`, `Int`, `Float`, `ID` 총 5가지입니다. 추가적인 scalar 가 필요하다면 해당 라이브러리를 의존성에 추가해서 사용하시길
> 추천드립니다.

```graphql
# query.graphqls
type Query {
    getMember(id: Long!): Member # Controller에 @QueryMapping 메서드명과 같아야 합니다.
    getMemberList: [Member]
}

# mutation.graphqls
type Mutation {
    saveMember(body: CreateMemberRequestDto!): Member # Controller에 @MutationMapping 메서드명과 같아야 합니다.

    updateMember(body: UpdateMemberRequestDto!): Member

    updateField(id: Long!, name: String, role: MemberRole, age: Int): Member
}

# scalars.graphqls
scalar Long

# member/member.graphqls
type Member {
    id: ID!
    name: String!
    role: MemberRole!
    age: Int
}

# member/member_input.graphqls
input CreateMemberRequestDto {
    name: String!
    role: MemberRole!
    age: Int
}

input UpdateMemberRequestDto {
    id: Long!
    name: String!
    role: MemberRole!
    age: Int
}

# member/member_payload.graphqls
enum MemberRole {
    NORMAL
    ADMIN
}
```

## application.yml

graphiql 경로를 지정해주고 해당 경로에 접속해서 GraphQL을 테스트해볼 수 있습니다. 또한 graphQL schema 스캔 대상을 지정해줄 수도 있습니다.

```yml
spring:
  graphql:
    graphiql:
      enabled: true
      # graphiql을 true를 설정해 주면 localhost:8080/graphiql (Default path)을 통해 graphql 쿼리를 테스트가 가능합니다
      # 이 방법 말고도 IntelliJ에 GraphQL 플러그인을 설치해서 IntelliJ에서 직접 테스트도 가능하며 Postman 으로도 가능합니다
    schema:
      printer:
        enabled: true                                   # jpa 의 show-sql 같이 graphql 쿼리를 출력해 줍니다.
      locations: classpath:graphql/**/                  # 해당 프로퍼티로 .graphqls 파일의 스캔범위를 지정할 수 있다
```

### graphiql 테스트

application.yml 파일에서 지정해준 graphiql 경로로 접근해서 graphQL 쿼리들을 날려보며 테스트해볼 수 있습니다.
![](https://velog.velcdn.com/images/ddangle/post/fb6f32fb-0f79-4aa7-860d-b84286c690e2/image.png)

### Postman 테스트

Postman으로도 테스트할 수 있습니다. 다만 GraphQL은 항상 `POST` HTTP Method 로 동작하기에 query라도 `POST`로 설정해서 요청을 보내야 정상작동합니다.
![](https://velog.velcdn.com/images/ddangle/post/2b587064-8958-41d8-bd01-244ee9bea42c/image.png)

# _Reference_

- [GraphQL Official documentation](https://graphql.org/learn/)
- [불로그 레퍼런스1](https://giljae.com/2022/08/05/Restful-vs-gRPC-vs-GraphQL.html)
- [블로그 레퍼런스2](https://luvstudy.tistory.com/195)
- [블로그 레퍼런스3](https://wildeveloperetrain.tistory.com/191)
- [Why does GraphQL only take HTTP POST for requests? - Reddit](https://www.reddit.com/r/graphql/comments/ekzn86/why_does_graphql_only_take_http_post_for_requests/)