const User = require('../models/User')
const db = require('../config/database')
const Reservations = require('../models/Reservations')
const Shopping = require('../models/Shopping')
const Sequelize = require('sequelize')
const util = require('../functions/Utils')
const firebase = require('../config/firebase/sendFireBaseMessage')



module.exports = {

    async getAllReservations(req, res) {
        try {
            var reservations = await Reservations.findAll({
                order: [
                    ['id', 'DESC']
                ], include: [User.scope('withoutPassword')]
            })

            if (Object.keys(reservations).length < 1) return res.status(200).send({ erro: "lista vazia" })

            return res.status(200).send(reservations)
        } catch (error) {
            return res.status(400).send({ erro: error.message })
        }
    },

    async getUserReservations(req, res) {
        var id = !req.headers.id || req.headers.id < 0 ? req.userId : req.headers.id
        try {
            var reservations = await Reservations.findAll({ where: { userId: id } })
            var reservationsAndServices = []
            for (var i = 0; i < Object.keys(reservations).length; i++) {
                var newList = {
                    id: reservations[i].id,
                    hour: reservations[i].hour,
                    date: reservations[i].date,
                    fullPrice: reservations[i].fullPrice,
                    status: reservations[i].status,
                    description: reservations[i].description,
                    services: await db.sequelize.query("SELECT s.id, s.title FROM db_agenda.reservations r inner join db_agenda.shopping sh inner join db_agenda.services s on sh.reservationId = r.id and sh.serviceId=s.id  where r.userId=" + id + " and r.id = " + reservations[i].id, { type: db.sequelize.QueryTypes.SELECT })

                }
                reservationsAndServices.push(newList)
            }

            return res.status(200).json(reservationsAndServices)
        } catch (error) {
            return res.status(400).send({ error: error.message })
        }
    },

    async newReservation(req, res) {
        var user = await User.scope('withoutPassword').findOne({ where: { id: req.userId } })

        if (!req.body.hour) return res.status(400).send({ error: "horas não pode ser vazio" })
        if (!req.body.date) return res.status(400).send({ error: "data não pode ser vazio" })
        if (!req.body.services) return res.status(400).send({ error: "serviços não pode ser vazio" })
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
                fullPrice: req.body.fullPrice,
                description: req.body.description,
                status: status
            })
            var list = req.body.services
            try {
                for (i in list) {
                    var item = list[i];
                    console.log(item.id)
                    await Shopping.create({
                        serviceId: item.id,
                        reservationId: reservation.id
                    })
                }
            } catch (error) {
                console.log(error)
            }

            var usersAdmin = await util.getAdms()
            //console.log(usersAdmin)
            await firebase.sendMessage("Nova reserva", user.name + " acabou de fazer um agendamento", usersAdmin)
            return res.status(200).json(reservation)

        } catch (error) {
            return res.send({ error: error.message })
        }
    },

    async updateReservation(req, res) {
        var user = await User.scope('withoutPassword').findOne({ where: { id: req.userId } })
        const { id, hour, date, description, status } = req.body

        if (!id) return res.status(400).send({ error: "id is required" })
        if (!hour) return res.status(400).send({ error: "hour is required" })
        if (!date) return res.status(400).send({ error: "date is required" })
        if (!description) return res.status(400).send({ error: "description is required" })
        if (!status) return res.status(400).send({ error: "status is required" })
        var usersAdmin = await util.getAdms()
        await Reservations.update({
            hour: hour,
            date: date,
            description: description,
            status: status
        }, {
            where: {
                userId: req.userId,
                id: req.body.id
            }
        }).then((reservation) => {

            firebase.sendMessage("Alteração de reserva", user.name + " acabou de alterar a reserva", usersAdmin)
            return res.status(200).send(reservation)
        }).catch((error) => {
            return res.status(400).send(error.message)
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
