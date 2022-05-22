///////////////////////////TOPNAVBAR////////////////////////////////
const url = "http://localhost:8080/api/"
const navTop = document.querySelector(".navbar.navbar-dark.bg-dark");
let temp = '';
fetch(url+"user")
    .then(res => res.json())
    .then(user => {
        temp = `<div class="col-11">
       <p class="text-light bg-dark">
        ${user.email}
        with roles:
        ${user.roles.map(e => e.name_role)}
        </p>
    </div>
    <div class="col-1">
        <a class="badge" style="color: grey" href="/logout">Logout</a>
    </div>`;
        navTop.innerHTML = temp;
    })
////////////////////////////////////////////////////////////////
//////////////////////LEFTNAVBAR///////////////////////////////
const tbodyLeftUser = document.getElementById("tbodyLeft")
let resultLeftUser = ""

fetch(url + "user")
    .then(response => response.json()).then(user => {
    resultLeftUser += `<tr>
                         <td>${user.id}</td>
                         <td>${user.name}</td>
                         <td>${user.lastName}</td>
                         <td>${user.age}</td>
                         <td>${user.email}</td>
                         <td>${user.roles.map(e => e.name_role)}</td></tr>`;

    tbodyLeftUser.innerHTML = resultLeftUser
})