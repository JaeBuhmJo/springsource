/**
 *
 */
document.querySelector("#uploadBtn").addEventListener("click", () => {
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
      let fileCallPath = encodeURIComponent(
        item.uploadPath + "\\s_" + item.uuid + "_" + item.fileName
      );
      //str += "<li><img src='/display?fileName=" + fileCallPath + "'></li>";

      //썸네일 이미지 클릭 => 원본 이미지 보여주기
      let oriFileCallPath = encodeURIComponent(
        item.uploadPath + "\\" + item.uuid + "_" + item.fileName
      );
      str += "<li><div>";
      str +=
        "<a href='/display?fileName=" +
        oriFileCallPath +
        "'data-lightbox='image'>";
      str += "<img src='/display?fileName=" + fileCallPath + "'></a></div>";
      str += "<span>" + item.fileName + "</span>";
      str +=
        "<span data-file='" + fileCallPath + "'data-type='image'> X </span>";
      str += "</li>";
    } else {
      str += "<li>" + item.fileName + "</li>";
    }
  });
  document.querySelector(".uploadResult ul").innerHTML = str;
}
