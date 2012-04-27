package projavafx.motivatingexample;

import javafx.scene.paint.Color;


public class JavaFXBeanControllerExample {

	private JavaFXBeanModelExample model;
	private JavaFXBeanViewExample view;
	
	public JavaFXBeanControllerExample(JavaFXBeanModelExample model, JavaFXBeanViewExample view) {
		this.model = model;
		this.view = view;
	}
	
	public void incrementIProperytOnModel() {
		model.setI(model.getI() + 1);
	}
	
	public void changeStrProperyOnModel() {
		final String str = model.getStr();
		if (str.equals("Hello")) {
			model.setStr("World");
		} else {
			model.setStr("Hello");
		}
	}
	
	public void switchColorPropertyOnModel() {
		final Color color = model.getColor();
		if (color.equals(Color.BLACK)) {
			model.setColor(Color.WHITE);
		} else {
			model.setColor(Color.BLACK);
		}
	}
}
