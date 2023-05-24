/**
 * 댓글 처리 자바스크립트 모듈
 */

let replyService = (function () {
  //reply : 댓글 작성 자바스크립트 객체
  //callback : function
  function add(reply, callback) {
    console.log("add 함수");

    fetch("/replies/new", {
      method: "post",
      headers: {
        "content-type": "application/json",
      },
      body: JSON.stringify(reply),
    })
      .then((response) => {
        //결과가 도착하게 되면 자동 호출 (비동기호출)
        if (!response.ok) {
          throw new Error("댓글 입력 실패");
        }
        return response.text(); //success
      })
      .then((data) => {
        // 처음 then()이 return을 하면 호출됨
        // 넘겨받은 함수를 호출하게 됨
        if (callback) {
          callback(data);
        }
      })
      .catch((error) => alert(error));
  }

  function getList(param, callback) {
    let bno = param.bno;
    let page = param.page;

    fetch("/replies/pages/" + bno + "/" + page)
      .then((response) => {
        if (!response.ok) {
          throw new Error("댓글 없음");
        }
        return response.json();
      })
      .then((data) => {
        if (callback) {
          callback(data);
        }
      })
      .catch((error) => console.log(error));
  }

  function displayTime(timeVal) {
    const today = new Date(); // 오늘 날짜
    let gap = today.getTime() - timeVal;

    //댓글 작성 날짜 Date 객체 생성
    let dateObj = new Date(timeVal);

    let str = "";
    if (gap < 1000 * 60 * 60 * 24) {
      let hh = dateObj.getHours();
      let mi = dateObj.getMinutes();
      let ss = dateObj.getSeconds();

      return [
        (hh > 9 ? "" : "0") + hh,
        ":",
        (mi > 9 ? "" : "0") + mi,
        ":",
        (ss > 9 ? "" : "0") + ss,
      ].join("");
    } else {
      const yy = dateObj.getFullYear();
      const mm = dateObj.getMonth() + 1;
      const dd = dateObj.getDate();
      return [
        yy,
        "/",
        (mm > 9 ? "" : "0") + mm,
        "/",
        (dd > 9 ? "" : "0") + dd,
      ].join("");
    }
  }

  function get(rno, callback) {
    fetch("/replies/" + rno)
      .then((response) => {
        if (!response.ok) {
          throw new Error("존재하지 않는 댓글입니다.");
        }
        return response.json();
      })
      .then((data) => {
        if (callback) {
          callback(data);
        }
      })
      .catch((error) => alert(error));
  }

  return {
    add: add,
    getList: getList,
    displayTime: displayTime,
    get: get,
  };
})(); // () : IIFE : 즉시 실행 함수
