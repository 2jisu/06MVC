package com.model2.mvc.service.prurchase.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.user.impl.UserServiceImpl;


/*
 *	FileName :  UserServiceTest.java
 * し JUnit4 (Test Framework) 引 Spring Framework 搭杯 Test( Unit Test)
 * し Spring 精 JUnit 4研 是廃 走据 適掘什研 搭背 什覗元 奄鋼 搭杯 砺什闘 坪球研 拙失 拝 呪 赤陥.
 * し @RunWith : Meta-data 研 搭廃 wiring(持失,DI) 拝 梓端 姥薄端 走舛
 * し @ContextConfiguration : Meta-data location 走舛
 * し @Test : 砺什闘 叔楳 社什 走舛
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {	"classpath:config/context-common.xml",
									"classpath:config/context-aspect.xml",
									"classpath:config/context-mybatis.xml",
									"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 戚遂 Wiring, Test 拝 instance DI
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;

	//@Test
	public void testAddPurchase() throws Exception {
		
		User user = new User();
		user.setUserId("testUserId");
		System.out.println(user);
		
		Product product = new Product();
		product.setProdNo(10042);
		System.out.println(product);
		
		Purchase purchase = new Purchase();
		purchase.setBuyer(user);
		purchase.setPurchaseProd(product);
		purchase.setPaymentOption("1");
		purchase.setReceiverName("戚走呪");
		purchase.setReceiverPhone("8532-888-888");
		purchase.setDivyAddr("勺督");
		purchase.setDivyRequest("dd");
		purchase.setDivyDate(null);
		
		purchaseService.addPurchase(purchase);
		
		purchase = purchaseService.getPurchase(10051);

		//==> console 溌昔
		System.out.println(purchase);
		
		//==> API 溌昔
		Assert.assertNotNull(purchase.getBuyer());
		Assert.assertNotNull(purchase.getPurchaseProd());
		Assert.assertEquals("1  " , purchase.getPaymentOption());
		Assert.assertEquals("戚走呪", purchase.getReceiverName());
		Assert.assertEquals("8532-888-888", purchase.getReceiverPhone());
		Assert.assertEquals("勺督", purchase.getDivyAddr());
		Assert.assertEquals("dd", purchase.getDivyRequest());
		Assert.assertNull(purchase.getDivyDate());
	}
	
	//@Test
	public void testGetPurchase() throws Exception {
		
		
		User user = new User();
		user.setUserId("testUserId");
		System.out.println(user);
		
		Product product = new Product();
		product.setProdNo(10042);
		System.out.println(product);
		
		Purchase purchase1 = new Purchase();		
		purchase1 = purchaseService.getPurchase(10051);
		System.out.println(purchase1);
		
		Purchase purchase2 = new Purchase();
		purchase2 = purchaseService.getPurchase2(10042);
		System.out.println(purchase2);
		
		//==> API 溌昔
		Assert.assertNotNull(purchase1.getBuyer());
		Assert.assertNotNull(purchase1.getPurchaseProd());
		Assert.assertEquals("1  " , purchase1.getPaymentOption());
		Assert.assertEquals("戚走呪", purchase1.getReceiverName());
		Assert.assertEquals("8532-888-888", purchase1.getReceiverPhone());
		Assert.assertEquals("勺督", purchase1.getDivyAddr());
		Assert.assertEquals("dd", purchase1.getDivyRequest());
		Assert.assertNull(purchase1.getDivyDate());
		
		
		Assert.assertNotNull(purchase2.getBuyer());
		Assert.assertNotNull(purchase2.getPurchaseProd());
		Assert.assertEquals("1  " , purchase2.getPaymentOption());
		Assert.assertEquals("戚走呪", purchase2.getReceiverName());
		Assert.assertEquals("8532-888-888", purchase2.getReceiverPhone());
		Assert.assertEquals("勺督", purchase2.getDivyAddr());
		Assert.assertEquals("dd", purchase2.getDivyRequest());
		Assert.assertNull(purchase2.getDivyDate());

		
	}
	
	//@Test
	 public void testUpdatePurchase() throws Exception{
		 
		Purchase purchase = purchaseService.getPurchase2(10042);
		Assert.assertNotNull(purchase);
		System.out.println("呪舛穿: "+purchase);
			
		Assert.assertNotNull(purchase.getBuyer());
		Assert.assertNotNull(purchase.getPurchaseProd());
		Assert.assertEquals("2  " , purchase.getPaymentOption());
		Assert.assertEquals("戚走呪しし", purchase.getReceiverName());
		Assert.assertEquals("8532-888-3333", purchase.getReceiverPhone());
		Assert.assertEquals("勺督姥", purchase.getDivyAddr());
		Assert.assertEquals("にに", purchase.getDivyRequest());
		Assert.assertNotNull(purchase.getDivyDate());
			
		purchase.setPaymentOption("2");
		purchase.setReceiverName("戚走呪しし");
		purchase.setReceiverPhone("8532-888-3333");
		purchase.setDivyAddr("勺督姥");
		purchase.setDivyRequest("にに");
		purchase.setDivyDate("20181123");
			
		purchaseService.updatePurchase(purchase);
			
		purchase = purchaseService.getPurchase2(10051);
		Assert.assertNotNull(purchase);
		System.out.println("呪舛板: "+purchase);
				
		Assert.assertNotNull(purchase.getBuyer());
		Assert.assertNotNull(purchase.getPurchaseProd());
		Assert.assertEquals("2  " , purchase.getPaymentOption());
		Assert.assertEquals("戚走呪しし", purchase.getReceiverName());
		Assert.assertEquals("8532-888-3333", purchase.getReceiverPhone());
		Assert.assertEquals("勺督姥", purchase.getDivyAddr());
		Assert.assertEquals("にに", purchase.getDivyRequest());
		Assert.assertNotNull(purchase.getDivyDate());	 	
	 }
	 
	 //@Test
	 public void testUpdateTranCode() throws Exception{
		 
		Purchase purchase = purchaseService.getPurchase(10051);
		Assert.assertNotNull(purchase);
		System.out.println("呪舛穿: "+purchase);
			
		Assert.assertNotNull(purchase.getBuyer());
		Assert.assertNotNull(purchase.getPurchaseProd());
		Assert.assertEquals("2  " , purchase.getPaymentOption());
		Assert.assertEquals("戚走呪しし", purchase.getReceiverName());
		Assert.assertEquals("8532-888-3333", purchase.getReceiverPhone());
		Assert.assertEquals("勺督姥", purchase.getDivyAddr());
		Assert.assertEquals("にに", purchase.getDivyRequest());
		Assert.assertEquals("2  ", purchase.getTranCode());
		Assert.assertNotNull(purchase.getDivyDate());
			
		purchase.setTranCode("3");
			
		purchaseService.updateTranCode(purchase);
			
		purchase = purchaseService.getPurchase2(10042);
		Assert.assertNotNull(purchase);
		System.out.println("呪舛板: "+purchase);
				
		Assert.assertEquals("3  ", purchase.getTranCode());
	}
	 
	 @Test
	 public void testGetPurchaseByUser() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	
	 	User user = new User();
	 	user.setUserId("testUserId");
	 	String buyerId = user.getUserId();
	 	System.out.println(buyerId);
	 	
	 	Map<String,Object> map = purchaseService.getPurchaseList(search, buyerId);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console 溌昔
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);	 	
	 }
	 
}