Spring Boot Examples
==========
Examples of how to configure multi-module projects all tracked under a consistent version to include:
* Pure library code (reusable POJO classes, interfaces, etc)
** The demo-core module has no spring dependencies at all, this encourages a pure library base which is easy to share across projects
* REST services in Spring boot with a Mongo backend
** The demo-webapp module uses Spring for Rest services, executable as a jar with embedded tomcat or deployable to a standalone tomcat
* Executable JAR in Spring boot that can be run as a locally-run daemon
** The demo-cli tool uses Spring for dependency injection and easily-created executable jar

# Steps to configure environment:
* Oracle Java 8 JDK installed on machine
* Eclipse IDE for Java developers (http://www.eclipse.org/downloads/packages/eclipse-ide-java-developers/neonr)
* Install Eclipse plugin for Spring (Spring IDE plugin, installed by opening Eclipse, Window menu > Eclipse Marketplace, searching for Spring > Install)
* Also install the 'buildship' gradle plugin (if it wasn't installed along with the Spring plugin... it might be included by default)

## Checking out the code
* Import the project from Git inside Eclipse
* On the last screen of the import dialog, choose "Import as a general project"
* After the code has been checked out, right-click 'spring-boot-example' in the workspace and choose `Gradle > Add Gradle Nature`

# Running the applications
* To see a list of all commands that can be executed, from within the 'spring-boot-example' directory, execute: `./gradlew tasks`
* Before running, install MongoDb for your machine and start with the command `mongod` 

### Webapp within Eclipse
* Right-click on the demo-webapp's `Application` class and choose `Run As > Spring Boot App`

### Webapp on the command line 
* REST services can be run on the command line with: `./gradlew :demo-webapp:bootRun`

### Command line client within Eclipse
* Right-click on the demo-cli's `Application` class and choose `Run As > Spring Boot App`
* This will run and stop because it didn't get its command-line arguments
* Go to `Run Configurations > demo-cli`
* 2nd tab for `Arguments`, enter `time` as the sole entry, save and re-run
* To create a run config for the command line rest-client, copy the above config and enter appropriate arguments for the other driver

### Command line client on the command line
* Execute: `./gradlew :demo-cli:bootRepackage`
* Then execute: `java -jar demo-cli/build/libs/demo-cli-1.0.0-SNAPSHOT.jar time`
 

# Tests
## Running all tests on the command line
` ./gradlew test`

## Viewing test coverage
* Execute: `/gradlew clean test build jacocoTestReport`
* Output html will be saved in: `spring-boot-example/demo-webapp/build/reports/jacoco/test/html/index.html`


# Example of working with the mongo command line client (comes with mongo installation)
* Start mongo daemon locally without authentication with: `mongod`
* Open the command line client with the command: `mongo`
    
    MongoDB shell version: 3.2.8
    connecting to: test
    > use test
    switched to db test
    > show collections
    Person
    > db.Person.find().pretty()
    {
        "_id" : ObjectId("579d60c0b428cd1d3b8832cd"),
        "name" : "andrew",
        "age" : 32
    }
    > 
    
# Invoking the REST services
* Strongly recommend using Postman for manually verifying the REST services

## save() method via curl

    curl -X POST -H "Content-Type: application/json" -d '{ "name":"andrew", "age":32 }' "http://localhost:8080/person/save"
    {"value":true}

## find() method via curl
    curl -X GET "http://localhost:8080/person/find/andrew"
    [{"id":"579d60c0b428cd1d3b8832cd","name":"andrew","age":32}]10:40:15 ~/Code/workspace/spring-boot-example $ 


    
