package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.service.BusyFlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BusyFlightsController {

    @Autowired
    private BusyFlightsService busyFlightsService;

    @RequestMapping(value = "/busyflights/flights", method = RequestMethod.GET)
    public List<BusyFlightsResponse> busyFlightsSearch(@Valid BusyFlightsRequest request) {
        return this.busyFlightsService.searchFlights(request);
    }

}
