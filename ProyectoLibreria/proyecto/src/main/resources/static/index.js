//FUNCION CONSUMO API DOLAR
// function cargarDatos() {
//     fetch('http://localhost:8080/api/dolar') 
//         .then(response => {
//             if (!response.ok) {
//                 throw new Error('No se pudo obtener el tipo de cambio del dólar');
//             }
//             return response.json();
//         })
//         .then(data => {
//             mostrarDatos(data);
//             console.log(data);
//         })
//         .catch(error => {
//             console.error('Error al cargar los datos:', error);
//             document.getElementById('dolarInfo').innerHTML = '<div class="alert alert-danger" role="alert">Error al obtener el tipo de cambio del dólar.</div>';
//         });
// }

// function mostrarDatos(data) {
//     document.getElementById('compra').textContent = data.compra.toFixed(2);
//     document.getElementById('venta').textContent = data.venta.toFixed(2);
//     document.getElementById('fechaActualizacion').textContent = moment(data.fechaActual).format('DD/MM/YYYY HH:mm:ss');
// }

// window.onload = cargarDatos;

// document.addEventListener('DOMContentLoaded', (event) => {
//     obtenerLibros();
// });

// Variables globales
const tablaLibrosHTML = document.getElementById('tablaLibros');
const titulo = document.getElementById('titulo');
const autor = document.getElementById('autor');
const genero = document.getElementById('genero');
const precio = document.getElementById('precio');
const stock = document.getElementById('stock');
const btnGuardar = document.getElementById('btnGuardar');
let tituloModal = document.getElementById('tituloModal');
let idLibroModificar = 0;

// Event listener para el botón de crear/modificar
btnGuardar.addEventListener('click', () => {
    if (tituloModal.innerText === "Modificar Libro") {
        crearLibro(idLibroModificar);
    } else 
        if (tituloModal.innerText === "Crear Libro") {
        crearLibro(0);
    }
});

// Eliminar Libro
async function eliminarLibro(id) {
    await fetch('http://localhost:8080/api/libro/eliminar/' + id, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(() => {
        alert('Libro eliminado correctamente');
        obtenerLibros();
    })
    .catch(err => {
        console.error('Error al eliminar libro:', err);
        alert('Error al eliminar el libro');
    });
}

// Modificar Libro

async function modificarLibro(id) {
    tituloModal.innerText = "Modificar Libro";
    modal.style.display = "block";  // Muestra el modal
    // Asignar el ID del libro para modificar
    idLibroModificar = id;
    resetModal();  // Limpiar los campos del formulario
    // Buscar el libro en la lista
    let libro = listaLibros.find(l => l.id === idLibroModificar);
    if (libro) {
        // Asignar los valores del libro al formulario
        titulo.value = libro.titulo;
        autor.value = libro.autor;
        genero.value = libro.genero;
        precio.value = libro.precio;
        stock.value = libro.stock;
        // Realizar la solicitud PUT para actualizar el libro
        await fetch('http://localhost:8080/api/libro/actualizar/' + id, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: id,  // Asegúrate de pasar el ID correctamente
                titulo: titulo.value,
                autor: autor.value,
                genero: genero.value,
                precio: parseFloat(precio.value),
                stock: parseInt(stock.value)
            })
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('No se pudo actualizar el libro');
            }
            return response.json();
        })
        .then(data => {
            console.log('Libro actualizado:', data);
            alert('Libro modificado correctamente');
            obtenerLibros();
        })
        .catch(err => {
            console.error('Error al modificar libro:', err);
        });
    }
}


// funcion para crear Libros
async function crearLibro(pid) {
    let libroGuardar = {
        id:pid,  // Asegúrate de que este ID no cause conflictos
        titulo: titulo.value,
        autor: autor.value,
        genero: genero.value,
        precio: parseFloat(precio.value),
        stock: parseInt(stock.value)
    };
    console.log(libroGuardar);
    await fetch('http://localhost:8080/api/libro/crear', {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(libroGuardar)
    })
    .then((response) => {
        console.log(response);
        alert("Libro guardado correctamente");
        modal.style.display = "none";
        obtenerLibros();  // Refrescar la lista de libros
        resetModal();  // Limpiar los campos del formulario
    })
    .catch((err) => {
        console.log(err);
        alert("Error al guardar el libro");
    });
}


// Traer todos los libros
let listaLibros = [];
async function obtenerLibros() {
    tablaLibrosHTML.innerHTML = '';
    await fetch('http://localhost:8080/api/libro/traer')
        .then(response => response.json())
        .then(libros => {
            listaLibros = libros;
            libros.forEach(libro => {
                tablaLibrosHTML.innerHTML += `
                    <tr>
                        <td>${libro.id}</td>
                        <td>${libro.titulo}</td>
                        <td>${libro.autor}</td>
                        <td>${libro.genero}</td>
                        <td>${libro.precio}</td>
                        <td>${libro.stock}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" onclick="eliminarLibro(${libro.id})">Eliminar</button>
                            <button class="btn btn-success btn-sm" onclick="modificarLibro(${libro.id})">Modificar</button>
                        </td>
                    </tr>
                `;
            });
        })
        .catch(err => {
            console.error('Error al obtener libros:', err);
            alert('Error al cargar los libros');
        });
}
obtenerLibros();

// Buscar libros
async function buscarLibros() {
    const busqueda = document.getElementById('busqueda').value;
    tablaLibrosHTML.innerHTML = '';

    await fetch('http://localhost:8080/api/libro/buscarSimilar/' + busqueda)
        .then(response => response.json())
        .then(libros => {
            listaLibros = libros;
            libros.forEach(libro => {
                tablaLibrosHTML.innerHTML += `
                    <tr>
                        <td>${libro.id}</td>
                        <td>${libro.titulo}</td>
                        <td>${libro.autor}</td>
                        <td>${libro.genero}</td>
                        <td>${libro.precio}</td>
                        <td>${libro.stock}</td>
                        <td>
                            <button class="btn btn-danger btn-sm" onclick="eliminarLibro(${libro.id})">Eliminar</button>
                            <button class="btn btn-success btn-sm" onclick="modificarLibro(${libro.id})">Modificar</button>
                        </td>
                    </tr>
                `;
            });
        })
        .catch(err => {
            console.error('Error al buscar libros:', err);
            alert('Error al cargar los libros');
        });
}


// Función para resetear el formulario del modal
function resetModal() {
    titulo.value = '';
    autor.value = '';
    genero.value = '';
    precio.value = '';
    stock.value = '';
}

// Modal 
var modal = document.getElementById("myModal");
var btn = document.getElementById("myBtn");

// Busca el primer elemento de clase "close" en el documento.
var span = document.getElementsByClassName("close")[0];

// Asigna una función al evento de clic del botón "myBtn".
btn.onclick = function () {
    tituloModal.innerText = "Crear Libro";
    modal.style.display = "block";
}

// Cuando se presiona este elemento (generalmente un icono de cierre), oculta el modal y llama a resetModal().
span.onclick = function () {
    modal.style.display = "none";
    resetModal();
}

// Si el objetivo del clic es el modal, ocultará el modal y llamará a resetModal().
window.onclick = function (event) {
    if (event.target == modal) {
    }
    }


    // document.addEventListener('DOMContentLoaded', function() {
    //     var elemento = document.getElementById('miElemento');
    //     if (elemento) {
    //         elemento.textContent = 'Elemento encontrado';
    //     } else {
    //         console.error('Elemento no encontrado');
    //     }
    // });
    