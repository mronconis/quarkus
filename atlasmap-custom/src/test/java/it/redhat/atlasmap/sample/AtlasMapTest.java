package it.redhat.atlasmap.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

import io.atlasmap.api.AtlasContext;
import io.atlasmap.api.AtlasContextFactory;
import io.atlasmap.api.AtlasSession;
import io.atlasmap.core.DefaultAtlasContextFactory;

public class AtlasMapTest {
    final static private Logger log = LogManager.getLogger(AtlasMapTest.class);

    String source = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Person><name>maria</name><surname>verdi</surname><gender>WOMAN</gender></Person>";
    
    @Test
    public void mappingFromXmlSchemaTest() throws Exception {
        String exptectedTarget = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><envelop xmlns=\"it.redhat.atlasmap.sample/env\"><customer xmlns=\"it.redhat.atlasmap.sample/customer\"><name>maria</name></customer></envelop>";

        URL url = Thread.currentThread().getContextClassLoader().getResource("mapping/atlasmapping-from-xml-schema.adm");
        AtlasContextFactory factory = DefaultAtlasContextFactory.getInstance();
        AtlasContext context = factory.createContext(url.toURI());
        AtlasSession session = context.createSession();

        session.setSourceDocument("person", source);
        context.process(session);

        String target = (String) session.getTargetDocument("customer-schemaset-43ca725e-d42a-434e-bbd6-30170b553acb");

        log.info("Target document from schema: " + target);

        assertEquals(exptectedTarget, target);
    }

    @Test
    public void mappingFromXmlInstanceTest() throws Exception {
        String exptectedTarget = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><envelop xmlns:cus=\"it.redhat.atlasmap.sample/customer\"><cus:customer><cus:name>maria</cus:name></cus:customer></envelop>";

        URL url = Thread.currentThread().getContextClassLoader().getResource("mapping/atlasmapping-from-xml-instance.adm");
        AtlasContextFactory factory = DefaultAtlasContextFactory.getInstance();
        AtlasContext context = factory.createContext(url.toURI());
        AtlasSession session = context.createSession();

        session.setSourceDocument("person", source);
        context.process(session);
        String target = (String) session.getTargetDocument("customer-instance-7da3fc84-6e6b-42cc-96a9-bac5f17dd83b");

        log.info("Target document from instance: " + target);

        assertEquals(exptectedTarget, target);
    }

    @Test
    public void mappingWithCustomTransformationTest() throws Exception {
        String exptectedTarget = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><envelop xmlns=\"it.redhat.atlasmap.sample/env\"><customer xmlns=\"it.redhat.atlasmap.sample/customer\"><name>Paul's custom field action: foo payload: maria</name></customer></envelop>";

        URL url = Thread.currentThread().getContextClassLoader().getResource("mapping/atlasmapping-custom-transformation.adm");
        AtlasContextFactory factory = DefaultAtlasContextFactory.getInstance();
        AtlasContext context = factory.createContext(url.toURI());
        AtlasSession session = context.createSession();

        session.setSourceDocument("person", source);
        context.process(session);

        String target = (String) session.getTargetDocument("customer-schemaset-b3013600-fe74-41e2-aba1-df7b89f801fb");

        log.info("Target document custom transformation: " + target);

        assertEquals(exptectedTarget, target);
    }
}
