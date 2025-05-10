const { Habitacion, Hotel } = require('../models');

exports.crear = async (req, res) => {
  try {
    const hab = await Habitacion.create(req.body);
    res.status(201).json(hab);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
};

exports.listar = async (_, res) => {
  const habs = await Habitacion.findAll({ include: Hotel });
  res.json(habs);
};

exports.obtener = async (req, res) => {
  const hab = await Habitacion.findByPk(req.params.id, { include: Hotel });
  if (!hab) return res.status(404).json({ error: 'No existe' });
  res.json(hab);
};

exports.actualizar = async (req, res) => {
  const hab = await Habitacion.findByPk(req.params.id);
  if (!hab) return res.status(404).json({ error: 'No existe' });
  await hab.update(req.body);
  res.json(hab);
};

exports.borrar = async (req, res) => {
  const hab = await Habitacion.findByPk(req.params.id);
  if (!hab) return res.status(404).json({ error: 'No existe' });
  await hab.destroy();
  res.status(204).end();
};
