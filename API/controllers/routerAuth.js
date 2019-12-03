const express = require('express')
const UserController = require('./userController')
const authMiddleware = require('../middleware/auth')

const routes = express.Router()

routes.use(authMiddleware)
routes.get('/allUsers/', UserController.getAllUsers)




module.exports = routes