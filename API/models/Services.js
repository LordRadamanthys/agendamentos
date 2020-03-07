const db = require('../config/database')
const Users = require('./User')
const Reservation = require('./Reservations')

const Services = db.sequelize.define('services', {
    id: {
        type: db.Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    title: {
        type: db.Sequelize.STRING,
        allowNull: false,
    },

    value: {
        type: db.Sequelize.DOUBLE,
        allowNull: false,
    },

    status: {
        type: db.Sequelize.BOOLEAN,
        allowNull: false,
        defaultValue: false,
    },
    description: {
        type: db.Sequelize.STRING,
        allowNull: false
    },

    createdAt: {
        type: db.Sequelize.DATE,
        defaultValue: Date.now,
    }
})


//Services.belongsTo(Reservation)

module.exports = Services
//executar uma unica vez
//Services.sync({ force: true })