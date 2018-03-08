package com.changhong.xbrl.sysmanage.service.impl;

import com.changhong.xbrl.sysmanage.dao.XbrlSmCompanyMapper;
import com.changhong.xbrl.sysmanage.domain.XbrlSmCompany;
import com.changhong.xbrl.sysmanage.service.XbrlSmCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class XbrlSmCompanyServiceImpl implements XbrlSmCompanyService{
    @Autowired
    private XbrlSmCompanyMapper xbrlSmCompanyMapper;

    @Override
    public List<XbrlSmCompany> selectXbrlSmCompanyList(HashMap<String, Object> searchParamsMap) {
        return xbrlSmCompanyMapper.selectXbrlSmCompanyList(searchParamsMap);
    }
    @Override
    public int getCount(HashMap<String,Object> searchParamsMap) {
        return xbrlSmCompanyMapper.getCount(searchParamsMap);
    }
    @Override
    public long getXbrlSmCompanyRowCount(){
        return xbrlSmCompanyMapper.getXbrlSmCompanyRowCount();
    }

    @Override
    public List<XbrlSmCompany> selectXbrlSmCompany(){
        return xbrlSmCompanyMapper.selectXbrlSmCompany();
    }
    @Override
    public XbrlSmCompany selectXbrlSmCompanyByObj(XbrlSmCompany obj){
        return xbrlSmCompanyMapper.selectXbrlSmCompanyByObj(obj);
    }

    @Override
    public List<XbrlSmCompany> selectXbrlSmCompanyListByObj(XbrlSmCompany obj){
        return xbrlSmCompanyMapper.selectXbrlSmCompanyListByObj(obj);
    }
    @Override
    public XbrlSmCompany selectXbrlSmCompanyById(Double id){
        return xbrlSmCompanyMapper.selectXbrlSmCompanyById(id);
    }
    @Override
    public int insertXbrlSmCompany(XbrlSmCompany value){
        return xbrlSmCompanyMapper.insertXbrlSmCompany(value);
    }
    @Override
    public int insertNonEmptyXbrlSmCompany(XbrlSmCompany value){
        return xbrlSmCompanyMapper.insertNonEmptyXbrlSmCompany(value);
    }
    @Override
    public int deleteXbrlSmCompanyById(Double id){
        return xbrlSmCompanyMapper.deleteXbrlSmCompanyById(id);
    }
//    @Override
//    public int deleteXbrlSmCompanyByIds(String ids) {
//        return xbrlSmCompanyMapper.deleteXbrlSmCompanyByIds(ids);
//    }
    @Override
    public int updateXbrlSmCompanyById(XbrlSmCompany enti){
        return xbrlSmCompanyMapper.updateXbrlSmCompanyById(enti);
    }
    @Override
    public int updateNonEmptyXbrlSmCompanyById(XbrlSmCompany enti){
        return xbrlSmCompanyMapper.updateNonEmptyXbrlSmCompanyById(enti);
    }

    public XbrlSmCompanyMapper getXbrlSmCompanyMapper() {
        return this.xbrlSmCompanyMapper;
    }

    public void setXbrlSmCompanyMapper(XbrlSmCompanyMapper xbrlSmCompanyMapper) {
        this.xbrlSmCompanyMapper = xbrlSmCompanyMapper;
    }

}