//package com.muzhi.model.vo;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import com.muzhi.model.CurrencyType;
//import com.muzhi.model.OrderFood;
//import com.muzhi.model.OrderReward;
//
//public class Order extends com.muzhi.model.Order{
//	public static Integer CHINESE = 1;
//	private static final Integer WEST = 2;
//	private static final Integer BARBECUE = 3;
//	private static final Integer DESSERT = 4;
//	private static final Integer DRINK = 5;
//	
//	private InventoryInfo menu;
//
//	private List<OrderReward> typeList;
//	
//
//	/**
//	 * 
//	 */
//	public Order() {
//		super();
//	}
//	
//	public static List<com.muzhi.model.vo.Order> getOrderVo(List<com.muzhi.model.Order> orders, List<OrderFood> orderFoodList) {
//		List<com.muzhi.model.vo.Order> orderList = new ArrayList<Order>();
//		for (com.muzhi.model.Order order : orders) {
//			List<OrderFood> orderFoodVOList = new ArrayList<OrderFood>(); 
//			for (OrderFood orderFood : orderFoodList ) {
//				if (order.getUuid().equals(orderFood.getUuid())) {
//					orderFoodVOList.add(orderFood);
//				}
//			}
//			Order orderVO = new Order(order , orderFoodVOList);
//			orderList.add(orderVO);
//		}
//		return orderList;
//	}
//	
//	/**
//	 * @param menu
//	 */
//	public Order(com.muzhi.model.Order order, List<OrderFood> orderFoodList) {
//		super(order.getOrderLevel(), order.getOrderType(), order.getTypeNum(), order.getRequestNum(), order.getQualifyRound(),
//				order.getGoldReward(), order.getCurencyNum(), order.getCurrencyRound(), order.getStartTime(), order.getEndTime(), 
//				order.getState(), order.getUuid(), order.getStatus());
//		this.setId(order.getId());
//		this.setOrderId(order.getOrderId());
//		List<InventoryVo> inventoryList = new ArrayList<>();
//		for (OrderFood orderFood : orderFoodList) {
//			
//			InventoryVo foodN = new InventoryVo();
//			foodN.setTypeList(orderFood.getMetarial());
//			foodN.setFoodId(orderFood.getFoodid());
//			foodN.setFoodLevel(orderFood.getFoodlevel());
//			foodN.setFoodQualify(orderFood.getFoodqualify());
//			foodN.setFoodType(orderFood.getFoodtype());
//			foodN.setFoodNum(order.getRequestNum());
//			inventoryList.add(foodN);
//		}
//		InventoryInfo menu = new InventoryInfo(inventoryList, 0, 0);
//		this.setMenu(menu);
//		// 组装数据
//	}
//	public List<CurrencyType> getMetarial(String str) {
//		List<CurrencyType> arrayList = new ArrayList<CurrencyType>();
//
//		String[] split = str.split(",");
//		for (int i = 0; i < split.length; i++) {
//			if (Integer.parseInt(split[i]) != 0) {
//				arrayList.add(new CurrencyType(i + 1, Integer.parseInt(split[i])));
//			}
//		}
//
//		return arrayList;
//
//	}
//
//	public InventoryInfo getMenu() {
//		return menu;
//	}
//
//	public void setMenu(InventoryInfo menu) {
//		this.menu = menu;
//	}
//
//	public List<OrderReward> getTypeList() {
//		return typeList;
//	}
//
//	public void setTypeList(List<OrderReward> typeList) {
//		this.typeList = typeList;
//	}
//	
//}
