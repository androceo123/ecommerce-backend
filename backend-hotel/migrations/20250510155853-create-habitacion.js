"use strict";
/** @type {import('sequelize-cli').Migration} */
module.exports = {
  async up(queryInterface, Sequelize) {
    await queryInterface.createTable("Habitacions", {
      id: {
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
        type: Sequelize.INTEGER,
      },
      numero: {
        type: Sequelize.INTEGER,
        allowNull: false,
      },
      hotelId: {
        type: Sequelize.INTEGER,
        allowNull: false,
        references: {
          model: "Hotels", // nombre de la tabla de hoteles
          key: "id",
        },
        onUpdate: "CASCADE",
        onDelete: "SET NULL",
      },
      posX: {
        type: Sequelize.FLOAT,
      },
      posY: {
        type: Sequelize.FLOAT,
      },
      piso: {
        type: Sequelize.STRING,
      },
      capacidad: {
        type: Sequelize.INTEGER,
      },
      caracteristicas: {
        type: Sequelize.TEXT,
      },
      createdAt: {
        allowNull: false,
        type: Sequelize.DATE,
      },
      updatedAt: {
        allowNull: false,
        type: Sequelize.DATE,
      },
    });
  },
  async down(queryInterface, Sequelize) {
    await queryInterface.dropTable("Habitacions");
  },
};
