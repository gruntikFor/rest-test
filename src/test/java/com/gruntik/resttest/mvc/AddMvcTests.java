package com.gruntik.resttest.mvc;

import com.gruntik.resttest.dao.StoreRepository;
import com.gruntik.resttest.entity.Store;
import com.gruntik.resttest.status.ResponseStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@AutoConfigureMockMvc
@SpringBootTest
public class AddMvcTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    StoreRepository storeRepository;

    final String STRING_OK = "{\"name\":\"igor\",\"value\":\"23\"}";
    final String STRING_NO_NAME = "{\"value\":\"23\"}";
    final String STRING_NO_VALUE = "{\"name\":\"igor\"}";

    @Test
    public void addOK() throws Exception {
        storeRepository.deleteAll();
        mvc.perform(post("/add")
                        .content(STRING_OK)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.code", is(ResponseStatus.OK.getValue())))
                .andExpect(jsonPath("$.description", is(ResponseStatus.OK.getDescription())));
    }

    @Test
    public void addAlreadyExists() throws Exception {
        storeRepository.deleteAll();
        storeRepository.save(new Store("igor", 23));

        mvc.perform(post("/add")
                        .content(STRING_OK)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.code", is(ResponseStatus.ALREADY_EXISTS.getValue())))
                .andExpect(jsonPath("$.description", is(ResponseStatus.ALREADY_EXISTS.getDescription())));
    }

    @Test
    public void addNoName() throws Exception {
        mvc.perform(post("/add")
                        .content(STRING_NO_NAME)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.code", is(ResponseStatus.NO_NAME.getValue())))
                .andExpect(jsonPath("$.description", is(ResponseStatus.NO_NAME.getDescription())));
    }

    @Test
    public void addNoValue() throws Exception {
        mvc.perform(post("/add")
                        .content(STRING_NO_VALUE)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.code", is(ResponseStatus.NO_VALUE.getValue())))
                .andExpect(jsonPath("$.description", is(ResponseStatus.NO_VALUE.getDescription())));
    }


}
