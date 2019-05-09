package cn.ce.services.auth.entity;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
*@auther GGs
*@date 2019-03-06 22:22:20
*/
@Data
public class User implements Serializable {
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    private String telnumber;
    @Email
    @NotBlank(message = "邮箱不能为空")
    private String email;

    private String orgid;

    private String orgname;
    /**
     * 用户类型 0:管理员，1:普通用户，2:提供者
     */
    private Byte usertype;

    private String avatar;

    private Byte state;

    private String lastloginip;

    private Date lastlogintime;

    private Date regtime;

    private static final long serialVersionUID = 1L;
}