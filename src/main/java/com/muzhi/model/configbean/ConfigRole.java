package com.muzhi.model.configbean;

import com.google.gson.Gson;
import com.muzhi.util.RandomUtil;
import java.util.List;

/**
 * @author 
 */
public class ConfigRole  {
    /**
     * 说明
     */
    private String describe;

    /**
     * 角色ID
     */
    private Integer roleid;

    /**
     * 正面动画资源ID
     */
    private String resources;

    /**
     * 背面动画资源
     */
    private String resources2;

//    /**
//     * 客人类型
//     */
//    private Integer type;
//
//    /**
//     * 权重
//     */
//    private Integer weight;
//
//    /**
//     * 增量
//     */
//    private Integer increase;
//
//    /**
//     * 轮次
//     */
//    private Integer rounds;

    /**
     * 说明
     */
    private String remarks;

    /**
     * 对白
     */
    private String dialogue;

//    /**
//     * 奖励类型
//     */
//    private String reward;

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getResources() {
        return resources;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public String getResources2() {
        return resources2;
    }

    public void setResources2(String resources2) {
        this.resources2 = resources2;
    }

  
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDialogue() {
        return dialogue;
    }

    public void setDialogue(String dialogue) {
        this.dialogue = dialogue;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
    /**
     * 随机获取角色
     * @param list
     * @return
     */
    public static ConfigRole getConfigRole(List<ConfigRole> list){
    	int i = RandomUtil.getInteger(list.size());
    	return list.get(i);
    }
}