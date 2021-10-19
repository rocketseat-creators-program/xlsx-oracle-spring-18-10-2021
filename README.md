<img src="https://storage.googleapis.com/golden-wind/experts-club/capa-github.svg" />

## Gerando arquivo xlsx com Oracle e consumindo com Spring

Utilizadno o banco de dados Oracle para gerar uma planilha excel através de uma função específica, liberando o back-end para continuar com o processamento necessário reduzindo assim o gargalo da operação. 

### Ambiente Utilizado
* Java JDK 16
* Spring 2.4.11
* Oracle 12c
* Docker
* IntelliJ (IDE)

### Configuração do oracle
* Realize o login do docker:
```shell
    docker login
 ```
* Vá no docker hub e aceite os termos do Oracle Database.
* Após podera realizar o download da imagem utlizando o seguinte comando:
```shell
    docker pull store/oracle/database-enterprise:12.2.0.1 
```
* Comando para rodar a imagem docker que realizamos o download, a senha padrão é `Oradoc_db1`.
> Outras configurações pode informar o *DB_SID*, *DB_PDB* e *DB_MEMORY* 
```shell
  docker run -d -it --name epxert_club -p 1521:1521 -p 5500:5500 -v C:\\projects\\oracle:/ORCL store/oracle/database-enterprise:12.2.0.1
```
* Para executar o projeto precisaremos dos seguintes scripts:
  * [https://github.com/ogobrecht/sample-data-sets-for-oracle/tree/master/customer_orders](https://github.com/ogobrecht/sample-data-sets-for-oracle/tree/master/customer_orders)
  * [https://github.com/mortenbra/alexandria-plsql-utils](https://github.com/mortenbra/alexandria-plsql-utils)

* Reduzir o consumo de memória do docker, para isso crie o arquivo `.wslconfig`, dentro do diretório do seu usuário, com o seguinte conteúdo:

```shell
# Enable extra metadata options by default
[automount]
enabled = true
root = /windir/
options = "metadata,umask=22,fmask=11"
mountFsTab = false

# Enable DNS – even though these are turned on by default, we'll specify here just to be explicit.
[network]
generateHosts = true
generateResolvConf = true

[wsl2]
memory=4GB # Limits VM memory in WSL 2 to 4 GB
processors=2 # Makes the WSL 2 VM use two virtual processors
```

### Diretório do Projeto

```sh
.
├── script/       # script que contém o SQL necessário para rodar o projeto
├── src/          # armazena código para consumo do spring
├── .gitignore    # arquivos que são desconsiderados pelo git
├── pom.xml       # dependências do projeto
└── README.md
```


## Expert

| [<img src="https://avatars.githubusercontent.com/u/1785791?s=400&u=cf86c9ae2216765f948ca2136eda7e632e0cd922&v=4" width="75px;"/>](https://github.com/gustavodsf) |
| :-: |
|[Gustavo Figueiredo](https://github.com/gustavodsf)|
