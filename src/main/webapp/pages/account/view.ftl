<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>账户信息</title>
        <style rel="stylesheet" type="text/css">
            body {
                color: #7d7268;
            }
            .table_gray {
                border-collapse: collapse;
                border-spacing: 0px;
                width: 100%;
                border-top: #ddd solid 1px;
                border-left: #ddd solid 1px;
            }
            .table_gray th {
                padding: 10px;
                border-bottom: #ddd solid 1px;
                border-right: #ddd solid 1px;
                text-align: center;
                font-weight: bold;
                background: #f5f5f5;
            }
            .table_gray td {
                padding: 8px;
                border-bottom: #ddd solid 1px;
                border-right: #ddd solid 1px;
                text-align: center;
            }
            .bg_gray {
                background-color: #faf9f9;
            }
        </style>
        
        <script type="text/javascript">
            
        </script>
    </head>
    <body>
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
                            ${agentAccountVo.merchantId}
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