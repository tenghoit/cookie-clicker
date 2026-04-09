import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import models.CookieModel;
import views.MainController;

public class Main extends Application {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("views/cookieView.fxml"));
		
		BorderPane view = loader.load();
		
		MainController cont = loader.getController();
		cont.setModel(new CookieModel());
		
		Scene s = new Scene(view);
		stage.setScene(s);
		stage.show();
	}

	
	public static void main(String[] args) {
		launch(args);
	}
}
