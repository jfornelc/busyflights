package com.travix.medusa.busyflights.repository;

import com.travix.medusa.busyflights.repository.converter.BusyFlightsConverter;
import com.travix.medusa.busyflights.domain.SuppliersResponse;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public abstract class BusyFlightsRepository {

    @Autowired
    protected BusyFlightsConverter busyFlightsConverter;

    @Autowired
    protected RestTemplate restTemplate;

    public abstract SuppliersResponse[] search(BusyFlightsRequest request);

    protected <T, R extends T> T getSupplierResponse(String urlTemplate, Class<R> responseType, Object[] uriArguments) {
        return this.restTemplate.getForObject(urlTemplate, responseType, uriArguments);
    }

}
