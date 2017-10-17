package com.travix.medusa.busyflights.repository.impl;

import com.travix.medusa.busyflights.domain.SuppliersResponse;
import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetRequest;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;
import com.travix.medusa.busyflights.repository.BusyFlightsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import javax.validation.Valid;

@Repository
public class ToughJetRepositoryImpl extends BusyFlightsRepository {

    @Value("${url.thoughjet}")
    private String url;

    @Override
    public SuppliersResponse[] search(BusyFlightsRequest request) {
        ToughJetRequest toughJetRequest = this.busyFlightsConverter.toToughJetRequest(request);

        return getToughJetSupplier(toughJetRequest);
    }

    public SuppliersResponse[] getToughJetSupplier(@Valid ToughJetRequest toughJetRequest) {
        return this.getSupplierResponse(url, ToughJetResponse[].class, toughJetRequest.getSupplierParameters());
    }
}
