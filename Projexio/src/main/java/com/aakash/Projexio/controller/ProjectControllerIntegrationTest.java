package com.aakash.Projexio.controller;

import com.aakash.Projexio.model.Project;
import com.aakash.Projexio.model.User;
import com.aakash.Projexio.service.ProjectService;
import com.aakash.Projexio.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ProjectController.class)
public class ProjectControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProjectService projectService;

    @Mock
    private UserService userService;

    @InjectMocks
    private ProjectController projectController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(projectController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCreateProject() throws Exception {
        String jwt = "sampleJwt";
        Project project = new Project();
        Project createdProject = new Project();

        when(userService.findUserProfileByJwt(jwt)).thenReturn(new User());
        when(projectService.createProject(any(Project.class), any(User.class))).thenReturn(createdProject);

        mockMvc.perform(post("/api/projects")
                        .header("Authorization", jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(project)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(createdProject)));
    }

    @Test
    public void testDeleteProject() throws Exception {
        Long projectId = 1L;
        String jwt = "sampleJwt";

        when(userService.findUserProfileByJwt(jwt)).thenReturn(new User());

        mockMvc.perform(delete("/api/projects/" + projectId)
                        .header("Authorization", jwt))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"message\":\"Project deleted successfully\"}"));
    }
}
