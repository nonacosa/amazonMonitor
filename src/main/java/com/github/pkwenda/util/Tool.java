package com.github.pkwenda.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class Tool {

    /**
     * 关键字全匹配，标题党暂时防不住 todo
     * @param name
     * @param keyword
     * @return
     */
    public static boolean matchKeyword(String name,String keyword){
        if(StringUtils.isEmpty(name)) return false;
        List<String> keys = Arrays.asList(keyword.split(" "));
        if(keys.size() <= 1) return true;
        for (String key : keys) {
            if(!name.toLowerCase().contains(key.toLowerCase())) return false;
        }
        return true;
    }

}
