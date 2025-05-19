import { useState } from "react";
import ReservaFilters from "../components/ReservaFilters";
import ReservaTable from "../components/ReservaTable";

export default function ListaReservas() {
  const [reservas, setReservas] = useState([]);

  return (
    <div className="max-w-6xl mx-auto p-6">
      <h1 className="text-3xl font-bold mb-6">📋 Lista de Reservas</h1>
      <ReservaFilters onSearch={setReservas} />
      <ReservaTable reservas={reservas} />
    </div>
  );
}
