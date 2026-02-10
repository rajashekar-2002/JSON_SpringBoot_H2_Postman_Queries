
Basic operations on a JSON file using Spring Boot

Add Record
POST http://localhost:8080/api/dataset/employee_dataset/record

example:
{
  "id": 1,
  "name": "Raja",
  "age": 22,
  "department": "Engineering"
}


 ![image.png](https://github.com/rajashekar-2002/JSON_SpringBoot_H2_Postman_Queries/blob/master/Output/Add_record.png)


 GroupBy

GET http://localhost:8080/api/dataset/employee_dataset/query?groupBy=department

 ![image.png](https://github.com/rajashekar-2002/JSON_SpringBoot_H2_Postman_Queries/blob/master/Output/GroupBy.png)



 SortBy

 GET http://localhost:8080/api/dataset/employee_dataset/query?sortBy=age&order=asc

  ![image.png](https://github.com/rajashekar-2002/JSON_SpringBoot_H2_Postman_Queries/blob/master/Output/SortBy_orderBy.png)


H2 Database
 ![image.png](https://github.com/rajashekar-2002/JSON_SpringBoot_H2_Postman_Queries/blob/master/Output/H2_Database.png)
