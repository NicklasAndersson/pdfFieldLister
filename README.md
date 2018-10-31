# pdfFieldLister
[![Build Status](https://travis-ci.org/NicklasAndersson/pdfFieldLister.svg?branch=master)](https://travis-ci.org/NicklasAndersson/pdfFieldLister)

### Run
```bash
java -jar target/pdfFieldLister-1.6.jar "filename.pdf"    
``` 
Expected output:
```
Field name : FieldType : Value   
``` 

### Release
```bash
 ./mvnw gitflow:release 
```

### Build
```bash
 ./mvnw package
```