package cn.ce.services.auth.entity;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
*@auther GGs
*@date 2019-03-05 11:20:48
*/
@Data
public class Identify implements Serializable {
    private Integer id;

    private String cardNumber;

    private String cardFront;

    private String cardBack;

    private Byte checkState;

    private String checkNote;

    private Date authTime;

    private static final long serialVersionUID = 1L;
}