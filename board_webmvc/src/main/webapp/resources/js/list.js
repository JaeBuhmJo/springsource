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
