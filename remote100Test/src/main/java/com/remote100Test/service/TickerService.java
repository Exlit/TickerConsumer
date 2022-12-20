package com.remote100Test.service;

import com.remote100Test.dto.TickerDto;
import com.remote100Test.dto.TickerDtoList;
import com.remote100Test.model.TickerModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TickerService {

    public Map<String, List<TickerModel>> tickUpdate(List<TickerModel> tickerModels) {
        return tickerModels.stream().sorted(Comparator.comparing(TickerModel::getTimePlaced)).collect(Collectors.groupingBy((this::createKey)));
    }

    public TickerDtoList tickGet(Map<String, List<TickerModel>> tickerMap) {
        return getTickerDto(tickerMap);
    }

    private TickerDtoList getTickerDto(Map<String, List<TickerModel>> tickerMap) {
        List<TickerDto> tickerDtos = new ArrayList<>();
        tickerMap.forEach((k, v) -> {
            TickerDto tickerDto = new TickerDto();
            tickerDto.setCurrencyPair(k);
            tickerDto.setTickerModels(v.stream().sorted(Comparator.comparing(TickerModel::getTimePlaced)).collect(Collectors.toList()));
            tickerDto.setDailyPercentage(getChange(tickerDto.getTickerModels(), 1));
            tickerDto.setWeeklyPercentage(getChange(tickerDto.getTickerModels(), 7));
            tickerDto.setMonthlyPercentage(getChange(tickerDto.getTickerModels(), 30));
            tickerDto.setCurrentBuyPrice(v.get(v.size()-1).getAmountBuy());
            tickerDto.setCurrentSellPrice(v.get(v.size()-1).getAmountSell());
            tickerDto.setCurrentRate(v.get(v.size()-1).getRate());
            tickerDtos.add(tickerDto);
        });
        return new TickerDtoList(tickerDtos);
    }

    private String createKey(TickerModel tickerModel) {
        return tickerModel.getCurrencyFrom() + tickerModel.getCurrencyTo();
    }

    private int getChange(List<TickerModel> tickerModels, int days) {
        List<TickerModel> sortedTickerModels = tickerModels.
                stream()
                .filter(tickerModel -> tickerModel.getTimePlaced().toLocalDate().isAfter(LocalDate.now().minusDays(days)))
                .collect(Collectors.toList());
        if (sortedTickerModels.size() > 1) {
            double firstTick = sortedTickerModels.get(0).getAmountSell();
            double lastTick = sortedTickerModels.get(sortedTickerModels.size() - 1).getAmountSell();
            return (int) ((lastTick / firstTick) * 100 - 100);
        } else {
            return 0;
        }
    }
}
