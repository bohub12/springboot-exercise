package com.example.demo.application.dto;

import com.example.demo.application.MemberRole;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

/**
 *  snake(userName > user_name) = SnakeCaseStrategy.class
 *  <li>camel case - lower(userName > userName) = LowerCamelCaseStrategy.class</li>
 *  <li>camel case - upper(userName > UserName) = UpperCamelCaseStrategy.class</li>
 *  <li>lower case(userName > username) = LowerCaseStrategy.class</li>
 */

@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public record JsonNamingResponseData(
        String myName,
        int myAge,
        MemberRole myRole
) {

}
