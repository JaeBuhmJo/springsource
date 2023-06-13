/**
 *  검색버튼 클릭 시 사용자 입력값 가져오기
 *  fetch("/api/restaurant/search")
 */

document.querySelector("#searchButton").addEventListener("click", () => {
  const query = document.querySelector("#searchBox").value;
  //기존 값 제거
  document.querySelector("#wish_image").removeAttribute("src");
  document.querySelector("#wish_title").innerHTML = "";
  document.querySelector("#wish_category").innerHTML = "";
  document.querySelector("#wish_address").innerHTML = "";
  document.querySelector("#wish_road_address").innerHTML = "";
  document.querySelector("#wish_link").removeAttribute("href");

  fetch("/api/restaurant/search?query=" + query)
    .then((response) => {
      if (!response.ok) {
        throw new Error("잘못된 요청");
      }
      return response.json();
    })
    .then((data) => {
      //console.log(data);
      //search result 에 정보 보여주기
      // img src 변경 - data 안에 imageLink
      if (data.imageLink) {
        document.querySelector("#wish_image").src = data.imageLink;
      }
      // 다른 정보들도 보여주기
      document.querySelector("#wish_title").innerHTML = data.title;
      document.querySelector("#wish_category").innerHTML = data.category;
      document.querySelector("#wish_address").innerHTML = data.address;
      document.querySelector("#wish_road_address").innerHTML = data.roadAddress;
      if (data.homePageLink) {
        document.querySelector("#wish_link").href = data.homePageLink;
      }
      //style 속성 변경
      document.querySelector("#search-result").style.visibility = "visible";
    })
    .catch((error) => console.log(error));
});

// 위시리스트 추가 클릭 시
// 검색 결과를 자바스크립트 객체로 생성
// fetch() + post
document.querySelector("#wishButton").addEventListener("click", () => {
  const wishItem = {
    title: document.querySelector("#wish_title").innerHTML,
    category: document.querySelector("#wish_category").innerHTML,
    address: document.querySelector("#wish_address").innerHTML,
    roadAddress: document.querySelector("#wish_road_address").innerHTML,
    homePageLink: document.querySelector("#wish_link").href,
    imageLink: document.querySelector("#wish_image").src,
  };

  fetch("/api/restaurant", {
    method: "post",
    headers: {
      "content-type": "application/json",
    },
    body: JSON.stringify(wishItem),
  })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      showList();
    })
    .catch((error) => console.log(error));
});

showList();

//wishList 가져오기
function showList() {
  fetch("/api/restaurant/all")
    .then((response) => {
      if (!response.ok) {
        throw new Error("가져올 데이터 없음");
      }
      return response.json();
    })
    .then((data) => {
      // console.log(data);
      let str = "";

      data.forEach((element) => {
        str += "<hr class='mt-1' />";
        str += "<div class='row'>";
        str += "<div class='col-sm-6 col-md-8'>";
        str += "<img alt='food' class='img-thumbnail w-100' src='" + element.imageLink + "'>";
        str += "</div>";
        str += "<div class='col-sm-6 col-md-4'>";
        str += "<ul class='list-group list-group-flush'>";
        str += "<li class='list-group-item'>장소 : " + element.title + "</li>";
        str += "<li class='list-group-item'>Category : " + element.category + "</li>";
        str += "<li class='list-group-item'>주소 : " + element.address + "</li>";
        str += "<li class='list-group-item'>도로명 : " + element.roadAddress + "</li>";
        str += "<li class='list-group-item'>방문여부 :";
        if (element.visit) {
          str += "방문";
        }
        str += "</li>";
        str += "<li class='list-group-item'>마지막 방문일자 : ";
        if (element.lastVisitdate) {
          str += element.lastVisitdate;
        }
        str += "</li>";
        str += "<li class='list-group-item'>방문횟수 : " + element.visitCount + "</li>";
        str += "<li class='list-group-item'>";
        str += "<a href='" + element.homePageLink + "'>홈페이지</a>";
        str += "</li>";
        str += "<li class='list-group-item'>";
        str += "<div class='d-grid gap-2'>";
        str += "<button class='btn btn-primary' type='button'>방문 추가</button>";
        str += "<button class='btn btn-warning' type='button'>위시리스트 삭제</button>";
        str += "</div>";
        str += "</li>";
        str += "</ul>";
        str += "</div>";
        str += "</div>";
      });
      document.querySelector("#wish-list").innerHTML = str;
    })
    .catch((error) => console.log(error));
}
