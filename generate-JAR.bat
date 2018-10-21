call mvn clean compile
robocopy "src/main/java/images" "target/classes/images" /s /e
robocopy "src/main/java/view" "target/classes/view" "embed.fxml"
robocopy "src/main/java/view" "target/classes/view" "frame.fxml"
robocopy "src/main/java/view" "target/classes/view" "menu.fxml"
robocopy "src/main/java/view" "target/classes/view" "sendPanel.fxml"
call mvn assembly:single
pause