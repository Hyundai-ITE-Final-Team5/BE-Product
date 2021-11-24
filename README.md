## Product

### navbar

| API | 설명 | input data |
| --- | --- | --- |
| get  /navbar/categoryList |  | ?depth1={categoryname} |
| get  /navbar/brandList |  |  |

### productList

| API | 설명 | input data |
| --- | --- | --- |
| get  /list/brand/{bno} |  | ?pageNo=1 |
| get  /list/category |  | ?depth1=WOMEN&depth2=DRESS&depth3=MINI%20DRESS |
| get  /list/addlike/{pid} |  |  |
| get  /list/delike/{pid} |  |  |
|  |  |  |

### productDetail

| API | 설명 | input data |
| --- | --- | --- |
| get  /product/{pcid} |  |  |
| get  /product/exit/{pcid} |  | ?depth1=WOMEN&depth2=DRESS&depth3=MINI%20DRESS |
