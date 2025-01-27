# Java Web Application with CI/CD

A simple Java web application demonstrating CI/CD pipeline using Jenkins.

## Project Structure

```
java-web-app/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── servlet/
│   │   │               └── HelloServlet.java
│   │   └── webapp/
│   │       ├── css/
│   │       │   └── style.css
│   │       ├── WEB-INF/
│   │       │   ├── views/
│   │       │   │   └── hello.jsp
│   │       │   └── web.xml
│   │       └── index.html
│   └── test/
├── .gitignore
├── Jenkinsfile
├── pom.xml
└── README.md
```

## Prerequisites

- JDK 11
- Maven 3.9.6
- Jenkins with following plugins:
  - Maven Integration
  - Pipeline
  - Git
  - JUnit
  - SonarQube Scanner
  - Deploy to Container

## Local Development

1. Build the project:
```bash
mvn clean package
```

2. Run tests:
```bash
mvn test
```

3. Deploy WAR file:
The WAR file will be generated in `target/simple-java-web-app.war`

## Jenkins Pipeline

The included Jenkinsfile defines a pipeline with the following stages:

1. **Checkout**: Retrieves code from Git repository
2. **Build**: Compiles and packages the application
3. **Test**: Runs unit tests and publishes results
4. **SonarQube Analysis**: Performs code quality analysis
5. **Deploy to Tomcat**: Deploys the WAR file to Tomcat server

## Jenkins Setup

1. Create new Pipeline job in Jenkins
2. Configure Git repository
3. Set JDK and Maven tools in Jenkins Global Tool Configuration:
   - JDK 11
   - Maven 3.9.6
4. Configure Tomcat credentials in Jenkins
5. Configure SonarQube server in Jenkins

## Application Features

- Simple web interface with modern styling
- Form to input user's name
- Displays personalized greeting and current time
- Responsive design

## Contributing

1. Create feature branch:
```bash
git checkout -b feature/your-feature
```

2. Commit changes:
```bash
git commit -am "Add your feature"
```

3. Push branch:
```bash
git push origin feature/your-feature
```

4. Create Pull Request

## License

MIT License
