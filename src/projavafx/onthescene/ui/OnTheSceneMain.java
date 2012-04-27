package projavafx.onthescene.ui;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.VPos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceBoxBuilder;
import javafx.scene.control.HyperlinkBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.LabelBuilder;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioButtonBuilder;
import javafx.scene.control.Slider;
import javafx.scene.control.SliderBuilder;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.FlowPaneBuilder;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;

public class OnTheSceneMain extends Application {
	DoubleProperty fillVals = new SimpleDoubleProperty(255.0);
	Scene sceneRef;
	ObservableList cursors = FXCollections.observableArrayList(
			Cursor.DEFAULT,
			Cursor.CROSSHAIR,
			Cursor.WAIT,
			Cursor.TEXT,
			Cursor.HAND,
			Cursor.MOVE,
			Cursor.N_RESIZE,
			Cursor.NE_RESIZE,
			Cursor.E_RESIZE,
			Cursor.SE_RESIZE,
			Cursor.S_RESIZE,
			Cursor.SW_RESIZE,
			Cursor.W_RESIZE,
			Cursor.NW_RESIZE,
			Cursor.NONE
			);
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Slider sliderRef;
		ChoiceBox choiceBoxRef;
		Text textSceneX;
		Text textSceneY;
		Text textSceneW;
		Text textSceneH;
		Label labelStageX;
		Label labelStageY;
		Label labelStageW;
		Label labelStageH;
		
		final ToggleGroup toggleGrp = new ToggleGroup();
		
		FlowPane sceneRoot = FlowPaneBuilder.create()
				.layoutX(20)
				.layoutY(40)
				.padding(new Insets(0, 20, 40, 0))
				.orientation(Orientation.VERTICAL)
				.vgap(10)
				.hgap(20)
				.columnHalignment(HPos.LEFT)
				.children(
						HBoxBuilder.create()
							.spacing(10)
							.children(
									sliderRef = SliderBuilder.create()
										.min(0)
										.max(255)
										.value(255)
										.orientation(Orientation.VERTICAL)
										.build(),
									choiceBoxRef = ChoiceBoxBuilder.create()
										.items(cursors)
										.build()
							)
							.build(),
						textSceneX = TextBuilder.create()
							.styleClass("emphasized-text")
							.build(),
						textSceneY = TextBuilder.create()
							.styleClass("emphasized-text")
							.build(),
						textSceneW = TextBuilder.create()
							.styleClass("emphasized-text")
							.build(),
						textSceneH = TextBuilder.create()
							.styleClass("emphasized-text")
							.id("sceneHeightText")
							.build(),
						HyperlinkBuilder.create()
							.text("lookup()")
							.onAction(new EventHandler<ActionEvent>() {
								@Override
								public void handle(ActionEvent arg0) {
									// TODO Auto-generated method stub
									System.out.println("sceneRef:" + sceneRef);
									Text textRef = (Text)sceneRef.lookup("#sceneHeightText");
									System.out.println(textRef.getText());
								}
							})
							.build(),
						RadioButtonBuilder.create()
							.text("onTheScene.css")
							.toggleGroup(toggleGrp)
							.selected(true)
							.build(),
						RadioButtonBuilder.create()
							.text("changeOfScene.css")
							.toggleGroup(toggleGrp)
							.build(),
						labelStageX = LabelBuilder.create()
							.id("stageX")
							.build(),
						labelStageY = LabelBuilder.create()
							.id("stageY")
							.build(),
						labelStageW = new Label(),
						labelStageH = new Label()
				)
				.build();
		
		sceneRef = SceneBuilder.create()
				.width(600)
				.height(250)
				.root(sceneRoot)
				.build();
		
		sceneRef.getStylesheets().addAll(OnTheSceneMain.class.getResource("onTheScene.css").toExternalForm());
		stage.setScene(sceneRef);
		
		choiceBoxRef.getSelectionModel().selectFirst();
		
		textSceneX.textProperty().bind(new SimpleStringProperty("Scene x:").concat(sceneRef.xProperty().asString()));
		textSceneY.textProperty().bind(new SimpleStringProperty("Scene y:").concat(sceneRef.yProperty().asString()));
		textSceneW.textProperty().bind(new SimpleStringProperty("Scene w:").concat(sceneRef.widthProperty().asString()));
		textSceneH.textProperty().bind(new SimpleStringProperty("Scene h:").concat(sceneRef.heightProperty().asString()));
		
		labelStageX.textProperty().bind(new SimpleStringProperty("Stage x:").concat(sceneRef.getWindow().xProperty().asString()));
		labelStageY.textProperty().bind(new SimpleStringProperty("Stage y:").concat(sceneRef.getWindow().yProperty().asString()));
		labelStageH.textProperty().bind(new SimpleStringProperty("Stage H:").concat(sceneRef.getWindow().heightProperty().asString()));
		labelStageW.textProperty().bind(new SimpleStringProperty("Stage W:").concat(sceneRef.getWindow().widthProperty().asString()));
		
		sceneRef.cursorProperty().bind(choiceBoxRef.getSelectionModel().selectedItemProperty());
		fillVals.bind(sliderRef.valueProperty());
		
		fillVals.addListener(new ChangeListener() {
			public void changed(ObservableValue ov, Object oldValue, Object newValue) {
				Double fillValue = fillVals.getValue() / 256.0;
				sceneRef.setFill(new Color(fillValue, fillValue, fillValue, 1.0));
			}
		});
		
		toggleGrp.selectedToggleProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue ov, Object oldValue, Object newValue) {
				String radioButtonText = ((RadioButton)toggleGrp.getSelectedToggle()).getText();
				sceneRef.getStylesheets().addAll(OnTheSceneMain.class.getResource(radioButtonText).toExternalForm());
			}
		});
		
		stage.setTitle("On the Scene");
		stage.show();
		
		Text addTextRef = TextBuilder.create()
				.layoutX(0)
				.layoutY(-30)
				.textOrigin(VPos.TOP)
				.fill(Color.BLUE)
				.font(Font.font("Sans Serif", FontWeight.BOLD, 16))
				.managed(false)
				.build();
		
		addTextRef.textProperty().bind(new SimpleStringProperty("Scene fill;").concat(sceneRef.fillProperty()));
		
		((FlowPane)sceneRef.getRoot()).getChildren().add(addTextRef);
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

}
