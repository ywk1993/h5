package com.muzhi.model.vo;

import com.muzhi.model.Food;

/**
* @author ykw
* @version 创建时间：2018年3月27日 下午8:40:10
*/
public class RoleVO {
		/** 角色id */
		private Integer id;
		/** 角色名 */
		private String name;
		/** 角色进入时的位置 */
		private Integer seatNumber;
		/** 角色头顶需要的食物 */
		private Food food;
		/** 角色类型 */
		private Integer roleType;
		/**买卖类型*/
		private Integer shopType;

		/**
		 * 
		 */
		
		public RoleVO() {
			super();
		}

		public RoleVO(Integer id, String name, Integer seatNumber, Food food, Integer roleType) {
			super();
			this.id = id;
			this.name = name;
			this.seatNumber = seatNumber;
			this.food = food;
			this.roleType = roleType;
		}

		public Integer getShopType() {
			return shopType;
		}

		public void setShopType(Integer shopType) {
			this.shopType = shopType;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getSeatNumber() {
			return seatNumber;
		}

		public void setSeatNumber(Integer seatNumber) {
			this.seatNumber = seatNumber;
		}

		public Food getFood() {
			return food;
		}

		public void setFood(Food food) {
			this.food = food;
		}

		public Integer getRoleType() {
			return roleType;
		}

		public void setRoleType(Integer roleType) {
			this.roleType = roleType;
		}

		@Override
		public String toString() {
			return "RoleBO [id=" + id + ", name=" + name + ", seatNumber=" + seatNumber + ", food=" + food + ", roleType="
					+ roleType + "]";
		}

	}

