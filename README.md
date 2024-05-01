# eTraveli-assignment
 eTraveli-assignment
 
This assignment is built as a maven Spring boot project. It has been created using Java 17, so you can compile it by using 
mvn clean package 
in the root folder and in the prduced target folder, using Java 17, you can run the application as:

java -jar eTraveli-0.0.1-SNAPSHOT.jar

The application will start on localhost:8080 and will boot up an in memory H2 DB, which if it runs for the first time, will be initiated with the clearing cost matrix starting data.

The application comes with the "test.mv.db" file that has already pre-loaded some data for clearing cost and also some data for the entity CardInfo, which is a combination of Country and card number. 

The application can be accessed through postman, using the following request formats:

Payment-cards-cost:

curl --location 'http://localhost:8080/api/payment-cards-cost' \
--header 'Content-Type: application/json' \
--data '{
"card_number": "43058949"
}'
![image](https://github.com/dglyk/eTraveli/assets/11438108/de1beb36-072c-4fd3-9bb5-7eb66bb2c6e0)

Crud operations for clearing-cost matrix:
Fetch all clearing costs:

curl --location 'http://localhost:8080/api/clearing_costs'
![image](https://github.com/dglyk/eTraveli/assets/11438108/a89ad513-fcff-45c9-aede-04e48406c43b)

Update clearing cost:
curl --location --request PUT 'http://localhost:8080/api/clearing_costs/7' \
--header 'Content-Type: application/json' \
--data '{
        "issuingCountry": "US",
        "cost": 15.0,
        "currency": "USD"
    }'
![image](https://github.com/dglyk/eTraveli/assets/11438108/d323e140-88d3-458f-89a4-af0ebec7fb5a)

Delete a clearing cost:
curl --location --request DELETE 'http://localhost:8080/api/clearing_costs/' \
--header 'Content-Type: application/json' \
--data '{
    "id": 7,
    "issuingCountry": "SE",
    "cost": 15.0,
    "currency": "USD"
}'
![image](https://github.com/dglyk/eTraveli/assets/11438108/e2359b0f-c2c4-402d-b1ee-d871cf96beb6)

The logic I have used, is that when a new card request is incoming on my API, first I am trying to find the card number in the cache.
If it does not exist in cache, I try to find it in the DB and store it in the cache in the same fetch operation.
If it does not exist neither in cache or in the DB, I then make the call to the external application.

Before implementing the solution with caching, I have been using RequestInterceptor in order to respect the SLA. I was only allowing 5 requests per hour, by adding the requests in a queue and counting the time passed from the first one. 

After implementing caching, I have disabled that Interceptor, as with the new implementation, the call will not reach the external application if it is served by a value existing either in DB or cache. 
In the case that the application actually calls to https://binlist.net/ and surpasses the restriction of the external api, I am handling the exception in RestResponseEntityExceptionHandler and delivering the exception to the client.

For any clarifications or further explanation needed, please do not hesitate to contact me on dglikiotis@gmail.com

    

