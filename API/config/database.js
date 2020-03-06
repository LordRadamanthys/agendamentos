const Sequelize = require('sequelize')

const sequelize = new Sequelize('db_agenda','root','admin',{
    host:'localhost',
    dialect:'mysql'
})

module.exports = {
    Sequelize : Sequelize,
    sequelize : sequelize
}