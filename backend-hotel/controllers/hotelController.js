const { Hotel } = require('../models');

exports.crear = async (req, res) => {
  try {
    const hotel = await Hotel.create(req.body);
    res.status(201).json(hotel);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
};

exports.listar = async (_, res) => {
  const hoteles = await Hotel.findAll();
  res.json(hoteles);
};

exports.obtener = async (req, res) => {
  const hotel = await Hotel.findByPk(req.params.id);
  if (!hotel) return res.status(404).json({ error: 'No existe' });
  res.json(hotel);
};

exports.actualizar = async (req, res) => {
  const hotel = await Hotel.findByPk(req.params.id);
  if (!hotel) return res.status(404).json({ error: 'No existe' });
  await hotel.update(req.body);
  res.json(hotel);
};

exports.borrar = async (req, res) => {
  const hotel = await Hotel.findByPk(req.params.id);
  if (!hotel) return res.status(404).json({ error: 'No existe' });
  await hotel.destroy();
  res.status(204).end();
};
