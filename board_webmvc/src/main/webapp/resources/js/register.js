const form = document.querySelector("#registerForm");

form.addEventListener("submit", (e) => {
  e.preventDefault();
  let str = "";
  if (!form.checkValidity()) {
    // e.stopPropagation();
    form.classList.add("was-validated");
  } else {
    const lis = document.querySelectorAll(".uploadResult ul li");
    //첨부파일 정보 수집하기
    lis.forEach((li, idx) => {
      //li 하나당 아래와 같이 태그 생성
      // <input type='hidden' name='attachList[0].uuid' value='uuidvalue'/>
      // <input type='hidden' name='attachList[0].uploadPath' value='2023//05//30'/>
      // <input type='hidden' name='attachList[0].fileName' value='cat.jpg'/>
      // <input type='hidden' name='attachList[0].fileType' value='true'/>

      str += "<input type='hidden' name='attachList[" + idx + "].uuid' value='" + li.dataset.uuid + "'/>";
      str += "<input type='hidden' name='attachList[" + idx + "].uploadPath' value='" + li.dataset.path + "'/>";
      str += "<input type='hidden' name='attachList[" + idx + "].fileName' value='" + li.dataset.filename + "'/>";
      str += "<input type='hidden' name='attachList[" + idx + "].fileType' value='" + li.dataset.type + "'/>";
    });

    form.insertAdjacentHTML("beforeend", str);
    // console.log(form);
  }
  form.submit();
});
