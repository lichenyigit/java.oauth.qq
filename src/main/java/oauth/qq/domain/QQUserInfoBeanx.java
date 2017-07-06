package oauth.qq.domain;

import com.qq.connect.QQConnectException;
import com.qq.connect.QQConnectResponse;
import com.qq.connect.javabeans.Avatar;
import com.qq.connect.utils.json.JSONException;
import com.qq.connect.utils.json.JSONObject;

import java.io.Serializable;

/**
 * @author Lichenyi
 * @date 2017-7-6
 */
public class QQUserInfoBeanx extends QQConnectResponse implements Serializable {
    private static final long serialVersionUID = 5606709876246698659L;
    private Avatar avatar = new Avatar("");
    private String nickname;
    private String gender;
    private boolean vip;
    private int level;
    private boolean yellowYearVip;
    private int ret;
    private String msg;
    private String openId;

    public String getNickname() {
        return this.nickname;
    }

    public String getGender() {
        return this.gender;
    }

    public boolean isVip() {
        return this.vip;
    }

    public Avatar getAvatar() {
        return this.avatar;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean isYellowYearVip() {
        return this.yellowYearVip;
    }

    public int getRet() {
        return this.ret;
    }

    public String getMsg() {
        return this.msg;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public QQUserInfoBeanx(JSONObject json) throws QQConnectException {
        this.init(json);
    }

    private void init(JSONObject json) throws QQConnectException {
        if(json != null) {
            try {
                this.ret = json.getInt("ret");
                if(0 != this.ret) {
                    this.msg = json.getString("msg");
                } else {
                    this.msg = "";
                    this.nickname = json.getString("nickname");
                    this.gender = json.getString("gender");
                    this.vip = json.getBoolean("vip");
                    JSONObject avatar = json.getJSONObject("avatar");
                    this.avatar = new Avatar(avatar.getString("avatarURL30"), avatar.getString("avatarURL50"), avatar.getString("avatarURL100"));
                    this.level = json.getInt("level");
                    this.yellowYearVip = json.getInt("is_yellow_year_vip") == 1;
                }
            } catch (JSONException var3) {
                throw new QQConnectException(var3.getMessage() + ":" + json.toString(), var3);
            }
        }

    }

    public int hashCode() {
        int result = 1;
        result = 31 * result + (this.nickname == null?0:this.nickname.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        } else if(obj == null) {
            return false;
        } else if(this.getClass() != obj.getClass()) {
            return false;
        } else {
            QQUserInfoBeanx other = (QQUserInfoBeanx)obj;
            if(this.nickname == null) {
                if(other.nickname != null) {
                    return false;
                }
            } else if(!this.nickname.equals(other.nickname)) {
                return false;
            }

            return true;
        }
    }

    public String toString() {
        return "UserInfo [nickname : " + this.nickname + " , " + "figureurl30 : " + this.avatar.getAvatarURL30() + " , " + "figureurl50 : " + this.avatar.getAvatarURL50() + " , " + "figureurl100 : " + this.avatar.getAvatarURL100() + " , " + "gender : " + this.gender + " , " + "vip : " + this.vip + " , " + "level : " + this.level + " , " + "isYellowYeaarVip : " + this.yellowYearVip + "]";
    }
}
