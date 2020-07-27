# CRUD Operations for File System
Springboot based Java application for doing CRUD operations on a file system in a directory.

## Installation

Run this command to create jar file

```bash
mvn clean install
```

Run this command to run the jar file created in the target folder

```bash
java -jar target/classname.jar
```

##Usage

Use postman or any other software the supports REST APi services .

####BasePath= localhost:8080/file

#####GET : localhost:8080/file
To get all the files.\
Produces Json output.\
Sample Output Response:
```
[
    {
       "id":"1",
        "fileName":"file1.txt",
        "fileContent":"This is file 1"
        "filePath":"C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file1.txt"
    },
    {    
        "id":"2",
        "fileName":"file2.txt",
        "fileContent":"This is file 2"
        "filePath":"C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file2.txt"
    }
]
```

#####GET BY FILE NAME : localhost:8080/file/{fileName}
To create a file.\

Sample Query: localhost:8080/file/file1.txt\
Output:File Content.\
Sample Output Response:
```
This is file 1
```

#####POST : localhost:8080/file
To create a file.\
Input:fileName (required),fileContent 
Sample Input Json:
```
    {
        "fileName":"file1.txt",
        "fileContent":"This is file 1"
    }
```

Produces Json output.\
Sample Output Response:
```
    {
       "id":"1",
        "fileName":"file1.txt",
        "fileContent":"This is file 1"
        "filePath":"C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file1.txt"
    }
```

#####PUT : localhost:8080/file/{fileName}
To update the file content.\
Input:fileName (required),fileContent 
Sample Input Json:
```
    {
        "fileName":"file1.txt",
        "fileContent":"This is file 1 updated"
    }
```
Produces Json output.\
Sample Output Response:
```
    {
       "id":"1",
        "fileName":"file1.txt",
        "fileContent":"This is file 1 updated"
        "filePath":"C:\\Users\\Abhinav\\Downloads\\assignment-java-group-five\\assignment-java-group-five\\file1.txt"
    }
```

#####DELETE : localhost:8080/file/{fileName}
Delete the file.
Output: Status Code\
Sample Query: localhost:8080/file/file1.txt
```
FORBIDDEN
```


