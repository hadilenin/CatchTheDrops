package com.mygdx.Util;

public class Util {
    public static <T extends Number> T clamp(T var,T firstVar,T lastVar){
        if(var.doubleValue() < firstVar.doubleValue())
            return firstVar;
        if (var.doubleValue() > lastVar.doubleValue())
            return lastVar;

        return var;
    }
}
