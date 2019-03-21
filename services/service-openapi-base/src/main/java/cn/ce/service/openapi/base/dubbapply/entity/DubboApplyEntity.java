package cn.ce.service.openapi.base.dubbapply.entity;

import java.util.Date;

public class DubboApplyEntity {
    /** 
     * 主键
     */
    private Integer pkid;

    /** 
     * id
     */
    private String id;

    /** 
     * 父id
     */
    private String parentid;

    /** 
     * 应用名称-接口获取
     */
    private String appname;

    /** 
     * 应用id-接口获取
     */
    private String appid;

    /** 
     * 应用code-接口获取
     */
    private String appcode;

    /** 
     * 接口所属单位   100000	平台
                     100001	官网
                     100002	营销宝
                     100003	扫码购
                     100004	酒店
                     100007	高程
                     100008	电商
                     166666	中台
     */
    private String unittype;

    /** 
     * jar class method
     */
    private String type;

    /** 
     * 
     */
    private String packagename;

    /** 
     * 
     */
    private String classname;

    /** 
     * 接口真实名称
     */
    private String interfacerealname;

    /** 
     * 自定义注解 name 名称
     */
    private String interfacename;

    /** 
     * 自定义注解 des 描述
     */
    private String interfacedes;

    /** 
     * 自定义注解 version 版本
     */
    private String interfaceversion;

    /** 
     * 
     */
    private String filename;

    /** 
     * jar file path
     */
    private String filepath;

    /** 
     * jar type 1 service 2 depency
     */
    private String filetag;

    /** 
     * 创建时间
     */
    private Date creattime;

    /** 
     * 
     */
    private Date updattime;

    /** 
     * 创建人
     */
    private String creatusername;

    /** 
     * 创建用户ID
     */
    private String creatuserid;

    /**
     * 主键
     * @return pkid
     */
    public Integer getPkid() {
        return pkid;
    }

    /**
     * 主键
     * @param pkid
     */
    public void setPkid(Integer pkid) {
        this.pkid = pkid;
    }

    /**
     * id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 父id
     * @return parentId
     */
    public String getParentid() {
        return parentid;
    }

    /**
     * 父id
     * @param parentid
     */
    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    /**
     * 应用名称-接口获取
     * @return appName
     */
    public String getAppname() {
        return appname;
    }

    /**
     * 应用名称-接口获取
     * @param appname
     */
    public void setAppname(String appname) {
        this.appname = appname;
    }

    /**
     * 应用id-接口获取
     * @return appId
     */
    public String getAppid() {
        return appid;
    }

    /**
     * 应用id-接口获取
     * @param appid
     */
    public void setAppid(String appid) {
        this.appid = appid;
    }

    /**
     * 应用code-接口获取
     * @return appCode
     */
    public String getAppcode() {
        return appcode;
    }

    /**
     * 应用code-接口获取
     * @param appcode
     */
    public void setAppcode(String appcode) {
        this.appcode = appcode;
    }

    /**
     * 接口所属单位   100000	平台
                     100001	官网
                     100002	营销宝
                     100003	扫码购
                     100004	酒店
                     100007	高程
                     100008	电商
                     166666	中台
     * @return unitType
     */
    public String getUnittype() {
        return unittype;
    }

    /**
     * 接口所属单位   100000	平台
                     100001	官网
                     100002	营销宝
                     100003	扫码购
                     100004	酒店
                     100007	高程
                     100008	电商
                     166666	中台
     * @param unittype
     */
    public void setUnittype(String unittype) {
        this.unittype = unittype;
    }

    /**
     * jar package class method
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * jar package class method
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return packageName
     */
    public String getPackagename() {
        return packagename;
    }

    /**
     * 
     * @param packagename
     */
    public void setPackagename(String packagename) {
        this.packagename = packagename;
    }

    /**
     * 
     * @return className
     */
    public String getClassname() {
        return classname;
    }

    /**
     * 
     * @param classname
     */
    public void setClassname(String classname) {
        this.classname = classname;
    }

    /**
     * 接口真实名称
     * @return interFaceRealName
     */
    public String getInterfacerealname() {
        return interfacerealname;
    }

    /**
     * 接口真实名称
     * @param interfacerealname
     */
    public void setInterfacerealname(String interfacerealname) {
        this.interfacerealname = interfacerealname;
    }

    /**
     * 自定义注解 name 名称
     * @return interFaceName
     */
    public String getInterfacename() {
        return interfacename;
    }

    /**
     * 自定义注解 name 名称
     * @param interfacename
     */
    public void setInterfacename(String interfacename) {
        this.interfacename = interfacename;
    }

    /**
     * 自定义注解 des 描述
     * @return interFaceDes
     */
    public String getInterfacedes() {
        return interfacedes;
    }

    /**
     * 自定义注解 des 描述
     * @param interfacedes
     */
    public void setInterfacedes(String interfacedes) {
        this.interfacedes = interfacedes;
    }

    /**
     * 自定义注解 version 版本
     * @return interFaceVersion
     */
    public String getInterfaceversion() {
        return interfaceversion;
    }

    /**
     * 自定义注解 version 版本
     * @param interfaceversion
     */
    public void setInterfaceversion(String interfaceversion) {
        this.interfaceversion = interfaceversion;
    }

    /**
     * 
     * @return fileName
     */
    public String getFilename() {
        return filename;
    }

    /**
     * 
     * @param filename
     */
    public void setFilename(String filename) {
        this.filename = filename;
    }

    /**
     * jar file path
     * @return filePath
     */
    public String getFilepath() {
        return filepath;
    }

    /**
     * jar file path
     * @param filepath
     */
    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    /**
     * jar type 1 service 2 depency
     * @return filetag
     */
    public String getFiletag() {
        return filetag;
    }

    /**
     * jar type 1 service 2 depency
     * @param filetag
     */
    public void setFiletag(String filetag) {
        this.filetag = filetag;
    }

    /**
     * 创建时间
     * @return creatTime
     */
    public Date getCreattime() {
        return creattime;
    }

    /**
     * 创建时间
     * @param creattime
     */
    public void setCreattime(Date creattime) {
        this.creattime = creattime;
    }

    /**
     * 
     * @return updatTime
     */
    public Date getUpdattime() {
        return updattime;
    }

    /**
     * 
     * @param updattime
     */
    public void setUpdattime(Date updattime) {
        this.updattime = updattime;
    }

    /**
     * 创建人
     * @return creatUserName
     */
    public String getCreatusername() {
        return creatusername;
    }

    /**
     * 创建人
     * @param creatusername
     */
    public void setCreatusername(String creatusername) {
        this.creatusername = creatusername;
    }

    /**
     * 创建用户ID
     * @return creatUserId
     */
    public String getCreatuserid() {
        return creatuserid;
    }

    /**
     * 创建用户ID
     * @param creatuserid
     */
    public void setCreatuserid(String creatuserid) {
        this.creatuserid = creatuserid;
    }
}