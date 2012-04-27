package projavafx.audioconfig.moel;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;

public class AudioConfigModel {

	public double minDecibels = 0.0;
	public double maxDecibels = 160.0;
	public IntegerProperty selectedDBs = new SimpleIntegerProperty(0);
	public BooleanProperty muting = new SimpleBooleanProperty(false);
	
	public ObservableList genres = FXCollections.observableArrayList(
		"Chamber",
		"Country",
		"Cowbell",
		"Metal",
		"Polka",
		"Rock"
	);
	
	public SingleSelectionModel genreSelectionModel;
	
	public void addListenerToGenreSelectionModel() {
		genreSelectionModel.selectedIndexProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue ov, Object oldValue, Object newValue) {
				int selectIndex = genreSelectionModel.selectedIndexProperty().getValue();
				switch(selectIndex) {
				case 0: selectedDBs.setValue(80);
					break;
				case 1: selectedDBs.setValue(100);
					break;
				case 2: selectedDBs.setValue(150);
					break;
				case 3: selectedDBs.setValue(140);
					break;
				case 4: selectedDBs.setValue(120);
					break;
				case 5: selectedDBs.setValue(130);
				
				}
			}
		});
	}
}
