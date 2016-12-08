<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>代理人列表</title>
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
            &nbsp;><span>&nbsp;代理人管理</span>
            &nbsp;><span>&nbsp;代理人管理（总数：<i>${page.total}</i> 条）</span>
        </div>
        <form id="listForm" action="${base}/agent/list.html" method="post">
            <div class="div_condition">
                <table class="table_condition">
                    <tr>
                        <td width="350px">
                            <i>姓名</i>
                            <input type="text" id="realName" name="realName"class="text" value="${(realName)!""}" maxlength="10"/>
                        </td>
                        <td width="350px">
                            <i>手机号</i>
                            <input type="text" id="mobileNo" name="mobileNo" class="text" value="${(mobileNo)!""}" maxlength="11"/>
                        </td>
                        <td width="350px">
                            <i>商户ID</i>
                            <input type="text" id="merchantId" name="merchantId" class="text" value="${(merchantId)!""}" maxlength="32"/>
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
            <div style="100%">
                <table class="table_gray" style="min-width: 1080px;">
                    <thead>
                        <tr>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>商户ID</th>
                            <th>支付宝账号</th>
                            <th>佣金利率</th>
                            <th>创建时间</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                    <#list page as agent>
                        <tr>
                            <td>
                                ${agent.realName}
                            </td>
                            <td>
                                ${agent.mobileNo}
                            </td>
                            <td>
                                ${(agent.merchantId)!"-"}
                            </td>
                            <td>
                                ${agent.alipayNo}
                            </td>
                            <td>
                                ${(agent.agentPercent?string("0.00%"))!"-"}
                            </td>
                            <td>
                                ${agent.gmtCreated?string("yyyy-MM-dd HH:mm")}
                            </td>
                            <td width="150px">
                                <a href="${base}/agent/account/view.html?agentId=${agent.id}">[账户信息]</a>
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