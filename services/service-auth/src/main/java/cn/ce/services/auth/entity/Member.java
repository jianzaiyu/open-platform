package cn.ce.services.auth.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

/**
 * @author: ggs
 * @date: 2019-05-09 15:34
 **/
@Data
@Document(collection = "MEM_LOGIN_USER")
public class Member {
    /**
     * id
     */
    @Field(value = "id")
    @Id
    private String id;
    /**
     * 主副账号关联字段
     */
    @Field(value = "uniKey")
    private String uniKey;
    /**
     * 账号名
     */
    @Field(value = "memberCode")
    private String memberCode;
    /**
     * 姓名
     */
    @Field(value = "memName")
    private String memName;// 公司名称 二级账号显示名称

    @Field(value = "linkName")
    private String linkName;// 联系人
    /**n
     * 账号状态  0 有效 (冻结) 1 锁定(开通)
     */
    @Field(value = "state")
    private Integer state;
    /**
     * 会员邮箱
     */
    @Field(value = "custMail")
    private String custMail;
    /**
     * 会员密码
     */
    @Field(value = "custPassword")
    private String custPassword;

    /** 手机号 */
    @Field(value = "custTel")
    private String custTel;
    /**
     * 0一级账户 1 二级账户
     */
    @Field(value = "userType")
    private Integer userType;
    /**
     * 二级账号
     */
    @Field(value = "alias")
    private String alias;

    @Field(value = "comment")
    private String comment;
    /**创建日期 */
    @Field(value = "createTime")
    private Date createTime;
}
