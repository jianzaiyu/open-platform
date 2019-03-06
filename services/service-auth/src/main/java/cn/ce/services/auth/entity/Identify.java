package cn.ce.services.auth.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
*@auther GGs
*@date 2019-03-06 22:22:20
*/
@Data
public class Identify implements Serializable {
    private Integer id;

    private Integer uid;

    private String cardNumber;

    private String cardFront;

    private String cardBack;

    private Byte checkState;

    private String checkNote;

    private Date authTime;

    private static final long serialVersionUID = 1L;
}