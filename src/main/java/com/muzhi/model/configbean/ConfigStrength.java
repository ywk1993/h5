package com.muzhi.model.configbean;

import com.google.gson.Gson;

/**
 * @author 
 */
public class ConfigStrength implements Comparable<ConfigStrength>{


	/**
     * ??
     */
    private Integer id;

    /**
     * ??ID
     */
    private Integer foodid;

    /**
     * ????
     */
    private Integer strength;

    /**
     * ????
     */
    private Integer crit;

    /**
     * ????
     */
    private Integer addition;


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

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getCrit() {
        return crit;
    }

    public void setCrit(Integer crit) {
        this.crit = crit;
    }

    public Integer getAddition() {
        return addition;
    }

    public void setAddition(Integer addition) {
        this.addition = addition;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", foodid=").append(foodid);
        sb.append(", strength=").append(strength);
        sb.append(", crit=").append(crit);
        sb.append(", addition=").append(addition);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

	@Override
	public int compareTo(ConfigStrength configStrength) {
	     return this.strength-configStrength.getStrength();
	}
}