package com.muzhi.model;

import java.io.Serializable;

import com.muzhi.model.vo.MenuAll;
import com.muzhi.model.vo.StaffAll;

/**
 * 
 * @author Yuwk
 *
 * 2017年11月13日
 */
public class Result implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 错误码 */
    private Integer code;

    /** 提示信息 */
    private String ErrMsg;

    /** 具体的内容 */
    private Object data;
    
    /** 额外需要刷新的基础数据 */
    private Fridge fridge;
    
    private Restaurant restaurant;
    
    private MenuAll menuAll;
    
    private StaffAll staff;
    
    private User user;
    
    private RefreshManager refreshManager;
    
    private Inventory Inventory;
    
  /*  private List<CurrencyType> currencyTypes;*/


	public Inventory getInventory() {
		return Inventory;
	}

	public void setInventory(Inventory inventory) {
		Inventory = inventory;
	}

	public Result(Integer code, String errMsg, Object data,Fridge fridge,User user/*,List<CurrencyType> currencyTypes*/) {
		super();
		this.code = code;
		ErrMsg = errMsg;
		this.data = data;
		this.fridge = fridge;
		this.user = user;
		/*this.currencyTypes =currencyTypes;*/
	}
    
	public RefreshManager getRefreshManager() {
		return refreshManager;
	}

	public void setRefreshManager(RefreshManager refreshManager) {
		this.refreshManager = refreshManager;
	}



	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public Fridge getFridge() {
		return fridge;
	}


	public void setFridge(Fridge fridge) {
		this.fridge = fridge;
	}


	public Result() {
		super();
	}

    
	/*public List<CurrencyType> getCurrencyTypes() {
		return currencyTypes;
	}*/

	public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

	public String getErrMsg() {
		return ErrMsg;
	}

	public void setErrMsg(String errMsg) {
		ErrMsg = errMsg;
	}


	@Override
	public String toString() {
		return "Result [code=" + code + ", ErrMsg=" + ErrMsg + ", data=" + data /*+ ", currencyTypes="*/ /*+ currencyTypes*/
				+ "]";
	}


	public Restaurant getRestaurant() {
		return restaurant;
	}


	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}


	public MenuAll getMenuAll() {
		return menuAll;
	}


	public void setMenuAll(MenuAll menuAll) {
		this.menuAll = menuAll;
	}


	public StaffAll getStaff() {
		return staff;
	}


	public void setStaff(StaffAll staff) {
		this.staff = staff;
	}

}
