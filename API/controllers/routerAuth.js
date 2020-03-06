const express = require('express')
const UserController = require('./userController')
const ServicesController = require('./serviceController')
const ReservationController = require('./reservationController')
const authMiddleware = require('../middleware/auth')

const routes = express.Router()

routes.use(authMiddleware)
routes.get('/allUsers/', UserController.getAllUsers)
routes.get('/getUser/', UserController.getUser)

routes.get('/getAllReservations/', ReservationController.getAllReservations)
routes.get('/getUserReservations/', ReservationController.getUserReservations)
routes.post('/newReservation/', ReservationController.newReservation)
routes.get('/getBlockDates/', ReservationController.getBlockDates)
routes.post('/updateReservation/', ReservationController.updateReservation)

routes.post('/service',ServicesController.newService)




module.exports = routes