import { useState, useEffect } from "react";

// eslint-disable-next-line react/prop-types
export default function SearchForm({ onSearch }) {
  const [form, setForm] = useState({
    hotelId: "",
    entrada: "",
    salida: "",
    personas: "",
  });

  const [hoteles, setHoteles] = useState([]);

  useEffect(() => {
    fetch("http://localhost:3000/api/hoteles")
      .then((res) => res.json())
      .then((data) => {
        console.log("✅ Hoteles cargados:", data);
        setHoteles(data);
      })
      .catch((err) => console.error("❌ Error al cargar hoteles:", err));
  }, []);

  const handleChange = (e) =>
    setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();

    const { hotelId, entrada, salida, personas } = form;

    // ✅ Validación: fecha ingreso debe ser anterior a salida
    if (new Date(entrada) >= new Date(salida)) {
      alert("⚠️ La fecha de ingreso debe ser anterior a la fecha de salida.");
      return;
    }

    const query = new URLSearchParams();
    query.append("hotelId", hotelId);
    query.append("fechaIngreso", entrada);
    query.append("fechaSalida", salida);
    if (personas) query.append("capacidad", personas);

    try {
      const res = await fetch(
        `http://localhost:3000/api/habitaciones/disponibles?${query}`
      );
      const habitaciones = await res.json();
      onSearch(habitaciones, form);
    } catch (err) {
      console.error("Error al buscar habitaciones:", err);
      alert("Error al buscar habitaciones disponibles");
    }
  };

  return (
    <form onSubmit={handleSubmit} className="grid md:grid-cols-4 gap-4 mb-6">
      <select
        name="hotelId"
        value={form.hotelId}
        onChange={handleChange}
        required
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
        className="md:col-span-4 bg-blue-600 hover:bg-blue-700 text-white px-4 py-2 rounded"
      >
        Buscar Habitaciones
      </button>
    </form>
  );
}
