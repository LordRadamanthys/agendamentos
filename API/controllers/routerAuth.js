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
routes.get('/users/', UserController.getAllUsers)
routes.get('/user/', UserController.getUser)
routes.put('/user/', UserController.updateUser)

routes.get('/reservations/', ReservationController.getAllReservations)
routes.get('/reservation/', ReservationController.getUserReservations)
routes.post('/reservation/', ReservationController.newReservation)
routes.get('/getBlockDates/', ReservationController.getBlockDates)
routes.put('/reservation/', ReservationController.updateReservation)

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
    console.log(req.params.id)

    var filePath
    try {
        types.map(value => {
            if (fs.existsSync(path.resolve(__dirname, '..', `uploads/${req.params.id}.${value}`))) {
                filePath= path.resolve(__dirname, '..', `uploads/${req.params.id}.${value}`)
            }
            // var filePath = path.resolve(__dirname, '..', `uploads/${req.params.id}.${value}`)
            // if (filePath) return filePath
        })
        console.log(req.params.id)
        console.log(filePath)
        res.sendFile(filePath)
    } catch (error) {
        console.log(req.params.id)
        res.send(error.message)
    }
    
    // var filePath = path.resolve(__dirname, '..', `uploads/${req.param.id}`)
    // return res.sendFile(`${filePath}${req.param.id}`)
})


module.exports = routes