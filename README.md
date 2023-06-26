# speaking-clock-service
The Speaking Clock Service is a simple application that converts a given time in the format HH:mm into spoken words. It provides a RESTful API endpoint to convert the input time into a textual representation.

# Getting Started
To use the Speaking Clock Service, follow the steps below:

# Prerequisites
1. Java Development Kit (JDK) 17 
2. Apache Maven

# Installation
1. Clone the repository to your local machine:
   git clone https://github.com/your-username/speaking-clock-service.git

2. Navigate to the project directory:
   cd speaking-clock-service

3. Build the project using Maven:
   mvn clean package

# Usage
1. Start the application by running the following command:
   java -jar target/speaking-clock-service.jar

# API Endpoints
# Convert Current Time to Words:
* Endpoint: /speaking-clock/deafult
* Method: GET
* Response:
   * Status Code: 200 (OK)
   * Body:
     * timestamp (string): The current timestamp in the format "yyyy-MM-dd HH:mm:ss".
     * status (integer): The HTTP status code.
     * error (string): The error message (if any).
     * clockMessage (string): The time converted to spoken words.
* Response Json structure:

 {
  "timestamp": "2023-06-26T10:30:00",
  "status": 200,
  "message": "Success",
  "data": "It's eleven thirty in the morning"
  }

# Convert input time to words: 
* Endpoint: /speaking-clock/input/{time}
* Method: GET
* Response:
   * Status Code: 200 (OK)
   * Body:
     * timestamp (string): The current timestamp in the format "yyyy-MM-dd HH:mm:ss".
     * status (integer): The HTTP status code.
     * error (string): The error message (if any).
     * clockMessage (string): The time converted to spoken words.
* Response Json structure:

 {
  "timestamp": "2023-06-26T10:30:00",
  "status": 200,
  "message": "Success",
  "data": "It's eleven thirty in the morning"
  }


# Error Handling
  The Speaking Clock Service handles the following error scenarios:

    * Invalid time format: If the provided time does not match the "HH:mm" format, the API will return a 400 Bad Request response with an error message indicating the expected format.

    * Invalid time range: If the provided time is outside the valid range of hours (0-23) or minutes (0-59), the API will return a 400 Bad Request response with an error message indicating the valid range.

# Custom Error Handling
  The Speaking Clock Service includes a custom error handler to handle any unexpected exceptions. It returns a consistent error response with the following structure:
  
  {
  "timestamp": "2023-06-26 10:15:30",
  "status": <HTTP Status Code>,
  "error": <Error Message>,
  "clockMessage": null
 }

