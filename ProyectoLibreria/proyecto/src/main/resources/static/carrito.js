async function cargarPedidos() {
    const listaPedidos = document.getElementById('listaPedidos');
    const loadingMessage = document.getElementById('loadingMessage');
    
    loadingMessage.style.display = 'block'; // Mostrar mensaje de carga

    try {
        const response = await fetch('http://localhost:8080/api/pedido/traer');
        
        // Verifica si la respuesta fue exitosa
        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const pedidos = await response.json();
        listaPedidos.innerHTML = ''; // Limpiar contenido previo

        if (pedidos.length === 0) {
            listaPedidos.innerHTML = '<p class="text-center">No hay pedidos en el carrito.</p>';
        } else {
            pedidos.forEach(pedido => {
                const pedidoItem = `
                    <a href="#" class="list-group-item list-group-item-action">
                        <div class="d-flex w-100 justify-content-between">
                            <h5 class="mb-1">Pedido ${pedido.id}</h5>
                            <small>${pedido.fecha}</small>
                        </div>
                        <p class="mb-1">Libro ID: ${pedido.libroId}</p>
                        <p class="mb-1">Cantidad: ${pedido.cantidad}</p>
                    </a>
                `;
                listaPedidos.innerHTML += pedidoItem;
            });
        }
    } catch (error) {
        console.error('Error al cargar los pedidos:', error);
        alert('Hubo un problema al cargar los pedidos.');
    } finally {
        loadingMessage.style.display = 'none'; // Ocultar mensaje de carga
    }
}

window.onload = cargarPedidos;

// Crear Pedido
document.addEventListener('DOMContentLoaded', function() {
    const btnGuardar = document.getElementById('btnGuardar');
    if (btnGuardar) {
        btnGuardar.addEventListener('click', () => {
            crearPedido();
        });
    } else {
        console.error('Elemento btnGuardar no encontrado');
    }
});

async function crearPedido() {
    const estado = document.getElementById('estado').value;
    const fecha = document.getElementById('fecha').value;
    const usuarioId = document.getElementById('usuarioId').value;
    const librosIds = document.getElementById('librosIds').value.split(',').map(id => parseInt(id.trim()));

    const pedidoGuardar = {
        estado: estado,
        fecha: fecha,
        usuarioId: usuarioId ? parseInt(usuarioId) : null,
        librosIds: librosIds
    };

    console.log(pedidoGuardar);
    await fetch('http://localhost:8080/api/pedido/crear', {
        method: 'POST',
        mode: 'cors',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(pedidoGuardar)
    })
    .then((response) => {
        if (!response.ok) {
            throw new Error('No se pudo crear el pedido');
        }
        return response.json();
    })
    .then((data) => {
        console.log('Pedido guardado:', data);
        alert("Pedido guardado correctamente");
        resetFormulario();  // Limpiar los campos del formulario
    })
    .catch((err) => {
        console.error('Error al guardar el pedido:', err);
        alert("Error al guardar el pedido");
    });
}

function resetFormulario() {
    document.getElementById('estado').value = '';
    document.getElementById('fecha').value = '';
    document.getElementById('usuarioId').value = '';
    document.getElementById('librosIds').value = '';
}

// Función para resetear el formulario del modal
function resetModal() {
    estado.value = '';
    fecha.value = '';
    usuarioId.value = '';
    librosIds.value = '';
}

// Variables globales
const estado = document.getElementById('estado');
const fecha = document.getElementById('fecha');
const usuarioId = document.getElementById('usuarioId');
const librosIds = document.getElementById('librosIds');
const btnGuardar = document.getElementById('btnGuardar');
let tituloModal = document.getElementById('tituloModal');

// Event listener para el botón de guardar
btnGuardar.addEventListener('click', () => {
    crearPedido();
});

// Modal 
var modal = document.getElementById("myModal");
var btn = document.getElementById("myBtn");
var span = document.getElementsByClassName("close")[0];

btn.onclick = function () {
    tituloModal.innerText = "Crear Pedido";
    modal.style.display = "block";
}

span.onclick = function () {
    modal.style.display = "none";
    resetModal();
}

window.onclick = function (event) {
    if (event.target == modal) {
    }
}

document.addEventListener('DOMContentLoaded', function() {
    const btnGuardar = document.getElementById('btnGuardar');
    if (btnGuardar) {
        btnGuardar.addEventListener('click', () => {
            crearPedido();
        });
    } else {
        console.error('Elemento btnGuardar no encontrado');
    }
});
