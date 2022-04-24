FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN mkdir /newrelic && cd /newrelic \
  && curl -O https://download.newrelic.com/newrelic/java-agent/newrelic-agent/current/newrelic.jar
COPY newrelic/newrelic.yml newrelic
ENTRYPOINT ["java","-javaagent:newrelic/newrelic.jar","-Dcom.sun.management.jmxremote=true","-Dcom.sun.management.jmxremote.port=8084","-Dcom.sun.management.jmxremote.local.only=false","-Dcom.sun.management.jmxremote.authenticate=false","-Dcom.sun.management.jmxremote.ssl=false","-Dcom.sun.management.jmxremote.rmi.port=8084","-Djava.rmi.server.hostname=localhost","-jar","/app.jar"]
EXPOSE 8082
EXPOSE 8084