package projavafx.stagecoach.ui;

import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.control.CheckBox;
import javafx.scene.control.CheckBoxBuilder;
import javafx.scene.control.ChoiceBoxBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFieldBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

public class StageCoachMain extends Application {
	StringProperty title = new SimpleStringProperty();
	
	Text textStageX;
	Text textStageY;
	Text textStageW;
	Text textStageH;
	Text textStageF;
	CheckBox checkBoxResizable;
	CheckBox checkBoxFullScreen;
	
	double dragAnchorX;
	double dragAnchorY;
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		StageStyle stageStyle = StageStyle.DECORATED;
		List<String> unnamedParams = getParameters().getUnnamed();
		
		if (unnamedParams.size() > 0) {
			String stageStyleParam = unnamedParams.get(0);
			if (stageStyleParam.equalsIgnoreCase("transparent")) {
				stageStyle = StageStyle.TRANSPARENT;
			} else if (stageStyleParam.equalsIgnoreCase("undecorated")) {
				stageStyle = StageStyle.UNDECORATED;
			} else if (stageStyleParam.equalsIgnoreCase("utility")) {
				stageStyle = StageStyle.UTILITY;
			}
		} 
		
		final Stage stageRef = stage;
		Group rootGroup;
		TextField titleTextField;
		Scene scene = SceneBuilder.create()
				.width(270)
				.height(370)
				.fill(Color.TRANSPARENT)
				.root(
						rootGroup = GroupBuilder.create()
							.children(
									RectangleBuilder.create()
										.width(250)
										.height(350)
										.arcWidth(50)
										.arcHeight(50)
										.fill(Color.SKYBLUE)
										.build(),
									VBoxBuilder.create()
										.layoutX(30)
										.layoutY(20)
										.spacing(10)
										.children(
												textStageX = TextBuilder.create()
													.textOrigin(VPos.TOP)
													.build(),
												textStageY = TextBuilder.create()
													.textOrigin(VPos.TOP)
													.build(),
												textStageW = TextBuilder.create()
													.textOrigin(VPos.TOP)
													.build(),
												textStageH = TextBuilder.create()
													.textOrigin(VPos.TOP)
													.build(),
												textStageH = TextBuilder.create()
													.textOrigin(VPos.TOP)
													.build(),
												textStageF = TextBuilder.create()
													.textOrigin(VPos.TOP)
													.build(),
												checkBoxResizable = CheckBoxBuilder.create()
													.text("resizable")
													.disable(stageStyle == StageStyle.TRANSPARENT || stageStyle == StageStyle.UNDECORATED)
													.build(),
												checkBoxFullScreen = CheckBoxBuilder.create()
													.text("fullScren")
													.build(),
												HBoxBuilder.create()
													.spacing(10)
													.children(
															new Label("Tilte:"),
															titleTextField = TextFieldBuilder.create()
																.text("Stage Coach")
																.prefColumnCount(15)
																.build()
													)
													.build(),
												ButtonBuilder.create()
													.text("toFont()")
													.onAction(new EventHandler<ActionEvent>() {
														
														@Override
														public void handle(ActionEvent arg0) {
															// TODO Auto-generated method stub
															stageRef.toFront();
														}
													})
													.build(),
												ButtonBuilder.create()
													.text("close()")
													.onAction(new EventHandler<ActionEvent>() {
														@Override
														public void handle(ActionEvent e) {
															stageRef.close();
														}
													})
													.build()
												
										)
										.build()
							)
							.build()
				)
				.build();
		
		rootGroup.setOnMousePressed(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				dragAnchorX = me.getSceneX() - stageRef.getX();
				dragAnchorY = me.getSceneY() - stageRef.getY();
			}
		});
		
		rootGroup.setOnMouseDragged(new EventHandler<MouseEvent>() {
			public void handle(MouseEvent me) {
				stageRef.setX(me.getSceneX() - dragAnchorX);
				stageRef.setY(me.getSceneY() - dragAnchorY);
			}
		});
		
		textStageX.textProperty().bind(new SimpleStringProperty("x: ")
				.concat(stageRef.xProperty().asString()));
		textStageY.textProperty().bind(new SimpleStringProperty("y: ")
				.concat(stageRef.yProperty().asString()));
		textStageW.textProperty().bind(new SimpleStringProperty("width: ")
				.concat(stageRef.widthProperty().asString()));
		textStageH.textProperty().bind(new SimpleStringProperty("height: ")
				.concat(stageRef.heightProperty().asString()));
		textStageF.textProperty().bind(new SimpleStringProperty("focused: ")
				.concat(stageRef.focusedProperty().asString()));
		stage.setResizable(true);
		checkBoxResizable.selectedProperty()
				.bindBidirectional(stage.resizableProperty());
		checkBoxFullScreen.selectedProperty().addListener(new ChangeListener() {
			public void changed(ObservableValue ov, Object oldValue, Object newValue) {
				stageRef.setFullScreen(checkBoxFullScreen.selectedProperty().getValue());
			}
		});
		
		title.bind(titleTextField.textProperty());
		
		stage.setScene(scene);
		stage.titleProperty().bind(title);
		stage.initStyle(stageStyle);
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle (WindowEvent we) {
				System.out.println("Stage is closing");
			}
		});
		stage.show();
		
		Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
		stage.setX((primScreenBounds.getWidth() - stage.getWidth())/2);
		stage.setY((primScreenBounds.getHeight() - stage.getHeight())/2);
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

}
