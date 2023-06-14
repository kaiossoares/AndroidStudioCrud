const AcaoService = require('../services/AcaoService')

module.exports = {
    recuperacaoDeTodos: async (req, res)=>{
        let json = {error:'', result:[]}

        let acoes = await AcaoService.recuperacaoDeTodos()

        for(let i in acoes){
            json.result.push({
                id: acoes[i].id,
                nome: acoes[i].nome,
                descricao: acoes[i].descricao,
                tipo: acoes[i].tipo,
            })
        }
        res.json(json)
    }
}