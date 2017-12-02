/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.control
 * @file BlendWidget.java
 * @author zuoguoqing
 * @date 2017年4月6日
 * @version 
 */
package name.zuoguoqing.jfx.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author zuoguoqing
 *
 */
public class BlendWidget extends Application {
	Task<Integer> worker;
	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		//menu
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("File");
		menu.getItems().addAll(new MenuItem("Open"), new MenuItem("Save"), new SeparatorMenuItem(),
				new Menu("Others"));
		menuBar.getMenus().addAll(menu, new Menu("Edit"), new Menu("About"));
		
		//file choser
		Button button = new Button("Open");
		button.setFont(Font.font("Microsoft YaHei", FontWeight.BOLD, 20));
		TextArea textArea = new TextArea();
		textArea.setPromptText("file content will show here");
		textArea.setPrefWidth(300);
		textArea.setPrefHeight(150);
		
		button.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				FileChooser fileChooser = new FileChooser();
				File file = fileChooser.showOpenDialog(primaryStage);
//				textArea.setText("File Name:\t\t\t" + file.getName() + "\n"
//						       + "File Path:\t\t\t\t" + file.getPath() + "\n"
//						       + "Last Modified Time:\t\t" + new Date(file.lastModified()) + "\n"
//						       + "Read\\Write:\t\t\t" + file.canRead() + "\\" + file.canWrite() + "\n"
//						       + "File Size:\t\t\t\t" + file.length()/(1024) + "KB");
				
				BufferedReader reader = null;
				try {
					reader = new BufferedReader(new FileReader(file));
					String string = null;
					int index = 0;
					textArea.setText(null);
					while ((string = reader.readLine()) != null) {
						System.out.println(string);
						textArea.insertText(index, string);
						index += string.length();
					}
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		});
		

		
		
		//check box
		CheckBox checkBox1 = new CheckBox("Check One");
		CheckBox checkBox2 = new CheckBox("Check Two");
		
		//Radio Button
		RadioButton radioButton1 = new RadioButton("Radio One");
		RadioButton radioButton2 = new RadioButton("Radio Two");
		ToggleGroup group = new ToggleGroup();
		radioButton1.setToggleGroup(group);
		radioButton2.setToggleGroup(group);
		
		//toggle Button
		ToggleButton toggleButton1 = new ToggleButton();
		toggleButton1.setText("Toggle One");
		ToggleButton toggleButton2 = new ToggleButton("Toggle Two");
		ToggleGroup group2 = new ToggleGroup();
		toggleButton1.setToggleGroup(group2);
		toggleButton2.setToggleGroup(group2);
		
		//choice box
		ChoiceBox<String> choiceBox = new ChoiceBox<>(
				FXCollections.observableList(
						Arrays.asList("item 1", "item 2", "item3", "item4")));
		
		//Date Picker
		DatePicker datePicker = new DatePicker();
		DatePicker datePicker2 = new DatePicker(LocalDate.now());
		
		//Color Picker
		ColorPicker colorPicker = new ColorPicker();
		ColorPicker colorPicker2 = new ColorPicker(Color.RED);
		
		
		Label workerText = new Label();
		//progress bar
		ProgressBar progressBar = new ProgressBar();
		ProgressBar progressBar2 = new ProgressBar(0.6);
		progressBar2.setPrefWidth(200);
		progressBar2.setPrefHeight(30);
		
		//progress indicator
		ProgressIndicator indicator = new ProgressIndicator();
		ProgressIndicator indicator2 = new ProgressIndicator(0.3);
		indicator2.setPrefSize(100, 100);
		
		Button startButton = new Button("Start");
		Button cancelButton = new Button("Cancel");
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startButton.setDisable(true);
				cancelButton.setDisable(false);
				progressBar2.setProgress(0d);
				indicator2.setProgress(0d);
				worker = createWorker();
				progressBar2.progressProperty().unbind();
				indicator2.progressProperty().unbind();
				progressBar2.progressProperty().bind(worker.progressProperty());
				indicator2.progressProperty().bind(worker.progressProperty());
				worker.messageProperty().addListener(new ChangeListener<String>() {
					public void changed(ObservableValue<? extends String> observable, String oldValue,
		                    String newValue) {
						workerText.setText(newValue);
					}
				});
				new Thread(worker).start();
			}
		});
		
		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				startButton.setDisable(false);
				cancelButton.setDisable(true);
				worker.cancel(true);
				progressBar2.progressProperty().unbind();
				progressBar2.setProgress(0d);
				indicator2.progressProperty().unbind();
				indicator2.setProgress(0d);
			}
		});
		
		HBox topBox = new HBox(10);
		topBox.setAlignment(Pos.CENTER);
		topBox.getChildren().addAll(menuBar, button, textArea);
		
		HBox bottomBox = new HBox(10);
		bottomBox.setAlignment(Pos.CENTER);
		bottomBox.getChildren().addAll(progressBar, progressBar2, startButton, cancelButton);
		
		VBox leftBox = new VBox(10);
		leftBox.setAlignment(Pos.CENTER_LEFT);
		leftBox.getChildren().addAll(checkBox1, checkBox2, 
				toggleButton1, toggleButton2, radioButton1, radioButton2, choiceBox);
		
		VBox rightBox = new VBox(10);
		rightBox.setAlignment(Pos.CENTER_RIGHT);
		rightBox.getChildren().addAll(datePicker, datePicker2,
				colorPicker, colorPicker2);
		
		FlowPane centerPane = new FlowPane(20, 20);
		centerPane.setAlignment(Pos.CENTER);
		centerPane.getChildren().addAll(indicator, indicator2, workerText);
		
		BorderPane root = new BorderPane();
		root.setTop(topBox);
		root.setBottom(bottomBox);
		root.setLeft(leftBox);
		root.setRight(rightBox);
		root.setCenter(centerPane);
		root.setBorder(new Border(new BorderStroke(
				Color.RED, Color.BLUE, Color.GREEN, Color.BROWN, 
				BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, 
				BorderStrokeStyle.DASHED, BorderStrokeStyle.DASHED, 
				new CornerRadii(10), new BorderWidths(5), new Insets(20))));
		
		Scene scene = new Scene(root, 600, 500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Blend Widget Test");
		primaryStage.show();
	}
	
	public Task<Integer> createWorker() {
		return new Task<Integer>() {
			@Override
			protected Integer call() throws InterruptedException {
				for (int i = 0; i < 100; i++) {
					Thread.sleep(100);
					updateMessage("time cost " + 100*(i+1) + "ms");
					updateProgress(i + 1, 100);
				}
				
				return null;
			}
		};
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
