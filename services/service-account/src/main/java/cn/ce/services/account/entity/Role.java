package cn.ce.services.account.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
*@auther GGs
*@date 2019-03-01 13:39:28
*/
@Data
public class Role implements Serializable {
    private Integer roleId;

    private String roleName;

    /**
     * 描述
     */
    private String description;

    private String belongSys;

    private String createUid;

    /**
     * 创建日期
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}