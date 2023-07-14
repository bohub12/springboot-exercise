package com.example.demo.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MemberController.class)
class MemberControllerTest {

    @Autowired MemberController memberController;

    @Autowired MockMvc mvc;

    @Test
    public void success() throws Exception {
        MockHttpServletRequestBuilder builder = post("/api/member")
                .content("{\"name\": \"김김\", \"email\": \"aa@naver.com\", \"address\": {\"zipCode\" : \"01111\", \"detailAddress\": \"101호\"}, \"favoriteFood\": \"바나나\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void fail_name() throws Exception {
        MockHttpServletRequestBuilder builder = post("/api/member")
                .content("{\"name\": null, \"email\": \"aa@naver.com\", \"address\": {\"zipCode\" : \"01111\", \"detailAddress\": \"101호\"}, \"favoriteFood\": \"바나나\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void fail_email() throws Exception {
        MockHttpServletRequestBuilder builder = post("/api/member")
                .content("{\"name\": \"김김\", \"email\": \"aabbcc\", \"address\": {\"zipCode\" : \"01111\", \"detailAddress\": \"101호\"}, \"favoriteFood\": \"바나나\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    /**
     * MemberSaveDto 에서 @Getter가 없을 때에는 해당 테스트 실패함
     * 이유는 뭘까? 리플렉션 관련된 문제 같은데, 자세히 모르겠음
     * @throws Exception
     */

    @Test
    public void fail_address() throws Exception {
        MockHttpServletRequestBuilder builder = post("/api/member")
                .content("{\"name\": \"김김\", \"email\": \"aa@naver.com\", \"address\": {\"zipCode\" : \"011\", \"detailAddress\": \"101호\"}, \"favoriteFood\": \"바나나\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void fail_enum() throws Exception {
        MockHttpServletRequestBuilder builder = post("/api/member")
                .content("{\"name\": \"김김\", \"email\": \"aa@naver.com\", \"address\": {\"zipCode\" : \"01111\", \"detailAddress\": \"101호\"}, \"favoriteFood\": \"사자\"}")
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mvc.perform(builder)
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}