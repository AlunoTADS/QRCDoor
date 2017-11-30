# QRCDoor

Sistema de controle de acesso utilizando QR code

### Prerequisitos

Banco de dados PostgreSQL 9.5 com uma instância de um banco chamado 'QRCDoor' e um usuario 'QRCDoor', senha 'QRCDoor'
Java 1.8

### Instalação

Adicionar o arquivo dist/qrc-door.jar em um diretório.

Executar o .jar pelo comando 

```
java -jar qrc-door.jar
```

## Compilação

Para compilar o software executar o comando
```
mvn package && java -jar qrc-door.jar
```

## License

Este projeto é licenciado sob GNU Licence - veja o arquivo [LICENSE.md](LICENSE.md) para mais detalhes

