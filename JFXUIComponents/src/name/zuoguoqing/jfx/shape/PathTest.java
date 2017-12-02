/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.shape
 * @file PathTest.java
 * @author zuoguoqing
 * @date 2017年4月5日
 * @version 
 */
package name.zuoguoqing.jfx.shape;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.QuadCurveTo;
import javafx.stage.Stage;

/**
 * @author zuoguoqing
 *
 */
public class PathTest extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Path path = new Path();
		path.getElements().addAll(new MoveTo(30, 30), new LineTo(10,100), 
				new MoveTo(30, 30), new LineTo(100,100));
		
		Path path2 = new Path();
		MoveTo mTo = new MoveTo();
		mTo.setX(50);
		mTo.setY(150);
		QuadCurveTo qTo = new QuadCurveTo();
		qTo.setControlX(100);
		qTo.setControlY(10);
		qTo.setX(200);
		qTo.setY(200);
		path2.getElements().addAll(mTo, qTo);
		
		Line line1 = new Line(50, 0, 50, 200);
		
		
		Group group = new Group();
		group.getChildren().addAll(path, path2, line1);
		
		Scene scene = new Scene(group, 700, 400);
		scene.setFill(Color.AQUA);
		primaryStage.setScene(scene);
		primaryStage.setTitle("PathTest");
		primaryStage.setOpacity(0.8);
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
