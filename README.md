# oauth.qq
QQ第三方登陆

项目启动后首先
1.在浏览器输入：localhost:8888/oauth/qq/login
然后扫码进入，
2.复制url地址后的code放到code后
http://localhost:8888/oauth/qq/userInfo?code=&state=
成功返回：

{
    
    "errorCode": 0,
    "errorDescription": "success",
    "requestId": "be2900aa-73c3-4ba1-a00f-3aa22479cab1",
    "result": {
        "avatar": {
            "avatarURL30": "http://qzapp.qlogo.cn/qzapp/101353522/2188A8B8E44C2C1CE2166166ECDD7D4C/30",
            "avatarURL50": "http://qzapp.qlogo.cn/qzapp/101353522/2188A8B8E44C2C1CE2166166ECDD7D4C/50",
            "avatarURL100": "http://qzapp.qlogo.cn/qzapp/101353522/2188A8B8E44C2C1CE2166166ECDD7D4C/100"
        },
        "nickname": "温柔的~草包",
        "gender": "男",
        "vip": false,
        "level": 0,
        "yellowYearVip": false,
        "ret": 0,
        "msg": "",
        "openId": "2188A8B8E44C2C1CE2166166ECDD7D4C"
    }
}