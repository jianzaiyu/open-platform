package cn.ce.service.openapi.base.admin.entity;//package cn.ce.service.openapi.base.admin.entity;
//
//import java.io.Serializable;
//import java.util.Date;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;
//
//
///**
// * 
// * @ClassName: AdminEntity
// * @Description: 管理员类型
// * @author dingjia@300.cn
// *
// */
//@Document(collection = "OPC_ADMIN")
//public class AdminEntity implements Serializable {
//
//    /**
//     * 
//     */
//    private static final long serialVersionUID = 4289385435070977887L;
//
//    /** 唯一标识 */
//    @Id
//    private String id;
//
//    /** 用户名称 */
//    @Field("username")
//    private String userName;
//
//    /** 用户密码 */
//    @Field("password")
//    private String password;
//
//    /** 用户说明  */
//    @Field("mem")
//    private String mem;
//
//    /** 创建时间 */
//    @Field("createDate")
//    private Date createDate;
//
//    /** 修改时间 */
//    @Field("updateDate")
//    private Date updateDate;
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getMem() {
//        return mem;
//    }
//
//    public void setMem(String mem) {
//        this.mem = mem;
//    }
//
//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
//
//    public Date getUpdateDate() {
//        return updateDate;
//    }
//
//    public void setUpdateDate(Date updateDate) {
//        this.updateDate = updateDate;
//    }
//
//}
