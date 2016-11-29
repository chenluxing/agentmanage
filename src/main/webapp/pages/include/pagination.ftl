<#if (page.totalPages>1)>
<div class="pagination">
    <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize}" />
    <input type="hidden" id="pageNumber" name="pageNumber" value="${page.pageNumber}" />
    <#if (page.totalPages <= 7)>
        <#list 1..page.totalPages as index>
            <a class="${(page.pageNumber==index)?string("curpage","")}" href="javascript: $.pageSkip(${index});">${index}</a>
        </#list>
    <#else>
        <#if (page.pageNumber <= 4)>
            <#list 1..6 as index>
                <a class="${(page.pageNumber==index)?string("curPage","")}" href="javascript: $.pageSkip(${index});">${index}</a>
            </#list>
            ...
            <a class="lastPage" href="javascript: $.pageSkip(${page.totalPages});">${page.totalPages}</a>
        <#elseif (page.pageNumber > 4) && (page.pageNumber < page.totalPages-3)>
            <a class="firstPage" href="javascript: $.pageSkip(1);">1</a>
            ...
            <#list (page.pageNumber-2)..(page.pageNumber+2) as index>
                <a class="${(page.pageNumber==index)?string("curPage","")}" href="javascript: $.pageSkip(${index});">${index}</a>
            </#list>
            ...
            <a class="lastPage" href="javascript: $.pageSkip(${page.totalPages});">${page.totalPages}</a>
        <#else>
            <a class="firstPage" href="javascript: $.pageSkip(1);">1</a>
            ...
            <#list (page.totalPages-5)..(page.totalPages) as index>
                <a class="${(page.pageNumber==index)?string("curPage","")}" href="javascript: $.pageSkip(${index});">${index}</a>
            </#list>
        </#if>
    </#if>
</div>
</#if>