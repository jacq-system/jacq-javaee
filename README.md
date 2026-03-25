Development Setup
- openjdk 17
- WildFly Plugin / JavaEE Edition
- WildFly 31.0.1.Final

Maven project can be opened directly from netbeans.

jacq-common contains JPA / JAX-RS classes / interfaces only.
jacq-service contains the actual implementation using manager classes.

# To build

We are using the the maven wrapper. So, instead of call mvn please use mvnw which is located in the `jacq` directory. For furher information
have a look at https://maven.apache.org/wrapper/index.html

This means you don't have to install maven manually.

## Skipping Tests

You can disable tests during the build process in two ways:

1. Using the command-line parameter:
   ```
   cd jacq && ./mvnw package -DskipTests=true
   ```

2. Using the skip-tests profile:
   ```
   cd jacq && ./mvnw package -Pskip-tests
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
2. Builds the project using the Maven wrapper (mvnw) in the `jacq` directory with tests skipped (-DskipTests=true)
3. Uploads the built artifacts (JAR and WAR files, as well as BIRT reports and fonts) for later use

You can also trigger the build workflow manually from the Actions tab in GitHub.

## Deploy Workflow

The deploy workflow is designed to automatically deploy the application to a remote WildFly server via SSH/SCP when manually triggered. It:

1. Downloads the built artifacts from a specific build run
2. Creates the necessary directories on the remote server
3. Copies JARs, WARs, BIRT reports, and fonts to the remote server
4. Installs the Code-39 TTF font system-wide
5. Restarts the WildFly service

### Required GitHub Variables and Secrets

To deploy to a remote WildFly server, you need to configure the following GitHub repository variables and secrets:

#### Variables (Settings > Secrets and variables > Actions > Variables)
- `WILDFLY_HOST`: The hostname or IP address of your WildFly server

#### Secrets (Settings > Secrets and variables > Actions > Secrets)
- `SSH_PRIVATE_KEY`: The private SSH key used for authentication on the remote server
- `GITHUB_TOKEN`: (Provided automatically by GitHub) Used to download artifacts from other workflows

These credentials are used securely by the GitHub Actions workflow to connect to your remote WildFly server and deploy the application.
