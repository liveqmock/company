package com.era.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Busmarset;
import com.era.orm.City;
import com.era.orm.Market;
import com.era.service.CityService;
import com.era.service.MarketService;
import com.era.util.BaseUtils;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MarketAction extends ActionSupport implements ModelDriven<Market>,
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	private MarketService marketService;
	private CityService cityService;
	private List<Object> list;
	private List<City> listCity;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	private String typeOne;
	private String result;

	Market model = new Market();

	/**
	 * 查询显示对应城市下面 所有市场类型列表
	 * 
	 */
	public void getMarketList() {
		try {
			int number = marketService.countMarket(Integer.valueOf(request.getParameter("cityId")));
			List<Market> list = marketService.getMarketList(Integer.valueOf(request.getParameter("cityId")),Integer.valueOf(request.getParameter("pageNo")),Integer.valueOf(request.getParameter("pageNum")));
			JSONArray array = JSONArray.fromObject(list);
			result = "{\"responseCode\":\"" + 0 + "\",\"countNum\":\""+ number + "\",\"results\":" + array.toString() + "}";
		} catch (Exception e) {
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
		
		
		
//		if (cityId_str != null) {
//			int cityId = Integer.parseInt(cityId_str);
//			int pageNo = pageNo_str != null ? Integer.parseInt(pageNo_str) : 1;
//			int pageNum = pageNum_str != null ? Integer.parseInt(pageNum_str) : 9;			
//			
//			System.out.println("----cityId---------------->" + cityId);
//			
			
//			
//			double pageSize = Math.ceil((double) number / pageNum);
//			
//			System.out.println("----number---------------->" + number);
//			
//			List<Market> list = marketService.getMarketList(cityId,pageNo,pageNum);
//			
//			System.out.println("----list---------------->" + list.size());
//			
//			if (list != null && list.size() > 0) {
//				
//				JSONArray array = JSONArray.fromObject(list);
//				
//				result = "{\"responseCode\":\"" + 0 + "\",\"countNum\":\""+ number + "\",\"results\":" + array.toString() + "}";
//				
//			} else {
//				if((double)pageNo > pageSize && number > 0 ){
//					result = "{\"responseCode\":\"" + -1 + "\"}";
//				}else{
//					result = "{\"responseCode\":\"" + -2 + "\"}";	
//				}
//			}
//		} else {
//			result = "{\"responseCode\":\"" + 1 + "\"}";
//		}
//		BaseUtils.responseInfo(result);
	}
	/**
	 * 通过市场名称查询市场
	 * @throws UnsupportedEncodingException 
	 */
	public void selMark() throws UnsupportedEncodingException
	{
		String markName = "";
		String pageNo = request.getParameter("pageNo");
		String pageNum = request.getParameter("pageNum");
		String cityId = request.getParameter("cityId");
		
		if(request.getParameter("markName") == null || request.getParameter("markName").equals(""))
		{
			
		}
		else
		{
			if(BaseUtils.isChinaese(request.getParameter("markName")))
			{
				markName = new String(request.getParameter("markName").getBytes("ISO-8859-1"),"UTF-8");
			}
			else
			{
				markName = request.getParameter("markName");
			}
		}
		List<Market> listMark = marketService.selLikeMark(markName,cityId,pageNo,pageNum);
		JSONArray array = JSONArray.fromObject(listMark);
		if(listMark.size()>0 || listMark != null)
		{
			result = "{\"responseCode\":\"" + 0 + "\",\"results\":" + array + "}";
		}
		else
		{
			result = "{\"responseCode\":\"" + 0 + "\",\"def\":\""+ 0 + "\",\"results\":" + array + "}";
		}
		BaseUtils.responseInfo(result);
	}
	/**
	 * 查询获取每个类型下面对应的注入商家是谁
	 */
	public void getMarketSeller(){
		try{
			String typeId_str = request.getParameter("typeId");
			if(typeId_str != null){
				Integer typeId = Integer.parseInt(typeId_str);
				Busmarset b = marketService.getBusMarketSet(typeId);
				if(b!=null){
					JSONArray array = JSONArray.fromObject(b);
					result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
				}else{
					result = "{\"responseCode\":\"" + -2 + "\"}";	
				}
			}else{
				result = "{\"responseCode\":\"" + 1 + "\"}";
			}
		}catch (Exception e) {
			// TODO: handle exception
			result = "{\"responseCode\":\"" + -2 + "\"}";	
		}
		BaseUtils.responseInfo(result);
	}

	/**
	 * 查询市场类型
	 * 
	 * @return
	 */
	public String selMarket() {
		if (request.getSession().getAttribute("userid") == null) 
		{
			return "error";
		}
		pagecount = marketService.numberMarket(request.getParameter("type"));
		typeOne = request.getParameter("type");
		if (pagecount < 15) {
			pageCount = 1;
		} else {
			pageCount = pagecount / 15;
			int i = pagecount % 15;
			if (i > 0) {
				pageCount += 1;
			}
		}
		if (pagenum < 1) {
			pagenum = 1;
		}
		if (pagenum > pagecount) {
			if (pagecount == 0) {

			} else {
				pagenum = pagecount;
			}
		}
		list = marketService.selMarket(request.getParameter("type"), pagenum,15);
		listCity = cityService.getCityAll();
		return SUCCESS;
	}

	/**
	 * 删除市场类型
	 * 
	 * @return
	 * @throws IOException
	 */
	public String delMarket() throws IOException {
		if (request.getSession().getAttribute("userid") == null) {
			return "error";
		}
		boolean bool = marketService.delMarket(Integer.valueOf(request
				.getParameter("id")));
		if (bool) {
			alertInFo.alertReturn("删除成功");
			selMarket();
		} else {
			alertInFo.alertReturn("删除异常");
		}
		return SUCCESS;
	}

	/**
	 * 跳转到添加页面
	 * 
	 * @return
	 */
	public String addMarketPage() {
		if (request.getSession().getAttribute("userid") == null) {
			return "error";
		}
		listCity = cityService.getCityAll();
		return SUCCESS;
	}

	/**
	 * 添加市场类型
	 * 
	 * @return
	 * @throws IOException
	 */
	public String addMarket() throws IOException {
		if (request.getSession().getAttribute("userid") == null) {
			return "error";
		}
		model.setCityId(Integer.valueOf(request.getParameter("city")));
		model.setSort(Integer.valueOf(request.getParameter("sort")));
		model.setType(request.getParameter("type"));
		boolean bool = marketService.addMarket(model);
		if (bool) {
			alertInFo.alertReturn("添加成功");
			selMarket();
		} else {
			alertInFo.alertReturn("添加异常");
		}
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Market getModel() {
		return model;
	}

	public MarketService getMarketService() {
		return marketService;
	}

	public void setMarketService(MarketService marketService) {
		this.marketService = marketService;
	}

	public List<Object> getList() {
		return list;
	}

	public void setList(List<Object> list) {
		this.list = list;
	}

	public int getPagenum() {
		return pagenum;
	}

	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}

	public int getPagesum() {
		return pagesum;
	}

	public void setPagesum(int pagesum) {
		this.pagesum = pagesum;
	}

	public int getPagecount() {
		return pagecount;
	}

	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public String getTypeOne() {
		return typeOne;
	}

	public void setTypeOne(String typeOne) {
		this.typeOne = typeOne;
	}

	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public List<City> getListCity() {
		return listCity;
	}

	public void setListCity(List<City> listCity) {
		this.listCity = listCity;
	}
}
