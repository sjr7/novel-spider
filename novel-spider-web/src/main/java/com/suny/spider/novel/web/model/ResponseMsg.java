package com.suny.spider.novel.web.model;

import lombok.*;

/**
 * 返回json数据的结果
 *
 * @author sunjianrong
 * @date 2017/02/27 20:14
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ResponseMsg {
    private int status;
    private String desc;
    private Object data;

    public static ResponseMsg success(Object data) {
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setStatus(1);
        responseMsg.setData(data);
        return responseMsg;
    }

    public static ResponseMsg error(String desc) {
        ResponseMsg responseMsg = new ResponseMsg();
        responseMsg.setStatus(0);
        responseMsg.setDesc(desc);
        return responseMsg;
    }

}
