package com.marck.common.sms;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

//import common.util.web.Base;

/**
 * 
 * @项目名称:xiaotuan
 * @类名�?SecondSMS
 * @类描�?发�?短信;
 * @创建�?苗有�?
 * @创建时间:Sep 18, 20103:04:52 PM
 * @修改�?Administrator
 * @修改时间:Sep 18, 20103:04:52 PM
 * @修改备注:
 * @version
 */
public class SendSMS {
	
	private String encode = "GB2312";
	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	// 服务器请求地�?�?��加密);
	private String servicesRequestAddRess = "";
	// 登录的用户名(�?��加密);
	private String username = "";
	// 登录的密�?�?��加密);
	private String password = "";
	// 短信发�?方式;
	private int smstype = 1;
	// 短信发�?是否定时;
	private int timerflag = 0;
	// 短信发�?定时时间;
	private String timervalue;
	// 短信发�?定时的类�?
	private int timertype = 1;
	// 短信发�?的编�?�?��加密);
	private String timerid;
	// 发�?的手机号�?�?��加密);
	private String mobiles;
	// 发�?的内�?�?��加密);
	private String message;

	public String getServicesRequestAddRess() {
		return servicesRequestAddRess;
	}

	public void setServicesRequestAddRess(String servicesRequestAddRess) {
		this.servicesRequestAddRess = servicesRequestAddRess; //Base.base64Decode(servicesRequestAddRess);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username; //Base.base64Decode(username);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;//Base.base64Decode(password);
	}

	public int getSmstype() {
		return smstype;
	}

	public void setSmstype(int smstype) {
		this.smstype = smstype;
	}

	public int getTimerflag() {
		return timerflag;
	}

	public void setTimerflag(int timerflag) {
		this.timerflag = timerflag;
	}

	public String getTimervalue() {
		return timervalue;
	}

	public void setTimervalue(String timervalue) {
		this.timervalue = timervalue;
	}

	public int getTimertype() {
		return timertype;
	}

	public void setTimertype(int timertype) {
		this.timertype = timertype;
	}

	public String getTimerid() {
		return timerid;
	}

	public void setTimerid(String timerid) {
		this.timerid = timerid;//Base.base64Decode(timerid);
	}

	public String getMobiles() {
		return mobiles;
	}

	public void setMobiles(String mobiles) {
		this.mobiles = mobiles;//Base.base64Decode(mobiles);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		try {
			this.message = URLEncoder.encode(message, this.getEncode());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		//this.message = message; //Base.base64Decode(message);
	}

	// 发�?短信;
	public Map<String, String> sendSMS() {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		Document doc;
		Map<String, String> result = new LinkedHashMap<String, String>();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			InputStream is = URLConnIO.getSoapInputStream(this
					.getServicesRequestAddRess(), this.getRequestData().toString());
			doc = db.parse(is);
			result.put("errorcode", doc.getElementsByTagName("errorcode").item(
					0).getFirstChild().getNodeValue());
			result.put("errordescription", doc.getElementsByTagName(
					"errordescription").item(0).getFirstChild().getNodeValue());
			result.put("time", doc.getElementsByTagName("time").item(0)
					.getFirstChild().getNodeValue());
			result.put("msgcount", doc.getElementsByTagName("msgcount").item(0)
					.getFirstChild().getNodeValue());
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("发�?短信失败!");
		}
		return result;
	}

	// 拼接参数;
	private StringBuffer getRequestData() {
		StringBuffer requestAddRess = new StringBuffer();
		requestAddRess.append("func=sendsms&username=");
		requestAddRess.append(this.username);
		requestAddRess.append("&password=");
		requestAddRess.append(password);
		requestAddRess.append("&smstype=");
		requestAddRess.append(smstype);
		requestAddRess.append("&timerflag=");
		requestAddRess.append(this.timerflag);
		if (this.timerflag != 0) {
			requestAddRess.append("&timervalue=");
			requestAddRess.append(this.timervalue);
		}
		requestAddRess.append("&timertype=");
		requestAddRess.append(this.timertype);
		requestAddRess.append("&timerid=");
		requestAddRess.append(this.timerid);
		requestAddRess.append("&mobiles=");
		requestAddRess.append(this.mobiles);
		requestAddRess.append("&message=");
		requestAddRess.append(this.message);
		return requestAddRess;
	}
}
