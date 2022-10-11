docker run --rm `
    --name tomcat `
    -p 8080:8080 `
    --network demo_network `
    -v "C:/Users/mari_/IdeaProjects/jakartaee-course/lesson24/target/lesson24-1.0-SNAPSHOT.war:/usr/local/tomcat/webapps/lesson24.war" `
    tomcat:9.0.53-jdk17-openjdk

docker run --rm `
    --name demo-postgres `
    --network demo_network `
    -e POSTGRES_USER=postgres `
    -e POSTGRES_PASSWORD=postgres `
    -p 15432:5432 `
    -v "C:/Users/mari_/IdeaProjects/jakartaee-course/lesson24/dev-env/postgres/init.sql:/docker-entrypoint-initdb.d/1-init.sql" `
    postgres:13.4-alpine
