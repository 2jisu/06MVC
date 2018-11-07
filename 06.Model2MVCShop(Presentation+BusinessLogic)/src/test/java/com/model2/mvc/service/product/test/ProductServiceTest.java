package com.model2.mvc.service.product.test;

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
import com.model2.mvc.service.product.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {	"classpath:config/context-common.xml",
									"classpath:config/context-aspect.xml",
									"classpath:config/context-mybatis.xml",
									"classpath:config/context-transaction.xml" })
public class ProductServiceTest {
	
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	//@Test
	public void testAddProduct() throws Exception{
		
		Product product = new Product();
		product.setProdName("커피");
		product.setProdDetail("coffee");
		product.setManuDate("2018.10.30");
		product.setPrice(1000);
		product.setFileName(null);
		
		productService.addProduct(product);
		
		product = productService.getProduct(10045);
		
		System.out.println(product);
		
		Assert.assertEquals("커피", product.getProdName());
		Assert.assertEquals("coffee", product.getProdDetail());
		Assert.assertEquals("20181030", product.getManuDate());
		Assert.assertEquals(1000, product.getPrice());
		Assert.assertNull(product.getFileName());
		
	}
	
	//@Test
	public void testGetProduct() throws Exception{
		
		Product product = new Product();
		
		product = productService.getProduct(10045);
		
		System.out.println(product);
		
		Assert.assertEquals("커피", product.getProdName());
		Assert.assertEquals("coffee", product.getProdDetail());
		Assert.assertEquals("20181030", product.getManuDate());
		Assert.assertEquals(1000, product.getPrice());
		Assert.assertNull(product.getFileName());
	}
	
	//@Test
	public void testUpdateProduct() throws Exception{
		
		Product product = productService.getProduct(10045);
		Assert.assertNotNull(product);
		System.out.println("수정전: "+product);
		
		Assert.assertEquals("커피", product.getProdName());
		Assert.assertEquals("coffee", product.getProdDetail());
		Assert.assertEquals("20181030", product.getManuDate());
		Assert.assertEquals(1000, product.getPrice());
		Assert.assertNull(product.getFileName());
		
		product.setProdName("커피커피");
		product.setProdDetail("coffeeeee");
		product.setManuDate("20181010");
		product.setPrice(5000);
		product.setFileName("image");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10045);
		Assert.assertNotNull(product);
		System.out.println("수정후: "+product);
		
		Assert.assertEquals("커피커피", product.getProdName());
		Assert.assertEquals("coffeeeee", product.getProdDetail());
		Assert.assertEquals("20181010", product.getManuDate());
		Assert.assertEquals(5000, product.getPrice());
		Assert.assertEquals("image",product.getFileName());
	}
	
	//@Test
	public void testGetProductListAll() throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String,Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("=======================================");
		
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		map = productService.getProductList(search);
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(3, list.size());
		
		System.out.println(list);
		
		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
	}
	
	//@Test
	public void testGetProductListByProdName() throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("1");
		search.setSearchKeyword("%커피");
		Map<String,Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(2, list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("=======================================");
		
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("1");
		search.setSearchKeyword("커피");
		map = productService.getProductList(search);
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(1, list.size());
		
		System.out.println(list);
		
		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		
	}
	
	@Test
	public void testGetProductListByPrice() throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("2");
		search.setSearchKeyword("500");
		Map<String,Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(1, list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("=======================================");
		
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("2");
		search.setSearchKeyword("4500");
		map = productService.getProductList(search);
		
		list = (List<Object>)map.get("list");
		Assert.assertEquals(0, list.size());
		
		System.out.println(list);
		
		totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		
	}



}
