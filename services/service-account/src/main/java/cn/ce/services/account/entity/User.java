package cn.ce.services.account.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
*@auther GGs
*@date 2019-03-01 13:39:28
*/
@Data
public class User implements Serializable {
    private Integer id;

    private String uid;

    private String username;

    private String password;

    private String userrealname;

    private String telnumber;

    private String email;

    private Date regtime;

    private String lastLoginIp;

    private Date lastLoginTime;

    private Byte state;

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

    private static final long serialVersionUID = 1L;
}