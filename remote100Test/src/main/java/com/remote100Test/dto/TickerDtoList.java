package com.remote100Test.dto;

import java.util.List;

public class TickerDtoList {
    List<TickerDto> tickerDtos;

    public List<TickerDto> getTickerDtoList() {
        return tickerDtos;
    }

    public void setTickerDtoList(List<TickerDto> tickerDtoList) {
        this.tickerDtos = tickerDtoList;
    }

    public TickerDtoList() {
    }

    public TickerDtoList(List<TickerDto> tickerDtoList) {
        this.tickerDtos = tickerDtoList;
    }
}
