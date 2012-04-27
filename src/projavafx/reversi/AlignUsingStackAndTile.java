package projavafx.reversi;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AlignUsingStackAndTile extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		StackPane left = new StackPane();
		left.setStyle("-fx-background-color: black");
		Text text = new Text("JavaFX");
		text.setFont(Font.font(null, FontWeight.BOLD, 18));
		text.setFill(Color.WHITE);
		
		StackPane.setAlignment(text, Pos.BASELINE_RIGHT);
		left.getChildren().add(text);
		
		Text right = new Text("Reversi");
		right.setFont(Font.font(null, FontWeight.BOLD, 18));
		
		TilePane tiles = new TilePane();
		TilePane.setAlignment(right, Pos.BASELINE_LEFT);

		tiles.getChildren().addAll(left, right);
		Scene scene = new Scene(tiles, 400, 100);
		left.prefWidthProperty().bind(scene.widthProperty().divide(2));
		left.prefHeightProperty().bind(scene.heightProperty());
		
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
