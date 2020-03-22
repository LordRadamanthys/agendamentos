const Reservations = require('../models/Reservations')
const User = require('../models/User')
const Sequelize = require('sequelize') 

module.exports = {
    async isAdmin(req) {
        const {admin} = await User.findOne({where:{id:req.userId}})
        return admin
    },
    async getAdms(){
        var user = await User.scope('withoutPassword').findAll({ where: { admin: true } })
        return user
    },

    async makeNewUserJson(user, token){
        return  newUser = {
            id: user.id,
            name: user.name,
            email: user.email,
            passowrd: user.password,
            device: user.device,
            admin: user.admin,
            token
        }
    }

}