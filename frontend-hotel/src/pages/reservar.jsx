import { useState } from "react";
import SearchForm from "../components/SearchForm";
import RoomList from "../components/RoomList";
import ClientForm from "../components/ClientForm";

export default function Reservar() {
  const [rooms, setRooms] = useState([]);
  const [selectedRoom, setSelectedRoom] = useState(null);
  const [filtros, setFiltros] = useState(null); // entrada, salida, personas

  return (
    <div className="max-w-5xl mx-auto p-6">
      <h1 className="text-3xl font-bold mb-6">🛏️ Reservar Habitación</h1>

      <SearchForm
        onSearch={(habitaciones, datosBusqueda) => {
          setRooms(habitaciones);
          setFiltros(datosBusqueda);
          setSelectedRoom(null); // resetear selección al buscar de nuevo
        }}
      />

      {rooms.length > 0 && (
        <RoomList
          rooms={rooms}
          selectedRoom={selectedRoom}
          onSelectRoom={setSelectedRoom}
        />
      )}

      {selectedRoom && filtros && (
        <ClientForm
          roomId={selectedRoom}
          hotelId={filtros.hotelId} // Ajustar si se quiere seleccionar desde SearchForm
          fechaIngreso={filtros.entrada}
          fechaSalida={filtros.salida}
          cantidadPersonas={filtros.personas}
        />
      )}
    </div>
  );
}
