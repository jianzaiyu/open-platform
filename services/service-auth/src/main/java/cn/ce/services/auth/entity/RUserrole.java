package cn.ce.services.auth.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
*@auther GGs
*@date 2019-03-06 22:22:20
*/
@Data
public class RUserrole implements Serializable {
    private Integer urId;
    @NotBlank(message = "用户ID不能为空")
    private Integer uid;
    @NotBlank(message = "角色ID不能为空")
    private Integer roleId;
    @NotBlank(message = "创建者ID不能为空")
    private String createId;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}