package cn.ce.framework.base.support;

import java.util.UUID;

/**
 * @author: ggs
 * @date: 2019-01-23 17:44
 **/
public class IdentifierGenerateSupport {
    public static String genRandomUUID8() {
        return UUID.randomUUID().toString().replace("-", "").substring(0,8);
    }

    public static String genRandomUUID32() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String genRowKeyWithUUIDSub(String id) {
        return id + String.valueOf((char)1) + genRandomUUID32().substring(0, 8);
    }
}
