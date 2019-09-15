# Jumbo Store Locator

This project helps locate the closest jumbo stores to a given position. Haversine formula is used to determine the distance of stores relative to the position provided

### Prerequisites

To build the app locally, the only pre-requisite is docker.
You can find instructions on how to install it here https://docs.docker.com/install/

## Getting Started
Once you have the project on your local system 
1. Navigate to the folder where this readme file is, through any command line interface (Powershell, Command prompt...)
2. Run "docker-compose up" without the quotes
3. The command brings up the 3 services required for this project to run within a docker container

The services can be accessed with the details below:
1. React client app: http://localhost:9090/
2. Spring boot backend: http://localhost:8080/
3. MS SQL database

## Running the tests

To run the tests, navigate to store-locator-backend directory, and run "mvn test".
Note: This does not depend on MSSQL service, the test uses H2 database that starts up and is torn down when test finishes


## Deployment

To deploy this app on a live system, you will need to set up a cluster environment, with any cluster management tool. I suggest kubernetes. 
## Built With

* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/) - The back end framework used
* [ReactJS](https://reactjs.org/docs/getting-started.html) - The front end framework used
* [Maven](https://maven.apache.org/) - Dependency Management
* [MSSQL](https://docs.microsoft.com/en-us/sql/) - Database


## Acknowledgments

* Haversine formula was used for distance calculation. https://en.wikipedia.org/wiki/Haversine_formula
