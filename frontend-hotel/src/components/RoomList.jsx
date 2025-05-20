import PropTypes from 'prop-types';
import clsx from 'clsx';

export default function RoomList({ rooms, selectedRoom, onSelectRoom }) {
  return (
    <div className="grid gap-4 mb-6">
      {rooms.map(room => (
        <div
          key={room.id}
          onClick={() => onSelectRoom(room.id)}
          className={clsx(
            "p-4 bg-white shadow hover:bg-blue-50 cursor-pointer rounded",
            room.id === selectedRoom ? "border-2 border-blue-500" : ""
          )}
        >
          <h3 className="font-semibold">Habitación {room.numero}</h3>
          <p className="text-sm text-gray-800">
            <strong>Hotel:</strong> {room.hotel?.nombre}<br />
            <strong>Piso:</strong> {room.piso} &nbsp;|&nbsp; 
            <strong>Capacidad:</strong> {room.capacidad} personas<br />
            <strong>Características:</strong> {room.caracteristicas}
          </p>
        </div>
      ))}
    </div>
  );
}

RoomList.propTypes = {
  rooms: PropTypes.array.isRequired,
  selectedRoom: PropTypes.number,
  onSelectRoom: PropTypes.func.isRequired,
};
