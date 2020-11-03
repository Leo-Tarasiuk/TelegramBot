package com.resliv.test.telegrambot.controller;

import com.resliv.test.telegrambot.dto.CityDto;
import com.resliv.test.telegrambot.entity.City;
import com.resliv.test.telegrambot.entity.PageCity;
import com.resliv.test.telegrambot.service.CityService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/city/")
public class CityController {

    private static final int PAGE_SIZE = 10;
    private static final String SORT_VALUE = "name";

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("all")
    public PageCity getAll(@PageableDefault(sort = {SORT_VALUE},
            direction = Sort.Direction.ASC,
            size = PAGE_SIZE) Pageable pageable) {

        return cityService.findAll(pageable);
    }

    @PostMapping("add")
    public CityDto save(@RequestBody City city) {
        return cityService.save(city);
    }

    @PostMapping("update")
    public CityDto update(@RequestBody City city) {
        return cityService.update(city);
    }

    @PostMapping("delete")
    public void delete(@RequestBody Long id) {
        cityService.delete(id);
    }
}
