# TEST TASK

### How to run?

1. gradle installSumService
2. cd build
3. java -jar sum_service.jar

### How to tests?
1. gradle test

### Entry points

1. /add
2. /remove
3. /sum

### Examples

1. Adding a name:value pair to the DB.

<pre>
   Relative URL:
       /add
   Request:
        {
            "name": "test",
            "value": 1
        }
   Response:
        {
            "code": 0,
            "description": "OK"
        }
</pre>

2. Removing a name:value from DB by name.

<pre>
   Relative URL:
        /remove
   Request:
        {
            "name": "test"
        }
   Response:
        {
            "code": 0,
            "description": "OK"
        }
</pre>

3. Getting the sum of two numbers identified by their names.

<pre>
   Relative URL:
        /sum
   Request:
        {
            "first": "test",
            "second": "test"
        }
   Response:
        {
            "sum": 2,
            "code": 0,
            "description": "OK"
        }
</pre>

### Status codes

| Code | Name              | Description                         |
|------|-------------------|-------------------------------------|
| 0    | OK                | OK                                  |
| 1    | NO_NAME           | NAME ISN'T PRESENT                  |
| 2    | NO_VALUE          | VALUE ISN'T PRESENT                 |
| 3    | ALREADY_EXISTS    | ENTRY WITH THIS NAME ALREADY EXISTS |
| 4    | NOTHING_TO_DELETE | NOTHING TO DELETE BY THIS NAME      |
| 5    | NO_DATA           | NO DATA                             |
| 6    | NO_FIRST_NUMBER   | THERE IS NO FIRST NAME              |
| 7    | NO_SECOND_NUMBER  | THERE IS NO SECOND NAME             |
| 8    | NOT_NUMBER_FIRST  | THE FIRST IS NOT A NUMBER           |
| 9    | NOT_NUMBER_SECOND | THE SECOND IS NOT A NUMBER          |
