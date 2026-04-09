package views;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.StringConverter;
import javafx.util.converter.NumberStringConverter;
import models.CookieModel;

public class MainController {
	
	CookieModel model;
	
    @FXML
    private Label cookieLabel;

    @FXML
    private Label incrementLabel;

    @FXML
    void addCookie(ActionEvent event) {
    	this.model.addCookie();
    	System.out.println("add cookie");
    	System.out.println(this.model.getCookies());
    }

    @FXML
    void increaseIncrement1(ActionEvent event) {
    	this.model.increaseIncrement1();
    	System.out.println("increase increment1");
    }

    @FXML
    void increaseIncrement2(ActionEvent event) {
    	this.model.increaseIncrement2();
    	System.out.println("increase increment2");
    }

    @FXML
    void increaseIncrement3(ActionEvent event) {
    	this.model.increaseIncrement3();
    	System.out.println("increase increment3");
    }

    @FXML
    void randomAdd(ActionEvent event) {
    	this.model.randomAdd();
    	System.out.println("add random");
    }
    
    public void setModel(CookieModel model) {
    	this.model = model;
    	
    	StringConverter<Number> fmt = new NumberStringConverter(); 
    	
    	Bindings.bindBidirectional(cookieLabel.textProperty(), this.model.getCookies(), fmt);
    	Bindings.bindBidirectional(incrementLabel.textProperty(), this.model.getIncrement(), fmt);
    }

}
