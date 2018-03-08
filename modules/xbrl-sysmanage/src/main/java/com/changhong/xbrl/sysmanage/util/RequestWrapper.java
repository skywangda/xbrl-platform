package com.changhong.xbrl.sysmanage.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 
 * <p>Title: 自定义请求Wrapper</p>
 * <p>Description: 自定义请求Wrapper</p>
 * <p>Company: changhong </p>
 * @author liBo
 * @date 2015-3-10
 * @version 1.0
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private String key;
    private String iv;

    private Map<String, String[]> paramMap;
    protected static Logger logger = LoggerFactory.getLogger(RequestWrapper.class);
    private final ByteArrayOutputStream bos = new ByteArrayOutputStream();
    public RequestWrapper(HttpServletRequest request) {
        super(request);
        paramMap = new HashMap<>();
        paramMap.putAll(request.getParameterMap());
    }
    public RequestWrapper(HttpServletRequest request, String key, String iv) {
        super(request);
        paramMap = new HashMap<>();
        paramMap.putAll(request.getParameterMap());
        this.setKey(key);
        this.setIv(iv);
    }

    @Override
    public String getParameter(String name) {
        String val = super.getParameter(name);
        //特殊字段没有加密解密过程
        if(StringUtils.equals(name, "sign")){
            return val;
        }
        boolean flag = true;
        String decryptVal = null;
        try {
            //AES密文长度大于16
            if(StringUtils.isEmpty(val) || val.length() < 16){
                decryptVal = val;
            }else {
                decryptVal = AesEncryptUtil.aesDecrypt(val, key, iv);
            }
        } catch (Exception e) {
            flag = false;
            logger.error("解密参数["+name+"]失败,值为"+val);
        }
        //参数全部解密成功
        if(flag) {
            return decryptVal;
        }else {
            return val;
        }
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> paramMap = super.getParameterMap();
        Map<String, String[]> decryptMap = new HashMap<String, String[]>();
        Iterator<Map.Entry<String, String[]>> itr = paramMap.entrySet().iterator();
        boolean flag = true;
        while(itr.hasNext()){
            Map.Entry<String, String[]> entry = itr.next();
            //AES解密value值
            String[] vals = entry.getValue();
            //特殊字段没有加密解密过程
            if(StringUtils.equals(entry.getKey(), "sign")){
                decryptMap.put(entry.getKey(), vals);
                continue;
            }
            if(vals != null && vals.length > 0) {
                String[] decryptVals = new String[vals.length];
                for (int i = 0; i < vals.length; i++) {
                    String val = vals[i];
                    try {
                        //AES密文长度大于16
                        if(StringUtils.isEmpty(val) || val.length() < 16){
                            decryptVals[i] = val;
                        }else {
                            decryptVals[i] = AesEncryptUtil.aesDecrypt(val, key, iv);
                        }
                    } catch (Exception e) {
                        flag = false;
                        logger.error("解密参数[" + entry.getKey() + "]失败,值为" + val);
                        break;
                    }
                }
                decryptMap.put(entry.getKey(), decryptVals);
            }else {
                decryptMap.put(entry.getKey(), vals);
            }
        }
        //参数全部解密成功
        if(flag) {
            return decryptMap;
        }else {
            return paramMap;
        }
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] vals = super.getParameterValues(name);
        //特殊字段没有加密解密过程
        if(StringUtils.equals(name, "sign")){
            return vals;
        }
        boolean flag = true;
        String[] decryptVals = null;
        if(vals != null && vals.length > 0) {
            decryptVals = new String[vals.length];
            for (int i = 0; i < vals.length; i++) {
                String val = vals[i];
                try {
                    //AES密文长度大于16
                    if (StringUtils.isEmpty(val) || val.length() < 16) {
                        decryptVals[i] = val;
                    } else {
                        decryptVals[i] = AesEncryptUtil.aesDecrypt(val, key, iv);
                    }
                } catch (Exception e) {
                    flag = false;
                    logger.error("解密参数[" + name + "]失败,值为" + val);
                    break;
                }
            }
        }else  {
            return vals;
        }
        //参数全部解密成功
        if(flag) {
            return decryptVals;
        }else {
            return vals;
        }
    }

    public void decryptdata(){
        Map<String, String[]> decrypara = getParameterMap();
        for(String key : paramMap.keySet()){
            paramMap.put(key,decrypara.get(key));
        }
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

}