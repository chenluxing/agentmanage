<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>新增代理人</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>

        <link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css">

        <script type="text/javascript" src="${base}/resources/js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
        <script type="text/javascript">
            
        </script>
    </head>
    <body>
        <form id="inputForm" action="${base}/agent/add.html" method="post">
            <div style="width:600px;">
                <table class="table_gray">
                    <tbody>
                        <tr>
                            <td class="bg_gray">手机号码</td>
                            <td>
                                <input type="text" id="mobileNo" name="mobileNo" placeholder="请填写手机号码" />
                            </td>
                        </tr>
                        <tr>
                            <td class="bg_gray">姓名</td>
                            <td>
                                <input type="text" id="realName" name="realName" placeholder="请填写姓名" />
                            </td>
                        </tr>
                        <tr>
                            <td class="bg_gray">商户ID</td>
                            <td>
                                <input type="text" id="merchantId" name="merchantId" placeholder="请填写商户ID" />
                            </td>
                        </tr>
                        <tr>
                            <td class="bg_gray">支付宝账号</td>
                            <td>
                                <input type="text" id="alipayNo" name="alipayNo" placeholder="请填写支付宝账号" />
                            </td>
                        </tr>
                        <tr>
                            <td class="bg_gray">佣金利率</td>
                            <td>
                                <input type="text" id="agentPercent" name="agentPercent" placeholder="请填写佣金利率" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="新增" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>