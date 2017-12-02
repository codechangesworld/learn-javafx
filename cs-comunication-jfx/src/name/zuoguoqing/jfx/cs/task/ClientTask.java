/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.cs.task
 * @file ClientTask.java
 * @author zuoguoqing
 * @date 2017年4月11日
 * @version 
 */
package name.zuoguoqing.jfx.cs.task;

import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;

/**
 * @author zuoguoqing
 *
 */
public class ClientTask extends Task<Void> {
    Socket socket;
    private String ip;
    private int port;
    private StringProperty inputMsg;
    private boolean send = false;

    /**
     * 
     */
    public ClientTask(String ip, int port) {
        this.ip = ip;
        this.port = port;
        inputMsg = new SimpleStringProperty();
        inputMsgListener();
    }

    /*
     * (non-Javadoc)
     * 
     * @see javafx.concurrent.Task#call()
     */
    @Override
    protected Void call() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        updateMessage(format.format(new Date()) + "\nconnecting to server...\n");
        socket = new Socket(ip, port);
        updateMessage(format.format(new Date()) + "\nsuccessfully connecte to server, try to talk now!\n");

        DataOutputStream outputStream = new DataOutputStream(
                socket.getOutputStream());
        while (socket.isConnected()) {
            if (isCancelled()) {
                break;
            }

            if (send) {
                outputStream.writeUTF(inputMsg.get());
                updateMessage(format.format(new Date()) + "\n" + inputMsg.get() + "\n");
                send = false;
            }
        }

        return null;
    }

    public String getInputMsg() {
        return inputMsg.get();
    }

    public void setInputMsg(String input) {
        inputMsg.set(input);
    }

    public StringProperty inputMsgProperty() {
        return inputMsg;
    }

    private void inputMsgListener() {
        inputMsg.addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                send = true;
            }
        });
    }

}
