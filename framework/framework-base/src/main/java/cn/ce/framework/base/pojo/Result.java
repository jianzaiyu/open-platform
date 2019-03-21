package cn.ce.framework.base.pojo;

import cn.ce.framework.base.common.CloudResult;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author ggs
 * @date 2018/8/5 11:07
 */
@Data
public class Result<T> implements CloudResult {
    private int status;
    private String code;
    private String msg;
    private T data;


    public Result(HttpStatus status, ResultCode resultCode, T data) {
        this.status = status.value();
        this.code = resultCode.getCode();
        this.msg = resultCode.getDesc();
        this.data = data;
    }

    public Result(HttpStatus status, ResultCode resultCode, T data, String msg) {
        this.status = status.value();
        this.code = resultCode.getCode();
        this.msg = msg;
        this.data = data;
    }

    public Result(T data) {
        this.status = HttpStatus.OK.value();
        this.code = ResultCode.SYS0000.getCode();
        this.msg = ResultCode.SYS0000.getDesc();
        this.data = data;
    }
}
