const db = require('../config/database')
const Services = require('./Services')

const Shopping = db.sequelize.define('shopping', {
    id: {
        type: db.Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    reservationId: {
        type: db.Sequelize.INTEGER,
        allowNull: false,
        references: {         // User belongsTo Company 1:1
            model: 'reservations',
            key: 'id'
          }
    },
    serviceId: {
        type: db.Sequelize.INTEGER,
        allowNull: false,
        references: {         // User belongsTo Company 1:1
            model: 'services',
            key: 'id'
          }
    },
   

})


//Reservation.belongsTo(Reservation)
// Shopping.hasMany(Services);//hasMany depending on your relationship
Shopping.belongsTo(Services);
module.exports = Shopping
//executar uma unica vez
//  Shopping.sync({force:true})