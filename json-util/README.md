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