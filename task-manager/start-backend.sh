xterm -e java -jar -Dserver.port=8080 tm-backend/target/task-manager.jar &
xterm -e java -jar -Dserver.port=9090 tm-backend/target/task-manager.jar &
xterm -e java -jar -Dserver.port=7070 tm-backend/target/task-manager.jar