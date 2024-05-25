# Acesso a Banco de Dados com SQLite e Java
Este é um resumo sobre como acessar um banco de dados utilizando SQLite como Sistema Gerenciador de Banco de Dados (SGBD) na linguagem Java.

# SQLite
SQLite é um sistema de banco de dados que possui uma biblioteca C que fornece um banco de dados baseado em disco que não requer um processo de servidor separado. Ele permite o acesso ao banco de dados usando um subconjunto não padrão do SQL. Como parte do projeto Android, o SQLite é usado como gerenciador de banco de dados para armazenamento local.

# JDBC
Java Database Connectivity (JDBC) é uma API Java que define como um cliente pode acessar um banco de dados. Ele fornece métodos para consultar e atualizar dados em um banco de dados.

# Conexão com o Banco de Dados
Para conectar-se ao banco de dados SQLite com Java, você precisa ter o driver JDBC SQLite em seu classpath. Você pode estabelecer uma conexão usando o método DriverManager.getConnection().
````
Connection connection = DriverManager.getConnection("jdbc:sqlite:meubanco.db");


````
# Executando Consultas
Você pode executar consultas SQL no banco de dados usando objetos Statement e PreparedStatement. Aqui está um exemplo de como criar uma tabela:
````
Statement statement = connection.createStatement();
statement.execute("CREATE TABLE IF NOT EXISTS funcionarios (id INTEGER PRIMARY KEY, nome TEXT, cpf TEXT)");

````
# Inserindo Dados
Você pode inserir dados no banco de dados usando um objeto PreparedStatement. Isso permite que você insira valores dinâmicos na consulta.
````
PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO funcionarios (nome, cpf) VALUES (?, ?)");
preparedStatement.setString(1, "Jonas");
preparedStatement.setString(2, "22.233.556-11");
preparedStatement.executeUpdate();

````
# Consultando Dados
Você pode consultar dados do banco de dados usando um objeto Statement.

````
ResultSet resultSet = statement.executeQuery("SELECT * FROM funcionarios");
while (resultSet.next()) {
    System.out.println(resultSet.getInt("id") +  ", " + resultSet.getString("nome") + ", " + resultSet.getString("cpf"));
}

````
# Fechando a Conexão
Finalmente, lembre-se de sempre fechar a conexão quando terminar de usá-la para liberar recursos.
````
connection.close();

````
