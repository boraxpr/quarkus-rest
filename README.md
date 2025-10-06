# quarkus-rest: Pragmatic REST API Starter

A clean, opinionated Quarkus setup built for small-to-medium backends — fast dev, low friction, strong conventions.

- ✅ Minimal boilerplate  
- ✅ Designed for **pragmatic teams** — fast-moving projects, prototype-to-prod  
- ✅ Emphasizes **JSON-first**, **typed query**, and **explicit mapping control**  
- ✅ Avoids overengineering (no premature hexagonal layering unless needed)  

---

## ⚙️ Tech Stack

- **Quarkus**: Supersonic, subatomic Java framework  
- **Jakarta REST**: Native REST endpoints  
- **Jackson**: JSON serialization  
- **PostgreSQL via JDBC**  
- *(Optional)*: `row_to_json` + Jackson mapping for frictionless data handling

---

## 🚀 Getting Started

### Dev Mode (live reload + Dev UI)

```bash
./mvnw quarkus:dev
```
### Open Telemetry tracings & logs for dev testing (Export via logging/stdout For Dev; For prod, it must be exported via OTLP)

#### Method 1 : Via All-in-One binary
```bash
curl -L -O https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar
export JAVA_TOOL_OPTIONS="-javaagent:./opentelemetry-javaagent.jar"   OTEL_TRACES_EXPORTER=logging OTEL_LOGS_EXPORTER=logging OTEL_INSTRUMENTATION_JDBC_ENABLED=true
```
#### Method 2 : Quarkus Style (Database Connection is auto-configured for mvnw dev)
application.properties
```properties
quarkus.otel.traces.exporter=logging 
quarkus.datasource.jdbc.telemetry=true
quarkus.datasource.jdbc.metrics.enabled=true
```
pom.xml
```xml
<dependency>
    <groupId>io.quarkus</groupId>
    <artifactId>quarkus-opentelemetry</artifactId>
</dependency>
<dependency>
    <groupId>io.opentelemetry</groupId>
    <artifactId>opentelemetry-exporter-logging</artifactId>
</dependency>
<dependency>
    <groupId>io.opentelemetry.instrumentation</groupId>
    <artifactId>opentelemetry-jdbc</artifactId>
</dependency>
```
