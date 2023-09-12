package com.example.demo.config;


import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
public class GraphQlConfig {

    // Long 타입 사용하기 위해서 custom scalar 사용
    // graphql-java-extended-scalars 라이브러리에서는 여러 타입들도 지원하기 때문에 지정해주고 사용하면 된다
    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(ExtendedScalars.GraphQLLong);
    }
}