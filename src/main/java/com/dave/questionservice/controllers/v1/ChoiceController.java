package com.dave.questionservice.controllers.v1;

import com.dave.questionservice.api.v1.model.ChoiceDto;
import com.dave.questionservice.services.ChoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ChoiceController.BASE_URL)
public class ChoiceController {

    public static final String BASE_URL = "/api/v1/choices";

    private final ChoiceService choiceService;

    public ChoiceController(ChoiceService choiceService) {
        this.choiceService = choiceService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ChoiceDto> getListOfChoices() {
        return choiceService.getAllChoices();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChoiceDto getChoiceById(@PathVariable Long id) {
        return choiceService.getChoiceById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChoiceDto createNewChoice(@RequestBody ChoiceDto choiceDto) {
        return choiceService.createNewChoice(choiceDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ChoiceDto updateChoice(@PathVariable Long id, @RequestBody ChoiceDto choiceDto) {
        return choiceService.saveChoiceByDto(id, choiceDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteChoice(@PathVariable Long id) {
        choiceService.deleteChoiceById(id);
    }
}
