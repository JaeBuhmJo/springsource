//첨부파일 가져오기
fetch("/board/getAttachList?bno=" + bno) // = fetch("getAttachList?bno"+bno)
  .then((response) => {
    if (!response.ok) {
      throw new Error("첨부파일 없음");
    }
    return response.json();
  })
  .then((data) => {
    showUploadedFile(data);
  })
  .catch((error) => console.log(error));

// 수정 버튼 클릭해 폼 submit이 일어나면 첨부파일 목록 수집하기
const modifyForm = document.querySelector("#modifyForm");

modifyForm.addEventListener("submit", (e) => {
  e.preventDefault();
  const lis = document.querySelectorAll(".uploadResult ul li");

  let str = "";
  lis.forEach((li, idx) => {
    str += "<input type='hidden' name='attachList[" + idx + "].uuid' value='" + li.dataset.uuid + "'/>";
    str += "<input type='hidden' name='attachList[" + idx + "].uploadPath' value='" + li.dataset.path + "'/>";
    str += "<input type='hidden' name='attachList[" + idx + "].fileName' value='" + li.dataset.filename + "'/>";
    str += "<input type='hidden' name='attachList[" + idx + "].fileType' value='" + li.dataset.type + "'/>";
  });
  modifyForm.insertAdjacentHTML("beforeend", str);
  console.log(modifyForm);
  modifyForm.submit();
});

//수정, 삭제 클릭 시 동작하는 폼
const form = document.querySelector("#operForm");

const btnDan = document.querySelector(".btn-danger");
if (btnDan) {
  btnDan.addEventListener("click", () => {
    form.action = "/board/remove";
    form.submit();
  });
}

document.querySelector(".btn-secondary").addEventListener("click", () => {
  form.firstElementChild.remove();
  form.action = "/board/list";
  form.submit();
});
