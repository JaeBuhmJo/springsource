/**
 * 
 */
const form = document.querySelector("form");

form.addEventListener("submit",(e)=>{
	e.preventDefault();
	const criteria = document.querySelector("[name='criteria']");
	const keyword = document.querySelector("[name='keyword']");
	
	if(criteria.value==="검색기준선택"){
		alert("검색 기준을 선택하세요");
		criteria.focus();
		return;
	}
	if(keyword.value==""){
		alert("검색어를 입력하세요");
		keyword.select();
		return;
	}
	form.submit();
})