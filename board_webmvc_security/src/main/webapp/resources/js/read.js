//글번호에 대해서 첨부파일 가져오기

function showAttachFile(uploadResultArr) {
  //첨부파일 목록 보여주기
  let str = "";
  uploadResultArr.forEach((item) => {
    //fileType이 true라면 image파일
    if (item.fileType) {
      //썸네일 이미지 경로 생성
      let fileCallPath = encodeURIComponent(item.uploadPath + "\\s_" + item.uuid + "_" + item.fileName);

      //썸네일 이미지 클릭 => 원본 이미지 보여주기
      let oriFileCallPath = encodeURIComponent(item.uploadPath + "\\" + item.uuid + "_" + item.fileName);

      str += "<li data-path='" + item.uploadPath + "' data-uuid='" + item.uuid + "' ";
      str += "data-filename='" + item.fileName + "' data-type='" + item.fileType + "' >";
      str += "<a href='/display?fileName=" + oriFileCallPath + "'data-lightbox='image'>";
      str += "<div class='text-center'><img src='/display?fileName=" + fileCallPath + "'></div>";
      str += "<small>" + item.fileName + "</small></a> ";
      str += "</li>";
    } else {
      // txt 파일
      let fileCallPath = encodeURIComponent(item.uploadPath + "\\" + item.uuid + "_" + item.fileName);

      str += "<li data-path='" + item.uploadPath + "' data-uuid='" + item.uuid + "' ";
      str += "data-filename='" + item.fileName + "' data-type='" + item.fileType + "' >";
      str += "<a href='/download?fileName=" + fileCallPath + "'>";
      str += "<div class='text-center'><img src='/resources/img/txt-file.png'></div>";
      str += "<small>" + item.fileName + "</small></a> ";
      str += "</li>";
    }
  });
  document.querySelector(".uploadResult ul").insertAdjacentHTML("beforeend", str);
}

fetch("/board/getAttachList?bno=" + bno) // = fetch("getAttachList?bno"+bno)
  .then((response) => {
    if (!response.ok) {
      throw new Error("첨부파일 없음");
    }
    return response.json();
  })
  .then((data) => {
    showAttachFile(data);
  })
  .catch((error) => console.log(error));

const form = document.querySelector("#operForm");

const btnInfo = document.querySelector(".btn-info");
if (btnInfo) {
  btnInfo.addEventListener("click", () => {
    form.action = "/board/modify";
    form.submit();
  });
}

// document.querySelector("#readForm").addEventListener("submit", (e) => {
//   e.preventDefault();
//   form.action = "/board/modify";
//   form.method = "GET";
//   form.submit();
// });

document.querySelector(".btn-secondary").addEventListener("click", () => {
  form.firstElementChild.remove();
  form.action = "/board/list";
  form.submit();
});

//댓글 보여줄 영역 가져오기
let page = 1;
let chat = document.querySelector(".chat");

showList(page);

function showReplyPage(total) {
  let endPage = Math.ceil(page / 10.0) * 10;
  let startPage = endPage - 9;
  let prev = startPage != 1;
  let next = false;

  if (endPage * 10 >= total) {
    endPage = Math.ceil(total / 10.0);
  }
  if (endPage * 10 < total) {
    next = true;
  }
  let str = "<ul class='pagination justify-content-center'>";
  if (prev) {
    str += "<li class='page-item'><a class='page-link' href='" + (startPage - 1) + "'>Previous</a></li>";
  }

  for (let i = startPage; i <= endPage; i++) {
    let active = page == i ? "active" : "";
    str += "<li class='page-item " + active + "'}'><a class='page-link' href='" + i + "'>" + i + "</a></li>";
  }

  if (next) {
    str += "<li class='page-item'><a class='page-link' href='" + (endPage + 1) + "'>Next</a></li>";
  }

  str += "</ul>";
  document.querySelector(".card-footer").innerHTML = str;
}

//댓글 페이지 나누기 숫자 클릭 시 a태그 동작 중지시키고
//href에 있는 값 가져오기
//가져온 값으로 showList 호출

document.querySelector(".card-footer").addEventListener("click", (e) => {
  e.preventDefault();
  if (e.target.tagName === "A") {
    page = e.target.getAttribute("href");
    showList(page);
  }
});

function showList(pageNum) {
  //현재 게시물에 대한 댓글 목록 가져오기
  // page || 1 : 자바스크립트 단축 평가 - > 왼쪽이 true면 오른쪽값을 읽어오지않고 그대로 왼쪽을 리턴
  replyService.getList({ bno: bno, page: page || 1 }, (total, result) => {
    if (pageNum == -1) {
      page = Math.ceil(total / 10.0);
      showList(page);
      return;
    }
    if (result == null || result.length == 0) {
      chat.innerHTML = "아직 댓글이 없습니다";
      return;
    }
    let str = "";
    for (let idx = 0; idx < result.length; idx++) {
      str += "<li class='list-group-item border-bottom' data-rno='" + result[idx].rno + "'>";
      str += "<div class='d-flex justify-content-between'>";
      str += "<strong class='primary-font'>" + result[idx].replyer + "</strong>";
      str += "<small class='text-muted text-right'>" + replyService.displayTime(result[idx].replyDate) + "</small>";
      str += "</div>";
      str += "<p>" + result[idx].reply + "</p>";
      str += '<div class="btn-group btn-group-sm">';
      str += '<button class="btn btn-warning" type="button">수정</button>';
      str += '<button class="btn btn-danger" type="button">삭제</button>';
      str += "</div>";
      str += "</li>";
    }
    chat.innerHTML = str;
    showReplyPage(total); //현 게시물에 달린 댓글 총숫자를 이용한 페이지 나누기 함수 호출
  });
}

// 댓글 작업 호출 - 댓글 작성 버튼 클릭시 (버튼에 타입 안주면 기본은 submit)
const replyForm = document.querySelector("#replyForm");
if (replyForm) {
  replyForm.addEventListener("submit", (e) => {
    e.preventDefault();
    const reply = document.querySelector("#reply");
    const replyer = document.querySelector("#replyer");

    replyService.add(
      {
        bno: bno,
        reply: reply.value,
        replyer: replyer.value,
      },
      (result) => {
        //이건 어차피 성공했을때만 호출되는 함수. 실패 케이스를 나눌 필요가 없다.
        reply.value = "";
        showList(-1);
      }
    );
  });
}

//수정 버튼 클릭 시 모달 창 띄우기
chat.addEventListener("click", (e) => {
  if (e.target.tagName != "BUTTON") {
    return;
  }
  let li = e.target.closest("li");
  let rno = li.dataset.rno;

  let replyer = li.firstElementChild.firstElementChild.innerHTML;
  let form_replyer = document.querySelector("#replyForm #replyer");
  let login_user = "";
  if (form_replyer) {
    login_user = form_replyer.value;
  }
  if (!login_user) {
    alert("로그인 후 수정 및 삭제가 가능합니다.");
    return;
  }
  if (e.target.classList.contains("btn-warning")) {
    if (replyer != login_user) {
      alert("본인이 작성한 댓글만 수정이 가능합니다.");
      return;
    }
    // 댓글 하나 가져오기
    replyService.get(rno, (result) => {
      // console.log(result);
      $("#replyModal #rno").val(result.rno);
      $("#replyModal #reply").val(result.reply);
      $("#replyModal #replyer").val(result.replyer);
      $("#replyModal").modal("show");
    });
  }
  if (e.target.classList.contains("btn-danger")) {
    if (replyer != login_user) {
      alert("본인이 작성한 댓글만 삭제가 가능합니다.");
      return;
    }
    replyService.remove(rno, replyer, (result) => {
      if (result === "success") {
        alert("삭제 성공");
        showList(page);
      }
    });
  }
});

document.querySelector(".modal-footer .btn-primary").addEventListener("click", (e) => {
  //모달 창 안에 있는 rno, reply 가져온 후 자바 스크립트 객체 생성
  let updateReply = {
    rno: document.querySelector("#replyModal #rno").value,
    reply: document.querySelector("#replyModal #reply").value,
    replyer: document.querySelector("#replyModal #replyer").value,
  };
  replyService.update(updateReply, (result) => {
    alert("result");
    $("#replyModal").modal("hide");
    showList(page);
  });
});
