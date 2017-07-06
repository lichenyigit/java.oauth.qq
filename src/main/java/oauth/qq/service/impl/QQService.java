package oauth.qq.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
import oauth.qq.domain.QQUserInfoBeanx;
import oauth.qq.service.iface.IQQService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lichenyi
 * @date 2017-7-5
 */
@SuppressWarnings("all")
@Service("IQQService")
public class QQService implements IQQService {
    private Logger logger = LogManager.getLogger(getClass());

    @Value("${qq.open.me}")
    private String meUrl;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取用户信息
     *
     * @param code
     * @return
     */
    @Override
    public Object getUserInfo(HttpServletRequest request) {
        try {
            request.getSession().setAttribute("qq_connect_state", request.getParameter("state"));
            AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
            String accessToken   = null,
                    openID        = null;
            long tokenExpireIn = 0L;
            if (accessTokenObj.getAccessToken().equals("")) {
                logger.info("没有获取到响应参数");
                return "没有获取到响应参数";
            } else {
                //获取token
                accessToken = accessTokenObj.getAccessToken();
                tokenExpireIn = accessTokenObj.getExpireIn();

                /*
                //通过自我调用QQ的方法获取openid和unionid
                JSONObject jsonObject = getQQMe(accessToken);
                String openid = jsonObject.getString("openid");
                String unionid = jsonObject.getString("unionid");
                */

                //获取个人的头像昵称等信息
                //获取openid
                OpenID openIDObj =  new OpenID(accessToken);
                openID = openIDObj.getUserOpenID();
                //获取用户信息
                UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
                UserInfoBean userInfoBean = (UserInfoBean) qzoneUserInfo.getUserInfo();
                com.qq.connect.utils.json.JSONObject jsonUserInfoBean = new com.qq.connect.utils.json.JSONObject(userInfoBean);
                QQUserInfoBeanx result = new QQUserInfoBeanx(jsonUserInfoBean);
                result.setOpenId(openID);
                return result;

            }
        } catch (QQConnectException e) {
            e.printStackTrace();
        }
        return null;
    }

    private JSONObject getQQMe(String accessToken){
        String url = String.format(meUrl, accessToken);
        String string = restTemplate.getForObject(url, String.class);
        String jsonStr = string.substring(string.indexOf("{"), string.indexOf("}")+1);
        JSONObject jsonObject = (JSONObject) JSON.parse(jsonStr);
        return jsonObject;
    }

}