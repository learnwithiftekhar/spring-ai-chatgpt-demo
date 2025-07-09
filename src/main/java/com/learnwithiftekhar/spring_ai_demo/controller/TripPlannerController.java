package com.learnwithiftekhar.spring_ai_demo.controller;

import com.learnwithiftekhar.spring_ai_demo.dto.PlanModel;
import com.learnwithiftekhar.spring_ai_demo.service.AIService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trip-planner")
public class TripPlannerController {
    private final AIService aiService;

    public TripPlannerController(AIService aiService) {
        this.aiService = aiService;
    }

    @GetMapping
    public String planMyTrip(@RequestBody PlanModel planModel) {
        String prompt = """
                System:
                You are a friendly but concise trip planner
                User:
                Plan a weekend trip for %s starting on %s
                Budget: %s USD total
                Travel Party: %s adults and %s children
                Return only the itinerary - no extra commentary.
                """.formatted(
                        planModel.getDestination(),
                        planModel.getDate(),
                        planModel.getBudget(),
                        planModel.getNumOfAdults(),
                        planModel.getNumOfChildren()
        );

        return aiService.chat(prompt);
    }
}
