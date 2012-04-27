package projavafx.zenpong.ui;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBuilder;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.LinearGradientBuilder;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CircleBuilder;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ZenPongMain extends Application {
	
	DoubleProperty centerX = new SimpleDoubleProperty();
	DoubleProperty centerY = new SimpleDoubleProperty();
	
	DoubleProperty leftPaddleY = new SimpleDoubleProperty();
	DoubleProperty rightPaddleY = new SimpleDoubleProperty();

	double leftPaddleDragAnchorY;
	double rightPaddleDragAnchorY;
	
	double initLeftPaddleTranslateY;
	double initRightPaddleTranslateY;

	Circle ball;
	
	Group pongComponents;
	
	Rectangle leftPaddle;
	Rectangle rightPaddle;

	Rectangle topWall;
	Rectangle rightWall;
	Rectangle leftWall;
	Rectangle bottomWall;
	
	Button startButton;
	
	BooleanProperty startVisible = new SimpleBooleanProperty(true);
	
	Timeline pongAnimation = TimelineBuilder.create()
			.keyFrames(
				new KeyFrame(
					new Duration(10.0),
					new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent e) {
							checkForCollision();
							int horzPixels = movingRight? 1: -1;
							int vertPixels = movingDown? 1: -1;
							centerX.setValue(centerX.getValue() + horzPixels);
							centerY.setValue(centerY.getValue() + vertPixels);
						}
					}
				)
			)
			.cycleCount(Timeline.INDEFINITE)
			.build();
	
	boolean movingRight = true;
	boolean movingDown = true;
	
	void initialize() {
		centerX.setValue(250);
		centerY.setValue(250);
		leftPaddleY.setValue(235);
		rightPaddleY.setValue(235);
		startVisible.set(true);
		pongComponents.requestFocus();
	}
			
	
	void checkForCollision() {
		if (ball.intersects(rightWall.getBoundsInLocal()) || ball.intersects(leftWall.getBoundsInLocal())) {
			pongAnimation.stop();
			initialize();
		} else if (ball.intersects(bottomWall.getBoundsInLocal()) || ball.intersects(topWall.getBoundsInLocal())) {
			movingDown = !movingDown;
		} else if (ball.intersects(leftPaddle.getBoundsInParent()) && !movingRight) {
			movingRight = !movingRight;
		} else if (ball.intersects(rightPaddle.getBoundsInParent()) && movingRight) {
			movingRight = !movingRight;
		}
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Scene scene = SceneBuilder.create()
				.width(500)
				.height(500)
				.fill(
						LinearGradientBuilder.create()
							.startX(0.0)
							.startY(0.0)
							.endX(0.0)
							.endY(1.0)
							.stops(
									new Stop(0.0, Color.BLACK),
									new Stop(0.0, Color.GRAY)
							)
							.build()
				)
				.root(
						pongComponents = GroupBuilder.create()
							.focusTraversable(true)
							.children(
									ball = CircleBuilder.create()
										.radius(5.0)
										.fill(Color.WHITE)
										.build(),
									topWall = RectangleBuilder.create()
										.x(0)
										.y(0)
										.width(500)
										.height(1)
										.build(),
									leftWall = RectangleBuilder.create()
										.x(0)
										.y(0)
										.width(1)
										.height(500)
										.build(),
									rightWall = RectangleBuilder.create()
										.x(500)
										.y(0)
										.width(1)
										.height(500)
										.build(),
									bottomWall = RectangleBuilder.create()
										.x(0)
										.y(500)
										.width(500)
										.height(1)
										.build(),
									leftPaddle = RectangleBuilder.create()
										.x(20)
										.width(10)
										.height(30)
										.fill(Color.LIGHTBLUE)
										.cursor(Cursor.HAND)
										.onMousePressed(new EventHandler<MouseEvent>() {
											@Override
											public void handle(MouseEvent me) {
												initLeftPaddleTranslateY = leftPaddle.getTranslateY();
												leftPaddleDragAnchorY = me.getSceneY();
											}
										})
										.onMouseDragged(new EventHandler<MouseEvent>() {
											@Override
											public void handle(MouseEvent me) {
												double dragY = me.getSceneY() - leftPaddleDragAnchorY;
												leftPaddleY.setValue(initLeftPaddleTranslateY + dragY);
											}
										})
										.build(),
									rightPaddle = RectangleBuilder.create()
										.x(470)
										.width(10)
										.height(30)
										.fill(Color.LIGHTBLUE)
										.cursor(Cursor.HAND)
										.onMousePressed(new EventHandler<MouseEvent>() {
											@Override
											public void handle(MouseEvent me) {
												initRightPaddleTranslateY = rightPaddle.getTranslateY();
												rightPaddleDragAnchorY = me.getSceneY();
											}
										})
										.onMouseDragged(new EventHandler<MouseEvent>() {
											@Override
											public void handle(MouseEvent me) {
												double dragY = me.getSceneY() - rightPaddleDragAnchorY;
												rightPaddleY.setValue(initRightPaddleTranslateY + dragY);
											}
										})
										.build(),
									startButton = ButtonBuilder.create()
										.layoutX(255)
										.layoutY(470)
										.text("Start!")
										.onAction(new EventHandler<ActionEvent>() {
											@Override
											public void handle(ActionEvent e) {
												startVisible.set(false);
												pongAnimation.playFromStart();
												pongComponents.requestFocus();
											}
										})
										.build()
							)
							.onKeyPressed(new EventHandler<KeyEvent>() {
								@Override
								public void handle(KeyEvent k) {
									if (k.getCode() == KeyCode.SPACE && pongAnimation.statusProperty().equals(Animation.Status.STOPPED)) {
										rightPaddleY.setValue(rightPaddleY.getValue()-6);
									} else if (k.getCode() == KeyCode.L && !rightPaddle.getBoundsInParent().intersects(topWall.getBoundsInLocal())) {
										rightPaddleY.setValue(rightPaddleY.getValue()-6);
									} else if (k.getCode() == KeyCode.COMMA && !rightPaddle.getBoundsInParent().intersects(bottomWall.getBoundsInLocal())) {
										rightPaddleY.setValue(rightPaddleY.getValue()+6);
									} else if (k.getCode() == KeyCode.A && !leftPaddle.getBoundsInParent().intersects(topWall.getBoundsInLocal())) {
										leftPaddleY.setValue(leftPaddleY.getValue()-6);
									} else if (k.getCode() == KeyCode.Z && !leftPaddle.getBoundsInParent().intersects(bottomWall.getBoundsInLocal())) {
										leftPaddleY.setValue(leftPaddleY.getValue()+6);
									}
								}
							})
							.build()
				)
				.build();
		
		ball.centerXProperty().bind(centerX);
		ball.centerYProperty().bind(centerY);
		leftPaddle.translateYProperty().bind(leftPaddleY);
		rightPaddle.translateYProperty().bind(rightPaddleY);
		startButton.visibleProperty().bind(startVisible);
		
		stage.setScene(scene);
		initialize();
		stage.setTitle("ZenPong Example");
		stage.show();

	}

}
