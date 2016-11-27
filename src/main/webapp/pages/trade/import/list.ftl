<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>交易记录列表</title>
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
            .table_gray .ta_right {
                text-align: right;
                padding-right: 20px;
            }
            .table_gray td {
                padding: 8px;
                border-bottom: #ddd solid 1px;
                border-right: #ddd solid 1px;
                text-align: center;
            }
        </style>
        
        <script type="text/javascript">
            
        </script>
    </head>
    
    <body>
        <a href="${base}/trade/import/toAdd.html">新增</a>
        <div style="width:100%;">
            <table class="table_gray">
                <thead>
                    <tr>
                        <th>导入数据量</th>
                        <th>有效数据量</th>
                        <th>交易总数量</th>
                        <th>交易总金额</th>
                        <th>创建时间</th>
                    </tr>
                </thead>
                <tbody>
                    <#list importlogList as importLog>
                    <tr>
                        <td>
                            ${importLog.dataCount}
                        </td>
                        <td>
                            ${importLog.usefulCount}
                        </td>
                        <td>
                            ${importLog.totalTradeCount}
                        </td>
                        <td class="ta_right">
                            ${importLog.totalTradeAmount}
                        </td>
                        <td>
                            ${importLog.gmtCreated?string("yyyy-MM-dd HH:mm")}
                        </td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </body>
</html>