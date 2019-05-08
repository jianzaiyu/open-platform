package cn.ce.services.account.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author: ggs
 * @date: 2019-04-09 11:25
 **/
@Data
public class UserDetail extends User{
    private static final long serialVersionUID = -1263623739219103105L;
    private String identifyId;

    private String realName;

    private String cardNumber;

    private String cardFront;

    private String cardBack;

    private Byte checkState;

    private String checkNote;

    private String enterpriseName;

    private Date authTime;
}
