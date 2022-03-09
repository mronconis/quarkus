package it.redhat.quarkus.sample;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;

@ApplicationScoped
public class SampleRoute extends RouteBuilder {
    
    @Override
    public void configure() throws Exception {
         
        rest("/xml2xml")
            .post()
            .route().routeId("xml2xml")
            .log("Input: ${body}")
            .to("direct:mapping");

        from("direct:mapping")
            .routeId("atlasmap-xml2xml")
            .to("atlasmap:mapping/atlasmapping-xml-to-xml.adm")
            .log("Output: ${body}");
    }
}

