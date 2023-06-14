const express = require('express')
const router = express.Router()

const AcaoController = require('./controllers/AcaoController')

router.get('/acoes', AcaoController.recuperacaoDeTodos)
router.get('/acoes/:id', AcaoController.recuperacaoDeUm)

module.exports = router