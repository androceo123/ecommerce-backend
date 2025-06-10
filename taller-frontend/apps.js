document.getElementById('consultaForm').addEventListener('submit', async function (e) {
  e.preventDefault();

  const clienteId = document.getElementById('clienteId').value;
  const fecha = document.getElementById('fecha').value;

  const url = new URL('http://localhost:8080/taller-backend-1.0-SNAPSHOT/api/servicios/buscar');
  if (clienteId) url.searchParams.append('clienteId', clienteId);
  if (fecha) url.searchParams.append('fecha', fecha);

  try {
    const response = await fetch(url);
    const servicios = await response.json();
    mostrarServicios(servicios);
  } catch (error) {
    console.error('Error al consultar servicios:', error);
  }
});

function mostrarServicios(servicios) {
  const contenedor = document.getElementById('resultados');
  contenedor.innerHTML = '';

  if (servicios.length === 0) {
    contenedor.innerHTML = '<p>No se encontraron servicios.</p>';
    return;
  }

  servicios.forEach(servicio => {
    const div = document.createElement('div');
    div.className = 'card mb-3';
    div.innerHTML = `
      <div class="card-body">
        <h5 class="card-title">Servicio ID: ${servicio.id}</h5>
        <p class="card-text"><strong>Fecha:</strong> ${servicio.fecha}</p>
        <p class="card-text"><strong>Descripción:</strong> ${servicio.descripcionGeneral}</p>
        <p class="card-text"><strong>Kilometraje:</strong> ${servicio.kilometrajeActual}</p>
        <p class="card-text"><strong>Costo Total:</strong> ${servicio.costoTotal}</p>
        <button class="btn btn-secondary" onclick="verDetalles(${servicio.id})">Ver Detalles</button>
        <div id="detalles-${servicio.id}" class="mt-3"></div>
      </div>
    `;
    contenedor.appendChild(div);
  });
}

async function verDetalles(servicioId) {
  const detallesDiv = document.getElementById(`detalles-${servicioId}`);
  detallesDiv.innerHTML = '<em>Cargando detalles...</em>';

  try {
    const response = await fetch(`http://localhost:8080/taller-backend-1.0-SNAPSHOT/api/servicios/${servicioId}/detalles`);
    const detalles = await response.json();

    if (detalles.length === 0) {
      detallesDiv.innerHTML = '<p>Este servicio no tiene detalles registrados.</p>';
      return;
    }

    let html = '<ul class="list-group">';
    detalles.forEach(det => {
      html += `
        <li class="list-group-item">
          <strong>Trabajo:</strong> ${det.descripcionTrabajo}<br>
          <strong>Repuestos:</strong> ${det.repuestos.map(r => r.nombre).join(', ')}<br>
          <strong>Mecánicos:</strong> ${det.mecanicos.map(m => m.nombre).join(', ')}<br>
          <strong>Costo:</strong> ${det.costo}
        </li>
      `;
    });
    html += '</ul>';

    detallesDiv.innerHTML = html;
  } catch (error) {
    detallesDiv.innerHTML = '<p>Error al cargar los detalles.</p>';
    console.error(error);
  }
}

