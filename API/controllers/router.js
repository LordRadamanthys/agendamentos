const express = require('express')
const UserController = require('./userController')
const authMiddleware = require('../middleware/auth')
const path = require('path')
const fs= require('fs')

const routes = express.Router()

routes.post('/user/create',UserController.newUser)
routes.post('/pushNotification/',UserController.pushNotification)
routes.post('/authenticate/',UserController.authenticate)



module.exports = routes