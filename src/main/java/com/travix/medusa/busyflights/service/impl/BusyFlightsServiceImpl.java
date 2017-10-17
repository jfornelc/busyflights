package com.travix.medusa.busyflights.service.impl;

import com.travix.medusa.busyflights.domain.SuppliersResponse;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;
import com.travix.medusa.busyflights.repository.BusyFlightsRepository;
import com.travix.medusa.busyflights.repository.converter.BusyFlightsConverter;
import com.travix.medusa.busyflights.service.BusyFlightsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusyFlightsServiceImpl implements BusyFlightsService{

    @Autowired
    private List<BusyFlightsRepository> busyFlightsSuppliers;

    @Autowired
    private BusyFlightsConverter busyFlightsConverter;

    @Override
    public List<BusyFlightsResponse> searchFlights(BusyFlightsRequest request) {
        List<SuppliersResponse[]> suppliers = busyFlightsSuppliers.stream()
                .map(supplier -> searchFlights(supplier, request)).collect(Collectors.toList());


        List<BusyFlightsResponse> responses = suppliers.stream().flatMap(Arrays::stream)
                .map(response -> busyFlightsConverter.toBusyFlightResponse(response)).collect(Collectors.toList());

        responses.sort(Comparator.comparing(BusyFlightsResponse::getFare));

        return responses;
    }

    private SuppliersResponse[] searchFlights(BusyFlightsRepository supplier,
                                                        BusyFlightsRequest request) {
        return supplier.search(request);
    }
}
