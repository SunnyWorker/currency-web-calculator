Running currency-web-calculator application locally

Application has several endpoints:

POST /api/v1/transactions - to create a transaction using provided information

POST /api/v1/currencies - to update exchange rates using provided list of changes

GET /api/v1/currencies - to get all existing currencies of your calculator

Postgres Database is located in Docker, so you need to start it using the command from `docker` folder:

docker compose up

After that you can run application locally
Network port of application - 8080
