package main;

import java.io.IOException;

import org.eclipse.sisu.launch.Main;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.CookieModel;
import views.MainController;

@ExtendWith(ApplicationExtension.class)
public class testCookie {

	@Start
	private void start(Stage stage) throws IOException
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(MainController.class.getResource("./cookieView.fxml"));
		try {
			BorderPane view = loader.load();
			
			MainController cont = loader.getController();
			cont.setModel(new CookieModel());
			
			Scene s = new Scene(view);
			stage.setScene(s);
			stage.show();
			}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void ClickCookie(FxRobot robot) 
	{
		try {
		robot.clickOn("#cookieButton");
		Thread.sleep(1);}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	private void clickCountIncrease(FxRobot robot, int ByHowMuch) 
		{
			try {
			String id = String.format("#IncreaseCount%d",ByHowMuch);
			robot.clickOn(id);
			Thread.sleep(1);}
			catch(InterruptedException e){
				e.printStackTrace();
			}
	}
	private void checkNumCookie( FxRobot robot, String Bal)
	{
		Label number = robot.lookup("#numOfCookie").queryAs(Label.class);
		Assertions.assertThat(number).hasText(Bal);
	}
	private void checkNumPerClick( FxRobot robot, String Bal)
	{
		ClickCookie(robot);
		Label number = robot.lookup("#cookiePerClick").queryAs(Label.class);
		Assertions.assertThat(number).hasText(Bal);
	}

	private void checkIncrementLabel(FxRobot robot, String Bal)
	{
		Label number = robot.lookup("#cookiePerClick").queryAs(Label.class);
		Assertions.assertThat(number).hasText(Bal);
	}

	private void clickRandomAdd(FxRobot robot)
	{
		try {
			robot.clickOn("#toPercent");
			Thread.sleep(1);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	// Verify the starting state of the model/view: 0 cookies, 1 per click.
		@Test
		public void TestInitialState(FxRobot robot)
		{
			checkNumCookie(robot,"0");
			checkIncrementLabel(robot,"1");
		}
	// Buying the "+1" upgrade costs 50 cookies and increases per-click by 1
	@Test
	public void TestingCookie(FxRobot robot)
	{
		for(int i=0;i<50;i++) {ClickCookie(robot);}
		checkNumCookie(robot,"50");
		checkNumPerClick(robot,"1");
		clickCountIncrease(robot,1);
		checkNumPerClick(robot,"2");

	}


	// Buying the "+3" upgrade costs 150 cookies and increases per-click by 3.
	@Test
	public void TestIncreaseCountBy3(FxRobot robot)
	{
		for(int i=0;i<151;i++) {ClickCookie(robot);}
		checkNumCookie(robot,"151");
		clickCountIncrease(robot,3);
		checkNumCookie(robot,"1");
		checkNumPerClick(robot,"4");
	}

	// Buying the "+5" upgrade costs 250 cookies and increases per-click by 5.
	@Test
	public void TestIncreaseCountBy5(FxRobot robot)
	{
		for(int i=0;i<251;i++) {ClickCookie(robot);}
		checkNumCookie(robot,"251");
		clickCountIncrease(robot,5);
		checkNumCookie(robot,"1");
		checkNumPerClick(robot,"6");
	}

	//  all three upgrades one after another. Tests interaction between upgrades.
	@Test
	public void TestStackUpgrades(FxRobot robot)
	{
		// Buy +1 upgrade. Cost 50
		for(int i=0;i<51;i++) {ClickCookie(robot);}
		checkNumCookie(robot,"51");
		clickCountIncrease(robot,1);      
		checkNumCookie(robot,"1");
		checkIncrementLabel(robot,"2");

		// Earn for +3 upgrade. Need >150. Each click gives 2 now.
		// 1 cookie on hand, 75 clicks gives 150
		for(int i=0;i<75;i++) {ClickCookie(robot);}
		checkNumCookie(robot,"151");
		clickCountIncrease(robot,3);      
		checkNumCookie(robot,"1");
		checkIncrementLabel(robot,"5");

		
		for(int i=0;i<50;i++) {ClickCookie(robot);}
		checkNumCookie(robot,"251");
		clickCountIncrease(robot,5);        // 251 - 250 = 1 cookie, increment = 10
		checkNumCookie(robot,"1");
		checkIncrementLabel(robot,"10");
	}

	// Upgrade buttons have no effect when can't afford
	@Test
	public void TestCannotAffordUpgrade(FxRobot robot)
	{
		// With 0 cookies, none should apply.
		clickCountIncrease(robot,1);
		clickCountIncrease(robot,3);
		clickCountIncrease(robot,5);
		checkNumCookie(robot,"0");
		checkIncrementLabel(robot,"1");

		for(int i=0;i<50;i++) {ClickCookie(robot);}
		checkNumCookie(robot,"50");
		clickCountIncrease(robot,1);
		checkNumCookie(robot,"50");         
		checkIncrementLabel(robot,"1");   
	}

	@Test
	public void TestRandomAddPreservesIncrement(FxRobot robot)
	{
		for(int i=0;i<10;i++) {ClickCookie(robot);}
		checkNumCookie(robot,"10");
		checkIncrementLabel(robot,"1");

		clickRandomAdd(robot);
		checkIncrementLabel(robot,"1");
	}

	@Test
	public void TestRandomAddWithZeroCookies(FxRobot robot)
	{
		checkNumCookie(robot,"0");
		clickRandomAdd(robot);
		checkNumCookie(robot,"0");
		checkIncrementLabel(robot,"1");
	}


}


 