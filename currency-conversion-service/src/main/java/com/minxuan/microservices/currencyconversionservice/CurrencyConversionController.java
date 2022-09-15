package com.minxuan.microservices.currencyconversionservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@Slf4j
public class CurrencyConversionController {
    private final CurrencyExchangeProxy proxy;

    @Autowired
    public CurrencyConversionController(CurrencyExchangeProxy proxy) {
        this.proxy = proxy;
    }

//    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
//    public ResponseEntity calculateConversion(
//            @PathVariable String from,
//            @PathVariable String to,
//            @PathVariable BigDecimal quantity
//            ) {
//        HashMap<String, String> uriVariables = new HashMap<>();
//        uriVariables.put("from", from);
//        uriVariables.put("to", to);
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<CurrencyConversion> responseEntity = restTemplate
//                .getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}",
//                CurrencyConversion.class, uriVariables);
//        CurrencyConversion currencyConversion = responseEntity.getBody();
//        currencyConversion.setQuantity(quantity);
//        currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
//        return ResponseEntity.ok(currencyConversion);
//    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public ResponseEntity calculateConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {
        log.info("calculate currency conversion from {} to {}", from, to);
        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);
        currencyConversion.setQuantity(quantity);
        currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
        return ResponseEntity.ok(currencyConversion);
    }
}
