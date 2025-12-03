package com.kanjiflashcard.kanjiflashcard;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class KanjiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRandomKanji() throws Exception {
        mockMvc.perform(get("/api/random"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.literal").exists());
    }

    @Test
    public void testRandomN3Kanji() throws Exception {
        mockMvc.perform(get("/api/random?jlpt=3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.jlptLevel").value(3));
    }

}
