package com.example.qsys.yousi.common;

import com.example.qsys.yousi.CustomApplication;
import com.example.qsys.yousi.R;

/**
 * Created by hanshaokai on 2017/10/10 11:58
 */

public class Constant {
    /**
     * 日志和读后感 activit 展示类型
     */
    public static final int DAYLIE = 0;
    public static final int READPRESSION = 1;
    /**
     * 日志详情
     */
    public static final int READPRESSION_DETAIL = 3;
    /**
     * 读后感详情
     */
    public static final int READPRESSION_READ = 4;
    /**
     * 日志传递对象
     */
    public static final String READPRESSION_OBJECT = "object";
    /**
     * ideaactivity 传递标记
     */
    public static final String IDEA_STYPE = "idea";
    /**
     * 网络返回成功标记
     */
    public static final int SCUCESS_COED = 1;
    /**
     * 我的界面编辑 类型 编辑昵称
     */
    public static final int EDITE_NICK = 1;
    /**
     * 性别男
     */
    public static final int MAN = 1;
    public static final String PASSWORD = "密码";
    public static final String ACCOUNT = "账号";
    public static final String LOGIN_IS_OR_NOT = "是否登录";
    public static final int NOT_NULL = 1;
    public static String accessToken = "";

    public static final String BASE_URL = CustomApplication.getAppContext().getResources().getString(R.string.url);
    /**
     * 登陆 不存在用户
     */
    public static final int USER_NOT_EXIT=0;
    /**
     * 密码错误标记
     */
    public static final int USER_PASSWORD_ERRO=1;
    /**
     * 登陆成功标记
     */
    public static final int USER_LOGIN_SUCESS=2;

    /**
     * 后台错误标记
     */
    public static final int SERVER_ERROR = 3;
    /**
     * 后台不可访问
     */
    public static final int SERVER_UNREACH = 4;
    /**
     * 网络未连接标记
     */
    public static final int NO_CONTENT = 1;
    /**
     * 网络不可用标记
     */
    public static final int NET_UNABLE = 2;

    /**
     * 登录情况 相关信息
     */
    public static String LOGIN_DETAIL = "login_detail";
}
