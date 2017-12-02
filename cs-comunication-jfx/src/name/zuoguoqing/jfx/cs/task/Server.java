/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.cs.task
 * @file Server.java
 * @author zuoguoqing
 * @date 2017年4月11日
 * @version 
 */
package name.zuoguoqing.jfx.cs.task;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import name.zuoguoqing.jfx.cs.model.ClientInfo;
import name.zuoguoqing.jfx.cs.model.ClientMsg;
import name.zuoguoqing.jfx.cs.model.Message;
import name.zuoguoqing.jfx.cs.view.ServerLayoutController;
import name.zuoguoqing.jfx.util.Status;

/**
 * @author zuoguoqing
 *
 */
public class Server extends Task<String> {
    private ServerSocket server;
    private int port;
    private ServerLayoutController controller;
    private static Set<ObjectOutput> writers = new HashSet<>();
    private static ObservableList<ClientInfo> clients = FXCollections.observableArrayList();
    private static Set<Socket> clientSockets = new HashSet<>();
    /**
     * 
     */
    public Server(int serverPort, ServerLayoutController controller) {
        port = serverPort;
        this.controller = controller;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javafx.concurrent.Task#call()
     */
    @Override
    protected String call() throws Exception {
        server = new ServerSocket(port);
        controller.setServerStatus(Status.START);
        Socket clientSocket = null;
        while (!server.isClosed()) {
            clientSocket = server.accept();
            clientSockets.add(clientSocket);
            new Handler(clientSocket).start();
        }

        return null;
    }

    class Handler extends Thread {
        Socket client;

        public Handler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            InputStream is = null;
            OutputStream os = null;
            ObjectInputStream ois = null;
            ObjectOutputStream oos = null;
            Message firstMsg = null;
            try {
                os = client.getOutputStream();
                oos = new ObjectOutputStream(os);
                is = client.getInputStream();
                ois = new ObjectInputStream(is);
                writers.add(oos);
                firstMsg = (Message) ois.readObject();
                addToClientsList(firstMsg);
                Message message = null;
                while (client.isConnected()) {
                    message = (Message) ois.readObject();
                    write(message);
                }
            } catch (ClassNotFoundException e) {
                System.err.println("ClassNotFoundException happened");
            } catch (IOException e) {
                System.err.println("some io exception happened");
            } catch (Exception e) {
                System.err.println("some exception happened");
            } finally {
                removeFromClientList(firstMsg);
            }
        }

        private void removeFromClientList(Message firstMsg) {
            Platform.runLater(() -> {
                ClientMsg info = firstMsg.getClientMsg();

                clients.remove(new ClientInfo(info.getIp(), info.getPort(), info.getHostname(),
                        info.getName()));
            });
        }

        private void addToClientsList(Message message) {
            Platform.runLater(() -> {
                ClientMsg info = message.getClientMsg();

                clients.add(new ClientInfo(info.getIp(), info.getPort(), info.getHostname(),
                        info.getName()));
            });
        }

        private void write(Message message) {
            try {
                for (ObjectOutput oo : writers) {
                    oo.writeObject(message);
                    oo.flush();
                }
            } catch (IOException e) {
                System.out.println("IOException hanppened");
            }
        }
    }

    public ServerSocket getServerSocket() {
        return server;
    }

    public ObservableList<ClientInfo> getClients() {
        return clients;
    }
    
    public void closeClients() {
        
        try {
            for(Socket socket : clientSockets) {
                if (!socket.isClosed()) {
                    socket.close();
                }
            }
        } catch (IOException e) {
            System.err.println("socket closed");
        }
    }

}
