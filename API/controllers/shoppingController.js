const Reservations = require('../models/Reservations')
const User = require('../models/User')
const Services = require('../models/Services')
const Shopping = require('../models/Shopping')
const Sequelize = require('sequelize')
const util = require('../functions/Utils')
const firebase = require('../config/firebase/sendFireBaseMessage')

module.exports = {

    // async newShopping(req, res) {

    //     var { id, servicesId, reservationsId } = req.body


    //     if (contais) return res.json({ erro: "serviço ja existe" })
    //     var service
    //     try {
    //         response = await Shopping.create({
    //             id,
    //             reservationsId,
    //             servicesId,
    //         })
    //         return res.status(200).json(response)
    //     } catch (error) {
    //         return res.status(400).json(error)
    //     }

    // },

    async getShoppings(req, res) {

        await Shopping.findAll({ include: [Services] }).then((response) => {
            return res.status(200).json(response)
        }).catch((error) => {
            return res.status(400).json(error.message)
        })

    },

    async getShoppingReservation(req, res) {

        await Shopping.findAll({where:{reservationId:req.body.reservationId}, include: [Services] }).then((response) => {
            return res.status(200).json(response)
        }).catch((error) => {
            return res.status(400).json(error.message)
        })

    },



    //   async updateService(req, res){
    //     var { title, value, status, description } = req.body
    //     if (!title) return res.json({ erro: "titulo não pode ser vazio" })
    //     if (!value) return res.json({ erro: "valor não pode ser vazio" })

    //     var service
    //     try {
    //       service = await Service.update({
    //         title,
    //         value,
    //         status,
    //         description
    //       }, {
    //         where:{
    //           id:req.body.id
    //         }
    //       })
    //       return res.status(200).json(service)
    //     } catch (error) {
    //       return res.status(400).json(error)
    //     }
    //   },

    //   async deleteService(req, res) {
    //     var response = await Service.destroy({ where: { id: req.body.id } }).then((response) => {
    //       if (response == 0) return res.status(400).send({ message: "Este serviço não existe" })

    //       return res.status(200).send({ message: "Serviço excluido com sucesso" })
    //     }).catch((error) => {
    //       return res.status(400).send({ error: "Erro ao excluir serviço" })
    //     })


    //   }
}