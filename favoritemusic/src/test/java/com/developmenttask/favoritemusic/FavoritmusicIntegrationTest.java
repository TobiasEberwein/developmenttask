package com.developmenttask.favoritemusic;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;

@SpringBootTest
@AutoConfigureMockMvc
public class FavoritmusicIntegrationTest {
    
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllMusic() throws Exception {
        this.mvc.perform(get("/api/favoritemusic"))
            .andExpect(status().isOk());
    }

    @Test
    void testRestSequence() throws Exception {

        // post new music
        Music music = new Music("Name", "Artist", 2000, "Format");
        MvcResult result = this.mvc.perform(post("/api/favoritemusic")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(music)))
            .andExpect(status().isOk()).andReturn();

        String musicId = result.getResponse().getContentAsString();

        // get music
        this.mvc.perform(get("/api/favoritemusic/" + musicId))
            .andExpect(status().isOk());

        // update music
        music = new Music("Name", "Artist", 1999, "Format");
        this.mvc.perform(put("/api/favoritemusic/" + musicId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(music)))
            .andExpect(status().isOk());

        // get music
        this.mvc.perform(get("/api/favoritemusic/" + musicId))
            .andExpect(status().isOk());

        // delete music
        this.mvc.perform(delete("/api/favoritemusic/" + musicId))
            .andExpect(status().isOk());
    }

    @Test
    void testGetNotExistingMusic() throws Exception {

        // ensure no music with the given id exists by posting a new music and deleting it again
        Music music = new Music("Name", "Artist", 2001, "Format");

        MvcResult result = this.mvc.perform(post("/api/favoritemusic")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(music)))
            .andExpect(status().isOk()).andReturn();

        String musicId = result.getResponse().getContentAsString();

        this.mvc.perform(delete("/api/favoritemusic/" + musicId))
            .andExpect(status().isOk());
            
        // try to get the deleted music
        Throwable exception = assertThrows(ServletException.class,
            () -> this.mvc.perform(get("/api/favoritemusic/" + musicId)));
        assertEquals("Request processing failed: java.lang.IllegalStateException: music with id " + musicId
            + " does not exist", exception.getMessage());
    }

    @Test
    void testDeleteNotExistingMusic() throws Exception {

        // add a new music, delete the same music, then try to delete it again
        Music music = new Music("Name", "Artist", 2002, "Format");

        MvcResult result = this.mvc.perform(post("/api/favoritemusic")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(music)))
        .andExpect(status().isOk()).andReturn();

        String musicId = result.getResponse().getContentAsString();

        this.mvc.perform(delete("/api/favoritemusic/" + musicId))
            .andExpect(status().isOk());

        Throwable exception = assertThrows(ServletException.class,
            () -> this.mvc.perform(delete("/api/favoritemusic/" + musicId)));
        assertEquals("Request processing failed: java.lang.IllegalStateException: music with id " + musicId
            + " does not exist", exception.getMessage());
    }

    @Test
    void testUpdateNotExistingMusic() throws Exception {

        // add new music, then delete it to ensure it doesnt exist before trying to update
        Music music = new Music("Name", "Artist", 2003, "Format");

        MvcResult result = this.mvc.perform(post("/api/favoritemusic")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(music)))
        .andExpect(status().isOk()).andReturn();

        String musicId = result.getResponse().getContentAsString();

        this.mvc.perform(delete("/api/favoritemusic/" + musicId))
            .andExpect(status().isOk());

        // try to update the deleted music
        Throwable exception = assertThrows(ServletException.class,
            () -> this.mvc.perform(put("/api/favoritemusic/" + musicId)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(music)))
            .andExpect(status().isOk()));
        assertEquals("Request processing failed: java.lang.IllegalStateException: music with id " + musicId
            + " does not exist", exception.getMessage());
    }
}
