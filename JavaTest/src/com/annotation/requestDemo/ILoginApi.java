package com.annotation.requestDemo;

/**
 * date:  2017/4/16 20:38 <br>
 * description: 登陆接口 <br>
 */
public interface ILoginApi {
    @ReqType(ReqTypeEnum.POST)
    @ReqUrl(reqUrl = "www.baidu.com")
    String login(@ReqParam("userId") String userId,@ReqParam("pwd") String pwd);

    @ReqType(ReqTypeEnum.POST)
    boolean logOut(@ReqParam("userId") String userId,@ReqParam("token") String token);
}
