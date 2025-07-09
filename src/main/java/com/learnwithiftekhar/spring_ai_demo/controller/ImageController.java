package com.learnwithiftekhar.spring_ai_demo.controller;

import com.learnwithiftekhar.spring_ai_demo.service.AIService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/image")
public class ImageController {
    private final AIService aiService;

    public ImageController(AIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/generate")
    public String imageForm() {
        return "imageForm";
    }

    @PostMapping("/generate")
    public String generateImage(
            @RequestParam(name = "instruction") String instruction,
            Model model) {
        String imageUrl = aiService.generateImage(instruction);
        model.addAttribute("imageUrl", imageUrl);
        return "show";
    }
}
