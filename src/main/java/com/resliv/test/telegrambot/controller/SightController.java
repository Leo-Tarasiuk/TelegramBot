package com.resliv.test.telegrambot.controller;

import com.resliv.test.telegrambot.dto.SightDto;
import com.resliv.test.telegrambot.entity.Sight;
import com.resliv.test.telegrambot.service.SightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sight/")
public class SightController {

    private final SightService sightService;

    public SightController(SightService sightService) {
        this.sightService = sightService;
    }

    @GetMapping("{city}")
    public List<SightDto> getAll(@PathVariable("city") String city) {
        return sightService.findAllByCity(city);
    }

    @PostMapping("add")
    public SightDto save(@RequestBody Sight sight) {
        return sightService.save(sight);
    }

    @PostMapping("update")
    public SightDto update(@RequestBody Sight sight) {
        return sightService.update(sight);
    }

    @PostMapping("delete")
    public void delete(@RequestBody Long id) {
        sightService.delete(id);
    }
}
