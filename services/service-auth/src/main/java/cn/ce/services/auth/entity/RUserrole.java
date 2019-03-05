package cn.ce.services.auth.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
*@auther GGs
*@date 2019-03-05 11:20:48
*/
@Data
public class RUserrole implements Serializable {
    private Integer urId;

    private String uid;

    private Integer roleId;

    private String createId;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}