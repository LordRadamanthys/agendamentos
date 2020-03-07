const Reservations = require('../models/Reservations')
const User = require('../models/User')
const Service = require('../models/Services')
const Sequelize = require('sequelize')
const util = require('../functions/Utils')
const firebase = require('../config/firebase/sendFireBaseMessage')

module.exports = {

  async newService(req, res) {

    var { title, value, status, description } = req.body
    if (!title) return res.json({ erro: "titulo não pode ser vazio" })
    if (!value) return res.json({ erro: "valor não pode ser vazio" })

    var contais = await Service.findOne({ where: { title: title } })
    if (contais) return res.json({ erro: "serviço ja existe" })
    var service
    try {
      service = await Service.create({
        title,
        value,
        status,
        description
      })
      return res.status(200).json(service)
    } catch (error) {
      return res.status(400).json(error)
    }

  },

  async getServices(req, res) {

    await Service.findAll().then((response) => {
      return res.status(200).json(response)
    }).cath((error) => {
      return res.status(400).json(error)
    })

  },

  async getService(req, res) {
    await Service.findOne({ where: { id: req.body.id } }).then((response) => {
      if (response == null) return res.status(200).json({ message: "serviço não encontrado" })
      return res.status(200).json(response)
    }).cath((error) => {
      return res.status(400).json(error)
    })
  },

  async deleteService(req, res) {
    var response = await Service.destroy({ where: { id: req.body.id } }).then((response) => {
      return res.status(200).send({ message: "Serviço excluido com sucesso" })
    }).catch((error) => {
      return res.status(400).send({ error: "Erro ao excluir serviço" })
    })


  }
}