/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.cs.model
 * @file ClientInfo.java
 * @author zuoguoqing
 * @date 2017年4月11日
 * @version 
 */
package name.zuoguoqing.jfx.cs.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author zuoguoqing
 *
 */
public class ClientInfo {
    private StringProperty ip;
    private StringProperty port;
    private StringProperty hostname;
    private StringProperty name;
    
    public ClientInfo() {
        this(null, null, null, null);
    }
    
    public ClientInfo(String ip, String port, String hostname, String name) {
        this.ip = new SimpleStringProperty(ip);
        this.port = new SimpleStringProperty(port);
        this.hostname = new SimpleStringProperty(hostname);
        this.name = new SimpleStringProperty(name);
    }
    
    
    public String getIp() {
        return ip.get();
    }
    public void setIp(String ip) {
        this.ip.set(ip);;
    }
    public StringProperty ipProperty(){
        return ip;
    }
    public String getPort() {
        return port.get();
    }
    public void setPort(String port) {
        this.port.set(port);;
    }
    public StringProperty portProperty() {
        return port;
    }
    public String getHostname() {
        return hostname.get();
    }
    public void setHostname(String hostname) {
        this.hostname.set(hostname);;
    }
    public StringProperty hostnameProperty() {
        return hostname;
    }
    public String getName() {
        return name.get();
    }
    public void setName(String name) {
        this.name.set(name);;
    }
    public StringProperty nameProperty() {
        return name;
    }

}
