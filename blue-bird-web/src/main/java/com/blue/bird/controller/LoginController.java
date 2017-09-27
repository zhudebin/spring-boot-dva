package com.blue.bird.controller;

import com.blue.bird.commons.StringUtil;
import com.blue.bird.entity.view.UserDTO;
import com.blue.bird.enums.ViewCodeEnum;
import com.blue.bird.exception.ApiException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.blue.bird.commons.Constants.VERIFY_CODE;
import static com.blue.bird.enums.ViewCodeEnum.SUCCESS;

/**
 * Created by jim on 2017/9/19.
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @GetMapping("/verify/login")
    public ModelAndView login(UserDTO userDTO, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        String verifyCodeSession = (String) session.getAttribute(VERIFY_CODE);
        String verifyCode = userDTO.getVerifyCode();

        if (!userDTO.verifyCode() || !StringUtil.equals(verifyCode, verifyCodeSession)) {
            throw new ApiException(ViewCodeEnum.VERIFY_CODE_ERROR);
        }
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
