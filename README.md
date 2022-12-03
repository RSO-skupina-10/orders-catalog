# orders catalog

## How to start app (first option)
### run docker:
- `docker run -d --name postgres-jdbc2 -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=ordercatalog -p 5433:5432 postgres:13`

### build and run:

- `mvn clean package`

- `java -jar api/target/api-1.0-SNAPSHOT.jar`

### now your app started
try:
- http://localhost:8081/v1/orders
