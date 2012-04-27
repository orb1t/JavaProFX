package projavafx.motivatingexample;

public class JavaFXBeanMainExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JavaFXBeanModelExample model = new JavaFXBeanModelExample();
		JavaFXBeanViewExample view = new JavaFXBeanViewExample(model);
		JavaFXBeanControllerExample controller = new JavaFXBeanControllerExample(model, view);
		
		controller.incrementIProperytOnModel();
		controller.changeStrProperyOnModel();
		controller.switchColorPropertyOnModel();
		controller.incrementIProperytOnModel();
		controller.changeStrProperyOnModel();
		controller.switchColorPropertyOnModel();
	}

}
