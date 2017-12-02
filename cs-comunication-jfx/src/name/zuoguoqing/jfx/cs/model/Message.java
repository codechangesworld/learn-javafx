/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.cs.model
 * @file Message.java
 * @author zuoguoqing
 * @date 2017年4月11日
 * @version 
 */
package name.zuoguoqing.jfx.cs.model;

import java.io.Serializable;

/**
 * @author zuoguoqing
 *
 */
public class Message implements Serializable{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String msg;
    private ClientMsg clientMsg;
    
    /**
     * 
     */
    public Message() {
        
    }
    
    public Message(String msg) {
        this.setMsg(msg);
    }
    
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ClientMsg getClientMsg() {
        return clientMsg;
    }

    public void setClientMsg(ClientMsg clientMsg) {
        this.clientMsg = clientMsg;
    }
    
}
