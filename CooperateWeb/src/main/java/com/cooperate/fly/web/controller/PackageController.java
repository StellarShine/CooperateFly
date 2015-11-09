package com.cooperate.fly.web.controller;

import com.cooperate.fly.bo.*;
import com.cooperate.fly.mapper.DataInfoMapper;
import com.cooperate.fly.mapper.DataValueMapper;
import com.cooperate.fly.mapper.PackageInfoMapper;
import com.cooperate.fly.mapper.UserMapper;
import com.cooperate.fly.service.model.ModelDesign;
import com.cooperate.fly.web.util.CatalogNode;
import com.cooperate.fly.web.util.EasyUITreeNode;
import com.cooperate.fly.web.util.Result;
import com.cooperate.fly.web.util.WebFrontHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * Created by yj-mbp on 15/11/1.
 * 数据包相关操作
 */
@Controller
@RequestMapping("/package")
public class PackageController {

    @Autowired
    private PackageInfoMapper packageInfoMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ModelDesign modelDesign;

    @Autowired
    private DataInfoMapper dataInfoMapper;

    @Autowired
    private DataValueMapper dataValueMapper;

    /**
     * 加载所有数据包信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/loadPackageInfo", produces = "application/json;charset=utf-8")
    public List<PackageInfo> loadPackageInfo(Catalog log){
        List<PackageInfo> result = modelDesign.getPackagesByCatalogId(log.getId());
        return result;
    }

    /**
     * 加载所有人信息
     * @return
     */
    @RequestMapping(value = "/loadPackageUser", produces = "application/json;charset=utf-8")
    @ResponseBody
    public String loadUserInfo(){
        List<User> users = userMapper.selectAll();
        List<Map> result = new LinkedList<Map>();
        for (User user : users){
            Map map = new HashMap();
            map.put("id", user.getId());
            map.put("userName", user.getUserName());
            result.add(map);
        }
        return new Gson().toJson(result);
    }


    /**
     * 保存数据包的属性
     * 增删改 都通过这个接口
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveAttr")
    public Result savePackageAttr(String packageId, String attrStr){
        PackageInfo packageInfo = packageInfoMapper.selectByPrimaryKey(Integer.valueOf(packageId));
        if(packageInfo == null)
            throw new IllegalArgumentException("数据包不存在");
        packageInfo.setExtraAttributes(attrStr);
        packageInfoMapper.updateByPrimaryKey(packageInfo);
        return new Result();
    }

    /**
     * 获取数据包的属性信息
     * @param packageId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getAttr", produces = "application/json;charset=utf-8")
    public String getPackageAttr(String packageId){
        PackageInfo packageInfo = packageInfoMapper.selectByPrimaryKey(Integer.valueOf(packageId));
        if (packageInfo == null)
            return null;
//        Map<String,String> attrs = new Gson().fromJson(packageInfo.getExtraAttributes(),
//                new TypeToken<Map<String, String>>(){}.getType());
//        List<Map> result = new LinkedList<Map>();
//        for (Map.Entry<String,String> entry : attrs.entrySet()){
//            Map<String, String> e = new HashMap<String, String>();
//            e.put("attributeName", entry.getKey());
//            e.put("attributeValue", entry.getValue());
//            result.add(e);
//        }
        return packageInfo.getExtraAttributes();
    }

    /**
     * 保存上游数据包和负责人信息
     * @param packageId 数据包id
     * @param directorIds 负责人ids
     * @param upstreamIds 上游数据包ids
     * @return
     */
    @ResponseBody
    @RequestMapping("/savePackageInfo")
    public Result savePackageInfo(String packageId, String directorId, String upstreamIds){
        PackageInfo packageInfo = packageInfoMapper.selectByPrimaryKey(Integer.valueOf(packageId));
        if(packageInfo == null){
            throw new IllegalArgumentException("错误的数据包");
        }
        packageInfo.setDirectorId(Integer.valueOf(directorId));
        packageInfo.setPid(upstreamIds.toString());
        packageInfoMapper.updateByPrimaryKey(packageInfo);
        return new Result();
    }

    /**
     * 获取数据包的数据信息
     * @param packageId
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getPackageData", produces = "application/json;charset=utf-8")
    public String getPackageData(String packageId){
        List<DataInfo> dataInfos = dataInfoMapper.selectByPackageId(Integer.valueOf(packageId));
        List<PackageData> dataList = new LinkedList<PackageData>();
        for (DataInfo info : dataInfos){
            PackageData data = new PackageData();
            data.setName(info.getName());
            data.setId(info.getId());
            data.setType(info.getType());
            data.setParentId(info.getParentId());
            dataList.add(data);
        }
        return new Gson().toJson(WebFrontHelper.buildPackageDataTree(dataList));
    }

    /**
     * 保存数据项
     * @return
     */
    @ResponseBody
    @RequestMapping("/savePackageData")
    public Result savePackageData(Integer packageId, String name, Integer type,Integer pid){
        DataInfo dataInfo = new DataInfo(name, type, packageId, pid);
        dataInfoMapper.insert(dataInfo);
        return new Result();
    }

    /**
     * 修改数据项
     * @return
     */
    @ResponseBody
    @RequestMapping("/editPackageData")
    public Result editPackageData(DataInfo dataInfo){
        dataInfoMapper.updateByPrimaryKey(dataInfo);
        return new Result();
    }

    /**
     * 删除数据项
     * @return
     */
    @ResponseBody
    @RequestMapping("/deletePackageData")
    public Result deletePackageData(Integer id){
        dataInfoMapper.deleteByPrimaryKey(id);
        return new Result();
    }

    private Map<String, String> parseAttrs(String attrs){
        Map<String, String> attrsMap = new HashMap<String, String>();
        if (attrs != null){
            String[] attsPair = attrs.split("$");
            for (String pair : attsPair){
                String[] KVs = pair.split("=");
                attrsMap.put(KVs[0], KVs[1]);
            }
        }
        return attrsMap;
    }



}
