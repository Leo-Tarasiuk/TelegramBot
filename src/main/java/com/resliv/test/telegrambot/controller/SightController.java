package com.resliv.test.telegrambot.controller;

import com.resliv.test.telegrambot.dto.SightDto;
import com.resliv.test.telegrambot.entity.Sight;
import com.resliv.test.telegrambot.service.SightService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sight/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SightController {

    private final SightService sightService;

    @GetMapping("{city}")
    public List<SightDto> getAll(@PathVariable("city") String city) {
        return sightService.findAllByCity(city);
    }

    @PostMapping("add")
    public SightDto save(@RequestBody Sight sight) {
        return sightService.save(sight);
    }

    @PutMapping("update")
    public SightDto update(@RequestBody Sight sight) {
        return sightService.update(sight);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        sightService.delete(id);
    }
}
