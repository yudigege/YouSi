package com.example.qsys.yousi.net.rx.extension;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanshaokai on 2017/10/11 18:05
 */

public class PagingReq {
    public int page = 0;
    public int pageSize = 15;
    public boolean desc = true;
    public String sort_key;


    //组合参数
    public Map<String, String> generatePagingParameter() {
        Map<String, String> map = new HashMap<>();
        map.put("page", String.valueOf(page));
        map.put("page_size", String.valueOf(pageSize));
        if (!TextUtils.isEmpty(sort_key)) {
            map.put("sort_key", sort_key);
        }
        map.put("desc", String.valueOf(desc));
        return map;
    }
}
