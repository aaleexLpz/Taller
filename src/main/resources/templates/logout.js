function logout() {
    fetch('/logout', {
        method: 'POST',
        credentials: 'same-origin',
    })
    .then(response => {
        if (response.ok) {
            window.location.href = '/login';
        } else {
            console.error('Error al cerrar sesión');
        }
    })
    .catch(error => {
        console.error('Error al cerrar sesión', error);
    });
}
