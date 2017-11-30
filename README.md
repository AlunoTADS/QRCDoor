# QRCDoor

Sistema de controle de acesso utilizando QR code. Mains informações ver site do [GEPTA - UFPR](http://gepta.weebly.com/qrc-door.html).

### Prerequisitos

Banco de dados PostgreSQL 9.5 com uma instância de um banco chamado 'QRCDoor' e um usuario 'QRCDoor', senha 'QRCDoor'
Os dados de acesso podem ser alterados no arquivo application.properties dentro do .jar

Java 1.8

### Instalação

Adicionar o arquivo target/qrcdoor-0.0.1-SNAPSHOT.jar em um diretório.

Executar o .jar pelo comando 

```
java -jar qrcdoor-0.0.1-SNAPSHOT.jar
```

## Compilação

Para compilar o software executar o comando
```
mvn package && java -jar qrcdoor-0.0.1-SNAPSHOT.jar
```

## License

Este projeto é licenciado sob GNU Licence - veja o arquivo [LICENSE.md](LICENSE.md) para mais detalhes

