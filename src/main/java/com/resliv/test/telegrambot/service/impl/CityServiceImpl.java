package com.resliv.test.telegrambot.service.impl;

import com.resliv.test.telegrambot.dto.CityDto;
import com.resliv.test.telegrambot.dto.converter.Converter;
import com.resliv.test.telegrambot.entity.City;
import com.resliv.test.telegrambot.entity.PageCity;
import com.resliv.test.telegrambot.exception.DeleteException;
import com.resliv.test.telegrambot.exception.SaveException;
import com.resliv.test.telegrambot.exception.UpdateException;
import com.resliv.test.telegrambot.repository.CityRepository;
import com.resliv.test.telegrambot.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final Converter converter;

    @Override
    public PageCity findAll(Pageable pageable) {
        PageCity pageCity = new PageCity();

        Page<City> cities = cityRepository.findAll(pageable);

        pageCity.setCities(cities.getContent()
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList()));
        pageCity.setPages(getPages(pageable.getPageSize()));

        return pageCity;
    }

    @Override
    public CityDto save(City city) {
        Optional<City> cityByName = cityRepository.findCityByName(city.getName());

        if (cityByName.isPresent()) {
            throw new SaveException("City " + city.getName() + " is already exist");
        }

        return converter.convertToDto(cityRepository.save(city));
    }

    @Override
    public void delete(Long id) {
        if (!cityRepository.existsById(id)) {
            throw new DeleteException("City with id=" + id + " is not exists.");
        }

        cityRepository.deleteById(id);
    }

    @Override
    public CityDto update(City city) {
        if (!cityRepository.existsById(city.getId())) {
            throw new UpdateException("City " + city.getName() + " is not exists.");
        }

        return converter.convertToDto(cityRepository.save(city));
    }

    private int getPages(int size) {
        return (int) cityRepository.count() / size;
    }
}
