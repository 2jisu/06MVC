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
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {	"classpath:config/context-common.xml",
									"classpath:config/context-aspect.xml",
									"classpath:config/context-mybatis.xml",
									"classpath:config/context-transaction.xml" })
public class PurchaseServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
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
		purchase.setReceiverName("이지수");
		purchase.setReceiverPhone("8532-888-888");
		purchase.setDivyAddr("송파");
		purchase.setDivyRequest("dd");
		purchase.setDivyDate(null);
		
		purchaseService.addPurchase(purchase);
		
		purchase = purchaseService.getPurchase(10051);

		//==> console 확인
		System.out.println(purchase);
		
		//==> API 확인
		Assert.assertNotNull(purchase.getBuyer());
		Assert.assertNotNull(purchase.getPurchaseProd());
		Assert.assertEquals("1  " , purchase.getPaymentOption());
		Assert.assertEquals("이지수", purchase.getReceiverName());
		Assert.assertEquals("8532-888-888", purchase.getReceiverPhone());
		Assert.assertEquals("송파", purchase.getDivyAddr());
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
		
		//==> API 확인
		Assert.assertNotNull(purchase1.getBuyer());
		Assert.assertNotNull(purchase1.getPurchaseProd());
		Assert.assertEquals("1  " , purchase1.getPaymentOption());
		Assert.assertEquals("이지수", purchase1.getReceiverName());
		Assert.assertEquals("8532-888-888", purchase1.getReceiverPhone());
		Assert.assertEquals("송파", purchase1.getDivyAddr());
		Assert.assertEquals("dd", purchase1.getDivyRequest());
		Assert.assertNull(purchase1.getDivyDate());
		
		
		Assert.assertNotNull(purchase2.getBuyer());
		Assert.assertNotNull(purchase2.getPurchaseProd());
		Assert.assertEquals("1  " , purchase2.getPaymentOption());
		Assert.assertEquals("이지수", purchase2.getReceiverName());
		Assert.assertEquals("8532-888-888", purchase2.getReceiverPhone());
		Assert.assertEquals("송파", purchase2.getDivyAddr());
		Assert.assertEquals("dd", purchase2.getDivyRequest());
		Assert.assertNull(purchase2.getDivyDate());

		
	}
	
	//@Test
	 public void testUpdatePurchase() throws Exception{
		 
		Purchase purchase = purchaseService.getPurchase2(10042);
		Assert.assertNotNull(purchase);
		System.out.println("수정전: "+purchase);
			
		Assert.assertNotNull(purchase.getBuyer());
		Assert.assertNotNull(purchase.getPurchaseProd());
		Assert.assertEquals("2  " , purchase.getPaymentOption());
		Assert.assertEquals("이지수ㅇㅇ", purchase.getReceiverName());
		Assert.assertEquals("8532-888-3333", purchase.getReceiverPhone());
		Assert.assertEquals("송파구", purchase.getDivyAddr());
		Assert.assertEquals("ㅛㅛ", purchase.getDivyRequest());
		Assert.assertNotNull(purchase.getDivyDate());
			
		purchase.setPaymentOption("2");
		purchase.setReceiverName("이지수ㅇㅇ");
		purchase.setReceiverPhone("8532-888-3333");
		purchase.setDivyAddr("송파구");
		purchase.setDivyRequest("ㅛㅛ");
		purchase.setDivyDate("20181123");
			
		purchaseService.updatePurchase(purchase);
			
		purchase = purchaseService.getPurchase2(10051);
		Assert.assertNotNull(purchase);
		System.out.println("수정후: "+purchase);
				
		Assert.assertNotNull(purchase.getBuyer());
		Assert.assertNotNull(purchase.getPurchaseProd());
		Assert.assertEquals("2  " , purchase.getPaymentOption());
		Assert.assertEquals("이지수ㅇㅇ", purchase.getReceiverName());
		Assert.assertEquals("8532-888-3333", purchase.getReceiverPhone());
		Assert.assertEquals("송파구", purchase.getDivyAddr());
		Assert.assertEquals("ㅛㅛ", purchase.getDivyRequest());
		Assert.assertNotNull(purchase.getDivyDate());	 	
	 }
	 
	 //@Test
	 public void testUpdateTranCode() throws Exception{
		 
		Purchase purchase = purchaseService.getPurchase(10051);
		Assert.assertNotNull(purchase);
		System.out.println("수정전: "+purchase);
			
		Assert.assertNotNull(purchase.getBuyer());
		Assert.assertNotNull(purchase.getPurchaseProd());
		Assert.assertEquals("2  " , purchase.getPaymentOption());
		Assert.assertEquals("이지수ㅇㅇ", purchase.getReceiverName());
		Assert.assertEquals("8532-888-3333", purchase.getReceiverPhone());
		Assert.assertEquals("송파구", purchase.getDivyAddr());
		Assert.assertEquals("ㅛㅛ", purchase.getDivyRequest());
		Assert.assertEquals("2  ", purchase.getTranCode());
		Assert.assertNotNull(purchase.getDivyDate());
			
		purchase.setTranCode("3");
			
		purchaseService.updateTranCode(purchase);
			
		purchase = purchaseService.getPurchase2(10042);
		Assert.assertNotNull(purchase);
		System.out.println("수정후: "+purchase);
				
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
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);	 	
	 }
	 
}