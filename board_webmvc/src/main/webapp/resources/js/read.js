
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