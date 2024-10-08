# Histórias de Usuário

## US1 - Cadastro de Professor

*Como* professor,  
*Quero* estar cadastrado no sistema,  
*Para* distribuir moedas aos alunos.  

**Critérios de aceitação:**
- O sistema deve receber a lista da instituição de ensino para cadastro de professores contendo nome, CPF e departamento vinculado.
- Cada professor deve receber um saldo inicial de 1.000 moedas acumulável semestralmente.
- O sistema deve verificar se o professor já está cadastrado, baseado no CPF e no departamento.

## US2 - Consulta de Extrato de Aluno

*Como* aluno,  
*Quero* consultar meu extrato,  
*Para* verificar quantas moedas tenho e as transações realizadas.  

**Critérios de aceitação:**
- O aluno deve poder visualizar o saldo total de moedas.
- O extrato deve listar todas as transações, incluindo recebimentos e trocas de moedas.
- O aluno deve ser notificado por email sempre que receber moedas.
- O sistema deve permitir o filtro por data e tipo de transação (recebimento ou troca).

## US3 - Notificação de Resgate para Empresa Parceira

*Como* empresa parceira,  
*Quero* receber notificações por email quando um aluno resgatar uma vantagem,  
*Para* acompanhar a utilização das minhas ofertas.  

**Critérios de aceitação:**
- A empresa deve ser notificada por email com um código de conferência quando um aluno resgatar uma vantagem.
- O email de notificação deve incluir detalhes do aluno, da vantagem resgatada, e um código gerado pelo sistema.
- O sistema deve garantir que apenas empresas cadastradas recebam notificações de resgate.
- As vantagens devem incluir descrição e foto do produto ou serviço ofertado.
