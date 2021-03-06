# btp400-library-app

## File structure:
* __application__ directory contains logic for UI rendering and manipulation
* __config__ directory contains logic for database manipulation
* __model__ directory contains entity representations

## Build details:
This project requires the following to work properly:
* _PostgreSQL JDBC Driver_ (https://jdbc.postgresql.org/)
* _JavaFX_ + _FXML_ videos on how to set it up: (https://www.youtube.com/watch?v=bC4XB6JAaoU , https://www.youtube.com/watch?v=yngO5WwfZCY)
* Add the following VM argument to Main: --module-path "C:\src\javafx-sdk-15.0.1\lib" --add-modules javafx.controls,javafx.fxml
