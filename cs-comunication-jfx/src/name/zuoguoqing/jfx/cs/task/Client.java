/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.cs.task
 * @file Client.java
 * @author zuoguoqing
 * @date 2017年4月11日
 * @version 
 */
package name.zuoguoqing.jfx.cs.task;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Task;
import name.zuoguoqing.jfx.cs.model.ClientMsg;
import name.zuoguoqing.jfx.cs.model.Message;
import name.zuoguoqing.jfx.cs.view.ClientLayoutController;
import name.zuoguoqing.jfx.util.Formater;
import name.zuoguoqing.jfx.util.Status;

/**
 * @author zuoguoqing
 *
 */
public class Client extends Task<Void> {
    private ClientLayoutController controller;
    private String localHostname;
    private String localIp;
    private String serverIp;
    private int port;
    private String clientName;
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private OutputStream os;
    private InputStream is;
    private StringProperty inputMsg;

    /**
     * @param clientName
     * 
     */
    public Client(String ip, int port, String clientName, ClientLayoutController controller) {
        this.controller = controller;
        this.port = port;
        this.serverIp = ip;
        this.clientName = clientName;
        try {
            localHostname = InetAddress.getLocalHost().getHostName();
            localIp = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            localHostname = null;
            localIp = null;
        }
        inputMsg = new SimpleStringProperty();
        inputMsgLinstener();
    }

    private void inputMsgLinstener() {
        inputMsg.addListener((observe, oldValue, newValue) -> {
            send(newValue);
        });
    }

    public StringProperty inputMsgProperty() {
        return inputMsg;
    }

    @Override
    protected Void call() throws Exception {
        try {
            socket = new Socket(serverIp, port);
            controller.setStatus(Status.START);

            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);
        } catch (UnknownHostException e) {
            controller.showMessage(new Message(Formater.getFormatTimeNow() + "\n" + clientName
                    + ":\tUnknow Host Exception\n"));
        } catch (IOException e) {
            controller.showMessage(new Message(
                    Formater.getFormatTimeNow() + "\n" + clientName + ":\tIO Exception\n"));
        }

        try {
            sendInfoToServer();
            while (socket.isConnected()) {
                Message message = (Message) ois.readObject();
                if (message != null) {
                    controller.showMessage(message);
                }
            }
        } catch (ClassNotFoundException e) {
            controller.showMessage(new Message(Formater.getFormatTimeNow() + "\n" + clientName
                    + ":\tClass Not Found Exception\n"));
        } catch (IOException e) {
            controller.showMessage(new Message(
                    Formater.getFormatTimeNow() + "\n" + clientName + ":\tIO Exception\n"));
        } catch (Exception e) {
            controller.showMessage(new Message(
                    Formater.getFormatTimeNow() + "\n" + clientName + ":\tException\n"));
        }

        return null;
    }

    private void sendInfoToServer() {
        ClientMsg msg = new ClientMsg();
        msg.setIp(localIp);
        msg.setPort(Integer.toString(port));
        msg.setName(clientName);
        msg.setHostname(localHostname);
        
        Message message = new Message();
        message.setClientMsg(msg);
        message.setMsg("Client info\n");
        send(message);
    }

    public void send(String msg) {
        try {
            oos.writeObject(new Message(
                    Formater.getFormatTimeNow() + "\n" + clientName + ":\t" + msg + "\n"));
            oos.flush();
        } catch (IOException e) {
            System.err.println("IO Exception hanppened");
        }
    }
    
    public void send(Message msg) {
        try {
            oos.writeObject(msg);
            oos.flush();
        } catch (IOException e) {
            System.err.println("IO Exception hanppened");
        }
    }
    
    public void closeSocket() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("client:\tsocket closed");
        }
    }

}
