const { Reserva, Habitacion, Cliente, Hotel } = require("../models");
const { Op } = require("sequelize");

exports.crearReserva = async (req, res) => {
  try {
    console.log("➡️ BODY recibido:", req.body);

    const {
      fechaIngreso,
      fechaSalida,
      cantidadPersonas,
      hotelId,
      habitacionId,
      cedula,
      nombre,
      apellido,
    } = req.body;

    if (!fechaIngreso || !fechaSalida || !hotelId || !habitacionId || !cedula) {
      return res.status(400).json({ error: "Faltan datos obligatorios en la reserva" });
    }

    const parsedHotelId = parseInt(hotelId);
    const parsedHabitacionId = parseInt(habitacionId);

    // Validar disponibilidad
    const reservasOcupadas = await Reserva.findOne({
      where: {
        habitacionId: parsedHabitacionId,
        [Op.or]: [
          {
            fechaIngreso: {
              [Op.lt]: fechaSalida,
            },
            fechaSalida: {
              [Op.gt]: fechaIngreso,
            },
          },
        ],
      },
    });

    if (reservasOcupadas) {
      return res.status(400).json({ error: "Habitación no disponible" });
    }

    // Buscar o crear cliente
    let cliente = await Cliente.findOne({ where: { cedula } });
    if (!cliente) {
      cliente = await Cliente.create({ cedula, nombre, apellido });
    }

    // Crear reserva
    const reserva = await Reserva.create({
      fechaIngreso,
      fechaSalida,
      cantidadPersonas,
      hotelId: parsedHotelId,
      habitacionId: parsedHabitacionId,
      clienteId: cliente.id,
    });

    res.status(201).json(reserva);
  } catch (error) {
    console.error("Error creando reserva:", error.message, error.stack);
    res.status(500).json({ error: "Error interno del servidor" });
  }
};

exports.listarReservas = async (req, res) => {
  try {
    const { hotelId, fechaIngreso, fechaSalida, clienteId } = req.query;

    if (!hotelId || !fechaIngreso) {
      return res.status(400).json({ error: "hotelId y fechaIngreso son obligatorios" });
    }

    const where = {
      hotelId,
      fechaIngreso: {
        [Op.gte]: fechaIngreso,
      },
    };

    if (fechaSalida) {
      where.fechaSalida = {
        [Op.lte]: fechaSalida,
      };
    }

    if (clienteId) {
      where.clienteId = clienteId;
    }

    const reservas = await Reserva.findAll({
      where,
      include: [
        { model: Cliente },
        {
          model: Habitacion,
          include: [{ model: Hotel, as: "hotel" }],
        },
      ],
      order: [
        ["fechaIngreso", "ASC"],
        [Habitacion, "piso", "ASC"],
        [Habitacion, "numero", "ASC"],
      ],
    });

    res.json(reservas);
  } catch (error) {
    console.error("Error listando reservas:", error.message, error.stack);
    res.status(500).json({ error: "Error interno del servidor" });
  }
};
