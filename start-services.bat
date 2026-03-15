@echo off

echo Starting Eureka Server...
start cmd /k "cd eureka-server && mvn spring-boot:run"

timeout /t 10

echo Starting User Service...
start cmd /k "cd user-service && mvn spring-boot:run"

timeout /t 5

echo Starting Membership Service...
start cmd /k "cd membership-service && mvn spring-boot:run"

timeout /t 5

echo Starting Trainer Service...
start cmd /k "cd trainer-service && mvn spring-boot:run"

timeout /t 5

echo Starting Booking Service...
start cmd /k "cd booking-service && mvn spring-boot:run"

timeout /t 5

echo Starting Payment Service...
start cmd /k "cd payment-service && mvn spring-boot:run"

timeout /t 5

echo Starting Notification Service...
start cmd /k "cd notification-service && mvn spring-boot:run"

timeout /t 5

echo Starting API Gateway...
start cmd /k "cd api-gateway && mvn spring-boot:run"

echo All services started.
pause