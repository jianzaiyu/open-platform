package cn.ce.services.auth.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
*@auther GGs
*@date 2019-03-05 11:20:48
*/
@Data
public class ThirdOauth implements Serializable {
    private Integer id;

    private String uid;

    private String oauthId;

    private String oauthType;

    private String keyToken;

    private String refreshToken;

    private Date expireTime;

    private static final long serialVersionUID = 1L;
}