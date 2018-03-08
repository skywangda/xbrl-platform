package com.changhong.xbrl.sysmanage.service.impl;
import com.changhong.xbrl.sysmanage.dao.XbrlSmCompanyUserMapper;
import com.changhong.xbrl.sysmanage.domain.XbrlSmCompanyUser;
import com.changhong.xbrl.sysmanage.service.XbrlSmCompanyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class XbrlSmCompanyUserServiceImpl implements XbrlSmCompanyUserService{
    @Autowired
    private XbrlSmCompanyUserMapper xbrlSmCompanyUserMapper;
    @Override
    public long getXbrlSmCompanyUserRowCount(){
        return xbrlSmCompanyUserMapper.getXbrlSmCompanyUserRowCount();
    }
    @Override
    public List<XbrlSmCompanyUser> selectXbrlSmCompanyUser(){
        return xbrlSmCompanyUserMapper.selectXbrlSmCompanyUser();
    }
    @Override
    public XbrlSmCompanyUser selectXbrlSmCompanyUserByObj(XbrlSmCompanyUser obj){
        return xbrlSmCompanyUserMapper.selectXbrlSmCompanyUserByObj(obj);
    }
    @Override
    public XbrlSmCompanyUser selectXbrlSmCompanyUserById(Object id){
        return xbrlSmCompanyUserMapper.selectXbrlSmCompanyUserById(id);
    }
    @Override
    public int insertXbrlSmCompanyUser(XbrlSmCompanyUser value){
        return xbrlSmCompanyUserMapper.insertXbrlSmCompanyUser(value);
    }
    @Override
    public int insertNonEmptyXbrlSmCompanyUser(XbrlSmCompanyUser value){
        return xbrlSmCompanyUserMapper.insertNonEmptyXbrlSmCompanyUser(value);
    }

    @Override
    public int insertXbrlSmCompanyUserBatch(List<XbrlSmCompanyUser> list) {
        return xbrlSmCompanyUserMapper.insertXbrlSmCompanyUserBatch(list);
    }

    @Override
    public int deleteXbrlSmCompanyUserById(Object id){
        return xbrlSmCompanyUserMapper.deleteXbrlSmCompanyUserById(id);
    }

    @Override
    public int deleteXbrlSmCompanyUserByUserIds(String ids) {
        String[] idArr = ids.split(",");
        return xbrlSmCompanyUserMapper.deleteXbrlSmCompanyUserByUserIds(idArr);
    }

    @Override
    public int deleteXbrlSmCompanyUserByCompanyId(Object id) {
        return xbrlSmCompanyUserMapper.deleteXbrlSmCompanyUserByCompanyId(id);
    }

    @Override
    public int updateXbrlSmCompanyUserById(XbrlSmCompanyUser enti){
        return xbrlSmCompanyUserMapper.updateXbrlSmCompanyUserById(enti);
    }
    @Override
    public int updateNonEmptyXbrlSmCompanyUserById(XbrlSmCompanyUser enti){
        return xbrlSmCompanyUserMapper.updateNonEmptyXbrlSmCompanyUserById(enti);
    }

    public XbrlSmCompanyUserMapper getXbrlSmCompanyUserMapper() {
        return this.xbrlSmCompanyUserMapper;
    }

    public void setXbrlSmCompanyUserMapper(XbrlSmCompanyUserMapper xbrlSmCompanyUserMapper) {
        this.xbrlSmCompanyUserMapper = xbrlSmCompanyUserMapper;
    }

}