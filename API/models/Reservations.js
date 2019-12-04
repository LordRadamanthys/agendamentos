const db = require('../config/database')

const Reservation = db.sequelize.define('reservation',{
    id:{
        type:db.Sequelize.INTEGER,
        primaryKey:true,
        autoIncrement: true
    },
    userId:{
        type:db.Sequelize.INTEGER,
        allowNull: false
    },
    hour:{
        type:db.Sequelize.STRING,
        allowNull: false
    },
    date:{
        type:db.Sequelize.STRING,
        allowNull: false
        
    },
    description:{
        type:db.Sequelize.STRING,
        allowNull: false
        
    },

    createdAt: {
        type: db.Sequelize.DATE,
        defaultValue: Date.now,
    }
})

Reservation.associate = function (models) {
    // associations can be defined here
    Reservation.belongsTo(models.Users, {
       as: 'user',
       foreignKey: 'userId',
       targetKey: 'id'
    });
   }
module.exports = Reservation
//executar uma unica vez
//Reservation.sync({force:true})