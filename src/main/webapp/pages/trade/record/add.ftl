<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>新增交易记录</title>
        <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
        <link href="${base}/resources/css/main.css" rel="stylesheet" type="text/css">

        <script type="text/javascript" src="${base}/resources/js/jquery-1.11.3.min.js"></script>
        <script type="text/javascript" src="${base}/resources/js/jquery.validate.js"></script>
        <script type="text/javascript">
            $().ready(function () {
                // 表单验证
                $("#inputForm").validate({
                    rules: {
                        merchantId: {
                            required: true,
                            remote: {
                                type: "POST",
                                url: "${base}/agent/checkSurNo.json",
                                data: {
                                    merchantId: function () {
                                        return $("#merchantId").val();
                                    }
                                }
                            }
                        },
                        tradeAmount: {
                            required: true,
                            isNumber: true
                        },
                        tradeCount: {
                            required: true
                        }
                    },
                    messages: {
                        remote:"商户ID信息不存在"
                    }
                });
            });
        </script>
    </head>
    <body>
        <form id="inputForm" action="${base}/trade/record/add.html" method="post">
            <div style="width:600px;">
                <table class="table_gray">
                    <tbody>
                        <tr>
                            <td class="bg_gray">商户ID</td>
                            <td>
                                <input type="text" class="text" id="merchantId" name="merchantId" placeholder="请填写商户ID" />
                            </td>
                        </tr>
                        <tr>
                            <td class="bg_gray">交易金额</td>
                            <td>
                                <input type="text" class="text" id="tradeAmount" name="tradeAmount" placeholder="交易金额" />
                            </td>
                        </tr>
                        <tr>
                            <td class="bg_gray">交易数量</td>
                            <td>
                                <input type="text" class="text" id="tradeCount" name="tradeCount" placeholder="交易数量" />
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