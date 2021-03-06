package com.resliv.test.telegrambot.dto.converter;

import com.resliv.test.telegrambot.dto.CityDto;
import com.resliv.test.telegrambot.dto.SightDto;
import com.resliv.test.telegrambot.entity.City;
import com.resliv.test.telegrambot.entity.Sight;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class Converter {

    private final ModelMapper modelMapper;

    public SightDto convertToDto(Sight sight) {
        return modelMapper.map(sight, SightDto.class);
    }

    public CityDto convertToDto(City city) {
        CityDto cityDto = modelMapper.map(city, CityDto.class);

        if (city.getSights() != null) {
            cityDto.setSights(city.getSights()
                    .stream()
                    .map(this::convertToDto)
                    .collect(Collectors.toList()));
        }

        return cityDto;
    }
}
