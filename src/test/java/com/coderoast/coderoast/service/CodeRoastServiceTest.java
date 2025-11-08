package com.coderoast.coderoast.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.coderoast.coderoast.entity.CodeSnippet;
import com.coderoast.coderoast.repository.CodeSnippetRepository;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CodeRoastServiceTest {

  @Mock private CodeSnippetRepository repository;

  @InjectMocks private CodeRoastService service;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testSubmitCode() {
    // Given
    String code = "public class Test {}";
    String language = "java";
    CodeSnippet savedSnippet = new CodeSnippet(code, language);
    savedSnippet.setId(1L);
    savedSnippet.setRoast("Test roast");
    savedSnippet.setTips("Test tips");

    when(repository.save(any(CodeSnippet.class))).thenReturn(savedSnippet);

    // When
    CodeSnippet result = service.submitCode(code, language);

    // Then
    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals(code, result.getCode());
    assertEquals(language, result.getLanguage());
    assertNotNull(result.getRoast());
    assertNotNull(result.getTips());
    verify(repository, times(1)).save(any(CodeSnippet.class));
  }

  @Test
  void testGetAllSnippets() {
    // Given
    CodeSnippet snippet1 = new CodeSnippet("code1", "java");
    CodeSnippet snippet2 = new CodeSnippet("code2", "python");
    List<CodeSnippet> snippets = Arrays.asList(snippet1, snippet2);

    when(repository.findAll()).thenReturn(snippets);

    // When
    List<CodeSnippet> result = service.getAllSnippets();

    // Then
    assertEquals(2, result.size());
    verify(repository, times(1)).findAll();
  }

  @Test
  void testGetSnippetById() {
    // Given
    Long id = 1L;
    CodeSnippet snippet = new CodeSnippet("code", "java");
    snippet.setId(id);

    when(repository.findById(id)).thenReturn(Optional.of(snippet));

    // When
    Optional<CodeSnippet> result = service.getSnippetById(id);

    // Then
    assertTrue(result.isPresent());
    assertEquals(id, result.get().getId());
    verify(repository, times(1)).findById(id);
  }

  @Test
  void testGetSnippetByIdNotFound() {
    // Given
    Long id = 1L;

    when(repository.findById(id)).thenReturn(Optional.empty());

    // When
    Optional<CodeSnippet> result = service.getSnippetById(id);

    // Then
    assertFalse(result.isPresent());
    verify(repository, times(1)).findById(id);
  }
}
