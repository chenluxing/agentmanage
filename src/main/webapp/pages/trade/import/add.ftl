<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>新增交易记录</title>
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
            .table_gray td {
                padding: 8px;
                border-bottom: #ddd solid 1px;
                border-right: #ddd solid 1px;
                text-align: center;
            }
            .bg_gray {
                background-color: #faf9f9;
            }
        </style>
        
        <script type="text/javascript">
            
        </script>
    </head>
    <body>
        <form id="inputForm" action="${base}/trade/import/add.html" method="post" enctype="multipart/form-data">
            <div style="width:600px;">
                <table class="table_gray">
                    <tbody>
                        <tr>
                            <td class="bg_gray">请选择附件</td>
                            <td>
                                <input type="file" id="file" name="file"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <input type="submit" value="上传" />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </form>
    </body>
</html>