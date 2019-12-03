const Sequelize = require('sequelize')

const sequelize = new Sequelize('db_agenda','root','',{
    host:'localhost',
    dialect:'mysql'
})

module.exports = {
    Sequelize : Sequelize,
    sequelize : sequelize
}