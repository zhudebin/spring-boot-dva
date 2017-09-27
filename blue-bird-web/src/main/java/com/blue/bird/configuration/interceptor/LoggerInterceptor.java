package com.blue.bird.configuration.interceptor;

import com.alibaba.fastjson.JSON;
import com.blue.bird.commons.HttpUtils;
import com.blue.bird.entity.LoggerBO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.serializer.SerializerFeature.WriteMapNullValue;
import static com.blue.bird.commons.Constants.LOGGER_RETVALUE;

/**
 * Created by jim on 2017/9/28.
 */
@Data(staticConstructor = "newLoggerInterceptor")
@Slf4j
public class LoggerInterceptor extends HandlerInterceptorAdapter {

    private static final String PRE_TIME = "PRE_TIME";

    private static final String PRE_LOGGERBO = "PRE_LOGGERBO";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 设置起始时间，记录日志使用
        request.setAttribute(PRE_TIME, System.currentTimeMillis());
        //获取请求sessionId
        String sessionId = request.getSession().getId();
        // 请求路径
        String url = request.getRequestURI();
        // 获取请求参数信息
        Map<String,Object> parameterMap = new HashMap<>();
        request.getParameterMap().forEach((k,v)->{
            parameterMap.put(k,v);
            if(v.length ==1){
                parameterMap.put(k,v[0]);
            }
        });
        String paramData = JSON.toJSONString(parameterMap, WriteMapNullValue);
        // 设置客户端ip
        String clientIp = HttpUtils.getRemoteIp(request);
        // 设置请求类型（json|普通请求）
        String type = HttpUtils.getRequestType(request);
        String remotePort = String.valueOf(request.getRemotePort());
        LoggerBO loggerBO = LoggerBO.newLoggerBO()
                .setStartTime(new Date())
                .setClientIp(clientIp.concat(":").concat(remotePort))
                // 设置请求方法
                .setMethod(request.getMethod())
                .setType(type)
                .setParamData(paramData)
                // 设置请求地址
                .setUri(url)
                .setSessionId(sessionId);
        request.setAttribute(PRE_TIME, System.currentTimeMillis());
        request.setAttribute(PRE_LOGGERBO, loggerBO);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 获取请求错误码
        int status = response.getStatus();
        //当前时间
        long currentTime = System.currentTimeMillis();
        // 请求开始时间
        long startTime = (long) request.getAttribute(PRE_TIME);
        // 获取本次请求日志实体
        LoggerBO loggerBO = (LoggerBO) request.getAttribute(PRE_LOGGERBO);
        // 设置请求时间差
        loggerBO.setCostTime(currentTime - startTime)
                // 设置返回时间
                .setEndTime(new Date())
                // 设置返回错误码
                .setHttpStatusCode(String.valueOf(status))
                // 设置返回值
                .setReturnData(JSON.toJSONString(request.getAttribute(LOGGER_RETVALUE)));
        //执行将日志写入数据库
        String result = new LogResponseWrapper(response).getResult();
        loggerBO.log();
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }


    public class LogResponseWrapper extends HttpServletResponseWrapper {

        private PrintWriter cacheWriter;
        private CharArrayWriter bufferWriter;

        public LogResponseWrapper(HttpServletResponse response) {
            super(response);
            bufferWriter = new CharArrayWriter();
            cacheWriter = new PrintWriter(bufferWriter);
        }

        @Override
        public PrintWriter getWriter() throws IOException {
            return cacheWriter;
        }

        public String getResult() {
            return String.valueOf(bufferWriter);
        }
    }
}
