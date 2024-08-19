package com.aakash.Projexio.controller;

import com.aakash.Projexio.model.Project;
import com.aakash.Projexio.model.User;
import com.aakash.Projexio.service.ProjectService;
import com.aakash.Projexio.service.UserService;
import com.aakash.Projexio.response.MessageResponse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProjectControllerTest {

    @InjectMocks
    private ProjectController projectController;

    @Mock
    private ProjectService projectService;

    @Mock
    private UserService userService;

    public ProjectControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateProject() throws Exception {
        String jwt = "sampleJwt";
        User user = new User();
        Project project = new Project();
        Project createdProject = new Project();

        when(userService.findUserProfileByJwt(jwt)).thenReturn(user);
        when(projectService.createProject(project, user)).thenReturn(createdProject);

        ResponseEntity<Project> response = projectController.createProject(jwt, project);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdProject, response.getBody());
        verify(userService, times(1)).findUserProfileByJwt(jwt);
        verify(projectService, times(1)).createProject(project, user);
    }

    @Test
    public void testDeleteProject() throws Exception {
        Long projectId = 1L;
        String jwt = "sampleJwt";
        User user = new User();

        when(userService.findUserProfileByJwt(jwt)).thenReturn(user);

        ResponseEntity<MessageResponse> response = projectController.deleteProject(projectId, jwt);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Project deleted successfully", response.getBody().getMessage());
        verify(userService, times(1)).findUserProfileByJwt(jwt);
        verify(projectService, times(1)).deleteProject(projectId, user.getId());
    }
}
