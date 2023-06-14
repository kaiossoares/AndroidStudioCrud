const AcaoService = require('../services/AcaoService')

module.exports = {
    recuperacaoDeTodos: async (req, res) => {
        let json = { error: '', result: [] }

        let acoes = await AcaoService.recuperacaoDeTodos()

        for (let i in acoes) {
            json.result.push({
                id: acoes[i].id,
                nome: acoes[i].nome,
                descricao: acoes[i].descricao,
                tipo: acoes[i].tipo,
            })
        }
        res.json(json)
    },

    recuperacaoDeUm: async (req, res) => {
        let json = { error: '', result: {} }
        let id = req.params.id
        let acao = await AcaoService.recuperacaoDeUm(id)

        if (acao) {
            json.result = acao
        }

        res.json(json)
    }
}