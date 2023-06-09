const express = require('express')
const router = express.Router()

const AcaoController = require('./controllers/AcaoController')

router.get('/acoes', AcaoController.recuperacaoDeTodos)
router.get('/acao/:id', AcaoController.recuperacaoDeUm)
router.post('/acao', AcaoController.inclusao)
router.put('/acao/:id', AcaoController.atualizacao)
router.delete('/acao/:id', AcaoController.remocao)

module.exports = router