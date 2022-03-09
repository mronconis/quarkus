package it.redhat.quarkus.sample;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class SampleResourceTest {
    
    @Test
    void testApi() {
        given()
            .when()
            .get("/foo/bar");
    }

    @Test
    void testApiDocs() {
        given()
            .when()
            .get("/q/openapi");
    }

    @Test
    void testSwaggerUI() {
        given()
            .when()
            .get("/swagger-ui");
    }
}
