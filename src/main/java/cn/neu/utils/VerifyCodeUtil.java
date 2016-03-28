package cn.neu.utils;

import java.util.Random;

public class VerifyCodeUtil{
	public static String gen(int digit){
		StringBuffer sb = new StringBuffer();
		if(digit<=0){
			digit=6;
		}
		for(int i=0;i<digit;i++){
			sb.append(new Random().nextInt(10));
		}
		return sb.toString();
	}
}
