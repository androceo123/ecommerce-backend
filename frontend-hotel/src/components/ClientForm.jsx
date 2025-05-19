import { useState } from "react";
import PropTypes from "prop-types";

export default function ClientForm({ roomId }) {
  const [cliente, setCliente] = useState({
    cedula: "",
    nombre: "",
    apellido: "",
  });

  const handleChange = (e) =>
    setCliente({ ...cliente, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    // TODO: Enviar a backend
    console.log("Reserva confirmada:", { ...cliente, roomId });
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
};
