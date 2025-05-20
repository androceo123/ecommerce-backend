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
            <td className="p-2 border">{r.Habitacion?.hotel?.nombre || r.hotelId}</td>
            <td className="p-2 border">{r.Habitacion?.numero}</td>
            <td className="p-2 border">{r.Habitacion?.piso}</td>
            <td className="p-2 border">{r.Cliente?.nombre} {r.Cliente?.apellido}</td>
            <td className="p-2 border">{r.fechaIngreso}</td>
            <td className="p-2 border">{r.fechaSalida}</td>
          </tr>
        ))}
      </tbody>
    </table>
  );
}

ReservaTable.propTypes = {
  reservas: PropTypes.array.isRequired,
};
