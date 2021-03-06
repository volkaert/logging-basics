# Welcome to Logging Basics !

## To start the server

```
./mvnw spring-boot:run
```


## To call the API - Basic Logging

```
curl localhost:8080/operation1/ABC
```
You should see the logs in the console of the server.


## To call the API - Hibernate Logging

```
curl localhost:8080/todolists
curl localhost:8080/todolists/1
```
You should see the logs in the console of the server.

Example of logs:
```
drop table if exists todo_item CASCADE 
drop table if exists todo_list CASCADE 
drop table if exists todo_list_items CASCADE 
create table todo_item (id bigint generated by default as identity, name varchar(255), priority integer not null, primary key (id))
create table todo_list (id bigint generated by default as identity, primary key (id))
create table todo_list_items (todo_list_id bigint not null, items_id bigint not null, primary key (todo_list_id, items_id))
alter table todo_list_items add constraint UK_2lhntg37yteqcdair9k8ks2ui unique (items_id)
alter table todo_list_items add constraint FK4hr3ya7imbnxe7jwmyxgmcps8 foreign key (items_id) references todo_item
alter table todo_list_items add constraint FKlb5hrbbttqbje7fntc7g8hxqm foreign key (todo_list_id) references todo_list
...
insert into todo_list (id) values (null)
insert into todo_item (id, name, priority) values (null, ?, ?)
insert into todo_item (id, name, priority) values (null, ?, ?)
insert into todo_list_items (todo_list_id, items_id) values (?, ?)
insert into todo_list_items (todo_list_id, items_id) values (?, ?)
...
select todolist0_.id as id1_1_0_, items1_.todo_list_id as todo_lis1_2_1_, todoitem2_.id as items_id2_2_1_, todoitem2_.id as id1_0_2_, todoitem2_.name as name2_0_2_, todoitem2_.priority as priority3_0_2_ from todo_list todolist0_ left outer join todo_list_items items1_ on todolist0_.id=items1_.todo_list_id left outer join todo_item todoitem2_ on items1_.items_id=todoitem2_.id where todolist0_.id=?
```

If you activate the `org.hibernate.type.descriptor.sql` in the `resources/logback-spring.xml` file, using:  
```
    <logger name="org.hibernate.type.descriptor.sql" level="TRACE" additivity="false">
        <appender-ref ref="CONSOLE"/>
    </logger>
```
then you will be able to see in the logs the used bind parameter values:
```
insert into todo_list (id) values (null)
insert into todo_item (id, name, priority) values (null, ?, ?)
binding parameter [1] as [VARCHAR] - [task2]
binding parameter [2] as [INTEGER] - [1]
insert into todo_item (id, name, priority) values (null, ?, ?)
binding parameter [1] as [VARCHAR] - [task1]
binding parameter [2] as [INTEGER] - [1]
insert into todo_list_items (todo_list_id, items_id) values (?, ?)
binding parameter [1] as [BIGINT] - [1]
binding parameter [2] as [BIGINT] - [1]
insert into todo_list_items (todo_list_id, items_id) values (?, ?)
binding parameter [1] as [BIGINT] - [1]
binding parameter [2] as [BIGINT] - [2]
```


## Example of Hibernate statistics
If you set `hibernate.generate_statistics=true` in the `resources/hibernate.properties` file, here is an example of 
the statistics provided by Hibernate (logged at the end of each Hibernate session):
```
232952 nanoseconds spent acquiring 1 JDBC connections;
0 nanoseconds spent releasing 0 JDBC connections;
178347 nanoseconds spent preparing 1 JDBC statements;
899348 nanoseconds spent executing 1 JDBC statements;
0 nanoseconds spent executing 0 JDBC batches;
0 nanoseconds spent performing 0 L2C puts;
0 nanoseconds spent performing 0 L2C hits;
0 nanoseconds spent performing 0 L2C misses;
2532173 nanoseconds spent executing 1 flushes (flushing a total of 3 entities and 1 collections);
0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
```
