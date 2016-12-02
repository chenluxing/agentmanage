<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>交易记录列表</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>

        <link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css">
        <link href="${base}/resources/css/pagination.css" rel="stylesheet" type="text/css">

        <script type="text/javascript" src="${base}/resources/js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="${base}/resources/plugin/datePicker/WdatePicker.js"></script>
        <script type="text/javascript" src="${base}/resources/js/list.js"></script>
        <script type="text/javascript">
            $().ready(function () {
                $("#btnSubmit").click(function(){
                    interruptedBtn();
                    $("#listForm").submit();
                })

                $("#btnImport").click(function(){
                    window.location.href = "${base}/trade/import/toAdd.html";
                })
            })
        </script>
    </head>
    
    <body>
        <div class="path">
            <span class="pahome"></span>
            <a href="${base}/common/index.html">首页</a>
            &nbsp;><span>&nbsp;交易管理</span>
            &nbsp;><span>&nbsp;交易记录列表（总数：<i>${page.total}</i> 条）</span>
        </div>
        <form id="listForm" action="${base}/trade/import/list.html" method="post">
            <div class="div_condition">
                <table class="table_condition">
                    <td width="400px">
                        <i>创建时间</i>
                        <input type="text" id="beginDate" name="beginDate" class="text Wdate" style="width:100px; margin:0px;" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'});" value="${(beginDate?string("yyyy-MM-dd"))!""}"/>
                        &nbsp;<input type="text" id="endDate" name="endDate" class="text Wdate" style="width:100px; margin:0px;" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'});" value="${(endDate?string("yyyy-MM-dd"))!""}"/>
                    </td>
                    <td width="200px">
                        <input type="button" id="btnSubmit" class="btn_normal" value="查询" />
                    </td>
                    <td></td>
                </table>
            </div>
            <div style="padding-bottom: 10px;">
                <input type="button" id="btnImport" class="btn_normal" value="新增导入" />
            </div>
            <div style="width:100%;">
                <table class="table_gray">
                    <thead>
                        <tr>
                            <th>导入数据量</th>
                            <th>有效数据量</th>
                            <th>交易总数量</th>
                            <th>交易总金额</th>
                            <th>创建时间</th>
                            <th>操作</th>
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
                                ${importLog.totalTradeAmount?string("0.00")}
                            </td>
                            <td>
                                ${importLog.gmtCreated?string("yyyy-MM-dd HH:mm")}
                            </td>
                            <td width="100px">
                                <a href="${base}/trade/import/detail/list.html?logId=${importLog.id}">[明细]</a>
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