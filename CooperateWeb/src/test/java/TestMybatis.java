import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cooperate.fly.service.model.ModelDesign;

@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "classpath*:conf/database/applicationContext.xml" })
public class TestMybatis extends AbstractJUnit4SpringContextTests{

	private static Logger logger=Logger.getLogger(TestMybatis.class);
	@Autowired
	private ModelDesign mDesign;
		
	@Test
	public void test1(){
			
		mDesign.createNoneLeafCatalogNode("飞行器主模型", 0);
		mDesign.createNoneLeafCatalogNode("(分类)型号A", 1);
		mDesign.createNoneLeafCatalogNode("(分类)型号B", 2);
	}
}
