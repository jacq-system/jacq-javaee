Development Setup
- openjdk 17
- WildFly Plugin / JavaEE Edition
- WildFly 31.0.1.Final

Maven project can be opened directly from netbeans.

jacq-common contains JPA / JAX-RS classes / interfaces only.
jacq-service contains the actual implementation using manager classes.

# To build

We are using the the maven wrapper. So, instead of call mvn please use mvnw. For furher information
have a look at https://maven.apache.org/wrapper/index.html

This means you don't have to install maven manually.

## Skipping Tests

You can disable tests during the build process in two ways:

1. Using the command-line parameter:
   ```
   ./mvnw package -DskipTests=true
   ```

2. Using the skip-tests profile:
   ```
   ./mvnw package -Pskip-tests
   ```

Both methods will skip the execution of tests during the build process.


# Wildfly 31.0.1.Final

To avoid the error `ModuleNotFoundException` java.se with Java 17 you have to add in the standalone.sh following line. Add it at the beginning of the file.

```
JAVA_OPTS="$JAVA_OPTS --add-modules=java.se"
```

# GitHub Actions

This project uses GitHub Actions for continuous integration and deployment:

## Build Workflow

The build workflow automatically builds the project when changes are pushed to the main/master or feature/next branches, or when a pull request is created targeting these branches. It:

1. Sets up Java 17
2. Builds the project using the Maven wrapper (mvnw) with tests skipped (-DskipTests=true)
3. Uploads the built artifacts (JAR and WAR files) for later use

You can also trigger the build workflow manually from the Actions tab in GitHub.

## Deploy Workflow

> **Note:** The deployment functionality is temporarily disabled for testing purposes. The workflow will run but will not actually deploy to the WildFly server.

The deploy workflow is designed to automatically deploy the application to a remote WildFly server when the build workflow completes successfully for the main/master or feature/next branches. When fully enabled, it:

1. Sets up Java 17
2. Downloads the built artifacts (or builds the project using the Maven wrapper if triggered manually)
3. Downloads the WildFly CLI client
4. Validates the WildFly connection parameters
5. Deploys the WAR files to the remote WildFly server

You can also trigger the deploy workflow manually from the Actions tab in GitHub.

### Required GitHub Variables and Secrets

To deploy to a remote WildFly server, you need to configure the following GitHub repository variables and secrets:

#### Variables (Settings > Secrets and variables > Actions > Variables)
- `WILDFLY_HOST`: The hostname or IP address of your WildFly server
- `WILDFLY_PORT`: (Optional) The management port of your WildFly server (default: 9990)

#### Secrets (Settings > Secrets and variables > Actions > Secrets)
- `WILDFLY_USERNAME`: The username for WildFly management authentication
- `WILDFLY_PASSWORD`: The password for WildFly management authentication

These credentials are used securely by the GitHub Actions workflow to connect to your remote WildFly server and deploy the application.
