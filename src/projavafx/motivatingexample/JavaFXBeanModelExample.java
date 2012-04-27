package projavafx.motivatingexample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.paint.Color;

public class JavaFXBeanModelExample {

	private IntegerProperty i = new SimpleIntegerProperty(this, "i", 0);
	private StringProperty str = new SimpleStringProperty(this, "str", "Hello");
	private ObjectProperty<Color> color = new SimpleObjectProperty<Color>(this, "color", Color.BLACK);
	
	public final int getI() {
		return i.get();
	}
	/**
	 * @return the i
	 */
	public IntegerProperty iProperty() {
		return i;
	}
	/**
	 * @param i the i to set
	 */
	public final void setI(int i) {
		this.i.set(i);
	}
	/**
	 * @return the str
	 */
	public StringProperty strProperty() {
		return str;
	}
	/**
	 * @param str the str to set
	 */
	public final void setStr(String str) {
		this.str.set(str);
	}
	
	public final String getStr() {
		return str.get();
	}
	
	/**
	 * @return the color
	 */
	public final ObjectProperty<Color> colorProperty() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public final void setColor(Color color) {
		this.color.set(color);
	}
	
	public final Color getColor() {
		return color.get();
	}
	
}
