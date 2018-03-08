package com.changhong.xbrl.sysmanage.service.impl;

import com.changhong.xbrl.sysmanage.dao.XbrlSmUserEntityMapper;
import com.changhong.xbrl.sysmanage.domain.XbrlSmUserEntity;
import com.changhong.xbrl.sysmanage.service.XbrlSmUserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XbrlSmUserEntityServiceImpl implements XbrlSmUserEntityService{
    @Autowired
    private XbrlSmUserEntityMapper xbrlSmUserEntityMapper;
    @Override
    public long getXbrlSmUserEntityRowCount(){
        return xbrlSmUserEntityMapper.getXbrlSmUserEntityRowCount();
    }
    @Override
    public List<XbrlSmUserEntity> selectXbrlSmUserEntity(){
        return xbrlSmUserEntityMapper.selectXbrlSmUserEntity();
    }
    @Override
    public XbrlSmUserEntity selectXbrlSmUserEntityByObj(XbrlSmUserEntity obj){
        return xbrlSmUserEntityMapper.selectXbrlSmUserEntityByObj(obj);
    }
    @Override
    public XbrlSmUserEntity selectXbrlSmUserEntityById(Object id){
        return xbrlSmUserEntityMapper.selectXbrlSmUserEntityById(id);
    }
    @Override
    public int insertXbrlSmUserEntity(XbrlSmUserEntity value){
        return xbrlSmUserEntityMapper.insertXbrlSmUserEntity(value);
    }
    @Override
    public int insertNonEmptyXbrlSmUserEntity(XbrlSmUserEntity value){
        return xbrlSmUserEntityMapper.insertNonEmptyXbrlSmUserEntity(value);
    }
    @Override
    public int deleteXbrlSmUserEntityById(Object id){
        return xbrlSmUserEntityMapper.deleteXbrlSmUserEntityById(id);
    }
    @Override
    public int updateXbrlSmUserEntityById(XbrlSmUserEntity enti){
        return xbrlSmUserEntityMapper.updateXbrlSmUserEntityById(enti);
    }
    @Override
    public int updateNonEmptyXbrlSmUserEntityById(XbrlSmUserEntity enti){
        return xbrlSmUserEntityMapper.updateNonEmptyXbrlSmUserEntityById(enti);
    }

    public XbrlSmUserEntityMapper getXbrlSmUserEntityMapper() {
        return this.xbrlSmUserEntityMapper;
    }

    public void setXbrlSmUserEntityMapper(XbrlSmUserEntityMapper xbrlSmUserEntityMapper) {
        this.xbrlSmUserEntityMapper = xbrlSmUserEntityMapper;
    }

}