<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>修改密码</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>

        <link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css">

        <script type="text/javascript" src="${base}/resources/js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
        <script type="text/javascript" src="${base}/resources/js/input.js"></script>
        <script type="text/javascript">
            $().ready(function () {
                // 表单验证
                $("#inputForm").validate({
                    rules: {
                        password: {
                            required: true,
                            minlength: 6,
                            remote: {
                                type: "POST",
                                async:false,
                                url: "${base}/user/checkPassword.json"
                            }
                        },
                        newPassword: {
                            required: true,
                            minlength: 6
                        },
                        surePassword: {
                            required: true,
                            minlength: 6
                        }
                    },
                    messages: {
                        password: {
                            remote: "原密码不正确"
                        }
                    }
                });

                $("#btnSubmit").click(function () {
                    disableBtn();
                    if($("#newPassword").val() != $("#surePassword").val()) {
                        alert("两次输入密码不一致");
                        removedisableBtn();
                        return;
                    }
                    $("#inputForm").submit();
                    removedisableBtn();
                })
            })
        </script>
    </head>
    <body>
        <div class="path">
            <span class="pahome"></span>
            <a href="${base}/common/index.html">首页</a>
            &nbsp;><span>&nbsp;我的账户</span>
            &nbsp;><span>&nbsp;修改密码</span>
        </div>
        <form id="inputForm" action="${base}/user/changePassword.html" method="post">
            <div style="width:600px;">
                <table class="table_input">
                    <tbody>
                        <tr>
                            <td class="td_title bg_gray" style="width: 160px;">原密码</td>
                            <td class="td_content">
                                <input type="password" id="password" name="password" class="text" maxlength="20" placeholder="请填写原密码" />
                            </td>
                        </tr>
                        <tr>
                            <td class="td_title bg_gray">新密码</td>
                            <td class="td_content">
                                <input type="password" id="newPassword" name="newPassword" class="text" maxlength="20" placeholder="请填写新密码" />
                            </td>
                        </tr>
                        <tr>
                            <td class="td_title bg_gray">确认密码</td>
                            <td class="td_content">
                                <input type="password" id="surePassword" name="surePassword" class="text" maxlength="20" placeholder="请确认密码" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="td_content" style="text-align: center;">
                                <input type="button" id="btnSubmit" class="btn_normal" value="保存" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>