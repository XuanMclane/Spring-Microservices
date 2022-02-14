package com.minxuan.microservices.currencyexchangeservice;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
public class CurrencyExchangeController {
    private Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

    private final Environment environment;

    private final CurrencyExchangeRepository repository;

    @Autowired
    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ResponseEntity retrieveExchangeValue(
            @PathVariable String from, @PathVariable String to) {
        logger.info("retrieveExchangeValue called with {} to {}", from, to);
        Optional<CurrencyExchange> currencyExchange = repository.findByFromAndTo(from, to);
        if (!currencyExchange.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        currencyExchange.get().setEnvironment(environment.getProperty("local.server.port"));

        return ResponseEntity.ok(currencyExchange);
    }
}
