/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.component
 * @file LabelTest.java
 * @author zuoguoqing
 * @date 2017年4月4日
 * @version 
 */
package name.zuoguoqing.jfx.control;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * @author zuoguoqing
 *
 */
public class LabelTest extends Application {

	/* (non-Javadoc)
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Label label1 = new Label();
		label1.setText("Search");
		label1.setFont(new Font("Arial Black", 25));
		label1.setTextFill(Color.BLUE);
		
		Label label2 = new Label("Vertical");
		label2.setFont(new Font(20));
		label2.setRotate(60);
		label2.setTranslateY(20);
		
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(20, 20, 20, 20));
		vBox.setAlignment(Pos.CENTER);
		vBox.getChildren().add(label2);
		
		Image image = new Image(getClass().getResourceAsStream("/resource/search.png"));
		Label label3 = new Label("image", new ImageView(image));
		label3.setGraphicTextGap(5);
		label3.setContentDisplay(ContentDisplay.TOP);
		
		HBox hBox = new HBox();
		hBox.setPadding(new Insets(20, 20, 20, 20));
		hBox.setAlignment(Pos.CENTER);
		hBox.getChildren().add(label3);
		
		Label label4 = new Label("this label has a very long text and shoud be wrapped like this");
		label4.setWrapText(true);
		label4.setTextAlignment(TextAlignment.CENTER);
//		label4.setTextOverrun(OverrunStyle.CENTER_ELLIPSIS);
		
		Label label5 = new Label("Scale Label");
		label5.setFont(Font.font(14));
		label5.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				label5.setScaleX(2.0);
				label5.setScaleY(2.0);
			}
			
		});
		label5.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				label5.setScaleX(1.0);
				label5.setScaleY(1.0);
			}
		});
		
		
		BorderPane root = new BorderPane();
		root.setCenter(label5);
		root.setLeft(vBox);
		root.setTop(hBox);
		root.setBottom(label4);
		root.setRight(label1);
		
		Scene scene = new Scene(root, 300, 300);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("LabelTest");
		primaryStage.initStyle(StageStyle.DECORATED);
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
