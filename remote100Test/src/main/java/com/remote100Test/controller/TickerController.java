package com.remote100Test.controller;

import com.remote100Test.dto.TickerDtoList;
import com.remote100Test.model.TickerModel;
import com.remote100Test.service.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/")
public class TickerController {
    Map<String, List<TickerModel>> currentTicks = new HashMap<>();
    List<TickerModel> tickerModels = new ArrayList<>();
    @Autowired
    TickerService tickerService;

    @PostMapping(
            value = "/tickConsume", consumes = "application/json", produces = "application/json")
    public void addTick(@RequestBody TickerModel tickerModel) {
        tickerModels.add(tickerModel);
        currentTicks = tickerService.tickUpdate(tickerModels);
    }

    @GetMapping("/getTicks")
    @CrossOrigin(origins = "http://localhost:3000")
    public TickerDtoList readTick() {
        return tickerService.tickGet(currentTicks);
    }

}
