package com.blue.bird.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by jim on 2017/9/21.
 */
@Controller
@Slf4j
@RequestMapping("/captchas")
public class CaptchasController {

    private static final String VRIFY_CODE = "vrifyCode";
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @RequestMapping("/image")
    public void creatCaptchas(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        try (ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
             ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream()) {
            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            httpServletRequest.getSession().setAttribute(VRIFY_CODE, createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
            //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
            byte[] captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
            httpServletResponse.setHeader("Cache-Control", "no-store");
            httpServletResponse.setHeader("Pragma", "no-cache");
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setContentType("image/jpeg");

            responseOutputStream.write(captchaChallengeAsJpeg);
            responseOutputStream.flush();
        } catch (Exception e) {
            log.error("创建验证码失败:", e);
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    @RequestMapping("/code")
    @ResponseBody
    public Map captchas() {
        Map<String,String> map = Maps.newHashMap();
        map.put("code", "/captchas/image");
        return map;
    }

    @PostMapping("/vrify")
    @ResponseBody
    public ModelAndView vrifyCaptchas(HttpServletRequest httpServletRequest) {
        ModelAndView andView = new ModelAndView();
        String captchaId = (String) httpServletRequest.getSession().getAttribute(VRIFY_CODE);
        String parameter = httpServletRequest.getParameter(VRIFY_CODE);

        if (!captchaId.equals(parameter)) {
            andView.addObject("info", "错误的验证码");
        } else {
            andView.addObject("info", "登录成功");
        }
        return andView;
    }
}
