const express = require('express')
const UserController = require('./userController')
const ServicesController = require('./serviceController')
const ReservationController = require('./reservationController')
const ShoppingController = require('./shoppingController')
const authMiddleware = require('../middleware/auth')
const multerConfig = require('../middleware/multer')
const multer = require('multer')
const path = require('path')
const fs= require('fs')

const routes = express.Router()

routes.use(authMiddleware)
routes.get('/allUsers/', UserController.getAllUsers)
routes.get('/getUser/', UserController.getUser)

routes.get('/getAllReservations/', ReservationController.getAllReservations)
routes.get('/getUserReservations/', ReservationController.getUserReservations)
routes.post('/newReservation/', ReservationController.newReservation)
routes.get('/getBlockDates/', ReservationController.getBlockDates)
routes.post('/updateReservation/', ReservationController.updateReservation)

routes.post('/service', ServicesController.newService)//criar serviço
routes.get('/services', ServicesController.getServices)//pegar todos os serviços
routes.get('/service/:id', ServicesController.getService)//pegar um serviço
routes.delete('/service', ServicesController.deleteService)//apagar serviço
routes.put('/service', ServicesController.updateService)//editar serviço

routes.get('/shoppings', ShoppingController.getShoppings)
routes.get('/shoppingReservation', ShoppingController.getShoppingReservation)

routes.post("/uploads", multer(multerConfig).single("file"), (req, res) => {
    console.log(req.file)
    console.log(req.body.nome)
    return res.json("foi")
})

routes.get("/uploads/:id", (req, res) => {
    const types = [
        'jpeg',
        'pjpeg',
        'jpg',
        'png',
        'gif',
    ]

    var teste
    types.map(value => {
        if (fs.existsSync(path.resolve(__dirname, '..', `uploads/${req.params.id}.${value}`))) {
            teste= path.resolve(__dirname, '..', `uploads/${req.params.id}.${value}`)
        }
        // var filePath = path.resolve(__dirname, '..', `uploads/${req.params.id}.${value}`)
        // if (filePath) return filePath
    })
    console.log(teste)
    res.sendFile(teste)
    // var filePath = path.resolve(__dirname, '..', `uploads/${req.param.id}`)
    // return res.sendFile(`${filePath}${req.param.id}`)
})


module.exports = routes