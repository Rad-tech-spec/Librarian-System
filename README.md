# btp400-library-app

## File structure:
* __application__ package contains logic for UI rendering and manipulation
  * __application.admin__ package contains logic associated with the Admin user role
  * __application.librarian__ package contains logic associated with the Librarian user role
  * __application.student__ package contains logic associated with the Student user role
* __dao__ package represents a Data Access Object and contains logic for database manipulation
* __model__ package contains entity representations
* __test__ package contains JUnit 5 tests
* __utilities__ package contains general helper logic

## Build details:
This project requires the following to work properly:
* _PostgreSQL JDBC Driver_. (https://jdbc.postgresql.org/), how to set it up: (https://www.youtube.com/watch?v=OLmAZmBSwMo&t=327s)
* _JavaFX_ + _FXML_. Videos on how to set it up: (https://www.youtube.com/watch?v=bC4XB6JAaoU , https://www.youtube.com/watch?v=yngO5WwfZCY)
* Add the following VM argument to Main: --module-path "PATH_TO_YOUR/javafx-sdk-15.0.1/lib" --add-modules javafx.controls,javafx.fxml

The `main()` method is located in `application.Main`.

## Authors:
* Nikita Mezhenskyi
* Rad Eshghi
* Alexander Samaniego
