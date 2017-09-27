package com.blue.bird.commons;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by jim on 2017/9/28.
 */
public final class HttpUtils {


    private static final String UNKNOWN = "unknown";

    /**
     * 返回客户端ip地址
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtil.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        String[] ips = StringUtil.split(ip, ",");
        return Arrays.stream(ips).filter(p -> !UNKNOWN.equalsIgnoreCase(p)).findFirst().orElse(StringUtil.EMPTY);
    }

    /**
     * 判断是否为ajax请求
     *
     * @param request
     * @return
     */
    public static String getRequestType(HttpServletRequest request) {
        return request.getHeader("X-Requested-With");
    }

    private HttpUtils() {
        //
    }
}
