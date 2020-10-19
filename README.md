# chat-java
Simple chat service.

## Requisites

* Java 11
* [Maven's settings](http://maven.apache.org/settings.html) file: `~/.m2/settings.xml`

## Building 

To build the client and server applications:

```
mvn clean install
```

## Testing

To run the unit tests:

```
mvn clean test
```

## Usage
### Running server

```
java -jar server/target/chat-server-with-dependencies.jar
```

### Running client(s)

```
java -jar client/target/chat-client-with-dependencies.jar
```

#### Possible commands:

* `/help` to view all possible commmands.
* `/exit` to exit the chat.
* `/p <user> <message>` to send a private message to `<user>`;