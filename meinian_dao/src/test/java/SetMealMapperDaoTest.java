import com.atguigu.dao.SetMealDao;
import com.atguigu.pojo.SetMeal;
import com.atguigu.pojo.TravelGroup;
import com.atguigu.pojo.TravelItem;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author 孔德成 @Slogan 致敬大师，致敬未来的你
 *
 * @date 2021/7/26
 */
@ContextConfiguration(locations = "classpath:applicationContext-dao.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class SetMealMapperDaoTest {

  @Autowired SetMealDao setMealDao;

  @Test
  public void test() {
    SetMeal setMeal = setMealDao.findById(5);
    System.out.println(setMeal.getImg());
    for (TravelGroup travelGroup : setMeal.getTravelGroups()) {
      System.out.println("--------");
      System.out.println(travelGroup.getName());
      for (TravelItem travelItem : travelGroup.getTravelItems()) {
        System.out.println("\t" + travelItem.getName());
      }
    }
  }
}
