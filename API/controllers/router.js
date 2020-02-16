const express = require('express')
const UserController = require('./userController')
const authMiddleware = require('../middleware/auth')

const routes = express.Router()

routes.post('/cadUser/',UserController.newUser)
routes.post('/pushNotification/',UserController.pushNotification)
routes.post('/authenticate/',UserController.authenticate)

module.exports = routes