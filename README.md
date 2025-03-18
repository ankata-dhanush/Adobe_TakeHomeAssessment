"# Adobe_TakeHomeAssessment"

                                            NUMERAL TO ROMAN CONVERTION APP

OVERVIEW

    This repo contains a web application that converts an integer between 1 to 3999 into a Roman number.
    This application consititues of two parts

    1) FrontEnd (React): UI build on React and Adobe React Spectrum library for that interacts with backend API
    2) Backend (Spring Boot): A RESTFul API that accepts an integer and returns its Roman numeral respresentation

    The project includes Observability features (log, metrics, traces) and Docker for deployment.

FEATURES

    - Converts Integer between 1 to 3999 to Roman number
    - UI built using React Spectrum components
    - Dark and light support based on system settings
    - Includes
        - Logs using Logback
        - Metrics using prometheus
        - Trace using OpenTelemety -> Jaeger
    - Running containerization and deployment using Docker

TECH STACK

    - Backend: SpringBoot (Java 17)
    - Frontend: React + TypeScript, ReactSpectrum
    - Observability: OpenTelemetry, Jaeger, Prometheus, Logback
    - Docker: Frontend, Backend, Docker-Compose for handling multi-containers
    - Testing: JUnit, Mockito for Backend. ErrorHandling in Frontend.

SETUP & RUNNING THE APP

    PreRequisites
        -Docker: Ensure Docker is installed and running on your machine (Docker Desktop is up)
        -Node.js & npm: To build and run React frontend
        -Java 17 : To build manually without Docker

    Running the Application using Docker Compose
        1) Clone the Repository
                git clone https://github.com/ankata-dhanush/Adobe_TakeHomeAssessment.git
                cd Adobe_TakeHomeAssessment

        2) Ensure all dependencies are installed
            -Frontend (React)
                cd roman-numeral-frontend-app
                npm install
            -Backend (SpringBoot)
                cd roman-numeral-backend-app
                mvn install
        3) Start the application with Docker Compose in Adobe_TakeHomeAssessment directory
            docker-compose up --build
            (Note: This will start frontend, backend, prometheus, jaeger)
        4) Access the application:
            - Frontend: http://localhost:80 (UI/UX)
            - Backend: http://localhost:8080 (Note: The webpage throws Bad Request as no controller for default localhost:8080, To check  provide the URL http://localhost:8080/romannumeral?query=54 on the webpage it will return the response in JSON form for verification)
            -Prometheus: http://localhost:9090 (monitor metrics)
            -Jaeger UI: http://localhost:16686 (view trace)

API SPECIFICAITON

    - Backend exposes the following endpoint:
        GET / romannumeral?query={integer}
        - Query Parameter: query - A whole number between 1 and 3999
        - Response (Success):
            JSON
                    {
                        "input":"1",
                        "output":"I"
                    }
        - Response (Error):
            If input is not a valid integer or out of range
                400 Bad Request: Input must be between 1 and 3999.
            If any other exception like when we get decimals
                500 Bad Request: Exception occurred while converting number to Roman numeral.

ERROR HANDLING

    Invalid input (negative, decimals, out of range) will return 400 bad request
    server side issue will return 400, 500 Internal Server Error

OBSERVABILITY

    This application implements three pillars of Observability:
    1) Logging:
        - Logs are implemnted using SLF4J with Logback, Log pattern includes Date, trace Id, Span Id, Severity Level, Logger, Msg, on new line
        - Logs are printed on the consol and stored in a log file this file will be available under SpringBoot container files tab --> under log folder.
        - Implemented Rollback Policy when max size of each file to store logs is 1MB, Max Cap to store all files is 10MB then fresh logging starts, only last 30 Log files are kept rest is deleted

    2) Metrics:
        - Prometheus is used to expose metrics, which can be accessed via http://localhost:9090 and backend esposes Prometheus-compatible metrics at /actuator/prometheus
        - Scraping interval every 5sec
        - Metrics on Spring Boot Application

    3) Traces:
        - OpenTelemetery is used for gathering the traces.
        - Jaeger for storing and visualization the traces which is collected from OpenTelemetery

TESTING

    -Backend: REST API validation and Unit Test is performed using JUnit and Mockito
    -Frontend: Validating front end but only giving input as integers rest all won't be accepted. Responsive layout tested on mobile app.

                To run the test manually
                    cd roman-numeral-backend-app
                    mvn test

DOCKER CONFIGURATION

    This project includes docker file for both frontend and backend application and docker-compose.yml file to integrate the entire application
        - Frontend Dockfile: Used to build the React and serve it with Ngnix
        - Backend Dockerfile: Used to Package the Springboot application
        - Docker Compose: Orchestrates the Frontend, Backend, Prometheus and Jaeger services

    docker-compose.yml structure
        frontend:
            -build
        backend:
            -build
        prometheus:  # Metrics
            -image
        jaeger:    # Trace
            -image

NOTES

    - Error Handling: Proper error is handled for both backend and frontend. Invalid inputs are caught and displayed to the users
    - Design: UI app is built using React ensuring consistency with Adobe's design system. It switches between light and dark mode based
              on system settings.

CONCLUSION

    This Assessment implements a robust web service and UI for converting integers to Roman numerals, complete with obsevability, testcases and Docker setup for ease of deployment. The design follows best practices, easy to maintain and open for extension.
