package com.blue.bird.entity.view;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by jim on 2017/9/27.
 */
@Data(staticConstructor = "newUserDTO")
@Accessors(chain = true)
public class UserDTO {

    /**
     * 用户名
     */
    @Max(50)
    @Min(6)
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
