package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Controller extends TextField{

    @FXML
    private TextField centerForce;

    @FXML
    private TextField gravity;

    @FXML
    private TextField numberOfCololrs;

    @FXML
    void initialize() {
        assert centerForce != null : "fx:id=\"centerForce\" was not injected: check your FXML file 'sample.fxml'.";
        assert gravity != null : "fx:id=\"gravity\" was not injected: check your FXML file 'sample.fxml'.";
        assert numberOfCololrs != null : "fx:id=\"numberOfCololrs\" was not injected: check your FXML file 'sample.fxml'.";

        gravity.addEventHandler(ActionEvent.ACTION, actionEvent -> System.out.println("print"));
    }
}
