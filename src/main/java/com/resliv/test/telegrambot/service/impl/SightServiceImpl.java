package com.resliv.test.telegrambot.service.impl;

import com.resliv.test.telegrambot.dto.converter.Converter;
import com.resliv.test.telegrambot.dto.SightDto;
import com.resliv.test.telegrambot.entity.Sight;
import com.resliv.test.telegrambot.exception.DeleteException;
import com.resliv.test.telegrambot.exception.SaveException;
import com.resliv.test.telegrambot.exception.UpdateException;
import com.resliv.test.telegrambot.repository.SightRepository;
import com.resliv.test.telegrambot.service.SightService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SightServiceImpl implements SightService {

    private static final String CITY_NOT_FOUND = "This city doesn't exist";
    private static final String RESPONSE = "I advise you to visit the following places:\n\n";

    private final SightRepository sightRepository;
    private final Converter converter;

    @Override
    public List<SightDto> findAllByCity(String city) {
        return sightRepository.findSightByCity_Name(city)
                .stream()
                .map(converter::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public SightDto save(Sight sight) {
        Optional<Sight> sightByName = sightRepository.findSightByName(sight.getName());

        if (sightByName.isPresent()) {
            throw new SaveException("Sight " + sight.getName() + " is already exist");
        }

        return converter.convertToDto(sightRepository.save(sight));
    }

    @Override
    public void delete(Long id) {
        if (!sightRepository.existsById(id)) {
            throw new DeleteException("Sight with id=" + id + " is not exists.");
        }

        sightRepository.deleteById(id);
    }

    @Override
    public SightDto update(Sight sight) {
        if (!sightRepository.existsById(sight.getId())) {
            throw new UpdateException("Sight " + sight.getName() + " is not exists.");
        }

        return converter.convertToDto(sightRepository.save(sight));
    }

    @Override
    public String findSight(String city) {
        List<SightDto> sights = findAllByCity(city);

        if (sights.isEmpty()) {
            return CITY_NOT_FOUND;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(RESPONSE);

        sights.forEach(sight -> {
            stringBuilder.append("> ");
            stringBuilder.append(sight.getName());
            stringBuilder.append("\n\n");
        });

        return stringBuilder.toString();
    }
}
