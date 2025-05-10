'use strict';
const {
  Model
} = require('sequelize');
module.exports = (sequelize, DataTypes) => {
  class Hotel extends Model {
    /**
     * Helper method for defining associations.
     * This method is not a part of Sequelize lifecycle.
     * The `models/index` file will call this method automatically.
     */
    static associate(models) {
      Hotel.hasMany(models.Habitacion, {
        foreignKey: 'hotelId',
        as: 'habitaciones'
      });
    }
  }
  Hotel.init({
    nombre: DataTypes.STRING,
    direccion: DataTypes.STRING
  }, {
    sequelize,
    modelName: 'Hotel',
  });
  return Hotel;
};