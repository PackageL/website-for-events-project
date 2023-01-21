package com.sda.controller;

import com.sda.model.Comment;
import com.sda.model.Event;
import com.sda.model.User;
import com.sda.service.CommentService;
import com.sda.service.EventService;
import com.sda.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EventController.class)
class EventControllerTest {
    private MockMvc mockMvc;

    @MockBean
    private EventService eventService;
    @MockBean
    private UserService userService;
    @MockBean
    private CommentService commentService;

    @Autowired
    public EventControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    public void createEvent_shouldRenderCreateEventView() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/event/create")
                .accept(MediaType.TEXT_HTML);

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(view().name("create-event"))
                .andExpect(model().attributeExists("event"));
    }
}
