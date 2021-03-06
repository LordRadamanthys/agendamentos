const User = require('../models/User')
const Sequelize = require('sequelize')
const jwt = require('jsonwebtoken')
const bcryptjs = require('bcryptjs')
const authConfig = require('../config/hash')
const util = require('../functions/Utils')
const firebase = require('../config/firebase/sendFireBaseMessage')


module.exports = {

    async getAllUsers(req, res) {

        try {
            var user = await User.scope('withoutPassword').findAll({ order: [['id', 'DESC']] })

            return res.json(user)
        } catch (error) {
            return res.status(500).send({ error: error.message })
        }
    },

    async getUser(req, res) {
        if (!req.params.id) return res.status(400).send({ error: 'nome não pode ser vazio' })
        try {
            var user = await User.scope('withoutPassword').findOne({ where: { id: req.params.id } })
            return res.json(user)
        } catch (error) {
            return res.send(error)
        }
    },

    async newUser(req, res) {
        if (!req.body.name) return res.status(400).send({ error: 'nome não pode ser vazio' })
        if (!req.body.email) return res.status(400).send({ error: "email obrigatorio" })
        if (!req.body.password) return res.status(400).send({ error: "senha obrigatorio" })
        if (!req.body.phone) return res.status(400).send({ error: "numero de telefone obrigatorio" })
        if (!req.body.device) return res.status(400).send({ error: "id do device não pode ser vazio" })

        var aux = await User.findOne({ where: { email: req.body.email } })
        if (aux) return res.status(400).send({ error: 'Usuario ja existe' })

        try {
            var user = await User.create({
                id: 0,
                name: req.body.name,
                email: req.body.email,
                password: req.body.password,
                device: req.body.device,
                phone: req.body.phone,
                admin: req.body.admin ? req.body.admin : false,
            })

            var usersAdmin = await util.getAdms()
            firebase.sendMessage("Novo usuario cadastrado", user.name + " acabou de fazer cadastro", usersAdmin)
            const token = generateToken({ id: user.id })
            const newUser = await util.makeNewUserJson(user, token)
            return res.send(newUser)

        } catch (error) {
            return res.status(400).send({ error: error.message })
        }
    },


    async updateUser(req, res) {
        if (!req.body.name) return res.status(400).send({ error: 'nome não pode ser vazio' })
        if (!req.body.email) return res.status(400).send({ error: "email obrigatorio" })
        if (!req.body.password) return res.status(400).send({ error: "senha obrigatorio" })
        if (!req.body.phone) return res.status(400).send({ error: "numero de telefone obrigatorio" })
        if (!req.body.device) return res.status(400).send({ error: "id do device não pode ser vazio" })

        // var aux = await User.findOne({ where: { email: req.body.email } })
        // if (aux) return res.status(400).send({ error: 'Usuario ja existe' })
        var id = req.body.id ? req.body.id : req.userId
        try {
            var user = await User.update({
                name: req.body.name,
                email: req.body.email,
                password: req.body.password,
                device: req.body.device,
                phone: req.body.phone,
                admin: req.body.admin ? req.body.admin : false,
            }, {
                where: {
                    id: id
                }
            })

            // var usersAdmin = await util.getAdms()
            // firebase.sendMessage("Novo usuario cadastrado", user.name + " acabou de fazer cadastro", usersAdmin)
            // const token = generateToken({ id: user.id })
            // const newUser = await util.makeNewUserJson(user, token)
            return res.status(200).json("Atualizado")

        } catch (error) {
            return res.status(400).send({ error: error.message })
        }
    },

    async pushNotification(req, res) {
        const { title, message } = req.body
        const response = await firebase.sendMessage(title, message)
        if (response.failureCount > 0) return res.send({ error: "erro ao mandar mensagem" })
        return res.json(response)
    },

    async authenticate(req, res) {
        console.log(req.body.email)
        validateEmailAndPassword(req, res)
        try {
            var user = await User.findOne({ where: { email: req.body.email } })
            if (!user) return res.status(400).json({ erro: "usuario não existe" })
            if (!await bcryptjs.compare(req.body.password, user.password)) {
                return res.status(400).send({ error: 'invalid password' })
            }
            const token = generateToken({ id: user.id })
            user.password = null
            const newUser = await util.makeNewUserJson(user, token)
            return res.send(newUser)
        } catch (error) {
            return res.send(newUser)
        }
    }
}

function generateToken(params = {}) {
    return jwt.sign(params, authConfig.token, {
        expiresIn: 86400,
    })
}

function validateEmailAndPassword(req, res) {
    if (!req.body.email) return res.status(400).send({ error: "email obrigatorio" })
    if (!req.body.password) return res.status(400).send({ error: "senha obrigatorio" })
}