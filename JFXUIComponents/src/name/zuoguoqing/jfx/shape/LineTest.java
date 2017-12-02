/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.shape
 * @file LineTest.java
 * @author zuoguoqing
 * @date 2017年4月5日
 * @version 
 */
package name.zuoguoqing.jfx.shape;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

/**
 * @author zuoguoqing
 *
 */
public class LineTest extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Line line1 = new Line(50, 50, 200, 200);
		line1.setStrokeWidth(15.0);
		line1.setStroke(Color.BROWN);
		line1.setStrokeLineCap(StrokeLineCap.BUTT);
		line1.setStrokeType(StrokeType.CENTERED);
		
		Line line2 = new Line(200, 50, 50, 200);
		line2.setStroke(Color.BLUEVIOLET);
		line2.setStrokeWidth(10);
		
		Line line3 = new Line(200, 200, 20, 100);
		line3.setStrokeWidth(10);
		line3.getStrokeDashArray().addAll(10d,3d,30d);
		line3.setStrokeDashOffset(5);
		
		Pane root = new Pane();
		root.getChildren().addAll(line1,line2,line3);
		
		Scene scene = new Scene(root, 500, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("LineTest");
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
