function toggleModoOscuro() {
    const body = document.body;
    const topBar = document.querySelector('.top-bar');
    const container = document.querySelector('.container');
    const tablaClientes = document.querySelector('#tablaClientes');

    if (body.classList.contains('modo-oscuro')) {
        // Cambiar a modo claro
        body.classList.remove('modo-oscuro');
        topBar.classList.remove('modo-oscuro');
        container.classList.remove('modo-oscuro');
        tablaClientes.classList.remove('modo-oscuro');
        document.getElementById('modoOscuroBtn').textContent = 'Activar Modo Oscuro';
    } else {
        // Cambiar a modo oscuro
        body.classList.add('modo-oscuro');
        topBar.classList.add('modo-oscuro');
        container.classList.add('modo-oscuro');
        tablaClientes.classList.add('modo-oscuro');
        document.getElementById('modoOscuroBtn').textContent = 'Activar Modo Claro';
    }
}

document.getElementById('modoOscuroBtn').addEventListener('click', toggleModoOscuro);