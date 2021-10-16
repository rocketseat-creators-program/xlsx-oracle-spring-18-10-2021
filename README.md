<img src="https://storage.googleapis.com/golden-wind/experts-club/capa-github.svg" />

## Pega essa visão: Oracle gerando xlsx e spring consumindo!

### Configuração do oracle
1.  Realize o login do docker:
```shell
    docker login
 ```
1. Vá no docker hub e aceite os termos do Oracle Database.
1. Após podera realizar o download da imagem utlizando o seguinte comando:
```shell
    docker pull store/oracle/database-enterprise:12.2.0.1 
```
1. Comando para rodar a imagem docker que realizamos o download, a senha padrão é `Oradoc_db1`.
```shell
  docker run -d -it --name epxert_club \
                    -p 1521:1521 -p 5500:5500 \
                    -e DB_SID=ORCLCDB \
                    -e DB_PDB=ORCLCDB1 \
                    -e DB_MEMORY=1GB \ 
                    -v C:\\home\\gustavo:/ORCL \ 
                    store/oracle/database-enterprise:12.2.0.1
```





