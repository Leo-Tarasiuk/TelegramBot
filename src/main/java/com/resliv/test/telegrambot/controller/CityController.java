package com.resliv.test.telegrambot.controller;

import com.resliv.test.telegrambot.dto.CityDto;
import com.resliv.test.telegrambot.entity.City;
import com.resliv.test.telegrambot.entity.PageCity;
import com.resliv.test.telegrambot.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CityController {

    private static final String SORT_VALUE = "name";

    private final CityService cityService;

    @GetMapping("all")
    public PageCity getAll(@PageableDefault(sort = {SORT_VALUE}, direction = Sort.Direction.ASC) Pageable pageable) {
        return cityService.findAll(pageable);
    }

    @PostMapping("add")
    public CityDto save(@RequestBody City city) {
        return cityService.save(city);
    }

    @PutMapping("update")
    public CityDto update(@RequestBody City city) {
        return cityService.update(city);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        cityService.delete(id);
    }
}
