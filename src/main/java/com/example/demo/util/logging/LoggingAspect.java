package com.example.demo.util.logging;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.time.Instant;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class LoggingAspect {
    @Autowired
    private ObjectMapper objectMapper;
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);


//    @Before("execution(* com.example.demo.controller..*(..)) ")
//    public void logBeforeMethodCall(JoinPoint joinPoint) {
//        System.out.println("Method executed before: " + joinPoint.getSignature());
//    }

//    @After("execution(* com.example.demo.controller..*(..)) && args(data,..)")
//    public void logAfterMethodCall(JoinPoint joinPoint, Object data) {
//        System.out.println("Method executed after: " + joinPoint.getSignature());
//        if (data != null) {
//            try {
//                String json = objectMapper.writeValueAsString(data);
//                System.out.println("Received data: " + json);
//            } catch (JsonProcessingException e) {
//                System.err.println("Error converting data to JSON: " + e.getMessage());
//            }
//        }
//    }

    @AfterReturning(pointcut = "execution(* com.example.demo.controller..*(..))", returning = "result")
    public void logAfterReturningMethodCall(JoinPoint joinPoint, Object result) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        // Get Headers
        Map<String, String> headersMap = new LinkedHashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headersMap.put(headerName, headerValue);
        }

        // Get Body
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Annotation[][] annotationMatrix = methodSignature.getMethod().getParameterAnnotations();

        String requestBody = IntStream.range(0, annotationMatrix.length)
                .filter(i -> Arrays.stream(annotationMatrix[i])
                        .anyMatch(annotation -> annotation instanceof RequestBody))
                .mapToObj(i -> args[i])
                .findFirst()
                .map(obj -> {
                    try {
                        return objectMapper.writeValueAsString(obj); // Convert object to JSON string
                    } catch (JsonProcessingException e) {
                        return "Error converting request body to JSON";
                    }
                })
                .orElse("null");

        // Create LogEntry
        LogEntry logEntry = new LogEntry();
        logEntry.setLogId(UUID.randomUUID().toString());
        logEntry.setClientIp(request.getRemoteAddr());
        logEntry.setTraceId(request.getHeader("X-B3-TraceId"));
        logEntry.setRequestDateTimeUtc(Instant.now().toString());
        logEntry.setRequestDateTimeUtcActionLevel(Instant.now().toString());
        logEntry.setRequestPath(request.getRequestURI());
        logEntry.setRequestQuery(request.getQueryString());
        logEntry.setRequestQueries(Arrays.asList(request.getQueryString() != null ? request.getQueryString().split("&") : new String[0]));
        logEntry.setRequestMethod(request.getMethod());
        logEntry.setRequestScheme(request.getScheme());
        logEntry.setRequestHost(request.getHeader("Host"));
        logEntry.setRequestHeaders(headersMap);
        logEntry.setRequestBody(requestBody);
        logEntry.setRequestContentType(request.getContentType());
        logEntry.setResponseDateTimeUtc(Instant.now().toString());
        logEntry.setResponseDateTimeUtcActionLevel(Instant.now().toString());
        logEntry.setResponseStatus(String.valueOf(response.getStatus()));
        logEntry.setResponseHeaders(response.getHeaderNames().stream()
                .collect(Collectors.toMap(h -> h, response::getHeader)));
//        logEntry.setResponseBody(result != null ? result.toString() : "null");
        logEntry.setResponseContentType(response.getContentType());
        logEntry.setIsExceptionActionLevel(null); // Set if needed
        logEntry.setExceptionMessage(null); // Set if exception occurs
        logEntry.setExceptionStackTrace(null); // Set if exception occurs
        try {
        logEntry.setResponseBody(result != null ? objectMapper.writeValueAsString(result) : "null");

        // Log Info
        String jsonLogEntry = objectMapper.writeValueAsString(logEntry);
            logger.info(jsonLogEntry);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }


    @AfterThrowing(pointcut = "execution(* com.example.demo.controller..*(..))", throwing = "ex")
    public void logThrowingMethod(JoinPoint joinPoint, Exception ex) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        // Get Headers
        Map<String, String> headersMap = new LinkedHashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headersMap.put(headerName, headerValue);
        }

        // Get Body
        Object[] args = joinPoint.getArgs();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Annotation[][] annotationMatrix = methodSignature.getMethod().getParameterAnnotations();
        String requestBody = IntStream.range(0, annotationMatrix.length)
                .filter(i -> Arrays.stream(annotationMatrix[i])
                        .anyMatch(annotation -> annotation instanceof RequestBody))
                .mapToObj(i -> args[i])
                .findFirst()
                .map(obj -> {
                    try {
                        return objectMapper.writeValueAsString(obj); // Convert object to JSON string
                    } catch (JsonProcessingException e) {
                        return "Error converting request body to JSON";
                    }
                })
                .orElse("null");

        // Create LogEntry
        LogEntry logEntry = new LogEntry();
        logEntry.setLogId(UUID.randomUUID().toString());
//        logEntry.setNode();
        logEntry.setClientIp(request.getRemoteAddr());
        logEntry.setTraceId(request.getHeader("X-B3-TraceId")); //
        logEntry.setRequestDateTimeUtc(Instant.now().toString());
        logEntry.setRequestDateTimeUtcActionLevel(Instant.now().toString());
        logEntry.setRequestPath(request.getRequestURI());
        logEntry.setRequestQuery(request.getQueryString());
        logEntry.setRequestQueries(Arrays.asList(request.getQueryString() != null ? request.getQueryString().split("&") : new String[0]));
        logEntry.setRequestMethod(request.getMethod());
        logEntry.setRequestScheme(request.getScheme());
        logEntry.setRequestHost(request.getHeader("Host"));
        logEntry.setRequestHeaders(headersMap);
        logEntry.setRequestBody(requestBody);
        logEntry.setRequestContentType(request.getContentType());
        logEntry.setResponseDateTimeUtc(null); // Not available in case of exception
        logEntry.setResponseDateTimeUtcActionLevel(null); // Not available in case of exception
        logEntry.setResponseStatus(null); // Not available in case of exception
        logEntry.setResponseHeaders(null); // Not available in case of exception
        logEntry.setResponseBody(null); // Not available in case of exception
        logEntry.setResponseContentType(null); // Not available in case of exception
        logEntry.setIsExceptionActionLevel("true"); // Indicates that an exception occurred
        logEntry.setExceptionMessage(ex.getMessage());
        logEntry.setExceptionStackTrace(Arrays.stream(ex.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n")));

        // Log Info
        String jsonLogEntry = null;
        try {
            jsonLogEntry = objectMapper.writeValueAsString(logEntry);
            logger.info(jsonLogEntry);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
        }
    }


}
