package com.example.qsys.yousi.common;

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
    public static String accessToken = "";
    /**
     * static final String BASE_URL = "http://116.196.109.167:8080/education/";
     */
    //public static final String BASE_URL = "http://192.168.56.1:8080/";
    public static final String BASE_URL = "http://192.168.1.133:8080/";
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
    public static final int SERVER_ERROR = 0;
    /**
     * 网络未连接标记
     */
    public static final int NO_CONTENT = 1;
    /**
     * 网络不可用标记
     */
    public static final int NET_UNABLE = 2;
    /**
     * 网络不可用标记 待改
     */
    public static final int UN_RECOGNICTION = 2;
}
