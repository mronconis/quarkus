# Quarkus DH key size 1024 bits in Java 8 or 11

## Enable DH >= 1024

- [Solution RH](https://access.redhat.com/solutions/4500291)
- [Documentation RH](https://access.redhat.com/documentation/en-us/openjdk/11/html-single/release_notes_for_openjdk_11.0.11/index#disabled_tls_1_0_and_1_1_versions)

Change the following entry
```
security.useSystemPropertiesFile=true

jdk.tls.disabledAlgorithms=SSLv3, TLSv1, TLSv1.1, RC4, DES, MD5withRSA, \
    DH keySize < 1024, EC keySize < 224, 3DES_EDE_CBC, anon, NULL, \
    include jdk.disabled.namedCurves
```

to

```
security.useSystemPropertiesFile=false

jdk.tls.disabledAlgorithms=SSLv3, RC4, DES, MD5withRSA, \
    DH keySize < 1024, EC keySize < 224, 3DES_EDE_CBC, anon, NULL, \
    include jdk.disabled.namedCurves
```

