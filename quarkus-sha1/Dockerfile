FROM registry.access.redhat.com/ubi8/ubi-minimal:8.6-854

ARG JAVA_PACKAGE=java-11-openjdk-headless
ARG RUN_JAVA_VERSION=1.3.8

RUN set -x \
    && microdnf update -y \
    && microdnf -y install curl ca-certificates ${JAVA_PACKAGE} --nodocs \
    && mkdir /deployments \
    && chown 1001 /deployments \
    && chmod "g+rwX" /deployments \
    && chown 1001:root /deployments \
    && curl https://repo1.maven.org/maven2/io/fabric8/run-java-sh/${RUN_JAVA_VERSION}/run-java-sh-${RUN_JAVA_VERSION}-sh.sh -o /deployments/run-java.sh \
    && chown 1001 /deployments/run-java.sh \
    && chmod 540 /deployments/run-java.sh \
    && microdnf clean all

ADD --chown=1001  quarkus-app/lib/ /deployments/lib/
ADD --chown=1001  quarkus-app/*.jar /deployments/
ADD --chown=1001  quarkus-app/app/ /deployments/app/
ADD --chown=1001  quarkus-app/quarkus/ /deployments/quarkus/

ADD java.security /etc/alternatives/jre/lib/security/java.security

EXPOSE 8080

USER 1001
ENTRYPOINT [ "/deployments/run-java.sh" ]
    