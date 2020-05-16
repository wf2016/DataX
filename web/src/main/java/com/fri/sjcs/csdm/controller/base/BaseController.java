package com.fri.sjcs.csdm.controller.base;

import com.fasterxml.jackson.databind.deser.std.DateDeserializers;
import com.fri.sjcs.utils.PageData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;


import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BaseController {
    public Gson gson = new Gson();

    public String jsonParam;

    public PageData getPageData(HttpServletRequest request){
        return new PageData(request);
    }


    /*
    * 变更分页参数
    * pageNo为默认数值
    * */
    public void changePage(PageData params, String key, int pageNo){
        params.put(key,params.get(key)==null?pageNo:params.getInt(key));
    }

    /*
    * 变更分页参数
    * noStr，sizeStr 为key
    * pageNo，pageSize 为默认值
    * start 为分页key
    * */
    public void changeStart(PageData params, String noStr, int pageNo, String sizeStr , int pageSize, String start){
        this.changePage(params,noStr,pageNo);
        this.changePage(params,sizeStr,pageSize);
        params.put(start,(params.getInt(noStr)-1)*params.getInt(sizeStr));
    }
    /*
    * 系统异常
    * */
    public Object error(){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("success", false);
        result.put("message", "操作失败，系统异常！");
        return result;
    }
    /*
    * 系统异常
    * */
    public Object error(int code){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("flag", false);
        result.put("code",code);
        result.put("message", "操作失败，系统异常！");
        return result;
    }
    /*
   * 系统异常
   * */
    public Object error(int code,String message){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("flag", false);
        result.put("code",code);
        result.put("message", message);
        return result;
    }
    /*
   * 系统异常
   * */
    public Object error(String message){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("flag", false);
        result.put("message", message);
        return result;
    }
    /*
    * 参数异常
    * */
    public Object paramsError(){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("success", false);
        result.put("message", "操作失败，参数异常！");
        return result;
    }
    /*
    * 参数异常
    * */
    public Object missingParams(String msg){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("success", false);
        result.put("message", "操作失败，"+msg);
        return result;
    }
    public Object missingParams(String msg,int code){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("flag", false);
        result.put("code",code);
        result.put("message", "操作失败，"+msg);
        return result;
    }

    public Object noUser(){
        Map<String,Object> result = new HashMap<String,Object>();
        result.put("flag", false);
        result.put("code",100);
        result.put("message", "操作失败，获取不到登录用户！");
        return result;
    }

    /*
    * 修改时间参数
    * @String def 默认时间
    * @Params params
    * @String[] keys 需要变更的参数 key
    */
    public void changeDate(String def, PageData params, String... keys) {
        for(String key : keys){
            params.put(key,params.get(key)==null?def:params.getString(key));
        }
    }


    protected <T> T fromJsonToBean(final String params, final Class<T> clazz) throws UnsupportedEncodingException {
        final String json = URLDecoder.decode(URLDecoder.decode(params, "UTF-8"), "UTF-8");
        final DateDeserializers.DateDeserializer jsonDeserializer = new DateDeserializers.DateDeserializer();
        final GsonBuilder gsonBuilder = (new GsonBuilder()).registerTypeAdapter(Date.class, jsonDeserializer);

        final Gson gson1 = gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson1.fromJson(json, clazz);
    }

    protected <T> T fromJsonToBean(final String params, final Class<T> clazz, final Map<Class, JsonDeserializer> deserializers) throws UnsupportedEncodingException {
        final String json = URLDecoder.decode(URLDecoder.decode(params, "UTF-8"), "UTF-8");
        final DateDeserializers.DateDeserializer jsonDeserializer = new DateDeserializers.DateDeserializer();
        final GsonBuilder gsonBuilder = (new GsonBuilder()).registerTypeAdapter(Date.class, jsonDeserializer);
        if (null != deserializers) {
            final Iterator gson = deserializers.entrySet().iterator();

            while (gson.hasNext()) {
                final Map.Entry deserializer = (Map.Entry) gson.next();
                gsonBuilder.registerTypeAdapter((Type) deserializer.getKey(), deserializer.getValue());
            }
        }

        final Gson gson1 = gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson1.fromJson(json, clazz);
    }

    /***
     *  多分配方式 弃用
     * @param param
     * @return
     */
//    public FdxfpmxEntity getFdxfpmxEntity(PageData param) {
//        FdxfpmxEntity fdxfpmxEntity = new FdxfpmxEntity();
////        params==null  退出
//        if(param==null){
//            return fdxfpmxEntity;
//        }
//        fdxfpmxEntity.setId(param.getString("id"));
//        fdxfpmxEntity.setJzfs(StaticVar.JZFS_02);
//        List<LinkedHashMap> fpxxs = (List<LinkedHashMap>) param.get("fpxx");
////        分配信息为空 退出
//        if(fpxxs==null){
//            fdxfpmxEntity.setFpxx(null);
//        }else{
////            转换fpxx
//            List<FdxfpmxEntity.FpxxEntity> fpxxEntities = new ArrayList<>();
//            fdxfpmxEntity.setFpxx(fpxxEntities);
//            for(LinkedHashMap page : fpxxs){
//                String dxmc = page.get("dxmc")==null?"":page.get("dxmc").toString();
//                List<LinkedHashMap> fpmxs = (List<LinkedHashMap>) page.get("fpmx");
//                if(StringUtils.isNotBlank(dxmc) && fpmxs!=null && fpmxs.size()>0){
//                    FdxfpmxEntity.FpxxEntity fpxxEntity = fdxfpmxEntity.new FpxxEntity();
//                    fpxxEntity.setDxmc(dxmc);
//                    fpxxEntity.setDxlxr((page.get("dxlxr")==null?"":page.get("dxlxr").toString()));
//                    fpxxEntity.setDxlxfx((page.get("dxlxfs")==null?"":page.get("dxlxfs").toString()));
//                    fpxxEntity.setDxlx((page.get("dxlx")==null?"":page.get("dxlx").toString()));
//                    List<FdxfpmxEntity.FpxxEntity.FpmxEntity> fpmxEntities = new ArrayList<>();
//                    fpxxEntity.setFpmx(fpmxEntities);
////                    转换分配明细
//                    for(LinkedHashMap data : fpmxs){
//                        String wzid = data.get("wzid")==null?"":data.get("wzid").toString();
//                        if(StringUtils.isNotBlank(wzid)&&data.get("fpsl")!=null){
//                            Double fpsl = data.get("fpsl")==null?0.0:Double.valueOf(data.get("fpsl").toString());
//                            String wzmc = data.get("wzmc")==null?"":data.get("wzmc").toString();
//                            String wzlx = data.get("wzlx")==null?"":data.get("wzlx").toString();
//                            FdxfpmxEntity.FpxxEntity.FpmxEntity fpmxEntity = fpxxEntity.new FpmxEntity();
//                            fpmxEntity.setFpsl(fpsl);
//                            fpmxEntity.setWzid(wzid);
//                            fpmxEntity.setWzlx(wzlx);
//                            fpmxEntity.setWzmc(wzmc);
//                            fpmxEntities.add(fpmxEntity);
//                        }
//                    }
//                    fpxxEntities.add(fpxxEntity);
//                }
//            }
//        }
//        return fdxfpmxEntity;
//    }


}
