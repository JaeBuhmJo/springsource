/**
 *
 */
document.querySelector("#uploadFile").addEventListener("change", () => {
  //FormData 객체 생성
  const formData = new FormData();

  //file 요소 가져오기
  let inputFiles = document.querySelector("#uploadFile").files;
  //   console.log(inputFiles);

  //가져온 file 요소를 formData에 추가
  for (let i = 0; i < inputFiles.length; i++) {
    formData.append("uploadFile", inputFiles[i]);
  }

  //비동기 - formData 전송
  fetch("/uploadAjax", {
    method: "post",
    headers: {
      "X-CSRF-TOKEN": csrfToken,
    },
    body: formData,
  })
    .then((response) => {
      if (!response.ok) {
        throw new Error("파일 업로드 실패");
      }
      return response.json();
    })
    .then((data) => {
      console.log(data);
      showUploadedFile(data);
    })
    .catch((error) => console.log(error));
});

function showUploadedFile(uploadResultArr) {
  let str = "";
  uploadResultArr.forEach((item) => {
    //fileType이 true라면 image파일이다. -> 썸네일 이미지 보여주기
    if (item.fileType) {
      //썸네일 이미지 경로 생성
      let fileCallPath = encodeURIComponent(item.uploadPath + "\\s_" + item.uuid + "_" + item.fileName);
      //str += "<li><img src='/display?fileName=" + fileCallPath + "'></li>";

      //썸네일 이미지 클릭 => 원본 이미지 보여주기
      let oriFileCallPath = encodeURIComponent(item.uploadPath + "\\" + item.uuid + "_" + item.fileName);

      str += "<li data-path='" + item.uploadPath + "' data-uuid='" + item.uuid + "' ";
      str += "data-filename='" + item.fileName + "' data-type='" + item.fileType + "' >";
      str += "<a href='/display?fileName=" + oriFileCallPath + "'data-lightbox='image'>";
      str += "<div class='text-center'><img src='/display?fileName=" + fileCallPath + "'></div>";
      str += "<small>" + item.fileName + "</small></a> ";
      str +=
        "<button type='button' class='btn btn-sm btn-circle btn-warning' data-file='" +
        fileCallPath +
        "'data-type='image'> X </button>";
      str += "</li>";
    } else {
      // txt 파일
      let fileCallPath = encodeURIComponent(item.uploadPath + "\\" + item.uuid + "_" + item.fileName);

      str += "<li data-path='" + item.uploadPath + "' data-uuid='" + item.uuid + "' ";
      str += "data-filename='" + item.fileName + "' data-type='" + item.fileType + "' >";
      str += "<a href='/download?fileName=" + fileCallPath + "'>";
      str += "<div class='text-center'><img src='/img/txt-file.png'></div>";
      str += "<small>" + item.fileName + "</small></a> ";
      str +=
        "<button type='button' class='btn btn-sm btn-circle btn-warning' data-file='" +
        fileCallPath +
        "'data-type='file'> X </button>";
      str += "</li>";
    }
  });
  console.log("파일첨부 ", str);

  document.querySelector(".uploadResult ul").insertAdjacentHTML("beforeend", str);
}

// x 클릭 시 첨부파일 제거
// register.jsp에서 사용하는 개념과 modify.jsp 사용하는 개념은 다름

document.querySelector(".uploadResult").addEventListener("click", (e) => {
  if (e.target.tagName === "BUTTON") {
    const targetFile = e.target.dataset.file;
    const type = e.target.dataset.type;

    const li = e.target.closest("li");

    if (path.includes("modify")) {
      // modify 요청 처리 - 화면상에서만 목록 제거
      if (confirm("정말로 파일을 삭제하시겠습니까?")) {
        li.remove();
      }
    } else {
      // register 요청 처리 - db에서도 제거
      //스크립트에서 <form> 생성
      const formData = new FormData();
      formData.append("fileName", targetFile);
      formData.append("type", type);

      fetch("/deleteFile", {
        method: "post",
        headers: {
          "X-CSRF-TOKEN": csrfToken,
        },
        body: formData,
      })
        .then((response) => {
          if (!response.ok) {
            throw new Error("파일 제거 실패");
          }
          return response.text();
        })
        .then((data) => {
          console.log(data);
          // 첨부 목록 정리
          li.remove();
        })
        .catch((error) => console.log(error));
    }
  }
});

//URLSearchParams -> /deleteFile?fileName=abcd.txt
//const data = new URLSearchParams(formData);
//위에 같은거 파라미터 형태로 주소줄에 딸려보낼 때 사용 가능한 객체
