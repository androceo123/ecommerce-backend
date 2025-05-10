require('dotenv').config();
const express = require('express');
const { sequelize } = require('./models');
const hoteles     = require('./routes/hoteles');
const habitaciones= require('./routes/habitaciones');

const app = express();
app.use(express.json());

app.use('/api/hoteles',     hoteles);
app.use('/api/habitaciones', habitaciones);

const PORT = process.env.PORT || 3000;
app.listen(PORT, async () => {
  console.log(`Servidor escuchando en http://localhost:${PORT}`);
  await sequelize.authenticate();
  console.log('Conectado a la base de datos.');
});
