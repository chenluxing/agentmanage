<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>代理人列表</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>

        <link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css">

        <script type="text/javascript" src="${base}/resources/js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
        <script type="text/javascript">

        </script>
    </head>

    <body>
        <div style="100%">
            <table class="table_gray">
                <thead>
                    <tr>
                        <th>姓名</th>
                        <th>手机号</th>
                        <th>商户ID</th>
                        <th>支付宝账号</th>
                        <th>佣金利率</th>
                        <th>操作</th>
                    </tr>
                </thead>
                <tbody>
                <#list agentList as agent>
                    <tr>
                        <td>
                            <a href="${base}/agent/account/view.html?agentId=${agent.id}">${agent.realName}</a>
                        </td>
                        <td>${agent.mobileNo}</td>
                        <td>${agent.merchantId}</td>
                        <td>${agent.alipayNo}</td>
                        <td>${(agent.agentPercent?string("0.0000"))!"-"}</td>
                        <td>
                            <a href="${base}/agent/toEdit.html?agentId=${agent.id}">[编辑]</a>
                        </td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </body>
</html>