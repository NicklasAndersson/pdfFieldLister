# pdfFieldLister
[![Build Status](https://travis-ci.org/NicklasAndersson/pdfFieldLister.svg?branch=master)](https://travis-ci.org/NicklasAndersson/pdfFieldLister)

Lists all form fields in a pdf. Can be used to find hidden fields or to help when mapping a pdf to a different data structure. 

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
