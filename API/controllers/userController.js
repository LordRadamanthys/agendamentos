const express = require('express')
const User = require('../models/User')
const jwt = require('jsonwebtoken')
const bcryptjs = require('bcryptjs')
const authConfig = require('../config/hash')


module.exports = {

    async getAllUsers(req, res) {
        try {
            User.findAll({ order: [['id', 'DESC']] }).then(function (users) {
                console.log(users)
                console.log("teste")
                res.send(users)
            })
        } catch (error) {
            res.send(error)
            res.send("error")
        }

        return res.json(user)
    },

    async newUser(req, res) {
        if (!req.body.name) res.status(400).send('nome não pode ser vazio')
        validateEmailAndPassword(req,res)
        var aux = await User.findOne({ where: { email: req.body.email } })
        if (aux) return res.status(400).send('Usuario ja existe')
 
        try {
            var user = await User.create({
                id: 0,
                name: req.body.name,
                email: req.body.email,
                password: req.body.password,
                admin: false,
            })

            const token = generateToken({ id: user.id })
            //console.log(token)
            res.send({ user, token })

        } catch (error) {
            res.status(400).send("error")
            console.log(error)
        }
    },

    async authenticate(req, res) {

        validateEmailAndPassword(req,res)
        try {
            var user = await User.findOne({ where: { email: req.body.email } })
            if (!user) return res.status(400).send("usuario não existe")
            if (!await bcryptjs.compare(req.body.password, user.password)) {
                console.log("error")
                return res.status(400).send({ 'error': 'invalid password' })
            }
            const token = generateToken({ id: user.id })
            res.send({ user, token })
        } catch (error) {
            res.send(error)
            res.send("error")
        }

        return res.json(user)
    }
}

function generateToken(params = {}) {
    return jwt.sign(params, authConfig.token, {
        expiresIn: 86400,
    })
}

function validateEmailAndPassword(req,res){
    if (!req.body.email) return res.status(400).send("email obrigatorio")
    if (!req.body.password) return res.status(400).send("senha obrigatorio")
}