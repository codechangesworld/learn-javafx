package name.zuoguoqing.jfx.cs;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import name.zuoguoqing.jfx.cs.view.ClientLayoutController;
import name.zuoguoqing.jfx.cs.view.RootLayoutController;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;

        initRootLayout();

    }

    private void initRootLayout() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    MainApp.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout, 800, 500);
            primaryStage.setScene(scene);
            primaryStage.setTitle("ClientServer");
            
            RootLayoutController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void showCSLayout() {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/CSLayout.fxml"));
            AnchorPane csLayout = (AnchorPane) loader.load();

            Stage csStage = new Stage();
            csStage.setScene(new Scene(csLayout));
            csStage.setTitle("Server Client Test");
//            csStage.initModality(Modality.NONE);
//            csStage.initOwner(primaryStage);

            csStage.show();
        } catch (IOException exception) {

        }
    }
    
    public void showClientLayout() {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Clientlayout.fxml"));
            AnchorPane clientLayout = (AnchorPane) loader.load();
            
            Stage clientStage = new Stage();
            clientStage.setScene(new Scene(clientLayout));
            clientStage.setTitle("Client");
            
            ClientLayoutController controller = loader.getController();
            controller.setStage(clientStage);
            
            clientStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                
                @Override
                public void handle(WindowEvent event) {
                    controller.handleOnClose();
                }
            });
            
            clientStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void showServerLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Serverlayout.fxml"));
            AnchorPane serverLayout = (AnchorPane) loader.load();
            
            Stage serverStage = new Stage();
            serverStage.setScene(new Scene(serverLayout));
            serverStage.setTitle("Server");
//            serverStage.initModality(Modality.NONE);
//            serverStage.initOwner(primaryStage);
            
            serverStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void showAbout() {
        BorderPane borderPane = new BorderPane();

        Label contentLabel = new Label();
        contentLabel.setText("This is a network test app.");
        Label copyrightLabel = new Label();
        copyrightLabel.setText("Copyright (c) zuoguoqing. All Right Reserved");

        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        box.setSpacing(20);
        box.getChildren().addAll(contentLabel, copyrightLabel);

        borderPane.setCenter(box);

        Scene scene = new Scene(borderPane);
        Stage aboutStage = new Stage();
        aboutStage.setScene(scene);
        aboutStage.setTitle("About");
        aboutStage.setWidth(300);
        aboutStage.setHeight(200);
        aboutStage.initOwner(primaryStage);
        aboutStage.initModality(Modality.WINDOW_MODAL);
        
        aboutStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
