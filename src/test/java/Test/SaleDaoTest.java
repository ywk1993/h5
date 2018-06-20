package Test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.muzhi.dao.RestaurantDao;
import com.muzhi.model.Restaurant;

public class SaleDaoTest extends BaseTest{
	@Autowired
	RestaurantDao restaurantDao;
	
	@Test
	public void restaurantDao() {
		Restaurant restaurant = new Restaurant();
		restaurant.setId(100);
		restaurant.setCustomNum(1);
		restaurant.setLevel(1);
		restaurant.setArea(1);
		restaurant.setLoveLimit(1);
		restaurant.setMaxAsher(1);
		restaurant.setMaxChef(1);
		restaurantDao.insert(restaurant);
		
		Restaurant restaurantSelect = new Restaurant();
		restaurantSelect.setId(101);
		restaurantSelect.setCustomNum(1);
		restaurantSelect.setLevel(1);
		restaurantDao.insertSelective(restaurantSelect);
		
		restaurantDao.selectByPrimaryKey(100);
		
		restaurantDao.updateByPrimaryKeySelective(restaurantSelect);
		restaurantDao.updateByPrimaryKey(restaurant);
		
		restaurantDao.deleteByPrimaryKey(100);
		restaurantDao.deleteByPrimaryKey(101);
	}
	
}
