module org.shevliakov.collegeaccounting {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.shevliakov.collegeaccounting to javafx.fxml;
    exports org.shevliakov.collegeaccounting;
}