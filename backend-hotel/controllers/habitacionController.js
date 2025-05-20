const { Habitacion, Hotel, Reserva } = require("../models");
const { Op } = require("sequelize");

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

exports.disponibles = async (req, res) => {
  try {
    const { hotelId, fechaIngreso, fechaSalida, capacidad } = req.query;

    if (!hotelId || !fechaIngreso) {
      return res.status(400).json({ error: "hotelId y fechaIngreso son obligatorios" });
    }

    const parsedHotelId = parseInt(hotelId);
    const parsedCapacidad = capacidad ? parseInt(capacidad) : 0;

    const habitaciones = await Habitacion.findAll({
      where: {
        hotelId: parsedHotelId,
        capacidad: {
          [Op.gte]: parsedCapacidad,
        },
      },
      include: [{ model: Hotel, as: "hotel" }],
    });

    const disponibles = [];

    for (const hab of habitaciones) {
      const reservasSolapadas = await Reserva.findOne({
        where: {
          habitacionId: hab.id,
          [Op.and]: [
            { fechaIngreso: { [Op.lt]: fechaSalida } },
            { fechaSalida: { [Op.gt]: fechaIngreso } },
          ],
        },
      });

      if (!reservasSolapadas) {
        disponibles.push(hab);
      }
    }

    res.json(disponibles);
  } catch (error) {
    console.error("Error en habitaciones disponibles:", error);
    res.status(500).json({ error: error.message });
  }
};
