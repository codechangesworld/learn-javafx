/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.component
 * @file ButtonTest.java
 * @author zuoguoqing
 * @date 2017年4月5日
 * @version 
 */
package name.zuoguoqing.jfx.control;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author zuoguoqing
 *
 */
public class ButtonTest extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Button button1 = new Button("Button1");
		button1.setLayoutX(100);
		button1.setLayoutY(100);
		
		Button button2 = new Button("Button2");
		button2.setLayoutX(200);
		button2.setLayoutY(100);
		
		Text text = new Text("Welcome");
		text.setFont(Font.font(20));
		text.setLayoutX(100);
		text.setLayoutY(20);
		
		Pane root = new Pane();
		root.getChildren().addAll(button1, button2, text);
		
		Scene scene = new Scene(root, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Button Test");
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
