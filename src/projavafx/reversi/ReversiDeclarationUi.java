package projavafx.reversi;


import projavafx.reversi.ReversiModel.Owner;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.SceneBuilder;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.InnerShadowBuilder;
import javafx.scene.layout.BorderPaneBuilder;
import javafx.scene.layout.FlowPaneBuilder;
import javafx.scene.layout.Region;
import javafx.scene.layout.RegionBuilder;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.StackPaneBuilder;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.TilePaneBuilder;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.EllipseBuilder;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextBuilder;
import javafx.stage.Stage;

public class ReversiDeclarationUi extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		Scene scene = SceneBuilder.create()
				.width(600)
				.height(400)
				.root(
						BorderPaneBuilder.create()
						.top(createTitle())
						.center(createBackground())
						.bottom(createScoreBoxes())
						.build()
				)
				.build();
		stage.setScene(scene);
		stage.show();
	}
	
	private StackPane createScore(Owner owner) {
		Region background;
		Ellipse piece;
		Text score;
		Text remaining;
		
		ReversiModel model = ReversiModel.getInstatnce();
		StackPane stack = StackPaneBuilder.create()
				.prefHeight(100)
				.children(
						background = RegionBuilder.create()
							.style("-fx-background-color: " + owner.opposite().getColorStyle())
							.build(),
						FlowPaneBuilder.create()
							.hgap(20)
							.vgap(10)
							.alignment(Pos.CENTER)
							.children(
									score = TextBuilder.create()
										.font(Font.font(null, FontWeight.BOLD, 100))
										.fill(owner.getColor())
										.build(),
									VBoxBuilder.create()
										.alignment(Pos.CENTER)
										.spacing(10)
										.children(
												piece = EllipseBuilder.create()
													.effect(DropShadowBuilder.create().color(Color.DODGERBLUE).spread(0.2).build())
													.radiusX(32)
													.radiusY(20)
													.fill(owner.getColor())
													.build(),
												remaining = TextBuilder.create()
													.font(Font.font(null, FontWeight.BOLD, 12))
													.fill(owner.getColor())
													.build()
										)
										.build()
							)
							.build()
				)
				.build();
		
		InnerShadow innerShadow = InnerShadowBuilder.create()
				.color(Color.DODGERBLUE)
				.choke(0.5)
				.build();
		background.effectProperty().bind(Bindings.when(model.turn.isEqualTo(owner))
				.then(innerShadow)
				.otherwise((InnerShadow)null));
		
		DropShadow dropShadow = DropShadowBuilder.create()
				.color(Color.DODGERBLUE)
				.spread(0.2)
				.build();
		piece.effectProperty().bind(Bindings.when(model.turn.isEqualTo(owner))
				.then(dropShadow)
				.otherwise((DropShadow) null));
		score.textProperty().bind(model.getScore(owner).asString());
		remaining.textProperty().bind(model.getTurnsRemaining(owner).asString().concat(" turns remaining"));
		
		return stack;
	}
	
	private Node createScoreBoxes() {
		TilePane tiles = TilePaneBuilder.create()
				.snapToPixel(false)
				.prefColumns(2)
				.children(
						createScore(Owner.BLACK),
						createScore(Owner.WHITE)
				)
				.build();
		tiles.prefTileWidthProperty().bind(Bindings.selectDouble(tiles.parentProperty(), "width").divide(2));
		return tiles;
	}
	
	private Node createTitle() {
		StackPane left = new StackPane();
		left.setStyle("-fx-background-color: black");
		Text text = new Text("JavaFX");
		text.setFont(Font.font(null, FontWeight.BOLD, 18));
		text.setFill(Color.WHITE);
		StackPane.setAlignment(text, Pos.CENTER_RIGHT);
		left.getChildren().add(text);
		Text right = new Text("Reversi");
		right.setFont(Font.font(null, FontWeight.BOLD, 18));
		TilePane tiles = new TilePane();
		tiles.setSnapToPixel(false);
		TilePane.setAlignment(right, Pos.CENTER_LEFT);
		tiles.getChildren().addAll(left, right);
		
		tiles.setPrefHeight(40);
		tiles.prefTileWidthProperty().bind(Bindings.selectDouble(tiles.parentProperty(), "width").divide(2));
		
		return tiles;
	}
	
	private Node createBackground() {
		return RegionBuilder.create()
				.style("-fx-background-color: radial-gradient(radius 100%, white, gray)")
				.build();
	}

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
