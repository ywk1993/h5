package com.muzhi.model.configbean;

import com.google.gson.Gson;

/**
 * @author 
 */
public class ConfigSkill implements Comparable<ConfigSkill>{


	/**
     * 表格序号
     */
    private Integer id;

    /**
     * 菜品ID
     */
    private Integer foodid;

    /**
     * 熟练度等级（后端不读取的）
     */
    private Integer temp;

    /**
     * 熟练度需求
     */
    private Integer request;

    /**
     * 熟练度奖励类型
     */
    private Integer rewardtype;

    /**
     * 熟练度奖励
     */
    private String reward;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFoodid() {
        return foodid;
    }

    public void setFoodid(Integer foodid) {
        this.foodid = foodid;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getRequest() {
        return request;
    }

    public void setRequest(Integer request) {
        this.request = request;
    }

    public Integer getRewardtype() {
        return rewardtype;
    }

    public void setRewardtype(Integer rewardtype) {
        this.rewardtype = rewardtype;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", foodid=").append(foodid);
        sb.append(", temp=").append(temp);
        sb.append(", request=").append(request);
        sb.append(", rewardtype=").append(rewardtype);
        sb.append(", reward=").append(reward);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

	@Override
	public int compareTo(ConfigSkill ConfigSkill) {
		
		return this.request-ConfigSkill.getRequest();
	}
}