# 베이스 이미지 + 별칭
FROM openjdk:17-jdk AS build
RUN microdnf install findutils
#gradle 복사
COPY ./gradlew .
COPY ./gradle gradle
#build.gradle 복사
COPY ./build.gradle .
#settings.gradle 복사
COPY ./settings.gradle .
#웹어플리케이션 소스 복사
COPY ./src src
#gradlew 실행 권한 부여
RUN chmod +x gradlew
#gradlew를 통해 실행 가능한 jar파일 생성
RUN ./gradlew bootJar
#build이미지에서 build/libs/*.jar 파일을 app.jar로 복사
FROM openjdk:17-jdk
COPY --from=build build/libs/*.jar app.jar
#jar 파일 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
#볼륨 지정
VOLUME /tmp