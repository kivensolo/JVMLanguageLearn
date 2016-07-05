package com.common.string;

/**
 * Copyright(C) 2016, 北京视达科科技有限公司
 * All rights reserved. <br>
 * author: King.Z <br>
 * date:  2016/6/16 10:37 <br>
 * description: 关于接口的一些格式化代码 <br>
 */
public class WebUrlFormatter {

    public static void main(String[] args) {
        String str = buildHuaWeiUrl("http://222.83.5.149:8082/EPG/oauth/v2/token?&refresh_token=gnr11ng1nm-mmq0hhqLx33m&grant_type=refresh_token&client_secret=20160606&client_id=kukan&userid=&" +
                ".");
        System.out.println("转换后：" + str);
    }

    /**
     * 去除'?'后的&符号
     * @param url
     * @return
     */
    private static String buildHuaWeiUrl(String url){
		String moreString = "";
		String stName = null;
		String us[] = url.split("\\?");

		if (us.length > 1)
		{
			String[] cells = us[1].split("&"); //拆分后面的每一个参数

			for (int i = 0; i < cells.length; i++)
			{
				String str[] = cells[i].split("=");
				String name = str[0];
				if (str.length > 1)
				{
					//if (TextUtils.isEmpty(moreString))
					if (moreString.length() == 0)
					{
						moreString += (name + "=" + str[1]);
					} else
					{
						moreString += ("&" + name + "=" + str[1]);
					}
				}

			}
		}
		return us[0] + "?" + moreString;
	}

}
