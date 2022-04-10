## InfinitiesSoft Take Home
1. Design a library system including CRUD functionalities and test cases
2. Wrap as Docker container
3. Push to gitHub 

## Test Cases Design
Make below classes coverage to 100% and additional test cases
- **BookService** additional test cases
    1. getAllBooks --> no additional
    2. createBook 
       - If book existed
       - If missing attributes
    3. updateBook
       - If there is no updated attribute
       - If book doesn't exist
    4. deleteBook
       - If book doesn't exist
- **BookValidator** additional test cases
   1. isAvailableForUpdate --> no additional
   2. isAvailableForCreate --> no additional

## Test Steps
1. `cd ./infinity_home_tesing`
2. run `mvn test`

## Test Result
![image](https://user-images.githubusercontent.com/48560984/162599288-b706f127-8539-4923-8cad-9bed566a58d3.png)

## Server Setup Steps (with Docker)
1. clone this project
2. `cd ./infinity_home_tesing`
2. run `mvn install`
3. run `docker build -t evislibrary:1.0 .`
4. run `docker run -p 8080:8080 evislibrary:1.0`
5. import this postman collection in your postman
   ```
   https://www.getpostman.com/collections/dd9f826641a7d3b4daaa
   ```
6. Use postman to test CRUD functions

## Postman Testing Result
0. I used Response Object to unify all response\
   ```json
   // All response format including exception
   {
      "timeStamp": "Current time stamp",
      "status": "Current Status code",
      "message": "Processing Message",
      "data": {"Data_key": "Data_value"}
   }
   ```
1. `/book/all`\
   Fetching all books with all details, there are two books in default (TestBook, TestBook2)
   ```json
   // Example Request
   // Nothing
   
   // Example Response
   {
       "timeStamp": "2022-04-10T00:40:43.134813",
       "status": 200,
       "message": "fetching all books",
       "data": {
           "books": [
               {
                   "isbn": 200,
                   "name": "TestBook",
                   "author": "TestAuthor",
                   "translator": "TestTranslator",
                   "publisher": "TestPublisher",
                   "publishDate": null,
                   "price": 20.00,
                   "currency": "TWD"
               },
               {
                   "isbn": 201,
                   "name": "TestBook2",
                   "author": "TestAuthor",
                   "translator": "TestTranslator",
                   "publisher": "TestPublisher",
                   "publishDate": "2022-04-09T16:40:08.000+00:00",
                   "price": 25.00,
                   "currency": "USD"
               }
           ]
       }
   } 
   ```
2. `/book/create`\
   Create the book with name TestBook4, name, author, publisher, price are mandatory
   ```json
   // Example Request 
   {
       "name": "TestBook4",
       "author": "TestAuthor",
       "translator": "TestTranslator",
       "publisher": "TestPublisher",
       "publishDate": "2022-04-09T14:53:34.000+00:00",
       "price": 25.00,
       "currency": "USD"
   }
   
   // Example Response if success
   {
       "timeStamp": "2022-04-09T16:40:27.385319",
       "status": 200,
       "message": "create the book successfully",
       "data": {
           "createdBook": {
               "isbn": 1,
               "name": "TestBook4",
               "author": "TestAuthor",
               "translator": "TestTranslator",
               "publisher": "TestPublisher",
               "publishDate": "2022-04-09T14:53:34.000+00:00",
               "price": 25.00,
               "currency": "USD"
           }
       }
   }
   
   // Example Response if name existed
   {
       "status": 500,
       "message": "Book with current Name already exited, please use update"
   }
   
   // Example Response if missing attribute
   {
       "status": 500,
       "message": "not-null property references a null or transient value : com.infinities.library.models.BookModel.author; nested exception is org.hibernate.PropertyValueException: not-null property references a null or transient value : com.infinities.library.models.BookModel.author"
   }
   ```
3. `/book/update`\
   Update the book with given name or isbn
   ```json
   // Example Request for updating the author 
   {
       "name": "TestBook4",
       "author": "TestAuthor222"
   }
   
   // Example Response
   {
       "timeStamp": "2022-04-10T00:24:23.529541",
       "status": "OK",
       "message": "update the book successfully",
       "data": {
           "updatedBook": {
               "isbn": 1,
               "name": "TestBook4",
               "author": "TestAuthor222",
               "translator": "TestTranslator",
               "publisher": "TestPublisher",
               "publishDate": "2022-04-09T14:53:34.000+00:00",
               "price": 25.00,
               "currency": "USD"
           }
       }
   }
   
   // Example Response if book doesn't exist
   {
       "status": 500,
       "message": "There is no book with current name or ISBN"
   }
   
   // Example Response if there is no updated attriubutes
   {
       "status": 500,
       "message": "At least put one attribute to update"
   }
   ```
4. `/book/delete`\
   Delete the book with isbn
   ```json
   // Example Request Params 
   isbn:1
   
   // Example Response if successfully update
   {
       "timeStamp": "2022-04-10T09:08:12.01728",
       "status": 200,
       "data": {
           "isDeletedBook": true
       }
   }
   
   // Example Response if there is no book with given isbn
   {
       "status": 500,
       "message": "There is no book with given isbn"
   }
   
   ```
