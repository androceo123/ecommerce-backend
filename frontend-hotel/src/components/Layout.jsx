import { Outlet, Link } from "react-router-dom";

export default function Layout() {
  return (
    <>
      <nav className="bg-blue-700 text-white px-6 py-4 shadow-md">
        <div className="max-w-7xl mx-auto flex items-center justify-between">
          <Link to="/">
            <h1 className="text-xl font-bold">🏨 Sistema de Reservas</h1>
          </Link>
          <div className="space-x-4">
            <Link to="/reservas/nueva" className="hover:underline">
              Reservar
            </Link>
            <Link to="/reservas" className="hover:underline">
              Ver Reservas
            </Link>
          </div>
        </div>
      </nav>

      <Outlet />
    </>
  );
}
