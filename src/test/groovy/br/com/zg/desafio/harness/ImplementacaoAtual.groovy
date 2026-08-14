package br.com.zg.desafio.harness

import br.com.zg.desafio.CalculadoraRepasse
import br.com.zg.desafio.impl.Referencia

/**
 * Resolve qual implementação a suíte vai exercitar.
 *
 * Neste repositório só existe a referência. Na avaliação, o mesmo mecanismo
 * troca a implementação sem que sua suíte precise mudar uma linha — por isso
 * você nunca deve instanciar uma calculadora diretamente nos seus testes.
 */
class ImplementacaoAtual {

    static final Map<String, Class<? extends CalculadoraRepasse>> REGISTRO = [
        referencia: Referencia,
    ].asImmutable()

    static CalculadoraRepasse novaInstancia() {
        String id = System.getProperty('impl', 'referencia')
        Class<? extends CalculadoraRepasse> tipo = REGISTRO[id]
        if (!tipo) throw new IllegalStateException("Implementação desconhecida: '${id}'. Válidas: ${REGISTRO.keySet()}")
        return tipo.getDeclaredConstructor().newInstance()
    }
}
