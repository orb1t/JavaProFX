package projavafx.reversi;

import javafx.animation.FadeTransition;
import javafx.animation.FadeTransitionBuilder;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.effect.Light;
import javafx.scene.effect.LightingBuilder;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.RegionBuilder;
import javafx.util.Duration;

public class ReversiSquare extends Region {
	private static ReversiModel model = ReversiModel.getInstatnce();
	private Region highlight = RegionBuilder.create().opacity(0).style("-fx-border-width: 3; -fx-border-color: dodgerblue").build();
	
	private FadeTransition highlightTransition = FadeTransitionBuilder.create()
			.node(highlight)
			.duration(Duration.millis(200))
			.fromValue(0)
			.toValue(1)
			.build();

	@Override
	protected void layoutChildren() {
		layoutInArea(highlight, 0, 0, getWidth(), getHeight(), getBaselineOffset(), HPos.CENTER, VPos.CENTER);
	}
	
	public ReversiSquare() {
		setStyle("-fx-background-color: burlywood");
		Light.Distant light = new Light.Distant();
		light.setAzimuth(-135);
		light.setElevation(30);
		setEffect(LightingBuilder.create().light(light).build());
		setPrefSize(200, 200);
	}
	
	public ReversiSquare(final int x, final int y) {
		addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				if (model.legalMove(x, y).get()) {
					highlightTransition.setRate(1);
					highlightTransition.play();
				}
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				highlightTransition.setRate(-1);
				highlightTransition.play();
			}
		});
		
		addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent e) {
				model.play(x, y);
				highlightTransition.setRate(1);
				highlightTransition.play();
			}
		});
		
		styleProperty().bind(Bindings.when(model.legalMove(x, y))
				.then("-fx-background-color: derive(dodgerblue, -60%)")
				.otherwise("-fx-background-color: burlywood"));
		getChildren().add(highlight);
		Light.Distant light = new Light.Distant();
		light.setAzimuth(-135);
		light.setElevation(30);
		setEffect(LightingBuilder.create().light(light).build());
		setPrefSize(200, 200);
	}
}
