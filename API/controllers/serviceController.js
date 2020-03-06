const Reservations = require('../models/Reservations')
const User = require('../models/User')
const Service = require('../models/Services')
const Sequelize = require('sequelize')
const util = require('../functions/Utils')
const firebase = require('../config/firebase/sendFireBaseMessage')

module.exports = {

    async newService(req, res) {
        var { title, value, status, description } = req.body
        var contais = await Service.findOne({where:{title:title}})
        if(contais) return res.json({erro:"serviço ja existe"})
       var service = await Service.create({
         title,  
         value,  
         status,
         description
       }).then((response)=>{
        return res.status(200).json(response)
       }).catch((error)=>{
        return res.status(400).json(error)
       })
    }
}