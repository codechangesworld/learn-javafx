package application;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage primaryStage;

	public Main() {

	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	@Override
	public void start(Stage primaryStage) {

		setPrimaryStage(primaryStage);

		initRootlayout();

		handleSceneSize();
	}

	private void initRootlayout() {
		Group group = new Group();
		Rectangle rectangle = new Rectangle(300, 300);
		Rectangle rectangle2 = new Rectangle(100, 100, Color.RED);
		rectangle.setFill(Color.BLUE);
		group.getChildren().addAll(rectangle,rectangle2);

		Scene scene = new Scene(group, 350, 350);

		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void handleSceneSize() {
		Scene scene = primaryStage.getScene();
		Group group = (Group) scene.getRoot();
		Button button = new Button("Resize");
		button.setLayoutX(100);
		button.setLayoutY(100);
		button.setOnAction((ActionEvent event) -> {
			double width = primaryStage.getWidth();
			double height = primaryStage.getHeight();
			primaryStage.setWidth(width < 500 ? width + 100 : width - 100);
			primaryStage.setHeight(height < 500 ? height + 100 : height - 100);
		});

		Button button2 = new Button("Change Color");
		button2.setLayoutX(180);
		button2.setLayoutY(100);
		button2.setOnAction((ActionEvent event) -> {
				scene.setFill(Color.rgb(
						new Random().nextInt(255), 
						new Random().nextInt(255), 
						new Random().nextInt(255)));
		});

		Button button3 = new Button("Change Opacity");
		button3.setLayoutX(150);
		button3.setLayoutY(150);
		button3.setOnAction((ActionEvent event) -> {
			group.setOpacity(new Random().nextDouble());
			primaryStage.opacityProperty().set(new Random().nextDouble());
		});
		
		Button button4 = new Button("Alert");
		button4.setLayoutX(150);
		button4.setLayoutY(200);
		button4.setOnAction((ActionEvent event) -> {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("confirmation");
			alert.setHeaderText("look, a confirmation alert");
			DoubleProperty count = new SimpleDoubleProperty(0);
			Task<Double> task = new Task<Double>() {

				@Override
				protected Double call() throws Exception {
					for (int i = 0; i <= 100; i++) {
						try {
							TimeUnit.MILLISECONDS.sleep(100);
							updateValue(Integer.valueOf(i).doubleValue());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if (i == 100) {
							i = 0;
						}
					}

					return null;
				}

			};
			count.bind(task.valueProperty());
			count.addListener(new ChangeListener<Object>() {

				@Override
				public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
					alert.setContentText("Count:\t" + count.get());
				}

			});
			alert.setContentText("Count:\t" + count.get());
			alert.show();

			new Thread(task).start();

		});

		group.getChildren().addAll(button, button2, button3, button4);
	}

	public static void main(String[] args) {
		launch(args);
	}
}
