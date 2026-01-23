function setCookie(cname, cvalue, exdays) {
    const d = new Date();
    d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
    let expires = "expires="+d.toUTCString();
    document.cookie = cname = "=" + cvalue + ";" + expires + ";path=/";
}

function getCookie(cname) {
    let name = cname + "=";
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function carregarTema() {
    let tema = getCookie("tema");
    if (tema === "escuro") {
        document.body.classList.add("dark-mode");
        document.getElementById("btn-tema").innerText = "Tema Claro";
    } else {
        document.getElementById("btn-tema").innerText = "Tema Escuro";
    }
}

function alternarTema() {
    let element = document.body;
    element.classList.toggle("dark-mode");

    if (element.classList.contains("dark-mode")) {
        setCookie("tema", "escuro", 30);
        document.getElementById("btn-tema").innerText = "Tema Claro";
    } else {
        setCookie("tema", "claro", 30);
        document.getElementById("btn-tema").innerText = "Tema Escuro";
    }
}

window.onload = function() {
    carregarTema();
};