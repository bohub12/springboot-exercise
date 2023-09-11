

```graphql
mutation {
    saveMember(body: {
        name: "멤버"
        age: 15
        role: ADMIN
    }) {
        id
        age
        name
        role
    }
}

query {
  getMember(id:1) {
    id
    name
    age
    role
  }
}

query {
    getMemberList {
        id
        name
        age
        role
    }
}
```


# _Reference_
- [GraphQL official documentation](https://graphql.org/learn)
- [GraphQL official documentation - schema](https://graphql.org/learn/schema/)
- [Baeldung Blog - Getting Started with GraphQL and Spring Boot](https://www.baeldung.com/spring-graphql)
- [tech blog1](https://danawalab.github.io/spring/2022/06/06/Spring-for-GraphQL.html)