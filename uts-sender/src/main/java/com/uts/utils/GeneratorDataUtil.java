package com.uts.utils;



import com.uts.entity.TradeDetail;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class GeneratorDataUtil {

	private static Random random = new Random(); 
	
	//交易时间：
	private static Date getRandomDate(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		Date d = new Date(117, 
				random.nextInt(7),
				(int)(Math.random()*28)+1,
				(int)(Math.random()*23)+1, 
				(int)(Math.random()*59)+1,
				(int)(Math.random()*59)+1);
		System.out.println(formatter.format(d));
		return d;
	}

	static Object[] accounts = null;
	static {
		Pair p1 = new Pair<String, String>("张三", "30001040191113380583");
		Pair p2 = new Pair<String, String>("李四", "40003242903000023274");
		Pair p3 = new Pair<String, String>("王五", "50033457780002320965");
		Pair p4 = new Pair<String, String>("赵六", "68886000080002320926");
		accounts = new Object[]{p1, p2, p3, p4};
	}
	
	static Object[] oppAccounts = null;
	static {
		Pair p1 = new Pair<String, String>("小1", "500013334380543599");
		Pair p2 = new Pair<String, String>("小2", "498980000566599998");
		Pair p3 = new Pair<String, String>("小3", "788876699990005589");
		Pair p4 = new Pair<String, String>("小4", "155500006660007779");
		Pair p5 = new Pair<String, String>("小5", "344339999333000001");
		oppAccounts = new Object[]{p1, p2, p3, p4, p5};
	}

	//
	private static String generateSerialno(Date d){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		StringBuffer key = new StringBuffer();
		key.append(formatter.format(d));
		String uid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
		key.append(uid.substring(12));
		return key.toString();
	}
	
	//
	private static Pair getRandomAccounts(){
		return (Pair) accounts[random.nextInt(accounts.length)];
	}
	
	private static Pair getRandomOppAccounts(){
		return (Pair) oppAccounts[random.nextInt(oppAccounts.length)];
	}
	
	
	static Object[] digests = null;
	static {
		String d1 = "转账";
		String d2 = "付款";
		String d3 = "百度买单";
		String d4 = "美团买单";
		String d5 = "淘宝买单";
		digests = new Object[]{d1, d2, d3, d4, d5};
	}
	
	private static String getRandomDigest(){
		return (String) digests[random.nextInt(digests.length)];
	}
	
	public static void main(String[] args) {
		System.err.println(getRandomBalance());
	}
	
	private static int getRandomCreditAmount(){
		return (int) ((Math.random()*100)+ (Math.random()*300) + (Math.random()*80));
	}
	
	private static int getRandomBalance(){
		return (int) ((Math.random()*100000)+ (Math.random()*3000) + (Math.random()*500));
	}
	
	
	public static TradeDetail generator() {
			TradeDetail td1 = new TradeDetail();
			td1.setId(KeyUtil.generatorUUID());
			Date tradeDate = getRandomDate();
			
			td1.setSerialno(generateSerialno(tradeDate));
			td1.setTradeDate(tradeDate);
			td1.setCreditAmount(new BigDecimal(getRandomCreditAmount()));
			td1.setDedbitAmount(new BigDecimal(0));
			td1.setBusinessType("1");
			td1.setBalance(new BigDecimal(getRandomBalance()));
			
			Pair account = getRandomAccounts();
			
			td1.setAccountName((String) account.getObject1());
			td1.setAccountNo((String) account.getObject2());
					
			
			Pair oppAccount = getRandomOppAccounts();
			td1.setOppAccountName((String) oppAccount.getObject1());
			td1.setOppAccountNo((String) oppAccount.getObject2());
			
			td1.setDigest(getRandomDigest());
			td1.setStatus("0");
			td1.setCreateBy("system");
			td1.setCreateTime(new Date());
			td1.setUpdateBy("system");
			td1.setUpdateTime(new Date());
			return td1;
			
	}
	
}
