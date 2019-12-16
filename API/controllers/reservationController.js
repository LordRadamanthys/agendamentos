const Reservations = require('../models/Reservations')
const User = require('../models/User')
const Sequelize = require('sequelize')


module.exports = {

    async getAllReservations(req, res) {
        try {
            var reservations = await Reservations.findAll({ order: [['id', 'DESC']], include: [User.scope('withoutPassword')] })
            res.status(200).send(reservations)
        } catch (error) {
            res.status(400).send({ erro: error.message })
        }
    },

    async getUserReservations(req, res) {
        try {
            var reservations = await Reservations.findAll({ where: { userId: req.userId } })
            res.status(200).send(reservations)
        } catch (error) {
            res.status(400).send({ error: error })
        }
    },

    async newReservation(req, res) {
        if (!req.body.hour) return res.status(400).send({ error: "horas não pode ser vazio" })
        if (!req.body.date) return res.status(400).send({ error: "data não pode ser vazio" })
        if (!req.body.description) return res.status(400).send({ error: "Description não pode ser vazio" })
        var isReseved = await Reservations.findOne({ where: { date: req.body.date, hour: req.body.hour, status: 'marcado' } })
        if (isReseved) return res.status(400).send({ error: "horario já reservado" })

        var status = req.body.status ? req.body.status : Reservations.rawAttributes.status.values[0]
        if (Reservations.rawAttributes.status.values[0] != status &&
            Reservations.rawAttributes.status.values[1] != status &&
            Reservations.rawAttributes.status.values[2] != status) return res.status(500).send({ error: "invalid status" })

        try {
            var reservation = await Reservations.create({

                userId: req.userId,
                hour: req.body.hour,
                date: req.body.date,
                description: req.body.description,
                status: status
            })
            return res.status(200).send(reservation)
        } catch (error) {
            res.send({ error: error.message })
        }
    },

    async updateReservation(req, res) {
        const {id,admin, hour, date, description, status} = req.body
        
        if(admin == false || !admin) return res.status(401).send({error:"você não tem permissão"})
        if(!id) return res.status(400).send({error:"id is required"})
        if(!hour) return res.status(400).send({error:"hour is required"})
        if(!date) return res.status(400).send({error:"date is required"})
        if(!description) return res.status(400).send({error:"description is required"})
        if(!status) return res.status(400).send({error:"status is required"})
        await Reservations.update({
            hour: hour,
            date: date,
            description: description,
            status: status
        }, {
            where: {
                userId: req.userId,
                id:req.body.id 
            }
        }).then((reservation)=>{
            res.status(200).send(reservation)
        }).catch((error)=>{
            res.status(400).send(error.message)
        })
        
    },

    async getBlockDates(req, res) {
        await Reservations.findAll({
            attributes: [[Sequelize.fn('DISTINCT', Sequelize.col('date')), 'date']]
        }).then((dates) => {
            return res.status(200).send(dates)
        }).catch((error) => {
            return res.status(400).send(error.message)
        })
    }
}