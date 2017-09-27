package com.blue.bird.entity.view;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

/**
 * Created by jim on 2017/9/27.
 */
@Data(staticConstructor = "newUserDTO")
@Accessors(chain = true)
public class UserDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 验证码
     */
    private String verifyCode;

    public boolean verifyCode() {
        return StringUtils.hasText(verifyCode);
    }
}
