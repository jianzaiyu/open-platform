package cn.ce.services.account.entity;

import java.io.Serializable;
import lombok.Data;

/**
*@auther GGs
*@date 2019-03-01 13:39:28
*/
@Data
public class RUserrole implements Serializable {
    private Integer urId;

    private String uid;

    private Integer roleId;

    private static final long serialVersionUID = 1L;
}