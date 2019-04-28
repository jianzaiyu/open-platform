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
public class Identify implements Serializable {
    private Integer id;
    @NotBlank
    private Integer uid;
    @NotBlank
    private String realName;
    @NotBlank
    private String cardNumber;

    private String cardFront;

    private String cardBack;

    private Byte checkState;

    private String checkNote;

    private String enterpriseName;

    private Date authTime;

    private static final long serialVersionUID = 1L;
}