checkModal(result);

history.replaceState({}, null, null);

function checkModal(result) {
  if (result === "" || history.state) return;

  if (parseInt(result) > 0) {
    document.querySelector(".modal-body").innerHTML =
      "게시글 " + result + "번이 등록되었습니다. ";
  } else {
    document.querySelector(".modal-body").innerHTML = result;
  }

  $("#registerModal").modal("show");
}

const operForm = document.getElementById("operForm");

const amount = document.querySelector("#amount");
amount.addEventListener("change", (e) => {
  document.querySelector("#operForm input[name='amount']").value = e.target.value;
  operForm.action = "/board/list";
  operForm.submit();
});

document.querySelector(".pagination").addEventListener("click", (e) => {
  e.preventDefault();

  if (e.target.tagName === "A") {
    document.querySelector("#operForm input[name='page']").value =
      e.target.getAttribute("href");
    operForm.action = "/board/list";
    operForm.submit();
  }
});

const moves = document.querySelectorAll(".move");

moves.forEach((element) => {
  element.addEventListener("click", (e) => {
    e.preventDefault();

    const href = element.getAttribute("href");
    const bno = "<input type='hidden' name='bno' value='" + href + "'/>";

    operForm.insertAdjacentHTML("beforeend", bno);
    operForm.action = "/board/read";
    operForm.submit();
  });
});

  window.onpageshow = function (event) {
  // persisted == true : webpage가 로딩될 때 캐시에서 읽어왔음
  if (event.persisted) {
    location.reload();
  }
};

const searchform = document.querySelector("#searchForm");
searchform.addEventListener("submit", (e) => {
  e.preventDefault();

  const type = document.querySelector("#type");
  const keyword = document.querySelector("#keyword");

  if (type.value == "") {
    alert("검색 기준을 선택해주세요");
    type.focus();
    return;
  }
  if (keyword.value == "") {
    alert("검색어를 입력해주세요");
    keyword.focus();
    return;
  }
  searchform.submit();
});