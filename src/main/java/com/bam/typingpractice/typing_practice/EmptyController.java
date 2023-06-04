package com.bam.typingpractice.typing_practice;

import javafx.event.ActionEvent;
import java.io.IOException;

public class EmptyController {


    public void submit(ActionEvent ae) throws IOException {
        DBUtils.changeScene(ae,"sample.fxml","Typing Practice",null,null);
    }

}
