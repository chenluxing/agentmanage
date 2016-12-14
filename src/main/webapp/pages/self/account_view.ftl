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
            &nbsp;><span>&nbsp;我的账户</span>
            &nbsp;><span>&nbsp;账户信息</span>
        </div>
        <div style="width:600px;">
            <table class="table_input">
                <tbody>
                    <tr>
                        <td class="td_title bg_gray" style="width: 180px;">手机号码</td>
                        <td class="td_content">
                            ${agentAccountVo.mobileNo}
                        </td>
                    </tr>
                    <tr>
                        <td class="td_title bg_gray">姓名</td>
                        <td class="td_content">
                            ${agentAccountVo.realName}
                        </td>
                    </tr>
                    <tr>
                        <td class="td_title bg_gray">支付宝账号</td>
                        <td class="td_content">
                            ${agentAccountVo.alipayNo}
                        </td>
                    </tr>
                    <tr>
                        <td class="td_title bg_gray">佣金利率</td>
                        <td class="td_content">
                            ${agentAccountVo.agentPercent?string("0.00")} 元/万元
                        </td>
                    </tr>
                    <tr>
                        <td class="td_title bg_gray">交易总额</td>
                        <td class="td_content">
                            ${agentAccountVo.totalAmount?string("0.00")}
                        </td>
                    </tr>
                    <tr>
                        <td class="td_title bg_gray">佣金总额</td>
                        <td class="td_content">
                            ${agentAccountVo.totalAgentAmount?string("0.00")}
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </body>
</html>