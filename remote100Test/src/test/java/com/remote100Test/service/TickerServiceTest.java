package com.remote100Test.service;

import com.remote100Test.dto.TickerDto;
import com.remote100Test.dto.TickerDtoList;
import com.remote100Test.model.TickerModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TickerServiceTest {

    private TickerService tickerService;
    private TickerDto dto;
    private List<TickerModel> tickerModels;
    private Map<String, List<TickerModel>> map;

    @BeforeEach
    public void init() {
        tickerService = new TickerService();
        TickerModel dayTick1 = new TickerModel("EUR", "USD", 0.92, 0.82, LocalDateTime.of(2022, 12, 19, 10, 00, 00));
        TickerModel dayTick2 = new TickerModel("EUR", "USD", 0.95, 0.85, LocalDateTime.of(2022, 12, 19, 11, 00, 00));
        TickerModel weekTick = new TickerModel("EUR", "USD", 0.97, 0.87, LocalDateTime.of(2022, 12, 14, 11, 00, 00));
        TickerModel monthTick = new TickerModel("EUR", "USD", 0.90, 0.80, LocalDateTime.of(2022, 11, 22, 11, 00, 00));
        tickerModels = List.of(dayTick1, dayTick2, weekTick, monthTick);
        map = new HashMap<>();
        map.put("EURUSD", tickerModels);
        dto = new TickerDto();
        dto.setDailyPercentage(3);
        dto.setWeeklyPercentage(-2);
        dto.setMonthlyPercentage(5);
    }

    @Test
    void getTickerDtoValues() {
       TickerDtoList actualDtoList = tickerService.tickGet(map);
       TickerDto actualDto = actualDtoList.getTickerDtoList().get(0);
        Assert.assertEquals(dto.getDailyPercentage(), actualDto.getDailyPercentage());
        Assert.assertEquals(dto.getWeeklyPercentage(), actualDto.getWeeklyPercentage());
        Assert.assertEquals(dto.getMonthlyPercentage(), actualDto.getMonthlyPercentage());
    }
}