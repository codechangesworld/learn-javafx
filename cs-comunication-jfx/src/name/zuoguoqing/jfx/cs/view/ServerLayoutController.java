/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.cs.view
 * @file ServerLayoutController.java
 * @author zuoguoqing
 * @date 2017年4月11日
 * @version 
 */
package name.zuoguoqing.jfx.cs.view;

import java.io.IOException;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import name.zuoguoqing.jfx.cs.model.ClientInfo;
import name.zuoguoqing.jfx.cs.task.Server;
import name.zuoguoqing.jfx.util.Status;
import name.zuoguoqing.jfx.util.Validator;

/**
 * @author zuoguoqing
 *
 */
public class ServerLayoutController {
    @FXML
    private TextField ipTextField;
    @FXML
    private TextField portTextField;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private TableView<ClientInfo> groupTableView;
    @FXML
    private TableColumn<ClientInfo, String> nameColumn;
    @FXML
    private TableColumn<ClientInfo, String> ipColumn;
    @FXML
    private TableColumn<ClientInfo, String> hostnameColumn;
    @FXML
    private TableColumn<ClientInfo, Integer> portColumn;
    @FXML
    private Label clientCountLabel;
    @FXML
    private Label serverStatusLabel;

    private Server server;

    // private ObservableList<ClientInfo> clientInfos =
    // FXCollections.observableArrayList();
    /**
     * 
     */
    public ServerLayoutController() {
        // TODO Auto-generated constructor stub
    }

    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ipColumn.setCellValueFactory(new PropertyValueFactory<>("ip"));
        hostnameColumn.setCellValueFactory(new PropertyValueFactory<>("hostname"));
        portColumn.setCellValueFactory(new PropertyValueFactory<>("port"));
    }

    @FXML
    private void handleStartButton() {
        if (server != null) {
            return;
        }

        String portString = portTextField.getText();
        if (!Validator.validatePort(portString)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid port!\nPlease input server port range from 1025~65535.");
            alert.show();

            return;
        }

        int serverPort = Integer.parseInt(portString);
        String ip = "127.0.0.1";
        ipTextField.setText(ip);

        server = new Server(serverPort, this);
        Thread thread = new Thread(server);
        thread.setDaemon(true);
        thread.setName("ServerThread");
        thread.start();

        showClients();
    }

    private void showClients() {
        groupTableView.setItems(server.getClients());
        changeCounts();
    }

    private void changeCounts() {
        groupTableView.getItems().addListener(new ListChangeListener<ClientInfo>() {

            @Override
            public void onChanged(ListChangeListener.Change<? extends ClientInfo> c) {
                
                clientCountLabel.setText(Integer.toString(c.getList().size()));
            }

        });
    }

    @FXML
    private void handlStopButton() {
        try {
            server.getServerSocket().close();
            server.closeClients();
            setServerStatus(Status.STOP);
        } catch (IOException e) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText(null);
            alert.setContentText("IOException hanppens when stop server");

            alert.show();
        }
    }

    public void setServerStatus(Status status) {
        Platform.runLater(() -> {
            serverStatusLabel.setText(status.name().toLowerCase());
        });
    }

}
