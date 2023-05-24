const form = document.querySelector("#operForm");

document.querySelector("#readForm").addEventListener("submit", (e) => {
  e.preventDefault();
  form.action = "/board/modify";
  form.method = "GET";
  form.submit();
});

document.querySelector(".btn-secondary").addEventListener("click", () => {
  form.firstElementChild.remove();
  form.action = "/board/list";
  form.submit();
});

//댓글 보여줄 영역 가져오기
let chat = document.querySelector(".chat");

showList(1);

function showList(page) {
  //현재 게시물에 대한 댓글 목록 가져오기
  // page || 1 : 자바스크립트 단축 평가 - > 왼쪽이 true면 오른쪽값을 읽어오지않고 그대로 왼쪽을 리턴
  replyService.getList({ bno: bno, page: page || 1 }, (result) => {
    // console.log(result);
    if (result == null || result.length == 0) {
      chat.innerHTML = "아직 댓글이 없습니다";
      return;
    }
    let str = "";
    for (let idx = 0; idx < result.length; idx++) {
      str +=
        "<li class='list-group-item border-bottom' data-rno='" +
        result[idx].rno +
        "'>";
      str += "<div class='d-flex justify-content-between'>";
      str +=
        "<strong class='primary-font'>" + result[idx].replyer + "</strong>";
      str +=
        "<small class='text-muted text-right'>" +
        replyService.displayTime(result[idx].replyDate) +
        "</small>";
      str += "</div>";
      str += "<p>" + result[idx].reply + "</p>";
      str += "</li>";
    }
    chat.innerHTML = str;
  });
}

// 댓글 작업 호출 - 댓글 작성 버튼 클릭시 (버튼에 타입 안주면 기본은 submit)
const replyForm = document.querySelector("#replyForm");

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
      replyer.value = "";
      showList(1);
    }
  );
});

// 댓글 하나 가져오기
replyService.get(3, (result) => {
  console.log(result);
});
