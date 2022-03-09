package it.redhat.quarkus.sample;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeSampleRouteIT extends SampleRouteTest {
    // Execute the same tests but in native mode.
}
