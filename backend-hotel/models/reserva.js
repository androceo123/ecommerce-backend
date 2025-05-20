module.exports = (sequelize, DataTypes) => {
  const Reserva = sequelize.define("Reserva", {
    fechaIngreso: {
      type: DataTypes.DATEONLY,
      allowNull: false,
    },
    fechaSalida: {
      type: DataTypes.DATEONLY,
      allowNull: false,
    },
    cantidadPersonas: {
      type: DataTypes.INTEGER,
      allowNull: true,
    },
  }, {
    tableName: "reservas"  // 👈 nombre de tabla forzado
  });

  Reserva.associate = (models) => {
    Reserva.belongsTo(models.Hotel, { foreignKey: "hotelId" });
    Reserva.belongsTo(models.Habitacion, { foreignKey: "habitacionId" });
    Reserva.belongsTo(models.Cliente, { foreignKey: "clienteId" });
  };

  return Reserva;
};
