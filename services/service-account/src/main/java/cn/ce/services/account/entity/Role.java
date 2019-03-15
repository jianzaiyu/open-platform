package cn.ce.services.account.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
*@auther GGs
*@date 2019-03-06 22:22:20
*/
@Data
public class Role implements Serializable {
    private Integer roleId;
    @NotBlank(message = "角色名不能为空")
    private String roleName;

    /**
     * 描述
     */
    private String description;

    private String belongSys;
    @NotBlank(message = "创建者ID不能为空")
    private Integer createId;

    /**
     * 创建日期
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}