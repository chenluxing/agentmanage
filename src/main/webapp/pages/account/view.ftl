<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>账户信息</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>

        <link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="path">
            <span class="pahome"></span>
            <a href="${base}/common/index.html">首页</a>
            &nbsp;><span>&nbsp;代理人管理</span>
            &nbsp;><span>&nbsp;账户详情</span>
        </div>
        <div style="width:600px;">
            <table class="table_gray">
                <tbody>
                    <tr>
                        <td class="bg_gray">手机号码</td>
                        <td>
                            ${agentAccountVo.mobileNo}
                        </td>
                    </tr>
                    <tr>
                        <td class="bg_gray">姓名</td>
                        <td>
                            ${agentAccountVo.realName}
                        </td>
                    </tr>
                    <tr>
                        <td class="bg_gray">商户ID</td>
                        <td>
                            ${(agentAccountVo.merchantId)!"-"}
                        </td>
                    </tr>
                    <tr>
                        <td class="bg_gray">支付宝账号</td>
                        <td>
                            ${agentAccountVo.alipayNo}
                        </td>
                    </tr>
                    <tr>
                        <td class="bg_gray">佣金利率</td>
                        <td>
                            ${agentAccountVo.agentPercent?string("0.00%")}
                        </td>
                    </tr>
                    <tr>
                        <td class="bg_gray">交易总额</td>
                        <td>
                            ${agentAccountVo.totalAmount?string("0.00")}
                        </td>
                    </tr>
                    <tr>
                        <td class="bg_gray">佣金总额</td>
                        <td>
                            ${agentAccountVo.totalAgentAmount?string("0.00")}
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <a href="javascript:void(0)" onclick="window.history.go(-1)">返回</a>
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>