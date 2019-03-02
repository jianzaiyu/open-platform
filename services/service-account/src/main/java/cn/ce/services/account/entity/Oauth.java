package cn.ce.services.account.entity;

import java.io.Serializable;
import lombok.Data;

/**
*@auther GGs
*@date 2019-03-01 13:39:28
*/
@Data
public class Oauth implements Serializable {
    private Integer id;

    private String uid;

    private String oauthId;

    private String oauthType;

    private static final long serialVersionUID = 1L;
}