package com.example.springboot.exceptionProcess;

/*@ControllerAdvice
public class GlobalDefaultExceptionHandler {

    *//**
     * 处理 Exception 类型的异常
     * @param e
     * @return
     *//*
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Map<String,Object> defaultExceptionHandler(Exception e) {

        Map<String,Object> map = new HashMap<>();
        map.put("code", 500);
        map.put("msg", e.getMessage());
        return map;
    }
}*/
