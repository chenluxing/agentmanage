<input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}" />

    <div class="pagination">
        <input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNumber}" />
        <a class="firstPage" href="javascript: $.pageSkip(1);">首页</a>
...
        <a class="firstPage" href="javascript: $.pageSkip(${page.totalPages});">末页</a>
    </div>
