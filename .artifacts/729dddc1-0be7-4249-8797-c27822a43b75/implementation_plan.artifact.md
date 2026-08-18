# Implementação de Lista de Senhas e Edição

Este plano detalha a criação do modelo de dados, persistência em memória (DAO) e as atividades para visualização e edição de senhas no app PlainText.

## Proposta Técnica

Embora o projeto utilize Jetpack Compose, seguiremos as instruções para criar `ListActivity` e `EditActivity` como Activities separadas, utilizando `RecyclerView` conforme solicitado.

## Mudanças Propostas

### Modelo e Persistência

#### [NEW] [Password.kt](file:///home/user/AndroidStudioProjects/PlainText/app/src/main/java/com/example/plaintext/model/Password.kt)
Criação da classe de dados `Password`.
- Atributos: `id`, `name`, `login`, `password`, `notes`.

#### [NEW] [PasswordDAO.kt](file:///home/user/AndroidStudioProjects/PlainText/app/src/main/java/com/example/plaintext/dao/PasswordDAO.kt)
Implementação do DAO usando um `ArrayList` estático para armazenamento em memória.
- Métodos: `add`, `getAll`, `getById`, `update`, `delete`.

---

### Interface do Usuário (UI)

#### [NEW] [ListActivity.kt](file:///home/user/AndroidStudioProjects/PlainText/app/src/main/java/com/example/plaintext/ListActivity.kt)
Atividade para listar as senhas.
- Utilizará um `RecyclerView`.
- Botão para adicionar nova senha (abre `EditActivity`).
- Clique em item para ver/editar (abre `EditActivity`).

#### [NEW] [EditActivity.kt](file:///home/user/AndroidStudioProjects/PlainText/app/src/main/java/com/example/plaintext/EditActivity.kt)
Atividade para criar ou editar uma senha.
- Campos para Nome, Login, Senha e Notas.
- Lógica para salvar (novo ou atualização) e excluir.

#### [MODIFY] [AndroidManifest.xml](file:///home/user/AndroidStudioProjects/PlainText/app/src/main/AndroidManifest.xml)
Registro das novas atividades `ListActivity` e `EditActivity`.

#### [MODIFY] [MainActivity.kt](file:///home/user/AndroidStudioProjects/PlainText/app/src/main/java/com/example/plaintext/MainActivity.kt)
Atualização da navegação após o login para abrir a `ListActivity`.

## Plano de Verificação

### Testes Manuais
1. **Login**: Realizar login e verificar se redireciona para a lista.
2. **Adição**: Adicionar uma nova senha e verificar se aparece na lista.
3. **Edição**: Clicar em uma senha, alterar os dados e salvar.
4. **Exclusão**: Excluir uma senha e verificar se desaparece da lista.
