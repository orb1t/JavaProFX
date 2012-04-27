package projavafx.motivatingexample;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class MotivatingExample {
	private static IntegerProperty intProperty;
	
	private static void createProperty(){
		System.out.println();
		intProperty = new SimpleIntegerProperty(1024);
		System.out.println("intProperty = "+intProperty);
		System.out.println("intProperty.get() = "+intProperty.get());
		System.out.println("intProperty.getValue() = "+intProperty.getValue().intValue());
	}
	
	private static void addAndRemoveInvalidationListener() {
		System.out.println();
		final InvalidationListener invalidationListner = new InvalidationListener() {
			
			@Override
			public void invalidated(Observable observable) {
				// TODO Auto-generated method stub
				System.out.println("The observable has been invalidated: " + observable + ".");
			}
		};
		
		intProperty.addListener(invalidationListner);
		System.out.println("Added invalidation listener.");
		
		System.out.println("Calling intProperty.set(2048)");
		intProperty.set(2048);
		
		System.out.println("Calling intProperty.setValue(3072)");
		intProperty.setValue(Integer.valueOf(3072));
		
		intProperty.removeListener(invalidationListner);
		System.out.println("Removed invalidation listener.");
		
		System.out.println("Calling intProperty.set(4096)");
		intProperty.set(4096);
	}
	
	private static void addAndRemoveChangeListener(){
		System.out.println();
		final ChangeListener changeListener = new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
				System.out.println("The observableValue has changed: oldValue = " + oldValue + ", newValue = "+newValue);
			}
		};
		
		intProperty.addListener(changeListener);
		System.out.println("Added change listener.");
		
		System.out.println("Calling intProperty.set(5120).");
		intProperty.set(5120);
		System.out.println("Calling intProperty.set(1000).");
		intProperty.set(1000);
		intProperty.removeListener(changeListener);
		System.out.println("Removed change listener.");
		
		System.out.println("Calling intProperty.set(6144).");
		intProperty.set(6144);
	}
	
	private static void bindAndUbindOnePropertyToAnother() {
		System.out.println();
		IntegerProperty otherProperty = new SimpleIntegerProperty(0);
		System.out.println("otherProperyt.get() = "+ otherProperty.get());
		
		System.out.println("Binding otherProperty to intProperty.");
		otherProperty.bind(intProperty);
		System.out.println("otherProperyt.get() = "+ otherProperty.get());
		
		System.out.println("Calling intProperty.set(7168)");
		intProperty.set(7168);
		System.out.println("otherProperyt.get() = "+ otherProperty.get());

		System.out.println("Unbinding otherPropery from intProperty.");
		otherProperty.unbind();
		System.out.println("otherProperyt.get() = "+ otherProperty.get());

		System.out.println("Calling intPropery.set(8192).");
		intProperty.set(8192);
		System.out.println("otherProperyt.get() = "+ otherProperty.get());

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		createProperty();
		addAndRemoveInvalidationListener();
		addAndRemoveChangeListener();
		bindAndUbindOnePropertyToAnother();
	}

}
