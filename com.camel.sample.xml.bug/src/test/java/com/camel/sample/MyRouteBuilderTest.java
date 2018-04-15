package com.camel.sample;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.apache.camel.CamelContext;
import org.apache.camel.EndpointInject;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.javaconfig.CamelConfiguration;
import org.apache.camel.test.spring.CamelSpringDelegatingTestContextLoader;
import org.apache.camel.test.spring.CamelSpringRunner;
import org.apache.camel.test.spring.CamelTestContextBootstrapper;
import org.apache.camel.test.spring.MockEndpoints;
import org.apache.camel.test.spring.ShutdownTimeout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.ContextConfiguration;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(CamelSpringRunner.class)
@BootstrapWith(CamelTestContextBootstrapper.class)
@ContextConfiguration(
        classes = MyRouteBuilderTest.TestConfig.class,
        loader = CamelSpringDelegatingTestContextLoader.class)
@MockEndpoints
@DirtiesContext(
        classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ShutdownTimeout(1)
public class MyRouteBuilderTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyRouteBuilderTest.class);

    @EndpointInject(uri = "mock:" + MyRouteBuilder.IN)
    private MockEndpoint in;
    @EndpointInject(uri = "mock:" + MyRouteBuilder.OUT)
    private MockEndpoint out;

    @Produce(uri = MyRouteBuilder.IN)
    private ProducerTemplate producerTemplate;

    @Value("classpath:invalid.xml")
    private Resource invalid;

    @Autowired
    private CamelContext camelContext;

    @After
    public void afterTestMethod() throws Exception {
        MockEndpoint.assertIsSatisfied(camelContext);
    }


    @Test
    public void testInvalid() {

        in.expectedMessageCount(1);
        out.expectedMessageCount(2);

        try {
            producerTemplate.send(TestHelper.buildExchange(producerTemplate, invalid));
        } catch (Exception e) {
            LOGGER.error("An exception occurred while executing test", e);
        }


    }

    @Configuration
    static class TestConfig extends CamelConfiguration {
        static {
            // make sure application server uses the implementation we have tested
            System.setProperty("javax.xml.stream.XMLInputFactory", "com.ctc.wstx.stax.WstxInputFactory");
            System.setProperty("javax.xml.stream.XMLOuputFactory", "com.ctc.wstx.stax.WstxOutputFactory");

        }


        @Bean
        @Override
        public List<RouteBuilder> routes() {
            return Arrays.asList(routeUnderTest(), mockRoutes());

        }

        @Bean
        public RouteBuilder routeUnderTest() {
            return new MyRouteBuilder();
        }

        @Bean
        public RouteBuilder mockRoutes() {
            return new RouteBuilder() {
                @Override
                public void configure() {
                    from(MyRouteBuilder.OUT).to("mock:result");
                }
            };
        }
    }
}
