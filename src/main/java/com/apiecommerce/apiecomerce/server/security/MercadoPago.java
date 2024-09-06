package com.apiecommerce.apiecomerce.server.security;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;

public class MercadoPago {

    PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
            .id("1234")
            .title("Games")
            .description("PS5")
            .pictureUrl("http://picture.com/PS5")
            .categoryId("games")
            .quantity(2)
            .currencyId("BRL")
            .unitPrice(new BigDecimal("4000"))
            .build();
    List<PreferenceItemRequest> item = new ArrayList<>();
    item.add(itemRequest);
    PreferenceRequest preferenceRequest = PreferenceRequest.builder()
            .items(items).build();
    PreferenceClient client = new PreferenceClient();
    Preference preference = client.create(request);

}
