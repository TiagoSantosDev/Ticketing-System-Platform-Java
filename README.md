# Ticketing-System-Platform-Java

This project component consists of a JAVA program capable of receiving data in different formats,
interpret them, convert them if necessary to the data structure used by the company, and
send them to the client, being configurable through a web application (see the repo Ticketing-System-Platform-FrontEnd for more info on the web application component).

Spring.io was also used, namely Spring Boot to speed up the Back-End development process. Postman was used to test the API.

The platform receives JSON Spec files, which are converted using the JOLT library, which are then manipulated by the JAVA platform. The platform can be split into 6 modules:

• OLR configuration application: component that allows configuration of the platform of
integration;
• Jolt Spec Generator (TSWI): Component that generates, according to data received from the
Data, a Spec file to be used by the Jolt library in order to generate the object
intended JSON;
• JSON Input Processor (TSWI): component responsible for analyzing the tickets received by
platform, compare its structure with existing structures in the Database and act
properly;
• JSON Object Converter (TSWI): component responsible for generating the adapted JSON file
to the target Web Service, based on the JSON file.
generated;
• JSON Log Generator (TSWI): component responsible for generating and updating associated logs
to each Ticket, adding relevant information about the processing of that Ticket;
• REST Web Service (TSWI): component responsible for HTTP communications with
different web services;

