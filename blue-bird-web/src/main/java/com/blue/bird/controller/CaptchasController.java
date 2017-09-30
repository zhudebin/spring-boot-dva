package com.blue.bird.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
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
import java.io.IOException;
import java.util.Map;

import static com.blue.bird.commons.Constants.VERIFY_CODE;

/**
 * Created by jim on 2017/9/21.
 */
@Controller
@Slf4j
@RequestMapping("/captchas")
public class CaptchasController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @RequestMapping("/image")
    public void creatCaptchas(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {

        try (ServletOutputStream outputStream = httpServletResponse.getOutputStream()) {
            httpServletResponse.setHeader("Cache-Control", "no-store");
            httpServletResponse.setHeader("Pragma", "no-cache");
            httpServletResponse.setDateHeader("Expires", 0);
            httpServletResponse.setContentType("image/jpeg");

            //生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            Config config = defaultKaptcha.getConfig();
            httpServletRequest.getSession().setAttribute(config.getSessionKey(), createText);

            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", outputStream);

            outputStream.flush();
        } catch (Exception e) {
            log.error("创建验证码失败:", e);
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }


    @RequestMapping("/code")
    @ResponseBody
    public Map captchas() {
        Map<String, String> map = Maps.newHashMap();
        map.put("code", "/captchas/image");
        return map;
    }

    @PostMapping("/verify")
    @ResponseBody
    public ModelAndView verifyCaptchas(HttpServletRequest httpServletRequest) {
        ModelAndView andView = new ModelAndView();
        Config config = defaultKaptcha.getConfig();
        String captchaId = (String) httpServletRequest.getSession().getAttribute(config.getSessionKey());
        String parameter = httpServletRequest.getParameter(VERIFY_CODE);

        if (!captchaId.equals(parameter)) {
            andView.addObject("info", "错误的验证码");
        } else {
            andView.addObject("info", "登录成功");
        }
        return andView;
    }
}
