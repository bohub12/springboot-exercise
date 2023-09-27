> 직렬화와 역직렬화
> - 직렬화 : 객체 > json
> - 역직렬화 : json > 객체

## JsonProperty & JsonNaming

- JsonProperty : 멤버변수 대상으로 json 필드 네이밍 지정

- JsonNaming : 클래스 대상으로 json 필드 네이밍 방식 지정 (camel, snake)


## JsonIgnore

```shell
com.fasterxml.jackson.databind.exc.InvalidDefinitionException: 
    No serializer found for class org.hibernate.proxy.pojo.bytebuddy.ByteBuddyInterceptor and no properties discovered to create BeanSerializer
```

ManyToOne 어노테이션으로 LAZY하게 연관관계를 가지는 객체를 가져오게 되면, 객체를 직렬화하지 못하고 해당 에러가 발생한다.

연관관계를 EAGER로 바꾸지 않고 간단히 해결하려면, @JsonIgnore 어노테이션을 필드에 달아주면 된다. 이렇게하면 해당 필드는 직렬화 과정에서 무시되고 에러가 발생하지 않는다.


## JsonIgnoreProperties

- property 중 어떤 이름 가지는 property를 무시할지 결정하는 어노테이션이다.

- `@JsonIgnoreProperties(value = {"id", "name"}, allowGetters = true)`

    - 명시되어 있는 필드의 직렬화는 허용하지만 역직렬화를 허용하지 않는다 (나머지 필드는 둘다 허용)

- `@JsonIgnoreProperties(value = {"id", "name"}, allowSetters = true)`

    - 명시되어 있는 필드의 역직렬화는 허용하지만 직렬화는 허용하지 않는다 (나머지 필드는 둘 다 허용)

- `@JsonIgnoreProperties(ignoreUnknown = true)`

    - 기본적으로 `deserialization: fail-on-unknown-properties: false` 으로 설정되어 있는데 이는 역직렬화할 때, 필요없는 필드가 있으면 무시되는 설정값이다.

    - 그래서 만약 저 값이 true로 설정되어 있다면, 필요없는 필드가 오면 에러가 발생하게 된다.


## JsonIgnoreType
클래스 대상으로 해당 클래스를 참조하고 있는 객체에서 Json에서 ignore 할지 말지 결정할 수 있는 어노테이션

## JsonInclude

Json으로 직렬화할 때, 필드에 특정 값(null, "", ... 등) 일 경우 핸들링할 수 있는 어노테이션이다. 아래는 JsonInclude 어노테이션 속성별로 어떤 값을 가질 때 제외되는지 정리한 내용이다.

- ALWAYS (default)

  - 조건 없음

- NON_NULL

  - null

- NON_ABSENT

  - null

  - absent = 참조(reference) 타입에 absent value (Optional, AtomicReference 등)

- NON_EMPTY

  - null

  - absent

  - 비어있는 Collection, Map

  - Array 길이가 0

  - String 길이가 0

- NON_DEFAULT

  - NON_EMPTY 조건

  - primitive 타입이 default 값인 경우 (int 형은 0, boolean은 false)


## JsonView

- 조건에 따라 직렬화하는 필드를 다르게 설정할 수 있는 어노테이션이다


## JsonManagedReference & JsonBackReference

- JPA 엔티티를 컨트롤러 단계까지 올리다가 순환참조로 문제 생기는 상황을 방지할 수 있게끔 해주는 어노테이션 조합이다.

- 연관관계의 주인에 `@JsonManagedReference` 를 달아주고, 매핑되는 엔티티는 `@JsonBackReference` 어노테이션을 달아주면, toString() 메서드를 호출해도 순환참조 되지 않는다.

- 당연히 Json 직렬화에도 순환참조로 생기는 문제를 막을 수 있다.

- 이 방식으로 하기보다는, 컨트롤러 단에서 DTO 리턴해주는 것이 순환참조 문제를 막을 수 있을 뿐더러 JPA라는 기술에 의존하지 않음으로써 장점을 가진다.