package com.coderoast.coderoast.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "code_snippets")
public class CodeSnippet {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, columnDefinition = "TEXT")
  private String code;

  @Column(nullable = false)
  private String language;

  @Column(nullable = false)
  private LocalDateTime submittedAt;

  @Column(columnDefinition = "TEXT")
  private String roast;

  @Column(columnDefinition = "TEXT")
  private String tips;

  // Constructors
  public CodeSnippet() {}

  public CodeSnippet(String code, String language) {
    this.code = code;
    this.language = language;
    this.submittedAt = LocalDateTime.now();
  }

  // Getters and Setters
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

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

  public LocalDateTime getSubmittedAt() {
    return submittedAt;
  }

  public void setSubmittedAt(LocalDateTime submittedAt) {
    this.submittedAt = submittedAt;
  }

  public String getRoast() {
    return roast;
  }

  public void setRoast(String roast) {
    this.roast = roast;
  }

  public String getTips() {
    return tips;
  }

  public void setTips(String tips) {
    this.tips = tips;
  }
}
