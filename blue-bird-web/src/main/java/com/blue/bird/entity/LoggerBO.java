package com.blue.bird.entity;

import com.blue.bird.commons.StringUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by jim on 2017/9/28.
 */
@Data(staticConstructor = "newLoggerBO")
@Accessors(chain = true)
@Slf4j
public class LoggerBO implements Serializable{
    private static final long serialVersionUID = 7794553223857172087L;
    //编号
    private String id;
    //客户端请求ip
    private String clientIp;
    //客户端请求路径
    private String uri;
    //终端请求方式,普通请求,ajax请求
    private String type;
    //请求方式method,post,get等
    private String method;
    //请求参数内容,json
    private String paramData;
    //请求接口唯一session标识
    private String sessionId;
    //请求时间
    private Date startTime;
    //接口返回时间
    private Date endTime;
    //接口返回数据json
    private String returnData;
    //请求时httpStatusCode代码，如：200,400,404等
    private String httpStatusCode;
    //请求耗时(毫秒)
    private long costTime;

    public void log() {
        if(StringUtil.isEmpty(this.id)){
          this.id = DateFormatUtils.format(this.startTime,"yyyyMMddHHmmss")+ UUID.randomUUID().toString();
        }
        log.info(toString());
    }
}
