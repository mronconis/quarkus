package it.redhat.quarkus.sample;

import io.quarkus.test.junit.NativeImageTest;

@NativeImageTest
public class NativeSampleResourceIT extends SampleResourceTest {
    // Execute the same tests but in native mode.
}
