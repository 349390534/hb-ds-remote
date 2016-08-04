package db.test;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.howbuy.uaa.remote.common.ProidType;
import com.howbuy.uaa.remote.dto.simu.ChannelPageDto;
import com.howbuy.uaa.remote.persistence.ChannelPage;
import com.howbuy.uaa.remote.service.simu.ChannelPageService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:context/spring/applicationContext*.xml","classpath:context/spring/spring-mvc.xml"  })
public class DBTest {

	@Autowired
	private ChannelPageService channelPageService;
	
	
	@Test
	public void queryTest(){
		ChannelPageDto dto = new ChannelPageDto();
		dto.setBeginDate("2015-08-30");
		dto.setEndDate("2015-10-28");
		
		dto.setAuthor("09999");
		dto.setIds("");
		dto.setNewsType("031");
		dto.setSubtype("0311");
		dto.setPageId(ProidType.PROID_ZIXUN.getIndex() + "_all");
		dto.setProid(ProidType.PROID_ZIXUN.getIndex());
		
		ChannelPage result = channelPageService.selectChannelPageListSum(dto);
		
		System.out.println("pageid:" + result.getPageId() + " pv:" + result.getPv() + " uv:" + result.getUv());
	}

}
