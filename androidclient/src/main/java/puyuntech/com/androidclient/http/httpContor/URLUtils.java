package puyuntech.com.androidclient.http.httpContor;


/**
 * 作者 zx
 * 创建时间 2016/4/19 0019
 * 描述 请求地址
 * 修改时间 2016/4/19 0019
 * 修改描述 请求地址
 * 修改者 zx
 **/
public class URLUtils {

    /**
     * 请求成功
     */
    public final static int RESULT_SUCCESS = 1;

    /**
     * 请求失败
     */
    public final static int RESULT_FAILED = 0;

    /**
     * 重复登录
     */
    public final static int RESULT_LOGIN_AGAIN = 102;

    /**
     * 请求失败，服务器崩溃
     */
    public final static int RESULT_ERROR = 500;


    /**
     * 服务器地址
     */
    //public static final String BASE_URL = "http://192.168.1.10:8055/";//服务器地址
    //public static final String BASE_URL = "http://58.240.32.170:7076/";//服务器地址
    //public static final String BASE_URL = "http://172.21.1.249:8033/";//服务器地址
    //public static final String BASE_URL = "http://172.21.1.142:8033/";//服务器地址
    public static final String BASE_URL = "http://172.21.1.29:7076/";

    /**
     * 接口地址
     */
    public static final String BASE_URL_APP = BASE_URL + "/WSMain.asmx/";//接口地址
    /**
     * 说明:登录
     * 入参:
     * 返回:
     */
    public static final String LOGIN = BASE_URL_APP + "login";//登录
    /**
     * 说明:任务下派
     * 入参:
     * 返回:
     */
    public static final String ADD_RENWU = BASE_URL_APP + "addRenWu";//任务下派
    /**
     * 说明:任务列表
     * 入参:
     * 返回:
     */
    public static final String GET_RENWU_LIST = BASE_URL_APP + "getRenWuList";//任务列表
    /**
     * 说明:任务详情
     * 入参:
     * 返回:
     */
    public static final String GET_RENWU_DETAIL = BASE_URL_APP + "getRenWuDetail";//任务详情
    /**
     * 说明:任务搜索
     * 入参:
     * 返回:
     */
    public static final String SEARCH_RENWU = BASE_URL_APP + "searchRenWu";//任务搜索
    /**
     * 说明:事件列表
     * 入参:
     * 返回:
     */
    public static final String GET_SHIJIAN_LIST = BASE_URL_APP + "getShiJianList";//事件列表
    /**
     * 说明:事件搜索
     * 入参:
     * 返回:
     */
    public static final String SEARCH_SHIJIAN = BASE_URL_APP + "searchShiJian";//事件搜索
    /**
     * 说明:回复任务
     * 入参:
     * 返回:
     */
    public static final String HUIFU_RENWU = BASE_URL_APP + "huifuRenWu";//回复任务
    /**
     * 说明:完成任务
     * 入参:
     * 返回:
     */
    public static final String WANCHENG_RENWU = BASE_URL_APP + "wanchengRenWu";//完成任务
    /**
     * 说明:OCR上传附件
     * 入参:
     * 返回:
     */
    public static final String OCR_UPLOADIDCARD = BASE_URL_APP + "OcrUploadIDCard";//OCR上传附件
    /**
     * 说明:上传附件
     * 入参:
     * 返回:
     */
    public static final String UPLOAD_PHOTO = BASE_URL_APP + "uploadFile";//上传附件
    /**
     * 说明:身份校验
     * 入参:
     * 返回:
     */
    public static final String CHECK_USER = BASE_URL_APP + "checkUser";//身份校验
    /**
     * 说明:人口录入
     * 入参:
     * 返回:
     */
    public static final String ADD_REN_KOU = BASE_URL_APP + "addRenKou";//人口录入
    /**
     * 说明:回复任务
     * 入参:
     * 返回:
     */
    public static final String HUI_FU_REN_WU = BASE_URL_APP + "huifuRenWu";//回复任务
    /**
     * 说明:新增事件
     * 入参:
     * 返回:
     */
    public static final String ADD_SHI_JIAN = BASE_URL_APP + "addShiJian";//新增事件
    /**
     * 说明:事件详情
     * 入参:
     * 返回:
     */
    public static final String SHI_JIAN_DETAIL = BASE_URL_APP + "shiJianDetail";//事件详情
    /**
     * 说明:事件操作
     * 入参:
     * 返回:
     */
    public static final String SHI_JIAN_CAO_ZUO = BASE_URL_APP + "shijianCaoZuo";//事件操作
    /**
     * 说明:获取位置
     * 入参:
     * 返回:
     */
    public static final String GET_LOCATION = BASE_URL_APP + "getLocation";//获取位置
    /**
     * 说明:获取对应的下级账号
     * 入参:
     * 返回:
     */
    public static final String GET_USERINGRIDDING = BASE_URL_APP + "getUserInGridding";//获取对应的下级账号
    /**
     * 说明:驳回回复
     * 入参:
     * 返回:
     */
    public static final String BOHUI_HUIFU = BASE_URL_APP + "bohuiHuifu";//驳回回复
    /**
     * 说明:协作部门
     * 入参:
     * 返回:
     */
    public static final String GET_XIEZUOBUMEN = BASE_URL_APP + "getXieZuoBuMen";//协作部门
    /**
     * 说明:人口列表
     * 入参:
     * 返回:
     */
    public static final String RENKOU_LIEBIAO = BASE_URL_APP + "getPeopleList";//人口列表
    public static final String RENKOU_LIEBIAO_SEARCH = BASE_URL_APP + "searchPeopleList";//人口列表搜索

    public static final String GET_PHONE_LIST = BASE_URL_APP + "getPhoneList";//通讯录列表
    public static final String SEARCH_PHONELIST = BASE_URL_APP + "searchPhoneList";//通讯录列表搜索
    public static final String GET_ORGANIZATIONBYPID = BASE_URL_APP + "getOrganizationByPId";//根据父节点ID获取组织列表结构【通用，选择下属对象的时候也用到】

}