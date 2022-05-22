const url = "http://localhost:8080/api/"
//////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////ALL USERS///////////////////////////////////////////////////
const tBodyAllUsers = document.querySelector("#tbAllUsers")
let result = ""

fetch(url + "users")
    .then(response => response.json()).then(users => {
    users.forEach(user => {
        result += `<tr id="${user.id}">
                         <td>${user.id}</td>
                         <td>${user.name}</td>
                         <td>${user.lastName}</td>
                         <td>${user.age}</td>
                         <td>${user.email}</td>
                         <td>${user.roles.map(e => e.name_role)}</td>
                         <td> <button id= "edit" type="button" class=" btnEdit btn-info " data-toggle="modal" > Edit</button></td>
                         <td> <button id = "delete"  type="button" class="btnEdit btn-danger" data-toggle="modal" >Delete</button> </td></tr> `

    })
    tBodyAllUsers.innerHTML = result
})

const on = (element, ev, selector, handler) => {
    element.addEventListener(ev, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}

let roleList = [{id: 1, name_role: "ADMIN"}, {id: 2, name_role: "USER"}]
let roles

////////////////////////////////////////////////////////////////////////////////////////
////////////////////////UPDATE/////////////////////////////////////////////
const idEdit = document.getElementById("id")
const lastNameEdit = document.getElementById("lastName")
const ageEdit = document.getElementById("age")
const emailEdit = document.getElementById("email")
const passwordEdit = document.getElementById("password")
const nameEdit = document.getElementById("name")
const myModalEdit = new bootstrap.Modal(document.querySelector("#modalE"))

on(document, "click", "#edit", e => {
    const line = e.target.parentNode.parentNode
    const idEd = line.children[0].innerHTML
    const nameEd = line.children[1].innerHTML
    const lastNameEd = line.children[2].innerHTML
    const ageEd = line.children[3].innerHTML
    const emailEd = line.children[4].innerHTML
    idEdit.value = idEd
    nameEdit.value = nameEd
    lastNameEdit.value = lastNameEd
    ageEdit.value = ageEd
    emailEdit.value = emailEd
    passwordEdit.value = ""

    myModalEdit.show()


    const update = document.getElementById("update")
    roles = []
    update.addEventListener("click", e => {
        e.preventDefault()
        let options = document.getElementById("roles").options
        roles = []
        for (let i = 0; i < options.length; i++) {
            if (options[i].selected) {
                roles.push(roleList[i]);

            }
        }
        fetch(url + "update", {
            method: "PUT",
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                id: idEdit.value,
                name: nameEdit.value,
                lastName: lastNameEdit.value,
                age: ageEdit.value,
                email: emailEdit.value,
                password: passwordEdit.value,
                roles: roles
            })
        })
            .then(res => res.json())

        myModalEdit.hide();

        $("#" + idEdit.value).html(`<td>${idEdit.value}</td>
            <td>${nameEdit.value}</td>
        <td>${lastNameEdit.value}</td>
            <td>${ageEdit.value}</td>
            <td>${emailEdit.value}</td>
            <td>${roles.map(e => e.name_role)}</td>
            <td> <button id= "edit" type="button" class=" btnEdit btn-info " data-toggle="modal" > Edit</button></td>
            <td> <button id = "delete"  type="button" class="btnEdit btn-danger" data-toggle="modal" >Delete</button> </td>`
        );
    })
})

///////////////////////////////////////////////////////////////////////////////////////////
////////////////////DELETE/////////////////////////////////////////////////////////////
on(document, "click", "#delete", e => {
    const line = e.target.parentNode.parentNode
    const idDe = line.children[0].innerHTML
    const nameDe = line.children[1].innerHTML
    const lastNameDe = line.children[2].innerHTML
    const ageDe = line.children[3].innerHTML
    const emailDe = line.children[4].innerHTML
    idDelete.value = idDe
    nameDelete.value = nameDe
    lastNameDelete.value = lastNameDe
    ageDelete.value = ageDe
    emailDelete.value = emailDe
    myModalDelete.show()
})
const myModalDelete = new bootstrap.Modal(document.querySelector("#modalD"))
const idDelete = document.getElementById("idD")
const nameDelete = document.getElementById("nameD")
const lastNameDelete = document.getElementById("lastNameD")
const ageDelete = document.getElementById("ageD")
const emailDelete = document.getElementById("emailD")
on(document, "click", "#del", (e) => {
    e.preventDefault()
    fetch(url + "delete/" + idDelete.value, {
        method: "DELETE"
    })

    document.getElementById(idDelete.value).remove()
    myModalDelete.hide()


})

////////////////////////////////////////////////////////////////////////////////
////////////////////////CREATE/////////////////////////////////////////////////
const nameNew = document.getElementById("nameN")
const lastNameNew = document.getElementById("lastNameN")
const ageNew = document.getElementById("ageN")
const emailNew = document.getElementById("emailN")
const passwordNew = document.getElementById("passwordN")
const add = document.getElementById("add")
add.addEventListener("click", e => {
    e.preventDefault()
    let options = document.getElementById("roleNew").options
    roles = []
    for (let i = 0; i < options.length; i++) {
        if (options[i].selected) {
            roles.push(roleList[i]);

        }
    }
    fetch(url + "create", {
        method: "POST",
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            name: nameNew.value,
            lastName: lastNameNew.value,
            age: ageNew.value,
            email: emailNew.value,
            password: passwordNew.value,
            roles: roles
        })
    })

        .then(() => location.reload())

})
/////////////////////////////////////////////////////////////////////
///////////////////////////TOPNAVBAR////////////////////////////////
const navTop = document.querySelector(".navbar.navbar-dark.bg-dark");
let temp = '';
fetch("http://localhost:8080/api/user")
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