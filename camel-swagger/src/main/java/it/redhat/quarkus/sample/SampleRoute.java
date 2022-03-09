package it.redhat.quarkus.sample;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.apache.camel.openapi.RestOpenApiSupport;

@ApplicationScoped
public class SampleRoute extends RouteBuilder {
    
    @Override
    public void configure() throws Exception {
       
        restConfiguration()
            .dataFormatProperty("prettyPrint", "true")
            .enableCORS(true)
            .apiComponent("openapi")
            .apiContextPath("{{camel.api.context.path}}")
            .apiProperty("api.title", "{{camel.api.title}}")
            .apiProperty("api.version", "{{camel.api.version}}");
                
        rest("/foo")
            .consumes("application/json")
            .produces("application/json")
            .post()
            .param().name("body").type(RestParamType.body).description("The user to update or create").endParam()
            .route().routeId("foo")
            .log("Input: ${body}");
       
    }
}

