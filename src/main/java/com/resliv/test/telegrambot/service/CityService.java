package com.resliv.test.telegrambot.service;

import com.resliv.test.telegrambot.dto.CityDto;
import com.resliv.test.telegrambot.entity.City;
import com.resliv.test.telegrambot.entity.PageCity;
import org.springframework.data.domain.Pageable;

public interface CityService {

    PageCity findAll(Pageable pageable);

    CityDto save(City city);

    void delete(Long id);

    CityDto update(City city);
}
