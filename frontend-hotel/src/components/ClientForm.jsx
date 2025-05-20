import { useState } from "react";
import PropTypes from "prop-types";

export default function ClientForm({ roomId, hotelId, fechaIngreso, fechaSalida, cantidadPersonas }) {
  const [cliente, setCliente] = useState({
    cedula: "",
    nombre: "",
    apellido: "",
  });

  const handleChange = (e) =>
    setCliente({ ...cliente, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();

    const body = {
      fechaIngreso,
      fechaSalida,
      cantidadPersonas,
      hotelId,
      habitacionId: roomId,
      cedula: cliente.cedula,
      nombre: cliente.nombre,
      apellido: cliente.apellido,
    };

    try {
      const res = await fetch("http://localhost:3000/api/reservas", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(body),
      });

      if (res.ok) {
        alert("Reserva confirmada");
      } else {
        const err = await res.json();
        alert("Error al confirmar: " + err.error);
      }
    } catch (err) {
      console.error("Error al conectar:", err);
      alert("Error de red");
    }
  };

  return (
    <form onSubmit={handleSubmit} className="grid md:grid-cols-3 gap-4">
      <input
        name="cedula"
        placeholder="Cédula"
        required
        onChange={handleChange}
        className="p-2 border rounded"
      />
      <input
        name="nombre"
        placeholder="Nombre"
        onChange={handleChange}
        className="p-2 border rounded"
      />
      <input
        name="apellido"
        placeholder="Apellido"
        onChange={handleChange}
        className="p-2 border rounded"
      />
      <button
        type="submit"
        className="md:col-span-3 bg-green-600 hover:bg-green-700 text-white px-4 py-2 rounded"
      >
        Confirmar Reserva
      </button>
    </form>
  );
}

ClientForm.propTypes = {
  roomId: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
  hotelId: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
  fechaIngreso: PropTypes.string.isRequired,
  fechaSalida: PropTypes.string.isRequired,
  cantidadPersonas: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
};
