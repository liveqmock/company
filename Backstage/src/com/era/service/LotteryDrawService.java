package com.era.service;

import java.util.List;

import com.era.orm.RecommendType;
import com.era.orm.Retrieve;
import com.era.orm.Recommend;
import com.era.orm.Seller;
import com.era.orm.Activities;
import com.era.orm.Issue;
import com.era.orm.LotteryDraw;
import com.era.orm.Tock;
import com.era.orm.Winning;
import com.era.util.LoginUser;

public interface LotteryDrawService 
{
	/**
	 * 添加抽奖号码
	 * @return
	 */
	public boolean addLotteryDraw(LotteryDraw draw);
	
	/**
	 * 查询我的抽奖号
	 * @param iphone
	 * @param designated
	 * @return
	 */
	public List<LotteryDraw> selMyLotteryDraw(String iphone,String designated,String pageNo,String pageNum);
	/**
	 * 查询活动
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public List<Activities> selActivities(String yz,String lack,String pageNo,String pageNum);
	/**
	 * 期号
	 * @return
	 */
	public List<Issue> selIssue(String iphone,String pageNo,String pageNum);
	/**
	 * 我的中奖信息
	 * @param sms
	 * @param pageNo
	 * @param num
	 * @return
	 */
	public List<Winning> selWinning(String sms,String pageNo,String pageNum);
	/**
	 * 我今天还有几次抽奖机会
	 * @param iphone
	 * @return
	 */
	public int setMyLackLotter(String iphone);
	/**
	 * 查询是否当前期
	 * @return
	 */
	public Issue selIssueId();
	/**
	 * 添加推送的人
	 * @param tock
	 * @return
	 */
	public boolean addTock(Tock tock);
	/**
	 * 安卓推送
	 * @return
	 */
	public List<Seller> selSellerTui();
	/**
	 * 更新商户
	 * @param seller
	 */
	public void addSeller(Seller seller);
	/**
	 * 查询应用汇
	 * @param isIos
	 * @param ismend
	 * @param pageNo
	 * @param pageNum
	 * @return
	 */
	public List<Recommend> selRecommend(String cityId,String isIos,String ismend,String typeId,String pageNo,String pageNum);
	/**
	 * 查看应用汇分类
	 * @return
	 */
	public List<RecommendType> selRecommendType(String hot);
	/**
	 * 找回密码
	 * @param parameter
	 * @param parameter2
	 * @return
	 */
	public List<Retrieve> selRetrievePassWord(String parameter, String parameter2);
	/**
	 * 添加找回密码验证信息
	 * @param retrieve
	 * @return
	 */
	public boolean addRetrieve(Retrieve retrieve);
	/**
	 * 修改密码
	 * @param parameter
	 * @param md5
	 * @return
	 */
	public boolean updaePassWord(String parameter, String md5);

	public int selTockWY(String parameter);

	public int numberOfficial(LoginUser lu, String title,String comment,int pre);

	public List<Activities> selOfficial(LoginUser lu, String title,String comment,int pre,String page, String rows);

	public boolean delOfficial(int parseInt);

	public boolean addActivities(Activities act);

	public boolean addfdasfdsaf(Activities act);

	public List<Winning> selLerLottery(String parameter, String parameter2,
			String page, String rows);

	public int numberWinning(String parameter, String parameter2);

	public boolean delLerLottery(int parseInt); 
}
