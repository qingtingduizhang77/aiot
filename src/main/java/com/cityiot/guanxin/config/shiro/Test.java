package com.cityiot.guanxin.config.shiro;

public class Test {

	public static void main(String[] args) {
		String principals="asdasd@@@#$PC";
		
		String username =principals.substring(0,principals.indexOf("@@@#$"));
		
		String[] aa=principals.split("@@@#$");
		String type =null;
				if(principals.indexOf("@@@#$")>0)
				{
					 type =principals.substring(principals.indexOf("@@@#$")+5,principals.length());
				}
		
		
		System.out.println(aa[0]);
		System.out.println(type);
		
		
	}

}
