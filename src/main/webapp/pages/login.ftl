<!DOCTYPE html>
<html lang="en">
<head>
    <title>登录</title>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta HTTP-EQUIV="pragma" CONTENT="no-cache">
    <meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
    <meta HTTP-EQUIV="expires" CONTENT="0">
    <meta name="keywords" content="" />
    <meta name="description" content="" />
    <meta name="apple-touch-fullscreen" content="yes" />
    <meta name="format-detection" content="telephone=no" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta http-equiv="Expires" content="-1" />
    <meta http-equiv="pragram" content="no-cache" />
    <meta name="screen-orientation" content="portrait">
    <meta name="x5-orientation" content="portrait">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">

    <link href="${base}/resources/css/login.css" rel="stylesheet" type="text/css">
</head>
<body>
    <div class="content" style="margin: 0 auto; position: relative;">
        <div class="loginCon">
            <form id="loginForm" action="${base}/loginIn.html" method="POST">
                <input type="hidden" id="enPassword" name="enPassword"/>
                <input type="hidden" name="captchaId" value="${captchaId}"/>
                <ul>
                    <li>
                        <input class="inputText inputMobile"  id="username" name="username" maxlength="11" type="text" placeHolder="手机号" /></li>
                    <li>
                        <input class="inputText inputPwd" id="password" name="password" maxlength="20" type="password" placeHolder="密码" />
                    </li>
                    <#if (failCount)?? && (failCount gt 3)>
                    <li class="verifyCode">
                        <div class="verifyImg fl">
                            <input class="inputText inputImg" id="captcha" name="captcha" type="text" maxlength="4" data-info="请输入图片验证码" placeholder="验证码">
                        </div>
                        <div class="imgCode fl">
                            <img id="captchaImage" src="${base}/common/captcha.html?captchaId=${captchaId}" />
                        </div>
                        <div class="imgCodeLink fl">
                            <a href="javascript:void(0);" onclick="changeCaptcha()">换一张</a>
                        </div>
                    </li>
                    </#if>
                    <li>
                        <div class="err">
                            <i class="errIcon"></i>
                            <p class="errText"></p>
                        </div>
                    </li>
                    <li>
                        <div class="rememberMe">
                            <input type="checkbox" class="reusername" id="isRememberUsername" value="true"/>
                            <label for="isRememberUsername">记住用户名</label>
                        </div>
                    </li>
                    <li>
                        <div>
                            <input class="loginBtn" type="button" value="登录" />
                        </div>
                    </li>
                    <li style="margin-top: 5px;">
                        <div id="div_chrome" style="display: none;font-size: 12px;color: #ff3607;">友情提示：推荐使用谷歌浏览器登录</div>
                    </li>
                </ul>
            </form>
        </div>
        <div class="am-footer">
            义乌市哈吉网络科技有限公司&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;电话：0579-84072981
        </div>
    </div>

</body>
<script type="text/javascript" src="${base}/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="${base}/resources/js/captcha/jsbn.js"></script>
<script type="text/javascript" src="${base}/resources/js/captcha/prng4.js"></script>
<script type="text/javascript" src="${base}/resources/js/captcha/rng.js"></script>
<script type="text/javascript" src="${base}/resources/js/captcha/rsa.js"></script>
<script type="text/javascript" src="${base}/resources/js/captcha/base64.js"></script>
<script type="text/javascript" src="${base}/resources/js/captcha/cookie.js"></script>
<script type="text/javascript" src="${base}/resources/js/common.js"></script>
<script>
    window.onresize = function(){
        var winh =($(window).height()-300) /3;
        $(".content").css("top", winh);
    }
    $(function() {
        var winh =($(window).height()-300) /3;
        $(".content").css("top", winh);
    });
    $(document).ready(function() {
        var isChrome = window.navigator.userAgent.indexOf("Chrome") !== -1;
        if(!isChrome){
            $("#div_chrome").show();
        }

        function InputCheck() {
            this.mobileFlag = false;
            this.errText = "";
            this.$err = $(".err");
        }

        InputCheck.prototype.showErr = function($input) {
            var tempObj = this;
            this.$err.children(".errText").html(tempObj.errText);
            this.$err.addClass("errShow");
            $input.addClass("inputErr");
        }

        InputCheck.prototype.hideErr = function($input) {
            this.$err.removeClass("errShow");
            $input.removeClass(".inputErr");
        }

        var inputCheck = new InputCheck();

        //光标进入文本框时，文本框变颜色
        $(".inputText").bind("focus", function() {
            // 文本框变颜色
            $(this).addClass("inputFocus");
            // 去除文本框错误的提示样式
            $(this).removeClass("inputErr");
        }).bind("blur", function() {
            // 文本框变灰色
            $(this).removeClass("inputFocus");
        })
        // 点击的登录按钮
        $(".loginBtn").bind("click", function() {
            var $username = $("#username");
            var $pwd = $("#password");
            //判断手机号是否输入
            if ($username.val() === "") {
                inputCheck.errText = "请输入手机号码";
                inputCheck.showErr($username);
                return false;
            }
            // 判断密码是否输入
            if($pwd.val() === "") {
                inputCheck.errText = "请输入密码";
                inputCheck.showErr($pwd);
                return false;
            }
            // 首先判断手机号码格式是否正确
            inputCheck.mobileFlag = check_m($username);
            // 手机号码格式验证成功，则验证密码是否为空
            if (inputCheck.mobileFlag) {
                inputCheck.pwdFlag = check_pwd($pwd);
            }
            if (!$(".err").hasClass("errShow")) {
                if ($("#isRememberUsername").prop("checked")) {
                    addCookie("adminUsername", $("#username").val(), {expires: 7 * 24 * 60 * 60});
                } else {
                    removeCookie("adminUsername");
                }
                var rsaKey = new RSAKey();
                rsaKey.setPublic(b64tohex("${modulus}"), b64tohex("${exponent}"));
                var enPassword = hex2b64(rsaKey.encrypt($pwd.val()));
                $("#enPassword").val(enPassword);
                $("#loginForm").submit();
            }
            inputCheck.imgFlag = false;
        });

        // 回车键触发登录
        $("#username,#password,.inputImg").keydown(function(e) {
            var curKey = e.keyCode || e.keyChar || e.which;
            if (curKey == 13) {
                $(".loginBtn").click();
            }
        });

        function check_m($username) {
            var inputVal = trimVal($username.val());
            var errText = "ok";
            errText = inputVal.length == 0 ? "请输入手机号码" : (checkResult = checkMoblie(inputVal) ? "ok" : "手机号码不正确");
            if (errText == "ok") {
                // java判断手机号是否有注册过，如果没有注册过，则执行以下一句
                $.ajax({
                    async: false,
                    type: 'POST',
                    url: '${base}/validateUserName.json',
                    data: {userName: $("#username").val()},
                    dataType: 'json',
                    success: function (data) {
                        if (data) {
                            errText = "ok";
                        } else {
                            errText = "用户名或密码错误";
                        }
                    }
                });
            }

            if (errText == "ok") {
                inputCheck.hideErr($username);
                return true;
            } else {
                inputCheck.errText = errText
                inputCheck.showErr($username);
                changeCaptcha();
                return false;
            }
        }

        function check_pwd($pwd) {
            var inputVal = $pwd.val();
            var errText = "ok";
            errText = inputVal.length == 0 ? "请输入密码" : (checkLenSection(inputVal,6, 30) ? "ok" : "密码长度应为6～30位");
            if (errText == "ok") {
                inputCheck.hideErr($pwd);
                return true;
            } else {
                inputCheck.errText = errText;
                inputCheck.showErr($pwd);
                changeCaptcha();
                return false;
            }
        }

        // 记住用户名
        if (getCookie("adminUsername") != null) {
            $("#isRememberUsername").prop("checked", true);
            $("#username").val(getCookie("adminUsername"));
            $("#password").focus();
        } else {
            $("#isRememberUsername").prop("checked", false);
            $("#username").focus();
        }

        //session失效时，父窗口跳转至登录页面
        var _topWin = window;
        while (_topWin != _topWin.parent.window) {
            _topWin = _topWin.parent.window;
        }
        if (window != _topWin){
            _topWin.document.location.href = '${base}/loginIn.html';
        }

    <#if (errMsg)?? && errMsg != "">
        inputCheck.errText = "${errMsg}";
        inputCheck.showErr();
    </#if>
    });

    //获得焦点边框加亮
    $('.inputText').focus(function(){
        $(this).css('border-color','#069bdf');
    }).blur(function(){
        $(this).css('border-color','#cacaca');
    });

    // 更换验证码
    function changeCaptcha() {
        $("#captchaImage").attr("src", "${base}/common/captcha.html?captchaId=${captchaId}");
    }
</script>
</html>