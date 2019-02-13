package com.example.springbootshiro.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.web.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * http request response 过滤XSS SQL 数据工具类
 *
 * @author carzy
 */
public class RequestResponseUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestResponseUtil.class);

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 获取request中的body json 数据转化为map
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> getRequestBodyMap(ServletRequest request) {
        Map<String, String> dataMap = new HashMap<>();
        // 判断是否已经将 inputStream 流中的 body 数据读出放入 attribute
        if (request.getAttribute("body") != null) {
            // 已经读出则返回attribute中的body
            return (Map<String, String>) request.getAttribute("body");
        } else {
            try {
                Map<String, String> maps = mapper.readValue(request.getInputStream(), Map.class);
                dataMap.putAll(maps);
                request.setAttribute("body", dataMap);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return dataMap;
        }
    }


    /**
     * 封装response  统一json返回
     */
    public static void responseWrite(String outStr, ServletResponse response) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        PrintWriter printWriter = null;
        try {
            printWriter = WebUtils.toHttp(response).getWriter();
            printWriter.write(outStr);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            if (null != printWriter) {
                printWriter.close();
            }
        }
    }

    /**
     * 取request中的数据封装到map 返回
     */
    public static Map<String, String> getRequestParameters(ServletRequest request) {
        Map<String, String> dataMap = new HashMap<>();
        Enumeration enums = request.getParameterNames();
        while (enums.hasMoreElements()) {
            String paraName = (String) enums.nextElement();
            String paraValue = RequestResponseUtil.getRequest(request).getParameter(paraName);
            if (null != paraValue && !"".equals(paraValue)) {
                dataMap.put(paraName, paraValue);
            }
        }
        return dataMap;
    }

    /**
     * 获取request中的body json 数据转化为map
     */
    private static HttpServletRequest getRequest(ServletRequest request) {
        return (HttpServletRequest) request;
    }

    /**
     * 读取request 已经被防止XSS，SQL注入过滤过的 请求参数key 对应的value
     */
    public static String getParameter(ServletRequest request, String key) {
        return RequestResponseUtil.getRequest(request).getParameter(key);
    }

    /**
     * 获取请求头中具体的key
     *
     * @param request ServletRequest
     * @param key     Key
     * @return value
     */
    public static String getHeader(ServletRequest request, String key) {
        return RequestResponseUtil.getRequest(request).getHeader(key);
    }

    /**
     * 获取请求头中所有的信息
     *
     * @param request ServletRequest
     * @return Map
     */
    public static Map<String, String> getRequestHeaders(ServletRequest request) {
        Map<String, String> headerMap = new HashMap<>();
        Enumeration enums = RequestResponseUtil.getRequest(request).getHeaderNames();
        while (enums.hasMoreElements()) {
            String name = (String) enums.nextElement();
            String value = RequestResponseUtil.getRequest(request).getHeader(name);
            if (null != value && !"".equals(value)) {
                headerMap.put(name, value);
            }
        }
        return headerMap;
    }

}
