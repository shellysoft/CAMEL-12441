package com.camel.sample;

import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.impl.DefaultExchange;

import org.springframework.core.io.Resource;

import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

final class TestHelper {

    static Exchange buildExchange(final ProducerTemplate producerTemplate, final Resource resource) throws Exception {
        final Exchange exchange = new DefaultExchange(producerTemplate.getCamelContext());
        exchange.getIn().setBody(FileUtils.readFileToString(resource.getFile(), StandardCharsets.UTF_8));
        return exchange;
    }
}
