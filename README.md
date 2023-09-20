Development Setup
- openjdk 17
- WildFly Plugin / JavaEE Edition
- WildFly 13.0.0

Maven project can be opened directly from netbeans.

jacq-common contains JPA / JAX-RS classes / interfaces only.
jacq-service contains the actual implementation using manager classes.

# To build

We are using the the maven wrapper. So, instead of call mvn please use mvnw. For furher information
have a look at https://maven.apache.org/wrapper/index.html

This means you don't have to install maven manually.


# Wildfly 13.0.0

To avoid the error `ModuleNotFoundException` java.se with Java 17 you have to add in the standalone.sh following line. Add it at the beginning of the file.

```

```