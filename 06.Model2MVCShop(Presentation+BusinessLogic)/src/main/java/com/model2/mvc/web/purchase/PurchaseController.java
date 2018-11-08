package com.model2.mvc.web.purchase;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;


@Controller
public class PurchaseController {
	
	///Field
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	//setter Method 구현 않음
		
	public PurchaseController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}") :pageunit이 없으면 3으로 set한다
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	
	@RequestMapping("/addPurchaseView.do")
	public ModelAndView addPurchaseView(@ModelAttribute("purchase") Purchase purchase, @RequestParam("prod_no") String ProdNo ) throws Exception {

		System.out.println("/addPurchaseView.do");
		System.out.println("prodNo:"+ProdNo);
		purchase = purchaseService.getPurchase2(Integer.parseInt(ProdNo));
		System.out.println("purchase:"+purchase);
		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("purchase", purchase);
		modelAndView.setViewName("/purchase/addPurchaseView.jsp");
		
		return modelAndView;
	}
	
	/*@RequestMapping("/addPurchase.do")
	public String addPurchase( @ModelAttribute("purchase") Purchase purchase ) throws Exception {

		System.out.println("/addPurchase.do");
		//Business Logic
		purchaseService.addPurchase(purchase);
		
		return "forward:/purchase/addPurchase.jsp";
	}
	
	@RequestMapping("/getPurchase.do")
	public String getPurchase( @RequestParam("tranNo") int tranNo , Model model, HttpServletRequest request, HttpServletResponse response ) throws Exception {
		
		System.out.println("/getPurchase.do");
		//Business Logic
		Purchase purchase = purchaseService.getPurchase(tranNo);
		// Model 과 View 연결
		model.addAttribute("purchase", purchase);
		
		
		return "forward:/purchase/getPurchase.jsp";
	}
	
	@RequestMapping("/updatePurchaseView.do")
	public String updatePurchaseView( @RequestParam("tranNo") int tranNo , Model model ) throws Exception{

		System.out.println("/updatePurchaseView.do");
		//Business Logic
		Purchase purchase = purchaseService.getPurchase(tranNo);
		// Model 과 View 연결
		model.addAttribute("purchase", purchase);
		
		return "forward:/purchase/updatePurchaseView.jsp";
	}
	
	@RequestMapping("/updatePurchase.do")
	public String updatePurchase( @ModelAttribute("purchase") Purchase purchase , Model model, HttpServletRequest request ) throws Exception{

		System.out.println("/updatePurchase.do");
		//Business Logic
		purchaseService.updatePurchase(purchase);
		
		return "redirect:/getPurchase.do?tranNo="+purchase.getTranNo();
	}
	
	@RequestMapping("/updateTranCode.do")
	public String updateTranCode( @ModelAttribute("purchase") Purchase purchase , Model model, HttpServletRequest request ) throws Exception{

		System.out.println("/updateTranCode.do");
		//Business Logic
		purchase = purchaseService.getPurchase2(purchase.getPurchaseProd().getProdNo());
		purchase.setTranCode(purchase.getTranCode());
		purchaseService.updateTranCode(purchase);
		
		return "redirect:/listPurchase.do";
	}
	
	@RequestMapping("/updateTranCodeByProd.do")
	public String updateTranCodeByProd( @ModelAttribute("purchase") Purchase purchase , Model model, HttpServletRequest request ) throws Exception{

		System.out.println("/updateTranCode.do");
		//Business Logic
		purchase = purchaseService.getPurchase2(purchase.getPurchaseProd().getProdNo());
		purchase.setTranCode(purchase.getTranCode());
		purchaseService.updateTranCode(purchase);
		
		return "redirect:/listPurchase.do?menu=manage";
	}
	
	@RequestMapping("/listPurchase.do")
	public String listPurchase( @ModelAttribute("search") Search search , @ModelAttribute("purchase") Purchase purchase , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/listPurchase.do");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		String buyerId = purchase.getBuyer().getUserId();
		// Business logic 수행
		Map<String , Object> map=purchaseService.getPurchaseList(search, buyerId);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/purchase/listPurchase.jsp";
	}*/
}