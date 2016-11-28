<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>交易记录列表</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
        <link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css">

        <script type="text/javascript" src="${base}/resources/js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
        <script type="text/javascript">
            
        </script>
    </head>
    
    <body>
        <div style="width:100%;">
            <table class="table_gray">
                <thead>
                    <tr>
                        <th>代理人姓名</th>
                        <th>手机号</th>
                        <th>商户ID</th>
                        <th>交易数量</th>
                        <th>交易金额</th>
                        <th>交易佣金比例</th>
                        <th>佣金金额</th>
                        <th>创建时间</th>
                    </tr>
                </thead>
                <tbody>
                    <#list records as record>
                    <tr>
                        <td>
                            ${record.agentName}
                        </td>
                        <td>
                            ${record.mobileNo}
                        </td>
                        <td>
                            ${record.merchantId}
                        </td>
                        <td class="ta_right">
                            ${record.tradeCount}
                        </td>
                        <td class="ta_right">
                            ${(record.tradeAmount?string("0.00"))!"-"}
                        </td>
                        <td class="ta_right">
                            ${(record.tradeAgentPercent?string("0.00%"))!"-"}
                        </td>
                        <td class="ta_right">
                            ${(record.agentAmount?string("0.00"))!"-"}
                        </td>
                        <td>
                            ${record.gmtCreated?string("yyyy-MM-dd HH:mm")}
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </body>
</html>