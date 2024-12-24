package com.example.demo.util.logging;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
public class LogEntry {
    private String logId;
    private String node;
    private String clientIp;
    private String traceId;
    private String requestDateTimeUtc;
    private String requestDateTimeUtcActionLevel;
    private String requestPath;
    private String requestQuery;
    private List<String> requestQueries;
    private String requestMethod;
    private String requestScheme;
    private String requestHost;
    private Map<String, String> requestHeaders;
    private String requestBody;
    private String requestContentType;
    private String responseDateTimeUtc;
    private String responseDateTimeUtcActionLevel;
    private String responseStatus;
    private Map<String, String> responseHeaders;
    private String responseBody;
    private String responseContentType;
    private String isExceptionActionLevel;
    private String exceptionMessage;
    private String exceptionStackTrace;

    @Override
    public String toString() {
        return "LogEntry{" +
                "logId='" + logId + '\'' +
                ", node='" + node + '\'' +
                ", clientIp='" + clientIp + '\'' +
                ", traceId='" + traceId + '\'' +
                ", requestDateTimeUtc='" + requestDateTimeUtc + '\'' +
                ", requestDateTimeUtcActionLevel='" + requestDateTimeUtcActionLevel + '\'' +
                ", requestPath='" + requestPath + '\'' +
                ", requestQuery='" + requestQuery + '\'' +
                ", requestQueries=" + requestQueries +
                ", requestMethod='" + requestMethod + '\'' +
                ", requestScheme='" + requestScheme + '\'' +
                ", requestHost='" + requestHost + '\'' +
                ", requestHeaders=" + requestHeaders +
                ", requestBody='" + requestBody + '\'' +
                ", requestContentType='" + requestContentType + '\'' +
                ", responseDateTimeUtc='" + responseDateTimeUtc + '\'' +
                ", responseDateTimeUtcActionLevel='" + responseDateTimeUtcActionLevel + '\'' +
                ", responseStatus='" + responseStatus + '\'' +
                ", responseHeaders=" + responseHeaders +
                ", responseBody='" + responseBody + '\'' +
                ", responseContentType='" + responseContentType + '\'' +
                ", isExceptionActionLevel='" + isExceptionActionLevel + '\'' +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                ", exceptionStackTrace='" + exceptionStackTrace + '\'' +
                '}';
    }
}
