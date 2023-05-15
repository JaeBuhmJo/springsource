/**
 * 
 */

document.querySelector(".btn-success").addEventListener("click",()=>{
	location.href=path
})

const form = document.querySelector("form");
form.addEventListener("submit", (e) => {
	e.preventDefault();

	const code = document.querySelector("#code");
	const title = document.querySelector("#title");
	const writer = document.querySelector("#writer");
	const price = document.querySelector("#price");

	if (isNaN(code.value) || code.value.length != 4) {
		alert("올바른 도서 코드를 입력하세요");
		code.select();
		return;
	}
	if (title.value == "") {
		alert("도서명을 입력하세요");
		title.focus();
		return;
	}
	if (writer.value == "") {
		alert("저자명을 입력하세요");
		writer.focus();
		return;
	}
	if (isNaN(price.value) || price.value.length > 8||price.value=="") {
		alert("올바른 가격을 입력하세요");
		price.select();
		return;
	}
	
	form.submit();
})