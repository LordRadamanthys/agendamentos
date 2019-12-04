const Reservations = require('../models/Reservations')
const User = require('../models/User')

module.exports = {

    async getAllReservations(req, res) {
        try {
            var reservations = await Reservations.findAll({ order: [['id', 'DESC']] })
            res.status(200).status(200).send(reservations)
        } catch (error) {
            res.status(400).send({ erro: error })
        }
    },

    async getUserReservations(req, res) {
        try {
            var reservations = await Reservations.findAll({ where: { userId: req.userId } })
            res.send(reservations)
        } catch (error) {
            res.status(400).send(error.message)
        }
    },

    async newReservation(req,res){
        if(!req.body.hour) return res.status(400).send({error:"horas não pode ser vazio"})
        if(!req.body.date)  return res.status(400).send({error:"data não pode ser vazio"})
        if(!req.body.description) return res.status(400).send({error:"Description não pode ser vazio"})
        var isReseved = await Reservations.findOne({where:{date:req.body.date,hour:req.body.hour}})
        if(isReseved) return res.status(400).send({error:"horario já reservado"})
        try {
           var reservation = await Reservations.create({
              
                userId:req.userId,
                hour:req.body.hour,
                date:req.body.date,
                description:req.body.description
            })
           return res.send(reservation)
        } catch (error) {
            res.send(error.message)
        }
    }
}