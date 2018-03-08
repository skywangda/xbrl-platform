package com.changhong.xbrl.sysmanage.service.impl;

import com.changhong.xbrl.sysmanage.dao.XbrlSmResourceMapper;
import com.changhong.xbrl.sysmanage.domain.XbrlSmResource;
import com.changhong.xbrl.sysmanage.service.XbrlSmResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class XbrlSmResourceServiceImpl implements XbrlSmResourceService{
    @Autowired
    private  XbrlSmResourceMapper  xbrlSmResourceMapper;
    @Override
    public List<XbrlSmResource> selectXbrlSmResourceList(HashMap<String, Object> searchParamsMap) {
        return xbrlSmResourceMapper.selectXbrlSmResourceList(searchParamsMap);
    }
    public  int getCount(HashMap<String,Object> searchParamsMap)
    {
        return xbrlSmResourceMapper.getCount(searchParamsMap);

    }
    public List<XbrlSmResource> getXbrlSmResourceListByNameAndUrl(String name, String url){
        return  xbrlSmResourceMapper.selectXbrlSmResourceListByNameAndUrl(name,url);
    }

   public Long saveXbrlSmResource(XbrlSmResource xbrlSmResource){
       return  xbrlSmResourceMapper.insertXbrlSmResource(xbrlSmResource);
    }

    public Long deleteXbrlSmResourceByIds(String[] ids){
       return  xbrlSmResourceMapper.deleteXbrlSmResourceByIds(ids);
    }

    public XbrlSmResource getXbrlSmResourceById(Integer id)
    {
        return  xbrlSmResourceMapper.selectXbrlSmResourceById(id);
    }

    public List<XbrlSmResource> getXbrlSmResourceAllByParentId(Integer parentId, String key, Integer type){
        return xbrlSmResourceMapper.selectXbrlSmResourceAllByParentId(parentId,key,type);
    }

   public Long updateXbrlSmResource(XbrlSmResource xbrlSmResource)
   {
       return  xbrlSmResourceMapper.updateXbrlSmResource(xbrlSmResource);
   }

   public 	int disableOrEnableXbrlSmResource(List<String> ids, Integer state)
   {
       return  xbrlSmResourceMapper.disableOrEnableXbrlSmResource(ids,state);
   }

   public List<XbrlSmResource> getXbrlSmResourceAllByRoleId(Integer roleId){
        return  xbrlSmResourceMapper.selectXbrlSmResourceListByRoleId(roleId);
   }

}