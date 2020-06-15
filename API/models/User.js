const db = require('../config/database')
const bcryptjs = require('bcryptjs')

const User = db.sequelize.define('users', {
    
    id: {
        type: db.Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true
    },
    name: {
        type: db.Sequelize.STRING,
        allowNull: false
    },
    email: {
        type: db.Sequelize.STRING,
        allowNull: false
    },

    phone: {
        type: db.Sequelize.STRING,
        // allowNull: false
    },

    password: {
        type: db.Sequelize.STRING,
        allowNull: false
        

    },
    device: {
        type: db.Sequelize.STRING,
        allowNull: false
        
    },
    admin: {
        type: db.Sequelize.BOOLEAN,
        allowNull: false,
        defaultValue: false
    },
    
    passwordResetToken: {
        type: db.Sequelize.STRING,
        select: false
    },

    passwordResetExpires: {
        type: db.Sequelize.DATE,
        select: false
    },

    createdAt: {
        type: db.Sequelize.DATE,
        defaultValue: Date.now,
    }
},{
    //remove password do retorno
    scopes: {
        withoutPassword: {
            attributes: { exclude: ['password'] },
          }
      },
    hooks: {
        beforeValidate: async function (user, fn) {
            user.password = await bcryptjs.hash(user.password, 10)
            
        }
    }
})

module.exports = User
//executar uma unica vez
// User.sync({force:true})