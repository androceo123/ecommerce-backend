import "./App.css";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./components/Layout";
import Home from "./pages/home";
import Reservar from "./pages/reservar";
import ListaReservas from "./pages/listaReservas";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route path="/" element={<Home />} />
          <Route path="/reservas/nueva" element={<Reservar />} />
          <Route path="/reservas" element={<ListaReservas />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
