const express = require("express");
const router = express.Router();
const { Cliente } = require("../models"); // Asegurate de que el path sea correcto

exports.listar = async (_, res) => {
  try {
    const clientes = await Cliente.findAll();
    res.json(clientes);
  } catch (error) {
    res
      .status(500)
      .json({ error: `Error al obtener los clientes ${error.message}` });
  }
};

exports.crear = async (req, res) => {
  try {
    const { cedula, nombre, apellido } = req.body;

    // Validación básica
    if (!cedula || !nombre || !apellido) {
      return res
        .status(400)
        .json({ error: "Todos los campos son obligatorios" });
    }

    const nuevoCliente = await Cliente.create({ cedula, nombre, apellido });
    res.status(201).json(nuevoCliente);
  } catch (error) {
    res
      .status(400)
      .json({ error: `Error al crear el cliente ${error.message}` });
  }
};
