package com.suny.spider.novel.web.entitys;

/**
 * Comments:   返回json数据的结果
 *
 * @author 孙建荣
 * @date 2017/02/27 20:14
 */
public class JSONResponse {
    private int status;
    private String desc;
    private Object data;

    public static JSONResponse success(Object data) {
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.setStatus(1);
        jsonResponse.setData(data);
        return jsonResponse;
    }

    public static JSONResponse error(String desc) {
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.setStatus(0);
        jsonResponse.setDesc(desc);
        return jsonResponse;
    }

    public JSONResponse() {
    }

    public JSONResponse(String desc, int status, Object data) {
        this.desc = desc;
        this.status = status;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JSONResponse{" +
                "status=" + status +
                ", desc='" + desc + '\'' +
                ", data=" + data +
                '}';
    }
}
