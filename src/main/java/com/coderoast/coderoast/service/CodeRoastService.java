package com.coderoast.coderoast.service;

import com.coderoast.coderoast.entity.CodeSnippet;
import com.coderoast.coderoast.repository.CodeSnippetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class CodeRoastService {

    @Autowired
    private CodeSnippetRepository repository;

    private final Random random = new Random();

    private final String[] roasts = {
        "This code is so bad, even the compiler is laughing at it!",
        "If code was a person, this would be the one wearing socks with sandals.",
        "Your code is like a bad joke – it compiles, but nobody gets it.",
        "This snippet looks like it was written by a caffeinated squirrel.",
        "Impressive! You've managed to make 'Hello World' look complicated.",
        "Your code is so inefficient, it makes a snail look like a race car.",
        "If this code were a movie, it'd be titled 'The Never-Ending Bug'.",
        "This is the kind of code that makes senior developers cry.",
        "Your variable names are so cryptic, even Sherlock Holmes couldn't debug this.",
        "This code is so old-school, it probably runs on punch cards."
    };

    private final String[] tips = {
        "Consider using more descriptive variable names to improve readability.",
        "Add comments to explain complex logic.",
        "Use consistent indentation and formatting.",
        "Break down large methods into smaller, focused functions.",
        "Implement proper error handling.",
        "Use meaningful commit messages when versioning your code.",
        "Follow the DRY (Don't Repeat Yourself) principle.",
        "Write unit tests to ensure code reliability.",
        "Use version control effectively.",
        "Refactor code regularly to maintain clean architecture."
    };

    public CodeSnippet submitCode(String code, String language) {
        CodeSnippet snippet = new CodeSnippet(code, language);
        snippet.setRoast(generateRoast());
        snippet.setTips(generateTips());
        return repository.save(snippet);
    }

    public List<CodeSnippet> getAllSnippets() {
        return repository.findAll();
    }

    public Optional<CodeSnippet> getSnippetById(Long id) {
        return repository.findById(id);
    }

    private String generateRoast() {
        return roasts[random.nextInt(roasts.length)];
    }

    private String generateTips() {
        StringBuilder tipsBuilder = new StringBuilder();
        int numTips = random.nextInt(3) + 1; // 1 to 3 tips
        for (int i = 0; i < numTips; i++) {
            if (i > 0) tipsBuilder.append(" ");
            tipsBuilder.append(tips[random.nextInt(tips.length)]);
        }
        return tipsBuilder.toString();
    }
}
