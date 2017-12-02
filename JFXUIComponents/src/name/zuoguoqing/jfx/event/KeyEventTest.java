/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.event
 * @file KeyEventTest.java
 * @author zuoguoqing
 * @date 2017年4月6日
 * @version 
 */
package name.zuoguoqing.jfx.event;

import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * @author zuoguoqing
 *
 */
public class KeyEventTest extends Application {

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		TextField textField = new TextField();
		textField.setPromptText("white here");

		textField.setOnKeyTyped((KeyEvent event) -> {
			System.out.println("key typed:\t" + event.getText() + "\t" + event.getCharacter() + "\t"
					+ event.getSource().getClass().getName() + "\t" + event.getTarget().getClass().getName());
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		textField.setOnKeyPressed((KeyEvent event) -> {
			System.out.println("key pressed:\t" + event.getText() + "\t" + event.getCharacter() + "\t"
					+ event.getSource().getClass().getName() + "\t" + event.getTarget().getClass().getName());
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		textField.setOnKeyReleased((KeyEvent event) -> {
			System.out.println("key released:\t" + event.getText() + "\t" + event.getCharacter() + "\t"
					+ event.getSource().getClass().getName() + "\t" + event.getTarget().getClass().getName());
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		BorderPane root = new BorderPane();
		root.setCenter(textField);

		Scene scene = new Scene(root, 400, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("KeyEventTest");
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
