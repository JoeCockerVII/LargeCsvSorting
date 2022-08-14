package com.example.csvsorting.domain.service;

import com.example.csvsorting.domain.entity.ClientDetails;

import java.util.Comparator;

public class ClientDetailsFidComparator implements Comparator<ClientDetails> {
    @Override
    public int compare(ClientDetails o1, ClientDetails o2) {
        return o1.getFid() - o2.getFid();
    }
}
