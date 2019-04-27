package cn.ce.services.account.entity;

import java.io.Serializable;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
*@auther GGs
*@date 2019-04-26 16:11:04
*/
@Data
public class OauthClientDetails implements Serializable {
    @NotBlank
    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String webServerRedirectUri;

    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    private String additionalInformation;

    private String autoapprove;

    private Byte clientType;

    private static final long serialVersionUID = 1L;
}