/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.event
 * @file MouseEventTest.java
 * @author zuoguoqing
 * @date 2017年4月6日
 * @version 
 */
package name.zuoguoqing.jfx.event;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author zuoguoqing
 *
 */
public class MouseEventTest extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Button button = new Button("Button");
		
		button.setOnMouseEntered((MouseEvent event) -> {
			System.out.println("mouse entered:\t" + event.getX() + "," + event.getY());
		});
		button.setOnMouseExited((MouseEvent event) -> {
			System.out.println("mouse exited:\t" + event.getX() + "," + event.getY());
		});
		button.setOnMousePressed((MouseEvent event) -> {
			System.out.println("mouse pressed:\t" + event.getX() + "," + event.getY());
		});
		button.setOnMouseClicked((MouseEvent event) -> {
			System.out.println("mouse clicked:\t" + event.getX() + "," + event.getY());
		});
//		button.setOnMouseMoved((MouseEvent event) -> {
//			System.out.println("mouse moved:\t" + event.getX() + "," + event.getY());
//		});
		button.setOnMouseReleased((MouseEvent event) -> {
			System.out.println("mouse released:\t" + event.getX() + "," + event.getY());
		});
		
		BorderPane root = new BorderPane();
		root.setCenter(button);
		
		Scene scene = new Scene(root, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("MouseEventTest");
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
