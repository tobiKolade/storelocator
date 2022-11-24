# Jumbo Store Locator.

This project helps locate the closest jumbo stores to a given position. Haversine formula is used to determine the distance of stores relative to the position provided

### Prerequisites

To build the app locally, You need to have docker and maven installed on your machine.
You can find instructions on how to install it here https://docs.docker.com/install/
You can find instructions on how to install maven here https://maven.apache.org/install.html

## Getting Started
Once you have the project on your local system 
1. Navigate to the folder where this readme file is, through any command line interface (Terminal,Powershell, Command prompt...)
2. Navigate to store-locator-backend folder(run cd store-locator-backend)
3. Run mvn clean package
4. When this is done, navigate back to previous folder(run cd..)
2. Run "docker-compose up" without the quotes 
    Note: There might be a prompt to allow mssql access to the PC, in order to mount the volume for persistent storage. Please grant the           access. The reason for this is so that data don't get lost when the mssql container goes down
3. If the is any failure, run "docker-compose down" then "docker-compose up" again
3. The command brings up the 3 services required for this project to run within a docker container

The services can be accessed with the details below:
1. React client app: http://localhost:9090/
2. Spring boot backend: http://localhost:8080/
3. MS SQL database

## API Documentation 
3 endpoints have been provided. Below is a brief description of the endpoints;
  1. HTTP GET http://localhost:8080/storelocator/api/v1/stores/nearest?longitude={longitude}&latitude={latitude}.
     This endpoint returns the nearest stores to the given position(longitude and latitude)
  2. HTTP GET http://localhost:8080/storelocator/api/v1/stores/nearest/active?longitude={longitude1&latitude={latitude}.
     This endpoint gets the nearest stores, but filter out stores with Open time as 'Gesloten'. I consider these stores to be inactive
  3. HTTP GET http://localhost:8080/storelocator/api/v1/stores/nearest/open?longitude={longitude}&latitude={latitude}
     This endpoint gets nearest open stores. Open stores are stores that are currently open, relative to open and close times
    
  You can import the collection to postman using this link: https://www.getpostman.com/collections/caf13ad62a2209a21724
 

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

## Demo
You can find a quick demo of the project here https://drive.google.com/open?id=1AzfxOcXYjHKtpb34QwW30gzsZmr1ir7m

## Other Notes
- No security was introduced to the app, as I expect it to be open to everyone who wants to locate Jumbo stores.
- I added the features I think all production apps should have for proper functioning and monitoring within a cluster. Features like logging(using the logback-spring.xml file), caching(A production app should use Redis, hazelcast and the likes, I used the default spring boot in memory cache for the purpose of this task). I added a few tests, but I demonstrated the use of a seperate database different from the primary app database(I used H2 database).
- I also containerized the app using docker. A single command can start all services. 

## Acknowledgments

* Haversine formula was used for distance calculation. https://en.wikipedia.org/wiki/Haversine_formula
