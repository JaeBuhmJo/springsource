/**
 * 
 */

const form = document.querySelector("form");

form.addEventListener("submit", (e) => {
	e.preventDefault();
	const curpwd = document.querySelector("#floatingPassword1");
	const newpwd = document.querySelector("#floatingPassword2");
	const confirmpwd = document.querySelector("#floatingPassword3");
	if (curpwd.value == "") {
		alert("현재 비밀번호 입력");
		newpwd.select();
		return;
	}
	if (newpwd.value == "") {
		alert("변경할 비밀번호 입력");
		newpwd.select();
		return;
	}
	if (confirmpwd.value == "") {
		alert("변경할 비밀번호 확인 입력");
		confirmpwd.select();
		return;
	}
	if (newpwd.value != confirmpwd.value) {
		alert("비밀번호 확인이 변경할 비밀번호와 일치하지 않습니다");
		confirmpwd.select();
		return;
	}
	form.submit();
})