
## gRPC exmaple with Kotlin

#### build
```
mvn clean package
```

#### Launch server
```bash
java -jar grpc-demo-server/target/grpc-demo-server-0.0.1-SNAPSHOT.jar
```

#### Launch client
```bash
java -jar grpc-demo-client/target/grpc-demo-client-0.0.1-SNAPSHOT.jar
```

#### Call client
```
http://localhost:8081/call
```
