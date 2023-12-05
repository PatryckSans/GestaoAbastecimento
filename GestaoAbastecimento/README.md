### Diagrama de Caso de Uso - UC:

![Diagrama de Caso de Uso](link_para_imagem_do_diagrama_de_caso_de_uso)

**Descrição dos Casos de Uso:**
1. **Cadastrar Veículo:**
   - **Ator Principal:** Usuário
   - **Descrição:** Permite que um usuário cadastre um novo veículo no sistema.
   - **Fluxo Básico:** O usuário fornece informações sobre o veículo (placa, marca, modelo) e confirma o cadastro.

2. **Cadastrar Posto:**
   - **Ator Principal:** Usuário
   - **Descrição:** Permite que um usuário cadastre um novo posto de combustível no sistema.
   - **Fluxo Básico:** O usuário fornece informações sobre o posto (nome, localização) e confirma o cadastro.

3. **Cadastrar Abastecimento:**
   - **Ator Principal:** Usuário
   - **Descrição:** Permite que um usuário registre um abastecimento associado a um veículo e a um posto.
   - **Fluxo Básico:** O usuário seleciona o veículo, o posto, fornece informações sobre o abastecimento (data, litros, quilômetros, preço, tipo de combustível) e confirma o registro.

### Diagrama de Classes:

![Diagrama de Classes](link_para_imagem_do_diagrama_de_classes)

**Classes:**
- `Veiculo`
- `Posto`
- `Abastecimento`
- `VeiculoRepository`
- `PostoRepository`
- `AbastecimentoRepository`
- `VeiculoService`
- `PostoService`
- `AbastecimentoService`
- `VeiculoController`
- `PostoController`
- `AbastecimentoController`
- `MongoDBConnection`

### Diagrama de Sequência:

![Diagrama de Sequência](link_para_imagem_do_diagrama_de_sequencia)

**Exemplo:**
1. **Cadastrar Veículo:**
   - `VeiculoController` chama `VeiculoService` para cadastrar veículo.
   - `VeiculoService` chama `VeiculoRepository` para salvar veículo no banco de dados.

### Modelo de Dados:

![Modelo de Dados](link_para_imagem_do_modelo_de_dados)

**Tabelas:**
- `veiculos`
- `postos`
- `abastecimentos`

### Solução de Software (Lista dos Requisitos Implementados):

1. Cadastro de Veículos, Postos e Abastecimentos.
2. Listagem de Veículos, Postos e Abastecimentos.
3. Cálculo de médias de quilômetros por litro e preço por placa.

### Cenário (Ex. Cliente Precisa Automatizar o Controle):

O cliente, uma empresa de transporte, precisa automatizar o controle de abastecimento de sua frota de veículos. A solução de software permitirá um registro eficiente dos abastecimentos, facilitando a análise de consumo, custos e manutenção da frota.

### Conclusão:

O software desenvolvido oferece uma solução eficaz para o controle de abastecimento de veículos, seguindo boas práticas de Desenvolvimento Orientado a Objetos. A implementação permite ao cliente gerenciar sua frota de forma mais eficiente, garantindo maior controle e acesso rápido a informações essenciais para a gestão de combustíveis. Possíveis melhorias futuras incluem integração com sistemas de telemetria para coleta automática de dados. Agradeço a todos os envolvidos no projeto por sua colaboração e dedicação.
