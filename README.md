# Demo module for Database Interactions from Java
## Pre-requisites
1. MS SQL Server
2. Create NorthwindDB in MS SQL Server.
    1. Copy the sample [SQL File](https://github.com/microsoft/sql-server-samples/blob/master/samples/databases/northwind-pubs/instnwnd.sql) from [Microsoft SQL Server Samples repo](https://github.com/microsoft/sql-server-samples) on GitHub
    2. Connect to MS SQL Server (either by using SSMS or any other client)
    3. Run the script

## How to run
### JDBC
1. Set `host`, `port`, `user`, `password` in [JdbcTests.java](src/test/java/JdbcTests.java)
2. Run the [JDBC Tests](src/test/java/JdbcTests.java)

### Hibernate
1. Set host, port in `connection.url` property in [hibernate.cfg.xml](src/main/resources/hibernate.cfg.xml)
2. Set user in `connection.username` property in [hibernate.cfg.xml](src/main/resources/hibernate.cfg.xml)
3. Set password in `connection.password` property in [hibernate.cfg.xml](src/main/resources/hibernate.cfg.xml)
4. Run the [Hibernate Tests](src/test/java/HibernateTests.java)

### Spring JPA
1. Set host, port in `javax.persistence.jdbc.url` property in [persistence.xml](src/main/resources/META-INF/persistence.xml)
2. Set user in `javax.persistence.jdbc.user` property in [persistence.xml](src/main/resources/META-INF/persistence.xml)
3. Set password in `javax.persistence.jdbc.password` property in [persistence.xml](src/main/resources/META-INF/persistence.xml)

Run the [Spring JPA](src/test/java/SpringJpa.java) tests

javax.persistence.jdbc.url