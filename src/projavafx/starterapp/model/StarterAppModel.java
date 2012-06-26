package projavafx.starterapp.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StarterAppModel {
	public ObservableList getTeamMembers() {
		ObservableList teamMembers = FXCollections.observableArrayList();
		for (int i=1; i<=10000; i++) {
			teamMembers.add(new Person("FirstName" + i, "LastName" + i, "Phone" + i));
		}
		return teamMembers;
	}
	
	public String getRandomWebSite() {
		String[] webSites = {
				"http://javafx.com",
				"http://fxecperience.com",
				"http://www.yahoo.co.jp",
				"http://www.google.com",
				"kakaku.com"
		};
		int randomIdx = (int)(Math.random() * webSites.length);
		return webSites[randomIdx];
	}
	
	public ObservableList listViewItems = FXCollections.observableArrayList();
	public ObservableList choiceBoxItems = FXCollections.observableArrayList(
			"Choice A",
			"Choice B",
			"Choice C",
			"Choice D");
	
	public double maxRpm = 800.0;
	public DoubleProperty rpm = new SimpleDoubleProperty(0);
	
	public double maxKph = 300.0;
	public DoubleProperty kph = new SimpleDoubleProperty(0);
	
}
