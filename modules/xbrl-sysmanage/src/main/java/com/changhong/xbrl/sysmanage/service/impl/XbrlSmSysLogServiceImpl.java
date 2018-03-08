package com.changhong.xbrl.sysmanage.service.impl;

import com.changhong.xbrl.sysmanage.dao.XbrlSmSysLogMapper;
import com.changhong.xbrl.sysmanage.domain.XbrlSmLog;
import com.changhong.xbrl.sysmanage.service.XbrlSmSysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Liang on 2018/3/2.
 */
@Service
public class XbrlSmSysLogServiceImpl implements XbrlSmSysLogService {

    @Autowired
    private XbrlSmSysLogMapper xbrlSmSysLogMapper;

    @Override
    public List<XbrlSmLog> selectXbrlSmLogList(HashMap<String, Object> paramsMap) {
       return xbrlSmSysLogMapper.selectXbrlSmLogList(paramsMap);
    }

    @Override
    public int getCount(String key) {
       return xbrlSmSysLogMapper.getCount(key);
    }

    @Override
    public XbrlSmLog selectXbrlSmLogById(long id) {
       return xbrlSmSysLogMapper.selectXbrlSmLogById(id);
    }

    @Override
    public int insertXbrlSmLog(XbrlSmLog log) {
       return  xbrlSmSysLogMapper.insertXbrlSmLog(log);
    }

}
