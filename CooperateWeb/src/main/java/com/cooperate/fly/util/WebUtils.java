package com.cooperate.fly.util;

import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class WebUtils {
	
	/**
     *
     * @param request 
     * @param clazz
     * @return
     */
   /* public static <T> T request2Bean(HttpServletRequest request,Class<T> clazz){
        try{
            T bean = clazz.newInstance();
            Enumeration<String> e = request.getParameterNames(); 
            while(e.hasMoreElements()){
                String name = (String) e.nextElement();
                String value = request.getParameter(name);
                BeanUtils.setProperty(bean, name, value);
            }
            return bean;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    *//**
     * @return
     *//*
    public static String makeId(){
        return UUID.randomUUID().toString();
    }*/
}
