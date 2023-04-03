# Loan Calculator Application

This application is using for Loan Calculation.

There are two ways for runing the application:
1. Run the spring boot application directly with Application class
2. Use docker with following instruction

   a) `docker build --tag=loan-calculator:latest .`

   b) `docker run -it -p 8080:8080 loan-calculator:latest`
3. With using Postman, you can use this URL;
   [localhost:8080/loan/calculate]()

4. Here is the request example;

`{
"type":"HOUSING_LOAN",
"term": 10,
"amount": 10000
}`

and expected response;

`{
"monthlyPayment": 98.89
}`