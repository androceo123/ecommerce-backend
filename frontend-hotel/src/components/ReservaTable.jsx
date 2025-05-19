import PropTypes from "prop-types";

export default function ReservaTable({ reservas }) {
  if (reservas.length === 0)
    return <p className="text-gray-600">No hay resultados.</p>;

  return (
    <table className="w-full border text-left">
      <thead className="bg-blue-100">
        <tr>
          <th className="p-2 border">Hotel</th>
          <th className="p-2 border">Habitación</th>
          <th className="p-2 border">Piso</th>
          <th className="p-2 border">Cliente</th>
          <th className="p-2 border">Fecha Entrada</th>
          <th className="p-2 border">Fecha Salida</th>
        </tr>
      </thead>
      <tbody>
        {reservas.map((r) => (
          <tr key={r.id} className="hover:bg-blue-50">
            <td className="p-2 border">{r.hotel}</td>
            <td className="p-2 border">{r.habitacion}</td>
            <td className="p-2 border">{r.piso}</td>
            <td className="p-2 border">{r.cliente}</td>
            <td className="p-2 border">{r.entrada}</td>
            <td className="p-2 border">{r.salida}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

ReservaTable.propTypes = {
  reservas: PropTypes.arrayOf(
    PropTypes.shape({
      id: PropTypes.number.isRequired,
      hotel: PropTypes.string.isRequired,
      habitacion: PropTypes.string.isRequired,
      piso: PropTypes.number.isRequired,
      cliente: PropTypes.string.isRequired,
      entrada: PropTypes.string.isRequired,
      salida: PropTypes.string,
    })
  ).isRequired,
};