<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>新增代理人</title>
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
                        mobileNo:{
                            required: true,
                            isMobile: true,
                            remote: {
                                type: "POST",
                                url: "${base}/agent/checkMobile.json"
                            }
                        },
                        realName:{
                            required: true,
                            remote: {
                                type: "POST",
                                url: "${base}/agent/checkRealName.json"
                            }
                        },
                        alipayNo:{
                            required: true
                        },
                        agentPercent: {
                            required: true,
                            isDecimalTwo: true
                        }
                    },
                    messages: {
                        mobileNo:{
                            remote: "手机号已经存在"
                        },
                        realName:{
                            remote: "同名用户已经存在"
                        }
                    }
                });

                $("#btnSubmit").click(function(){
                    disableBtn();
                    $("#inputForm").submit();
                    removedisableBtn();
                })

            });
        </script>
    </head>
    <body>
        <div class="path">
            <span class="pahome"></span>
            <a href="${base}/common/index.html">首页</a>
            &nbsp;><span>&nbsp;代理人管理</span>
            &nbsp;><span>&nbsp;新增代理人</span>
        </div>
        <form id="inputForm" action="${base}/agent/add.html" method="post">
            <div style="width:600px;">
                <table class="table_input">
                    <tbody>
                        <tr>
                            <td class="td_title bg_gray" style="width: 160px;">手机号码</td>
                            <td class="td_content">
                                <input type="text" id="mobileNo" name="mobileNo" class="text" placeholder="请填写手机号码" />
                            </td>
                        </tr>
                        <tr>
                            <td class="td_title bg_gray">姓名</td>
                            <td class="td_content">
                                <input type="text" id="realName" name="realName" class="text" placeholder="请填写姓名" />
                            </td>
                        </tr>
                        <tr>
                            <td class="td_title bg_gray">支付宝账号</td>
                            <td class="td_content">
                                <input type="text" id="alipayNo" name="alipayNo" class="text" placeholder="请填写支付宝账号" />
                            </td>
                        </tr>
                        <tr>
                            <td class="td_title bg_gray">佣金利率</td>
                            <td class="td_content">
                                <input type="text" id="agentPercent" name="agentPercent" class="text" placeholder="请填写佣金利率" />元/万元
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="td_content" style="text-align: center;">
                                <input type="button" id="btnSubmit" class="btn_normal" value="新增" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>