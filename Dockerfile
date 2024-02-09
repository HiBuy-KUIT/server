# 베이스 이미지 + 별칭
FROM openjdk:17 AS builder
#gradle 복사
COPY hibuy-server/gradlew .
COPY hibuy-server/gradle gradle
#build.gradle 복사
COPY hibuy-server/build.gradle .
#settings.gradle 복사
COPY hibuy-server/settings.gradle .
#웹어플리케이션 소스 복사
COPY hibuy-server/src src
#gradlew 실행 권한 부여
RUN chmod +x ./gradlew
#gradlew를 통해 실행 가능한 jar파일 생성
RUN ./gradlew bootJar
#build이미지에서 build/libs/*.jar 파일을 app.jar로 복사
COPY --from=builder build/libs/*.jar app.jar
#jar 파일 실행
ENTRYPOINT ["java", "-jar", "/app.jar"]
#볼륨 지정
VOLUME /tmp