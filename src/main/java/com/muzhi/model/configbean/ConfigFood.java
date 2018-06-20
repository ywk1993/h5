package com.muzhi.model.configbean;

import com.google.gson.Gson;

/**
 * @author 
 */
public class ConfigFood  {


	/**
     * ??ID
     */
    private Integer foodid;

    /**
     * ????
     */
    private String foodname;

    /**
     * ????
     */
    private String foodlogo;

    /**
     * ????
     */
    private Integer foodlevel;

    /**
     * ????
     */
    private Integer foodtype;

    /**
     * ????
     */
    private Integer foodqualify;

    /**
     * ???????
     */
    private Integer maketime;

    /**
     * ????
     */
    private String metarial;

    /**
     * ????
     */
    private Integer strength;

    /**
     * ????
     */
    private Integer price;
    
    /**
     * 道具id
     * @return
     */
    private Integer propId;
    /**
     * 道具数量
     * @return
     */
    private Integer number;


    public Integer getPropId() {
		return propId;
	}

	public void setPropId(Integer propId) {
		this.propId = propId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getFoodid() {
        return foodid;
    }

    public void setFoodid(Integer foodid) {
        this.foodid = foodid;
    }

    public String getFoodname() {
        return foodname;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public String getFoodlogo() {
        return foodlogo;
    }

    public void setFoodlogo(String foodlogo) {
        this.foodlogo = foodlogo;
    }

    public Integer getFoodlevel() {
        return foodlevel;
    }

    public void setFoodlevel(Integer foodlevel) {
        this.foodlevel = foodlevel;
    }

    public Integer getFoodtype() {
        return foodtype;
    }

    public void setFoodtype(Integer foodtype) {
        this.foodtype = foodtype;
    }

    public Integer getFoodqualify() {
        return foodqualify;
    }

    public void setFoodqualify(Integer foodqualify) {
        this.foodqualify = foodqualify;
    }

    public Integer getMaketime() {
        return maketime;
    }

    public void setMaketime(Integer maketime) {
        this.maketime = maketime;
    }

    public String getMetarial() {
        return metarial;
    }

    public void setMetarial(String metarial) {
        this.metarial = metarial;
    }

    public Integer getStrength() {
        return strength;
    }

    public void setStrength(Integer strength) {
        this.strength = strength;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", foodid=").append(foodid);
        sb.append(", foodname=").append(foodname);
        sb.append(", foodlogo=").append(foodlogo);
        sb.append(", foodlevel=").append(foodlevel);
        sb.append(", foodtype=").append(foodtype);
        sb.append(", foodqualify=").append(foodqualify);
        sb.append(", maketime=").append(maketime);
        sb.append(", metarial=").append(metarial);
        sb.append(", strength=").append(strength);
        sb.append(", price=").append(price);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}