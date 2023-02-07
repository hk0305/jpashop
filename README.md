# H2 데이터베이스 설치
* 개발이나 테스트 용도로 가볍고 편리한 DB, 웹 화면 제공

1. 윈도우 설치 버전: https://h2database.com/h2-setup-2019-10-14.exe 
2. 윈도우, 맥 , 리눅스 실행 버전: https://h2database.com/h2-2019-10-14.zip
3. https://www.h2database.com

* 다운로드 및 설치 데이터베이스 파일 생성 방법
> chmod 755 ./h2.sh
> 
> jdbc:h2:~/jpashop 최초 한 번 실행
> 
> ~/jpashop.mv.db 파일 생성 확인
> 
> 이후 부터는 jdbc:h2:tcp://localhost/~/jpashop 이렇게 접속

# build 후 jar 실행
> ./gradlew clean build
>
> cd ./build/lib
>
> java -jar jpashop-0.0.1-SNAPSHOT.jar


