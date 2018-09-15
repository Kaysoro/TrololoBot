call mvn clean compile
robocopy "src/main/java/images" "target/classes/images" /s /e
call mvn assembly:single
pause