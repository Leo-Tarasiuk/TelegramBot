package com.resliv.test.telegrambot.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CityDto {

    private Long id;

    private String name;

    private List<SightDto> sights;
}
