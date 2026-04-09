package models;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

public class ViewTransitionModel implements ViewTransitionModelInterface {

	BorderPane mainView;
	
	public ViewTransitionModel(BorderPane view) {
		// TODO Auto-generated constructor stub
		this.mainView = view;
	}

	@Override
	public void showCookieView() {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class.getResource("../views/cookieView.fxml"));
		
		try {
			BorderPane view = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
