# Spring Boot Project README

## Project Overview
The Bus Ticket Booking System streamlines bus ticket booking, PDF ticket generation,
and email verification. Utilizing Lombok, ModelMapper, and JWT token authentication,
it ensures a smooth and secure user experience.

Users register and authenticate securely via JWT tokens. They can easily search for routes,
select seats, specify travel dates, and book tickets. Lombok minimizes boilerplate code,
while ModelMapper simplifies object mapping, enhancing code readability.

Upon booking, the system generates PDF tickets with crucial journey details, including passenger information,
seat numbers, and travel times. These PDF tickets provide tangible records of bookings and ease travel logistics.

To validate user email addresses, the system sends verification emails with unique links upon registration.
Users must verify their emails to activate accounts and access booking features.
Confirmed bookings trigger automatic PDF ticket delivery to users' verified email addresses.

The frontend is responsive and intuitive, catering to diverse devices and screen sizes.
Built on a secure and scalable architecture, the system prioritizes data encryption, validation,
and error handling for user data integrity.

In summary, the Bus Ticket Booking System enhances bus travel booking efficiency and convenience.
With advanced features and a modern tech stack, it offers a robust solution for ticket booking,
PDF ticket generation, and email verification.

## Technologies Used
- **Spring Boot**: Utilizes Spring Boot version 2.7.17 with Java 8.
- **Spring MVC**: Manages web requests and responses.
- **Spring Data JPA**: Facilitates easy implementation of JPA repositories for database interaction.
- **Spring Security**: Ensures application security.
- **Swagger**: Employs Swagger for API documentation and testing.
- **MySQL**: Utilizes MySQL as the database management system.


## Project Structure
- **src/main/java**: Contains the Java source code.
  - **com.TicketBooking**: Main package for the project.
    - **controller**: Contains controllers for handling web requests.
    - **service**: Business logic layer for handling service-related operations.
    - **repository**: Data access layer containing repository interfaces.
    - **entity**: Entity classes representing database tables.
    - **exception**: Handling the exception in the project.
    - **payload**: Configure all the Data transfer Object (DTO) or pojo classes.
    - **util**: EmailService and pdfService configuration.
    - **security**: All the security files included Like Spring Security.
    - **config**: Configuration classes for Spring Boot like SwaggerConfig.
- **src/main/resources**: Contains application properties, templates, and static resources.

## Setup Instructions
1. Clone the repository: https://github.com/Amanv333/ticket-booking.git
2. Navigate to the project directory:
3. Build the project using Maven:
4. Run the Spring Boot application:

## Configuration
- Modify `application.properties` files in the `src/main/resources` directory to configure database connection, server port, etc.
- Customize security configurations in `SecurityConfig.java` if using Spring Security.

## Usage
- Access the application at `http://localhost:8080` (modify port if configured differently).
- First register yourself in the application with the given role . You can use API endpoint.

## Documentation
- The API documentation is provided using Swagger UI.
- To access the Swagger documentation:
  1. Run the Spring Boot application.
  2. Open a web browser and navigate to: `http://localhost:8080/swagger-ui.html`
- Here, you can explore the API endpoints, test them, and view detailed documentation for each endpoint.




