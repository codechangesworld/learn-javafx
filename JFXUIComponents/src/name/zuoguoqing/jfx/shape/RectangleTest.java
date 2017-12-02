/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.shape
 * @file RectangleTest.java
 * @author zuoguoqing
 * @date 2017年4月5日
 * @version 
 */
package name.zuoguoqing.jfx.shape;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * @author zuoguoqing
 *
 */
public class RectangleTest extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Rectangle rectangle1 = new Rectangle(50, 50, 200, 150);
		rectangle1.setStroke(Color.RED);
		rectangle1.setStrokeWidth(10);
		rectangle1.setArcHeight(40);
		rectangle1.setArcWidth(50);
		
		Ellipse ellipse = new Ellipse(200, 200, 100, 50);
		ellipse.setStroke(Color.GREEN);
		ellipse.setStrokeWidth(5);
		ellipse.setFill(Color.BLUE);
		ellipse.setOpacity(0.5);
		
		Group group = new Group();
		group.getChildren().addAll(rectangle1,ellipse);
		
		Scene scene = new Scene(group, 500, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("RectangleTest");
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
