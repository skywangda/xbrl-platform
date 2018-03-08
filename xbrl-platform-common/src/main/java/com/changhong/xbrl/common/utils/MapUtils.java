package com.changhong.xbrl.common.utils;

import java.util.HashMap;

/**
 * Map工具类
 *
 * @author hlc
 * @email 768353393@qq.com
 * @date 2018年3月8日
 */
public class MapUtils extends HashMap<String, Object> {

    @Override
    public MapUtils put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
