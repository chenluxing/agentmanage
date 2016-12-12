<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>导入记录明细</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>

        <link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css">
        <link href="${base}/resources/css/pagination.css" rel="stylesheet" type="text/css">

        <script type="text/javascript" src="${base}/resources/js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="${base}/resources/js/list.js"></script>
        <script type="text/javascript">
            
        </script>
    </head>
    
    <body>
        <div class="path">
            <span class="pahome"></span>
            <a href="${base}/common/index.html">首页</a>
            &nbsp;><span>&nbsp;交易管理</span>
            &nbsp;><span>&nbsp;导入记录明细（总数：<i>${page.total}</i> 条）</span>
        </div>
        <div style="padding-top: 10px; padding-right: 30px; height: 40px; float: right;">
            <input type="button" class="btn_normal" onclick="window.history.go(-1)" value="返回" />
        </div>
        <form id="listForm" action="${base}/trade/import/detail/list.html" method="post">
            <input type="hidden" name="logId" value="${logId}">
            <div style="width:100%;">
                <table class="table_gray">
                    <thead>
                    <tr>
                        <th>代理人姓名</th>
                        <th>交易数量</th>
                        <th>交易金额</th>
                        <th>创建时间</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list page as importDetail>
                    <tr>
                        <td>
                        ${importDetail.agentName}
                        </td>
                        <td class="td_amount">
                        ${importDetail.tradeCount}
                        </td>
                        <td class="td_amount">
                        ${importDetail.tradeAmount}
                        </td>
                        <td>
                        ${importDetail.gmtCreated?string("yyyy-MM-dd HH:mm")}
                        </td>
                    </tr>
                    </#list>
                    </tbody>
                </table>
            <#include "/include/pagination.ftl" />
            </div>
        </form>
    </body>
</html>