docker run --rm `
    --name tomcat `
    -p 8080:8080 `
    --network demo_network `
    -v "C:/Users/mari_/IdeaProjects/jakartaee-course/lesson22/target/lesson22-1.0-SNAPSHOT.war:/usr/local/tomcat/webapps/lesson22.war" `
    tomcat:9.0.53-jdk17-openjdk

docker run --network demo_network --rm curlimages/curl:7.85.0 -X POST http://tomcat:8080/lesson22/counter
