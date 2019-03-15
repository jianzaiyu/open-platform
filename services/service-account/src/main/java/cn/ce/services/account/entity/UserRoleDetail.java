package cn.ce.services.account.entity;


import lombok.Data;

import java.io.Serializable;

/**
 * @author: ggs
 * @date: 2019-03-05 13:49
 **/
@Data
public class UserRoleDetail extends RUserrole implements Serializable {

    private String roleName;

    private String description;

    private String belongSys;

    private static final long serialVersionUID = 1L;
}
