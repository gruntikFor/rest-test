package com.gruntik.resttest.mvc;

import com.gruntik.resttest.dao.StoreRepository;
import com.gruntik.resttest.entity.Store;
import com.gruntik.resttest.status.ErrorStatus;
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
public class RemoveMvcTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    StoreRepository storeRepository;

    final String STRING_OK = "{\"name\":\"igor\"}";
    final String STRING_NO_DATA = "{}";

    @Test
    public void removeOK() throws Exception {
        storeRepository.deleteAll();
        storeRepository.save(new Store("igor", 23));

        mvc.perform(post("/remove")
                        .content(STRING_OK)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.code", is(ErrorStatus.OK.getValue())))
                .andExpect(jsonPath("$.description", is(ErrorStatus.OK.getDescription())));
    }

    @Test
    public void removeNoData() throws Exception {
        mvc.perform(post("/remove")
                        .content(STRING_NO_DATA)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.code", is(ErrorStatus.NO_DATA.getValue())))
                .andExpect(jsonPath("$.description", is(ErrorStatus.NO_DATA.getDescription())));
    }

    @Test
    public void removeNothingToDelete() throws Exception {
        storeRepository.deleteAll();

        mvc.perform(post("/remove")
                        .content(STRING_OK)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.code", is(ErrorStatus.NOTHING_TO_DELETE.getValue())))
                .andExpect(jsonPath("$.description", is(ErrorStatus.NOTHING_TO_DELETE.getDescription())));
    }

}
