/**
 * 
 */

document.querySelector(".btn-primary").addEventListener("click", () => {
	location.href = listPath;
})

document.querySelector(".btn-danger").addEventListener("click", () => {
	location.href = removePath+"?code=" + code;
})

const form = document.querySelector("form");
form.addEventListener("submit", (e) => {
	e.preventDefault();

	const title = document.querySelector("#title");
	const writer = document.querySelector("#writer");
	const price = document.querySelector("#price");

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
	if (isNaN(price.value) || price.value.length > 8 || price.value == "") {
		alert("올바른 가격을 입력하세요");
		price.select();
		return;
	}
	form.submit();
})