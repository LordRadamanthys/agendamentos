const db = require('../config/database')
const Users = require('./User')
const Reservation = db.sequelize.define('reservation', {
    id: {
        type: db.Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    userId: {
        type: db.Sequelize.INTEGER,
        allowNull: false,
        references: {         // User belongsTo Company 1:1
            model: 'users',
            key: 'id'
          }
    },

    hour: {
        type: db.Sequelize.STRING,
        allowNull: false
    },
    date: {
        type: db.Sequelize.STRING,
        allowNull: false

    },

    fullPrice: {
        type: db.Sequelize.DOUBLE,
        allowNull: false

    },

    status: {
        type: db.Sequelize.ENUM,
        allowNull: false,
        values: ['marcado', 'feito', 'desmarcado']
    },
    description: {
        type: db.Sequelize.STRING,
        allowNull: false
    },
    reasonToUncheck: {
        type: db.Sequelize.TEXT,
        allowNull: true
    },

    createdAt: {
        type: db.Sequelize.DATE,
        defaultValue: Date.now,
    },
    updatedAt: {
        type: db.Sequelize.DATE,
        defaultValue: Date.now,
    }
}, {
    //remove password do retorno
    scopes: {
        withoutPassword: {
            attributes: { exclude: ['password'] },
        }
    }
})



Reservation.belongsTo(Users)

module.exports = Reservation
//executar uma unica vez
// Reservation.sync({force:true})