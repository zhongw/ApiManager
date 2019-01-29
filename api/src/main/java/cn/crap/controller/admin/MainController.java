package cn.crap.controller.admin;

import cn.crap.beans.Config;
import cn.crap.dto.LoginInfoDto;
import cn.crap.dto.SettingDto;
import cn.crap.enu.SettingStatus;
import cn.crap.framework.JsonResult;
import cn.crap.framework.base.BaseController;
import cn.crap.framework.interceptor.AuthPassport;
import cn.crap.service.ISearchService;
import cn.crap.service.tool.SystemService;
import cn.crap.utils.HttpPostGet;
import cn.crap.utils.IConst;
import cn.crap.utils.LoginUserHelper;
import cn.crap.utils.Tools;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MainController extends BaseController {
    @Autowired
    private ISearchService luceneService;
    @Autowired
    private SystemService systemService;

    /**
     * admin dashboard
     * 后台管理主页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/admin.do")
    @AuthPassport
    public String showHomePage() throws Exception {
        return "resources/html/admin/index.html";
    }

    /**
     * 获取系统最新信息
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/admin/property.do")
    @AuthPassport(authority = C_AUTH_SETTING)
    @ResponseBody
    public JsonResult property() throws Exception {
        Map<String, Object> returnMap = new HashMap<>();
        Map<String, Object> properties = new HashMap<>();
        properties.put("domain", Config.domain);
        properties.put("openRegister", Config.openRegister);
        properties.put("luceneSearchNeedLogin", Config.luceneSearchNeedLogin);
        properties.put("openRegister", Config.openRegister);
        properties.put("canRepeatUrl", Config.canRepeatUrl);
        properties.put("cacheTime", Config.cacheTime);
        properties.put("loginInforTime", Config.loginInforTime);
        properties.put("fileSize", Config.fileSize);
        properties.put("fileType", Config.fileType);
        properties.put("imageType", Config.imageType);
        properties.put("mail", Config.mail);
        properties.put("clientID", Config.clientID);

        returnMap.put("properties", properties);
        // 从crapApi获取版本信息
        try {
            String crapApiInfo =
                    HttpPostGet.get("http://api.crap.cn/mock/trueExam.do?id=c107b205-c365-4050-8fa9-dbb7a38b3d11", null, null);
            JSONObject json = JSONObject.fromObject(crapApiInfo);
            if (json.getInt("success") == 1) {
                json = json.getJSONObject("data");
                returnMap.put("crapApiInfo",
                        Tools.getStrMap("latestVersion", json.getString("latestVersion"),
                                "latestVersion", json.getString("latestVersion"),
                                "addFunction", json.getString("addFunction"),
                                "updateUrl", json.getString("updateUrl"),
                                "noticeUrl", json.getString("noticeUrl"),
                                "notice", json.getString("notice")));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JsonResult(1, returnMap);
    }

    /**
     * 登录 or 注册页面
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/loginOrRegister.do")
    public String loginOrRegister() throws Exception {
        return "resources/html/admin/loginOrRegister.html";
    }


    /**
     * 删除错误提示
     */
    @RequestMapping("/admin/closeErrorTips.do")
    @ResponseBody
    @AuthPassport(authority = C_AUTH_ADMIN)
    public JsonResult closeErrorTips() throws Exception {
        stringCache.del(C_CACHE_ERROR_TIP);
        return new JsonResult(1, null);
    }

    /**
     * 后台页面初始化
     */
    @RequestMapping({"/admin/init.do", "back/init.do"})
    @ResponseBody
    @AuthPassport
    public JsonResult init(HttpServletRequest request) throws Exception {
        Map<String, String> settingMap = new HashMap<>();
        for (SettingDto setting : settingCache.getAll()) {
            if (SettingStatus.COMMON.getStatus().equals(setting.getStatus())) {
                settingMap.put(setting.getKey(), setting.getValue());
            }
        }
        settingMap.put(IConst.DOMAIN, Config.domain);

        Map<String, Object> returnMap = new HashMap<String, Object>();
        returnMap.put("settingMap", settingMap);
        LoginInfoDto user = LoginUserHelper.getUser();
        returnMap.put("sessionAdminName", user.getUserName());
        returnMap.put("sessionAdminAuthor", user.getAuthStr());
        returnMap.put("sessionAdminRoleIds", user.getRoleId());
        returnMap.put("sessionAdminId", user.getId());
        returnMap.put("errorTips", stringCache.get(C_CACHE_ERROR_TIP));

        return new JsonResult(1, returnMap);
    }

    /**
     * 重建索引，只有最高管理员才具有该权限
     */
    @ResponseBody
    @RequestMapping("/admin/rebuildIndex.do")
    @AuthPassport(authority = C_SUPER)
    public JsonResult rebuildIndex() throws Exception {
        return new JsonResult(1, luceneService.rebuild());
    }

    /**
     * 清除缓存，只有最高管理员才具有该权限
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/admin/flushDB.do")
    @AuthPassport(authority = C_SUPER)
    public JsonResult flushDb() {
        projectCache.flushDB();
        stringCache.flushDB();
        moduleCache.flushDB();
        settingCache.flushDB();
        objectCache.flushDB();
        return new JsonResult().success();
    }

    @ResponseBody
    @RequestMapping("/admin/compress.do")
    @AuthPassport(authority = C_SUPER)
    public JsonResult compress() throws Exception{
        systemService.compressSource();
        systemService.mergeSource();
        return new JsonResult().success();
    }

    @ResponseBody
    @RequestMapping("/admin/cleanLog.do")
    @AuthPassport(authority = C_SUPER)
    public JsonResult cleanLog() throws Exception{
        systemService.cleanLog();
        return new JsonResult().success();
    }


}
