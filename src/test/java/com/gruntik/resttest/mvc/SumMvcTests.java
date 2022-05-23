package com.gruntik.resttest.mvc;

import com.gruntik.resttest.status.ResponseStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest
public class SumMvcTests {

    @Autowired
    private MockMvc mvc;

    final String STRING_OK = "{\"first\":\"2\",\"second\":\"2\"}";
    final String STRING_NO_DATA = "{}";
    final String STRING_NO_FIRST_NUMBER = "{\"second\":\"2\"}";
    final String STRING_NO_SECOND_NUMBER = "{\"first\":\"2\"}";
    final String STRING_NOT_NUMBER_FIRST = "{\"first\":\"str\",\"second\":\"2\"}";
    final String STRING_NOT_NUMBER_SECOND = "{\"first\":\"2\",\"second\":\"str\"}";

    @Test
    public void sumOK() throws Exception {
        mvc.perform(post("/sum")
                        .content(STRING_OK)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.sum", is(4)))
                .andExpect(jsonPath("$.code", is(ResponseStatus.OK.getValue())))
                .andExpect(jsonPath("$.description", is(ResponseStatus.OK.getDescription())));
    }

    @Test
    public void sumNoData() throws Exception {
        mvc.perform(post("/sum")
                        .content(STRING_NO_DATA)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.code", is(ResponseStatus.NO_DATA.getValue())))
                .andExpect(jsonPath("$.description", is(ResponseStatus.NO_DATA.getDescription())));
    }

    @Test
    public void sumNoFirstNumber() throws Exception {
        mvc.perform(post("/sum")
                        .content(STRING_NO_FIRST_NUMBER)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.code", is(ResponseStatus.NO_FIRST_NUMBER.getValue())))
                .andExpect(jsonPath("$.description", is(ResponseStatus.NO_FIRST_NUMBER.getDescription())));
    }

    @Test
    public void sumNoSecondNumber() throws Exception {
        mvc.perform(post("/sum")
                        .content(STRING_NO_SECOND_NUMBER)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.code", is(ResponseStatus.NO_SECOND_NUMBER.getValue())))
                .andExpect(jsonPath("$.description", is(ResponseStatus.NO_SECOND_NUMBER.getDescription())));
    }

    @Test
    public void sumNotNumberFirst() throws Exception {
        mvc.perform(post("/sum")
                        .content(STRING_NOT_NUMBER_FIRST)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.code", is(ResponseStatus.NOT_NUMBER_FIRST.getValue())))
                .andExpect(jsonPath("$.description", is(ResponseStatus.NOT_NUMBER_FIRST.getDescription())));
    }

    @Test
    public void sumNotNumberSecond() throws Exception {
        mvc.perform(post("/sum")
                        .content(STRING_NOT_NUMBER_SECOND)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.code", is(ResponseStatus.NOT_NUMBER_SECOND.getValue())))
                .andExpect(jsonPath("$.description", is(ResponseStatus.NOT_NUMBER_SECOND.getDescription())));
    }

}
