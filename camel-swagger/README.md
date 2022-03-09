# camel-swagger Project

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/camel-extension-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.
ss
## Provided Code

### Quarkus OpenAPI & Swagger UI
Enable quarkus OpenAPI & Swagger UI by configuring the following maven dependency
````
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-smallrye-openapi</artifactId>
</dependency>
````

By default, Swagger UI is only available when Quarkus is started in dev or test mode.
If you want to make it available in production too, you can include the following configuration in your application.properties:
````
quarkus.swagger-ui.always-include=true
````

The UI exposed at following URL
````
http://localhost:8080/swagger-ui
````

### Camel OpenAPI
Enable camel OpenAPI by configuring the following maven dependency
````
<dependency>
    <groupId>org.apache.camel.quarkus</groupId>
    <artifactId>camel-quarkus-openapi-java</artifactId>
</dependency>
````

Configure Swagger UI to explore camel openapi on default
````
quarkus.swagger-ui.urls.direct=/c/openapi
````

The documentation exposed at following URL
````
http://localhost:8080/c/openapi?format=json
````
