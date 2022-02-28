package it.redhat.quarkus.sample;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeAtlasMapRouteIT extends AtlasMapRouteTest {
    // Execute the same tests but in native mode.
}
