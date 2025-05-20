'use strict';
module.exports = {
  async up(queryInterface, Sequelize) {
    await queryInterface.createTable('reservas', {
      id: {
        allowNull: false,
        autoIncrement: true,
        primaryKey: true,
        type: Sequelize.INTEGER
      },
      fechaIngreso: {
        type: Sequelize.DATEONLY,
        allowNull: false
      },
      fechaSalida: {
        type: Sequelize.DATEONLY,
        allowNull: false
      },
      cantidadPersonas: {
        type: Sequelize.INTEGER
      },
      hotelId: {
        type: Sequelize.INTEGER,
        references: {
          model: 'Hotels',
          key: 'id'
        },
        onUpdate: 'CASCADE',
        onDelete: 'CASCADE'
      },
      habitacionId: {
        type: Sequelize.INTEGER,
        references: {
          model: 'Habitacions',
          key: 'id'
        },
        onUpdate: 'CASCADE',
        onDelete: 'CASCADE'
      },
      clienteId: {
        type: Sequelize.INTEGER,
        references: {
          model: 'Clientes',
          key: 'id'
        },
        onUpdate: 'CASCADE',
        onDelete: 'CASCADE'
      },
      createdAt: {
        allowNull: false,
        type: Sequelize.DATE
      },
      updatedAt: {
        allowNull: false,
        type: Sequelize.DATE
      }
    });
  },

  async down(queryInterface, Sequelize) {
    await queryInterface.dropTable('reservas');
  }
};
