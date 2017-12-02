/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.cs.view
 * @file CSLayoutController.java
 * @author zuoguoqing
 * @date 2017年4月10日
 * @version 
 */
package name.zuoguoqing.jfx.cs.view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import name.zuoguoqing.jfx.cs.task.ClientTask;
import name.zuoguoqing.jfx.cs.task.ServerTask;
import name.zuoguoqing.jfx.util.Validator;

/**
 * @author zuoguoqing
 *
 */
public class CSLayoutController {
    @FXML
    private TextArea serverTextAreaUp;
    @FXML
    private TextArea serverTextAreaDown;
    @FXML
    private Button serverClearButton;
    @FXML
    private Button serverSendButton;
    @FXML
    private Button serverStartButton;
    @FXML
    private TextField serverIPTextField;
    @FXML
    private TextField serverPortTextField;

    @FXML
    private TextArea clientTextAreaUp;
    @FXML
    private TextArea clientTextAreaDown;
    @FXML
    private Button clientClearButton;
    @FXML
    private Button clientSendButton;
    @FXML
    private Button clientConnectButton;
    @FXML
    private TextField clientIPTextField;
    @FXML
    private TextField clientPortTextField;
    
    private StringProperty serverMsg;
    private StringProperty clientMsg;
    
    private ServerTask serverTask;
    private ClientTask clientTask;
    
    public CSLayoutController() {
        serverMsg = new SimpleStringProperty();
        clientMsg = new SimpleStringProperty();
    }
    
        
    @FXML
    private void handleServerStartButton() {
        serverPortTextField.setText("10000");
        startServer();
    }

    /**
     * 启动服务器线程
     */
    private void startServer() {
        String portString = serverPortTextField.getText();
        if (!Validator.validatePort(portString)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid port! Please input server port range from 1025~65535.");
            alert.showAndWait();

            serverTextAreaUp.appendText("fail to start server cause invalid port number!");
            return;
        }

        int serverPort = Integer.parseInt(portString);
        String ip = "127.0.0.1";
        serverIPTextField.setText(ip);
        
        serverTask = new ServerTask(serverPort);
        serverMsg.bind(serverTask.messageProperty());
        serverTextAreaMsgListener();
        
        Thread thread = new Thread(serverTask);
        thread.setDaemon(true);
        thread.setName("ServerThread");
        thread.start();
    }

    /**
     * 添加新消息监听器，当收到新消息时显示到上边文本框中
     */
    private void serverTextAreaMsgListener() {
        serverMsg.addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                serverTextAreaUp.appendText(newValue);
            }
            
        });
    }

    @FXML
    private void handleServerClearButton() {
        serverTextAreaUp.clear();
    }

    @FXML
    private void handleServerSendButton() {
        serverTextAreaUp.appendText(serverTextAreaDown.getText()+"\n");
        serverTextAreaDown.clear();
    }

    @FXML
    private void handleClientConnectButton() {
        clientConnectServer();
    }

    /**
     * 连接服务器
     */
    private void clientConnectServer() {
        String ip = clientIPTextField.getText();
        String port = clientPortTextField.getText();
        
        if (!Validator.validateIP(ip) || !Validator.validatePort(port)) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Invalid ip or port! Please input right information.");
            alert.showAndWait();
            
            return;
        }
        
        clientTask = new ClientTask(ip, Integer.parseInt(port));
        clientMsg.bind(clientTask.messageProperty());
        clientTextAreaMsgListener();
        
        Thread thread = new Thread(clientTask);
        thread.setDaemon(true);
        thread.setName("ClientThread");
        thread.start();
    }
    
    /**
     * 添加监听器，客户端有新消息时更新显示框消息
     */
    private void clientTextAreaMsgListener() {
        clientMsg.addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                clientTextAreaUp.appendText(newValue);
            }
        });
    }


    @FXML
    private void handleClientClearButton() {
        clientTextAreaUp.clear();
    }

    @FXML
    private void handleClientSendButton() {
        clientTask.setInputMsg(clientTextAreaDown.getText());
        clientTextAreaDown.clear();
    }

}
