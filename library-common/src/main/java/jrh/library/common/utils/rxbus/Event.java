package jrh.library.common.utils.rxbus;

import java.util.HashMap;
import java.util.Map;

public class Event<T>  {
    public String code = "";
    public T data;
    public Map<Object,Object> map = new HashMap<>();

    public Event() {
    }

    public Event(String code) {
        this.code = code;
    }

    public Event(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void put(Object key,Object value){
        map.put(key,value);
    }
    public Object get(String key){
        return map.get(key);
    }
}
