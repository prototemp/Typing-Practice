module com.bam.typingpractice.typing_practice {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.bam.typingpractice.typing_practice to javafx.fxml;
    exports com.bam.typingpractice.typing_practice;
}