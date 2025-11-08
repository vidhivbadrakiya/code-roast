package com.coderoast.coderoast.controller;

import com.coderoast.coderoast.entity.CodeSnippet;
import com.coderoast.coderoast.service.CodeRoastService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/roast")
public class CodeRoastController {

  @Autowired private CodeRoastService service;

  @PostMapping("/submit")
  public ResponseEntity<CodeSnippet> submitCode(@RequestBody CodeSnippetRequest request) {
    CodeSnippet snippet = service.submitCode(request.getCode(), request.getLanguage());
    return ResponseEntity.ok(snippet);
  }

  @GetMapping("/snippets")
  public ResponseEntity<List<CodeSnippet>> getAllSnippets() {
    List<CodeSnippet> snippets = service.getAllSnippets();
    return ResponseEntity.ok(snippets);
  }

  @GetMapping("/snippet/{id}")
  public ResponseEntity<CodeSnippet> getSnippetById(@PathVariable Long id) {
    Optional<CodeSnippet> snippet = service.getSnippetById(id);
    return snippet.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
  }

  public static class CodeSnippetRequest {
    private String code;
    private String language;

    public String getCode() {
      return code;
    }

    public void setCode(String code) {
      this.code = code;
    }

    public String getLanguage() {
      return language;
    }

    public void setLanguage(String language) {
      this.language = language;
    }
  }
}
