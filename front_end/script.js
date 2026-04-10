const API = "http://localhost:8080/api/users";

let editingId = null;

// LOGIN
function login(){

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    fetch(API + "/login",{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify({username,password})
    })
    .then(async r => {
        const text = await r.text();
        return text ? JSON.parse(text) : null;
    })
    .then(user=>{
        if(user && user.userId){
            localStorage.setItem("user", JSON.stringify(user));
            window.location.href = "admin.html";
        }else{
            alert("Usuario o contraseña incorrectos");
        }
    })
    .catch(()=>alert("Error servidor"));
}

// PROTEGER ADMIN
if(window.location.href.includes("admin.html")){

    let user = JSON.parse(localStorage.getItem("user"));

    if(!user){
        window.location.href = "login.html";
    }else{
        loadUsers();
    }
}

// LOGOUT
function logout(){
    localStorage.removeItem("user");
    window.location.href = "login.html";
}

// CARGAR USUARIOS
function loadUsers(){

    fetch(API)
    .then(r=>r.json())
    .then(data=>{

        let table = document.getElementById("table");
        if(!table) return;

        table.innerHTML="";

        data.forEach(u=>{
            table.innerHTML += `
            <tr>
                <td>${u.userId}</td>
                <td>${u.username}</td>
                <td>
                    <button onclick="editUser(${u.userId}, '${u.firstName || ""}', '${u.lastName || ""}', '${u.email || ""}', '${u.username || ""}', '${u.role || ""}')">
                        Editar
                    </button>

                    <button onclick="deleteUser(${u.userId})">
                        Eliminar
                    </button>
                </td>
            </tr>`;
        });
    });
}

// CARGAR DATOS EN FORMULARIO (EDITAR)
function editUser(id, firstName, lastName, email, username, role){

    document.getElementById("firstName").value = firstName;
    document.getElementById("lastName").value = lastName;
    document.getElementById("email").value = email;
    document.getElementById("username").value = username;
    document.getElementById("role").value = role;

    editingId = id;
}

// CREAR O EDITAR USUARIO
function createUser(){

    let passwordValue = document.getElementById("password").value.trim();

    const user = {
        firstName: document.getElementById("firstName").value.trim(),
        lastName: document.getElementById("lastName").value.trim(),
        email: document.getElementById("email").value.trim(),
        username: document.getElementById("username").value.trim(),
        role: document.getElementById("role").value.trim()
    };

    // SOLO ENVÍA PASSWORD SI NO ESTÁ VACÍA
    if(passwordValue !== ""){
        user.password = passwordValue;
    }

    // EDITAR
    if(editingId !== null){

        fetch(API + "/" + editingId,{
            method:"PUT",
            headers:{"Content-Type":"application/json"},
            body: JSON.stringify(user)
        })
        .then(res=>{
            if(res.ok){
                alert("Usuario actualizado correctamente");
                resetForm();
                loadUsers();
            }else{
                alert("Error al actualizar usuario");
            }
        });

    } else {
        // CREAR
        fetch(API,{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body: JSON.stringify(user)
        })
        .then(res=>{
            if(res.ok){
                alert("Usuario creado correctamente");
                resetForm();
                loadUsers();
            }else{
                alert("Error al crear usuario");
            }
        });
    }
}

// LIMPIAR FORMULARIO
function resetForm(){
    document.getElementById("firstName").value = "";
    document.getElementById("lastName").value = "";
    document.getElementById("email").value = "";
    document.getElementById("username").value = "";
    document.getElementById("password").value = "";
    document.getElementById("role").value = "";

    editingId = null;
}

// ELIMINAR
function deleteUser(id){
    fetch(API+"/"+id,{method:"DELETE"})
    .then(()=>loadUsers());
}