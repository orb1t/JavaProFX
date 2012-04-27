package projavafx.helloearthrise.ui;

import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.application.Application;
import javafx.geometry.VPos;
import javafx.scene.GroupBuilder;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageViewBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.RectangleBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelloEarthRiseMain extends Application {
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) {
		String message = "Earthrise at Christmas: aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" ;
		
		Text textRef = TextBuilder.create()
				.layoutY(100)
				.textOrigin(VPos.TOP)
				.textAlignment(TextAlignment.JUSTIFY)
				.wrappingWidth(200)
				.text(message)
				.fill(Color.rgb(187, 195, 107))
				.font(Font.font("SansSerif", FontWeight.BOLD,24))
				.build();
		
		TranslateTransition transTransition = TranslateTransitionBuilder.create()
				.duration(new Duration(750))
				.node(textRef)
				.toY(-820)
				.interpolator(Interpolator.LINEAR)
				.cycleCount(Timeline.INDEFINITE)
				.build();
		
		Scene scene = SceneBuilder.create()
				.width(516)
				.height(387)
				.root(
						GroupBuilder.create()
						.children(ImageViewBuilder.create()
								.image(new Image("http://projavafx.com/images/earthrise.jpg"))
								.build(),
								GroupBuilder.create()
								.layoutX(50)
								.layoutY(180)
								.children(textRef)
								.clip(RectangleBuilder.create()
										.width(430)
										.height(85)
										.build()).build()).build()).build();
		
		stage.setScene(scene);
		stage.setTitle("Hello Earthrise");
		stage.show();
		
		transTransition.play();
	}
}
