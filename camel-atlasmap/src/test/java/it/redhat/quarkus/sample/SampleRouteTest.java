package it.redhat.quarkus.sample;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class SampleRouteTest {
    
    @Test
    void testWomanCustomer() {
        String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Person><name>maria</name><surname>verdi</surname><gender>WOMAN</gender></Person>";
        String expectedResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><envelop xmlns=\"it.redhat.atlasmap/env\"><customer xmlns=\"it.redhat.atlasmap/customer\"><name>Maria</name><surname>Verdi</surname><fullname>Miss. Maria Verdi</fullname></customer></envelop>";

        given()
            .contentType(ContentType.XML)
            .body(request)
            .when()
            .post("/xml2xml")
            .then()
            .body(equalTo(expectedResponse));
    }

    @Test
    void testManCustomer() {
        String request = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Person><name>mario</name><surname>rossi</surname><gender>MAN</gender></Person>";
        String expectedResponse = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><envelop xmlns=\"it.redhat.atlasmap/env\"><customer xmlns=\"it.redhat.atlasmap/customer\"><name>Mario</name><surname>Rossi</surname><fullname>Sr. Mario Rossi</fullname></customer></envelop>";

        given()
            .contentType(ContentType.XML)
            .body(request)
            .when()
            .post("/xml2xml")
            .then()
            .body(equalTo(expectedResponse));
    }
}
