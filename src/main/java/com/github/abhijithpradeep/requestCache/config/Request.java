package com.github.abhijithpradeep.requestCache.config;

/**
 * Created by abhijithpradeep on 26/9/17.
 */
public class Request {

    private String requestId;

    private String requestName;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestId='" + requestId + '\'' +
                ", requestName='" + requestName + '\'' +
                '}';
    }
}
