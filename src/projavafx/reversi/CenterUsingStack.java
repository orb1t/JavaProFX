package projavafx.reversi;

import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CenterUsingStack extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub

		Text text = new Text("JavaFX Reversi");
		text.setFont(Font.font(null, FontWeight.BOLD, 18));
		text.setFill(Color.YELLOW);
		
		Ellipse ellipse = new Ellipse();
		StackPane stack = new StackPane();
		
		stack.getChildren().addAll(ellipse, text);
		
		Scene scene = new Scene(stack, 400, 100);
		ellipse.radiusXProperty().bind(scene.widthProperty().divide(2));
		ellipse.radiusYProperty().bind(scene.heightProperty().divide(2));
		
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
