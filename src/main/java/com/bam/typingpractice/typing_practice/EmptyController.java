package com.bam.typingpractice.typing_practice;

import javafx.event.ActionEvent;

public class EmptyController {


    public void submit(ActionEvent ae) {
        DBUtils.changeScene(ae,"sample.fxml","Typing Practice",null,null);
    }

}
