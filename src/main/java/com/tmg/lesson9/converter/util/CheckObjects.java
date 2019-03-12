package com.tmg.lesson9.converter.util;

public class CheckObjects {

    public <T extends Object> boolean isAnyObjectIsNullOrEmpty(T... arg){
        for(T value : arg){
            // check for null
            if(value == null)
                return true;
            // check for empty if object is String type
            if(value instanceof String && ((String) value).isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
