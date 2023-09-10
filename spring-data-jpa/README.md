# Spring Data JPA

## 객체간의 상속관계 표현하기
데이터베이스 모델링 기법에 슈퍼타입-서브타입 관계 라는 방법이 있는데 이것이 객체의 상속관계와 가장 흡사하다. 아래 방법들은 슈퍼타입-서브타입 관계 에 해당되는 방법들이다.

### 1. 조인 전략 (`InheritanceType.JOINED`)
- 구분자 컬럼(`DTYPE`)을 이용하는 방법이다
- 장점
    - 테이블이 정규화
    - 외래키 참조 무결성 제약조건 활용가능
    - 저장공간 효율적으로 사용
- 단점
    - 조회할 때 조인이 많이 사용되므로, 성능이 저하된다
    - 조회쿼리가 복잡하다
    - 데이터를 등록할 INSERT SQL 두번 실행한다
- 사용되는 어노테이션
    - `@PrimaryKeyJoinColumn, @DiscriminatorColumn, @DiscriminatorValue`

```java
// Item.java
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {

  @Id @GeneratedValue
  private long id;

  private String name;
  private int price;
}

// Album.java
@Entity
@DiscriminatorValue("A")
public class Album extends Item {

  private String artist;
}

// Book.java
@Entity
@DiscriminatorValue("B")
public class Book extends Item {

  private String author;
}
```

### 2. 단일테이블 전략 (`InheritanceType.SINGLE_TABLE`)
- 이름 그래도 테이블을 하나만 사용하는 방식이다
- 또한 `조인전략`과 마찬가지로 구분칼럼(`DTYPE`)을 이용해서 어떤 자식데이터가 저장되었는지 구분한다
- 장점
    - 조회할 때 조인을 사용하지 않으므로 가장 빠르다
    - 조회 쿼리가 단순하다
- 단점
    - 자식 엔티티가 매핑한 컬럼은 모두 null 허용해야한다
    - 한 테이블에 모두 저장하므로 테이블이 의도치않게 커질 수 있다. 변경과 확장에 취약해지게 된다.

```java
// Item.java
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {

    @Id @GeneratedValue
    private long id;

    private String name;
    private int price;
}

// Album.java
@Entity
@DiscriminatorValue("A")
public class Album extends Item {

  private String artist;
}

// Book.java
@Entity
@DiscriminatorValue("B")
public class Book extends Item {

  private String author;
}
```

### 3. 구현클래스마다 테이블 (`InheritanceType.TABLE_PER_CLASS`)
- 애플리케이션에서 부모 엔티티는 추상클래스로 두고, 자식 엔티티마다 테이블을 만드는 방식이다
- 추천하지 않는 방식이다
- 장점
    - 서브 타입을 완전히 구분지어 사용하고 싶을 때 효과적 (살짝 억지인 경향도 있다)
- 단점
    - 자식 테이블을 통합해서 쿼리하기 어려워진다. 만약 자식테이블이 100개라면..? 끔찍…
    - 여러 자식 테이블을 함께 조회할 때 성능도 안좋다 (UNION 사용하기 때문! 모든 결과값을 메모리에 저장한 뒤, 중복제거해야하기 때문)

```java
// Item.java
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item {

    @Id @GeneratedValue
    private long id;

    private String name;
    private int price;
}


// Album.java
@Entity
public class Album extends Item {

    private String artist;
}

// Book.java
@Entity
public class Book extends Item {

    private String author;
}

```