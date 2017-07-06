package oauth.qq.controller;

import com.qq.connect.QQConnectException;
import com.qq.connect.oauth.Oauth;
import oauth.qq.service.iface.IQQService;
import oauth.qq.util.Result;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 微信服务controller
 * @author Lichenyi
 * @date 2017-7-5
 */
@Controller
@RequestMapping("/oauth")
public class OauthController {
    private Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private IQQService qqService;


    /**
     * 跳转QQ登陆页面
     * @param request
     * @param response
     */
    @GetMapping("/qq/login")
    @ResponseBody
    public void qqLogin(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        try {
            String url = new Oauth().getAuthorizeURL(request);
            logger.info(String.format("qq sendRedirect --> %s", url));
            response.sendRedirect(new Oauth().getAuthorizeURL(request));
        } catch (QQConnectException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取QQ账号信息, 最终返回的是狮吼平台的用户数据
     * @param request  参数 code 和  state
     * @param response
     * @return
     */
    @GetMapping("/qq/userInfo")
    @ResponseBody
    public ResponseEntity<Result> getQQUserInfo(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=utf-8");
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(Result.build().content(qqService.getUserInfo(request)));
    }

}
