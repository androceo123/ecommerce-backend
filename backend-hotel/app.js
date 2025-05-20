require("dotenv").config();
const express = require("express");
const { sequelize } = require("./models");
const hotelesRoutes = require("./routes/hoteles");
const habitacionesRoutes = require("./routes/habitaciones");
const clientesRoutes = require("./routes/clientes");
const reservasRoutes = require("./routes/reservas");


const app = express();
app.use(express.json());

app.use("/api/hoteles", hotelesRoutes);
app.use("/api/habitaciones", habitacionesRoutes);
app.use("/api/clientes", clientesRoutes);
app.use("/api/reservas", reservasRoutes);
app.use((_, res) => {
  res.status(404).json({ error: "Ruta no encontrada" });
});

const PORT = process.env.PORT || 3000;
app.listen(PORT, async () => {
  console.log(`Servidor escuchando en http://localhost:${PORT}`);
  await sequelize.authenticate();
  console.log("Conectado a la base de datos.");
});
