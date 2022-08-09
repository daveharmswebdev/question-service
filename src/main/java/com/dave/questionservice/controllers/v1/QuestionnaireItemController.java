package com.dave.questionservice.controllers.v1;

import com.dave.questionservice.api.v1.model.QuestionnaireItemDto;
import com.dave.questionservice.services.QuestionnaireItemService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(QuestionnaireItemController.BASE_URL)
public class QuestionnaireItemController {

    public static final String BASE_URL = "/api/v1/questionnaireItems";

    private final QuestionnaireItemService questionnaireItemService;

    public QuestionnaireItemController(QuestionnaireItemService questionnaireItemService) {
        this.questionnaireItemService = questionnaireItemService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionnaireItemDto> getListOfItems() {
        return questionnaireItemService.getAllQuestionnaireItems();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QuestionnaireItemDto getById(@PathVariable Long id) {
        return questionnaireItemService.getQuestionnaireItemById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public QuestionnaireItemDto createNewItem(@RequestBody QuestionnaireItemDto dto) {
        return questionnaireItemService.createNewQuestionnaireItem(dto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public QuestionnaireItemDto updateQuestionnaireItem(@PathVariable Long id, @RequestBody QuestionnaireItemDto questionnaireItemDto) {
        return questionnaireItemService.saveQuestionnaireItemByDto(id, questionnaireItemDto);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteQuestionnaireItem(@PathVariable Long id) {
        questionnaireItemService.deleteQuestionnaireItemById(id);
    }

}
