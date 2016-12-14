<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
        <form id="listForm" action="${base}/trade/record/list.html" method="post">
            <div class="div_condition">
                <table class="table_condition">
                    <tr>
                        <td width="350px">
                            <i>姓名</i>
                            <input type="text" class="text" name="realName" value="${(realName)!""}"/>
                        </td>
                        <td width="400px">
                            <i>创建时间</i>
                            <input type="text" id="beginDate" name="beginDate" class="text Wdate" style="width:100px; margin:0px;" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'});" value="${(beginDate?string("yyyy-MM-dd"))!""}"/>
                            <input type="text" id="endDate" name="endDate" class="text Wdate" style="width:100px; margin:0px;" onfocus="WdatePicker({dateFmt: 'yyyy-MM-dd'});" value="${(endDate?string("yyyy-MM-dd"))!""}"/>
                        </td>
                        <td width="50px"></td>
                    </tr>
                    <tr>
                        <td colspan="4" style="text-align: center">
                            <input type="button" id="btnSubmit" class="btn_normal" value="查询" />
                        </td>
                    </tr>
                </table>
            </div>
            <div style="width:100%;">
                <table class="table_gray" style="min-width: 1080px;">
                    <thead>
                        <tr>
                            <th>代理人姓名</th>
                            <th>手机号</th>
                            <th>交易数量</th>
                            <th>交易金额</th>
                            <th>佣金比例（元/万元）</th>
                            <th>佣金金额</th>
                            <th>创建时间</th>
                        </tr>
                    </thead>
                    <tbody>
                        <#list page as record>
                        <tr>
                            <td>
                                ${record.agentName}
                            </td>
                            <td>
                                ${record.mobileNo}
                            </td>
                            <td class="td_amount">
                                ${record.tradeCount}
                            </td>
                            <td class="td_amount">
                                ${(record.tradeAmount?string("0.00"))!"-"}
                            </td>
                            <td class="td_amount">
                                ${(record.tradeAgentPercent?string("0.00"))!"-"}
                            </td>
                            <td class="td_amount">
                                ${(record.agentAmount?string("0.00"))!"-"}
                            </td>
                            <td>
                                ${record.gmtCreated?string("yyyy-MM-dd HH:mm")}
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