# btp400-library-app

## File structure:
* __application__ directory contains logic for UI rendering and manipulation
* __config__ directory contains logic for database manipulation
* __librarian__ directory contains logic associated with the Librarian user role
* __model__ directory contains entity representations

## Build details:
This project requires the following to work properly:
* _PostgreSQL JDBC Driver_. (https://jdbc.postgresql.org/), how to set it up: (https://www.youtube.com/watch?v=OLmAZmBSwMo&t=327s)
* _JavaFX_ + _FXML_. Videos on how to set it up: (https://www.youtube.com/watch?v=bC4XB6JAaoU , https://www.youtube.com/watch?v=yngO5WwfZCY)
* Add the following VM argument to Main: --module-path "PATH_TO_YOUR/javafx-sdk-15.0.1/lib" --add-modules javafx.controls,javafx.fxml

The startup class with the `main()` method is `application.Main`.

## Authors:
* Nikita Mezhenskyi
* Rad Eshghi
* Alexander Samaniego
