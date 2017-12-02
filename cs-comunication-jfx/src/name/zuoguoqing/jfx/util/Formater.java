/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.util
 * @file Formater.java
 * @author zuoguoqing
 * @date 2017年4月12日
 * @version 
 */
package name.zuoguoqing.jfx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author zuoguoqing
 *
 */
public class Formater {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    /**
     * 
     */
    public Formater() {
        // TODO Auto-generated constructor stub
    }
    
    public static String getFormatTimeNow() {
        return dateFormat.format(new Date());
    }

}
