//package com.jingxi.test;
//
//import java.util.List;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import com.jingxi.mapper.TbItemMapper;
//import com.jingxi.model.TbItem;
//import com.jingxi.model.TbItemExample;
//
//public class TestPageHelper {
//
//	@SuppressWarnings("resource")
//	@Test
//	public void testPageHelper(){
//		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
//		TbItemMapper mapper = applicationContext.getBean(TbItemMapper.class);
//		TbItemExample example = new TbItemExample();
//		//分页处理
//		PageHelper.startPage(1, 10);
//		List<TbItem> list = mapper.selectByExample(example);
//		//取商品列表
//		for (TbItem tbItem : list){
//			System.out.println(tbItem.getTitle());
//		}
//		//取分页信息
//		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
//		long total = pageInfo.getTotal();
//		System.out.println("全部商品: " + total);
//	}
//
//}
