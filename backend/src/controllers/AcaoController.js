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
    },

    inclusao: async (req, res) => {
        let json = { error: '', result: {} }
        let nome = req.body.nome
        let descricao = req.body.descricao
        let tipo = req.body.tipo

        if (nome && descricao && tipo) {
            let AcaoId = await AcaoService.inclusao(nome, descricao, tipo)
            json.result = {
                id: AcaoId,
                nome,
                descricao,
                tipo
            }
        }else{
            json.error = 'Campos não enviados'
        }

        res.json(json)
    }
}