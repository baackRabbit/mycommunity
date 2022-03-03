package com.glodon.mycommunity;

import com.glodon.mycommunity.dao.AlphaDao;
import com.glodon.mycommunity.dao.AlphaMybatisDaoImpl;
import com.glodon.mycommunity.service.AlphaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
@ContextConfiguration(classes = MycommunityApplication.class)
class MycommunityApplicationTests implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	//获取ioc容器
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	//获取ioc容器中的bean
	@Test
	public void testApplicationContext(){
		//获取优先级高的bean
		AlphaDao alphaDao = applicationContext.getBean(AlphaDao.class);
		alphaDao.select();

		//获取某个指定的bean
		AlphaDao alphaDao1 = applicationContext.getBean("alphaMybatis",AlphaDao.class);
		AlphaDao alphaDao2 = applicationContext.getBean(AlphaMybatisDaoImpl.class);
		alphaDao1.select();
		alphaDao2.select();
	}

	//对bean的管理
	@Test
	public void testBeanManage(){
		AlphaService alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);

		alphaService = applicationContext.getBean(AlphaService.class);
		System.out.println(alphaService);
	}

	//利用ioc容器管理第三方库的bean
	@Test
	public void testDateFromatBean(){
		SimpleDateFormat simpleDateFormat = applicationContext.getBean(SimpleDateFormat.class);
		System.out.println(simpleDateFormat.format(new Date()));
	}

	@Autowired
	@Qualifier("alphaMybatis")
	AlphaDao alphaDao;

	@Autowired
	AlphaService alphaService;

	@Autowired
	SimpleDateFormat simpleDateFormat;

	//测试依赖注入
	@Test
	public void testDI(){
		alphaDao.select();
		System.out.println(alphaService);
		System.out.println(simpleDateFormat.format(new Date()));
	}
}
