/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.cs.model
 * @file ClientMsg.java
 * @author zuoguoqing
 * @date 2017年4月12日
 * @version 
 */
package name.zuoguoqing.jfx.cs.model;

import java.io.Serializable;

/**
 * @author zuoguoqing
 *
 */
public class ClientMsg implements Serializable {
    private static final long serialVersionUID = 1L;

    private String ip;
    private String port;
    private String hostname;
    private String name;
    

    /**
     * 
     */
    public ClientMsg() {
        // TODO Auto-generated constructor stub
    }


    public ClientMsg(String ip, String port, String hostname, String name) {
        super();
        this.ip = ip;
        this.port = port;
        this.hostname = hostname;
        this.name = name;
    }


    public String getIp() {
        return ip;
    }


    public void setIp(String ip) {
        this.ip = ip;
    }


    public String getPort() {
        return port;
    }


    public void setPort(String port) {
        this.port = port;
    }


    public String getHostname() {
        return hostname;
    }


    public void setHostname(String hostname) {
        this.hostname = hostname;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

}
