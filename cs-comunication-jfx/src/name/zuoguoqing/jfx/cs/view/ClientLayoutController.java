/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.cs.view
 * @file ClientLayoutController.java
 * @author zuoguoqing
 * @date 2017年4月11日
 * @version 
 */
package name.zuoguoqing.jfx.cs.view;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import name.zuoguoqing.jfx.cs.model.Message;
import name.zuoguoqing.jfx.cs.task.Client;
import name.zuoguoqing.jfx.util.Status;
import name.zuoguoqing.jfx.util.Validator;

/**
 * @author zuoguoqing
 *
 */
public class ClientLayoutController {
    @FXML
    private TextArea textAreaDisplay;
    @FXML
    private TextArea textAreaInput;
    @FXML
    private Button exitButton;
    @FXML
    private Button sendButton;
    @FXML
    private Button connectButton;
    @FXML
    private TextField clientNameTextField;
    @FXML
    private TextField ipTextField;
    @FXML
    private TextField portTextField;
    @FXML
    private Label connectStatus;

    private Client client;
    private Stage stage;

    private StringProperty inputMsg;
    /**
     * 
     */
    public ClientLayoutController() {
        initialize();
    }

    private void initialize() {
        inputMsg = new SimpleStringProperty();
    }

    @FXML
    private void handleConnectButton() {
        if (client != null) {
            return;
        }
        
        String ip = ipTextField.getText();
        String portString = portTextField.getText();
        if (!Validator.validateIP(ip) || !Validator.validatePort(portString)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid ip or port!\nPlease input right information!");
            alert.show();

            return;
        }

        client = new Client(ip, Integer.parseInt(portString), clientNameTextField.getText(), this);
        client.inputMsgProperty().bind(inputMsg);
        
        Thread thread = new Thread(client, "ClientThread");
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private void handleExitButton() {
        client.closeSocket();
        client.cancel();
        stage.close();
    }
    
    public void handleOnClose() {
        handleExitButton();
    }

    @FXML
    private void handleSendButton() {
        String msg = textAreaInput.getText();
        if (msg == null || msg.isEmpty()) {
            return;
        }
        inputMsg.set(msg);
        textAreaInput.clear();
    }

    public void showMessage(Message message) {
        Platform.runLater(() -> {
            textAreaDisplay.appendText(message.getMsg());
        });
    }
    
    @FXML
    private void textAreaInputOnKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleSendButton();
            textAreaInput.clear();
        }
    }
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setStatus(Status status) {
        Platform.runLater(() -> {
            connectStatus.setText(status.name().toLowerCase());
        });
    }
}
