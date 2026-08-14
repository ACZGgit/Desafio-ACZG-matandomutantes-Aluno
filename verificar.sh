#!/usr/bin/env bash
# Sanity check antes de entregar: sua suíte precisa estar 100% verde
# contra a implementação de referência.
set -uo pipefail

GRADLE="./gradlew"
[[ -x "$GRADLE" ]] || GRADLE="gradle"

echo "Rodando sua suíte contra a implementação de referência..."
echo

if $GRADLE test -Dimpl=referencia --console=plain --rerun-tasks; then
  cat <<'MSG'

  ENTREGA VÁLIDA.
  Sua suíte está verde contra a referência.

  Antes de abrir o PR, três perguntas:
    1. Cada teste tem um nome que descreve o COMPORTAMENTO esperado?
    2. Existe algum `then:` sem asserção de verdade?
    3. Dá para matar o mesmo tanto com menos testes?

MSG
else
  cat <<'MSG'

  ENTREGA INVÁLIDA.
  Pelo menos um teste falhou contra a implementação de referência.

  Um teste que acusa código correto é pior que teste nenhum: ele treina
  o time a ignorar a suíte. Corrija antes de entregar.

MSG
  exit 1
fi
