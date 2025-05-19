import { Link } from "react-router-dom";

export default function Home() {
  return (
    <main className="max-w-4xl mx-auto mt-20 text-center">
      <h2 className="text-3xl font-bold mb-6">Bienvenido al sistema</h2>
      <p className="text-lg mb-12">Seleccione una opción para comenzar</p>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-8">
        <div className="bg-white p-6 rounded-lg shadow hover:shadow-lg transition">
          <h3 className="text-2xl font-semibold mb-2">
            🛏️ Reservar Habitación
          </h3>
          <p className="mb-4">
            Buscá habitaciones disponibles por fecha y capacidad.
          </p>
          <Link
            to="/reservas/nueva"
            className="inline-block bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded"
          >
            Ir a Reservar
          </Link>
        </div>

        <div className="bg-white p-6 rounded-lg shadow hover:shadow-lg transition">
          <h3 className="text-2xl font-semibold mb-2">📋 Lista de Reservas</h3>
          <p className="mb-4">Visualizá y filtrá las reservas existentes.</p>
          <Link
            to="/reservas"
            className="inline-block bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded"
          >
            Ver Reservas
          </Link>
        </div>
      </div>
    </main>
  );
}
