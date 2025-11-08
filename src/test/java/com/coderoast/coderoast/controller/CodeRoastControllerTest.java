package com.coderoast.coderoast.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.coderoast.coderoast.entity.CodeSnippet;
import com.coderoast.coderoast.service.CodeRoastService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(CodeRoastController.class)
class CodeRoastControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private CodeRoastService service;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void testSubmitCode() throws Exception {
    // Arrange
    CodeSnippet snippet = new CodeSnippet("public class Test {}", "java");
    snippet.setId(1L);
    snippet.setRoast("Test roast");
    snippet.setTips("Test tips");

    when(service.submitCode(any(String.class), any(String.class))).thenReturn(snippet);

    CodeRoastController.CodeSnippetRequest request = new CodeRoastController.CodeSnippetRequest();
    request.setCode("public class Test {}");
    request.setLanguage("java");

    // Act & Assert
    mockMvc
        .perform(
            post("/api/roast/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.code").value("public class Test {}"))
        .andExpect(jsonPath("$.language").value("java"));
  }

  @Test
  void testGetAllSnippets() throws Exception {
    // Arrange
    CodeSnippet snippet1 = new CodeSnippet("code1", "java");
    CodeSnippet snippet2 = new CodeSnippet("code2", "python");
    List<CodeSnippet> snippets = Arrays.asList(snippet1, snippet2);

    when(service.getAllSnippets()).thenReturn(snippets);

    // Act & Assert
    mockMvc
        .perform(get("/api/roast/snippets"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(2));
  }

  @Test
  void testGetSnippetById() throws Exception {
    // Arrange
    Long id = 1L;
    CodeSnippet snippet = new CodeSnippet("code", "java");
    snippet.setId(id);

    when(service.getSnippetById(id)).thenReturn(Optional.of(snippet));

    // Act & Assert
    mockMvc
        .perform(get("/api/roast/snippet/{id}", id))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id))
        .andExpect(jsonPath("$.code").value("code"))
        .andExpect(jsonPath("$.language").value("java"));
  }

  @Test
  void testGetSnippetByIdNotFound() throws Exception {
    // Arrange
    Long id = 1L;

    when(service.getSnippetById(id)).thenReturn(Optional.empty());

    // Act & Assert
    mockMvc.perform(get("/api/roast/snippet/{id}", id)).andExpect(status().isNotFound());
  }
}
