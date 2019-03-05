package cn.ce.services.auth.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
*@auther GGs
*@date 2019-03-05 11:20:48
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

    private String createId;

    /**
     * 创建日期
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}