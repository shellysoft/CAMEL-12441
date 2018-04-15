package com.camel.sample;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.xml.Namespaces;
import org.apache.camel.processor.aggregate.UseLatestAggregationStrategy;

public class MyRouteBuilder extends RouteBuilder {

    private static final Namespaces NAMESPACES = new Namespaces("", "*");
    public static final String IN = "direct:in";
    public static final String OUT = "direct:out";

    @Override
    public void configure() {
        from(IN)
                .split()
                .xtokenize("//person", 'i', NAMESPACES)
                    .aggregationStrategy(new UseLatestAggregationStrategy())
                    .stopOnException()
                    .parallelAggregate()
                    .parallelProcessing()
                    .streaming().stopOnException()
                    .id("xml-splitter")
                .to(OUT)
                .end()
                .routeId(getClass().getSimpleName());
    }
}
