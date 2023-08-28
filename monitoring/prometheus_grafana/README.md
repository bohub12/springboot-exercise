# 스프링부트 액추에이터 (Actuator)

스프링 부트 액추에이터(Actuator)는 스프링 부트 프로젝트에서 운영 환경에서의 모니터링과 관리를 쉽게 할 수 있도록 도와주는 라이브러리입니다. 이는 스프링 부트 애플리케이션의 내부 상태와 메타데이터에 접근할 수 있는 강력한 기능을 제공하여, 애플리케이션의 운영 중 상태를 실시간으로 파악하고, 문제를 진단하며, 필요한 조치를 취하는 데 도움을 줍니다.

스프링부트 액추에이터는 다양한 엔드포인트(Endpoint)를 제공하는데, 이 엔드포인트들을 통해 애플리케이션의 다양한 정보와 통계를 조회할 수 있습니다. 몇 가지 주요한 엔드포인트는 다음과 같습니다. 이들에 대한 정보는 "{server-base-url}/actuator" 에서 확인할 수 있습니다.

- `health` : 서버의 구동 여부를 나타내는 엔드포인트
- `info` : 애플리케이션에 관련된 정보를 제공하는 엔드포인트
- `metrics` : 애플리케이션의 여러 메트릭 정보를 조회할 수 있는 엔드포인트
  - CPU 사용량, 메모리 사용량, HTTP 요청 & 응답 수 등
  - 개발자가 커스텀하게 직접 메트릭 등록 가능
- `env` : 애플리케이션의 환경 변수와 설정 정보를 조회할 수 있는 엔드포인트
- `loggers` : 로깅 설정을 조회하고 변경할 수 있는 엔드포인트
- `auditevents` : 인증 및 인가 관련 이벤트를 조회할 수 있는 엔드포인트
- `mappings` : 애플리케이션의 URL 매핑 정보를 조회할 수 있는 엔드포인트
- `beans` : 애플리케이션의 스프링 컨테이너에 등록된 빈을 조회할 수 있는 엔드포인트

위의 엔드포인트들과는 다른 결을 가지는 중요한 엔드포인트 중 하나는 `shutdown` 입니다. 사실 스프링부트 액츄에이터는 엔드포인트를 활성화시키고 노출을 HTTP 방식 혹은 JMX 방식으로 이용할 수 있는 구조가 됩니다.
즉, 엔드포인트 활성화 + 엔드포인트 노출 이라는 두 가지 과정이 필요한 것입니다. 스프링부트 액추에이터는 기본적으로 shutdown 엔드포인트를 제외한 나머지 엔드포인트들은 모두 활성화되어 있는 상태입니다. 왜? shutdown 엔드포인트를 사용할 때에는 조심해야한다는 것을 알려주는 것이고, 그만큼 노출시키지 않는 것이 베스트프랙티스라는 것을 의미하기도 합니다. 다만 개발자들에게 상황에 따라서는 shutdown 기능이 필요할 수도 있기 때문에 직접 shutdown 엔드포인트를 비활성화 상태로 제공하는 것입니다.

그럼 엔드포인트 활성화 혹은 노출을 어떻게 설정하는 것일까요?

### 엔드포인트 노출

```yml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

### 엔드포인트 활성화

```yml
## shutdown 엔드포인트 활성화 & metrics 엔드포인트 비활성화
management:
  endpoint:
    shutdown:
      enabled: true
    metrics:
      enabled: false
```

## _Reference_

- [spring boot actuator official documentation](https://docs.spring.io/spring-boot/docs/current/actuator-api/htmlsingle/)
- [우아한형제들 "Actuator 안전하게 사용하기"](https://techblog.woowahan.com/9232/)

---

# 마이크로미터 (Micrometer)

세상에는 수많은 모니터링 툴(프로메테우스, 그라파나, Datadog 등)이 있습니다. 만약 A라는 모니터링툴을 이용하다가 B라는 모니터링툴을 이용하려고 했을 때, actuator 에서 수집하는 다양한 지표들을 바꾼 모니터링 툴에 맞게끔 보내주어야 합니다. 이런 문제를 해결해주는 것이 바로 `마이크로미터(Micrometer)` 입니다.
`마이크로미터(Micrometer)`는 애플리케이션의 모니터링과 메트릭 수집을 위한 오픈 소스 라이브러리입니다. 마이크로미터는 다양한 메트릭에 대한 추상화 계층을 제공하여, 애플리케이션에서 생성된 다양한 메트릭 데이터를 효율적으로 수집하고 저장할 수 있게 도와줍니다. 이를 통해 개발자는 애플리케이션의 성능과 상태를 편하게 모니터링할 수 있고, 모니터링 툴의 변경에도 자유로울 수 있습니다.
또한 마이크로미터는 `애플리케이션 메트릭 파사드`라고 불리는데, 애플리케이션의 메트릭(측정 지표)을 마이크로미터가 정한 표준 방법으로 모아서 제공해줍니다.

## _Reference_

- [micrometer official documentation](https://micrometer.io/docs)

> 스프링부트에서 자동으로 지원해주는 메트릭 리스트는 아래의 공식문서를 참고해주세요
> [Spring Boot-Supported Metrics and Meters](https://docs.spring.io/spring-boot/docs/current/reference/html/actuator.html#actuator.metrics.supported)

---

# 프로메테우스 & 그라파나

프로메테우스와 그라파나는 많이 사용되는 모니터링 툴입니다. 프로메테우스는 메트릭을 수집하는 역할을 하고, 그라파나는 메트릭 시각화에 많이 사용되는 툴입니다. 사용법과 각각의 목적 및 개념들에 대해 간단히 알아보겠습니다.

- [프로메테우스 공식문서](https://prometheus.io/)
- [그라파나 공식문서](https://grafana.com/)

## 프로메테우스

프로메테우스는 메트릭을 수집하고 보관하는 DB 역할을 합니다. 프로메테우스는 다양한 특징과 기능을 가지는데 아래와 같습니다.

- 다양한 데이터 수집: 프로메테우스는 다양한 소스에서 메트릭 데이터를 수집할 수 있습니다. 애플리케이션의 성능 및 상태와 관련된 메트릭을 수집하여 중앙 집중식으로 관리할 수 있습니다.
- 다차원 데이터 모델: 프로메테우스는 메트릭 데이터를 시계열로 저장하는데, 이 데이터는 다차원 레이블로 구성되어 있습니다. 이를 통해 쿼리 및 데이터 필터링이 유연하게 이루어질 수 있습니다.
- 강력한 쿼리 언어: 프로메테우스는 `PromQL`이라는 강력한 쿼리 언어를 제공하여 메트릭 데이터에 대한 복잡한 질의를 수행할 수 있습니다. 이를 통해 데이터의 시각화와 분석을 용이하게 할 수 있습니다.
- 알림 규칙 및 경고: 프로메테우스는 설정된 조건에 따라 경고를 생성할 수 있습니다. 예를 들어, 특정 임계값을 초과하는 경우 경고를 생성하여 시스템 관리자에게 알릴 수 있습니다.
- 길게 지속 가능한 데이터 저장: 프로메테우스는 메트릭 데이터를 자체적으로 저장하며, 데이터의 보존 기간을 설정할 수 있습니다. 이를 통해 오래된 데이터도 분석 및 비교가 가능합니다.
- 확장 가능한 아키텍처: 프로메테우스는 확장 가능한 아키텍처를 가지고 있어서 수많은 서비스와 노드의 메트릭을 처리할 수 있습니다.
- 오픈 소스 커뮤니티: 프로메테우스는 활발한 오픈 소스 커뮤니티에 의해 개발 및 유지보수되며, 다양한 플러그인과 확장 기능이 제공됩니다.

아래는 공식문서에 나와있는 프로메테우스를 사용하는 기본적인 아키텍처 이미지입니다.
![](https://velog.velcdn.com/images/ddangle/post/6f903a31-b964-4e66-a5ec-e92e3c0ce721/image.png)

## 그라파나

그라파나는 데이터를 시각화하여 분석 및 모니터링을 용이하게 해주는 오픈소스 분석 플랫폼입니다. 여러 데이터 소스를 연동하여 사용 할 수 있으며 시각화 된 데이터들을 대시보드로 만들 수 있습니다. 그라파나의 특징은 데이터를 저장하지 않고, 외부의 데이터소스를 정의함으로써 외부의 데이터저장소에 쿼리를 날려 결과를 얻는다는 특징을 가집니다. 즉, 프로메테우스, InfluxDB, Loki 와 같은 데이터소스에 접근해서 데이터를 가져옵니다.

- Datadog이라는 좋은 데이터 시각화 툴이 있지만 이는 상용소스이므로, 만약 비용이 부담된다면 Grafana도 좋은 선택지 인 것 같다

## (도커) 프로메테우스 설치 및 연동

우선 프로메테우스 컨테이너부터 실행시켜봅시다. 로컬에 프로메테우스를 설치하는 방법도 있겠지만, 로컬에는 뭔가 설치하기가 싫은 이상한 마음에 도커를 사용하게 됐습니다 ㅎㅎ 마음에 드시는 방법 선택해서 구현하셔도 됩니다.

일단 도커로 프로메테우스와 그라파나를 띄우겠다면 아래처럼 `prometheus.yml` 파일을 작성해줍니다.

```yml
## prometheus.yml

global:
  scrape_interval: 15s
  evaluation_interval: 15s
alerting:
  alertmanagers:
    - static_configs:
        - targets:
          # - alertmanager:9093

rule_files:
# - "first.rules"
# - "second.rules"

scrape_configs:
  - job_name: "prometheus"
    static_configs:
      - targets: ["host.docker.internal:9090"]
  - job_name: "spring-actuator"
    metrics_path: "/actuator/prometheus"
    scrape_interval: 1s
    static_configs:
      - targets: ["host.docker.internal:8080"]
```

그런 다음 도커 컨테이너를 띄울 때 prometheus 컨테이너의 설정 파일로 사용될 수 있도록 볼륨 설정을 해줍니다.

```shell
$ docker run -d -p 9090:9090 \
--name my_prometheus \
-v /Users/gimbogyu/Documents/Github/study/springboot-exercise/monitoring/prometheus_grafana/src/main/resources/prometheus.yml:/etc/prometheus/prometheus.yml \
prom/prometheus
```

컨테이너를 띄웠다면, "localhost:9090" 에 접속했을 때 정상적으로 화면이 띄워진다면 프로메테우스가 실행된 것이고, 해당 화면에서 "Status > Configuration" 란에서 내가 설정한 파일의 내용이 제대로 적용됐는지 확인해보고 "Status > Targets" 란에서 내가 수집하고자 하는 대상(스프링부트 서버 애플리케이션)이 나온다면 프로메테우스 설치 및 연동 성공입니다.

## (도커) 그라파나 설치 및 연동

그라파나는 조금 간단합니다. 아래처럼 Shell 을 입력해준 뒤, localhost:3000 으로 접속하면 화면을 볼 수 있습니다.

- 초기 아이디와 비밀번호는 admin/admin

```shell
$ docker run -d --name=my_grafana \
-p 3000:3000 grafana/grafana
```

아래와 같은 이미지가 나오면 정상적으로 실행된 것입니다.
![](https://velog.velcdn.com/images/ddangle/post/a4b7171c-864b-41bc-9d1c-dbb3b615f18e/image.png)

여기서 프로메테우스와 연동해서 사용해보자면, `홈 > add user first data source 란` 로 움직여줍니다. 여기서 Prometheus 를 선택하고 Prometheus server URL 에 `http://host.docker.internal:909` 을 입력해줍니다. 그럼 아래 이미지처럼, Connection 란에서 프로메테우스가 연동되었다는 것을 확인할 수 있습니다.

![](https://velog.velcdn.com/images/ddangle/post/eb4fd37f-fc87-43e8-a61f-1b43ede82768/image.png)

이제 꾸며보겠습니다. [그라파나 대쉬보드](https://grafana.com/grafana/dashboards/) 로 이동한 뒤, 원하는 대시보드를 선택해서 ID를 복사해줍니다.
그리고 다시 그라파나 페이지로 들어가서, 대시보드를 import 해주고 대시보드를 생성해줍니다.

![](https://velog.velcdn.com/images/ddangle/post/f643ebb5-10e7-4713-a2c6-9f310380640a/image.png)
![](https://velog.velcdn.com/images/ddangle/post/8bdd94f2-1342-4b9f-83ab-4fe509b70e16/image.png)
![](https://velog.velcdn.com/images/ddangle/post/f73c25f0-1951-4a4b-9878-8cd68b1f11a4/image.png)
![](https://velog.velcdn.com/images/ddangle/post/78d67399-e69a-42ed-98f2-85bd22d3c057/image.png)
![](https://velog.velcdn.com/images/ddangle/post/ddeeb5ed-dcc6-4d8c-a769-4443297820fc/image.png)
![](https://velog.velcdn.com/images/ddangle/post/050f0a17-ffb3-4307-8890-e34b8caeab7e/image.png)

- [블로그 레퍼런스1](https://velog.io/@fj2008/Springboot-prometheus-grafana%EB%AA%A8%EB%8B%88%ED%84%B0%EB%A7%81-%EC%8B%9C%EC%8A%A4%ED%85%9C-%EA%B5%AC%EC%B6%95)
- [블로그 레퍼런스2](https://quantrader.tistory.com/158)
