module sample.market {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;
    
    opens sample.market to javafx.fxml;
    exports sample.market;
}
