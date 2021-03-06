package com.era.serviceImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.era.dao.BaseDAO;
import com.era.orm.Activities;
import com.era.orm.Comment;
import com.era.orm.Issue;
import com.era.orm.LotteryDraw;
import com.era.orm.Recommend;
import com.era.orm.RecommendType;
import com.era.orm.Retrieve;
import com.era.orm.Seller;
import com.era.orm.Tock;
import com.era.orm.Winning;
import com.era.service.LotteryDrawService;
import com.era.util.LoginUser;

public class LotteryDrawServiceImpl implements LotteryDrawService{

	private BaseDAO dao;
	
	@Override
	public boolean addLotteryDraw(LotteryDraw draw) 
	{
		boolean flag = false;
		try {
			dao.saveOrUpdate(draw);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public List<LotteryDraw> selMyLotteryDraw(String iphone, String designated,String pageNo,String pageNum) 
	{
		List<LotteryDraw> list = null;
		String hql="from LotteryDraw where 1 = 1";
		if(iphone == null || iphone.equals(""))
		{
			
		}
		else
		{
			hql += " and iphone = '"+iphone+"'";
		}
		if(designated == null || designated.equals(""))
		{
			
		}
		else
		{
			hql += " and designated = '"+designated+"'";
		}
		if(pageNo == null || pageNo.equals("") || pageNum == null || pageNum.equals("")) 
		{
			list = dao.query(hql);
		}
		else
		{
			list = dao.query(hql,Integer.valueOf(pageNo), Integer.valueOf(pageNum));
		}
		return list;
	}
	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}
	@Override
	public List<Activities> selActivities(String yz,String lack,String pageNo, String pageNum) {
		List<Activities> list = null;
		if(yz == "1")
		{
			list = dao.query("from Activities where lucky = 1");
		}
		else
		{
			if(pageNo == null || pageNo.equals("") || pageNum == null || pageNum.equals("")) 
			{
				list = dao.query("from Activities");
			}
			else
			{
				list = dao.query("from Activities",Integer.valueOf(pageNo), Integer.valueOf(pageNum));
			}
		}
		return list;
	}
	@Override
	public List<Issue> selIssue(String iphone,String pageNo, String pageNum) {
		List<Issue> list = null;
		List<Issue> listIssue = new ArrayList<Issue>();
		if(pageNo == null || pageNo.equals("") || pageNum == null || pageNum.equals("")) 
		{
			list = dao.query("from Issue");
		}
		else
		{
			list = dao.query("from Issue",Integer.valueOf(pageNo), Integer.valueOf(pageNum));
		}
		for (int i = 0; i < list.size(); i++) 
		{
			Issue issue = list.get(i);
			Issue issueOne = new Issue();
			List<LotteryDraw> listDraw = dao.query("from LotteryDraw where iphone = '"+iphone+"' and designated = '"+issue.getTitle()+"'");
			if(listDraw.size()>0)
			{
				issueOne.setTitle(issue.getTitle());
				issueOne.setId(issue.getId());
				issueOne.setListDraw(listDraw);
				listIssue.add(issueOne);
			}
		}
		return listIssue;
	}
	@Override
	public List<Winning> selWinning(String sms, String pageNo, String pageNum) {
		List<Winning> list = null;
		String hql="from Winning where 1 = 1";
		if(sms == null || sms.equals(""))
		{
			
		}
		else
		{
			hql += " and iphone = '"+sms+"'";
		}
		if(pageNo == null || pageNo.equals("") || pageNum == null || pageNum.equals("")) 
		{
			list = dao.query(hql);
		}
		else
		{
			list = dao.query(hql,Integer.valueOf(pageNo), Integer.valueOf(pageNum));
		}
		return list;
	}
	@Override
	public int setMyLackLotter(String iphone)
	{
		URL url;
		String newTime = "";
		try {
			url = new URL("http://www.bjtime.cn");
			 URLConnection uc = url.openConnection();// 生成连接对象
		        uc.connect(); // 发出连接
		        long ld = uc.getDate(); // 取得网站日期时间
		        Date date = new Date(ld); // 转换为标准时间对象
		        java.text.DateFormat format1 = new java.text.SimpleDateFormat("yyyy-MM-dd");
				newTime = format1.format(date);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}// 取得资源对象
		catch (IOException e) {
			e.printStackTrace();
		}
		int lack = dao.countBySql("select count(*) from LotteryDraw where iphone ='"+iphone+"' and addtime = '"+newTime+"'");
		return lack;
	}
	@Override
	public Issue selIssueId() {
		List<Issue> listIssue = dao.query("from Issue where current = 1");
		Issue issue = null;
		if(listIssue.size()>0)
		{
			issue = listIssue.get(0);
		}
		return issue;
	}
	@Override
	public boolean addTock(Tock tock) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(tock);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public List<Seller> selSellerTui()
	{
		List<Seller> list = dao.query("from Seller where imgesId = 1");
		return list;
	}
	@Override
	public void addSeller(Seller seller) 
	{
		try {
			dao.saveOrUpdate(seller);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Recommend> selRecommend(String cityId,String isIos, String ismend, String typeId,
			String pageNo, String pageNum) {
		List<Recommend> list = null;
		String hql="from Recommend where 1 = 1";
		if(isIos == null || isIos.equals(""))
		{
			
		}
		else
		{
			hql += " and isIos = "+isIos;
		}
		if(cityId == null || cityId.equals(""))
		{
			
		}
		else
		{
			hql += " and cityId = "+cityId;
		}
		if(ismend == null || ismend.equals(""))
		{
			
		}
		else
		{
			hql += " and ismend = "+ismend;
		}
		if(typeId == null || typeId.equals(""))
		{
			
		}
		else
		{
			hql += " and typeId = "+typeId;
		}
		if(pageNo == null || pageNo.equals("") || pageNum == null || pageNum.equals("")) 
		{
			list = dao.query(hql);
		}
		else
		{
			list = dao.query(hql,Integer.valueOf(pageNo), Integer.valueOf(pageNum));
		}
		return list;
	}
	@Override
	public List<Retrieve> selRetrievePassWord(String parameter, String parameter2) {
		List<Retrieve> list = dao.query("from Retrieve where iphone = '"+parameter+"' and retrieve = '"+parameter2+"' and addtime < timedao");
		return list;
	}
	@Override
	public boolean addRetrieve(Retrieve retrieve) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(retrieve);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean updaePassWord(String parameter, String md5)
	{
		try {
			int i = dao.update("update User set passWord = '"+md5+"' where email = '"+parameter+"'");
			if(i>0)
			{
				return true;
			}
			else
			{
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}
	@Override
	public List<RecommendType> selRecommendType(String hot) 
	{
		List<RecommendType> list = dao.query("from RecommendType where hot = "+hot);
		return list;
	}
	@Override
	public int selTockWY(String parameter) {
		int count = dao.countBySql("select count(*) from Tock where tockId = '"+parameter+"'");
		return count;
	}
	@Override
	public int numberOfficial(LoginUser lu,String title,String comment,int pre) {
		String hql = "select count(*) from Activities where personal = "+pre;
		if(lu.getRole() == 4){
			hql += " and sellId = "+lu.getId();
		}
		if(title == null ||title.equals(""))
		{
			
		}
		else
		{
			hql +=" and title like '%"+title+"%'";
		}
		if(comment == null ||comment.equals(""))
		{
			
		}
		else
		{
			hql +=" and comment like '%"+comment+"%'";
		}
		int number = dao.countQuery(hql);
		return number;
	}
	
	@Override
	public List<Activities> selOfficial(LoginUser lu,String title,String comment,int pre,String page, String rows)
	{
		String hql = "from Activities where personal = "+pre;
		if(lu.getRole() == 4){
			hql += " and sellId = "+lu.getId();
		}
		if(null != title && !title.equals(""))
		{
			hql +=" and title like '%"+title+"%'";
		}
		if(null != comment && !comment.equals(""))
		{
			hql +=" and comment like '%"+comment+"%'";
		}
		List<Activities> list = dao.query(hql,Integer.valueOf(page),Integer.valueOf(rows));
		return list;
	}
	
	@Override
	public boolean delOfficial(int parseInt) {
		boolean flag = false;
		try {
			dao.delById(Activities.class, parseInt);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	@Override
	public boolean addActivities(Activities act) {
		boolean flag = false;
		try {
			dao.saveOrUpdate(act);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public boolean addfdasfdsaf(Activities act) {
		// TODO Auto-generated method stub
		boolean flag = false;
		try {
			dao.saveOrUpdate(act);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	@Override
	public List<Winning> selLerLottery(String iphone, String comment,
			String page, String rows) {
		String hql = " from Winning where 1 = 1 and official = 2";
		if(iphone == null ||iphone.equals(""))
		{
			
		}
		else
		{
			hql+=" and iphone like '%"+iphone+"%'";
		}
		if(comment == null || comment.equals(""))
		{
			
		}
		else
		{
			hql +=" and comment like '%"+comment+"%'";
		}
		List<Winning> list = dao.query(hql,Integer.valueOf(page),Integer.valueOf(rows));
		return list;
	}
	@Override
	public int numberWinning(String iphone, String comment) {
		String hql = "select count(*) from Winning where 1 = 1 and official = 2";
		if(iphone == null ||iphone.equals(""))
		{
			
		}
		else
		{
			hql+=" and iphone like '%"+iphone+"%'";
		}
		if(comment == null || comment.equals(""))
		{
			
		}
		else
		{
			hql +=" and comment like '%"+comment+"%'";
		}
		int number = dao.countQuery(hql);
		return number;
	}
	@Override
	public boolean delLerLottery(int parseInt) {
		boolean flag = false;
		try {
			dao.delById(Winning.class, parseInt);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}

}
