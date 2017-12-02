package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Login extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane grid = new GridPane();
			grid.setAlignment(Pos.CENTER);
			grid.setHgap(10);
			grid.setVgap(10);
			grid.setPadding(new Insets(25, 25, 25, 25));
//			grid.setGridLinesVisible(true);
			
			Text text = new Text("Welcome");
//			text.setFont(Font.font("Tahoma", FontWeight.BLACK, 25));
			text.setId("welcome-text");
			grid.add(text, 0, 0, 2, 1);
			
			Label userNameLabel = new Label("User Name ");
			Label passwordLabel = new Label("Password  ");
			TextField userNameTextField = new TextField();
			PasswordField passwordField = new PasswordField();
			grid.add(userNameLabel, 0, 1);
			grid.add(passwordLabel, 0, 2);
			grid.add(userNameTextField, 1, 1);
			grid.add(passwordField, 1, 2);
			
			HBox hBox = new HBox(10);
			Button button = new Button("Sign In");
			hBox.setAlignment(Pos.BOTTOM_RIGHT);
			hBox.getChildren().add(button);
			grid.add(hBox, 1, 4);
			
			Text msg = new Text();
			grid.add(msg, 1, 6);
			msg.setId("msg");
			button.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					msg.setText("user sign in!");
//					msg.setFill(Color.GREEN);
				}
			});
			
			Scene scene = new Scene(grid,400,300);
			scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
			primaryStage.setTitle("Login");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
