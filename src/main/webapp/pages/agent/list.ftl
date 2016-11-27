<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>代理人列表</title>
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