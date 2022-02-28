package it.redhat.quarkus.sample;

import org.apache.camel.builder.RouteBuilder;

public class AtlasMapRoute extends RouteBuilder {
    
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

