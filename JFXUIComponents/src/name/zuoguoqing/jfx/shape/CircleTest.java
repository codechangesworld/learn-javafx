/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.shape
 * @file CircleTest.java
 * @author zuoguoqing
 * @date 2017年4月5日
 * @version 
 */
package name.zuoguoqing.jfx.shape;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * @author zuoguoqing
 *
 */
public class CircleTest extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
//		Shadow shadow = new Shadow();
		DropShadow dropShadow = new DropShadow();
		dropShadow.setOffsetX(10);
		dropShadow.setOffsetY(10);
		dropShadow.setColor(Color.RED);
//		dropShadow.setRadius(20);

		Circle circle = new Circle(200, 200, 50);
		circle.setFill(Color.GREEN);
		circle.setEffect(dropShadow);
		circle.setCache(true);
		
		Arc arc = new Arc();
		arc.setCenterX(100);
		arc.setCenterY(100);
		arc.setRadiusX(50);
		arc.setRadiusY(50);
		arc.setStartAngle(30);
		arc.setLength(270);
		arc.setType(ArcType.ROUND);
		arc.setStroke(Color.RED);
		arc.setStrokeWidth(5);
		arc.setEffect(dropShadow);
		
		Group root = new Group();
		root.getChildren().addAll(circle, arc);
		
		Scene scene = new Scene(root, 500, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("CircleTest");
//		primaryStage.setOpacity(0.8);
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
