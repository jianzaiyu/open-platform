package cn.ce.services.auth.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
*@auther GGs
*@date 2019-03-06 22:22:20
*/
@Data
public class ThirdOauth implements Serializable {
    private Integer id;

    private Integer uid;

    private String oauthId;

    private String oauthType;

    private String keyToken;

    private String refreshToken;

    private Date expireTime;

    private static final long serialVersionUID = 1L;
}