package com.resliv.test.telegrambot.service;

import com.resliv.test.telegrambot.dto.SightDto;
import com.resliv.test.telegrambot.entity.Sight;

import java.util.List;

public interface SightService {

    List<SightDto> findAllByCity(String city);

    SightDto save(Sight sight);

    void delete(Long id);

    SightDto update(Sight sight);

    String findSight(String city);
}
