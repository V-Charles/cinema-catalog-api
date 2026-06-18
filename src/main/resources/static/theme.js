function carregarTema() {
    let tema = localStorage.getItem("tema");
    if (tema === "escuro") {
        document.body.classList.add("dark-mode");
        document.getElementById("btn-tema").innerText = "Tema Claro";
    } else {
        document.body.classList.remove("dark-mode");
        document.getElementById("btn-tema").innerText = "Tema Escuro";
    }
}

function alternarTema() {
    let element = document.body;
    element.classList.toggle("dark-mode");

    if (element.classList.contains("dark-mode")) {
        localStorage.setItem("tema", "escuro");
        document.getElementById("btn-tema").innerText = "Tema Claro";
    } else {
        localStorage.setItem("tema", "claro");
        document.getElementById("btn-tema").innerText = "Tema Escuro";
    }
}

window.onload = function() {
    carregarTema();
};