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
        <div class="path">
            <span class="pahome"></span>
            <a href="${base}/common/index.html">首页</a>
            &nbsp;><span>&nbsp;代理人管理</span>
            &nbsp;><span>&nbsp;代理人管理（总数：<i>${page.total}</i> 条）</span>
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
            <div style="100%">
                <table class="table_gray" style="min-width: 1080px;">
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
                    <#list page as agent>
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
                <#include "/include/pagination.ftl" />
            </div>
        </form>
    </body>
</html>