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

    String source = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Person xmlns:ns=\"foo\"><ns:name>Mario</ns:name><ns:surname>Rossi</ns:surname><ns:gender>MAN</ns:gender></Person>";
    String exptectedTarget = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><envelop xmlns:cus=\"it.redhat.atlasmap.sample/customer\"><cus:customer><cus:name>Mario</cus:name></cus:customer></envelop>";
    
    @Test
    public void mappingFromXmlSchemaTest() throws Exception {

        URL url = Thread.currentThread().getContextClassLoader().getResource("mapping/atlasmapping-from-xml-schema.adm");
        AtlasContextFactory factory = DefaultAtlasContextFactory.getInstance();
        AtlasContext context = factory.createContext(url.toURI());
        AtlasSession session = context.createSession();

        session.setSourceDocument("person", source);
        context.process(session);
        String target = (String) session.getTargetDocument("customer-schemaset-33417d3a-c092-4cc0-a8ff-fbe8484447b4");

        log.info("Target document from schema: " + target);

        assertEquals(exptectedTarget, target);
    }

    @Test
    public void mappingFromXmlInstanceTest() throws Exception {

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
    public void mappingFromXmlSchemaModifiedTest() throws Exception {

        URL url = Thread.currentThread().getContextClassLoader().getResource("mapping/atlasmapping-from-xml-schema-mod.adm");
        AtlasContextFactory factory = DefaultAtlasContextFactory.getInstance();
        AtlasContext context = factory.createContext(url.toURI());
        AtlasSession session = context.createSession();

        session.setSourceDocument("person", source);
        context.process(session);
        String target = (String) session.getTargetDocument("customer-schemaset-b9fe20ef-e634-4628-9fbe-433df42761ab");

        log.info("Target document from schema modified: " + target);

        assertEquals(exptectedTarget, target);
    }
}
