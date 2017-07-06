package oauth.qq.service.iface;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lichenyi
 * @date 2017-7-6
 */
public interface IQQService {
    Object getUserInfo(HttpServletRequest request);
}
