package db.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.howbuy.uaa.remote.common.ProidType;
import com.howbuy.uaa.remote.dto.simu.ChannelPageDto;
import com.howbuy.uaa.remote.persistence.ChannelPage;
import com.howbuy.uaa.remote.service.CustLabelService;
import com.howbuy.uaa.remote.service.simu.ChannelPageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:context/spring/applicationContext*.xml","classpath:context/spring/spring-mvc.xml"  })
public class OracleTest {

	@Autowired
	private CustLabelService channelPageService;
	
	@Autowired@Qualifier("oraceSqlSessionTemplate")
	private SqlSessionTemplate sqlSessionTemplateOracle;
	
	
	@Test
	public void queryTest(){
		
		System.out.println(channelPageService.getMapping());
//		sqlSessionTemplateOracle.selectList("label_oracle.select_cust_label2");
//		System.out.println(channelPageService.query());
	}

}
