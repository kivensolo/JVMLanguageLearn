package com;

/**
 * Copyright(C) 2015, 北京视达科科技有限公司
 * All rights reserved.
 * author: King.Z
 * date:  2015/12/30 11:55
 * description:
 */
//这是程序的入口 main
class jisuanqi
{
    public static void main(String[] args)
    {
        yunsuan p=new yunsuan();
        int r= p.test(4,'*',5); //用运算类运算结果
//       p.print(r);  //这里不能用p的print方法  p代表一个yunsuan()方法  但是里面没有print的这个方法  所以这里错误
        //print是dayin类里面的方法  所以要这样
        //用打印类打印
        dayin printClass = new dayin();
        printClass.print(r); //


    }
}
class  dayin{
//    String print(yunsuan ans){  这里也错误了  方法里  ans需要参数类型修饰  你传入的结果  是个int的  所以不能是yunsuan来修饰ans 而是int
    // 并且 返回值是String  但是你直接打印了 就不需要返回值 直接void就行了
//		System.out.println("运算结果为："+ans);
//	}
    void print(int ans){
		System.out.println("运算结果为："+ans);
	}
}

class yunsuan
{
	int test(int a,char f,int b)
		{
		int ans=0;
		if (f=='+')
		{
			 ans=a+b;
		}else if (f=='-')
		{
			 ans=a-b;
		}else if (f=='*')
		{
			ans=a*b;
		}else if (f=='/')
		{
			 ans=a/b;
		}
		else
			{System.out.println("运算符号不正确");}
		return ans;
	}

}
