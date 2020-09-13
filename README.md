# FOOD TRUCK
## Prerequisites
* Maven version 3.x.x
* Java 8 or above
## How to clean and build
### Go to the root folder of the project where you can see the `pom.xml`
`$ mvn clean install`
## How to run
### Go to the root folder of the project where you can see the `pom.xml`
* Run without api key: <br/>
  `$ mvn exec:java  -Dexec.mainClass=Application`
* Run with api key:  <br/>
  `$ mvn exec:java  -Dexec.mainClass=Application -Dexec.args="ZSjlcEQXWw4A5o7KPrS5kUQyj"` 

## Project design
Following the MVC design pattern, I segregate the project into 3 main layers:<br/>
* Data access layer: where I have my repositories and entity to fetch the data. In this project, the data source is given by Socrata API.
* Service layer: where all the logic is in. Its responsibility is to get the data given by Data access layer, do all the magic and turn the data into specific DTO for the controller layer
* Controller layer: Get DTO service from Service layer, repair the response for the UI
  
UI: In this application, console is acting as the UI, it will send request to the controller with parameters to get the response and show according output to the user.

I also make sure the code is as SOLID as possible in each layer. For example, in the service or repository layers, I separate every specific logic into separate methods. For every service/repository, there is an according interface which can helps to achieve the 'D' principle of the SOLID.
### Folder structure
* `/src/food/truck/api`: acts as data access layer where the source data from API can be fetched
* `/src/food/truck/service`: service layer, all the business logic go inside this folder
* `/src/food/truck/controller`: controller layer
* `/src/food/truck/common`: contains all common used pieces of code: api helper functions, constants, api status enum, etc.
* `/src/food/truck/entity`: Entity model, which is the mapping to the API output json format
* `/src/food/truck/dto`: Data transfer object, which is used to carry the data from service to controller and used as part of the response to the UI
* `/src/food/truck/exception`: All custom exceptions
### Scalability
   * To able to serve a massive number of concurrent users:
        * Pre-load the data source: Fetching data from Socrata, store it in our database. This job can be done by a periodical background job.
        * Cache should be used to reduce the direct 'hitting' to database since the data change is not too frequent. Invalidate the cache properly to make sure the data is not out of date.
        * Add more nodes to share the load: Load balancing. It is not only helpful in term of load distribution but also for high availability
### Extensibility
   * With a separate data access layer, the data source can easily be switched to any other type such as SQL or NoSQL.
   * UI: a web client or mobile application can be easily added without or less modifying the backend side because the controller layer is acting as an endpoint with general response structure.