package com.era.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.era.orm.Collect;
import com.era.orm.Comment;
import com.era.service.CollectService;
import com.era.service.CommentService;
import com.era.util.BaseUtils;
import com.era.util.alertInFo;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CommentAction extends ActionSupport implements
		ModelDriven<Comment>, ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HttpServletRequest request;
	CommentService commentService;
	private List<Comment> list;
	private int pagenum;
	private int pagesum;
	private int pagecount = 1;
	private int pagesize = 1;
	private int pageCount = 1;
	Comment model = new Comment();
	private CollectService collectService;
	private Map<String,Object> map = new HashMap<String, Object>();
	private String result;
	
	private String rows; 
	private String page;

	/**
	 * 查询所有评论
	 * 
	 * @return
	 */
	public String selComment()
	{
		list=commentService.selComment(request.getParameter("name"),Integer.valueOf(page),Integer.valueOf(rows));
   	 	int total=commentService.numberComment(request.getParameter("name"));
   	 	map.put("total", total);
   	 	map.put("rows", list);
		return SUCCESS;
	}

	/**
	 * 删除评论
	 * 
	 * @return
	 * @throws IOException
	 */
	public String delComment() throws IOException 
	{
		String items=request.getParameter("items");
    	String[] ids=items.split(",");
    	for (int i = 0; i < ids.length; i++) { 
    		commentService.delComment(Integer.parseInt(ids[i])); 
        } 
		return null;
	}

	/**
	 * TODO 查询商家评论
	 */
	public void getCommentList() {
		String sellerId_str = request.getParameter("sellerId");
		String pageNo_str = request.getParameter("pageNo");
		String pageNum_str = request.getParameter("pageNum");
		String userId_str = request.getParameter("userId");
		String type = request.getParameter("type");

		if (sellerId_str != null) {
			int sellerId = Integer.parseInt(sellerId_str);
			int pageNo = pageNo_str != null ? Integer.parseInt(pageNo_str) : 1;
			int pageNum = pageNum_str != null ? Integer.parseInt(pageNum_str) : 3;
			
			System.out.println("---------getCommentList---------pageNo------------>"+pageNo+"<--------------");
			System.out.println("---------getCommentList---------pageNum------------>"+pageNum+"<--------------");
			String collectNo = "";
			if(userId_str!=null){
				Collect collect = collectService.repeatCollect(Integer.parseInt(userId_str),sellerId);
//				System.out.println("---------getCommentList---------collect.getId()------------>"+collect.getId()+"<--------------");
				if(collect!=null){
					collectNo = collect.getId()+"";
				}else{
					collectNo = "-2";
				}
			}else{
				collectNo = "-2";
			}
			
			System.out.println("---------getCommentList---------collectNo------------>"+collectNo+"<--------------");

			// 统计当前对应商家总的评论条数
			int number = commentService.countComment(sellerId);
			double pageSize = Math.ceil((double) number / pageNum);

			List<Comment> list = commentService.getCommentInfo(type,sellerId,pageNo, pageNum);
			
			if (number > 0 && list != null && list.size() > 0) {
				JSONArray array = new JSONArray();
				for(Comment c : list){
					JSONObject json = new JSONObject();
					json.put("id", c.getId());
					json.put("addtime", c.getAddtime());
					json.put("conent", c.getConent());
					json.put("level", c.getLevel());
					json.put("nickName", c.getNickName());
					array.add(json);
				}
				System.out.println(array.toString());
				result = "{\"responseCode\":\"" + 0 + "\",\"countNum\":\""+ number + "\",\"results\":" + array.toString() + ",\"collectNo\":\"" + collectNo + "\"}";
			} else {
				if((double)pageNo > pageSize && number > 0){
					result = "{\"responseCode\":\"" + -1 + "\",\"collectNo\":\"" + collectNo + "\"}";	
				}else{
					result = "{\"responseCode\":\"" + -2 + "\",\"collectNo\":\"" + collectNo + "\"}";
				}
			}
		} else {
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}

	/**
	 * TODO 查询商家评论条数
	 */
	public void countComment() {
		int sellerId = Integer.parseInt(request.getParameter("sellerId"));
		System.out.println("----sellerId------------->" + sellerId);
		if (sellerId > 0) {
			int number = commentService.countComment(sellerId);
			if (number > 0) {
				JSONArray array = JSONArray.fromObject(number);
				result = "{\"responseCode\":\"" + 0 + "\",\"results\":"+ array.toString() + "}";
			} else {
				result = "{\"responseCode\":\"" + 1 + "\"}";
			}
		}else {
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}

	/**
	 * TODO 添加评论
	 */
	public void putComment() {
		String sellerId_str = request.getParameter("sellerId");
		String level_str = request.getParameter("level");
		String content = request.getParameter("content");//评论内容
		String user = request.getParameter("user");
		
		user = user != null ? user : "游客";//对应用户
		
		 System.out.println("---putComment---sellerId_str---->"+sellerId_str+"<--------------");
		 System.out.println("---putComment---level_str---->"+level_str+"<--------------");
		 System.out.println("---putComment---content---->"+content+"<--------------");
		 System.out.println("---putComment---user---->"+user+"<--------------");

		if (sellerId_str != null && level_str != null && content != null && user != null) {
			boolean torf_content = BaseUtils.isChinaese(content);
			if (torf_content) {
				try {
					// 用于IOS客户端传递过来的用户名
					 content = new String(content.getBytes("ISO-8859-1"),"UTF-8");
					 System.out.println("---isChinaese---content--IOS-->"+content+"<--------------");
					// 用于IE网页传递过来的用户名
//					content = new String(content.getBytes("ISO-8859-1"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				content = content;
			}
			System.out.println("---isChinaese---content--IE-->"+content+"<--------------");
			
			boolean torf_user = BaseUtils.isChinaese(user);
			if (torf_user) {
				try {
					// 用于IOS客户端传递过来的用户名
					 user = new String(user.getBytes("ISO-8859-1"),"UTF-8");
					 System.out.println("---isChinaese---user--IOS-->"+user+"<--------------");
					// 用于网页传递过来的用户名
//					user = new String(user.getBytes("ISO-8859-1"));
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				user = user;
			}
			System.out.println("---isChinaese---user--IE-->"+user+"<--------------");
			
			int sellerId = Integer.parseInt(sellerId_str);
			double level = Double.parseDouble(level_str);
			
			model.setConent(content);
			model.setAddtime(BaseUtils.getNowStringDateTime(new Date()));
			model.setSellerId(sellerId);
			model.setUser(user);
			model.setLevel(level);
			model.setType(Integer.valueOf(request.getParameter("type")));
			boolean flag = commentService.putComment(model);
			if (flag) {
				result = "{\"responseCode\":\"" + 0 + "\"}";
			} else {
				result = "{\"responseCode\":\"" + 1 + "\"}";
			}
		} else {
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}
		BaseUtils.responseInfo(result);
	}

	/**
	 * TODO 商家查询评论
	 */
	public void getComment() {
		String sellerId_str = request.getParameter("sellerId");
		String pageNo_str = request.getParameter("pageNo");
		String pageNum_str = request.getParameter("pageNum");
		int pageNo = pageNo_str != null ? Integer.parseInt(pageNo_str) : 1;
		int pageNum = pageNum_str != null ? Integer.parseInt(pageNum_str)
				: BaseUtils.DEFAULT_PAGENUM;
		if (sellerId_str != null) {
			int sellerId = Integer.parseInt(sellerId_str);
			if (sellerId > 0 && pageNo > 0 && pageNum > 0) {
				List<Comment> list = commentService.getComment(sellerId,pageNo, pageNum);
				if (list != null && list.size() > 0) {
					JSONArray array = JSONArray.fromObject(list);
					result = "{\"responseCode\":\"" + 0 + "\",\"results\":"
							+ array.toString() + "}";
				} else {
					result = "{\"responseCode\":\"" + 1 + "\"}";
				}
			} else {
				result = "{\"responseCode\":\"" + 1 + "\"}";
			}
		} else {
			result = "{\"responseCode\":\"" + 1 + "\"}";
		}

		BaseUtils.responseInfo(result);
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public Comment getModel() {
		return model;
	}

	public CommentService getCommentService() {
		return commentService;
	}

	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public List<Comment> getList() {
		return list;
	}

	public void setList(List<Comment> list) {
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

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public CollectService getCollectService() {
		return collectService;
	}

	public void setCollectService(CollectService collectService) {
		this.collectService = collectService;
	}

	public void setModel(Comment model) {
		this.model = model;
	}

	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}

	public String getRows() {
		return rows;
	}

	public void setRows(String rows) {
		this.rows = rows;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
}
