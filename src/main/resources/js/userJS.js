const navbar = document.querySelector(".navbar.navbar-dark.bg-dark");
let temp = '';
fetch("http://localhost:8080/api/user")
    .then(res => res.json())
    .then(user => {
        temp = `<div class="col-11">
       <p class="text-light bg-dark">
        ${user.email}
        with roles:
        ${user.role}
        </p>
    </div>
    <div class="col-1">
        <a class="badge" style="color: grey" href="/logout">Logout</a>
    </div>`;
        navbar.innerHTML = temp;
        console.log(temp)
    })


const tbodyLeftUser = document.getElementById("tbodyLeft")
let resultLeftUser = ""

fetch("http://localhost:8080/api/user")
    .then(response => response.json()).then(user => {
    resultLeftUser += `<tr>
                         <td>${user.id}</td>
                         <td>${user.name}</td>
                         <td>${user.lastName}</td>
                         <td>${user.age}</td>
                         <td>${user.email}</td>
                         <td>${user.role}</td></tr>`;

    tbodyLeftUser.innerHTML = resultLeftUser
})