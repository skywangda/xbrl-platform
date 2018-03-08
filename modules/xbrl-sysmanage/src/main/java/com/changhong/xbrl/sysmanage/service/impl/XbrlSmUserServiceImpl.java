package com.changhong.xbrl.sysmanage.service.impl;

import com.changhong.xbrl.sysmanage.dao.XbrlSmUserMapper;
import com.changhong.xbrl.sysmanage.domain.XbrlSmCompany;
import com.changhong.xbrl.sysmanage.domain.XbrlSmRole;
import com.changhong.xbrl.sysmanage.domain.XbrlSmUser;
import com.changhong.xbrl.sysmanage.service.XbrlSmUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class XbrlSmUserServiceImpl implements XbrlSmUserService{

    @Autowired
    private XbrlSmUserMapper xbrlSmUserMapper;

    @Override
    public long getXbrlSmUserRowCount(String key){
        return xbrlSmUserMapper.getXbrlSmUserRowCount(key);
    }
    @Override
    public List<XbrlSmUser> selectXbrlSmUser(){
        return xbrlSmUserMapper.selectXbrlSmUser();
    }

    @Override
    public List<XbrlSmUser> selectXbrlSmUserPage(String key, Integer pageNumber, Integer pagzSize, String sortType, String direction) {
        Integer start = pageNumber > 0 ? (pageNumber - 1) * pagzSize : 0;
        if(StringUtils.equals("auto", sortType)){
            sortType = "user_code";
        }
        return xbrlSmUserMapper.selectXbrlSmUserPage(key,start,pagzSize,sortType,direction);
    }

    @Override
    public XbrlSmUser selectXbrlSmUserByObj(XbrlSmUser obj){
        return xbrlSmUserMapper.selectXbrlSmUserByObj(obj);
    }
    @Override
    public XbrlSmUser selectXbrlSmUserById(int id){
        return xbrlSmUserMapper.selectXbrlSmUserById(id);
    }

    @Override
    public List<XbrlSmRole> selectXbrlSmRoleByUserId(int id) {
        return xbrlSmUserMapper.selectXbrlSmRoleByUserId(id);
    }

    @Override
    public List<XbrlSmUser> selectXbrlSmUserByRoleId(int id) {
        List<XbrlSmUser> list = xbrlSmUserMapper.selectXbrlSmUserByRoleId(id);
        return list;
    }

    @Override
    public List<XbrlSmCompany> selectXbrlSmCompanyByUserId(int id) {
        List<XbrlSmCompany> list = xbrlSmUserMapper.selectXbrlSmCompanyByUserId(id);
        return list;
    }

    @Override
    public int insertXbrlSmUser(XbrlSmUser value){
        return xbrlSmUserMapper.insertXbrlSmUser(value);
    }
    @Override
    public int insertNonEmptyXbrlSmUser(XbrlSmUser value){
        return xbrlSmUserMapper.insertNonEmptyXbrlSmUser(value);
    }
    @Override
    public int deleteXbrlSmUserById(int id){
        return xbrlSmUserMapper.deleteXbrlSmUserById(id);
    }

    @Override
    public int deleteXbrlSmUserByIds(String ids) {
        String[] idArr = ids.split(",");
        return xbrlSmUserMapper.deleteXbrlSmUserByIds(idArr);
    }

    @Override
    public int updateXbrlSmUserById(XbrlSmUser enti){
        return xbrlSmUserMapper.updateXbrlSmUserById(enti);
    }
    @Override
    public int updateNonEmptyXbrlSmUserById(XbrlSmUser enti){
        return xbrlSmUserMapper.updateNonEmptyXbrlSmUserById(enti);
    }

    @Override
    public int resetUserPwdStatereset(List<XbrlSmUser> list) {
        return xbrlSmUserMapper.updatePwdStateBatch(list);
    }

    @Override
    public int disableOrEnableXbrlSmUser(List<String> ids, Integer state) {
        return xbrlSmUserMapper.disableOrEnableXbrlSmUser(ids, state);
    }

    public XbrlSmUserMapper getXbrlSmUserMapper() {
        return this.xbrlSmUserMapper;
    }

    public void setXbrlSmUserMapper(XbrlSmUserMapper xbrlSmUserMapper) {
        this.xbrlSmUserMapper = xbrlSmUserMapper;
    }

    @Override
    public Long getRepeatedNameCount(String name) {
        return xbrlSmUserMapper.getRepeatedNameCount(name);
    }

    @Override
    public Long getRepeatedCodeCount(String code) {
        return xbrlSmUserMapper.getRepeatedCodeCount(code);
    }


    @Override
    public Long getRepeatedMailCount(String mail) {
        return xbrlSmUserMapper.getRepeatedMailCount(mail);
    }

    @Override
    public List<Map<String, Object>> selectUserForRolePage(Integer roleId, String key, Integer pageNumber, Integer pagzSize) {
        Integer start = pageNumber > 0 ? (pageNumber - 1) * pagzSize : 0;
        return xbrlSmUserMapper.selectUserForRolePage(roleId,key, start, pagzSize);
    }

    @Override
    public List<Map<String, Object>> selectUserForCompanyPage(Integer companyId, String key, Integer pageNumber, Integer pagzSize) {
        Integer start = pageNumber > 0 ? (pageNumber - 1) * pagzSize : 0;
        return xbrlSmUserMapper.selectUserForCompanyPage(companyId,key, start, pagzSize);
    }

    @Override
    public List<Map<String, Object>> selectUserByCompanyId(Integer companyId) {
        return xbrlSmUserMapper.selectUserByCompanyId(companyId);
    }

    @Override
    public List<Map<String, Object>> selectUserForRole() {
        return xbrlSmUserMapper.selectUserForRole();
    }

    @Override
    public List<Map<String, Object>> selectUserByRoleId(Integer roleId) {
        return xbrlSmUserMapper.selectUserByRoleId(roleId);
    }

    @Override
    public List<Map<String, Object>> selectFlagCompanyByUserId(Integer userId) {
        return xbrlSmUserMapper.selectFlagCompanyByUserId(userId);
    }

    @Override
    public List<Map<String, Object>> selectFlagRoleByUserId(Integer userId) {
        return xbrlSmUserMapper.selectFlagRoleByUserId(userId);
    }
}