package com.resliv.test.telegrambot.entity;

import com.resliv.test.telegrambot.dto.CityDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageCity {

    private int pages;

    private List<CityDto> cities;
}
