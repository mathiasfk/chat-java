# chat-java
Simple chat service.

## Requisites

* Java 11
* Maven's settings file: `~/.m2/settings.xml`

## Building 

```
mvn clean install
```

## Running server

```
java -jar server/target/chat-server-with-dependencies.jar
```

## Running client(s)

```
java -jar client/target/chat-client-with-dependencies.jar
```

### Possible commands:

* `/exit` to exit the chat.