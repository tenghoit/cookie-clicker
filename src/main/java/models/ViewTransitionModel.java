package models;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.SplitPane;

public class ViewTransitionModel implements ViewTransitionModelInterface {

	SplitPane mainView;
	
	public ViewTransitionModel(SplitPane view) {
		// TODO Auto-generated constructor stub
		this.mainView = view;
	}

	@Override
	public void showCookieView() {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class.getResource("../views/cookieView.fxml"));
		
		SplitPane view = loader.load();
	}

}
