import { useState } from "react";

// eslint-disable-next-line react/prop-types
export default function SearchForm({ onSearch }) {
  const [form, setForm] = useState({
    entrada: "",
    salida: "",
    personas: "",
  });

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    // TODO: Llamar a backend con parámetros
    const dummyRooms = [
      { id: 1, numero: "101", piso: 1, capacidad: 3 },
      { id: 2, numero: "203", piso: 2, capacidad: 2 },
    ];
    onSearch(dummyRooms);
  };

  return (
    <form onSubmit={handleSubmit} className="grid md:grid-cols-3 gap-4 mb-6">
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
        required
        onChange={handleChange}
        className="p-2 border rounded"
      />
      <input
        name="personas"
        type="number"
        min="1"
        onChange={handleChange}
        placeholder="Cantidad de personas"
        className="p-2 border rounded"
      />
      <button
        type="submit"
        className="md:col-span-3 bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded"
      >
        Buscar Habitaciones
      </button>
    </form>
  );
}
