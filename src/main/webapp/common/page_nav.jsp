<%@ page contentType="text/html; charset=UTF-8" language="java" %>


		<%-- 分頁條的開始 --%>
		<div id="page_nav">
			<%-- 大於首頁，才顯示
			注意:請將表達式寫在{}裡面,因為我自己很常寫成 => ${sessionScope.page.pageNo} > 1
			--%>
			<c:if test="${sessionScope.page.pageNo > 1}">
				<a href="${sessionScope.page.url}pageNo=1">首頁</a>
				<a href="${sessionScope.page.url}pageNo=${sessionScope.page.pageNo-1}">上一頁</a>
			</c:if>


			<%-- 頁碼輸出的開始 --%>
			<c:choose>
				<%-- 總頁碼小於等於 5 的情況 --%>
				<c:when test = "${sessionScope.page.pageNo <= 5}">
					<c:set var = "begin" value = "1"></c:set>
					<c:set var = "end" value = "${sessionScope.page.pageTotal}"></c:set>
				</c:when>
				
				<%-- 總頁碼大於 5 的情況,底下還有分 3 種情況 --%>
				<c:when test="${sessionScope.page.pageTotal > 5 }">
					<c:choose>
					
						<%-- 當前頁碼是 1~3 的情況,頁碼範圍是: 1~5 --%>
						<c:when test="${sessionScope.page.pageNo <= 3 }">
							<c:set var = "begin" value = "1"></c:set>
							<c:set var = "end" value = "5"></c:set>				
						</c:when>
						
						<%-- 當前頁碼為 最後3個 的情況,頁碼範圍是: 總頁碼 - 4 ~ 總頁碼 --%>
						<c:when test="${sessionScope.page.pageNo > sessionScope.page.pageTotal-3 }">
							<c:set var = "begin" value = "${sessionScope.page.pageTotal - 4}"></c:set>
							<c:set var = "end" value = "${sessionScope.page.pageTotal}"></c:set>						
						</c:when>
						
						<%-- 其他情況, 頁碼範圍: 當前頁碼 - 2 ~ 當前頁碼 + 2 --%>
						<c:otherwise>
							<c:set var = "begin" value = "${sessionScope.page.pageNo - 2}"></c:set>
							<c:set var = "end" value = "${sessionScope.page.pageNo + 2}"></c:set>
						</c:otherwise>
						
						
					</c:choose>
				</c:when>
			</c:choose>


			<c:forEach begin="${begin}"
				end="${end}" var="i">
				
				<c:if test="${i == sessionScope.page.pageNo }">
									${i}
				</c:if>
				
				<c:if test="${i != sessionScope.page.pageNo }">
					<a href="${sessionScope.page.url}pageNo=${i}"> ${i} </a>
				</c:if>
				
			</c:forEach>


			<%-- 頁碼輸出的結束 --%>
			
			<%-- 如果已經是最後一頁,則不顯示下一頁,末頁 --%>
			<c:if test="${sessionScope.page.pageNo < sessionScope.page.pageTotal}">
				<a href="${sessionScope.page.url}pageNo=${sessionScope.page.pageNo+1}">下一頁</a>
				<a href="${sessionScope.page.url}pageNo=${sessionScope.page.pageTotal}">末頁</a>
			</c:if>
			 
			共${ sessionScope.page.pageTotal }頁，${ sessionScope.page.pageTotalCount }條紀錄
			
		</div>
		<%-- 分頁條的結束 --%>



