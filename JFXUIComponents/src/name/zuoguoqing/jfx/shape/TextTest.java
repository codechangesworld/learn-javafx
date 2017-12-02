/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.shape
 * @file TextTest.java
 * @author zuoguoqing
 * @date 2017年4月5日
 * @version 
 */
package name.zuoguoqing.jfx.shape;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Reflection;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author zuoguoqing
 *
 */
public class TextTest extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Text text = new Text(100, 100, "this is a text");
		text.setFill(Color.RED);
		text.setFont(Font.font(java.awt.Font.SERIF, FontWeight.BOLD, 40));
		text.setRotate(30);
		
		Reflection reflection = new Reflection();
		reflection.setFraction(0.7);
		
		text.setEffect(reflection);
		
		Group root = new Group();
		root.getChildren().addAll(text);
		
		Scene scene = new Scene(root, 500, 400);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Text Test");
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
