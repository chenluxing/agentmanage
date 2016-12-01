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
        <div class="path">
            <span class="pahome"></span>
            <a href="${base}/common/index.html">首页</a>
            &nbsp;><span>&nbsp;交易管理</span>
            &nbsp;><span>&nbsp;交易记录列表（总数：<i>${page.total}</i> 条）</span>
        </div>
        <form id="listForm" action="${base}/trade/record/list.html" method="post">
            <div class="div_condition">
                <table class="table_condition">
                    <tr>
                        <td width="350px">
                            <i>姓名</i>
                            <input type="text" class="text" name="merchantId"/>
                        </td>
                        <td width="350px">
                            <i>商户ID</i>
                            <input type="text" class="text" name="realName"/>
                        </td>
                        <td width="400px">
                            <i>创建时间</i>
                            <input type="text" id="beginDate" name="beginDate" class="text Wdate" style="width:100px; margin:0px;" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'});" value="${(beginDate?string("yyyy-MM-dd"))!""}"/>
                            <input type="text" id="endDate" name="endDate" class="text Wdate" style="width:100px; margin:0px;" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'});" value="${(endDate?string("yyyy-MM-dd"))!""}"/>
                        </td>
                        <td></td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align: center">
                            <input type="button" value="查询"/>
                        </td>
                    </tr>
                </table>
            </div>
            <a href="${base}/trade/import/toAdd.html">新增</a>
            <div style="width:100%;">
                <table class="table_gray" style="min-width: 1080px;">
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
                        <#list page as importLog>
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
                <#include "/include/pagination.ftl" />
            </div>
        </form>
    </body>
</html>