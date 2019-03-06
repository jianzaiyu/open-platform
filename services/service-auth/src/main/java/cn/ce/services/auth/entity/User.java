package cn.ce.services.auth.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
*@auther GGs
*@date 2019-03-05 11:20:48
*/
@Data
public class User implements Serializable {
    private Integer id;

    private String userCode;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    private String userrealname;

    private String telnumber;

    private String email;

    private String orgid;

    private String orgname;

    /**
     * 企业名称
     */
    private String enterprisename;

    /**
     * 用户类型 0:管理员，1:普通用户，2:提供者
     */
    private Byte usertype;

    private String avatar;

    private Byte state;

    private String lastLoginIp;

    private Date lastLoginTime;

    private Date regtime;

    private static final long serialVersionUID = 1L;
}