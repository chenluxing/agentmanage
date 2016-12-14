<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>编辑代理人</title>
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
                        agentPercent: {
                            required: true,
                            isDecimalFour: true
                        }
                    },
                    messages: {
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
            &nbsp;><span>&nbsp;编辑代理人</span>
        </div>
        <form id="inputForm" action="${base}/agent/edit.html" method="post">
            <input type="hidden" id="agentId" name="agentId" value="${agentInfo.id}">
            <div style="width:600px;">
                <table class="table_input">
                    <tbody>
                        <tr>
                            <td class="td_title bg_gray" style="width: 160px;">手机号码</td>
                            <td class="td_content">
                                <input type="text" id="mobileNo" name="mobileNo" class="text" value="${agentInfo.mobileNo}" readonly />
                            </td>
                        </tr>
                        <tr>
                            <td class="td_title bg_gray">姓名</td>
                            <td class="td_content">
                                <input type="text" id="realName" name="realName" class="text" value="${agentInfo.realName}" readonly />
                            </td>
                        </tr>
                        <tr>
                            <td class="td_title bg_gray">支付宝账号</td>
                            <td class="td_content">
                                <input type="text" id="alipayNo" name="alipayNo" class="text" value="${agentInfo.alipayNo}" readonly />
                            </td>
                        </tr>
                        <tr>
                            <td class="td_title bg_gray">佣金利率</td>
                            <td class="td_content">
                                <input type="text" id="agentPercent" name="agentPercent" class="text" value="${(agentInfo.agentPercent?string("0.00"))!""}" placeholder="请填写佣金利率" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="td_content" style="text-align: center;">
                                <input type="button" id="btnSubmit" class="btn_normal" value="保存" />
                                <input type="button" class="btn_normal" onclick="window.history.go(-1)" value="返回" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>