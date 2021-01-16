# Cấu hình kết nối Postgresql


[pom.xml](pom.xml)
```xml
<dependency>
  <groupId>org.postgresql</groupId>
  <artifactId>postgresql</artifactId>
  <scope>runtime</scope>
</dependency>
```

[application.properties](src/main/resources/application.properties)
```
## default connection pool
spring.datasource.hikari.connectionTimeout=20000
spring.datasource.hikari.maximumPoolSize=5

## PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/blog
spring.datasource.username=user
spring.datasource.password=abc123
#spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

#drop n create table again, good for testing, comment this in production
spring.jpa.hibernate.ddl-auto=create



spring.jpa.properties.hibernate.hbm2ddl.import_files=user.sql
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

# Một số lỗi hay gặp phải

Cần tránh đặt tên bảng trùng với những từ khoá của Postgresql như:
- ```user```
- ```select```
- ```where```

# Kiểm thử với Postgresql
https://www.baeldung.com/spring-boot-testcontainers-integration-test
https://www.testcontainers.org/test_framework_integration/junit_5