#TITLE
PROJECT COM

#INTRODUCTION
PROJECT COM is a springboot web application service for communication system.

#REQUIREMENT
Java 8 installed
Maven installed
Oracle installed
IDEA tool like IntelliJ

#RUN APPLICATION
Using IDEA
    Run the provided script ProjectCOM_db_scripts.sql in Oracle
    Unzip the project folder.
    Open with preferred IDEA tool.
    Import dependencies and compile.
    Run the project using IDEA.
Using command line
    Run in the terminal window using following command.
    mvnw spring-boot:run

#TEST APPLICATION
Using Postman API platform. 
	Import provided collection ProjectCOM.postman_collection.json
	Send the request
Using Swagger UI
	Go to http://localhost:8088/swagger-ui/index.html
	Click Try it Out button
	Modify data
	Execute

#IMPORTANT URL
Swagger UI
	http://localhost:8088/swagger-ui/
	http://localhost:8088/swagger-ui/index.html
API DOCs
	http://localhost:8088/v2/api-docs