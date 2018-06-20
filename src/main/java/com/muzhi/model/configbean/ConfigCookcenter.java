package com.muzhi.model.configbean;

import com.google.gson.Gson;

/**
 * @author 
 */
public class ConfigCookcenter  {
    /**
     * 等级
     */
    private Integer level;

    /**
     * 制作彩品等级
     */
    private Integer makelevel;

    /**
     * 研究力
     */
    private Integer power;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getMakelevel() {
        return makelevel;
    }

    public void setMakelevel(Integer makelevel) {
        this.makelevel = makelevel;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", level=").append(level);
        sb.append(", makelevel=").append(makelevel);
        sb.append(", power=").append(power);
        sb.append("]");
        return sb.toString();
    }

    public String toJson() {
        return new Gson().toJson(this);
    }
}