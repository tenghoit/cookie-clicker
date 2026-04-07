package models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class CookieModel {
	
	DoubleProperty cookies;
	DoubleProperty increment;
	

	public CookieModel() {
		// TODO Auto-generated constructor stub
		this.cookies = new SimpleDoubleProperty();
		this.cookies.set(0);
		
		this.increment = new SimpleDoubleProperty();
		this.increment.set(1);
	}
	
	public void addCookie() {
		cookies.set(cookies.get()+increment.get());
	}
	
	
	public void increaseIncrement1() {
		if(cookies.get() > 50) {
			increment.set(increment.get()+1);
			cookies.set(cookies.get()-50);
		}	
	}
	
	public void increaseIncrement2() {
		if(cookies.get() > 150) {
			increment.set(increment.get()+3);
			cookies.set(cookies.get()-150);
		}	
	}
	
	public void increaseIncrement3() {
		if(cookies.get() > 250) {
			increment.set(increment.get()+5);
			cookies.set(cookies.get()-250);
		}	
	}
	
	public void randomAdd() {
		Double curr = cookies.get() * 0.25;
		cookies.set(cookies.get()-curr);
		
		Double min = curr * 0.75;
		Double max = curr * 1.25;
		Double result = ((Math.random() * (max - min)) + min);
				
		cookies.set(cookies.get()+result);
		
	}

	public DoubleProperty getCookies() {
		return cookies;
	}

	public DoubleProperty getIncrement() {
		return increment;
	}

}
