package com.blue.bird.controller;

import com.blue.bird.commons.StringUtil;
import com.blue.bird.entity.view.UserDTO;
import com.blue.bird.enums.ViewCodeEnum;
import com.blue.bird.exception.ApiException;
import com.blue.bird.service.LoginService;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import static com.blue.bird.enums.ViewCodeEnum.SUCCESS;

/**
 * Created by jim on 2017/9/19.
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private LoginService loginService;

    @GetMapping("/verify/login")
    public ModelAndView login(@Valid UserDTO userDTO,  BindingResult result, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        String key = defaultKaptcha.getConfig().getSessionKey();
        String verifyCodeSession = (String) session.getAttribute(key);
        String verifyCode = userDTO.getVerifyCode();

        // 验证码
        if (!userDTO.verifyCode() || !StringUtil.equals(verifyCode, verifyCodeSession)) {
            throw new ApiException(ViewCodeEnum.VERIFY_CODE_ERROR);
        }
        if(result.hasErrors()){
            FieldError fieldError = result.getFieldError();
            throw new ApiException(fieldError.getDefaultMessage(),ViewCodeEnum.OPT_ERROR);
        }
        // 验证用户名密码
        loginService.login(null);
        session.removeAttribute(key);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(SUCCESS);
        return modelAndView;
    }

    @GetMapping("/test")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(UserDTO.newUserDTO().setUsername("test1234").setPassword("pd5678"));
        return modelAndView;
    }

}
