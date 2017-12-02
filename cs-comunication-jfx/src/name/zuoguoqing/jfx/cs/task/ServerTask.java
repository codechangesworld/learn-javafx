/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.cs.task
 * @file ServerTask.java
 * @author zuoguoqing
 * @date 2017年4月11日
 * @version 
 */
package name.zuoguoqing.jfx.cs.task;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.concurrent.Task;

/**
 * @author zuoguoqing
 *
 */
public class ServerTask extends Task<Void> {

    private ServerSocket server;
    private Socket client;
    private int port;

    /**
     * 
     */
    public ServerTask(int port) {
        this.port = port;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javafx.concurrent.Task#call()
     */
    @Override
    protected Void call() throws Exception {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        updateMessage(format.format(new Date()) + "\nstarting server...\n");
        server = new ServerSocket(port);
        updateMessage(format.format(new Date())
                + "\nsuccessfully start server at port [" + port + "]\n");
        updateMessage(format.format(new Date())
                + "\nserver is waiting for connecting...\n");

        client = server.accept();
        updateMessage(format.format(new Date()) + "\nconnected with client: "
                + client.getInetAddress().getHostAddress() + ":"
                + client.getPort() + "\n\n");

        DataInputStream inputStream = new DataInputStream(
                client.getInputStream());

        while (client.isConnected()) {
            if (isCancelled()) {
                break;
            }

            String msg = inputStream.readUTF();
            updateMessage(format.format(new Date()) + "\n" + msg + "\n");
        }

        return null;
    }
}
