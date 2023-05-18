
  const form = document.querySelector("#operForm");

  document.querySelector(".btn-danger").addEventListener("click", () => {
    form.action = "/board/remove";
    form.submit();
  });

  document.querySelector(".btn-secondary").addEventListener("click", () => {
    form.firstElementChild.remove();
    form.action = "/board/list";
    form.submit();
  });