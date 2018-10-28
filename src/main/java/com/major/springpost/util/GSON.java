package com.major.springpost.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.Reader;

public class GSON {

    public static String toJson(Object src){
        return new Gson().toJson(src);
    }

    public static JsonObject parseJsonObject(String src){
        return new JsonParser().parse(src).getAsJsonObject();
    }

    public static JsonObject parseJsonObject(Reader reader){
        return new JsonParser().parse(reader).getAsJsonObject();
    }


}
