import { useState } from "react";
import SearchForm from "../components/SearchForm";
import RoomList from "../components/RoomList";
import ClientForm from "../components/ClientForm";

export default function Reservar() {
  const [rooms, setRooms] = useState([]);
  const [selectedRoom, setSelectedRoom] = useState(null);

  return (
    <div className="max-w-5xl mx-auto p-6">
      <h1 className="text-3xl font-bold mb-6">🛏️ Reservar Habitación</h1>
      <SearchForm onSearch={setRooms} />
      {rooms.length > 0 && (
        <RoomList rooms={rooms} selectedRoom={selectedRoom} onSelectRoom={setSelectedRoom} />
      )}
      {selectedRoom && <ClientForm roomId={selectedRoom} />}
    </div>
  );
}