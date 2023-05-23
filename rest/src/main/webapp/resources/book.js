//버튼 클릭 시 도서 전체 목록 깜빡임data 없이 가져오기

// ajax : fetch, jquery, axios

const tbody = document.querySelector(".table tbody");
document.querySelector(".btn-primary").addEventListener("click", () => {
  /*fetch("경로")
    .then("서버로부터 데이터 받기(적절한 타입으로 변환)")
    .then("화면 출력 등 작업")
    .catch("에러 발생한 경우"); */
  fetch("/list")
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      let str = "";
      data.forEach((item) => {
        str += "<tr>";
        str += "<th scope='row'>" + item.code + "</th>";
        str += "<td>" + item.title + "</td>";
        str += "<td>" + item.writer + "</td>";
        str += "<td>" + item.price + "</td>";
        str += "</tr>";
      });
      tbody.innerHTML = str;
    })
    .catch((error) => console.log(error));
});

document.querySelector(".btn-info").addEventListener("click", () => {
  const code = document.querySelector("#code1");
  fetch("/" + code.value)
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      let str = "<tr>";
      str += "<th scope='row'>" + data.code + "</th>";
      str += "<td>" + data.title + "</td>";
      str += "<td>" + data.writer + "</td>";
      str += "<td>" + data.price + "</td>";
      str += "</tr>";
      tbody.innerHTML = str;
    })
    .catch((error) => console.log(error));
});

const form = document.querySelector("#insertForm");
form.addEventListener("submit", (e) => {
  e.preventDefault();
  let data = {
    code: document.querySelector("#code").value,
    title: document.querySelector("#title").value,
    writer: document.querySelector("#writer").value,
    price: document.querySelector("#price").value,
    description: document.querySelector("#description").value,
  };
  //data를 같이 전송하는 fetch : fetch("",{})
  fetch("/create", {
    method: "post",
    headers: {
      "content-type": "application/json",
    },
    //JSON.stringify : js객체를 json 형태로 변환
    body: JSON.stringify(data),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("데이터 확인");
      }
      return response.text();
    })
    .then((data) => {
      if (data === "success") {
        alert("입력 성공");
      } else [alert("입력 실패")];
    })
    .catch((error) => alert(error));
});

document.querySelector(".btn-danger").addEventListener("click", (e) => {
  const code = document.querySelector("#code1");
  fetch("/" + code.value, {
    method: "delete",
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("삭제 실패");
      }
      return response.text();
    })
    .then((data) => {
      if (data === "success") {
        alert("삭제 성공");
      }
    })
    .catch((error) => alert(error));
});

const updateForm = document.querySelector("#updateForm");

updateForm.addEventListener("submit", (e) => {
  e.preventDefault();
  let data = {
    code: document.querySelector("#code2").value,
    price: document.querySelector("#price2").value,
  };

  fetch("/update", {
    method: "PUT",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(data),
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("수정 실패");
      }
      return response.text();
    })
    .then((data) => {
      if (data === "success") {
        alert("수정 성공");
      }
    })
    .catch((error) => alert(error));
});