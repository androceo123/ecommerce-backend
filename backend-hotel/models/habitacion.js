'use strict';
const { Model } = require('sequelize');

module.exports = (sequelize, DataTypes) => {
  class Habitacion extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      // Cada habitación pertenece a un hotel
      Habitacion.belongsTo(models.Hotel, {
        foreignKey: 'hotelId',
        as: 'hotel'
      });
    }
  }

  Habitacion.init({
    numero:          DataTypes.INTEGER,
    hotelId:         {
      type: DataTypes.INTEGER,
      allowNull: false
    },
    posX:            DataTypes.FLOAT,
    posY:            DataTypes.FLOAT,
    piso:            DataTypes.STRING,
    capacidad:       DataTypes.INTEGER,
    caracteristicas: DataTypes.TEXT
  }, {
    sequelize,
    modelName: 'Habitacion',
    tableName: 'Habitacions',
    timestamps: true
  });

  return Habitacion;
};
