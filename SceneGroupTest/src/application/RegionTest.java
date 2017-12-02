package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class RegionTest extends Application {

	private Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		initRoot();
	}

	@SuppressWarnings(value = { "all" })
	private void initRoot() {
		AnchorPane root = new AnchorPane();
		InputStream is1 = getFile("D:\\resource\\images\\background\\1.jpg");
		InputStream is2 = getFile("D:\\resource\\images\\background\\2.jpg");
		Image image1 = new Image(is1);
		Image image2 = new Image(is2);
		BackgroundImage bImage1 = new BackgroundImage(image1, 
				BackgroundRepeat.SPACE, BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		BackgroundImage bImage2 = new BackgroundImage(image2, 
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		BackgroundFill bFill1 = new BackgroundFill(Color.LIGHTGREEN, new CornerRadii(30) , new Insets(5));
		BackgroundFill bFill2 = new BackgroundFill(Color.LIGHTSEAGREEN, new CornerRadii(30) , new Insets(5));
		
//		root.setBackground(new Background(bFill1));
//		root.getChildren().add(new ImageView(image1));
//		root.getChildren().addAll(
//				new Button("this is a button, press me!"));
		Circle circle = new Circle(100, Color.BLACK);
		root.setShape(circle);
//		root.setOpacity(0.5);
		
		Scene scene = new Scene(root, 800, 600);
		scene.setFill(new LinearGradient(
				scene.getX(), scene.getY(), 
				scene.getWidth(), scene.getHeight(), 
				false, CycleMethod.NO_CYCLE, 
				new Stop[]{
						new Stop(0, Color.LIGHTGREEN),
						new Stop(0.5, Color.RED),
						new Stop(1, Color.LIGHTBLUE)
				}));
		primaryStage.setScene(scene);
		primaryStage.setTitle("RegionTest");
		primaryStage.show();
	}
	
	public InputStream getFile(String filepath) {
		try {
			return new FileInputStream(filepath);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
