/**
 * Copyright (C) zuoguoqing All Right Reserved
 *
 * @description 
 * @package name.zuoguoqing.jfx.util
 * @file Validator.java
 * @author zuoguoqing
 * @date 2017年4月11日
 * @version 
 */
package name.zuoguoqing.jfx.util;

/**
 * @author zuoguoqing
 *
 */
public class Validator {
    /**
     * 验证端口号是否正确
     * @param port
     * @return 正确返回true，错误返回false
     */
    public static boolean validatePort(String port) {
        if (port == null || port.length() == 0
                || Integer.parseInt(port) <= 1024) {
            return false;
        }
        
        return true;
    }
    
    /**
     * 验证IP格式是否正确
     * @param ip
     * @return 正确返回true，错误返回false
     */
    public static boolean validateIP(String ip) {
        if (ip == null || ip.length() == 0) {
            return false;
        }
        
        String patten = "\\d{1,3}.\\d{1,3}.\\d{1,3}.\\d{1,3}";
        if (!ip.matches(patten)) {
            return false;
        }
        
        String[] nums = ip.split("\\.");
        for (int i = 0; i < 4; i++) {
            int num = Integer.parseInt(nums[i]);
            if (num < 0 || num > 255) {
                return false;
            }
        }
        
        return true;
    }
}
