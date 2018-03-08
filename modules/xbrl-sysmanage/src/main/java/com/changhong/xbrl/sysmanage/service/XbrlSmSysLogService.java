package com.changhong.xbrl.sysmanage.service;

import com.changhong.xbrl.sysmanage.domain.XbrlSmLog;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Liang on 2018/3/2.
 */
public interface XbrlSmSysLogService {

    /**
     * 获得XbrlSmLog数据集合
     * @return
     */
    List<XbrlSmLog> selectXbrlSmLogList(HashMap<String, Object> paramsMap);

    /**
     * 获得XbrlSmLog数据的总行数
     * @return
     */
    int getCount(String key);

    /**
     * 通过XbrlSmLog的id获得XbrlSmLog对象
     * @param id
     * @return
     */
    XbrlSmLog selectXbrlSmLogById(long id);

    /**
     * 插入XbrlSmLog到数据库,包括null值
     * @param log
     * @return
     */
    int insertXbrlSmLog(XbrlSmLog log);

}
