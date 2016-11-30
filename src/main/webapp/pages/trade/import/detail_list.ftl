<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>导入记录明细</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>

        <link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css">

        <script type="text/javascript" src="${base}/resources/js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
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
                        <td class="td_amount">
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