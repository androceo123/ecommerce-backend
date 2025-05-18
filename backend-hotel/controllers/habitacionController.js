const { Habitacion, Hotel } = require("../models");

exports.crear = async (req, res) => {
  try {
    const hab = await Habitacion.create(req.body);
    res.status(201).json(hab);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
};

exports.listar = async (_, res) => {
  try {
    const habs = await Habitacion.findAll({
      include: [
        {
          model: Hotel,
          as: "hotel",
        },
      ],
    });
    res.json(habs);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
};

exports.obtener = async (req, res) => {
  try {
    const hab = await Habitacion.findByPk(req.params.id, {
      include: [
        {
          model: Hotel,
          as: "hotel",
        },
      ],
    });
    if (!hab) return res.status(404).json({ error: "No existe" });
    res.json(hab);
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
};

exports.actualizar = async (req, res) => {
  try {
    const hab = await Habitacion.findByPk(req.params.id);
    if (!hab) return res.status(404).json({ error: "No existe" });
    await hab.update(req.body);
    res.json(hab);
  } catch (err) {
    res.status(400).json({ error: err.message });
  }
};

exports.borrar = async (req, res) => {
  try {
    const hab = await Habitacion.findByPk(req.params.id);
    if (!hab) return res.status(404).json({ error: "No existe" });
    await hab.destroy();
    res.status(204).end();
  } catch (err) {
    res.status(500).json({ error: err.message });
  }
};
