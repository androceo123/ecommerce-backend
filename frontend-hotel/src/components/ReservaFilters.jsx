import { useState, useEffect } from "react";
import PropTypes from "prop-types";

export default function ReservaFilters({ onSearch }) {
  const [filters, setFilters] = useState({
    hotelId: "",
    entrada: "",
    salida: "",
    clienteId: "",
  });

  const [hoteles, setHoteles] = useState([]);
  const [clientes, setClientes] = useState([]);

  useEffect(() => {
    fetch("http://localhost:3000/api/hoteles")
      .then((res) => res.json())
      .then(setHoteles);

    fetch("http://localhost:3000/api/clientes")
      .then((res) => res.json())
      .then(setClientes);
  }, []);

  const handleChange = (e) =>
    setFilters({ ...filters, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();

    const query = new URLSearchParams();
    query.append("hotelId", filters.hotelId);
    query.append("fechaIngreso", filters.entrada);
    if (filters.salida) query.append("fechaSalida", filters.salida);
    if (filters.clienteId) query.append("clienteId", filters.clienteId);

    const res = await fetch(`http://localhost:3000/api/reservas?${query}`);
    const data = await res.json();
    onSearch(data);
  };

  return (
    <form onSubmit={handleSubmit} className="grid md:grid-cols-4 gap-4 mb-6">
      <select
        name="hotelId"
        required
        value={filters.hotelId}
        onChange={handleChange}
        className="p-2 border rounded"
      >
        <option value="">Seleccionar Hotel</option>
        {hoteles.map((h) => (
          <option key={h.id} value={h.id}>
            {h.nombre}
          </option>
        ))}
      </select>

      <input
        name="entrada"
        type="date"
        required
        value={filters.entrada}
        onChange={handleChange}
        className="p-2 border rounded"
      />
      <input
        name="salida"
        type="date"
        value={filters.salida}
        onChange={handleChange}
        className="p-2 border rounded"
      />
      <select
        name="clienteId"
        value={filters.clienteId}
        onChange={handleChange}
        className="p-2 border rounded"
      >
        <option value="">Todos los Clientes</option>
        {clientes.map((c) => (
          <option key={c.id} value={c.id}>
            {c.nombre} {c.apellido}
          </option>
        ))}
      </select>

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
