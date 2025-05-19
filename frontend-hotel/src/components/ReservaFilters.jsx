import { useState } from "react";
import PropTypes from "prop-types";

export default function ReservaFilters({ onSearch }) {
  const [filters, setFilters] = useState({
    hotel: "",
    entrada: "",
    salida: "",
    cliente: "",
  });

  const handleChange = (e) =>
    setFilters({ ...filters, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();

    const normalizeText = (text) =>
      text
        .normalize("NFD")
        .replace(/[\u0300-\u036f]/g, "")
        .toLowerCase();

    // TODO: Reemplazar con llamada real a la API
    const dummy = [
      {
        id: 1,
        hotel: "Hotel Central",
        habitacion: "101",
        piso: 1,
        cliente: "Juan Pérez",
        entrada: "2025-06-01",
        salida: "2025-06-03",
      },
      {
        id: 2,
        hotel: "Hotel Central",
        habitacion: "203",
        piso: 2,
        cliente: "María Gómez",
        entrada: "2025-06-02",
        salida: "2025-06-05",
      },
    ];

    const filtered = dummy
      .filter((r) => r.hotel.includes(filters.hotel))
      .filter((r) => r.entrada >= filters.entrada)
      .filter((r) => (filters.salida ? r.salida <= filters.salida : true))
      .filter((r) =>
        filters.cliente
          ? normalizeText(r.cliente).includes(normalizeText(filters.cliente))
          : true
      )
      .sort((a, b) => {
        if (a.entrada !== b.entrada) return a.entrada.localeCompare(b.entrada);
        if (a.piso !== b.piso) return a.piso - b.piso;
        return a.habitacion.localeCompare(b.habitacion);
      });

    onSearch(filtered);
  };

  return (
    <form onSubmit={handleSubmit} className="grid md:grid-cols-4 gap-4 mb-6">
      <input
        name="hotel"
        required
        placeholder="Hotel"
        onChange={handleChange}
        className="p-2 border rounded"
      />
      <input
        name="entrada"
        type="date"
        required
        onChange={handleChange}
        className="p-2 border rounded"
      />
      <input
        name="salida"
        type="date"
        onChange={handleChange}
        className="p-2 border rounded"
      />
      <input
        name="cliente"
        placeholder="Cliente (opcional)"
        onChange={handleChange}
        className="p-2 border rounded"
      />
      <button
        type="submit"
        className="md:col-span-4 bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded"
      >
        Buscar
      </button>
    </form>
  );
}

ReservaFilters.propTypes = {
  onSearch: PropTypes.func.isRequired,
};
